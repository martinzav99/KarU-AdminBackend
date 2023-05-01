package com.ungspp1.gadminbackend.repository;

import com.ungspp1.gadminbackend.entity.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDE, Long> {

    Optional<UserDE> findByUsernameAndPassword(String username, String password);

    Optional<UserDE> findByUsername(String username);

    @Query(value = "select u from UserDE u where u.contactData.phoneCode = :phoneCode and u.contactData.phoneNumber = :phoneNumber")
    Optional<UserDE> findByPhone(@Param("phoneCode") String phoneCode, @Param("phoneNumber") String phoneNumber);

    @Query(value = "select u from UserDE u where u.contactData.email = :email")
    Optional<UserDE> findByEmail(@Param("email") String email);
}
