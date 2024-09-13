package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.Attempts;
import it.live.iqgame.entity.Question;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.repository.AttemptsRepository;
import it.live.iqgame.repository.QuestionsRepository;
import it.live.iqgame.service.AttemptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {
    private final AttemptsRepository attemptsRepository;
    private final QuestionsRepository questionsRepository;

    @Override
    public ResponseEntity<ApiResponse> create(Long questionId, String userAnswer) {
        Question question = questionsRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question topilmadi"));
        Attempts attempts = new Attempts();
        attempts.setQuestion(question);
        attempts.setSubject(question.getLevel().getCollection().getSubject());
        attempts.setQuestionType(question.getQuestionType());
        attempts.setUser(SecurityConfiguration.getOwnSecurityInformation());
        if (question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
            attempts.setIsFinished(true);
        } else {
            attempts.setIsFinished(false);
        }
        attemptsRepository.save(attempts);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").object(question.getCorrectAnswer().equalsIgnoreCase(userAnswer)).build());
    }

    @Override
    public Page<GetAttemptsDTO> getAttempt(Long userId) {
        return null;
    }
}