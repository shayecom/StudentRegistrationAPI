package studentsRegistrationAPI.studentRegistrationAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentsRegistrationAPI.studentRegistrationAPI.model.Course;
import studentsRegistrationAPI.studentRegistrationAPI.repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Long createCourse(Course course) {
        return courseRepository.createCourse(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}
