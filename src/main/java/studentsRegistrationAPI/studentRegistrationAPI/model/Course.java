package studentsRegistrationAPI.studentRegistrationAPI.model;

import java.time.LocalDate;

public class Course {

    private Long id;
    private String name;
    private LocalDate startDate;

    public Course() {
    }

    public Course(Long id, String name, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
