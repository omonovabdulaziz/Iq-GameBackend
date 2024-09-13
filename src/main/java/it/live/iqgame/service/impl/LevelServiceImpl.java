package it.live.iqgame.service.impl;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    @Override
    public ResponseEntity<ApiResponse> create(Long collectionId, String title) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long levelId, String title) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long levelId) {
        return null;
    }

    @Override
    public Page<GetLevelDTO> getAllLevels() {
        return null;
    }

    @Override
    public Page<GetLevelDTO> getAllByCollectionId(Long collectionId) {
        return null;
    }
}