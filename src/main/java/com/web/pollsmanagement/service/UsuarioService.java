package com.web.pollsmanagement.service;

import com.web.pollsmanagement.model.Usuario;
import com.web.pollsmanagement.repository.UsuariosRepository;
import com.web.pollsmanagement.service.core.Abstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends Abstract {

    @Autowired
    public UsuariosRepository repository;

    public Usuario findUser(){
        String nome = getUserLogged().getName();
        return repository.findUsuarioByNomeContaining(nome);
    }

}
