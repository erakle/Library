package ge.org.napr.library.DTO;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDTO {
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String language;
    
    @NotEmpty
    private String genre;
    
    private Date date;
    
    @NotEmpty
    private List<BookAuthorDTO> authors;
    
}
