package com.example.academicherald.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
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
    @JoinColumn(name = "author")
    private User author;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfEvent;

    @Column(columnDefinition = "time")
    private Timestamp startTime;

    private String location;
    @Column(columnDefinition = "interval")
    private Double duration;

    private LocalDateTime rdt;

    @Column(columnDefinition = "text")
    private String preview; // ссылка к картинке
}
