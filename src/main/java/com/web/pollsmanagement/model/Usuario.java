package com.web.pollsmanagement.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    private Integer id;

    private String name;

    private Set<String> jogos;

    private String senha;

    private String auth;

    private Boolean enabled = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getJogos() {
        return jogos;
    }

    public void setJogos(Set<String> jogos) {
        this.jogos = jogos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
