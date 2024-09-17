package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.QuestionDTOs.*;
import it.live.iqgame.service.DemoQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demoQuestion")
@RequiredArgsConstructor
@Tag(name = "DemoQuestion Management", description = "APIs for managing DemoQuestions")
public class DemoQuestionsController {
    private final DemoQuestionService demoQuestionService;

    @Operation(
            summary = "Create a new demo question (Accessible by ROLE_ADMIN)",
            description = "Creates a new demo question with the provided type, level ID, and other details. Optionally includes a file."
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
        return demoQuestionService.createQuestion(type, CreateQuestionDTO.builder()
                .additiveAnswer(additiveAnswer)
                .levelId(levelId)
                .correctAnswer(correctAnswer)
                .questionValue(questionValue)
                .build(), file);
    }

    @Operation(
            summary = "Update an existing demo  question (Accessible by ROLE_ADMIN)",
            description = "Updates the demo  question with the specified ID using the provided details and optional file."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{questionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateQuestion(
            @PathVariable Long questionId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam String correctAnswer,
            @RequestParam(required = false) List<String> additiveAnswer,
            @RequestParam QuestionType questionType,
            @RequestParam String questionValue) {
        return demoQuestionService.updateQuestion(questionId, file, UpdateQuestionDTO.builder()
                .additiveAnswer(additiveAnswer)
                .correctAnswer(correctAnswer)
                .questionType(questionType)
                .questionValue(questionValue)
                .build());
    }

    @Operation(
            summary = "Delete a demo question (Accessible by ROLE_ADMIN)",
            description = "Deletes the demo  question with the specified ID."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{questionID}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long questionID) {
        return demoQuestionService.delete(questionID);
    }

    @Operation(
            summary = "Get all demo image questions (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all demo image-based questions."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllImgQuestions")
    public Page<GetImgQuestionDTO> GetAllQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return demoQuestionService.getAllImgQuestions(page, size);
    }

    @Operation(
            summary = "Get all test demo questions (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all demo test-based questions."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllTestQuestions")
    public Page<GetTestQuestionDTO> getAllTestQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return demoQuestionService.getAllTestQuestions(page, size);
    }

    @Operation(
            summary = "Get test demo questions by level ID (Accessible by ROLE_ADMIN and ROLE_USER)",
            description = "Retrieves a list of test demo questions associated with the given level ID."
    )
    @GetMapping("/getQuestionByLevelId/{levelId}")
    public List<TestQuestionDTO> getTestQuestionByLevelId(@PathVariable Long levelId) {
        return demoQuestionService.getTestQuestionByLevelId(levelId);
    }
}
