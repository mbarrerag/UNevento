package com.unevento.api.infra.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenAndUserId {
    private String jwtToken;
    private Long userId;

    public TokenAndUserId(String jwtToken, Long userId) {
        this.jwtToken = jwtToken;
        this.userId = userId;
    }

}
