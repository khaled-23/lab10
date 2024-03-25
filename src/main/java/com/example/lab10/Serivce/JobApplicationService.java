package com.example.lab10.Serivce;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserService userService;
    private final  JobPostService jobPostService;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;


    public List<JobApplication> getAllJobApplication(){
        return jobApplicationRepository.findAll();
    }

    public String apply(JobApplication jobApplication) {
        Boolean doesUserExists = userService.doesUserExists(jobApplication.getUser_id());
        if(!doesUserExists){
            return "0";
        }
        Boolean jobPostExists = jobPostService.doesJobPostExists(jobApplication.getJob_post_id());
        if(!jobPostExists){
            return "1";
        }
        jobApplicationRepository.save(jobApplication);
        return "2";
    }

    public String withdrawJobApplication(Integer userId, Integer jobApplicationId) {
        Boolean doesUserExists = userService.doesUserExists(userId);
        if(!doesUserExists){
            return "0";
        }
        JobApplication jobApplication = jobApplicationRepository.getById(jobApplicationId);
        if(jobApplication==null){
            return "1";
        }
        if(jobApplication.getUser_id().equals(userId)&& jobApplication.getId().equals(jobApplicationId)){
            jobApplicationRepository.deleteById(jobApplicationId);
            return "2";
        }
        return "3";
    }
}
