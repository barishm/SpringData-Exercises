package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 1000)
    private String comment;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;

    public Diagnose(String name) {
        this.name = name;
        this.patients = new HashSet<>();
    }
}
