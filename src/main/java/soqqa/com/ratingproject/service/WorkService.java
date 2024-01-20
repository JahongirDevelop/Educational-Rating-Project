package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.repository.WorkRepository;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
}
