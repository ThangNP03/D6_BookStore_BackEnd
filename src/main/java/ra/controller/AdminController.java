package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.OrderResponse;
import ra.model.ReturnAndBorrowBooks;
import ra.service.IMPL.ReturnAndBorrowBookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookStore/admin")
public class AdminController {
    @Autowired
    private ReturnAndBorrowBookService returnAndBorrowBookService;

    @PutMapping("/borrowed")
    public ResponseEntity<?> borrowed(@RequestBody OrderResponse orderResponse) {

        orderResponse.getListId().forEach(
                id -> {
                    ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
                    returnAndBorrowBooks.setStatus("Borrowed");
                    returnAndBorrowBookService.save(returnAndBorrowBooks);
                }
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PutMapping("/returnBooks")
    public ResponseEntity<?> returnBooks(@RequestBody OrderResponse orderResponse) {

        orderResponse.getListId().forEach(
                id -> {
                    ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
                    returnAndBorrowBooks.setStatus("Return");
                    returnAndBorrowBookService.save(returnAndBorrowBooks);
                }
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestBody OrderResponse orderResponse) {

        orderResponse.getListId().forEach(
                id -> {
                    ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
                    returnAndBorrowBooks.setStatus("Cancel");
                    returnAndBorrowBookService.save(returnAndBorrowBooks);
                }
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
