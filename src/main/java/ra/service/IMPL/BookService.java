package ra.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Books;
import ra.model.user.Roles;
import ra.repository.IBookRepository;
import ra.service.IBookService;

import java.util.List;
@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Override
    public List<Books> findBooksByBookNameContains(String bookName) {
        return bookRepository.findBooksByBookNameContains(bookName);
    }
    @Override
    public List<Books> findBooksByAuthorContains(String author) {
        return bookRepository.findBooksByAuthorContains(author);
    }

    @Override
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Books books) {
        bookRepository.save(books);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Books findById(Long id) {
        return bookRepository.findById(id).get();
    }
}
