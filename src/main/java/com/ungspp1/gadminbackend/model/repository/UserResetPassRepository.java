package com.ungspp1.gadminbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.UserResetPassDE;

@Repository
public interface UserResetPassRepository extends JpaRepository<UserResetPassDE,Long> {
    
    @Query(value = "select s from UserResetPassDE s where s.userData.contactData.email = :email")
    Optional<UserResetPassDE> findByEmail(@Param("email") String email); // creo que no lo uso we

    @Query(value = "select s from UserResetPassDE s where s.token = :token")
    Optional<UserResetPassDE> findByToken(@Param("token") String token);
}
