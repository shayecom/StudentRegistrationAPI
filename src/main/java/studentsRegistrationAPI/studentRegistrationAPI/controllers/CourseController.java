package studentsRegistrationAPI.studentRegistrationAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studentsRegistrationAPI.studentRegistrationAPI.model.Course;
import studentsRegistrationAPI.studentRegistrationAPI.services.CourseService;
import studentsRegistrationAPI.studentRegistrationAPI.services.CourseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;
    @PostMapping("/create")
    public Long createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/all")
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

}
