package hu.elte.joooble.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

	ROLE_EMPLOYEE,ROLE_EMPLOYER, ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
