package studentsRegistrationAPI.studentRegistrationAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studentsRegistrationAPI.studentRegistrationAPI.DTO.AuthenticationResponse;
import studentsRegistrationAPI.studentRegistrationAPI.DTO.MessageResponse;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;
import studentsRegistrationAPI.studentRegistrationAPI.services.UserServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @PostMapping("/public/user/login")
    public AuthenticationResponse login(@RequestBody UserDetails userDetails) throws Exception {
        return userService.login(userDetails);
    }

    @PostMapping({"/public/user/signup", "/public/user/save"})
    public ResponseEntity<MessageResponse> saveUser(@RequestBody UserDetails userDetails) {
        userService.saveUser(userDetails);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/user/logout")
    public ResponseEntity<MessageResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {
        userService.logout(authorizationHeader);
        return ResponseEntity.ok(new MessageResponse("Logged out successfully"));
    }

    @GetMapping("/user/me")
    public String me(Authentication authentication) {
        return authentication.getName();
    }

}
