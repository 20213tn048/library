package mx.edu.utez.library.controllers.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.library.models.book.Book;
import mx.edu.utez.library.services.BookService;
import mx.edu.utez.library.utils.CustomResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController @RequestMapping( "/books" ) @CrossOrigin( origins = { "*" } )
public class BookController {
    
    @Autowired
    private BookService service;

    @GetMapping("/")
    public CustomResponse<List<Book>> getAll(){
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public CustomResponse<Book> getBook(@PathVariable Long id){
        return this.service.getBook(id);
    }

    @PostMapping("/")
    public CustomResponse<Book> addNewBook(@RequestBody BookDTO bookDTO){
        return this.service.addNewBook(bookDTO);
    }

    @PutMapping("/{id}")
    public CustomResponse<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id){
        return this.service.updateBook(id,bookDTO);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteBook(@PathVariable Long id){
        return this.service.deleteBook(id);
    }
    
}
