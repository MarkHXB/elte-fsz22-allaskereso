package hu.elte.joooble.service;

import hu.elte.joooble.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isEmailAddressInUse(String email){
        return userRepository.findByCredential_Email(email) != null;
    }
}
