package dev.eduardo.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.eduardo.security.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Component
public class TokenConfig {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("userId", user.getId())
                    .withSubject(user.getEmail())
                    .withExpiresAt(Instant.now().plusSeconds(86400))
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);
    }

    public Optional<JWTUSerData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUSerData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .build());

        }catch (JWTVerificationException exception){
            return Optional.empty();
        }
    }
}
