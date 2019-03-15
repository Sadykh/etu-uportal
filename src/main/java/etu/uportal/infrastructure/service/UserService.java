package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.repository.UserRepository;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(final UserDto dto) {
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        final User user = new User(dto.getEmail(), passwordHash, dto.getRoleId());
        return userRepository.save(user);
    }

    public Page<User> getAll(OffsetLimitPageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }


    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
