package soqqa.com.ratingproject.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkResponse {
    private String name;
    private List<UserResponse> employees;
}
