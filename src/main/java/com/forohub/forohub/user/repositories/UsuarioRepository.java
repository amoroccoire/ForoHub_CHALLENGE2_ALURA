package com.forohub.forohub.user.repositories;

import com.forohub.forohub.user.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
    UserDetails findByCorreo(String correo);
}
