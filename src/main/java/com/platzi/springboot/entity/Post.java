package com.platzi.springboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity para que no se siga el contador de otros registros
    @Column(name = "id_post", nullable = false, unique = true)
    private Long id;

    @Column(name = "description", length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id") //No necesario para el ejemplo del curso pero se puede explicar
    @JsonBackReference
    private User user;

    public Post(){}

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }
}
