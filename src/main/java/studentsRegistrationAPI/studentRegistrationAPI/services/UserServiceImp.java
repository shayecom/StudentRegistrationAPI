package studentsRegistrationAPI.studentRegistrationAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import studentsRegistrationAPI.studentRegistrationAPI.DTO.AuthenticationResponse;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;
import studentsRegistrationAPI.studentRegistrationAPI.repository.UserRepositoryImp;
import studentsRegistrationAPI.studentRegistrationAPI.util.JwtUtil;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepositoryImp userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void saveUser(UserDetails userDetails) {
        if (userRepository.findUserByUsername(userDetails.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userDetails.getRoles() == null || userDetails.getRoles().isBlank()) {
            userDetails.setRoles("USER");
        }
        if (userDetails.getPermissions() == null || userDetails.getPermissions().isBlank()) {
            userDetails.setPermissions("USER");
        }
        userDetails.setActive(1);
        userRepository.saveUser(userDetails);
    }

    @Override
    public UserDetails findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public AuthenticationResponse login(UserDetails userDetails) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
        UserDetails user = userRepository.findUserByUsername(userDetails.getUsername());
        return new AuthenticationResponse(jwtUtil.generateToken(user));
    }

    @Override
    public void logout(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            tokenBlacklistService.blacklist(authorizationHeader.substring(7));
        }
    }
}
