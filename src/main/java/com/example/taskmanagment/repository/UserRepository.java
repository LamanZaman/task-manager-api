package com.example.taskmanagment.repository;

import com.example.taskmanagment.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    Optional<UserEntity> findByIdAndIsEnabledTrue(Integer id);

    Page<UserEntity> findAllByIsEnabledTrue(Pageable pageable);
}
