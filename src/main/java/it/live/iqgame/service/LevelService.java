package it.live.iqgame.service;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LevelService {


    ResponseEntity<ApiResponse> create(Long collectionId, String title);

    ResponseEntity<ApiResponse> update(Long levelId, String title);

    ResponseEntity<ApiResponse> delete(Long levelId);

    Page<GetLevelDTO> getAllLevels(int page, int size);


    List<GetLevelDTO> getAllByCollectionId(Long collectionId);
}