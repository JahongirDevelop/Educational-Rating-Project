package soqqa.com.ratingproject.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "works")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WorkEntity extends BaseEntity {
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
    private List<UserEntity> employees;
}
