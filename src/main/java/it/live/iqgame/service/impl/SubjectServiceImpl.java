package it.live.iqgame.service.impl;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO;
import it.live.iqgame.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    @Override
    public ResponseEntity<ApiResponse> create(Long educId, String name, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long subjectId, String name, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long subjectId) {
        return null;
    }

    @Override
    public Page<GetSubjectDTO> getAllSubjects() {
        return null;
    }

    @Override
    public List<GetSubjectDTO> getSubjectByEducId(Long educId) {
        return List.of();
    }
}