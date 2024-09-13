package it.live.iqgame.repository;

import it.live.iqgame.entity.Education;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query(value = "SELECT new it.live.iqgame.payload.EducationDTOs.EducationGetDTO(e.id , e.name , e.createdAt) FROM Education e")
    List<EducationGetDTO> findAllEGD();
}