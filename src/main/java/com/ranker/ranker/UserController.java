package com.ranker.ranker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend origin
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String getMethodName() {
        return new String("Health Ok");
    }

    @GetMapping("/users")
    public Mono<User> getUser(@RequestParam String username) {
        return userService.getUser(username)
        .map(savedUser -> {
            savedUser.setPassword(null); // Ensure password is excluded
            return savedUser;
        });
    }

    @PostMapping("/users")
    public Mono<Map<String, String>> createUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getEmail(), user.getPassword())
        .then(Mono.just(Map.of("status", "success")));
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> loginUser(@RequestBody LoginRequest req) {
        return userService.loginUser(req.getUsername(), req.getPassword())
        .then(Mono.just(Map.of("status", "success")));
    }
    

    @PutMapping("/{username}/lists/{listName}")
    public Mono<Map<String, String>> updateList(
            @PathVariable String username,
            @PathVariable String listName,
            @RequestBody RankList updatedList) {
        return userService.updateUserList(username, listName, updatedList)
        .then(Mono.just(Map.of("status", "success")));
    }
    

}
