package com.web.pollsmanagement.service;

import com.web.pollsmanagement.model.Jogo;
import com.web.pollsmanagement.model.Poll;
import com.web.pollsmanagement.model.Usuario;
import com.web.pollsmanagement.repository.JogoRepository;
import com.web.pollsmanagement.repository.PollRepository;
import com.web.pollsmanagement.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    public PollRepository pollRepository;

    @Autowired
    public UsuariosRepository userRepository;

    @Autowired
    public JogoRepository jogoRepository;

    @Transactional
    public Poll savePoll(Poll poll, Usuario user) {

        poll.setUsuario(user);
        Poll savedPoll = pollRepository.save(poll);
        poll.getJogos().forEach(jg -> {
            jg.setPoll(savedPoll);
            jogoRepository.save(jg);
        });

        return savedPoll;
    }

    @Transactional
    public void votar(Long id, String nomeJogo) throws Exception {
        Poll poll = pollRepository.getById(id);

        List<Jogo> jogos = poll.getJogos().stream().filter(jogo -> Objects.equals(jogo.getNome(), nomeJogo)).collect(Collectors.toList());
        if (jogos.size() == 1) {
            Jogo jogo = jogos.get(0);
            jogo.setVoto(jogo.getVoto() + 1);
            jogoRepository.save(jogo);
            pollRepository.save(poll);
        } else {
            throw new Exception("Nome do jogo não é único!");
        }
    }
}
