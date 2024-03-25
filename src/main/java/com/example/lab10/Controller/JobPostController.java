package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Serivce.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;


    @GetMapping("/posts")
    public ResponseEntity getAllJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("job post added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        Boolean isUpdated = jobPostService.updateJobPost(id,jobPost);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("job post updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("job post not found"));
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeJobPost(@PathVariable Integer id){
        Boolean isRemoved = jobPostService.removeJobPost(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("job Post removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("job post not found"));
    }
}
