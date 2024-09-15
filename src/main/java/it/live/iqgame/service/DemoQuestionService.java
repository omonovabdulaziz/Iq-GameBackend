package it.live.iqgame.service;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.QuestionDTOs.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DemoQuestionService {

    ResponseEntity<ApiResponse> createQuestion(QuestionType type, CreateQuestionDTO build, MultipartFile file);

    ResponseEntity<ApiResponse> updateQuestion(Long questionId, MultipartFile file, UpdateQuestionDTO build);

    ResponseEntity<ApiResponse> delete(Long questionID);

    Page<GetImgQuestionDTO> getAllImgQuestions(int page, int size);

    Page<GetTestQuestionDTO> getAllTestQuestions(int page, int size);

    List<TestQuestionDTO> getTestQuestionByLevelId(Long levelId);

}
