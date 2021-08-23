package com.web.pollsmanagement.repository;

import com.web.pollsmanagement.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {

    @Query(value = "SELECT * FROM poll WHERE usuario_id = :id", nativeQuery = true)
    List<Poll> historico(@Param("id") Long id);
}
