package com.example.hexagonal.user.infrastructure.adapter.in;

import com.example.hexagonal.user.application.command.LoginUserCommand;
import com.example.hexagonal.user.application.service.UserCommandService;
import com.example.hexagonal.user.infrastructure.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserCommandService userCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserCommandService userCommandService, JwtTokenProvider jwtTokenProvider) {
        this.userCommandService = userCommandService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Endpoint para iniciar sesión y generar un token JWT.
     *
     * @param loginUserCommand Los datos de inicio de sesión, como username y password.
     * @return El token JWT generado.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserCommand loginUserCommand) {
        String token = userCommandService.login(
                new LoginUserCommand(loginUserCommand.username(), loginUserCommand.password()));
        return ResponseEntity.ok(token);
    }

    /**
     * Endpoint para validar un token JWT.
     *
     * @param token El token JWT que se va a validar.
     * @return Respuesta que indica si el token es válido.
     */
    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            return ResponseEntity.ok("Token is valid. Username: " + username);
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
