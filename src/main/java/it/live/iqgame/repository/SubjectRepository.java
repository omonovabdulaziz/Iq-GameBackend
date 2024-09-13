package it.live.iqgame.repository;

import it.live.iqgame.entity.Subject;
import it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT new it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO(s.id , s.imgUrl , s.name , s.education.name , COUNT(c), s.createdAt) " +
            "FROM Subject s LEFT JOIN s.collections c " +
            "GROUP BY s.id, s.imgUrl, s.name, s.education.name, s.createdAt")
    Page<GetSubjectDTO> findAllByPage(Pageable pageable);

    @Query("SELECT new it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO(s.id , s.imgUrl , s.name , s.education.name , COUNT(c), s.createdAt) " +
            "FROM Subject s LEFT JOIN s.collections c " +
            "WHERE s.education.id = :educId " +
            "GROUP BY s.id, s.imgUrl, s.name, s.education.name, s.createdAt")
    List<GetSubjectDTO> getSubjectByEducId(Long educId);
}