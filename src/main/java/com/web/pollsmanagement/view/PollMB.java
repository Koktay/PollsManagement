package com.web.pollsmanagement.view;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.model.Jogo;
import com.web.pollsmanagement.model.Poll;
import com.web.pollsmanagement.model.Usuario;
import com.web.pollsmanagement.repository.IGDBInterface;
import com.web.pollsmanagement.repository.JogoRepository;
import com.web.pollsmanagement.repository.PollRepository;
import com.web.pollsmanagement.repository.UsuariosRepository;
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
import java.util.*;
import java.util.stream.Collectors;

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
    private Long id;

    @Autowired
    public UsuariosRepository usuariosRepository;

    @Getter
    @Setter
    private List<Jogo> selectGame = new ArrayList<>();


    @Getter
    @Setter
    private Poll pollCriada = new Poll();


    @PostConstruct
    public void init() throws UnirestException {
        games = IGDBInterface.buscar();
        keys.addAll(games.keySet());
        user = userService.findUser();
        polls = pollRepository.findAll();
        polls.forEach(a -> setSelectGame(getJogos()));
        jogos = jogoRepository.buscarJogos(games.size());
        if (jogos.size() < 1) {
            for (String mp : games.values()) {
                Jogo jogo = new Jogo();
                jogo.setNome(mp);
                jogoRepository.save(jogo);
            }
            jogos = jogoRepository.buscarJogos(games.size());
        }
    }

    public void votar() throws Exception {
//        poll.setJogos(jogos);
//        poll.setTitulo(categoria);
        if (!Assert.isNotNullOrEmpty(jogoVotado)) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha um filme" +
                            ".",
                            ""));
        } else {
//            if (votarBln.equals(true)) {
                pollService.votar(id, jogoVotado);
//            } else {
//            }
        }
    }

    public void criar() {

        try {
            Poll poll = new Poll();
            poll.setTitulo(categoria);

            List<Jogo> jogoList = new ArrayList<>(jogos);
            if(polls.size() > 0) {
                jogoList.forEach(j -> j.setId(null));
            }
            poll.setJogos(jogoList);

            pollCriada = pollService.savePoll(poll, user);

//            Jogo jogo = new Jogo();
//            jogo.setPoll(poll);
//
//            jogoRepository.save(jogo);

//            poll.setJogos(jogos);
//            jogos.forEach(j->j.setPoll(pollCriada));
//            jogoRepository.saveAll(jogos);

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poll criado com sucesso!", ""));
//            FacesContext.getCurrentInstance().addMessage("messages",
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Poll criado com sucesso!" +
//                            "",
//                            ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("criar");

        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar Poll" +
                            ".",
                            ""));
        }
    }

    public void votarSelected() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("votar?categoria=" + selectedPoll.getTitulo());
    }

    public void onRowSelect(SelectEvent event) throws IOException {
        Poll poll = (Poll) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("votar?id=" + poll.getId() + "&categoria=" + poll.getTitulo());
    }

}
