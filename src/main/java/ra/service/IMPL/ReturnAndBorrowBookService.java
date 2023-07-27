package ra.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.ReturnAndBorrowBooks;
import ra.repository.IReturnAndBorrowBookRepository;
import ra.service.IReturnAndBorrowBooks;

import java.util.List;
@Service
public class ReturnAndBorrowBookService implements IReturnAndBorrowBooks {
    @Autowired
    private IReturnAndBorrowBookRepository returnAndBorrowBookRepository;
    @Override
    public List<ReturnAndBorrowBooks> findAll() {
        return returnAndBorrowBookRepository.findAll();
    }

    @Override
    public void save(ReturnAndBorrowBooks returnAndBorrowBooks) {
        returnAndBorrowBookRepository.save(returnAndBorrowBooks);
    }

    @Override
    public void deleteById(Long id) {
        returnAndBorrowBookRepository.deleteById(id);
    }

    @Override
    public ReturnAndBorrowBooks findById(Long id) {
        return returnAndBorrowBookRepository.findById(id).get();
    }
}
