package studentsRegistrationAPI.studentRegistrationAPI.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import studentsRegistrationAPI.studentRegistrationAPI.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Course(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDate("start_date").toLocalDate()
        );
    }
}
