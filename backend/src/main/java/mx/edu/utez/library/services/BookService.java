package mx.edu.utez.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.edu.utez.library.models.book.Book;
import mx.edu.utez.library.models.book.BookRepository;
import mx.edu.utez.library.models.genre.GenreRepository;
import mx.edu.utez.library.utils.CustomResponse;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private GenreRepository genreRepository;

    public CustomResponse<List<Book>> getAll(){
        try{
            List<Book> books = bookRepository.findAll();

            if(books.isEmpty()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"NO HAY REGISTROS ENCONTRADOS");
            }
            return new CustomResponse<>(books, false, HttpStatus.OK.value(), "LISTA DE LIBROS");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"INTERNAL SERVER ERROR");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<Book> addNewBook(Book book){
        try{

            if(this.bookRepository.findByName(book.getName()).isPresent()){
                return new CustomResponse<>(null, true, HttpStatus.CONFLICT.value(),"LIBRO REGISTRADO ANTERIORMENTE");
            }

            if(!genreRepository.existsById(book.getFkGenreId().getId())){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"GÉNERO NO ENCONTRADO");
            }
            return new CustomResponse<>(this.bookRepository.saveAndFlush(book),false, HttpStatus.CREATED.value(),"LIBRO GUARDADO EXITOSAMENTE");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"INTERNAL SERVER ERROR");
        }
    }
}
