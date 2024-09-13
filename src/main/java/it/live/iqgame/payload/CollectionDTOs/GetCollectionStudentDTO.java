package it.live.iqgame.payload.CollectionDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCollectionStudentDTO {
    private Long id;
    private String title;
    private String description;
    private String subjectName;
    private Boolean isFinished;
}
