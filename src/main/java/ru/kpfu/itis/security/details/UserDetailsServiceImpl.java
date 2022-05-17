package ru.kpfu.itis.security.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.repositories.UsersRepository;

import java.util.Optional;

@Service("customUserDetailsImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       log.info("loading user by username");
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        } else {
            throw new SecurityException("User with email <" + email + "> not found");
        }
    }
}
