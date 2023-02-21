package hu.elte.joooble.security;

import hu.elte.joooble.domain.user.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JooobleUserPrincipal implements UserDetails {

	final private User user;

	public JooobleUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

		if(user instanceof Employee)
			authorities.add(Role.ROLE_EMPLOYEE);
		else if(user instanceof Employer)
			authorities.add(Role.ROLE_EMPLOYER);
		else
			authorities.add(Role.ROLE_ADMIN);

		return authorities;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getCredential().getPassword();
	}

	@Override
	public String getUsername() {
		return user.getCredential().getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
