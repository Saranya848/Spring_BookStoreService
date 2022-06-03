package com.example.bookstore.service.userregistration;

import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Email;
import org.springframework.http.ResponseEntity;


public interface IEmailService {
    public ResponseEntity<ResponseDTO> sendMail(Email email);

    public String getLink(String token);
}
