package com.stackroute.plasma.controller;


import com.stackroute.plasma.model.InputQuery;
import com.stackroute.plasma.model.NlpModel;
import com.stackroute.plasma.model.UserQuery;
import com.stackroute.plasma.service.NlpService;
import com.stackroute.plasma.service.RabbitMQSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class NlpController {
    Logger logger = LoggerFactory.getLogger(NlpController.class.getName());

    @Autowired
    NlpService nlpService;
    @Autowired
    RabbitMQSender rabbitMQSender;

    NlpModel nlpModel = new NlpModel();
    List<String> temp;
    UserQuery userQuery ;
    int i=0;
@PostMapping("/query")
public ResponseEntity<?> extractedQuery(@RequestBody InputQuery inputQuery) {
    temp = new ArrayList<>();
    userQuery = new UserQuery();
    userQuery.setUser_id(inputQuery.getUser_id());
    userQuery.setUser_query(inputQuery.getUser_query());
    userQuery.setJwt(inputQuery.getJwt());
    userQuery.setRole(inputQuery.getRole());
    logger.info(userQuery.getUser_query());

    nlpService.save(userQuery);
    temp = nlpService.queryConverter(inputQuery.getUser_query());
    nlpModel.setTokenized_lematized(temp);
    nlpModel.setUserId(inputQuery.getUser_id());
    nlpModel.setJwt(inputQuery.getJwt());
    nlpModel.setRole(inputQuery.getRole());
    logger.info("controller output" + temp);
    rabbitMQSender.sender(nlpModel);
    return new ResponseEntity<>(temp.stream().map(String::toString).collect(Collectors.toList()), HttpStatus.CREATED);

  }


//    @GetMapping("/query")
//    public ResponseEntity<?> extractedQuery() {
//        InputQuery inputQuery = new InputQuery();
//        temp = new ArrayList<>();
//        userQuery = new UserQuery();
//        userQuery.setUser_id(inputQuery.getUser_id());
//        userQuery.setUser_query(inputQuery.getUser_query());
//        userQuery.setJwt(inputQuery.getJwt());
//        userQuery.setRole(inputQuery.getRole());
//        logger.info(userQuery.getUser_query());
//
//        nlpService.save(userQuery);
//        temp = nlpService.queryConverter(inputQuery.getUser_query());
//        nlpModel.setTokenized_lematized(temp);
//        logger.info("controller output" + temp);
//        //rabbitMQSender.sender(nlpModel);
//        return new ResponseEntity<>(temp.stream().map(String::toString).collect(Collectors.toList()), HttpStatus.CREATED);
//    }
}
