package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.dto.request.EducationCreateRequest;
import soqqa.com.ratingproject.dto.response.EducationResponse;
import soqqa.com.ratingproject.dto.response.UserResponse;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.exception.DataAlreadyExistsException;
import soqqa.com.ratingproject.exception.DataNotFoundException;
import soqqa.com.ratingproject.repository.EducationRepository;
import soqqa.com.ratingproject.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public EducationResponse create(EducationCreateRequest createRequest) {
        if (educationRepository.existsByName(createRequest.getName())){
            throw new DataAlreadyExistsException("Education already exists with this name: " + createRequest.getName());
        }
        EducationEntity education = modelMapper.map(createRequest, EducationEntity.class);
        educationRepository.save(education);
        return modelMapper.map(createRequest, EducationResponse.class);
    }

    public List<EducationEntity> getAllEducations(){
        List<EducationEntity> educations = educationRepository.findAll();
        educations.sort(Comparator.comparingInt(EducationEntity::getEmployedCount).reversed());
        return educations;
    }

    public String delete(UUID educationId){
        EducationEntity education = getEducation(educationId);
        educationRepository.deleteById(education.getId());
        return "Successfully deleted";
    }

    public EducationEntity getEducation(UUID educationId){
        return educationRepository.findById(educationId)
                .orElseThrow(() -> new DataNotFoundException("Education not found with this id: " + educationId));
    }

    public List<UserEntity> getStudentsByEducation(UUID educationId) {
        EducationEntity education = getEducation(educationId);
        return userRepository.findAllByEducation(education);
    }

    public EducationResponse searchByName(String keyWord) {
        EducationEntity educationEntity = educationRepository.searchByNameContainsIgnoreCase(keyWord);
        EducationResponse response = modelMapper.map(educationEntity, EducationResponse.class);
        List<UserEntity> allByEducation = userRepository.findAllByEducation(educationEntity);
        List<UserResponse> collect = allByEducation.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class))
                .collect(Collectors.toList());
        response.setStudents(collect);
         return response;
    }

}
