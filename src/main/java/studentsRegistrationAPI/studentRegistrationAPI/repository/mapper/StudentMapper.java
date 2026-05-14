package studentsRegistrationAPI.studentRegistrationAPI.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import studentsRegistrationAPI.studentRegistrationAPI.model.Course;
import studentsRegistrationAPI.studentRegistrationAPI.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long courseId = rs.getLong("course_id");
        Long joinedCourseId = rs.getLong("joined_course_id");
        Course course = rs.wasNull()
                ? null
                : new Course(
                joinedCourseId,
                rs.getString("course_name"),
                rs.getDate("course_start_date").toLocalDate()
        );

        return new Student(
                rs.getLong("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                courseId,
                course
        );
    }
}
