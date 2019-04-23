package com.example.springbootexperiment02.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String detail;
    @ManyToOne
    private User user;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_STAMP",
    insertable = false
    ,updatable = false)
    private LocalDateTime inserTime;
    public Address(String detail){
        this.detail = detail;
        
    }

}
