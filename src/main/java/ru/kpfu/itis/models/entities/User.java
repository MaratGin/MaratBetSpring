package ru.kpfu.itis.models.entities;

import lombok.*;
import ru.kpfu.itis.enums.Role;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "confirmed")
    private Integer confirmed;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "registration")
    private Timestamp registration;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_bet",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "bet_id"))
//    private List<Bet> bets;
}
