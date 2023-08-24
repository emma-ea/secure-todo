package com.emma_ea.secure_todo.repository;

import com.emma_ea.secure_todo.model.UserAuthDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserAuthRepository extends JpaRepository<UserAuthDetail, Long> {
    Optional<UserAuthDetail> findByEmail(String email);
}
