package studentsRegistrationAPI.studentRegistrationAPI.repository;

import studentsRegistrationAPI.studentRegistrationAPI.model.Student;

import java.util.List;

public interface StudentRepository {
    Student createStudent(Student student);

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();
}
