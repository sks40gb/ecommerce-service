package com.ziletech.ecommerce.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "is_enable")
    private Boolean isEnable;


}
