package it.live.iqgame.payload.UserDTOs;

import it.live.iqgame.entity.enums.Region;
import it.live.iqgame.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String surname;
    private Region region;
    private Long educationId;
    private RoleName roleName;
    private String phoneNumber;
    private Long ball;
    private Long key;
    private String avaName;
}
