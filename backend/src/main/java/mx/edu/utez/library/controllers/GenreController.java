package mx.edu.utez.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.library.services.GenreService;
import mx.edu.utez.library.utils.CustomResponse;
import mx.edu.utez.library.models.genre.Genre;
import org.springframework.web.bind.annotation.GetMapping;


@RestController @RequestMapping("/genres") @CrossOrigin( origins = {"*"})
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping("/")
    public CustomResponse<List<Genre>> getAll(){
        return service.getAll();
    }
    
}
