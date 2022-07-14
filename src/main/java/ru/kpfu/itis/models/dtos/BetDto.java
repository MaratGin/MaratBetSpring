package ru.kpfu.itis.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetDto implements Serializable {
    private String name;
    private Integer matchId;
    private Integer value;
    private Integer outcome;
}
