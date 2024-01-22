package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.dto.request.WorkCreateRequest;
import soqqa.com.ratingproject.dto.response.WorkResponse;
import soqqa.com.ratingproject.dto.response.UserResponse;
import soqqa.com.ratingproject.enitity.WorkEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.exception.DataAlreadyExistsException;
import soqqa.com.ratingproject.exception.DataNotFoundException;
import soqqa.com.ratingproject.repository.UserRepository;
import soqqa.com.ratingproject.repository.WorkRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WorkResponse create(WorkCreateRequest createRequest) {
        if (workRepository.existsByName(createRequest.getName())){
            throw new DataAlreadyExistsException("Work already exists with this name: " + createRequest.getName());
        }
        WorkEntity work = modelMapper.map(createRequest, WorkEntity.class);
        workRepository.save(work);
        return modelMapper.map(createRequest, WorkResponse.class);
    }

    public List<WorkEntity> getAllWorks(){
        return workRepository.findAll();
    }

    public String delete(UUID workId){
        WorkEntity work = getWork(workId);
        workRepository.deleteById(work.getId());
        return "Successfully deleted";
    }

    public WorkEntity getWork(UUID workId){
        return workRepository.findById(workId)
                .orElseThrow(() -> new DataNotFoundException("Work not found with this id: " + workId));
    }

    public List<UserEntity> getStudentsByWork(UUID workId) {
        WorkEntity work = getWork(workId);
        return userRepository.findAllByWork(work);
    }

    public WorkResponse searchByName(String keyWord) {
        WorkEntity workEntity = workRepository.searchByNameContainsIgnoreCase(keyWord);
        WorkResponse response = modelMapper.map(workEntity, WorkResponse.class);
        List<UserEntity> allByWork = userRepository.findAllByWork(workEntity);
        List<UserResponse> collect = allByWork.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class))
                .collect(Collectors.toList());
        response.setEmployees(collect);
        return response;
    }

}
