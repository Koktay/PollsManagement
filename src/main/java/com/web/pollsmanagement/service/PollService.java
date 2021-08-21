package com.web.pollsmanagement.service;

import com.web.pollsmanagement.model.Poll;
import com.web.pollsmanagement.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService{

    @Autowired
    public PollRepository repository;

    public void savePoll(Poll poll){
        repository.save(poll);
    }
}
