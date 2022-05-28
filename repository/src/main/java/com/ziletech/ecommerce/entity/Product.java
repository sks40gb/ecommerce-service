package com.ziletech.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "short_desc", nullable = false)
    private String shortDesc;

    @Column(name = "long_desc")
    private String longDesc;

    @Lob
    @Column(name = "unit_type", nullable = false)
    private String unitType;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "mrp", nullable = false)
    private Double mrp;

    @Column(name="quantity",nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;

}