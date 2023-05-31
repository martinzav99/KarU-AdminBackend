package com.ungspp1.gadminbackend.model.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.UserResetPassDE;

@Repository
public interface UserResetPassRepository extends JpaRepository<UserResetPassDE,Long> {
    
    @Query(value = "select u from UserResetPassDE u where u.token = :token")
    Optional<UserResetPassDE> findByToken(@Param("token") String token);

    @Query(value = "select u from UserResetPassDE u where u.userData.username = :username")
    Optional<UserResetPassDE> findByusername(@Param("username") String username);    
}
