package studentsRegistrationAPI.studentRegistrationAPI.services;

import studentsRegistrationAPI.studentRegistrationAPI.DTO.AuthenticationResponse;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;

public interface UserService {
    void saveUser(UserDetails userDetails);

    UserDetails findUserByUsername(String username);

    AuthenticationResponse login(UserDetails userDetails) throws Exception;

    void logout(String authorizationHeader);
}
