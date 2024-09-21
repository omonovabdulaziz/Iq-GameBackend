package it.live.iqgame.service.impl;

import it.live.iqgame.entity.Collection;
import it.live.iqgame.entity.Level;
import it.live.iqgame.exception.MainException;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import it.live.iqgame.repository.CollectionRepository;
import it.live.iqgame.repository.LevelRepository;
import it.live.iqgame.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final CollectionRepository collectionRepository;
    private final LevelRepository levelRepository;

    @Override
    public ResponseEntity<ApiResponse> create(Long collectionId, String title) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new NotFoundException("Level topilmadi"));
        if (levelRepository.countAllByCollectionId(collection.getId()) >= 5) {
            throw new MainException("You cannot create max len 5");
        }
        levelRepository.save(Level.builder().collection(collection).title(title).build());
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long levelId, String title) {
        Level level = levelRepository.findById(levelId).orElseThrow(() -> new NotFoundException("Level topilmadi"));
        level.setTitle(title);
        levelRepository.save(level);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long levelId) {
        levelRepository.deleteById(levelId);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("deleted").build());
    }

    @Override
    public Page<GetLevelDTO> getAllLevels(int page, int size) {
        return levelRepository.findAllLevels(PageRequest.of(page, size));
    }

    @Override
    public List<GetLevelDTO> getAllByCollectionId(Long collectionId) {
        return levelRepository.findAllByCollectionId(collectionId);
    }
}