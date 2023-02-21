package hu.elte.joooble.security;

import hu.elte.joooble.domain.user.Employer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class SecurityHelper {

	public static boolean isAdmin() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getAuthorities().contains(Role.ROLE_ADMIN);
	}
	public static boolean isEmployee() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getAuthorities().contains(Role.ROLE_EMPLOYEE);
	}
	public static boolean isEmployer() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getAuthorities().contains(Role.ROLE_EMPLOYER);
	}
	
	public static String getUsername() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	public static boolean isLoggedIn(){
		JooobleUserPrincipal userDetails = (JooobleUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUser() != null;
	}
}
