package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Question Management", description = "APIs for managing questions")
public class QuestionsController {
    private final QuestionsService questionsService;

    @Operation(
            summary = "Create a new question (Accessible by ROLE_ADMIN)",
            description = "Creates a new question with the provided type, level ID, and other details. Optionally includes a file.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/add/{type}/{levelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createQuestion(
            @PathVariable QuestionType type,
            @RequestParam(required = false) MultipartFile file,
            @PathVariable Long levelId,
            @RequestParam(required = false) List<String> additiveAnswer,
            @RequestParam String correctAnswer,
            @RequestParam String questionValue) {
        return questionsService.createQuestion(type, CreateQuestionDTO.builder()
                .additiveAnswer(additiveAnswer)
                .levelId(levelId)
                .correctAnswer(correctAnswer)
                .questionValue(questionValue)
                .build(), file);
    }

    @Operation(
            summary = "Update an existing question (Accessible by ROLE_ADMIN)",
            description = "Updates the question with the specified ID using the provided details and optional file.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{questionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateQuestion(
            @PathVariable Long questionId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam String correctAnswer,
            @RequestParam(required = false) List<String> additiveAnswer,
            @RequestParam QuestionType questionType) {
        return questionsService.updateQuestion(questionId, file, UpdateQuestionDTO.builder()
                .additiveAnswer(additiveAnswer)
                .correctAnswer(correctAnswer)
                .questionType(questionType)
                .build());
    }

    @Operation(
            summary = "Delete a question (Accessible by ROLE_ADMIN)",
            description = "Deletes the question with the specified ID.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{questionID}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long questionID) {
        return questionsService.delete(questionID);
    }

    @Operation(
            summary = "Get all image questions (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all image-based questions.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllImgQuestions")
    public Page<GetImgQuestionDTO> GetAllQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionsService.getAllImgQuestions(page, size);
    }

    @Operation(
            summary = "Get all test questions (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all test-based questions.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllTestQuestions")
    public Page<GetTestQuestionDTO> getAllTestQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionsService.getAllTestQuestions(page, size);
    }

    @Operation(
            summary = "Get test questions by level ID (Accessible by ROLE_ADMIN and ROLE_USER)",
            description = "Retrieves a list of test questions associated with the given level ID.",
            tags = {"Question Management"}
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/getQuestionByLevelId/{levelId}")
    public List<TestQuestionDTO> getTestQuestionByLevelId(@PathVariable Long levelId) {
        return questionsService.getTestQuestionByLevelId(levelId);
    }
}
