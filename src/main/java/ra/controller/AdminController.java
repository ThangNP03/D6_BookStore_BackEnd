package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PatchMapping("/borrowed/{id}")
    public ResponseEntity<?> borrowed(@PathVariable Long id) {
        ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
        returnAndBorrowBooks.setStatus("Borrowed");
        returnAndBorrowBookService.save(returnAndBorrowBooks);
        return ResponseEntity.ok("ok");
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
        returnAndBorrowBooks.setStatus("Cancel");
        returnAndBorrowBookService.save(returnAndBorrowBooks);
        return ResponseEntity.ok("ok");
    }

}
