package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/level")
public class LevelController {
    private final LevelService levelService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/{collectionId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long collectionId, @RequestParam String title) {
        return levelService.create(collectionId, title);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{levelId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long levelId, @RequestParam String title) {
        return levelService.update(levelId, title);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{levelId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long levelId) {
        return levelService.delete(levelId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllLevels")
    public Page<GetLevelDTO> getAllLevels(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size) {
        return levelService.getAllLevels(page , size);
    }

    @GetMapping("/getAllByCollectionId/{collectionId}")
    public List<GetLevelDTO> getAllByCollectionId(@PathVariable Long collectionId) {
        return levelService.getAllByCollectionId(collectionId);
    }
}