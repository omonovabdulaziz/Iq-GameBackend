package it.live.iqgame.payload.SubjectDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Long collectionCount;
    private LocalDateTime createdAt;
}
