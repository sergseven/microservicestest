package com.svn.test.spring.cassandra.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import com.svn.test.spring.cassandra.ApplicationConfig;
import com.svn.test.spring.cassandra.model.PersonEntity;
import com.svn.test.spring.cassandra.persistence.CassandraConfig;
import com.svn.test.spring.cassandra.persistence.repository.PersonRepository;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * TODO: javadoc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CassandraConfig.class, ApplicationConfig.class})
@TestPropertySource(locations = "classpath:cassandra/cassandra.properties")
@EmbeddedCassandra(timeout = 20000L)
@TestExecutionListeners(
    listeners = {
        CassandraUnitDependencyInjectionTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
    },
    mergeMode = MERGE_WITH_DEFAULTS
)
@CassandraDataSet(value = "cassandra/ddl/person_table_creation.cql", keyspace = "test")
public class PersonDaoTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonDao underTest;

  @Test
  public void testSave() {
    PersonEntity person = new PersonEntity();
    person.setPersonCode("abc123");
    person.setName("serg");

    PersonEntity saved = underTest.save(person);

    assertNotNull(saved);
    PersonEntity found = personRepository.findOne(BasicMapId.id("personCode", "abc123"));
    assertEquals("serg", found.getName());
  }

}
