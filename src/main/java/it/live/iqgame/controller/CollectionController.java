package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionCreateDTO;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import it.live.iqgame.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {
    private CollectionService collectionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CollectionCreateDTO collectionCreateDTO) {
        return collectionService.create(collectionService);
    }

    @PutMapping("/update/{collectionId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long collectionId, @RequestBody CollectionUpdateDTO collectionUpdateDTO) {
        return collectionService.update(collectionId, collectionUpdateDTO);
    }

    @DeleteMapping("/delete/{collectionId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long collectionId) {
        return collectionService.delete(collectionId);
    }

    @GetMapping("/getAllCollections")
    public Page<GetCollectionDTO> getAllCollection(@RequestParam int page, @RequestParam int size) {
        return collectionService.getAllCollection(page, size);
    }


    @GetMapping("/getCollectionsBySubjectId/{subjectId}")
    public Page<GetCollectionDTO> getCollectionBySubId(@PathVariable Long subjectId) {
        return collectionService.getCollectionBySubId(subjectId);
    }
}