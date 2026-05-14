package studentsRegistrationAPI.studentRegistrationAPI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import studentsRegistrationAPI.studentRegistrationAPI.model.Course;
import studentsRegistrationAPI.studentRegistrationAPI.repository.mapper.CourseMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private static final String COURSE_TABLE = "course";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long createCourse(Course course) {
        String sql = "INSERT INTO " + COURSE_TABLE + " (name, start_date) VALUES ( ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setObject(2, course.getStartDate());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Course getCourseById(Long courseId) {
        String sql = "SELECT * FROM " + COURSE_TABLE + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CourseMapper(), courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM " + COURSE_TABLE;
        return jdbcTemplate.query(sql, new CourseMapper());
    }
}
