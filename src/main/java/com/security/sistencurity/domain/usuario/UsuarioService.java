package com.security.sistencurity.domain.usuario;

import com.security.sistencurity.domain.auth.RefreshDTO;
import com.security.sistencurity.domain.auth.RefreshToken;
import com.security.sistencurity.domain.auth.RefreshTokenRepository;
import com.security.sistencurity.infra.exceptions.RefreshTokenExpiradoException;
import com.security.sistencurity.infra.exceptions.RefreshTokenNaoExisteException;
import com.security.sistencurity.infra.security.TokenResponseDTO;
import com.security.sistencurity.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
@Transactional
    public Long cadastrarUsuarioMorador(@NotBlank String nome,  @NotBlank String senha,@NotBlank Perfil perfil) {
        Usuario usuarioNovo = new Usuario(nome,senha,perfil);
        repository.save(usuarioNovo);
        return usuarioNovo.getId();
    }
@Transactional
    public TokenResponseDTO efetuarLogin(@Valid UsuarioDTOEntrada dados,Authentication authentication) {
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(usuarioAutenticado);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(tokenService.gerarRefreshToken());
        refreshToken.setUsuario(usuarioAutenticado);
        refreshToken.setDataExpiracão(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshTokenRepository.save(refreshToken);
        return new TokenResponseDTO(tokenJWT,refreshToken.getToken());
    }
@Transactional
    public TokenResponseDTO gerarNovoToken(@Valid RefreshDTO refreshDTO) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshDTO.refreshtoken()).orElseThrow(()-> new RefreshTokenNaoExisteException("O token Informado não Existe."));
        if(refreshToken.getDataExpiracão().isBefore(Instant.now())){
            throw new RefreshTokenExpiradoException("RefreshToken expirado");
        }
        Usuario usuario = refreshToken.getUsuario();
        var novoTokenJWT = tokenService.gerarToken(usuario);
        return new TokenResponseDTO(novoTokenJWT, refreshToken.getToken());
    }

    public void fazerLogout(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        refreshTokenRepository.deleteByUsuario(usuario);
    }
}
