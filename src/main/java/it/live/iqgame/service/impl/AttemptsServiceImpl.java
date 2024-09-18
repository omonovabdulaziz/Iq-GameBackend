package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.*;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.exception.MainException;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.repository.*;
import it.live.iqgame.service.AttemptsService;
import it.live.iqgame.utils.CalculatingKeyBall;
import it.live.iqgame.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {
    private final AttemptsRepository attemptsRepository;
    private final QuestionsRepository questionsRepository;
    private final RedisUtils redisUtils;
    private final UserCollectionRepository userCollectionRepository;
    private final UsedKeyRepository usedKeyRepository;
    private final CalculatingKeyBall calculatingKeyBall;
    private final SubjectRepository subjectRepository;

    @Override
    public ResponseEntity<ApiResponse> create(Long questionId, String userAnswer) {
        Question question = questionsRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question topilmadi"));
        User user = SecurityConfiguration.getOwnSecurityInformation();
        Object isLocked = redisUtils.find(user.getId().toString());
        if (isLocked != null) {
            throw new MainException("You are LOCKED please wait " + redisUtils.getTTL(user.getId().toString()) + " second");
        }
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

    @Override
    public ResponseEntity<ApiResponse> useKey(Long subjectId) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Long usedKeyCount = usedKeyRepository.countAllByUserIdAndSubjectId(systemUser.getId(), subjectId);
        long keysUser = usedKeyCount - calculatingKeyBall.calculate(QuestionType.IMAGE, systemUser.getId(), subjectId);
        if (keysUser < 0)
            throw new MainException("You have not enough key");
        Optional<UsedKey> optionalUsedKey = usedKeyRepository.findByUserIdAndSubjectId(systemUser.getId(), subjectId);
        if (optionalUsedKey.isPresent()) {
            UsedKey usedKey = optionalUsedKey.get();
            usedKey.setCount(usedKey.getCount() + 1);
            usedKeyRepository.save(usedKey);
            return ResponseEntity.ok(ApiResponse.builder().message("ok").status(200).object(true).build());
        } else {
            usedKeyRepository.save(UsedKey.builder().count(1L).subject(subjectRepository.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject topilmadi"))).user(systemUser).build());
            return ResponseEntity.ok(ApiResponse.builder().message("ok").status(200).object(true).build());
        }
    }
}