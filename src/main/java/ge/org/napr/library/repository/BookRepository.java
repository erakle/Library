package ge.org.napr.library.repository;

import ge.org.napr.library.model.Book;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

    public List<Book> findAllByAuthorId(long id);
    
}
