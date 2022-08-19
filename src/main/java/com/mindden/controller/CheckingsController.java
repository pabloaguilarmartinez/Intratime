package com.mindden.controller;

import com.mindden.model.CheckingInfo;
import com.mindden.service.CheckingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

import static com.mindden.constants.Constants.CHECKINGS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = CHECKINGS_URI, produces = APPLICATION_JSON_VALUE)
public class CheckingsController {

    private final CheckingsService checkingsService;

    @GetMapping
    public ResponseEntity<Collection<CheckingInfo>> findAll() {
        return ResponseEntity.ok(checkingsService.findAll());
    }

    @PostMapping
    public ResponseEntity<CheckingInfo> create(@RequestBody @Valid final CheckingInfo dto) {
        URI location = URI.create(CHECKINGS_URI);
        return ResponseEntity.created(location).body(checkingsService.create(dto));
    }

}
