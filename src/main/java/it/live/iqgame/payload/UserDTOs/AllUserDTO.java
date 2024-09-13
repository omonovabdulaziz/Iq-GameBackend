package it.live.iqgame.payload.UserDTOs;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
public class AllUserDTO {
    private Long id;
    private String name;
    private String surname;
    private String educationName;
    private String phoneNumber;
    private String imgName;
    private LocalDateTime createdAt;

    public AllUserDTO(Long id, String name, String surname, String educationName, String phoneNumber, String avaName, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.educationName = educationName;
        this.phoneNumber = phoneNumber;
        this.imgName = avaName;
        this.createdAt = createdAt;
    }
}
