package com.example.dbrelations.configuration;

import com.example.dbrelations.entity.GameCharacter;
import com.example.dbrelations.entity.Player;
import com.example.dbrelations.entity.Profile;
import com.example.dbrelations.repository.GameCharacterRepository;
import com.example.dbrelations.repository.PlayerRepository;
import com.example.dbrelations.utility.Gender;
import com.example.dbrelations.utility.Role;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitDatabase {
    private static final Logger logger =  LoggerFactory.getLogger(InitDatabase.class);

    @Bean
    CommandLineRunner initDataBase(PlayerRepository playerRepository, GameCharacterRepository characterRepository) {
        List<Player> players = createPlayers();
        List<GameCharacter> characters = createCharacters();

        List<Player> savedPlayers = playerRepository.saveAll(players);
        List<GameCharacter> savedCharacters = characterRepository.saveAll(characters);
        return args -> {
            for (Player player: savedPlayers) {
                logger.info("Preload Player: {}", player);
            }

            for (GameCharacter character: savedCharacters) {
                logger.info("Preload Character: {}", character);
            }
        };
    }

    List<Player> createPlayers() {
        List<Profile> profiles = List.of(
                new Profile("@gilbertwang", "gilbert_wang", "gilbertwang@gmail.com"),
                new Profile("@albahuang", "alba_huang", "albahuang@gmail.com"),
                new Profile("@kevinHu", "kevin_hu", "kevinhu@gmail.com")
        );

        List<Player> players = List.of(
                new Player("Gilbert", "Wang", new Profile()),
                new Player("Alba", "Huang", new Profile()),
                new Player("Kevin", "Hu", new Profile())
        );

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setProfile(profiles.get(i));
        }

        return players;
    }

    List<GameCharacter> createCharacters() {
        return List.of(
                new GameCharacter("taya87136", Gender.MALE, Role.WARRIOR),
                new GameCharacter("sukilatic", Gender.FEMALE, Role.CLERIC),
                new GameCharacter("gg5257", Gender.MALE, Role.MAGICIAN),
                new GameCharacter("taya30621", Gender.FEMALE, Role.THIEF)
        );
    }
}
