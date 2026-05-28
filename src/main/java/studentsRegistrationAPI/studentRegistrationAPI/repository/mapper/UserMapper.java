package studentsRegistrationAPI.studentRegistrationAPI.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import studentsRegistrationAPI.studentRegistrationAPI.model.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserDetails(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("permissions"),
                rs.getInt("active")
        );
    }
}
