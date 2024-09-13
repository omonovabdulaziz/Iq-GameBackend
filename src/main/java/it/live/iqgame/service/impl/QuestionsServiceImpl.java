package it.live.iqgame.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    @Override
    public ResponseEntity<ApiResponse> createQuestion(QuestionType type, CreateQuestionDTO createQuestionDTO, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updateQuestion(Long questionId, MultipartFile file, UpdateQuestionDTO updateQuestionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long questionID) {
        return null;
    }

    @Override
    public Page<GetImgQuestionDTO> getAllImgQuestions() {
        return null;
    }

    @Override
    public Page<GetTestQuestionDTO> getAllTestQuestions() {
        return null;
    }

    @Override
    public List<Object> getTestQuestionByLevelId(Long levelId) {
        return List.of();
    }
}