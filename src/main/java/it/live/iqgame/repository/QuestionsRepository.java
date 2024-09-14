package it.live.iqgame.repository;

import it.live.iqgame.entity.Collection;
import it.live.iqgame.entity.Question;
import it.live.iqgame.entity.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByQuestionType(QuestionType questionType, Pageable pageable);

    List<Question> findAllByLevelIdOrderByCreatedAtAsc(Long levelId);

    List<Question> findByLevel_CollectionOrderByCreatedAtDesc(Collection level_collection, Pageable of);
}