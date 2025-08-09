package com.sna.project.phonephop.PhoneShop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kok")
public class Kok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    @Column(name = "color_name")
    private String name;
}
