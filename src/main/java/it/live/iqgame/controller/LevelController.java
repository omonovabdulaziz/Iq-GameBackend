package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Level Management", description = "APIs for managing levels")
public class LevelController {
    private final LevelService levelService;

    @Operation(
            summary = "Create a new level (Accessible by ROLE_ADMIN)",
            description = "Creates a new level for a given collection ID with the specified title.",
            tags = {"Level Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/{collectionId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long collectionId, @RequestParam String title) {
        return levelService.create(collectionId, title);
    }

    @Operation(
            summary = "Update an existing level (Accessible by ROLE_ADMIN)",
            description = "Updates the level with the specified ID using the provided title.",
            tags = {"Level Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{levelId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long levelId, @RequestParam String title) {
        return levelService.update(levelId, title);
    }

    @Operation(
            summary = "Delete a level (Accessible by ROLE_ADMIN)",
            description = "Deletes the level with the specified ID.",
            tags = {"Level Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{levelId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long levelId) {
        return levelService.delete(levelId);
    }

    @Operation(
            summary = "Get all levels (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all levels.",
            tags = {"Level Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllLevels")
    public Page<GetLevelDTO> getAllLevels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return levelService.getAllLevels(page, size);
    }

    @Operation(
            summary = "Get all levels by collection ID",
            description = "Retrieves a list of levels associated with the given collection ID. Accessible by all roles.",
            tags = {"Level Management"}
    )
    @GetMapping("/getAllByCollectionId/{collectionId}")
    public List<GetLevelDTO> getAllByCollectionId(@PathVariable Long collectionId) {
        return levelService.getAllByCollectionId(collectionId);
    }
}
