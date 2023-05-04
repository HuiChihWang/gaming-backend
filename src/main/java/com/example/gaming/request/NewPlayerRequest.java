package com.example.gaming.request;

public record NewPlayerRequest(
        String firstName,
        String lastName,
        String twitter,
        String instagram,
        String email
) {
}
