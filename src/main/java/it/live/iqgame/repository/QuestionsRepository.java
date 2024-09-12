package it.live.iqgame.repository;

import it.live.iqgame.entity.Education;
import it.live.iqgame.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

}