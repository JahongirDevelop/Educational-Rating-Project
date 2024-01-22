package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soqqa.com.ratingproject.dto.request.UserCreateRequest;
import soqqa.com.ratingproject.dto.response.UserResponse;
import soqqa.com.ratingproject.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
//
    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserCreateRequest userCreateDto, Principal principal){
        return ResponseEntity.ok(userService.updateProfile(userCreateDto, principal));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Principal principal){
        return ResponseEntity.ok(userService.me(principal));
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<String > delete(Principal principal){
        return ResponseEntity.status(200).body(userService.delete(principal));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody @Valid UserCreateRequest userCr) {
        return ResponseEntity.ok(userService.addAdmin(userCr));
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchByEducationOrWork(@RequestParam String keyWord) {
        return ResponseEntity.status(200).body(userService.searchByEducationOrWork(keyWord));
    }

//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create-moderator")
//    public ResponseEntity<UserResponse> createModerator(@RequestBody @Valid UserCreateRequest userCr) {
//        return ResponseEntity.ok(userService.addModerator(userCr));
//    }


}
