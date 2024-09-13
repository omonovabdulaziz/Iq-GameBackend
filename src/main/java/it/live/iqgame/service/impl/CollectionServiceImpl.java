package it.live.iqgame.service.impl;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import it.live.iqgame.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    @Override
    public ResponseEntity<ApiResponse> create(CollectionService collectionService) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long collectionId, CollectionUpdateDTO collectionUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long collectionId) {
        return null;
    }

    @Override
    public Page<GetCollectionDTO> getAllCollection(int page, int size) {
        return null;
    }

    @Override
    public Page<GetCollectionDTO> getCollectionBySubId(Long subjectId) {
        return null;
    }
}