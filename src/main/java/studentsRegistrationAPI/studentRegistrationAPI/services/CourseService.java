package studentsRegistrationAPI.studentRegistrationAPI.services;

import studentsRegistrationAPI.studentRegistrationAPI.model.Course;

import java.util.List;

public interface CourseService {
    Long createCourse(Course course);

    Course getCourseById(Long courseId);

    List<Course> getAllCourses();
}
