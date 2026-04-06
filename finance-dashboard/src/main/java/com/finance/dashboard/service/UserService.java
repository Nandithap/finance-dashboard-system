package com.finance.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.entity.User;
import com.finance.dashboard.entity.Role;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.repository.RoleRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // ✅ Create User (with Role handling)
    public User saveUser(User user) {

        // Check if role is provided
        if (user.getRole() != null && user.getRole().getId() != null) {

            Long roleId = user.getRole().getId();

            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));

            user.setRole(role);
        }

        return userRepository.save(user);
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // ✅ Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ Delete all users
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
 // ✅ Update user
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setStatus(updatedUser.getStatus());

        // Update role if provided
        if (updatedUser.getRole() != null && updatedUser.getRole().getId() != null) {
            Long roleId = updatedUser.getRole().getId();
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
            user.setRole(role);
        }

        return userRepository.save(user);
    }
}