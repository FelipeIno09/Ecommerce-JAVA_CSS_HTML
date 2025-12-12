package com.spring.Ecommerce.repository;

import com.spring.Ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByEmail(String email);
}
