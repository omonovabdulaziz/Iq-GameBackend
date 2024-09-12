package it.live.iqgame.payload.UserDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllUserDTO {
    private Long id;
    private String name;
    private String surname;
    private String educationName;
    private String phoneNumber;
    private String imgName;
    private Date createdAt;
}
