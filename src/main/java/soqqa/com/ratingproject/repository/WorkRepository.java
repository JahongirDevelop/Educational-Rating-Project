package soqqa.com.ratingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.WorkEntity;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, UUID> {

    boolean existsByName(String name);

    WorkEntity searchByNameContainsIgnoreCase(String name);
    Optional<WorkEntity> findByName(String work);
}
