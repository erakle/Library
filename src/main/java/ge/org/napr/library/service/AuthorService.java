package ge.org.napr.library.service;

import ge.org.napr.library.model.Author;
import ge.org.napr.library.DTO.AuthorDTO;
import ge.org.napr.library.DTO.BookAuthorDTO;
import ge.org.napr.library.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author mapToEntity(AuthorDTO authorDTO) {

        Author author = new Author();

        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setNationality(authorDTO.getNationality());

        return author;
    }

    public AuthorDTO mapToAuthorDto(Author author) {

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setNationality(author.getNationality());

        return authorDTO;
    }
    
    public BookAuthorDTO mapToBookAuthorDto(Author author) {

        BookAuthorDTO bookAuthorDTO = new BookAuthorDTO();

        bookAuthorDTO.setId(author.getId());
        bookAuthorDTO.setFirstName(author.getFirstName());
        bookAuthorDTO.setLastName(author.getLastName());
        bookAuthorDTO.setNationality(author.getNationality());

        return bookAuthorDTO;
    }

    public Author addAuthor(AuthorDTO authorDto) {

        Author author = this.mapToEntity(authorDto);
        authorRepository.save(author);
        
        return author;
    }

    public void updateAuthor(AuthorDTO authorDto, long id) {

        if (authorRepository.existsById(id)) {

            Optional<Author> author = authorRepository.findById(id);
            author.get().setFirstName(authorDto.getFirstName());
            author.get().setLastName(authorDto.getLastName());
            author.get().setNationality(authorDto.getNationality());

            authorRepository.save(author.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author doesn't exist!");
        }
    }

    public void deleteAuthor(long id) {

        if (authorRepository.existsById(id)) {         
            authorRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author doesn't exist!");
        }
    }

    public List<AuthorDTO> getAuthors() {

        List<Author> authors = (List<Author>) authorRepository.findAll();
        return authors.stream()
                .map(this::mapToAuthorDto)
                .collect(Collectors.toList());
    }
}
