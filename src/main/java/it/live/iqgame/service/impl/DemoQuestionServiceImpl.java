package it.live.iqgame.service.impl;

import it.live.iqgame.entity.DemoQuestions;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.exception.MainException;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.payload.QuestionDTOs.*;
import it.live.iqgame.repository.DemoQuestionRepository;
import it.live.iqgame.repository.LevelRepository;
import it.live.iqgame.service.DemoQuestionService;
import it.live.iqgame.utils.FileComposer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoQuestionServiceImpl implements DemoQuestionService {
    private final DemoQuestionRepository demoQuestionRepository;
    private final LevelRepository levelRepository;

    @Override
    public ResponseEntity<ApiResponse> createQuestion(QuestionType type, CreateQuestionDTO createQuestionDTO, MultipartFile file) {
        if (type == QuestionType.IMAGE) if (file.isEmpty())
            throw new NotFoundException("Siz Image question tangladingiz va rasm qo'shishingiz shart");
        DemoQuestions demoQuestion = new DemoQuestions();
        demoQuestion.setQuestionType(type);
        demoQuestion.setLevel(levelRepository.save(levelRepository.findById(createQuestionDTO.getLevelId()).orElseThrow(() -> new NotFoundException("Level topilmadi"))));
        if (file != null) {
            demoQuestion.setImgUrl(FileComposer.imageUploader(file));
        }
        demoQuestion.setAdditiveAnswers(createQuestionDTO.getAdditiveAnswer());
        demoQuestion.setCorrectAnswer(createQuestionDTO.getCorrectAnswer());
        demoQuestionRepository.save(demoQuestion);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateQuestion(Long questionId, MultipartFile file, UpdateQuestionDTO updateQuestionDTO) {
        DemoQuestions question = demoQuestionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        if (updateQuestionDTO.getQuestionType() == QuestionType.IMAGE)
            if (file == null) throw new MainException("File bilan taminlanishi kerak");
        question.setCorrectAnswer(updateQuestionDTO.getCorrectAnswer());
        question.setQuestionType(updateQuestionDTO.getQuestionType());
        question.setAdditiveAnswers(updateQuestionDTO.getAdditiveAnswer());
        if (file != null) question.setImgUrl(FileComposer.imageUploader(file));
        demoQuestionRepository.save(question);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long questionID) {
        demoQuestionRepository.deleteById(questionID);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("deleted").build());
    }

    @Override
    public Page<GetImgQuestionDTO> getAllImgQuestions(int page, int size) {
        Page<DemoQuestions> questionPage = demoQuestionRepository.findAllByQuestionType(QuestionType.IMAGE, PageRequest.of(page, size));
        return questionPage.map(question -> {
            GetLevelDTO getLevelDTO = GetLevelDTO.builder().id(question.getLevel().getId()).title(question.getLevel().getTitle()).collectionName(question.getLevel().getCollection().getTitle()).educationName(question.getLevel().getCollection().getSubject().getEducation().getName()).build();

            return GetImgQuestionDTO.builder().id(question.getId()).imgName(question.getImgUrl()).correctAnswer(question.getCorrectAnswer()).getLevelDTO(getLevelDTO).build();
        });
    }


    @Override
    public Page<GetTestQuestionDTO> getAllTestQuestions(int page, int size) {
        Page<DemoQuestions> questionPage = demoQuestionRepository.findAllByQuestionType(QuestionType.TEST, PageRequest.of(page, size));
        return questionPage.map(question -> GetTestQuestionDTO.builder().id(question.getId()).correctAnswer(question.getCorrectAnswer()).additiveAnswer(question.getAdditiveAnswers()).build());
    }

    @Override
    public List<TestQuestionDTO> getTestQuestionByLevelId(Long levelId) {
        List<DemoQuestions> questions = demoQuestionRepository.findAllByLevelIdOrderByCreatedAtAsc(levelId);

        return questions.stream()
                .map(question -> TestQuestionDTO.builder()
                        .id(question.getId())
                        .correctAnswer(question.getCorrectAnswer())
                        .imgUrl(question.getImgUrl())
                        .questionType(question.getQuestionType())
                        .additiveAnswer(question.getAdditiveAnswers())
                        .correctAnswerLength(question.getCorrectAnswer().length())
                        .build())
                .toList();
    }
}
