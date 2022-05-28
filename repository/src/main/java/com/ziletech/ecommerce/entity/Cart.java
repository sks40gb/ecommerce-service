package com.ziletech.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Cart {
    @Id
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
