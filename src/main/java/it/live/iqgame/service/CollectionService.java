package it.live.iqgame.service;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CollectionService {


    ResponseEntity<ApiResponse> create(CollectionService collectionService);

    ResponseEntity<ApiResponse> update(Long collectionId, CollectionUpdateDTO collectionUpdateDTO);

    ResponseEntity<ApiResponse> delete(Long collectionId);

    Page<GetCollectionDTO> getAllCollection(int page, int size);

    Page<GetCollectionDTO> getCollectionBySubId(Long subjectId);
}