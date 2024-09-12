package it.live.iqgame.service;

import it.live.iqgame.entity.Subject;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService {


    ResponseEntity<ApiResponse> create(Long educId, String name, MultipartFile file);

    ResponseEntity<ApiResponse> update(Long subjectId, String name, MultipartFile file);

    ResponseEntity<ApiResponse> delete(Long subjectId);


    Page<GetSubjectDTO> getAllSubjects();


    List<GetSubjectDTO> getSubjectByEducId(Long educId);

}