package ra.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.Books;
import ra.model.user.Roles;
import ra.repository.IBookRepository;
import ra.service.IBookService;

import java.util.List;
import java.util.Optional;

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
    public Optional<Books> getBooksById(Long id) {
        return bookRepository.getBooksById(id);
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

//    @Override
//    public Page<Books> findAllBook(Pageable pageable) {
//        return bookRepository.findAllBook(pageable);
//    }
}
