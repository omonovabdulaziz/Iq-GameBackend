package it.live.iqgame.repository;

import it.live.iqgame.entity.Collection;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO;
import it.live.iqgame.payload.CollectionDTOs.GetCollectionStudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query(value = "SELECT new it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO(c.id , c.title , c.description , c.subject.name) FROM Collection c")
    Page<GetCollectionDTO> getAllCollection(Pageable pageable);

    @Query(value = "SELECT new it.live.iqgame.payload.CollectionDTOs.GetCollectionDTO(c.id, c.title, c.description, c.subject.name) FROM Collection c WHERE c.subject.id = :subjectId")
    List<GetCollectionDTO> findAllBySubjectId(@Param("subjectId") Long subjectId);

    @Query("SELECT new it.live.iqgame.payload.CollectionDTOs.GetCollectionStudentDTO(c.id, c.title, c.description, c.subject.name, " +
            "CASE WHEN COUNT(uc) > 0 THEN true ELSE false END) " +
            "FROM Collection c " +
            "LEFT JOIN UserCollection uc ON c.id = uc.collection.id AND uc.user.id = :userId " +
            "WHERE c.subject.id = :subjectId " +
            "GROUP BY c.id, c.title, c.description, c.subject.name")
    List<GetCollectionStudentDTO> findAllBySubjectIdWhereStudentNotFinished(@Param("subjectId") Long subjectId, @Param("userId") Long userId);
}