package it.live.iqgame.config;

import it.live.iqgame.entity.Education;
import it.live.iqgame.entity.User;
import it.live.iqgame.entity.enums.Region;
import it.live.iqgame.entity.enums.RoleName;
import it.live.iqgame.repository.EducationRepository;
import it.live.iqgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(sqlInitMode, "always")) {
            Education otm = educationRepository.save(Education.builder().name("OTM").build());
            educationRepository.save(Education.builder().name("MAKTAB").build());
            educationRepository.save(Education.builder().name("KOLLEJ").build());
            userRepository.save(User.builder().name("Doniyor").surname("Jonibekov").roleName(RoleName.ADMIN).education(otm).phoneNumber("+998994768495").region(Region.SIRDARYO).password(passwordEncoder.encode("itlive123")).build());
            System.out.println("MALUMOTLAR SAQLANDI");
        }
    }


}