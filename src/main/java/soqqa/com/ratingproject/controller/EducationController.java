package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soqqa.com.ratingproject.dto.request.EducationCreateRequest;
import soqqa.com.ratingproject.dto.response.EducationResponse;
import soqqa.com.ratingproject.enitity.EducationEntity;
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
    public ResponseEntity<String> deleteFloor(@PathVariable UUID educationId){
        return ResponseEntity.status(200).body(educationService.delete(educationId));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-educations")
    public List<EducationEntity> getAllEducations(){
        return educationService.getAllEducations();
    }
}
