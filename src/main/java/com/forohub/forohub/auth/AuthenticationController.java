package com.forohub.forohub.auth;

import com.forohub.forohub.user.entities.Usuario;
import com.forohub.forohub.user.entities.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        System.out.println("LLEGO");
        System.out.println(usuarioDTO.correo() + " ---------- " + usuarioDTO.contrasena());
        Authentication token = new UsernamePasswordAuthenticationToken(usuarioDTO.correo(), usuarioDTO.contrasena());
        System.out.println("TERMINA");
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(jwtToken));
    }
}
