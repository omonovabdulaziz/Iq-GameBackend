package it.live.iqgame.repository;

import it.live.iqgame.entity.Question;
import it.live.iqgame.entity.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByQuestionType(QuestionType questionType, Pageable pageable);

    List<Question> findAllByLevelIdOrderByCreatedAtAsc(Long levelId);
}