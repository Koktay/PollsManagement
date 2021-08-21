package com.web.pollsmanagement.repository;

import com.web.pollsmanagement.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
