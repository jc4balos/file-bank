package com.jc4balos.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jc4balos.storage.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM user_data WHERE active = 1")
    List<User> getAllActiveUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM user_data WHERE user_id = :userId")
    User getUser(Long userId);

}
