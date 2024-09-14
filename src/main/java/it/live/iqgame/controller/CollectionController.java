package it.live.iqgame.controller;

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
public class CollectionController {
    private final CollectionService collectionService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CollectionCreateDTO collectionCreateDTO) {
        return collectionService.create(collectionCreateDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{collectionId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long collectionId, @RequestBody CollectionUpdateDTO collectionUpdateDTO) {
        return collectionService.update(collectionId, collectionUpdateDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{collectionId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long collectionId) {
        return collectionService.delete(collectionId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllCollections")
    public Page<GetCollectionDTO> getAllCollection(@RequestParam int page, @RequestParam int size) {
        return collectionService.getAllCollection(page, size);
    }


    @GetMapping("/getCollectionsBySubjectId/{subjectId}")
    public List<Object> getCollectionBySubId(@PathVariable Long subjectId) {
        return collectionService.getCollectionBySubId(subjectId);
    }
}