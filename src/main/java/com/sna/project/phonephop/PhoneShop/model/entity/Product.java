package com.sna.project.phonephop.PhoneShop.model.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
//For every row in the products table, the combination of model_id and color_id must be unique.
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

}