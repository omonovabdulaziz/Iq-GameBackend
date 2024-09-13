package it.live.iqgame.payload.EducationDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationGetDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
