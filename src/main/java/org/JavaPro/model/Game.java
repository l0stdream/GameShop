package org.JavaPro.model;

import org.JavaPro.enums.GameType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Game {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private short rating;
    private double price;
    private String description;
    private GameType type;
    private LocalDateTime creationDate;
}
