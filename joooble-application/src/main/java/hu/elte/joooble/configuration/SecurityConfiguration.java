package hu.elte.joooble.configuration;

import hu.elte.joooble.security.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // for dev
		/*http
				.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                .and()
                    .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                 .headers().frameOptions().sameOrigin(); // h2-console access;*/


        // for prod
    	http
        	.authorizeRequests()
				.antMatchers("/","/home").permitAll()
				.antMatchers("/register").permitAll()
        	 	.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/assets/images/**").permitAll()
        	 	.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/register-employer").permitAll()
				.antMatchers("/register-employee").permitAll()
				.antMatchers("/registrationEmployer").permitAll()
				.antMatchers("/registrationEmployee").permitAll()
				.antMatchers("/jobs/employer-**").hasRole("EMPLOYER")
				.antMatchers("/jobs/addJob/**").hasRole("EMPLOYER")
				.antMatchers("/jobs/hireEmployee**").hasRole("EMPLOYER")
				.antMatchers("/jobs/rejectEmployee**").hasRole("EMPLOYER")
				.antMatchers("/jobs/activate**").hasAnyRole("EMPLOYER","ADMIN")
				.antMatchers("/jobs/inactivate/**").hasAnyRole("EMPLOYER","ADMIN")
				.antMatchers("/jobs/applicants/**").hasAnyRole("EMPLOYER","ADMIN")
				.antMatchers("/jobs/addJob/**").hasAnyRole("EMPLOYER","ADMIN")
				.antMatchers("/jobs/employee-**").hasRole("EMPLOYEE")
				.anyRequest().authenticated()
				.and()
	    	 	.csrf().ignoringAntMatchers("/h2-console/**")
		 	.and()
	    		.headers().frameOptions().sameOrigin() // h2-console access
			.and()
        .formLogin()
				.loginPage("/login").permitAll()
				.and()
				.logout();
    }

    @Bean
    public PasswordEncoder passwordEncode() {
    	return new BCryptPasswordEncoder(12);
    }
}
