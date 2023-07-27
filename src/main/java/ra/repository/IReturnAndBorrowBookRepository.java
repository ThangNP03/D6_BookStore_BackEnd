package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.ReturnAndBorrowBooks;
@Repository
public interface IReturnAndBorrowBookRepository extends JpaRepository<ReturnAndBorrowBooks, Long> {
}
