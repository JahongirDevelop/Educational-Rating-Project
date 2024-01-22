package soqqa.com.ratingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {

    Optional<EducationEntity> findByName(String education);
    List<EducationEntity> findAllByStudents(List<UserEntity> students);
    boolean existsByName(String name);
}
