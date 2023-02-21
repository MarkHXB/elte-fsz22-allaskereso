package hu.elte.joooble.security;

import hu.elte.joooble.domain.user.Credential;
import hu.elte.joooble.domain.user.User;
import hu.elte.joooble.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JooobleUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        User user = userRepository.findByCredential_Email(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new JooobleUserPrincipal(user);
    }
}
