package com.weavus.weatube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieTitle;
    private String userId;
    private String filePath;
    private String fileUrl;
    private String category;
    private String movieStatus;
    @CreationTimestamp
    @Column(name = "uploadDate")
    private LocalDate uploadDate;

}
