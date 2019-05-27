package com.svn.test.spring.cassandra.persistence.dao;

import com.svn.test.spring.cassandra.model.PersonEntity;
import com.svn.test.spring.cassandra.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO: javadoc
 */
@Component
public class PersonDao {

  @Autowired
  private PersonRepository personRepository;

  public PersonEntity save(PersonEntity person) {
    return personRepository.save(person);
  }

}
