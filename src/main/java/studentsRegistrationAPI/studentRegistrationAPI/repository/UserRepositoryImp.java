package studentsRegistrationAPI.studentRegistrationAPI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;
import studentsRegistrationAPI.studentRegistrationAPI.repository.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImp implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String USER_TABLE = "users";
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDetails userDetails) {
        String customPassword = userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        String sql = "INSERT INTO " + USER_TABLE + " (username, password, permissions, roles) VALUES ( ?, ?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDetails.getUsername());
            preparedStatement.setString(2, customPassword);
            preparedStatement.setString(3, userDetails.getPermissions());
            preparedStatement.setString(4, userDetails.getRoles());
            return preparedStatement;
        }, keyHolder);

//        return keyHolder.getKey().longValue();
    }

    @Override
    public UserDetails login(UserDetails userDetails) {
        String sql = "SELECT * FROM " + USER_TABLE + " WHERE username = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), userDetails.getUsername(), userDetails.getPassword());
    }

    @Override
    public UserDetails findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_TABLE + " WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
