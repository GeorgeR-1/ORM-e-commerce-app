package com.cybertek.model;

import com.cybertek.enums.ProductAndUserStatus;
import com.cybertek.enums.ProductCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity<Long>{

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @DecimalMin("0.00")
    private BigDecimal price;

    private BigDecimal volume;
    private BigDecimal weight;

    @Positive
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductAndUserStatus status;

    @Enumerated(EnumType.STRING)
    private ProductCondition condition;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id")
    private Uom uom;

    @ManyToMany
    @JoinTable(name = "product_sub_category_rel",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_category_id"))
    private List<SubCategory> subCategories;

    @ManyToMany
    @JoinTable(name = "product_attribute_value_rel",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "attribute_value_id"))
    private List<AttributeValue> attributeValue;



}
