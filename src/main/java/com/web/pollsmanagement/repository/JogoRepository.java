package com.web.pollsmanagement.repository;

import com.web.pollsmanagement.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    @Query(value = "SELECT * FROM jogo where id BETWEEN 0 AND :tam", nativeQuery = true)
    List<Jogo> buscarJogos (@Param("tam") int tam);

}
