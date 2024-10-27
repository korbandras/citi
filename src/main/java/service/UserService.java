package service;

import exceptions.UserServiceException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import repository.pojo.UserRequest;
import repository.pojo.UserResponse;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Long id) throws UserServiceException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserServiceException("User not found"));
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .domains(user.getDomains())
                .build();
    }


    @Transactional
    public UserResponse createUser(UserRequest userRequest) throws UserServiceException {
        User user = userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .username(userRequest.getUsername())
                        .domains(userRequest.getDomains())
                        .build());
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .domains(user.getDomains())
                .build();
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
