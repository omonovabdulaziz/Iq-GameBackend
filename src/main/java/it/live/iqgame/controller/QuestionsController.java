package it.live.iqgame.controller;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.QuestionDTOs.*;
import it.live.iqgame.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionsController {
    private final QuestionsService questionsService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/add/{type}/{levelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createQuestion(@PathVariable QuestionType type, @RequestParam(required = false) MultipartFile file, @PathVariable Long levelId, @RequestParam(required = false) List<String> additiveAnswer, @RequestParam String correctAnswer) {
        return questionsService.createQuestion(type, CreateQuestionDTO.builder().additiveAnswer(additiveAnswer).levelId(levelId).correctAnswer(correctAnswer).build(), file);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{questionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateQuestion(
            @PathVariable Long questionId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam String correctAnswer,
            @RequestParam(required = false) List<String> additiveAnswer,
            @RequestParam QuestionType questionType) {
        return questionsService.updateQuestion(questionId, file, UpdateQuestionDTO.builder().additiveAnswer(additiveAnswer).correctAnswer(correctAnswer).questionType(questionType).build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{questionID}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long questionID) {
        return questionsService.delete(questionID);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllImgQuestions")
    public Page<GetImgQuestionDTO> GetAllQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionsService.getAllImgQuestions(page, size);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllTestQuestions")
    public Page<GetTestQuestionDTO> getAllTestQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionsService.getAllTestQuestions(page , size);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping("/getQuestionByLevelId/{levelId}")
    public List<TestQuestionDTO> getTestQuestionByLevelId(@PathVariable Long levelId) {
        return questionsService.getTestQuestionByLevelId(levelId);
    }
}