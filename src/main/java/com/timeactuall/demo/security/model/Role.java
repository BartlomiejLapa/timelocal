package com.timeactuall.demo.security.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
//
//    public Role(String name) {
//        this.name = name;
//    }
}
