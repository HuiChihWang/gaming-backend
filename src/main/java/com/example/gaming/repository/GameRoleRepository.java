package com.example.gaming.repository;

import com.example.gaming.entity.GameRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoleRepository extends JpaRepository<GameRole, Integer> {
    boolean existsByName(String name);
}
