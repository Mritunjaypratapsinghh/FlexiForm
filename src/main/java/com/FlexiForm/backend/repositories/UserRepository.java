package com.FlexiForm.backend.repositories;

import com.FlexiForm.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
     Optional<UserEntity> findByEmail(String email);
}
