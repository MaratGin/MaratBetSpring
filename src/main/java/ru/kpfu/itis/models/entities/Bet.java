package ru.kpfu.itis.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "match_id")
    private int matchId;

    @Column(name = "outcome")
    private int outcome;

    @Column(name = "k")
    private double k;

    @ManyToMany
    @JoinTable(name = "user_bet",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "bet_id"))
    @Fetch(value = FetchMode.JOIN)
    private List<User> users;

}
