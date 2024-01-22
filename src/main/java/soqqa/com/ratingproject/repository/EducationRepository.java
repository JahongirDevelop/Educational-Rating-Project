package soqqa.com.ratingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.com.ratingproject.enitity.EducationEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {

    Optional<EducationEntity> findByName(String education);
    boolean existsByName(String name);

    EducationEntity searchByNameContainsIgnoreCase(String name);
}
