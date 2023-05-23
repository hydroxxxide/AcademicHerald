package com.example.academicherald.models.lms;

import com.example.academicherald.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HandingInHomework")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HandingInHomework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private User studentId;
    private String linkGitHubHw;
}
