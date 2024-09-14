package it.live.iqgame.controller;

import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.SubjectDTOs.GetSubjectDTO;
import it.live.iqgame.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/create/{educId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> create(@PathVariable Long educId, @RequestParam String name, @RequestParam MultipartFile file) {
        return subjectService.create(educId, name, file);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{subjectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long subjectId, @RequestParam String name, @RequestParam MultipartFile file) {
        return subjectService.update(subjectId, name, file);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long subjectId) {
        return subjectService.delete(subjectId);
    }

    @GetMapping("/getAllSubjects")
    public Page<GetSubjectDTO> getAllSubjects(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return subjectService.getAllSubjects(page , size);
    }

    @GetMapping("/getSubjectByEducId/{educId}")
    public List<GetSubjectDTO> getSubjectByEducId(@PathVariable Long educId) {
        return subjectService.getSubjectByEducId(educId);
    }

}
