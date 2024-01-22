package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.com.ratingproject.dto.request.EducationCreateRequest;
import soqqa.com.ratingproject.dto.response.EducationResponse;
import soqqa.com.ratingproject.service.EducationService;

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
}
