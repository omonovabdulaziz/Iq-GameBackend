package it.live.iqgame.service;

import it.live.iqgame.payload.*;
import it.live.iqgame.payload.UserDTOs.AllUserDTO;
import it.live.iqgame.payload.UserDTOs.LoginDTO;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {


    ResponseEntity<ApiResponse> register(RegisterDTO registerDTO);

    ResponseEntity<ApiResponse> login(LoginDTO loginDTO);

    ResponseEntity<ApiResponse> updateAvatar(MultipartFile file);

    ResponseEntity<ApiResponse> updateInformation(UpdateInformationDTO updateInformationDTO);

    Page<AllUserDTO> getAllUsers(int page, int size);

    Page<AllUserDTO> search(String name, String phoneNumber);
}