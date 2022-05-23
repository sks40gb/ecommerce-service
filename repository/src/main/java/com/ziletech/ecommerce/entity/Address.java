package com.ziletech.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apartment;
    private String street;
    private String city;
    private String state;
    @Column(name = "pin_code")
    private Long pinCode;
    @ManyToOne
    @JoinColumn(
            name = "user_id", referencedColumnName = "id")
    private User user;
}
