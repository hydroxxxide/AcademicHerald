package com.example.academicherald.entity;

import com.example.academicherald.enums.PublicationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "publications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String subtitle;

    @Column(columnDefinition = "text")
    private String text;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category")
    @JsonIgnore
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author")
    @JsonBackReference
    private User author;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PublicationType type;

    @ManyToMany
    @JoinTable(
            name = "publication_tags",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<Tag> tags;

    @OneToMany
    private List<Like> likes;

    private Boolean pass = false;

    private LocalDateTime rdt;

    @Column(columnDefinition = "text")
    private String preview; // ссылка к картинке

}
