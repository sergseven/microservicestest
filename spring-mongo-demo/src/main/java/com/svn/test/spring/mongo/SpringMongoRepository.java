package com.svn.test.spring.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongoRepository extends ReactiveMongoRepository<TestDocument, String> {

}
