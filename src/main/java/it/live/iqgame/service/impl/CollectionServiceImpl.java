package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.Collection;
import it.live.iqgame.entity.Subject;
import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.mapper.CollectionMapper;
import it.live.iqgame.mapper.SubjectMapper;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.CollectionDTOs.CollectionCreateDTO;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import it.live.iqgame.repository.CollectionRepository;
import it.live.iqgame.repository.SubjectRepository;
import it.live.iqgame.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;

    @Override
    public ResponseEntity<ApiResponse> create(CollectionCreateDTO collectionCreateDTO) {
        collectionRepository.save(collectionMapper.toEntity(collectionCreateDTO));
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long collectionId, CollectionUpdateDTO collectionUpdateDTO) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new NotFoundException("not found collection"));
        collectionRepository.save(collectionMapper.toEntityForUpdate(collection, collectionUpdateDTO));
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("updated").build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long collectionId) {
        collectionRepository.deleteById(collectionId);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("deleted").build());
    }

    @Override
    public Page<GetCollectionDTO> getAllCollection(int page, int size) {
        return collectionRepository.getAllCollection(PageRequest.of(page, size));
    }

    @Override
    public List<Object> getCollectionBySubId(Long subjectId, Boolean isDemo) {
        if (isDemo) {
            return Collections.singletonList(collectionRepository.findAllBySubjectId(subjectId));
        }
        User currectuser = SecurityConfiguration.getOwnSecurityInformation();
        if (currectuser.getRoleName() == RoleName.ADMIN) {
            return Collections.singletonList(collectionRepository.findAllBySubjectId(subjectId));
        } else {
            return Collections.singletonList(collectionRepository.findAllBySubjectIdWhereStudentNotFinished(subjectId, currectuser.getId()));
        }
    }
}