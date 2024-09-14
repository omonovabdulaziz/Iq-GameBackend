package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.*;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.repository.AttemptsRepository;
import it.live.iqgame.repository.QuestionsRepository;
import it.live.iqgame.repository.UserCollectionRepository;
import it.live.iqgame.service.AttemptsService;
import it.live.iqgame.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {
    private final AttemptsRepository attemptsRepository;
    private final QuestionsRepository questionsRepository;
    private final RedisUtils redisUtils;
    private final UserCollectionRepository userCollectionRepository;

    @Override
    public ResponseEntity<ApiResponse> create(Long questionId, String userAnswer) {
        User user = SecurityConfiguration.getOwnSecurityInformation();
        Question question = questionsRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question topilmadi"));
        Attempts attempts = new Attempts();
        attempts.setQuestion(question);
        attempts.setSubject(question.getLevel().getCollection().getSubject());
        attempts.setQuestionType(question.getQuestionType());
        attempts.setUser(user);
        if (question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
            attempts.setIsFinished(true);
            Collection collection = question.getLevel().getCollection();
            Question lastQuestion = questionsRepository.findByLevel_CollectionOrderByCreatedAtDesc(collection, PageRequest.of(0, 1)).get(0);
            if (lastQuestion != null && Objects.equals(lastQuestion.getId(), questionId))
                userCollectionRepository.save(UserCollection.builder().collection(collection).user(user).build());

        } else {
            attempts.setIsFinished(false);
            redisUtils.save(user.getId().toString(), "LOCKED");
        }
        attemptsRepository.save(attempts);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").object(question.getCorrectAnswer().equalsIgnoreCase(userAnswer)).build());
    }

    @Override
    public Page<GetAttemptsDTO> getAttempt(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getTTL() {
        String key = SecurityConfiguration.getOwnSecurityInformation().getId().toString();
        Long ttl = redisUtils.getTTL(key);
            if (ttl == null || ttl < 0) {
            return ResponseEntity.ok(new ApiResponse("ok", null, 200));
        }
        ApiResponse response = new ApiResponse("ok", ttl, 200);
        return ResponseEntity.ok(response);
    }
}