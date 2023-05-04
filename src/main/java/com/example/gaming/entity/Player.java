package com.example.gaming.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonManagedReference
    private Profile profile;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonBackReference
    private final List<GameCharacter> characters = new ArrayList<>();

    public Player() {}

    public Player(String firstName, String lastName, Profile profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile = profile;
    }

    public Player(long id, String firstName, String lastName, Profile profile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<GameCharacter> getCharacters() {
        return characters;
    }

    public void addCharacter(GameCharacter character) {
        this.characters.add(character);
        character.setPlayer(this);
    }

    public void removeCharacter(GameCharacter character) {
        this.characters.remove(character);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName) && Objects.equals(profile, player.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profile_id='" + profile.getId() + '\'' +
                ", profile_email='" + profile.getEmail() + '\'' +
                '}';
    }
}
