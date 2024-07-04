package com.lec11.orm.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lec11.orm.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserIdAndUserPw(String userId, String userPw);
    UserEntity findByUserId(String userId);
    
}