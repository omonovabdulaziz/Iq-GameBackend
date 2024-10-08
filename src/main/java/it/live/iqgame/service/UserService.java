package it.live.iqgame.service;

import it.live.iqgame.payload.*;
import it.live.iqgame.payload.UserDTOs.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {


    ResponseEntity<ApiResponse> register(RegisterDTO registerDTO);

    ResponseEntity<ApiResponse> login(LoginDTO loginDTO);

    ResponseEntity<ApiResponse> updateAvatar(MultipartFile file) throws IOException;

    ResponseEntity<ApiResponse> updateInformation(UpdateInformationDTO updateInformationDTO);

    Page<AllUserDTO> getAllUsers(int page, int size);

    Page<AllUserDTO> search(String name, String phoneNumber, int page, int size);

    Page<RatingData> rating(Long subjectId, int page, int size);
}