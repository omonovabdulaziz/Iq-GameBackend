package it.live.iqgame.controller;

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
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @PutMapping(value = "/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateAvatar(@RequestPart MultipartFile file) throws IOException {
        return userService.updateAvatar(file);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @PutMapping("/updateInformation")
    public ResponseEntity<ApiResponse> updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO) {
        return userService.updateInformation(updateInformationDTO);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping("/getCurrenInformation/{subjectId}")
    public UserDTO getCurrentInformation(@PathVariable Long subjectId) {
        return userMapper.toDTOCurrent(SecurityConfiguration.getOwnSecurityInformation(), subjectId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public Page<AllUserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public Page<AllUserDTO> search(@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.search(name, phoneNumber, page, size);
    }


    @GetMapping("/rating/{subjectId}")
    public Page<RatingData> rating(@PathVariable Long subjectId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.rating(subjectId, page, size);
    }

    @GetMapping("/getFiles/{filename}")
    public ResponseEntity<?> getFile(@PathVariable String filename) throws MalformedURLException {
        String filePath = "DOCUMENTS/" + URLEncoder.encode(filename, StandardCharsets.UTF_8);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filePath + "\"").body(new FileUrlResource(filePath));
    }

}