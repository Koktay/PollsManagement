package com.web.pollsmanagement.view;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.model.Jogo;
import com.web.pollsmanagement.model.Poll;
import com.web.pollsmanagement.model.Usuario;
import com.web.pollsmanagement.repository.IGDBInterface;
import com.web.pollsmanagement.repository.JogoRepository;
import com.web.pollsmanagement.repository.PollRepository;
import com.web.pollsmanagement.service.PollService;
import com.web.pollsmanagement.service.UsuarioService;
import com.web.pollsmanagement.util.Assert;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("view")
public class PollMB {

    @Getter
    @Setter
    @Autowired
    private IGDBInterface IGDBInterface;

    @Getter
    @Setter
    private Map<Integer, String> games;

    @Getter
    @Setter
    private List<Integer> keys = new ArrayList<>();

    @Getter
    @Setter
    private String categoria;

    @Getter
    @Setter
    private Poll poll = new Poll();

    @Getter
    @Setter
    private List<Poll> polls = new ArrayList<>();

    @Getter
    @Setter
    private Poll selectedPoll = new Poll();

    @Autowired
    public PollService pollService;

    @Autowired
    public UsuarioService userService;

    @Getter
    @Setter
    private Usuario user = new Usuario();

    @Autowired
    public JogoRepository jogoRepository;

    @Autowired
    public PollRepository pollRepository;

    @Getter
    @Setter
    private List<Jogo> jogos = new ArrayList<>();

    @Getter
    @Setter
    private String jogoVotado;

    @Getter
    @Setter
    private List<String> categorias = new ArrayList<>();

    @Getter
    @Setter
    private Boolean votarBln = true;

    @Getter
    @Setter
    private Long id;

    @PostConstruct
    public void init() throws UnirestException {
        games = IGDBInterface.buscar();
        keys.addAll(games.keySet());
        user = userService.findUser();
        polls = pollRepository.findAll();
        jogos = jogoRepository.findAll();
        if (jogos.size() < 1) {
            for (String mp : games.values()) {
                Jogo jogo = new Jogo();
                jogo.setNome(mp);
                jogoRepository.save(jogo);
            }
        }
    }

    public void votar() throws Exception {
        poll.setJogos(jogos);
        poll.setTitulo(categoria);
        if (!Assert.isNotNullOrEmpty(jogoVotado)) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha um filme" +
                            ".",
                            ""));
        } else {
            if (votarBln.equals(true)) {
                poll = pollService.savePoll(poll, user);
                pollService.votar(poll.getId(), jogoVotado);
            } else {
                pollService.votar(id, jogoVotado);
            }
        }
    }

    public void criar() throws IOException {
        polls.forEach(a -> categorias.add(a.getTitulo()));

        if (!Assert.isEmpty(polls)) {
            if (categorias.contains(categoria)) {
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria existente!" +
                                "",
                                ""));
            } else
                FacesContext.getCurrentInstance().getExternalContext().redirect("votar?categoria=" + categoria);
        } else
            FacesContext.getCurrentInstance().getExternalContext().redirect("votar?categoria=" + categoria);
    }

    public void votarSelected() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("votar?categoria=" + selectedPoll.getTitulo() + "&votarBln=" + false + "&id=" + selectedPoll.getId());
    }

    public void onRowSelect(SelectEvent event) throws IOException {
        Poll poll = (Poll) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("votar?id=" + poll.getId() + "&votarBln=" + false + "&categoria=" + poll.getTitulo());
    }

}
