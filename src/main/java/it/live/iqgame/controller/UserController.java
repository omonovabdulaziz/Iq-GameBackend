package it.live.iqgame.controller;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.mapper.UserMapper;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.UserDTOs.*;
import it.live.iqgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PutMapping(value = "/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateAvatar(@RequestPart MultipartFile file) throws IOException {
        return userService.updateAvatar(file);
    }

    @PutMapping("/updateInformation")
    public ResponseEntity<ApiResponse> updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO) {
        return userService.updateInformation(updateInformationDTO);
    }

    @GetMapping("/getCurrenInformation/{subjectId}")
    public UserDTO getCurrentInformation(@PathVariable Long subjectId) {
        return userMapper.toDTOCurrent(SecurityConfiguration.getOwnSecurityInformation(), subjectId);
    }

    @GetMapping("/getAllUsers")
    public Page<AllUserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/search")
    public Page<AllUserDTO> search(@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.search(name, phoneNumber, page, size);
    }

    @GetMapping("/rating/{subjectId}")
    public Page<RatingData> rating(@PathVariable Long subjectId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.rating(subjectId , page , size);
    }
}