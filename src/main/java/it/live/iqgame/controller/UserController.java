package it.live.iqgame.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.mapper.UserMapper;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.UserDTOs.*;
import it.live.iqgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Register a new user (Accessible by anyone)",
            description = "Registers a new user in the system.",
            tags = {"User Management"}
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @Operation(
            summary = "Login a user (Accessible by anyone)",
            description = "Logs in an existing user.",
            tags = {"User Management"}
    )
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @Operation(
            summary = "Update user avatar (Accessible by ROLE_ADMIN and ROLE_USER)",
            description = "Updates the avatar of the currently logged-in user.",
            tags = {"User Management"}
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @PutMapping(value = "/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateAvatar(@RequestPart MultipartFile file) throws IOException {
        return userService.updateAvatar(file);
    }

    @Operation(
            summary = "Update user information (Accessible by ROLE_ADMIN and ROLE_USER)",
            description = "Updates the information of the currently logged-in user.",
            tags = {"User Management"}
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @PutMapping("/updateInformation")
    public ResponseEntity<ApiResponse> updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO) {
        return userService.updateInformation(updateInformationDTO);
    }

    @Operation(
            summary = "Get current user information (Accessible by ROLE_ADMIN and ROLE_USER)",
            description = "Retrieves information of the currently logged-in user for a specific subject.",
            tags = {"User Management"}
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping("/getCurrenInformation/{subjectId}")
    public UserDTO getCurrentInformation(@PathVariable Long subjectId) {
        return userMapper.toDTOCurrent(SecurityConfiguration.getOwnSecurityInformation(), subjectId);
    }

    @Operation(
            summary = "Get all users (Accessible by ROLE_ADMIN)",
            description = "Retrieves a paginated list of all users.",
            tags = {"User Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public Page<AllUserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @Operation(
            summary = "Search users (Accessible by ROLE_ADMIN)",
            description = "Searches for users by name or phone number.",
            tags = {"User Management"}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public Page<AllUserDTO> search(@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.search(name, phoneNumber, page, size);
    }

    @Operation(
            summary = "Get user ratings (Accessible by anyone)",
            description = "Retrieves a paginated list of user ratings for a specific subject.",
            tags = {"User Management"}
    )
    @GetMapping("/rating/{subjectId}")
    public Page<RatingData> rating(@PathVariable Long subjectId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.rating(subjectId, page, size);
    }

    @Operation(
            summary = "Get a file (Accessible by anyone)",
            description = "Retrieves a file by its filename.",
            tags = {"User Management"}
    )
    @GetMapping("/getFiles/{filename}")
    public ResponseEntity<?> getFile(@PathVariable String filename) throws MalformedURLException {
        String filePath = "DOCUMENTS/" + URLEncoder.encode(filename, StandardCharsets.UTF_8);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filePath + "\"").body(new FileUrlResource(filePath));
    }
}
