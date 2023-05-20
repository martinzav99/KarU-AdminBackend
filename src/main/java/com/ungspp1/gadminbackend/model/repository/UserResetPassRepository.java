package com.ungspp1.gadminbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.UserResetPassDE;

@Repository
public interface UserResetPassRepository extends JpaRepository<UserResetPassDE,Long> {
     
    @Modifying
    @Query(value = "delete from UserResetPassDE s where s.userData.username = :username")
    void DeleteByUsername(@Param("username") String username);

    @Query(value = "select s from UserResetPassDE s where s.token = :token")
    Optional<UserResetPassDE> findByToken(@Param("token") String token);
}
