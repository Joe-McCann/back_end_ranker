package com.ranker.ranker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(String username, String email, String password) {
        // Check if username already exists
        return userRepository.findByUsername(username)
            .flatMap(_ ->  Mono.<User>error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists")))
            .switchIfEmpty(
                // Hash password and save the user
                Mono.defer(() -> {
                    String hashedPassword = hashPassword(password); // Implement this
                    User newUser = new User(username, email, hashedPassword);
                    return userRepository.save(newUser);
                })
            );
    }

    public Mono<User> loginUser(String username, String password){
        return userRepository.findByUsername(username)
        .filter(user -> BCrypt.checkpw(password, user.getPassword()))
        .switchIfEmpty(Mono.<User>error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }
    
    public Mono<User> getUser(String username){
        return userRepository.findByUsername(username)
        .switchIfEmpty(Mono.<User>error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }

    public Mono<User> updateUserList(String username, String listname, RankList list){
        return userRepository.findByUsername(username)
        .switchIfEmpty(Mono.<User>error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
        .flatMap(user -> {
            user.getLists().put(listname, list);
            return userRepository.save(user);
        });
    }

    private String hashPassword(String password) {
        // Use a secure hashing library like BCrypt
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }   
}
