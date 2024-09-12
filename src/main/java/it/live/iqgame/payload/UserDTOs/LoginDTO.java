package it.live.iqgame.payload.UserDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    @NotNull(message = "Telefon raqam kiriting")
    private String phoneNumber;
    @NotNull(message = "Parol  kiriting")
    private String password;
}
