package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.OrderResponse;
import ra.dto.respone.ResponseMessage;
import ra.dto.respone.ReturnAndBorrowBooksResponse;
import ra.model.Books;
import ra.model.ReturnAndBorrowBooks;
import ra.model.user.Users;
import ra.sercurity.userpricipal.CustomUserDetailsService;
import ra.service.IBookService;
import ra.service.IMPL.ReturnAndBorrowBookService;
import ra.service.IUserService;

import java.util.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookStore/cart")
public class ReturnAndBorrowBookController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private ReturnAndBorrowBookService returnAndBorrowBookService;

    @GetMapping()
    public ResponseEntity<?> show() {
        List<ReturnAndBorrowBooks> list = returnAndBorrowBookService.findAll();
        Collections.sort(list, Comparator.comparing(ReturnAndBorrowBooks::getId));
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable Long id) {
        returnAndBorrowBookService.deleteByBookId(id);
        return ResponseEntity.ok("Xóa thành công !!!");
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> create(@RequestBody ReturnAndBorrowBooksResponse returnAndBorrowBooksResponse) {
        Users users = customUserDetailsService.getCurrentUser();
        Books books = bookService.findById(returnAndBorrowBooksResponse.getBookId());

        List<ReturnAndBorrowBooks> list = returnAndBorrowBookService.findByUserId_UserId(users.getUserId());
        ReturnAndBorrowBooks returnAndBorrow = ReturnAndBorrowBooks.builder()
                .bookId(books)
                .userId(users)
                .returnAt(new Date())
                .borrowAt(new Date())
                .status("")
                .reason("")
                .quantity(returnAndBorrowBooksResponse.getQuantity())
                .build();
        for (int i = 0; i < list.size(); i++) {
            if (returnAndBorrow.getBookId() == list.get(i).getBookId() ) {
                list.get(i).setQuantity(list.get(i).getQuantity() + 1)  ;
                if (list.get(i).getStatus() == "Borrowed"){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (list.get(i).getQuantity() > books.getQuantity()) {
                    return new ResponseEntity<>(new ResponseMessage("postMessage"), HttpStatus.OK);
                }
                returnAndBorrowBookService.save(list.get(i));
                return new ResponseEntity<>(new ResponseMessage("Ok"), HttpStatus.OK);
            }
        }
        returnAndBorrow.setQuantity(returnAndBorrow.getQuantity() + 1);
        returnAndBorrowBookService.save(returnAndBorrow);
        return new ResponseEntity<>(new ResponseMessage("oke"), HttpStatus.OK);
    }


    @PutMapping("/minusQuantity")
    public ResponseEntity<?> minusQuantity(@RequestBody ReturnAndBorrowBooksResponse returnAndBorrowBooksResponse) {
        Users users = customUserDetailsService.getCurrentUser();
        Books books = bookService.findById(returnAndBorrowBooksResponse.getBookId());
        List<ReturnAndBorrowBooks> list = returnAndBorrowBookService.findByUserId_UserId(users.getUserId());
        ReturnAndBorrowBooks returnAndBorrow = ReturnAndBorrowBooks.builder()
                .bookId(books)
                .userId(users)
                .returnAt(new Date())
                .borrowAt(new Date())
                .status("")
                .reason("")
                .quantity(returnAndBorrowBooksResponse.getQuantity())
                .build();
        for (int i = 0; i < list.size(); i++) {
            if (returnAndBorrow.getBookId() == list.get(i).getBookId()) {
                list.get(i).setQuantity(list.get(i).getQuantity() - 1);
                if (list.get(i).getQuantity() <= 0) {
                    return new ResponseEntity<>(new ResponseMessage("refuse"), HttpStatus.OK);
                }
                returnAndBorrowBookService.save(list.get(i));
                return new ResponseEntity<>(new ResponseMessage("test"), HttpStatus.OK);
            }
        }
        returnAndBorrowBookService.save(returnAndBorrow);
        return new ResponseEntity<>(new ResponseMessage("test"), HttpStatus.OK);
    }

    @GetMapping("/findUserID/{id}")
    public ResponseEntity<?> findListCartByUserID(@PathVariable Long id) {
        List<ReturnAndBorrowBooks> listCart = returnAndBorrowBookService.findByUserId_UserId(id);
        return new ResponseEntity<>(listCart, HttpStatus.OK);
    }

    @PutMapping("/orderBook")
    public ResponseEntity<?> orderBook(@RequestBody OrderResponse orderResponse) {

            orderResponse.getListId().forEach(
                id -> {
                    ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
                    returnAndBorrowBooks.setStatus("Loading");
                    returnAndBorrowBookService.save(returnAndBorrowBooks);
                }
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/replay/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
        returnAndBorrowBooks.setStatus("Loading");
        returnAndBorrowBookService.save(returnAndBorrowBooks);
        return ResponseEntity.ok("ok");
    }
    @PatchMapping("/author-cancel/{id}")
    public ResponseEntity<?> authorCancel(@PathVariable Long id) {
        ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
        returnAndBorrowBooks.setStatus("Cancel");
        returnAndBorrowBookService.save(returnAndBorrowBooks);
        return ResponseEntity.ok("ok");
    }
    @PatchMapping("/returnBooks/{id}")
    public ResponseEntity<?> returnBooks(@PathVariable Long id) {
        ReturnAndBorrowBooks returnAndBorrowBooks = returnAndBorrowBookService.findById(id);
        returnAndBorrowBooks.setStatus("Return");
        returnAndBorrowBookService.save(returnAndBorrowBooks);
        return ResponseEntity.ok("ok");
    }

}
