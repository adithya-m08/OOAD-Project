package com.example.blogging.services;

import com.example.blogging.dto.UserDto;
import com.example.blogging.models.User;
import com.example.blogging.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            User user =
                userRepository.findByUsername(userDto.getUsername()).get();
            return new UserDto(user.getId(), user.getUsername(),
                               user.getPassword(), user.getRole());
        }
        User user = new User(userDto.getUsername(), userDto.getPassword(),
                             userDto.getRole());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getUsername(),
                           savedUser.getPassword(), savedUser.getRole());
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
            ()
                -> new EntityNotFoundException(
                    "User not found with username: " + username));
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                           user.getRole());
    }

    public User getUserById(User author) {
        Optional<User> userOptional = userRepository.findById(author.getId());
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NoSuchElementException("User not found with id: " +
                                             author.getId());
        }
    }

    public UserDto findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return new UserDto(user.getId(), user.getUsername(),
                               user.getPassword(), user.getRole());
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }
}
