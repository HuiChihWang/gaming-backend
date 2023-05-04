package com.example.gaming.controller;

import com.example.gaming.entity.Profile;
import com.example.gaming.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Profile> getProfileWithId(@PathVariable("id") long profileId) {
        Profile profile = profileService.getProfileById(profileId);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    void deleteProfile(@PathVariable("id") long profileId) {
        profileService.deleteProfileById(profileId);
    }


}
