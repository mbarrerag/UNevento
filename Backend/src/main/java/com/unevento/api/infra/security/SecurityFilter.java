package com.unevento.api.infra.security;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final VerificationTokenService verificationToken;
    Long id = null;

    public SecurityFilter(UserRepository userRepository, VerificationTokenService verificationToken) {
        this.userRepository = userRepository;
        this.verificationToken = verificationToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = request.getHeader("Authorization");

        if (token != null) {
            String Token = token.replace("Bearer ", "");
            String[] parts = Token.split(", ");
            String jwtToken = parts[1];
            Long idLong = Long.valueOf(parts[0]);

            VerificationTokenService verificationToken = new VerificationTokenService();
            try {
                System.out.println("Token:" + jwtToken+ " id:" + idLong);
                Usuario usuario = userRepository.findByIdUsuario(idLong);
                String subject = verificationToken.verifyRS256Token(id, jwtToken, usuario);
                System.out.println("Subject: " + subject);

            } catch (Exception e) {
                throw new RuntimeException("Token inválido");
            }



        }
        filterChain.doFilter(request, response);

    }
}
