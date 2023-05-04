package com.example.dbrelations.service;

import com.example.dbrelations.entity.GameRole;
import com.example.dbrelations.entity.Skill;
import com.example.dbrelations.repository.GameRoleRepository;
import com.example.dbrelations.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameRoleService {
    private final GameRoleRepository roleRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public GameRoleService(GameRoleRepository roleRepository, SkillRepository skillRepository) {
        this.roleRepository = roleRepository;
        this.skillRepository = skillRepository;
    }

    public boolean checkRoleExistsByName(String name) {
        return roleRepository.existsByName(name);
    }

    public List<GameRole> getAllGameRoles() {
        return roleRepository.findAll();
    }

    public GameRole getGameRoleById(int roleId) {
        return roleRepository
                .findById(roleId)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public GameRole createNewRole(String name) {
        if (checkRoleExistsByName(name)) {
            throw new RuntimeException();
        }

        GameRole newRole = new GameRole(name);
        return roleRepository.save(newRole);
    }

    @Transactional
    public void deleteRole(int roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException();
        }

        roleRepository.deleteById(roleId);
    }

    @Transactional
    public void removeSkillFromRole(int roleId, int skillId) {
        GameRole role = getGameRoleById(roleId);
        Skill skill = skillRepository
                .findById(skillId)
                .orElseThrow(RuntimeException::new);
        role.removeSkill(skill);
        roleRepository.save(role);
    }
}
