package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ra.model.ReturnAndBorrowBooks;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface IReturnAndBorrowBookRepository extends JpaRepository<ReturnAndBorrowBooks, Long> {

   List<ReturnAndBorrowBooks> findByUserId_UserId (Long id);
   @Modifying
   @Transactional
   @Query(value = "delete from returnandborrowbooks where id =?1", nativeQuery = true)
   void deleteByBookId(Long id);

}
