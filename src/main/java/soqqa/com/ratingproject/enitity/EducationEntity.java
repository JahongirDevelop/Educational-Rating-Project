package soqqa.com.ratingproject.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "educations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EducationEntity extends BaseEntity {
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    private List<UserEntity> students;
}
