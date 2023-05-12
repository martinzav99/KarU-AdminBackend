package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.UserDE;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDE, Long> {

    Optional<UserDE> findByUsernameAndPassword(String username, String password);

    Optional<UserDE> findByUsername(String username);

    List<UserDE> findByType(String type);

    List<UserDE> findByBranch(String branch);

    List<UserDE> findByTechnicalLevel(String level);

    List<UserDE> findAll();

    @Query(value = "select u from UserDE u where u.contactData.phoneCode = :phoneCode and u.contactData.phoneNumber = :phoneNumber")
    Optional<UserDE> findByPhone(@Param("phoneCode") String phoneCode, @Param("phoneNumber") String phoneNumber);

    @Query(value = "select u from UserDE u where u.contactData.email = :email")
    Optional<UserDE> findByEmail(@Param("email") String email);

    @Query(value = "select u from UserDE u where u.id = :id")
    Optional<UserDE> findById(@Param("id") BigInteger id);
    
}
