package com.example.academicherald.models;

import com.example.academicherald.enums.PublicationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
    private String title;
    private String subtitle;
    private String text;
    private Timestamp dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private PublicationType type;

}
