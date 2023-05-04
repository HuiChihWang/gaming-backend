package com.example.dbrelations.controller;

import com.example.dbrelations.entity.Player;
import com.example.dbrelations.request.NewPlayerRequest;
import com.example.dbrelations.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    ResponseEntity<Player> getPlayerById(@PathVariable("id") long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(player);
    }

    @PostMapping
    ResponseEntity<Player> createNewPlayer(@RequestBody NewPlayerRequest request) {
        Player newPlayer = playerService.createPlayer(request);
        return ResponseEntity.ok(newPlayer);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable("id") long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok().build();
    }
}
