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
@Entity(name = "medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Visitation> visitations;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Patient> patients;

    public Medicament(String name) {
        this.name = name;
        this.patients = new HashSet<>();
        this.visitations = new HashSet<>();
    }
}
