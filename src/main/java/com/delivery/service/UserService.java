package com.delivery.service;

import com.delivery.model.DTO.RegisterUserDTO;
import com.delivery.model.User;
import com.delivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return (UserDetails) user;
    }

    public boolean register(RegisterUserDTO userDTO) {
        User user = repository.findByEmail(userDTO.getEmail());
        if (user != null) {
            return false;
        }

        user = new User(userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        repository.save(user);
        return true;
    }
}
