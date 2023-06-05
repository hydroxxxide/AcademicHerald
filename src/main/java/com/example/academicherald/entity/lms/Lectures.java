package com.example.academicherald.entity.lms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lectures")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Chapter chapter;
    private String title;
    @OneToOne
    private Material material;
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
        if (chapter != null) {
            chapter.getLectures().add(this); // Добавляем текущую лекцию в список лекций главы
        }
    }
}
