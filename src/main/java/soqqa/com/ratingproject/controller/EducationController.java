package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soqqa.com.ratingproject.dto.request.EducationCreateRequest;
import soqqa.com.ratingproject.dto.response.EducationResponse;
import soqqa.com.ratingproject.enitity.EducationEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.service.EducationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/education")
public class EducationController {
    private final EducationService educationService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-education")
    public ResponseEntity<EducationResponse> createEducation(@RequestBody @Valid EducationCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.create(createRequest));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-education{educationId}")
    public ResponseEntity<String> deleteEducation(@PathVariable UUID educationId){
        return ResponseEntity.status(200).body(educationService.delete(educationId));
    }


    @GetMapping("/all-educations")
    public List<EducationEntity> getAllEducations(){
        return educationService.getAllEducations();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-education/{id}")
    public EducationEntity getEducationById(@PathVariable UUID id) {
        return educationService.getEducation(id);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-students-by-education/{education_id}")
    public ResponseEntity<List<UserEntity>> getStudentsByEducation(@PathVariable UUID education_id){
        return ResponseEntity.status(200).body(educationService.getStudentsByEducation(education_id));
    }

    @GetMapping("/search")
    public ResponseEntity<EducationResponse> searchByName(@RequestParam String keyWord) {
        return ResponseEntity.status(200).body(educationService.searchByName(keyWord));
    }

}
