package com.ungspp1.gadminbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.entity.UserSessionDE;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionDE, Long>{

    @Query(value = "select s from UserSessionDE s where s.userData.username = :username")
    UserSessionDE findSessionByUsername(@Param("username") String username);
}
