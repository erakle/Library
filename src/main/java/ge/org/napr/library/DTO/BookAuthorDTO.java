package ge.org.napr.library.DTO;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookAuthorDTO extends AuthorDTO{
    
    @NotEmpty
    private Long id;    
}
