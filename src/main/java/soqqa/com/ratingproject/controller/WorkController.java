package soqqa.com.ratingproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.com.ratingproject.service.WorkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work")
public class WorkController {
    private final WorkService workService;
}
