package it.live.iqgame.mapper;

import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import it.live.iqgame.payload.UserDTOs.UserDTO;
import it.live.iqgame.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final EducationRepository educationRepository;

    public User toEntityForRegister(RegisterDTO registerDTO) {
        String password = passwordEncoder.encode(registerDTO.getPassword());
        return User.builder().name(registerDTO.getName()).education(educationRepository.findById(registerDTO.getEducId()).orElseThrow(() -> new NotFoundException("Education Topilmadi"))).roleName(RoleName.USER).ball(0).key(0).password(password).region(registerDTO.getRegion()).surname(registerDTO.getSurname()).phoneNumber(registerDTO.getPhoneNumber()).build();

    }

    public User toEntityForUpdateInformation(User ownSecurityInformation, UpdateInformationDTO updateInformationDTO) {
        ownSecurityInformation.setName(updateInformationDTO.getName());
        ownSecurityInformation.setSurname(updateInformationDTO.getSurname());
        ownSecurityInformation.setRegion(updateInformationDTO.getRegion());
        ownSecurityInformation.setPhoneNumber(updateInformationDTO.getPhoneNumber());
        if (updateInformationDTO.getPassword() != null) {
            ownSecurityInformation.setPassword(passwordEncoder.encode(updateInformationDTO.getPassword()));
        }
        return ownSecurityInformation;
    }

    public UserDTO toDTOCurrent(User ownSecurityInformation) {
        return UserDTO.builder()
                .name(ownSecurityInformation.getName()).
                surname(ownSecurityInformation.getSurname()).
                roleName(ownSecurityInformation.getRoleName()).
                educationId(ownSecurityInformation.getEducation().getId())
                .ball(ownSecurityInformation.getBall())
                .key(ownSecurityInformation.getKey())
                .phoneNumber(ownSecurityInformation.getPhoneNumber())
                .region(ownSecurityInformation.getRegion())
                .avaName(ownSecurityInformation.getAvaName())
                .build();
    }
}