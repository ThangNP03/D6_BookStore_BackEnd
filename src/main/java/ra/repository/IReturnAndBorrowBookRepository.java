package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ra.model.ReturnAndBorrowBooks;

import java.util.List;


@Repository
public interface IReturnAndBorrowBookRepository extends JpaRepository<ReturnAndBorrowBooks, Long> {

   List<ReturnAndBorrowBooks> findByUserId_UserId (Long id);
}
