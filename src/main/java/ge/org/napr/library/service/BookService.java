package ge.org.napr.library.service;

import ge.org.napr.library.model.Author;
import ge.org.napr.library.model.Book;
import ge.org.napr.library.DTO.BookAuthorDTO;
import ge.org.napr.library.DTO.BookDTO;
import ge.org.napr.library.repository.AuthorRepository;
import ge.org.napr.library.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private AuthorService authorService;
    
    public Book mapToEntity(BookDTO bookDTO, List<Author> authors){
        
        Book book = new Book();
        
        book.setTitle(bookDTO.getTitle());
        book.setLanguage(bookDTO.getLanguage());
        book.setGenre(bookDTO.getGenre());
        book.setDate(bookDTO.getDate());
        book.setAuthor(authors);
        
        return book;
    }
    
    public BookDTO mapToBookDto(Book book){
        
        BookDTO bookDTO = new BookDTO();
        
        bookDTO.setTitle(book.getTitle());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setDate(book.getDate());
        
        List<BookAuthorDTO> bookAuthorDtos = book.getAuthor().stream()
                .map(authorService::mapToBookAuthorDto)
                .collect(Collectors.toList());
        
        bookDTO.setAuthors(bookAuthorDtos);
        
        return bookDTO;
    }
    
    @Transactional
    public void addBook(BookDTO bookDto){
        
        List<Author> authors = this.getOrCreateAuthor(bookDto);
        
        Book book = this.mapToEntity(bookDto, authors);
        
        bookRepository.save(book);
    }
    
    private List<Author> getOrCreateAuthor(BookDTO bookDto){
        
        List<Author> authors = new ArrayList<>();
        
        for (BookAuthorDTO bookAuthorDto : bookDto.getAuthors()) {
            
            if (bookAuthorDto.getId() != null){
                
                Optional<Author> author = authorRepository.findById(bookAuthorDto.getId());
                
                if (author.isPresent()){
                    authors.add(author.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author doesn't exist!");
                }
            } else {
                authors.add(authorService.addAuthor(bookAuthorDto));            
            }
        } 
        
        return authors;
    }
    
    public void updateBook(BookDTO bookDto, long id){
        
        Optional<Book> bookOp = bookRepository.findById(id);
        
        if (bookOp.isPresent()) {
            
            Book book = bookOp.get();
        
            book.setTitle(bookDto.getTitle());
            book.setLanguage(bookDto.getLanguage());
            book.setGenre(bookDto.getGenre());
            book.setDate(bookDto.getDate());
            book.setAuthor(this.getOrCreateAuthor(bookDto));

            bookRepository.save(book);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book doesn't exist!");
        }
        
    }
    
    public void deleteBook(long id){
        
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book doesn't exist!");
        }   
    }
    
    public List<BookDTO> getBooks(){
        
        List<Book> books = (List<Book>) bookRepository.findAll();
        
        return books.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
    
    public List<BookDTO> searchBooksByAuthor(long id){
        
        if (authorRepository.existsById(id)) {
            List<Book> books = bookRepository.findAllByAuthorId(id);
        
            return books.stream()
                    .map(this::mapToBookDto)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author doesn't exist!");
        }
    }
}
