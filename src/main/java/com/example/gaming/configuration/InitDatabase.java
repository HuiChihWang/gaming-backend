package com.example.gaming.configuration;

import com.example.gaming.entity.*;
import com.example.gaming.repository.GameCharacterRepository;
import com.example.gaming.repository.GameRoleRepository;
import com.example.gaming.repository.PlayerRepository;
import com.example.gaming.repository.SkillRepository;
import com.example.gaming.utility.Gender;
import com.example.gaming.utility.Role;
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
    CommandLineRunner initDatabase2(SkillRepository skillRepository, GameRoleRepository roleRepository) {
        List<GameRole> roles = roleRepository.saveAll(getRoles());
        List<Skill> skills = skillRepository.saveAll(getSkills());

        roles.get(0).addSkill(skills.get(3));
        roles.get(0).addSkill(skills.get(6));

        roles.get(1).addSkill(skills.get(2));
        roles.get(1).addSkill(skills.get(5));

        roles.get(2).addSkill(skills.get(0));
        roles.get(2).addSkill(skills.get(3));

        roles.get(3).addSkill(skills.get(1));
        roles.get(3).addSkill(skills.get(2));
        roles.get(3).addSkill(skills.get(4));

        List<GameRole> updatedRoles = roleRepository.saveAll(roles);

        return args -> {
            for (Skill skill: skills) {
                logger.info("Preload Skill: {}", skill);
            }

            for (GameRole role: updatedRoles) {
                logger.info("Preload Role: {}", role);
            }
        };
    }

    private List<GameRole> getRoles() {
        return List.of(
                new GameRole("magician"),
                new GameRole("warrior"),
                new GameRole("cleric"),
                new GameRole("thief")
        );


    }
    private List<Skill> getSkills() {
        return List.of(
                new Skill("recover"),
                new Skill("flash"),
                new Skill("stab"),
                new Skill("magic attack"),
                new Skill("steal"),
                new Skill("strengthen"),
                new Skill("magic explosion")
        );

    }
    @Bean
    CommandLineRunner initDataBase(PlayerRepository playerRepository, GameCharacterRepository characterRepository) {
        List<Player> players = createPlayers();
        List<GameCharacter> characters = createCharacters();

        players.get(0).addCharacter(characters.get(0));
        players.get(0).addCharacter(characters.get(1));
        players.get(1).addCharacter(characters.get(2));
        players.get(2).addCharacter(characters.get(3));

        List<Player> savedPlayers = playerRepository.saveAll(players);
        List<GameCharacter> savedCharacters = characterRepository.findAll();

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
