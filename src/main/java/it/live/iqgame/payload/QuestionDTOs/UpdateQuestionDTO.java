package it.live.iqgame.payload.QuestionDTOs;

import it.live.iqgame.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateQuestionDTO {
    private String correctAnswer;
    private List<String> additiveAnswer;
    private QuestionType questionType;
    private String questionValue;
}
