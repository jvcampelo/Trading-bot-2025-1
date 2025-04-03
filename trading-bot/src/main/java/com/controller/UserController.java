package com.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.model.UserConfiguration;
import com.model.UserTrackingTicker;
import com.repository.UserConfigurationRepository;
import com.repository.UserRepository;
import com.repository.UserTrackingTickerRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfigurationRepository userConfigurationRepository;

    @Autowired
    private UserTrackingTickerRepository userTrackingTickerRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        this.userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/configuration")
public ResponseEntity<User> associteConfiguration(@PathVariable("id") Integer id, @RequestBody UserConfiguration configuration) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();
    
    // Garante que a lista não seja nula
    if (user.getConfigurations() == null) {
        user.setConfigurations(new ArrayList<>());
    }

    // Associa e salva
    configuration.setUser(user);
    user.getConfigurations().add(configuration);
    userConfigurationRepository.save(configuration);
    userRepository.save(user);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
}

@PostMapping("{id}/tracking-ticker")
public ResponseEntity<User> associateTicker(@PathVariable("id") Integer id, @RequestBody UserTrackingTicker ticker) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();

    // Garante que a lista não seja nula
    if (user.getTrackingTickers() == null) {
        user.setTrackingTickers(new ArrayList<>());
    }

    // Associa e salva
    ticker.setUser(user);
    user.getTrackingTickers().add(ticker);
    userTrackingTickerRepository.save(ticker);
    userRepository.save(user);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
}
}