package com.post.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
