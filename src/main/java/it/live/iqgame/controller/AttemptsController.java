package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.service.AttemptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attempts")
public class AttemptsController {
    private final AttemptsService attemptsService;

    @PostMapping("/create/{questionId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long questionId, @RequestParam String userAnswer) {
        return attemptsService.create(questionId, userAnswer);
    }

    @GetMapping("/getAllAttemptsByUserId/{userId}")
    public Page<GetAttemptsDTO> getAttempt(@PathVariable Long userId) {
        return attemptsService.getAttempt(userId);
    }
}