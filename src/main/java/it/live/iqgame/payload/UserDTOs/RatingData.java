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
public class RatingData {
    private Long id;
    private String name;
    private String surname;
    private Region region;
    private Integer iq;
    private Integer percent;
    private Integer ball_count;
    private Integer key_count;
    private Integer total_attempts;
}
