package com.example.springbootexperiment02.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
    insertable = false
    ,updatable = false)
    private LocalDateTime inserttime;
    public User(int id){
        this.id = id;
    }
    public User(String name){
        this.name = name;
    }
}
