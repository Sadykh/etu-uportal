package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.repository.UserRepository;
import etu.uportal.web.dto.user.UserCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCreateDto registerNewUserAccount(final UserCreateDto dto) {
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        final User user = new User(dto.getEmail(), passwordHash, dto.getRoleId());
        userRepository.save(user);
        dto.setPassword(null);
        return dto;
    }

    public UserCreateDto updateById(final long id, final UserCreateDto dto) {
        final User user = userRepository.getOne(id);
        user.setEmail(dto.getEmail());
        user.setRoleId(dto.getRoleId());
        if (dto.getPassword().length() > 1) {
            String passwordHash = passwordEncoder.encode(dto.getPassword());
            user.setPasswordHash(passwordHash);
        }
        userRepository.save(user);
        dto.setPassword(null);
        return dto;
    }

    public Page<User> getAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public User getOneById(long id) {
        return userRepository.getOne(id);
    }


    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
