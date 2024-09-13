package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.Question;
import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.exception.MainException;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.payload.QuestionDTOs.*;
import it.live.iqgame.repository.LevelRepository;
import it.live.iqgame.repository.QuestionsRepository;
import it.live.iqgame.service.QuestionsService;
import it.live.iqgame.utils.FileComposer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final LevelRepository levelRepository;

    @Override
    public ResponseEntity<ApiResponse> createQuestion(QuestionType type, CreateQuestionDTO createQuestionDTO, MultipartFile file) {
        if (type == QuestionType.IMAGE) if (file.isEmpty())
            throw new NotFoundException("Siz Image question tangladingiz va rasm qo'shishingiz shart");
        Question question = new Question();
        question.setQuestionType(type);
        question.setLevel(levelRepository.save(levelRepository.findById(createQuestionDTO.getLevelId()).orElseThrow(() -> new NotFoundException("Level topilmadi"))));
        if (file != null) {
            question.setImgUrl(FileComposer.imageUploader(file));
        }
        question.setAdditiveAnswers(createQuestionDTO.getAdditiveAnswer());
        question.setCorrectAnswer(createQuestionDTO.getCorrectAnswer());
        questionsRepository.save(question);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateQuestion(Long questionId, MultipartFile file, UpdateQuestionDTO updateQuestionDTO) {
        Question question = questionsRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        if (updateQuestionDTO.getQuestionType() == QuestionType.IMAGE)
            if (file == null) throw new MainException("File bilan taminlanishi kerak");
        question.setCorrectAnswer(updateQuestionDTO.getCorrectAnswer());
        question.setQuestionType(updateQuestionDTO.getQuestionType());
        question.setAdditiveAnswers(updateQuestionDTO.getAdditiveAnswer());
        if (file != null) question.setImgUrl(FileComposer.imageUploader(file));
        questionsRepository.save(question);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long questionID) {
        questionsRepository.deleteById(questionID);
        return ResponseEntity.ok(ApiResponse.builder().message("deleted").status(200).build());
    }

    @Override
    public Page<GetImgQuestionDTO> getAllImgQuestions(int page, int size) {
        Page<Question> questionPage = questionsRepository.findAllByQuestionType(QuestionType.IMAGE, PageRequest.of(page, size));
        return questionPage.map(question -> {
            GetLevelDTO getLevelDTO = GetLevelDTO.builder().id(question.getLevel().getId()).title(question.getLevel().getTitle()).collectionName(question.getLevel().getCollection().getTitle()).educationName(question.getLevel().getCollection().getSubject().getEducation().getName()).build();

            return GetImgQuestionDTO.builder().id(question.getId()).imgName(question.getImgUrl()).correctAnswer(question.getCorrectAnswer()).getLevelDTO(getLevelDTO).build();
        });
    }

    @Override
    public Page<GetTestQuestionDTO> getAllTestQuestions(int page, int size) {
        Page<Question> questionPage = questionsRepository.findAllByQuestionType(QuestionType.TEST, PageRequest.of(page, size));
        return questionPage.map(question -> GetTestQuestionDTO.builder().id(question.getId()).correctAnswer(question.getCorrectAnswer()).additiveAnswer(question.getAdditiveAnswers()).build());
    }


    @Override
    public List<TestQuestionDTO> getTestQuestionByLevelId(Long levelId) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        List<Question> questions = questionsRepository.findAllByLevelIdOrderByCreatedAtAsc(levelId);

        List<TestQuestionDTO> testQuestionDTOs = questions.stream()
                .map(question -> TestQuestionDTO.builder()
                        .id(question.getId())
                        .correctAnswer(question.getCorrectAnswer())
                        .imgUrl(question.getImgUrl())
                        .questionType(question.getQuestionType())
                        .additiveAnswer(question.getAdditiveAnswers())
                        .correctAnswerLength(question.getCorrectAnswer().length())
                        .build())
                .collect(Collectors.toList());
        if (systemUser.getRoleName() == RoleName.ADMIN) {
            return testQuestionDTOs;
        } else {
            for (TestQuestionDTO testQuestionDTO : testQuestionDTOs) {
                testQuestionDTO.setCorrectAnswer(null);
                if (testQuestionDTO.getQuestionType() == QuestionType.TEST) {
                    testQuestionDTO.setCorrectAnswerLength(0);
                    testQuestionDTO.setImgUrl(null);
                } else {
                    testQuestionDTO.setAdditiveAnswer(null);
                }
            }
            return testQuestionDTOs;
        }
    }


}