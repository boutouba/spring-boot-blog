package com.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    //@ManyToMany(mappedBy="roles",cascade=CascadeType.MERGE)
    @ManyToMany(mappedBy="roles",cascade=CascadeType.ALL)
    private List<User> users;
}
