package com.edw.service;

import com.edw.model.AppUser;
import com.edw.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AppUserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean register(String username, String password) {
        if (users.existsByUsername(username)) {
            return false;
        }

        users.save(new AppUser(username, passwordEncoder.encode(password)));
        return true;
    }
}
