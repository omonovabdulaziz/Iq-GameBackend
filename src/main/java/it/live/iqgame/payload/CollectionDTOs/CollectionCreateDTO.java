package it.live.iqgame.payload.CollectionDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionCreateDTO {
    @NotNull(message = "title kiriting")
    private String title;
    @NotNull(message = "title kiriting")
    private String description;
    @NotNull(message = "title kiriting")
    private Long subjectId;
}
