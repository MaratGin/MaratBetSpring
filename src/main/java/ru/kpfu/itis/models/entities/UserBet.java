package ru.kpfu.itis.models.entities;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_bet")
public class UserBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bet_id")
    private Long betId;

    @Column(name = "outcome")
    private int outcome;

    @Column(name = "value")
    private double value;
}
