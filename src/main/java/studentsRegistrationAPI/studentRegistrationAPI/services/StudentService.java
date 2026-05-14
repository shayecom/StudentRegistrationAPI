package studentsRegistrationAPI.studentRegistrationAPI.services;

import studentsRegistrationAPI.studentRegistrationAPI.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();
}
