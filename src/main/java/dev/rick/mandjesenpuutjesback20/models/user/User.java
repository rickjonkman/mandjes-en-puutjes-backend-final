package dev.rick.mandjesenpuutjesback20.models.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private long userId;

    private String password;

    private String firstname;

    private boolean enabled;

    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Authority> authorities = new HashSet<>();

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }
}
