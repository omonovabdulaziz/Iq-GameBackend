package it.live.iqgame.controller;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.User;
import it.live.iqgame.payload.*;
import it.live.iqgame.payload.UserDTOs.AllUserDTO;
import it.live.iqgame.payload.UserDTOs.LoginDTO;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import it.live.iqgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PutMapping(value = "/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateAvatar(@RequestPart MultipartFile file) {
        return userService.updateAvatar(file);
    }

    @PutMapping("/updateInformation")
    public ResponseEntity<ApiResponse> updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO) {
        return userService.updateInformation(updateInformationDTO);
    }

    @GetMapping("/getCurrenInformation")
    public User getCurrentInformation() {
        return SecurityConfiguration.getOwnSecurityInformation();
    }

    @GetMapping("/getAllUsers")
    public Page<AllUserDTO> getAllUsers(@RequestPart int page, @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/search")
    public Page<AllUserDTO> search(@RequestParam String name, @RequestParam String phoneNumber) {
        return userService.search(name, phoneNumber);
    }
}