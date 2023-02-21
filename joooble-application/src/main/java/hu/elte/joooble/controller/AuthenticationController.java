package hu.elte.joooble.controller;

import hu.elte.joooble.security.SecurityHelper;
import hu.elte.joooble.webdomain.authentication.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthenticationController {
	@GetMapping("login")
	public String loginForm(LoginForm loginForm) {
		return "login";
	}

	//@ModelAttribute("userIsLoggedIn")
	//public boolean getCurrentUserTypeIsEmployer() {
	//	return SecurityHelper.isLoggedIn();
	//}
}
