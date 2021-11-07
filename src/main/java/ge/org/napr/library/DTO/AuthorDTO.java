package ge.org.napr.library.DTO;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthorDTO {
    
    @NotEmpty
    private String firstName;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private String nationality;
    
}
