package com.example.sbreactbootcamp.books.controllers;

import com.example.sbreactbootcamp.books.model.Books;
import com.example.sbreactbootcamp.books.repositories.IBooksRepository;
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
@RequestMapping("/api/books")
@Tag(name = "default")
@CrossOrigin("*")
public class BooksController {

    private static final Logger logger= LoggerFactory.getLogger(BooksController.class);

    @Autowired
    IBooksRepository booksRepository;

    @Operation(summary ="Find the Book list")
    @GetMapping("")
    @ResponseBody
    public R<List<Books>> findBooks(){
        List<Books> booksList = null;

        try{
            booksList = booksRepository.findAll();
        }catch (Exception e){
            logger.error("Find the Book list fails: " +e.getMessage());
        }

        return new R<List<Books>>().success().data(booksList);
    }

    @Operation(summary = "Creates a new book")
    @PostMapping
    public R<Books> addBook(@RequestBody Books book){
        Books createdBook=null;
        try {
            createdBook=booksRepository.save(book);
        }
        catch (Exception e){
            logger.error("Creates a new book fails:"+e.getMessage());
        }
        return new R<Books>().success().data(createdBook);
    }

    @Operation(summary = "Update an existing book")
    @PutMapping
    public R<Books> updateBook(@RequestBody Books book){
        Books updatedBook=null;
        try {
            updatedBook=booksRepository.save(book);
        }catch (Exception e){
            logger.error("Update an existing book fails:"+e.getMessage());
        }
        return new R<Books>().success().data(updatedBook);
    }

    @Operation(summary = "Get an existing book by ID")
    @GetMapping("/{id}")
    public R<Books> findBookById(@Parameter(description = "A book's Id")@PathVariable String id){
        Books book=null;
        try {
           book= booksRepository.findById(id).orElse(new Books());

        }catch (Exception e){
            logger.error("Get an existing book fails:"+e.getMessage());
        }
        return new R<Books>().success().data(book);
    }

    @Operation(summary = "Get an existing book from book ID")
    @DeleteMapping("/{id}")
    public R<Books> deleteBook(@Parameter(description = "Delete an existing book by Id")@PathVariable String id){
        try {
             booksRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Get an existing book fails:"+e.getMessage());
        }
        return new R<Books>().success();
    }

}
