package com.example.gaming.repository;

import com.example.gaming.entity.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {
    boolean existsByName(String name);
}
