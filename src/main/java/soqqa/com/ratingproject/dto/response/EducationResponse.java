package soqqa.com.ratingproject.dto.response;

import lombok.*;
import soqqa.com.ratingproject.enitity.UserEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationResponse {
    private String name;
    private List<UserResponse> students;
}
