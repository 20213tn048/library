package mx.edu.utez.library.models.book;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.library.models.genre.Genre;

@Entity @Table ( name = "books" ) @Data
public class Book {
    @Id @GeneratedValue ( strategy = GenerationType.AUTO) @Column( name = "book_id")
    private Long id;

    @Column( nullable = false )
    private String name;

    @Column( nullable = false )
    private String author;

    @ManyToOne
    @JoinColumn( name = "fk_genre_id")
    private Genre fkGenreId;

    @Column( nullable = false, name = "publication_year")
    private String publicationYear;
}
