package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionCreateDTO;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import it.live.iqgame.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
@Tag(name = "Collection Management", description = "APIs for managing collections")
public class CollectionController {
    private final CollectionService collectionService;

    @Operation(
            summary = "Create a new collection (Accessible by ROLE_ADMIN)",
            description = "Creates a new collection with the details provided in the request body.",
            tags = {"Collection Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CollectionCreateDTO collectionCreateDTO) {
        return collectionService.create(collectionCreateDTO);
    }

    @Operation(
            summary = "Update an existing collection (Accessible by ROLE_ADMIN)",
            description = "Updates the collection with the specified ID using the details provided in the request body.",
            tags = {"Collection Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{collectionId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long collectionId, @RequestBody CollectionUpdateDTO collectionUpdateDTO) {
        return collectionService.update(collectionId, collectionUpdateDTO);
    }

    @Operation(
            summary = "Delete a collection (Accessible by ROLE_ADMIN)",
            description = "Deletes the collection with the specified ID.",
            tags = {"Collection Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{collectionId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long collectionId) {
        return collectionService.delete(collectionId);
    }

    @Operation(
            summary = "Get all collections (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all collections.",
            tags = {"Collection Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllCollections")
    public Page<GetCollectionDTO> getAllCollection(@RequestParam int page, @RequestParam int size) {
        return collectionService.getAllCollection(page, size);
    }

    @Operation(
            summary = "Get collections by subject ID",
            description = "Retrieves a list of collections associated with the specified subject ID.",
            tags = {"Collection Management"}
    )
    @GetMapping("/getCollectionsBySubjectId/{subjectId}")
    public List<Object> getCollectionBySubId(@PathVariable Long subjectId) {
        return collectionService.getCollectionBySubId(subjectId);
    }
}
