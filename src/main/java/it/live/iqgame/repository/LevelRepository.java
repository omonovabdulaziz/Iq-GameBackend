package it.live.iqgame.repository;

import it.live.iqgame.entity.Level;
import it.live.iqgame.payload.LevelDTOs.GetLevelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {

    @Query(value = "SELECT new it.live.iqgame.payload.LevelDTOs.GetLevelDTO(l.id, l.title, l.collection.title, l.collection.subject.education.name, count(q)) " +
            "FROM Level l LEFT JOIN Question q ON l.id = q.level.id " +
            "GROUP BY l.id, l.title, l.collection.title, l.collection.subject.education.name " +
            "ORDER BY l.createdAt ASC")
    Page<GetLevelDTO> findAllLevels(Pageable pageable);

    @Query(value = "SELECT new it.live.iqgame.payload.LevelDTOs.GetLevelDTO(l.id, l.title, l.collection.title, l.collection.subject.education.name, count(q)) " +
            "FROM Level l LEFT JOIN Question q ON l.id = q.level.id " +
            "WHERE l.collection.id = :collectionID " +
            "GROUP BY l.id, l.title, l.collection.title, l.collection.subject.education.name " +
            "ORDER BY l.createdAt ASC")
    List<GetLevelDTO> findAllByCollectionId(@Param("collectionID") Long collectionID);


}