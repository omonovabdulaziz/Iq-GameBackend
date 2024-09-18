package it.live.iqgame.payload.AttemptDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptCreateDTO {
    private String userAnswer;
    private Long questionId;
}
