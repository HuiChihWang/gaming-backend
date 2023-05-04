package com.example.gaming.controller;

import com.example.gaming.entity.GameRole;
import com.example.gaming.service.GameRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class GameRoleController {
    private final GameRoleService roleService;

    @Autowired
    public GameRoleController(GameRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    ResponseEntity<List<GameRole>> getAllRoles() {
        List<GameRole> roles = roleService.getAllGameRoles();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRoleById(@PathVariable("id") int roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok().build();
    }
}
