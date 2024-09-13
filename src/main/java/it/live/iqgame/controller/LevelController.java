package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/level")
public class LevelController {
    private final LevelService levelService;

    @PostMapping("/create/{collectionId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long collectionId, @RequestParam String title) {
        return levelService.create(collectionId, title);
    }

    @PutMapping("/update/{levelId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long levelId, @RequestParam String title) {
        return levelService.update(levelId, title);
    }

    @DeleteMapping("/delete/{levelId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long levelId) {
        return levelService.delete(levelId);
    }

    @GetMapping("/getAllLevels")
    public Page<GetLevelDTO> getAllLevels() {
        return levelService.getAllLevels();
    }

    @GetMapping("/getAllByCollectionId/{collectionId}")
    public Page<GetLevelDTO> getAllByCollectionId(@PathVariable Long collectionId) {
        return levelService.getAllByCollectionId(collectionId);
    }
}