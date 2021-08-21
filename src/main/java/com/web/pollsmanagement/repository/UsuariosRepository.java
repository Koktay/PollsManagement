package com.web.pollsmanagement.repository;

import com.web.pollsmanagement.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByNomeContaining(String nome);

}
