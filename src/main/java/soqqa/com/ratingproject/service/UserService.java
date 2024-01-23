package soqqa.com.ratingproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import soqqa.com.ratingproject.config.jwt.AuthDto;
import soqqa.com.ratingproject.config.jwt.JwtResponse;
import soqqa.com.ratingproject.config.jwt.JwtService;
import soqqa.com.ratingproject.dto.request.UserCreateRequest;
import soqqa.com.ratingproject.dto.response.UserResponse;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.enitity.WorkEntity;
import soqqa.com.ratingproject.enitity.enums.UserRole;
import soqqa.com.ratingproject.enitity.enums.UserStatus;
import soqqa.com.ratingproject.exception.DataNotFoundException;
import soqqa.com.ratingproject.repository.EducationRepository;
import soqqa.com.ratingproject.repository.UserRepository;
import soqqa.com.ratingproject.repository.WorkRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static soqqa.com.ratingproject.enitity.enums.UserRole.*;
import static soqqa.com.ratingproject.enitity.enums.UserStatus.EMPLOYED;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final WorkRepository workRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final EmailSenderService senderService;

    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (dto.getPassword().equals(user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }

    public String signUp(UserCreateRequest dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("this email is already exists: " + dto.getEmail());
        }
        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
        Optional<EducationEntity> education = educationRepository.findByName(dto.getEducation());
        if (education.isPresent()) {
            List<UserEntity> students = education.get().getStudents();
            students.add(userEntity);
            education.get().setStudents(students);

            userEntity.setStatus(dto.getStatus());
            if (dto.getStatus() == EMPLOYED) education.get().setEmployedCount(+1);

            EducationEntity mapped = modelMapper.map(education, EducationEntity.class);
            educationRepository.save(mapped);
            userEntity.setEducation(mapped);
        } else {
            EducationEntity entity = new EducationEntity();
            entity.setName(dto.getEducation());
            educationRepository.save(entity);
            userEntity.setEducation(entity);
        }


        Optional<WorkEntity> work = workRepository.findByName(dto.getWork());
        if (work.isPresent()) {
            List<UserEntity> employees = work.get().getEmployees();
            employees.add(userEntity);
            work.get().setEmployees(employees);

            workRepository.save(work.get());
            userEntity.setWork(work.get());
            userEntity.setUserRole(USER);

        } else {
            WorkEntity workEntity = new WorkEntity();
            workEntity.setName(dto.getWork());
            workRepository.save(workEntity);
            userEntity.setWork(workEntity);
        }
        userEntity.setUserRole(USER);
        userEntity.setCreatedDate(LocalDateTime.now());
        userRepository.save(userEntity);
        return "Successfully sign up";
    }


    public  <T> T me(Principal principal) {
        UserEntity userEntity = userRepository.findById(UUID.fromString(principal.getName()))
                .orElseThrow(() -> new DataNotFoundException("User not found!"));;
        UserRole role = userEntity.getUserRole();
        if (role == ADMIN){
            UserResponse adminResponse = modelMapper.map(userEntity, UserResponse.class);
            adminResponse.setRole(ADMIN);
            return (T) adminResponse;
        } else if (role == USER) {
            UserResponse userResponse =  modelMapper.map(userEntity,UserResponse.class);
            userResponse.setRole(USER);
            return (T)userResponse;
        }
        return (T) modelMapper.map(userEntity,UserResponse.class);
    }

    public UserResponse updateProfile(UserCreateRequest user, Principal principal) {
        UserEntity entity = userRepository.findById(UUID.fromString(principal.getName())).
                orElseThrow(() -> new DataNotFoundException("User not found!"));
        if(!Objects.equals(user.getName(),null)){
            entity.setName(user.getName());
        }if(!Objects.equals(user.getPhoneNumber(),null)){
            entity.setPhoneNumber(user.getPhoneNumber());
        }if(!Objects.equals(user.getPassword(),null)){
            entity.setPassword(user.getPassword());
        }if(!Objects.equals(user.getEmail(),null)){
            entity.setEmail(user.getEmail());
        }
        UserEntity userEntity = userRepository.save(entity);
        return modelMapper.map(userEntity,UserResponse.class);
    }

    public String delete(Principal principal) {
        userRepository.deleteById(UUID.fromString(principal.getName()));
        return "Deleted!";
    }



//    public UserResponse addModerator(UserCreateRequest userCr) {
//        existByEmail(userCr);
//        UserEntity userEntity = modelMapper.map(userCr, UserEntity.class);
//        userEntity.setUserRole(MODERATOR);
//        userRepository.save(userEntity);
//        return modelMapper.map(userEntity, UserResponse.class);
//    }

//
    public UserResponse addAdmin(UserCreateRequest userCr) {
        if (!userRepository.existsByEmail(userCr.getEmail())) {
            throw new DataNotFoundException("User not found");
        }
        UserEntity userEntity = modelMapper.map(userCr, UserEntity.class);
        userEntity.setUserRole(ADMIN);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);
    }

    public List<UserResponse> searchByEducationOrWork(String keyWord) {
        List<UserEntity> userEntities = userRepository.searchAllByEducationNameContainingIgnoreCaseOrWorkNameContainingIgnoreCase(keyWord, keyWord);
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class))
                .collect(Collectors.toList());
    }
}
