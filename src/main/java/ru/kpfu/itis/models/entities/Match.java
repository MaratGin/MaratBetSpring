package ru.kpfu.itis.models.entities;


import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team1_logo_url")
    private String teamOneLogo;

    @Column(name = "team1_name")
    private String teamOneName;

    @Column(name = "team1_k")
    private double teamOneK;

    @Column(name = "team2_logo_url")
    private String teamTwoLogo;

    @Column(name = "team2_name")
    private String teamTwoName;

    @Column(name = "team2_k")
    private double teamTwoK;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "draw_k")
    private double drawK;
}