package ru.kpfu.itis.models.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBetForm {
    private Long userId;
    private Long betId;
    private Long matchId;
    private Integer value;
    private Integer outcome;
}
