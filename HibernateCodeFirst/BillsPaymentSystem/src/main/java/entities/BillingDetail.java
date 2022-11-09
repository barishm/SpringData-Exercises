package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "billing_detail")
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @ManyToMany(mappedBy = "billingDetails")
    private Set<User> users;

    public BillingDetail(String number, Set<User> users) {
        this.number = number;
        this.users = users;
    }
}
