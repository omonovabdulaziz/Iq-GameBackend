package it.live.iqgame.service.impl;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.service.AttemptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AttemptsServiceImpl implements AttemptsService {

    @Override
    public ResponseEntity<ApiResponse> create(Long questionId, String userAnswer) {
        return null;
    }

    @Override
    public Page<GetAttemptsDTO> getAttempt(Long userId) {
        return null;
    }
}