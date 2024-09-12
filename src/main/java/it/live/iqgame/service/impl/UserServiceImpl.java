package it.live.iqgame.service.impl;

import it.live.iqgame.payload.*;
import it.live.iqgame.payload.UserDTOs.AllUserDTO;
import it.live.iqgame.payload.UserDTOs.LoginDTO;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import it.live.iqgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<ApiResponse> register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updateInformation(UpdateInformationDTO updateInformationDTO) {
        return null;
    }

    @Override
    public Page<AllUserDTO> getAllUsers(int page, int size) {
        return null;
    }

    @Override
    public Page<AllUserDTO> search(String name, String phoneNumber) {
        return null;
    }
}