package mx.edu.utez.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.library.models.book.Book;
import mx.edu.utez.library.services.BookService;
import mx.edu.utez.library.utils.CustomResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController @RequestMapping( "/books" ) @CrossOrigin( origins = { "*" } )
public class BookController {
    
    @Autowired
    private BookService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Book>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Book>> addNewBook(@RequestBody Book book){
        return new ResponseEntity<>(this.service.addNewBook(book),HttpStatus.OK);
    }
    
}
