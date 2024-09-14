package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.EducationDTOs.EducationGetDTO;
import it.live.iqgame.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education")
@Tag(name = "Education Management", description = "APIs for managing educational entities")
public class EducationController {
    private final EducationService educationService;

    @Operation(
            summary = "Create a new education entry (Accessible by ROLE_ADMIN)",
            description = "Creates a new education entry with the specified name.",
            tags = {"Education Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestParam String name) {
        return educationService.create(name);
    }

    @Operation(
            summary = "Update an existing education entry (Accessible by ROLE_ADMIN)",
            description = "Updates the education entry with the specified ID using the provided name.",
            tags = {"Education Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{educId}")
    public ResponseEntity<ApiResponse> update(@RequestParam String name, @PathVariable Long educId) {
        return educationService.update(name, educId);
    }

    @Operation(
            summary = "Delete an education entry (Accessible by ROLE_ADMIN)",
            description = "Deletes the education entry with the specified ID.",
            tags = {"Education Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestParam Long educId) {
        return educationService.delete(educId);
    }

    @Operation(
            summary = "Get all education entries",
            description = "Retrieves a list of all education entries. Accessible by all roles.",
            tags = {"Education Management"}
    )
    @GetMapping("/getAll")
    public List<EducationGetDTO> getAll() {
        return educationService.getAll();
    }
}
