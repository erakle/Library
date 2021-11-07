package ge.org.napr.library.controller;

import ge.org.napr.library.DTO.BookDTO;
import ge.org.napr.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Operation(summary = "Add new book")
    @PostMapping
    public void addBook(@Valid @RequestBody BookDTO bookDto) {

        bookService.addBook(bookDto);
    }
    
    @Operation(summary = "Update book")
    @PutMapping("{id}")
    public void updateBook(@Valid @RequestBody BookDTO bookDto, @PathVariable("id") long id) {

        bookService.updateBook(bookDto, id);
    }
    
    @Operation(summary = "Delete book")
    @DeleteMapping("{id}")
    public void deleteBooks(@Valid @PathVariable("id") long id) {

        bookService.deleteBook(id);
    }
    
    @Operation(summary = "List of all books")
    @GetMapping
    public List<BookDTO> getBooks() {

        return bookService.getBooks();
    }
    
    @Operation(summary = "Search books by author")
    @GetMapping("/author/{id}")
    public List<BookDTO> searchBooksByAuthor(@Valid @PathVariable("id") long authorId) {

        return bookService.searchBooksByAuthor(authorId);
    }
}
