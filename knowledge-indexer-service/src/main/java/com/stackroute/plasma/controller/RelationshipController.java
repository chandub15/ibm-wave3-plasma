package com.stackroute.plasma.controller;

import com.stackroute.plasma.model.Relationship;
import com.stackroute.plasma.service.RelationshipService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Api(description="Creates relationship between the description node and concept node of domain ontology")
public class RelationshipController {

    @Autowired
    RelationshipService relationshipService;

    @GetMapping("/create")
    public Relationship create(){
        return relationshipService.create("Erlang",90,"basics");
    }

    @DeleteMapping("/delete")
    public Relationship delete(){
        return relationshipService.delete("Erlang");
    }

}
