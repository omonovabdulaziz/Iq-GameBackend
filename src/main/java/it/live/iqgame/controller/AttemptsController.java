package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.AttemptDTOs.GetAttemptsDTO;
import it.live.iqgame.service.AttemptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attempts")
@Tag(name = "Attempts Management", description = "APIs for managing user attempts")
public class AttemptsController {
    private final AttemptsService attemptsService;

    @Operation(
            summary = "Create a new attempt(Accessible by ROLE_USER)",
            description = "Submits an attempt for a specific question with the user's answer.",
            tags = {"Attempts Management"}
    )
    @PostMapping("/create/{questionId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long questionId, @RequestBody String userAnswer) {
        return attemptsService.create(questionId, userAnswer);
    }

    @Operation(
            summary = "Get all attempts by user ID (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all attempts made by a user specified by their ID.",
            tags = {"Attempts Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllAttemptsByUserId/{userId}")
    public Page<GetAttemptsDTO> getAttempt(@PathVariable Long userId) {
        return attemptsService.getAttempt(userId);
    }

    @Operation(
            summary = "Get user TTL",
            description = "Retrieves the Time-to-Live (TTL) for user attempts.",
            tags = {"Attempts Management"}
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getUserTTL")
    public ResponseEntity<ApiResponse> getTTl() {
        return attemptsService.getTTL();
    }
}
