package com.habeeb.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habeeb.ecommerce.model.User;
import com.habeeb.ecommerce.request.CreateUserReq;
import com.habeeb.ecommerce.request.UpdateUserReq;
import com.habeeb.ecommerce.response.ApiResponse;
import com.habeeb.ecommerce.service.user.IUserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(Long id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(new ApiResponse("Success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserReq req){
        try {
            User user = userService.createUser(req);
            return ResponseEntity.ok(new ApiResponse("Success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserReq req){
        try {
            User user = userService.updateUser(req,id);
            return ResponseEntity.ok(new ApiResponse("Success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
