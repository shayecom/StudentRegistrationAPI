package studentsRegistrationAPI.studentRegistrationAPI.model;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private Long courseId;
    private Course course;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, Long courseId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseId = courseId;
    }

    public Student(Long id, String firstName, String lastName, Long courseId, Course course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseId = courseId;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
