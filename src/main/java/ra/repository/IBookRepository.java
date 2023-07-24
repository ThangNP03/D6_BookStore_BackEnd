package ra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.Books;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Books, Long> {
    List<Books> findBooksByBookNameContains(String bookName );
    List<Books> findBooksByAuthorContains(String author);
//    Page <Books> findAllBook(Pageable pageable);

    Optional<Books> getBooksById(Long id );
}
