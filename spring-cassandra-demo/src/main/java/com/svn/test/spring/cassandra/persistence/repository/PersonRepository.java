package com.svn.test.spring.cassandra.persistence.repository;

import com.svn.test.spring.cassandra.model.PersonEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO: javadoc
 */
@Repository
public interface PersonRepository extends PersonCustomRepository, CassandraRepository<PersonEntity> {

}
