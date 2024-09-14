package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Subject Management", description = "APIs for managing subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Operation(
            summary = "Create a new subject (Accessible by ROLE_ADMIN)",
            description = "Creates a new subject with the provided education ID, name, and file.",
            tags = {"Subject Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/create/{educId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> create(@PathVariable Long educId, @RequestParam String name, @RequestParam MultipartFile file) {
        return subjectService.create(educId, name, file);
    }

    @Operation(
            summary = "Update an existing subject (Accessible by ROLE_ADMIN)",
            description = "Updates the subject with the specified ID using the provided name and file.",
            tags = {"Subject Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{subjectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long subjectId, @RequestParam String name, @RequestParam MultipartFile file) {
        return subjectService.update(subjectId, name, file);
    }

    @Operation(
            summary = "Delete a subject (Accessible by ROLE_ADMIN)",
            description = "Deletes the subject with the specified ID.",
            tags = {"Subject Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long subjectId) {
        return subjectService.delete(subjectId);
    }

    @Operation(
            summary = "Get all subjects (Accessible by anyone)",
            description = "Retrieves a paginated list of all subjects.",
            tags = {"Subject Management"}
    )
    @GetMapping("/getAllSubjects")
    public Page<GetSubjectDTO> getAllSubjects(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return subjectService.getAllSubjects(page , size);
    }

    @Operation(
            summary = "Get subjects by education ID (Accessible by anyone)",
            description = "Retrieves a list of subjects associated with the given education ID.",
            tags = {"Subject Management"}
    )
    @GetMapping("/getSubjectByEducId/{educId}")
    public List<GetSubjectDTO> getSubjectByEducId(@PathVariable Long educId) {
        return subjectService.getSubjectByEducId(educId);
    }
}
