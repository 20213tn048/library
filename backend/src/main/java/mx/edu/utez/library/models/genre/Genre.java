package mx.edu.utez.library.models.genre;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table( name = "genres" ) @Data
public class Genre {
    @Id @GeneratedValue( strategy = GenerationType.AUTO ) @Column( name = "genre_id")
    private Long id;

    @Column( nullable = false )
    private String description;
}
