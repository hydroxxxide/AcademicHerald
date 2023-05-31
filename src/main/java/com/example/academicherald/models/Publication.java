package com.example.academicherald.models;

import com.example.academicherald.enums.PublicationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "publications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String subtitle;

    @NotNull
    private String text;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PublicationType type;


    private Boolean pass = false;

    private LocalDateTime rdt;

}
