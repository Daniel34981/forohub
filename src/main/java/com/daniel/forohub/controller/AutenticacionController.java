package com.daniel.forohub.controller;

import com.daniel.forohub.infra.security.DatosJWTToken;
import com.daniel.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DatosJWTToken autenticar(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.correoElectronico(),
                datos.contrasena()
        );

        Authentication autenticacion = authenticationManager.authenticate(authToken);

        String token = tokenService.generarToken(autenticacion);

        return new DatosJWTToken(token);
    }
}