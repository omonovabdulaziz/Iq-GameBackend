package it.live.iqgame.payload.LevelDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLevelDTO {
    private Long id;
    private String title;
    private String collectionName;
    private String educationName;
}
