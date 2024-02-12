package mx.edu.utez.library.models.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{
    Optional<Book> findByName(String name);
}
