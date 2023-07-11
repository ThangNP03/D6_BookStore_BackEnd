package ra.service;

import ra.model.Books;

import java.util.List;

public interface IBookService extends IGeneric<Books, Long>{
    List<Books> findBooksByBookNameContains(String bookName );
    List<Books> findBooksByAuthorContains(String author);
}
