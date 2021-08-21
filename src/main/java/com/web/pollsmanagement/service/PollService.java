package com.web.pollsmanagement.service;

import com.web.pollsmanagement.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService{

    @Autowired
    public PollRepository repository;
}
