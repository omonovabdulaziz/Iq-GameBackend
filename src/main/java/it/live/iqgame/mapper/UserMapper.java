package it.live.iqgame.mapper;

import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.exception.NotFoundException;
import it.live.iqgame.payload.UserDTOs.RegisterDTO;
import it.live.iqgame.payload.UserDTOs.UpdateInformationDTO;
import it.live.iqgame.payload.UserDTOs.UserDTO;
import it.live.iqgame.repository.AttemptsRepository;
import it.live.iqgame.repository.EducationRepository;
import it.live.iqgame.repository.UsedKeyRepository;
import it.live.iqgame.utils.CalculatingKeyBall;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final EducationRepository educationRepository;
    private final CalculatingKeyBall calculatingKeyBall;
    private final UsedKeyRepository usedKeyRepository;

    public User toEntityForRegister(RegisterDTO registerDTO) {
        String password = passwordEncoder.encode(registerDTO.getPassword());
        return User.builder().name(registerDTO.getName()).education(educationRepository.findById(registerDTO.getEducId()).orElseThrow(() -> new NotFoundException("Education Topilmadi"))).roleName(RoleName.USER).password(password).region(registerDTO.getRegion()).surname(registerDTO.getSurname()).phoneNumber(registerDTO.getPhoneNumber()).build();

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

    public UserDTO toDTOCurrent(User ownSecurityInformation, Long subjectId) {
        UserDTO build = UserDTO.builder()
                .name(ownSecurityInformation.getName()).
                surname(ownSecurityInformation.getSurname()).
                roleName(ownSecurityInformation.getRoleName()).
                educationId(ownSecurityInformation.getEducation().getId())
                .phoneNumber(ownSecurityInformation.getPhoneNumber())
                .region(ownSecurityInformation.getRegion())
                .avaName(ownSecurityInformation.getAvaName())
                .build();
        try {
            Long allBalls = calculatingKeyBall.calculate(QuestionType.TEST, ownSecurityInformation.getId(), subjectId);
            Long allKeys = calculatingKeyBall.calculate(QuestionType.IMAGE, ownSecurityInformation.getId(), subjectId);
            Long usedKey = 0L;
            try {
                usedKey = usedKeyRepository.findByUserIdAndSubjectId(ownSecurityInformation.getId(), subjectId).get().getCount();
            } catch (Exception e) {
                usedKey = 0L;
            }
            build.setBall(allBalls);
            build.setKey(allKeys - usedKey);
        } catch (Exception e) {
            build.setBall(null);
            build.setKey(null);
        }
        return build;
    }
}