package it.live.iqgame.payload.UserDTOs;

import it.live.iqgame.entity.enums.Region;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    @NotNull(message = "Ism kiriting")
    private String name;
    @NotNull(message = "Familiya kiriting")
    private String surname;
    @NotNull(message = "Viloyat kiriting")
    private Region region;
    @NotNull(message = "O'qish joyingizni kiriting")
    private Long educId;
    @NotNull(message = "Telefon raqam kiriting")
    private String phoneNumber;
    @NotNull(message = "Parol kiriting")
    private String password;
}