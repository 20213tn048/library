package mx.edu.utez.library.controllers.book;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookDTO {
    private Integer id;
    
    private String name;
    private String author;
    private String publicationYear;
    private Long fkGenreId;
}
