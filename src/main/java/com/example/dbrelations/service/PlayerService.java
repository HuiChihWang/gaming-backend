package com.example.dbrelations.service;

import com.example.dbrelations.entity.Player;
import com.example.dbrelations.entity.Profile;
import com.example.dbrelations.repository.PlayerRepository;
import com.example.dbrelations.request.NewPlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Player createPlayer(NewPlayerRequest request) {
        Profile profile = new Profile(request.twitter(), request.instagram(), request.email());
        Player newPlayer = new Player(request.firstName(), request.lastName(), profile);
        return playerRepository.save(newPlayer);
    }
}
