package soqqa.com.ratingproject.dto.response;

import lombok.*;
import soqqa.com.ratingproject.enitity.enums.UserRole;
import soqqa.com.ratingproject.enitity.enums.UserStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String work;
    private String education;
    private UserRole role;
    private UserStatus status;
}
