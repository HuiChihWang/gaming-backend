package com.example.dbrelations.repository;

import com.example.dbrelations.entity.GameRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoleRepository extends JpaRepository<GameRole, Integer> {
    boolean existsByName(String name);
}
