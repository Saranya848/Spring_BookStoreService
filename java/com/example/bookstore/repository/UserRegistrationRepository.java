package com.example.bookstore.repository;

import com.example.bookstore.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer> {
@Query(value = "select * from registration_table where email_id= :emailId", nativeQuery = true)
UserRegistrationData findUserListByEmail(String emailId);

//    Optional<UserRegistrationData> findByEmailIdAndPassword(String emailId, String password);

//    Optional<UserRegistrationData> findUserRegistrationDataByEmailId(String emailId);
}
