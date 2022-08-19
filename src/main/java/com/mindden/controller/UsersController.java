package com.mindden.controller;

import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.mindden.constants.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = USERS_URI, produces = APPLICATION_JSON_VALUE)
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<Collection<UserInfo>> findUsers() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping(ID_URI + CHECKINGS_URI)
    public ResponseEntity<Collection<CheckingBasicInfo>> findCheckingsUserById(@PathVariable final Integer id) {
        return ResponseEntity.ok(usersService.findCheckingsUserById(id));
    }

    @GetMapping(ID_URI + REQUESTS_URI)
    public ResponseEntity<Collection<RequestBasicInfo>> findRequestsUserById(@PathVariable final Integer id) {
        return ResponseEntity.ok(usersService.findRequestsUserById(id));
    }
}
