package it.live.iqgame.repository;

import it.live.iqgame.entity.Attempts;
import it.live.iqgame.entity.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttemptsRepository extends JpaRepository<Attempts, UUID> {
    Long countAllByUserIdAndSubjectIdAndQuestionTypeAndIsFinished(Long user_id, Long subject_id, QuestionType questionType, Boolean isFinished);
}