package it.live.iqgame.service;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AttemptsService {


    ResponseEntity<ApiResponse> create(Long questionId, String userAnswer);

    Page<GetAttemptsDTO> getAttempt(Long userId);

    ResponseEntity<ApiResponse> getTTL();

    ResponseEntity<ApiResponse> useKey(Long subjectId);
}