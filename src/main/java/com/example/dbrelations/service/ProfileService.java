package com.example.dbrelations.service;

import com.example.dbrelations.entity.Profile;
import com.example.dbrelations.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfileById(long profileId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()) {
            throw new RuntimeException();
        }
        return profile.get();
    }

    public void deleteProfileById(long profileId) {
        Optional<Profile> profile = profileRepository.findById(profileId);

        if (profile.isEmpty()) {
            throw new RuntimeException();
        }

        profileRepository.deleteById(profileId);
    }
}
