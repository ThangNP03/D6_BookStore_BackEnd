package ra.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.Books;

import java.util.List;
import java.util.Optional;

public interface IBookService extends IGeneric<Books, Long>{
    List<Books> findBooksByBookNameContains(String bookName );
    List<Books> findBooksByAuthorContains(String author);
    Optional<Books> getBooksById(Long id );
//    Page <Books> findAllBook(Pageable pageable);
}
