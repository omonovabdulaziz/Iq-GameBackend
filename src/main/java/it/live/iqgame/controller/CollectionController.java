package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionCreateDTO;
import it.live.iqgame.repository.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {
    private CollectionRepository collectionRepository;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CollectionCreateDTO collectionCreateDTO) {

    }

}