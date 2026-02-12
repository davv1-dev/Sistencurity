package com.security.sistencurity.domain.auth;

import com.security.sistencurity.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    Optional<RefreshToken> findByToken(@NotBlank String refreshtoken);

    void deleteByUsuario(Usuario usuario);
}
