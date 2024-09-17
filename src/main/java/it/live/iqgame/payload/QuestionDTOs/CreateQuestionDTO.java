package it.live.iqgame.payload.QuestionDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionDTO {
    @NotNull(message = "Level id kiriting")
    private Long levelId;
    private List<String> additiveAnswer;
    @NotNull(message = "To'gri javobni  kiriting")
    private String correctAnswer;
    private String questionValue;
}
