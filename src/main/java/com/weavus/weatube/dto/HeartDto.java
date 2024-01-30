package com.weavus.weatube.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class HeartDto {
    private String heartId;
    private Integer movieId;
    public HeartDto(Integer movieId) {
        this.movieId = movieId;
    }
}
