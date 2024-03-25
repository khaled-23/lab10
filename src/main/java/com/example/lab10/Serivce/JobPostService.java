package com.example.lab10.Serivce;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
         return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost){
        JobPost j = jobPostRepository.getById(id);
        if(j == null){
            return false;
        }
        j.setTitle(jobPost.getTitle());
        j.setDescription(jobPost.getDescription());
        j.setLocation(jobPost.getLocation());
        j.setSalary(jobPost.getSalary());
        j.setPosting_date(jobPost.getPosting_date());
        jobPostRepository.save(j);
        return true;
    }

    public Boolean removeJobPost(Integer id){
        JobPost jobPost = jobPostRepository.getById(id);
        if(jobPost==null){
            return false;
        }
        jobPostRepository.deleteById(id);
        return true;
    }

    public Boolean doesJobPostExists(Integer jobPostId){
        return jobPostRepository.existsById(jobPostId);
    }
}
