package com.ungspp1.gadminbackend.repository;

import com.ungspp1.gadminbackend.entity.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDE, Long> {

    Optional<UserDE> findByUsernameAndPassword(String username, String password);

    Optional<UserDE> findByUsername(String username);
}
