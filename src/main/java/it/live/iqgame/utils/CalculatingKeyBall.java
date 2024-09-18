package it.live.iqgame.utils;

import it.live.iqgame.entity.UsedKey;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.repository.AttemptsRepository;
import it.live.iqgame.repository.UsedKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalculatingKeyBall {
    private final AttemptsRepository attemptsRepository;
    private final UsedKeyRepository usedKeyRepository;

    public Long calculate(QuestionType questionType, Long userId, Long subjectId) {
        Long count = attemptsRepository.countAllByUserIdAndSubjectIdAndQuestionTypeAndIsFinished(userId, subjectId, questionType, true);
        if (questionType == QuestionType.IMAGE) {
            Optional<UsedKey> optionalUsedKey = usedKeyRepository.findByUserIdAndSubjectId(userId, subjectId);
            return count - (optionalUsedKey.isEmpty() ? 0 : optionalUsedKey.get().getCount());
        } else {
            return count;
        }
    }
}
