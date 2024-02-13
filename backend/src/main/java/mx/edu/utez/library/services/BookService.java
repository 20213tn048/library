package mx.edu.utez.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.edu.utez.library.controllers.book.BookDTO;
import mx.edu.utez.library.models.book.Book;
import mx.edu.utez.library.models.genre.Genre;
import mx.edu.utez.library.models.book.BookRepository;
import mx.edu.utez.library.models.genre.GenreRepository;
import mx.edu.utez.library.utils.CustomResponse;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    public CustomResponse<List<Book>> getAll() {
        try {
            List<Book> books = bookRepository.findAll();

            if (books.isEmpty()) {
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(), "NO HAY REGISTROS ENCONTRADOS");
            }
            return new CustomResponse<>(books, false, HttpStatus.OK.value(), "LISTA DE LIBROS");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<Book> addNewBook(BookDTO bookDTO) {
        try {
            if (bookRepository.findByName(bookDTO.getName()).isPresent()) {
                return createErrorResponse(HttpStatus.CONFLICT.value(), "LIBRO REGISTRADO ANTERIORMENTE");
            }
            Optional<Genre> genre = genreRepository.findById(bookDTO.getFkGenreId());
            if (!genre.isPresent()) {
                return createErrorResponse(HttpStatus.NOT_FOUND.value(), "GÉNERO NO ENCONTRADO");
            }

            Book newBook = new Book();
            newBook.setName(bookDTO.getName());
            newBook.setAuthor(bookDTO.getAuthor());
            newBook.setFkGenreId(genre.get());
            newBook.setPublicationYear(bookDTO.getPublicationYear());

            Book savedBook = bookRepository.saveAndFlush(newBook);
            return createSuccessResponse(savedBook, HttpStatus.CREATED.value(), "LIBRO GUARDADO EXITOSAMENTE");
        } catch (Exception e) {
            return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }

    private CustomResponse<Book> createSuccessResponse(Book data, int status, String message) {
        return new CustomResponse<>(data, false, status, message);
    }

    private CustomResponse<Book> createErrorResponse(int status, String message) {
        return new CustomResponse<>(null, true, status, message);
    }

}
