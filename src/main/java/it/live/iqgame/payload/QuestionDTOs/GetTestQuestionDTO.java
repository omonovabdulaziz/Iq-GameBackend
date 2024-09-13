package it.live.iqgame.payload.QuestionDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTestQuestionDTO {
    private Long id;
    private String correctAnswer;
    private List<String> additiveAnswer;
}
