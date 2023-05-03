package com.example.dbrelations.configuration;

import com.example.dbrelations.entity.Player;
import com.example.dbrelations.entity.Profile;
import com.example.dbrelations.repository.PlayerRepository;
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
    CommandLineRunner initDataBase(PlayerRepository repository) {
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

        List<Player> savedPlayers = repository.saveAll(players);

        return args -> {
            for (Player player: savedPlayers) {
                logger.info("Preload Player: {}", player);
            }
        };
    }
}
