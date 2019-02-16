package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Role;
import etu.uportal.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        try {
            log.info("email");
            log.info(email);
            final User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.getMap().get(user.getRoleId())));

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), true, true, true, true, grantedAuthorities);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


}