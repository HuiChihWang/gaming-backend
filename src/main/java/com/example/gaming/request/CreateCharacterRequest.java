package com.example.gaming.request;

import com.example.gaming.utility.Gender;
import com.example.gaming.utility.Role;

public record CreateCharacterRequest(
        String name,
        Gender gender,
        Role role,
        long playerId
) {
}
