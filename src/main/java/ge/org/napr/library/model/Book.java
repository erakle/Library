package ge.org.napr.library.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String title;
    
    private String language;
    
    private String genre;
    
    private Date date;
    
    @ManyToMany
    private List<Author> author;
    
}
