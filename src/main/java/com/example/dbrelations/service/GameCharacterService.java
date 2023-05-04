package com.example.dbrelations.service;

import com.example.dbrelations.entity.GameCharacter;
import com.example.dbrelations.entity.Player;
import com.example.dbrelations.repository.GameCharacterRepository;
import com.example.dbrelations.request.CreateCharacterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameCharacterService {
    private final GameCharacterRepository characterRepository;

    private final PlayerService playerService;

    @Autowired
    public GameCharacterService(GameCharacterRepository characterRepository, PlayerService playerService) {
        this.characterRepository = characterRepository;
        this.playerService = playerService;
    }

    public List<GameCharacter> getCharactersByPlayerId(long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return player.getCharacters();
    }

    @Transactional
    public GameCharacter createCharacter(CreateCharacterRequest request) {
        if (characterRepository.existsByName(request.name())) {
            throw  new RuntimeException();
        }

        Player player = playerService.getPlayerById(request.playerId());

        GameCharacter newCharacter = new GameCharacter(
                request.name(),
                request.gender(),
                request.role()
        );

        newCharacter.setPlayer(player);
        return characterRepository.save(newCharacter);
    }

    @Transactional
    public void deleteCharacter(long characterId) {
        //TODO: create exception to handle error
        if (!characterRepository.existsById(characterId)) {
            throw new RuntimeException();
        }

        characterRepository.deleteById(characterId);
    }
}
