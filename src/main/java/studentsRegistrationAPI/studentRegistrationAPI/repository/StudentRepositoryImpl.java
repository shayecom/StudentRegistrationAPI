package studentsRegistrationAPI.studentRegistrationAPI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import studentsRegistrationAPI.studentRegistrationAPI.model.Student;
import studentsRegistrationAPI.studentRegistrationAPI.repository.mapper.StudentMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String STUDENT_TABLE = "student";
    private static final String STUDENT_WITH_COURSE_SELECT = """
            SELECT s.id AS student_id,
                   s.first_name,
                   s.last_name,
                   s.course_id,
                   c.id AS joined_course_id,
                   c.name AS course_name,
                   c.start_date AS course_start_date
            FROM student s
            LEFT JOIN course c ON s.course_id = c.id
            """;

    @Override
    public Student createStudent(Student student) {
        String sql = "INSERT INTO " + STUDENT_TABLE + " (first_name, last_name, course_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setLong(3, student.getCourseId());
            return preparedStatement;
        }, keyHolder);

        return getStudentById(keyHolder.getKey().longValue());
    }

    @Override
    public Student getStudentById(Long studentId) {
        String sql = STUDENT_WITH_COURSE_SELECT + "WHERE s.id = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return jdbcTemplate.query(STUDENT_WITH_COURSE_SELECT, new StudentMapper());
    }
}
