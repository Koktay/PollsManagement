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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.*;

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
    private List<Poll> polls = new ArrayList<>();

    @Getter
    @Setter
    private Poll selectedPoll;

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
    private Long id;

    @Getter
    @Setter
    private List<Poll> historico = new ArrayList<>();

    @Getter
    @Setter
    private Poll selectedHistory;


    @PostConstruct
    public void init() throws UnirestException {
        games = IGDBInterface.buscar();
        keys.addAll(games.keySet());
        user = userService.findUser();
        polls = pollRepository.findAll();
        jogos = jogoRepository.buscarJogos(games.size());
        if (jogos.size() < 1) {
            for (String mp : games.values()) {
                Jogo jogo = new Jogo();
                jogo.setNome(mp);
                jogoRepository.save(jogo);
            }
            jogos = jogoRepository.buscarJogos(games.size());
        }
        historico = pollRepository.historico(user.getId());
    }

    public void votar() throws Exception {
        if (!Assert.isNotNullOrEmpty(jogoVotado)) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha um filme" +
                            ".",
                            ""));
        } else {
            pollService.votar(id, jogoVotado);
        }
    }

    public void criar() {

        try {

            Set<String> titulos = new HashSet<>();
            polls.forEach(p -> titulos.add(p.getTitulo()));

            if (categoria.isEmpty()) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha o campo!", ""));
            } else if (titulos.contains(categoria)) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Categoria existente!", ""));
            } else {

                Poll poll = new Poll();
                poll.setTitulo(categoria);

                List<Jogo> jogoList = new ArrayList<>(jogos);
                if (polls.size() > 0) {
                    jogoList.forEach(j -> j.setId(null));
                }
                poll.setJogos(jogoList);

                pollService.savePoll(poll, user);


                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poll criado com sucesso!", ""));

                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("criar");

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar Poll" +
                            ".",
                            ""));
        }
    }

    public void votarSelected() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("votar?categoria=" + selectedPoll.getTitulo()
                + "&id=" + selectedPoll.getId());
    }

    public void delPoll() {

        try {
            if(selectedHistory != null){
                pollRepository.delete(selectedHistory);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poll eliminada com sucesso!", ""));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("historico");
            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione a linha antes de eliminar!", ""));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao eliminar!", ""));
            e.printStackTrace();
        }
    }

}
