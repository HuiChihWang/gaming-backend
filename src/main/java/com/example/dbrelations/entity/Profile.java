package com.example.dbrelations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String twitter;
    private String instagram;
    private String email;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonBackReference
    private Player player;

    public Profile() {}

    public Profile(String twitter, String instagram, String email) {
        this.twitter = twitter;
        this.instagram = instagram;
        this.email = email;
    }

    public Profile(long id, String twitter, String instagram, String email) {
        this.id = id;
        this.twitter = twitter;
        this.instagram = instagram;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id && Objects.equals(twitter, profile.twitter) && Objects.equals(instagram, profile.instagram) && Objects.equals(email, profile.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", twitter='" + twitter + '\'' +
                ", instagram='" + instagram + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
