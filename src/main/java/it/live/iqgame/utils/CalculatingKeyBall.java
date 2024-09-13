package it.live.iqgame.utils;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.repository.AttemptsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatingKeyBall {
    private final AttemptsRepository attemptsRepository;

    public Integer calculate(QuestionType questionType, Long userId, Long subjectId) {
        if (questionType == QuestionType.IMAGE) {
            return attemptsRepository.countAllByUserIdAndSubjectIdAndQuestionTypeAndIsFinished(userId, subjectId, questionType, true);
        } else {
            return attemptsRepository.countAllByUserIdAndSubjectIdAndQuestionTypeAndIsFinished(userId, subjectId, questionType, false);
        }
    }
}
