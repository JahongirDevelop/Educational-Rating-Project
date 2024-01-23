package soqqa.com.ratingproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import soqqa.com.ratingproject.enitity.enums.UserStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;

    private String work;
    @NotBlank
    @NotNull
    private String education;
    private UserStatus status;
}
