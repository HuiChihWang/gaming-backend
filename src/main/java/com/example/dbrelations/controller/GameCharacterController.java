package com.example.dbrelations.controller;

import com.example.dbrelations.entity.GameCharacter;
import com.example.dbrelations.request.CreateCharacterRequest;
import com.example.dbrelations.service.GameCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/characters")
public class GameCharacterController {
    private final GameCharacterService characterService;

    @Autowired
    public GameCharacterController(GameCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{playerId}")
    ResponseEntity<List<GameCharacter>> getCharactersByPlayerId(@PathVariable("playerId") long playerId) {
        List<GameCharacter> characters = characterService.getCharactersByPlayerId(playerId);
        return ResponseEntity.ok(characters);
    }

    //TODO: optimize response by DTO pattern
    @PostMapping
    ResponseEntity<GameCharacter> createNewCharacter(@RequestBody CreateCharacterRequest request) {
        GameCharacter newCharacter = characterService.createCharacter(request);
        return ResponseEntity.ok(newCharacter);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCharacter(@PathVariable("id") long characterId) {
        characterService.deleteCharacter(characterId);
        return ResponseEntity.ok().build();
    }
}
