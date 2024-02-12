package mx.edu.utez.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.edu.utez.library.models.genre.Genre;
import mx.edu.utez.library.models.genre.GenreRepository;
import mx.edu.utez.library.utils.CustomResponse;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;

    public CustomResponse<List<Genre>> getAll(){
        try{
            List<Genre> genres = repository.findAll();

            if(genres.isEmpty()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"NO HAY GENEROS REGISTRADOS");
            }
            return new CustomResponse<>(genres, false, HttpStatus.OK.value(),"LISTA DE GENEROS");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"INTERNAL SERVER ERROR");
        }
    }
}
