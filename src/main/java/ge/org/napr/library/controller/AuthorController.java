package ge.org.napr.library.controller;

import ge.org.napr.library.DTO.AuthorDTO;
import ge.org.napr.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @Operation(summary = "Add new author")
    @PostMapping
    public void addAuthor(@Valid @RequestBody AuthorDTO authorDto) {

        authorService.addAuthor(authorDto);     
    }
    
    @Operation(summary = "Update author")
    @PutMapping("{id}")
    public void updateAuthor(@Valid @RequestBody AuthorDTO authorDto, @PathVariable("id") long id) {

        authorService.updateAuthor(authorDto, id);
    }
    
    @Operation(summary = "Delete author")
    @DeleteMapping("{id}")
    public void deleteAuthor(@Valid @PathVariable("id") long id) {

        authorService.deleteAuthor(id);
    }
    
    @Operation(summary = "List of all authors")
    @GetMapping
    public List<AuthorDTO> getAuthors() {

        return authorService.getAuthors();
    }
}
