package it.live.iqgame.service;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.QuestionDTOs.CreateQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.GetImgQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.GetTestQuestionDTO;
import it.live.iqgame.payload.QuestionDTOs.UpdateQuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionsService {


    ResponseEntity<ApiResponse> createQuestion(QuestionType type, CreateQuestionDTO createQuestionDTO, MultipartFile file);

    ResponseEntity<ApiResponse> updateQuestion(Long questionId, MultipartFile file, UpdateQuestionDTO updateQuestionDTO);

    ResponseEntity<ApiResponse> delete(Long questionID);

    Page<GetImgQuestionDTO> getAllImgQuestions();

    Page<GetTestQuestionDTO> getAllTestQuestions();

    List<Object> getTestQuestionByLevelId(Long levelId);

}