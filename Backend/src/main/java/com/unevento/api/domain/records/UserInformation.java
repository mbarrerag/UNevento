package com.unevento.api.domain.records;

public record UserInformation(Long id, String name, String lastname, String email, int role, String imageUrl) {
}
