package studentsRegistrationAPI.studentRegistrationAPI.repository;

import org.springframework.security.core.userdetails.User;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;

public interface UserRepository {

    void saveUser(UserDetails userDetails);

    UserDetails login(UserDetails userDetails);

    UserDetails findUserByUsername(String username);
}
