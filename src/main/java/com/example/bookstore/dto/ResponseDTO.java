package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDTO {
    private String message;
    private Object data;

    private String token;
    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
        this.token=token;
    }

    public ResponseDTO(String message, Object data, String token) {
        this.message = message;
        this.data = data;
        this.token=token;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
}
