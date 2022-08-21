package com.example.sbreactbootcamp.books.controllers;


import com.example.sbreactbootcamp.books.model.Authors;
import com.example.sbreactbootcamp.books.repositories.IAuthorsRepository;
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
@RequestMapping("/api/authors")
@Tag(name = "default")
@CrossOrigin("*")
public class AuthorsController {
    private static final Logger logger= LoggerFactory.getLogger(AuthorsController.class);

    @Autowired
    IAuthorsRepository authorsRepository;

    @Operation(summary ="Find the Author list")
    @GetMapping("")
    @ResponseBody
    public R<List<Authors>> findAuthors(){
        List<Authors> authorsList = null;

        try{
            authorsList = authorsRepository.findAll();
        }catch (Exception e){
            logger.error("Find the Authors list fails: " +e.getMessage());
        }
        return new R<List<Authors>>().success().data(authorsList);
    }

    @Operation(summary = "Creates a new author")
    @PostMapping
    public R<Authors> addAuthor(@RequestBody Authors author){
        Authors cratedAuthor=null;
        try {
            cratedAuthor=authorsRepository.save(author);
        }
        catch (Exception e){
            logger.error("Creates a new author fails:"+e.getMessage());
        }
        return new R<Authors>().success().data(cratedAuthor);
    }

    @Operation(summary = "Update an existing author")
    @PutMapping
    public R<Authors> updateAuthor(@RequestBody Authors author){
        Authors updatedAuthor=null;
        try {
            updatedAuthor=authorsRepository.save(author);
        }catch (Exception e){
            logger.error("Update an existing author fails:"+e.getMessage());
        }
        return new R<Authors>().success().data(updatedAuthor);
    }

    @Operation(summary = "Get an existing author by ID")
    @GetMapping("/{id}")
    public R<Authors> findAuthorById(@Parameter(description = "An author's Id")@PathVariable String id){
        Authors author=null;
        try {
            author= authorsRepository.findById(id).orElse(new Authors());

        }catch (Exception e){
            logger.error("Get an existing author fails:"+e.getMessage());
        }
        return new R<Authors>().success().data(author);
    }

    @Operation(summary = "Get an existing author by author ID")
    @DeleteMapping("/{id}")
    public R<Authors> deleteAuthor(@Parameter(description = "Delete an existing author by Id")@PathVariable String id){
        try {
            authorsRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Delete an existing author fails:"+e.getMessage());
        }
        return new R<Authors>().success();
    }

}
