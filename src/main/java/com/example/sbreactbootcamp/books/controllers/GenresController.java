package com.example.sbreactbootcamp.books.controllers;

import com.example.sbreactbootcamp.books.model.Genres;
import com.example.sbreactbootcamp.books.repositories.IGenresRepository;
import com.example.sbreactbootcamp.utils.response.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@Tag(name = "default")
@CrossOrigin("*")
public class GenresController {

    private static final Logger logger= LoggerFactory.getLogger(GenresController.class);

    @Autowired
    IGenresRepository genresRepository;

    @GetMapping("")
    public R<List<Genres>> findGenres(){
        List<Genres> genresList = null;

        try{
            genresList = genresRepository.findAll();
        }catch (Exception e){
            logger.error("Find the genres list fails" + e.getMessage());
        }

        return new R<List<Genres>>().success().data(genresList);
    }

    @Operation(summary = "Creates a new genre")
    @PostMapping
    public R<Genres> addGenre(@RequestBody Genres genre){
        Genres createdGenre=null;
        try {
            createdGenre=genresRepository.save(genre);
        }
        catch (Exception e){
            logger.error("Creates a new genre fails:"+e.getMessage());
        }
        return new R<Genres>().success().data(createdGenre);
    }

    @Operation(summary = "Update an existing genre")
    @PutMapping
    public R<Genres> updateGenre(@RequestBody Genres genre){
        Genres updatedGenre=null;
        try {
            updatedGenre=genresRepository.save(genre);
        }catch (Exception e){
            logger.error("Update an existing genre fails:"+e.getMessage());
        }
        return new R<Genres>().success().data(updatedGenre);
    }

    @Operation(summary = "Get an existing genre by ID")
    @GetMapping("/{id}")
    public R<Genres> findGenreById(@Parameter(description = "A genre's Id")@PathVariable String id){
        Genres genre=null;
        try {
            genre= genresRepository.findById(id).orElse(new Genres());

        }catch (Exception e){
            logger.error("Get an existing genre fails:"+e.getMessage());
        }
        return new R<Genres>().success().data(genre);
    }

    @Operation(summary = "Get an existing genre from genre ID")
    @DeleteMapping("/{id}")
    public R<Genres> deleteGenre(@Parameter(description = "Delete an existing genre by Id")@PathVariable String id){
        try {
            genresRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Get an existing genre fails:"+e.getMessage());
        }
        return new R<Genres>().success();
    }

}
