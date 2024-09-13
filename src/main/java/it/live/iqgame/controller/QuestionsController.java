package it.live.iqgame.controller;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.QuestionDTOs.CreateQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.GetImgQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.GetTestQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.UpdateQuestionDTO;
import it.live.iqgame.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionsController {
    private final QuestionsService questionsService;

    @PostMapping("/add/{type}")
    public ResponseEntity<ApiResponse> createQuestion(@PathVariable QuestionType type, @RequestPart CreateQuestionDTO createQuestionDTO, @RequestParam MultipartFile file) {
        return questionsService.createQuestion(type, createQuestionDTO, file);
    }

    @PutMapping("/update/{questionId}")
    public ResponseEntity<ApiResponse> updateQuestion(@PathVariable Long questionId, @RequestParam MultipartFile file, @RequestPart UpdateQuestionDTO updateQuestionDTO) {
        return questionsService.updateQuestion(questionId, file, updateQuestionDTO);
    }

    @DeleteMapping("/delete/{questionID}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long questionID) {
        return questionsService.delete(questionID);
    }

    @GetMapping("/getAllImgQuestions")
    public Page<GetImgQuestionDTO> GetAllQuestions() {
        return questionsService.getAllImgQuestions();
    }

    @GetMapping("/getAllTestQuestions")
    public Page<GetTestQuestionDTO> getAllTestQuestions() {
        return questionsService.getAllTestQuestions();
    }

    @GetMapping("/getQuestionByLevelId/{levelId}")
    public List<Object> getTestQuestionByLevelId(@PathVariable Long levelId) {
        return questionsService.getTestQuestionByLevelId(levelId);
    }
}