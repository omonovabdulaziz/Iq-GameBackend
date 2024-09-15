package it.live.iqgame.repository;

import it.live.iqgame.entity.DemoQuestions;
import it.live.iqgame.entity.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoQuestionRepository extends JpaRepository<DemoQuestions, Long> {
    Page<DemoQuestions> findAllByQuestionType(QuestionType questionType, Pageable of);

    List<DemoQuestions> findAllByLevelIdOrderByCreatedAtAsc(Long levelId);
}
