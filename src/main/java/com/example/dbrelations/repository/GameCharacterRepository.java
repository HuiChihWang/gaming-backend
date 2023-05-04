package com.example.dbrelations.repository;

import com.example.dbrelations.entity.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {
    boolean existsByName(String name);
}
