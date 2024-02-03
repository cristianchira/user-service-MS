package com.codedecode.userinfo.controller;


import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

 UserService userService;

 @Autowired
 public UserController(UserService userService) {
  this.userService = userService;
 }

 @GetMapping("/fetchAllUsers")
 public ResponseEntity<List<UserDTO>> fetchAllUsers() {
  return userService.fetchAllUsers();
 }

 @PostMapping("/addUser")
 public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
  return userService.addUser(userDTO);
 }

 @GetMapping("/fetchUserById/{userId}")
 public ResponseEntity<UserDTO> fetchUserDetailsById(@PathVariable Integer userId) {
  return userService.fetchUserDetailsById(userId);
 }

 @GetMapping("/search")
 public ResponseEntity<List<UserDTO>> searchUsersByName(@RequestParam String name) {
  return userService.fetchUsersByName(name);
 }

 @PutMapping("/updateUser/{userId}")
 public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
  UserDTO updatedUser = userService.updateUser(userId, userDTO);
  return new ResponseEntity<>(updatedUser, HttpStatus.OK);
 }
}
