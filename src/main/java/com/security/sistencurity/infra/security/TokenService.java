package com.security.sistencurity.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.security.sistencurity.domain.usuario.Usuario;
import com.security.sistencurity.infra.exceptions.ErroAoGerarTokenException;
import com.security.sistencurity.infra.exceptions.TokenInvalidoException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    Algorithm algoritimo = Algorithm.HMAC256(secret);
    public String gerarToken(@NotNull Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ApiSistencurity")
                    .withSubject(usuario.getNomeMorador())
                    .withClaim("id: ",usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new ErroAoGerarTokenException("Erro ao gerar TokenJWT");
        }
    }
    public String getSubject(String tokenJWT) {
        try {

            return JWT.require(algoritimo)
                    .withIssuer("ApiSistencurity")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new TokenInvalidoException("TokenJWT invalido ou expirado");
        }
    }
    public String gerarRefreshToken() {
        return UUID.randomUUID().toString();
    }

    private Instant dataExpiracao() {
        return Instant.now().plus(15, ChronoUnit.MINUTES);
    }
}
