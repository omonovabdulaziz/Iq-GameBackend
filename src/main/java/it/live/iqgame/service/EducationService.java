package it.live.iqgame.service;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EducationService {


    ResponseEntity<ApiResponse> create(String name);

    ResponseEntity<ApiResponse> update(String name, Long educId);

    ResponseEntity<ApiResponse> delete(Long educId);

    List<EducationGetDTO> getAll();
}