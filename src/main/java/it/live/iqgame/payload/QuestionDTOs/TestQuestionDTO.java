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
public class TestQuestionDTO {
    private Long id;
    private String correctAnswer;
    private String imgUrl;
    private QuestionType questionType;
    private List<String> additiveAnswer;
    private int correctAnswerLength;
}