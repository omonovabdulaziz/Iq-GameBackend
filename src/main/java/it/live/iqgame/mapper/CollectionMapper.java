package it.live.iqgame.mapper;

import it.live.iqgame.entity.Collection;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.CollectionDTOs.CollectionCreateDTO;
import it.live.iqgame.payload.CollectionDTOs.CollectionUpdateDTO;
import it.live.iqgame.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

@Service
@RequiredArgsConstructor
public class CollectionMapper {

    private final SubjectRepository subjectRepository;

    public Collection toEntity(CollectionCreateDTO collectionCreateDTO) {
        return Collection.builder()
                .subject(subjectRepository.findById(collectionCreateDTO.getSubjectId()).orElseThrow(() -> new NotFoundException("subject topilmadi")))
                .title(collectionCreateDTO.getTitle())
                .description(collectionCreateDTO.getDescription())
                .build();
    }

    public Collection toEntityForUpdate(Collection collection, CollectionUpdateDTO collectionUpdateDTO) {
        collection.setDescription(collectionUpdateDTO.getDescription());
        collection.setTitle(collectionUpdateDTO.getTitle());
        return collection;
    }
}