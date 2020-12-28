package com.cybertek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attributes")
public class Attribute extends BaseEntity<Integer>{

    private String name;

    @Column(columnDefinition = "text")
    private String description;

}
