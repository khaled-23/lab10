package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Serivce.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.StringConcatException;

@RestController
@RequestMapping("/api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/applications")
    public ResponseEntity getAllJobApplication(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }

    @PostMapping("/apply")
    public ResponseEntity apply(@RequestBody @Valid JobApplication jobApplication){
        String condition = jobApplicationService.apply(jobApplication);
        return switch (condition){
            case "0" -> ResponseEntity.status(400).body(new ApiResponse("user doesn't exists"));
            case "1" -> ResponseEntity.status(400).body(new ApiResponse("job posts doesn't exists"));
            case "2" -> ResponseEntity.status(200).body(new ApiResponse("applied"));
            default -> throw new IllegalStateException("Unexpected value: " + condition);
        };
    }

    @DeleteMapping("/withdraw/{userId}/{jobApplicationId}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer userId,@PathVariable Integer jobApplicationId){
        String condition = jobApplicationService.withdrawJobApplication(userId, jobApplicationId);
        return switch (condition){
            case "0" -> ResponseEntity.status(400).body(new ApiResponse("user doesn't exists"));
            case "1" -> ResponseEntity.status(400).body(new ApiResponse("job application doesn't exists"));
            case "2" -> ResponseEntity.status(200).body(new ApiResponse("job application withdraw"));
            case "3" -> ResponseEntity.status(400).body(new ApiResponse("job application doesn't belong to given user"));
            default -> throw new IllegalStateException("Unexpected value: " + condition);
        };
    }

}
