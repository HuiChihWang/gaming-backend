package com.example.dbrelations.controller;

import com.example.dbrelations.entity.Skill;
import com.example.dbrelations.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/skills")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getSkillsByRoleId(@RequestParam("roleId") int roleId) {
        List<Skill> skills = skillService.getAllSkillsByRoleId(roleId);
        return ResponseEntity.ok(skills);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("id") int skillId) {
        skillService.deleteSkillById(skillId);
        return ResponseEntity.ok().build();
    }
}
