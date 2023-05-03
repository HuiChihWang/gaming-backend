package com.example.dbrelations.request;

import com.example.dbrelations.utility.Gender;
import com.example.dbrelations.utility.Role;

public record CreateCharacterRequest(
        String name,
        Gender gender,
        Role role,
        long playerId
) {
}
