package com.example.bookstore.service.userregistration;

import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.model.UserRegistrationData;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.LoginDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationService {

List<UserRegistrationData> getUserRegistrationData();

    UserRegistrationData getUserRegistrationDataByUserId(int userId);

    UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userDTO);

    UserRegistrationData getUserByEmailId(String email);


    ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO);

    ResponseEntity<ResponseDTO> createUserRegistrationData(UserRegistrationDTO userRegistrationDTO);

    ResponseEntity<ResponseDTO> verify(String token);
}
