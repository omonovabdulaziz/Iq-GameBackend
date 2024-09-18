package it.live.iqgame.utils;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.repository.AttemptsRepository;
import it.live.iqgame.repository.UsedKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatingKeyBall {
    private final AttemptsRepository attemptsRepository;
    private final UsedKeyRepository usedKeyRepository;

    public Long calculate(QuestionType questionType, Long userId, Long subjectId) {
        Long count = attemptsRepository.countAllByUserIdAndSubjectIdAndQuestionTypeAndIsFinished(userId, subjectId, questionType, true);
        if (questionType == QuestionType.IMAGE) {
            return count - usedKeyRepository.countAllByUserIdAndSubjectId(userId, subjectId);
        } else {
            return count;
        }
    }
}
