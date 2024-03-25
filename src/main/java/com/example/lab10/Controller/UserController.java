package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Serivce.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added"));
    }
    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        Boolean isUpdated = userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeUser(@PathVariable Integer id){
        Boolean isRemoved = userService.removeUser(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("user removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }
}
