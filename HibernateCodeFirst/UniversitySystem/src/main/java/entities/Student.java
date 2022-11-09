package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avg_grade",nullable = false)
    private Double avgGrade;

    @Column
    private int attendance;

    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "students_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id",referencedColumnName = "id")
    )
    private Set<Course> courses;

    public Student(String firstName, String lastName, String phoneNumber, Double avgGrade, int attendance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.avgGrade = avgGrade;
        this.attendance = attendance;
    }
}
