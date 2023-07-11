package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.Books;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Books, Long> {
    List<Books> findBooksByBookNameContains(String bookName );
    List<Books> findBooksByAuthorContains(String author);
}
