package com.svn.test.spring.cassandra.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * TODO: javadoc
 */
@Configuration
@EnableCassandraRepositories(basePackages = "com.svn.test.spring.cassandra.persistence.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

  @Value("${cassandra.keyspace}")
  private String keyspace;

  @Value("${cassandra.port:9042}")
  private int port;

  protected String getKeyspaceName() {
    return keyspace;
  }

  @Override
  protected int getPort() {
    return port;
  }
}
