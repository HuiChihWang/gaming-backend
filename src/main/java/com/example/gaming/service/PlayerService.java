package com.example.gaming.service;

import com.example.gaming.entity.Player;
import com.example.gaming.entity.Profile;
import com.example.gaming.repository.PlayerRepository;
import com.example.gaming.request.NewPlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(long id) {
        Optional<Player> player = playerRepository.findById(id);

        //TODO: create not found exception
        if (player.isEmpty()) {
            throw new RuntimeException("Player doesn't exist.");
        }

        return player.get();
    }

    @Transactional
    public Player createPlayer(NewPlayerRequest request) {
        Profile profile = new Profile(request.twitter(), request.instagram(), request.email());
        Player newPlayer = new Player(request.firstName(), request.lastName(), profile);
        return playerRepository.save(newPlayer);
    }

    @Transactional
    public void deletePlayer(long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw  new RuntimeException();
        }

        playerRepository.deleteById(playerId);
    }
}
