package com.cybertek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "currencies",uniqueConstraints = {@UniqueConstraint(columnNames = {"name","symbol"})})
public class Currency extends BaseEntity<Integer>{

    private String name;

    private String symbol;

}
