package soqqa.com.ratingproject.dto.response;

import lombok.*;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.WorkEntity;
import soqqa.com.ratingproject.enitity.enums.UserRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponseDTO {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String work;
    private String education;
    private UserRole role;
}
