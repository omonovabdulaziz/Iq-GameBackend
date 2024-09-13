package it.live.iqgame.service;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface LevelService {


    ResponseEntity<ApiResponse> create(Long collectionId, String title);

    ResponseEntity<ApiResponse> update(Long levelId, String title);

    ResponseEntity<ApiResponse> delete(Long levelId);

    Page<GetLevelDTO> getAllLevels();


    Page<GetLevelDTO> getAllByCollectionId(Long collectionId);
}