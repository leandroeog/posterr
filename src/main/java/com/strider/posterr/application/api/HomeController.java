package com.strider.posterr.application.api;

import com.strider.posterr.application.infra.fake.UserFake;
import com.strider.posterr.application.usecase.ShowHomeUseCase;
import com.strider.posterr.application.usecase.exception.InvalidHomeInputException;
import com.strider.posterr.application.usecase.input.HomeInput;
import com.strider.posterr.application.usecase.output.HomeOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("api/home")
public class HomeController {

    private final ShowHomeUseCase showHomeUseCase;

    @Autowired
    public HomeController(ShowHomeUseCase showHomeUseCase) {
        this.showHomeUseCase = showHomeUseCase;
    }

    @GetMapping
    public ResponseEntity<?> show(
            @RequestParam(value = "isPrivate", defaultValue = "false") boolean isPrivate,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber
    ) {

        try {

            UUID userId = isPrivate ? UserFake.getDefault() : UserFake.getRandom();

            HomeInput input = new HomeInput(isPrivate, startDate, endDate, userId, pageNumber);

            HomeOutput output = showHomeUseCase.execute(input);

            return ResponseEntity.ok(output);

        } catch (InvalidHomeInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
