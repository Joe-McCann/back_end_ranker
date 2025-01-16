package com.ranker.ranker;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    
    Mono<User> findByUsername(String username);

    @Query("{'username': ?0, 'lists.?1': {$exists: true}}")
    Mono<RankList> findListByUsernameAndListName(String username, String listName);

}
