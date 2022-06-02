package com.example.bookstore.dto;

//import lombok.ToString;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
public class UserRegistrationDTO {
    @NotEmpty(message = "first name cannot be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First name is Invalid!!! ")
    public String firstName;
    @NotEmpty(message = "last name cannot be null")
    @Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s]{2,}$", message = "Last name is Invalid!!! ")

    public String lastName;
    @NotEmpty(message = "Email cannot be empty!")
    @Pattern(regexp = "^[a-zA-Z-9]+([._+-]*[0-9A-Za-z]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-z]{2,4})?$",
            message = "Invalid email")
    public String email;

    @NotEmpty(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]{1})[A-Za-z\\d!@#$%^&*]{8,}$",
            message = "Invalid password")
    public String password;
    @NotEmpty(message = "Address cannot be empty")
    @Pattern(regexp = "^[A-Za-z,.0-9\\s]{3,}$", message = "Invalid address")
    public String address;
    public UserRegistrationDTO(String firstName, String lastName, String address, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}
