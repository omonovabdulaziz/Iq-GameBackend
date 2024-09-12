package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import it.live.iqgame.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education")
public class EducationController {
    private final EducationService educationService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestParam String name) {
        return educationService.create(name);
    }

    @PutMapping("/update/{educId}")
    public ResponseEntity<ApiResponse> update(@RequestParam String name, @PathVariable Long educId) {
        return educationService.update(name, educId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestParam Long educId) {
        return educationService.delete(educId);
    }

    @GetMapping("/getAll")
    public List<EducationGetDTO> getAll() {
        return educationService.getAll();
    }
}