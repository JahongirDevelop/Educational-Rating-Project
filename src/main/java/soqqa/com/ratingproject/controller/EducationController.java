package soqqa.com.ratingproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.com.ratingproject.service.EducationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/education")
public class EducationController {
    private final EducationService educationService;
}
