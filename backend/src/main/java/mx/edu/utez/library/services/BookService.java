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
    public CustomResponse<Book> getBook(Long id){
        try{
            Optional<Book> findBook = bookRepository.findById(id);

            if(findBook.isPresent()){
                return new CustomResponse<>(findBook.get(),false,HttpStatus.OK.value(),"LIBRO ENCONTRADO");
            }
            return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"LIBRO NO ENCONTRADO");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<Book> addNewBook(BookDTO bookDTO) {
        try {
            if (bookRepository.findByName(bookDTO.getName()).isPresent()) {
                return new CustomResponse<>(null,true,HttpStatus.CONFLICT.value(), "LIBRO REGISTRADO ANTERIORMENTE");
            }
            Optional<Genre> genre = genreRepository.findById(bookDTO.getFkGenreId());
            if (!genre.isPresent()) {
                return new CustomResponse<>(null, true,HttpStatus.NOT_FOUND.value(), "GÉNERO NO ENCONTRADO");
            }

            Book newBook = new Book();
            newBook.setName(bookDTO.getName());
            newBook.setAuthor(bookDTO.getAuthor());
            newBook.setFkGenreId(genre.get());
            newBook.setPublicationYear(bookDTO.getPublicationYear());

            Book savedBook = bookRepository.saveAndFlush(newBook);
            return new CustomResponse<>(savedBook, false, HttpStatus.CREATED.value(), "LIBRO GUARDADO EXITOSAMENTE");
        } catch (Exception e) {
            return new CustomResponse<>(null, true,HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<Book> updateBook(Long id, BookDTO bookDTO) {
        try {
            Optional<Book> existBookOptional = bookRepository.findById(id);
    
            if (!existBookOptional.isPresent()) {
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(), "LIBRO NO ENCONTRADO");
            }
    
            Book existBook = existBookOptional.get();
    
            Optional<Genre> genreOptional = genreRepository.findById(bookDTO.getFkGenreId());
    
            if (!genreOptional.isPresent()) {
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(), "GÉNERO NO ENCONTRADO");
            }
    
            Genre genre = genreOptional.get();
    
            // Actualizando los campos del libro existente con los nuevos valores
            existBook.setName(bookDTO.getName());
            existBook.setAuthor(bookDTO.getAuthor());
            existBook.setPublicationYear(bookDTO.getPublicationYear());
            existBook.setFkGenreId(genre);
    
            // Guardando y devolviendo el libro actualizado
            Book updatedBook = bookRepository.saveAndFlush(existBook);
            return new CustomResponse<>(updatedBook, false, HttpStatus.OK.value(), "LIBRO ACTUALIZADO EXITOSAMENTE");
    
        } catch (Exception e) {
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }
    

    @SuppressWarnings("null")
    public CustomResponse<String> deleteBook(Long id){
        try{
            Optional<Book> findBook = bookRepository.findById(id);
            if(findBook.isPresent()){
                bookRepository.deleteById(id);
                return new CustomResponse<>(null, false, HttpStatus.OK.value(), "REGISTRO ELIMINADO CORRECTAMENTE");
            }
            return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"LIBRO NO ENCONTRADO");
        }catch(Exception e){
            return new CustomResponse<>(null, true,HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
        }
    }

}
