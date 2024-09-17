package it.live.iqgame.payload.QuestionDTOs;

import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetImgQuestionDTO {
    private Long id;
    private String imgName;
    private String correctAnswer;
    private GetLevelDTO getLevelDTO;
    private String questionValue;
}
