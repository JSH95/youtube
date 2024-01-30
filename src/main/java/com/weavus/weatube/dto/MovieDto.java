package com.weavus.weatube.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class MovieDto {
    private Integer id;
    private String movieTitle;
    private String userId;
    private String filePath;
    private String fileUrl;
    private String category;
    private String movieStatus;
    private LocalDate uploadDate;
}