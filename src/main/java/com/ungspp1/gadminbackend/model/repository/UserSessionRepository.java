package com.ungspp1.gadminbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.UserSessionDE;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionDE, Long>{

    @Query(value = "select s from UserSessionDE s where s.userData.username = :username")
    Optional<UserSessionDE> findByUsername(@Param("username") String username);

    @Query(value = "select s from UserSessionDE s where s.userData.username = :username and s.userData.password = :password")
    Optional<UserSessionDE> findByUserAndPassword(@Param("username") String username, @Param("password") String password);
}
