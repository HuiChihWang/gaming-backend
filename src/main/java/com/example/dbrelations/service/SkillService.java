package com.example.dbrelations.service;

import com.example.dbrelations.entity.GameRole;
import com.example.dbrelations.entity.Skill;
import com.example.dbrelations.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final GameRoleService gameRoleService;

    @Autowired
    public SkillService(SkillRepository skillRepository, GameRoleService gameRoleService) {
        this.skillRepository = skillRepository;
        this.gameRoleService = gameRoleService;
    }

    public Skill getSkillById(int skillId) {
        return skillRepository
                .findById(skillId)
                .orElseThrow(RuntimeException::new);
    }

    public List<Skill> getAllSkillsByRoleId(int roleId) {
        GameRole role = gameRoleService.getGameRoleById(roleId);
        return role.getSkills();
    }

    @Transactional
    public void deleteSkillById(int skillId) {
        Skill skill = getSkillById(skillId);
        List<GameRole> rolesWithSkill = skill.getRoles();

        rolesWithSkill.forEach(role -> gameRoleService.removeSkillFromRole(role.getId(), skillId));

        skillRepository.deleteById(skillId);
    }
}
