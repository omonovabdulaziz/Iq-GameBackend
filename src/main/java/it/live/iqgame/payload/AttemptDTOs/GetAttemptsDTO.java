package it.live.iqgame.payload.AttemptDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAttemptsDTO {
    private Object user;
    private Object question;
    private Boolean isPassed;
}
