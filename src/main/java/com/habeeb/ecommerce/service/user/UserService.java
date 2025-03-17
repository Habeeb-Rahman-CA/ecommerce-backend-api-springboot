package com.habeeb.ecommerce.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.habeeb.ecommerce.exceptions.ResourceNotFoundException;
import com.habeeb.ecommerce.model.User;
import com.habeeb.ecommerce.repository.UserRepository;
import com.habeeb.ecommerce.request.CreateUserReq;
import com.habeeb.ecommerce.request.UpdateUserReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserReq req) {
        return Optional.of(req).filter(user -> !userRepository.existsByEmail(req.getEmail())).map(request -> {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setName(request.getName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User updateUser(UpdateUserReq req, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setName(req.getName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () -> {
            throw new ResourceNotFoundException("User not found");
        });
    }
    
}
