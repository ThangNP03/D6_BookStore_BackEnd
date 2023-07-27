package ra.service;

import ra.model.Books;
import ra.model.ReturnAndBorrowBooks;
import ra.model.user.Users;

import java.util.List;

public interface IReturnAndBorrowBooks extends IGeneric<ReturnAndBorrowBooks, Long >{
    List<ReturnAndBorrowBooks> findByUserId_UserId (Long id);

}
