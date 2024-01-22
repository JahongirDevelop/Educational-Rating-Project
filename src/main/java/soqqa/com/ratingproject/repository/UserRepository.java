package soqqa.com.ratingproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.enitity.WorkEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByEducationName(String name);
    List<UserEntity> findAllByEducation(EducationEntity education);
    List<UserEntity> findAllByWork(WorkEntity work);

    boolean existsByEmail(String email);
    List<UserEntity> searchAllByEducationNameContainingIgnoreCaseOrWorkNameContainingIgnoreCase(String education, String work);

}
