package ra.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ra.model.Books;
import ra.model.ReturnAndBorrowBooks;
import ra.model.user.Users;

import javax.transaction.Transactional;
import java.util.List;

public interface IReturnAndBorrowBooks extends IGeneric<ReturnAndBorrowBooks, Long >{
    List<ReturnAndBorrowBooks> findByUserId_UserId (Long id);
    @Modifying
    @Transactional
    @Query(value = "delete from subscription where sub_id =?1", nativeQuery = true)
    void deleteByBookId(Long id);
}
