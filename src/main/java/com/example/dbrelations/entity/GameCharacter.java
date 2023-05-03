package com.example.dbrelations.entity;

import com.example.dbrelations.utility.Gender;
import com.example.dbrelations.utility.Role;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    public GameCharacter(String name, Gender gender, Role role) {
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    public GameCharacter() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter gameCharacter = (GameCharacter) o;
        return id == gameCharacter.id && name.equals(gameCharacter.name) && gender == gameCharacter.gender && role == gameCharacter.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }
}