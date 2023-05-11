package com.example.academicherald.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private LocalDate dateOfCreation;
    @ManyToMany
    @JoinTable(name="publications_comments",
            joinColumns=  @JoinColumn(name="publications_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="comment_id", referencedColumnName="id") )
    private List<Comment> commentList;



}
