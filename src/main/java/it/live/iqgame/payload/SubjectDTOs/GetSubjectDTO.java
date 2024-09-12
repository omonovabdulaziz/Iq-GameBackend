package it.live.iqgame.payload.SubjectDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSubjectDTO {
    private Long id;
    private String imgName;
    private String name;
    private String educName;
    private Integer collectionCount;
    private Date createdAt;
}
