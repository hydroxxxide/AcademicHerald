package com.example.academicherald.models.lms;

import com.example.academicherald.enums.CourseType;
import com.example.academicherald.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startCourse = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonManagedReference
    private User mentor;
    @OneToMany
    private List<User> students;
    @OneToMany
    @JsonIgnore
    private List<Chapter> chapters;



}
