package com.example.bookstore.service.userregistration;

import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.exception.userregistration.UserRegistrationCustomException;
import com.example.bookstore.model.UserRegistrationData;
import com.example.bookstore.model.Email;
import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.bookstore.util.TokenUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserRegistrationService implements IUserRegistrationService{
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    IEmailService emailService;
    @Override
    public List<UserRegistrationData> getUserRegistrationData() {

        return userRegistrationRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseDTO> createUserRegistrationData(UserRegistrationDTO userRegistrationDTO) {

        UserRegistrationData user= userRegistrationRepository.save(new UserRegistrationData(userRegistrationDTO));

        String token = tokenUtil.createToken(user.getUserId());
//        Email email = new Email(user.getEmail()," user is registered",user.getFirstName() + "=>" + emailService.getLink(token));

        Email email = new Email(user.getEmail()," user is registered",user.getFirstName() + "=>" + emailService.getLink(token));
        emailService.sendMail(email);
        ResponseDTO responseDTO = new ResponseDTO("User is created", user,token);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> verify(String token) {
        Optional<UserRegistrationData> user=userRegistrationRepository.findById(tokenUtil.decodeToken(token));
        if (user.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO("ERROR: Invalid token", null, token);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
        user.get().setVerified(true);
        userRegistrationRepository.save(user.get());
        ResponseDTO responseDTO = new ResponseDTO(" The user has been verified ", user, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

//    @Override
//    public List<UserRegistrationData> getUserRegistrationData() {
//        List<UserRegistrationData> usersList = userRegistrationRepository.findAll();
//        return usersList;
//    }

    @Override
    public UserRegistrationData getUserRegistrationDataByUserId(int userId) {
        System.out.println("Userid:" +userId);
        return userRegistrationRepository.findById(userId)
                .orElseThrow(() -> new UserRegistrationCustomException("User  with id " + userId + " does not exist in database..!"));

    }

    @Override
    public UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = this.getUserByEmailId(userId);
        userRegistrationData.updateUserRegistrationData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
    }

    private UserRegistrationData getUserByEmailId(int userId) {
        return null;
    }

    @Override
    public UserRegistrationData getUserByEmailId(String emailId) {
        return userRegistrationRepository.findUserListByEmail(emailId);
    }

    @Override
    public ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO) {
        UserRegistrationData user=userRegistrationRepository.findUserListByEmail(loginDTO.getEmailId());
        boolean password=user.getPassword().equals(loginDTO.getPassword());
        if(password=false){
            ResponseDTO responseDTO=new ResponseDTO("login failed",null,null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
        else{
            ResponseDTO responseDTO=new ResponseDTO(" Login Sucessfully",user,null);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }


}
