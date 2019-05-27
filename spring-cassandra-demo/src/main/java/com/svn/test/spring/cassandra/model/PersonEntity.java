package com.svn.test.spring.cassandra.model;

import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

import lombok.Data;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * TODO: javadoc
 */
@Data
@Table("person")
public class PersonEntity {

  @PrimaryKeyColumn(value = "personCode", type = PARTITIONED)
  private String personCode;

  @Column("name")
  private String name;
}
