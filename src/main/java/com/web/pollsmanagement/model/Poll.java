package com.web.pollsmanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Poll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "poll")
    private List<Jogo> jogos;

    @ManyToOne
    private Usuario usuario;

    private String restricao;

    ArrayList<String> votantes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRestricao() {
        return restricao;
    }

    public void setRestricao(String restricao) {
        this.restricao = restricao;
    }

    public ArrayList<String> getVotantes() {
        return votantes;
    }

    public void setVotantes(ArrayList<String> votantes) {
        this.votantes = votantes;
    }
}
