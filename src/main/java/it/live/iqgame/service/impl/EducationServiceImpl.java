package it.live.iqgame.service.impl;

import it.live.iqgame.entity.Education;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import it.live.iqgame.repository.EducationRepository;
import it.live.iqgame.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {


    private final EducationRepository educationRepository;

    @Override
    public ResponseEntity<ApiResponse> create(String name) {
        educationRepository.save(Education.builder().name(name).build());
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("saved").build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(String name, Long educId) {
        Education education = educationRepository.findById(educId).orElseThrow(() -> new NotFoundException("Education topilmadi"));
        education.setName(name);
        educationRepository.save(education);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("updated").build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long educId) {
        educationRepository.deleteById(educId);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("deleted").build());
    }

    @Override
    public List<EducationGetDTO> getAll() {
        return educationRepository.findAllEGD();
    }
}