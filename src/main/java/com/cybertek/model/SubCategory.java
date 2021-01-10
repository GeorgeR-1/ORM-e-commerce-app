package com.cybertek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@Table(name = "sub_categories", uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "category_id"})})
public class SubCategory extends BaseEntity<Integer>{

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

}
