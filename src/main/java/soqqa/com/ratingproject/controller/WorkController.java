package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soqqa.com.ratingproject.dto.request.WorkCreateRequest;
import soqqa.com.ratingproject.dto.response.WorkResponse;
import soqqa.com.ratingproject.enitity.WorkEntity;
import soqqa.com.ratingproject.enitity.UserEntity;
import soqqa.com.ratingproject.service.WorkService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work")
public class WorkController {
    private final WorkService workService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-work")
    public ResponseEntity<WorkResponse> createWork(@RequestBody @Valid WorkCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(workService.create(createRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-work{workId}")
    public ResponseEntity<String> deleteWork(@PathVariable UUID workId){
        return ResponseEntity.status(200).body(workService.delete(workId));
    }


    @GetMapping("/all-works")
    public List<WorkEntity> getAllWorks(){
        return workService.getAllWorks();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-work/{id}")
    public WorkEntity getWorkById(@PathVariable UUID id) {
        return workService.getWork(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-students-by-work/{work_id}")
    public ResponseEntity<List<UserEntity>> getStudentsByWork(@PathVariable UUID work_id){
        return ResponseEntity.status(200).body(workService.getStudentsByWork(work_id));
    }

    @GetMapping("/search")
    public ResponseEntity<WorkResponse> searchByName(@RequestParam String keyWord) {
        return ResponseEntity.status(200).body(workService.searchByName(keyWord));
    }
}
