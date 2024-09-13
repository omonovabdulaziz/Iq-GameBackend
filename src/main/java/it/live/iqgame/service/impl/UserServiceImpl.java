package it.live.iqgame.service.impl;

import it.live.iqgame.config.SecurityConfiguration;
import it.live.iqgame.entity.User;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.jwt.JwtProvider;
import it.live.iqgame.mapper.UserMapper;
import it.live.iqgame.payload.ApiResponse;
import it.live.iqgame.payload.UserDTOs.AllUserDTO;
import it.live.iqgame.payload.UserDTOs.LoginDTO;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import it.live.iqgame.repository.UserRepository;
import it.live.iqgame.service.UserService;
import it.live.iqgame.utils.FileComposer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<ApiResponse> register(RegisterDTO registerDTO) {
        User user = userRepository.save(userMapper.toEntityForRegister(registerDTO));
        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(ApiResponse.builder().message("ok").status(200).object(token).build());
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO loginDTO) {
        User user = userRepository.findByPhoneNumber(loginDTO.getPhoneNumber()).orElseThrow(() -> new NotFoundException("Login or password incorrect"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new NotFoundException("Login or password incorrect");
        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(ApiResponse.builder().status(200).object(token).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateAvatar(MultipartFile file) {
        User currentUser = SecurityConfiguration.getOwnSecurityInformation();
        String extension = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String name = UUID.randomUUID() + "." + extension;
        FileComposer.imageUploader(name, file);
        currentUser.setAvaName(name);
        userRepository.save(currentUser);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateInformation(UpdateInformationDTO updateInformationDTO) {
        User user = userRepository.save(userMapper.toEntityForUpdateInformation(SecurityConfiguration.getOwnSecurityInformation(), updateInformationDTO));
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("ok").object(jwtProvider.generateToken(user)).build());
    }

    @Override
    public Page<AllUserDTO> getAllUsers(int page, int size) {
        return userRepository.findAllByPage(PageRequest.of(page, size));
    }

    @Override
    public Page<AllUserDTO> search(String name, String phoneNumber, int page, int size) {
        return userRepository.searchByNameOrPhoneNumber(name, phoneNumber, PageRequest.of(page, size));
    }
}