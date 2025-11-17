package com.jcaa.hexagonal.entrypoint.rest.v1.auth;

import com.jcaa.hexagonal.adapter.rest.config.SessionUserFilter;
import com.jcaa.hexagonal.core.port.in.AuthUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.auth.dto.LoginRequest;
import com.jcaa.hexagonal.entrypoint.rest.v1.auth.dto.LoginResponse;
import com.jcaa.hexagonal.entrypoint.rest.v1.auth.dto.RememberPasswordResponse;
import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                               HttpServletRequest servletRequest) {
        AuthenticatedUser authUser = authUseCase.login(request.username(), request.password());
        HttpSession session = servletRequest.getSession(true);
        session.setAttribute(SessionUserFilter.SESSION_ATTRIBUTE, authUser);
        SecurityContextHolder.getContext().setAuthentication(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        authUser, null, authUser.getAuthorities()
                )
        );
        return ResponseEntity.ok(new LoginResponse(
                authUser.getUsuarioId(),
                authUser.getUsername(),
                authUser.getRol().name(),
                authUser.getClienteId(),
                authUser.getPilotoId()
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponse> currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthenticatedUser authUser) {
            return ResponseEntity.ok(new LoginResponse(
                    authUser.getUsuarioId(),
                    authUser.getUsername(),
                    authUser.getRol().name(),
                    authUser.getClienteId(),
                    authUser.getPilotoId()
            ));
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

    /**
     * “Recordar contraseña” devuelve la clave tal cual se almacena.
     * Se expone sin autenticación según la consigna.
     */
    @GetMapping("/remember")
    public ResponseEntity<RememberPasswordResponse> rememberPassword(
            @RequestParam(name = "username") String username) {
        String password = authUseCase.recordarClave(username);
        return ResponseEntity.ok(new RememberPasswordResponse(username, password));
    }
}