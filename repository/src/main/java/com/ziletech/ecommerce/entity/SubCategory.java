package com.ziletech.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubCategory extends BaseCategory {

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

}
