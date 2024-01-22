package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.dto.request.EducationCreateRequest;
import soqqa.com.ratingproject.dto.response.EducationResponse;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.exception.DataAlreadyExistsException;
import soqqa.com.ratingproject.repository.EducationRepository;
import soqqa.com.ratingproject.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public EducationResponse create(EducationCreateRequest createRequest) {
        if (educationRepository.existsByName(createRequest.getName())){
            throw new DataAlreadyExistsException("Education already exists with this name: " + createRequest.getName());
        }
        EducationEntity education = modelMapper.map(createRequest, EducationEntity.class);
        List<UserEntity> students = userRepository.findByEducationName(createRequest.getName());
        education.setStudents(students);
        return modelMapper.map(createRequest, EducationResponse.class);
    }
}
