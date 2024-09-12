package it.live.iqgame.payload.UserDTOs;

import it.live.iqgame.entity.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateInformationDTO {
    private String name;
    private String surname;
    private Region region;
    private String phoneNumber;
    private String password;
}
