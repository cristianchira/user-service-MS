package com.codedecode.userinfo.service;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import com.codedecode.userinfo.exceptions.UserNotFoundException;
import com.codedecode.userinfo.mapper.UserMapper;
import com.codedecode.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

 @Autowired
 UserRepo userRepo;

 public ResponseEntity<List<UserDTO>> fetchAllUsers() {
  List<User> users = userRepo.findAll();
  List<UserDTO> usersDTO = users.stream().map(user -> UserMapper.INSTANCE.mapUserToUserDTO(user)).collect(Collectors.toList());
  return new ResponseEntity<>(usersDTO, HttpStatus.OK);
 }

 public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
  Optional<User> fetchedUser = userRepo.findById(userId);
  if (fetchedUser.isPresent()) {
   return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
  } else {
   throw new UserNotFoundException("User with id " + userId + " is not found!");
  }

 }

 public ResponseEntity<List<UserDTO>> fetchUsersByName(String name) {
  List<User> users = userRepo.findByUserName(name);
  List<UserDTO> usersDTO = users.stream().map(user -> UserMapper.INSTANCE.mapUserToUserDTO(user)).collect(Collectors.toList());
  return new ResponseEntity<>(usersDTO, HttpStatus.OK);
 }

 public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
  User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
  return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(savedUser), HttpStatus.OK);

 }

 public UserDTO updateUser(Integer userId, UserDTO userDTO) {
  // Fetch existing user by userId
  User existingUser = userRepo.findById(userId)
          .orElseThrow(() -> new UserNotFoundException("User not found"));

  // Update fields with json payload
  existingUser.setUserName(userDTO.getUserName());
  existingUser.setUserPassword(userDTO.getUserPassword());
  existingUser.setAddress(userDTO.getAddress());
  existingUser.setCity(userDTO.getCity());

  // Save updated user
  User updatedUser = userRepo.save(existingUser);

  // Convert to DTO and return
  return convertToDTO(updatedUser);
 }

 // Convert to DTO and return
 private UserDTO convertToDTO(User user) {
  UserDTO userDTO = new UserDTO();
  userDTO.setUserId(user.getUserId());
  userDTO.setUserName(user.getUserName());
  userDTO.setUserPassword(user.getUserPassword());
  userDTO.setAddress(user.getAddress());
  userDTO.setCity(user.getCity());
  return userDTO;
 }

}
