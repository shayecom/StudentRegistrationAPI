package studentsRegistrationAPI.studentRegistrationAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;
import studentsRegistrationAPI.studentRegistrationAPI.repository.UserRepositoryImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryImp userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder().username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .authorities(buildAuthorities(userDetails))
                .disabled(userDetails.getActive() == 0)
                .build();
    }

    private String[] buildAuthorities(UserDetails userDetails) {
        List<String> authorities = new ArrayList<>();
        addCsvAuthorities(authorities, userDetails.getPermissions(), false);
        addCsvAuthorities(authorities, userDetails.getRoles(), true);
        return authorities.toArray(new String[0]);
    }

    private void addCsvAuthorities(List<String> authorities, String csv, boolean role) {
        if (csv == null || csv.isBlank()) {
            return;
        }
        for (String value : csv.split(",")) {
            String authority = value.trim();
            if (authority.isEmpty()) {
                continue;
            }
            if (role && !authority.startsWith("ROLE_")) {
                authority = "ROLE_" + authority;
            }
            authorities.add(authority);
        }
    }
}
