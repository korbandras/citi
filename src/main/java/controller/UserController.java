package controller;

import exceptions.UserServiceException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.pojo.UserRequest;
import repository.pojo.UserResponse;
import service.UserService;

@RestController
public class UserController {

    private final Logger logger;
    private final UserService userService;

    public UserController(Logger logger, UserService userService) {
        this.logger = logger;
        this.userService = userService;
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestParam(name = "id") Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.OK);
        } catch (UserServiceException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
