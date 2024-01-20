package soqqa.com.ratingproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.com.ratingproject.config.jwt.AuthDto;
import soqqa.com.ratingproject.config.jwt.JwtResponse;
import soqqa.com.ratingproject.dto.request.UserCreateDto;
import soqqa.com.ratingproject.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public JwtResponse signIn(@Valid @RequestBody AuthDto dto) {
        return userService.signIn(dto);
    }

    @PostMapping("/sign-up")
    public String auth(@Valid @RequestBody UserCreateDto dto) {
        return userService.signUp(dto);
    }
}
