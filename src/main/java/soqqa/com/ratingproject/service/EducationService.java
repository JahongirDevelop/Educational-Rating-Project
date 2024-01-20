package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.repository.EducationRepository;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
}
