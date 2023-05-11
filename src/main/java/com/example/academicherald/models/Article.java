package com.example.academicherald.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article extends Publications {

    public Article(Long id, User user, String title, LocalDate dateOfCreation, List<Comment> commentList){
        super(id, user, title, dateOfCreation, commentList);
    }

}
