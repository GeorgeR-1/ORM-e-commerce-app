package com.cybertek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "units_of_measure",uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Uom extends BaseEntity<Integer>{

    private String name;

    private String description;

}
