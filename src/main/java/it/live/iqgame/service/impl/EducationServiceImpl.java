package it.live.iqgame.service.impl;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import it.live.iqgame.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {


    @Override
    public ResponseEntity<ApiResponse> create(String name) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(String name, Long educId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long educId) {
        return null;
    }

    @Override
    public List<EducationGetDTO> getAll() {
        return List.of();
    }
}