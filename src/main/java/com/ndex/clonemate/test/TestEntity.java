package com.ndex.clonemate.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "test_entity")
@Entity
public class TestEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private String data;
}