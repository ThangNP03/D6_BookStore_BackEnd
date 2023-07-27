package ra.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class ReturnAndBorrowBookVontroller {
     @Autowired
    private IUserService userService;
     @Autowired
    private IBookService bookService;
     @Autowired
    private CustomUserDetailsService customUserDetailsService;
     @Autowired
     private ReturnAndBorrowBookService returnAndBorrowBookService;

     @GetMapping()
    public ResponseEntity<?> show(){
         List<ReturnAndBorrowBooks> list =returnAndBorrowBookService.findAll();
         Collections.sort(list, Comparator.comparing(ReturnAndBorrowBooks::getId));
         return ResponseEntity.ok(list);
     }
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id ){
         Long booking = returnAndBorrowBookService.findById(id).getId();
         return new ResponseEntity<>(booking, HttpStatus.OK);
     }
    @PostMapping("/addBook")

    public ResponseEntity<?> create(@RequestBody ReturnAndBorrowBooksResponse returnAndBorrowBooksResponse ) {
        Users users = customUserDetailsService.getCurrentUser();
        Books books = bookService.findById(returnAndBorrowBooksResponse.getBookId());
        ReturnAndBorrowBooks returnAndBorrow = ReturnAndBorrowBooks.builder()
                .bookId(books)
                .userId(users)
                .returnAt(new Date())
                .borrowAt(new Date())
                .status("")
                .reason("")
                .build();
        if (books.getId() != null){
            returnAndBorrow.setQuantity(returnAndBorrow.getQuantity() + 1);
            returnAndBorrowBookService.save(returnAndBorrow);
        }
        returnAndBorrowBookService.save(returnAndBorrow);
        return new ResponseEntity<>(new ResponseMessage("test"), HttpStatus.OK);
    }

    @GetMapping("/findUserID/{id}")
    public ResponseEntity<?> findListCartByUserID (@PathVariable Long id){
         List<ReturnAndBorrowBooks> listCart = returnAndBorrowBookService.findByUserId_UserId(id);
         return new ResponseEntity<>(listCart, HttpStatus.OK) ;
    }
    @PostMapping("/orderBook")
    public ResponseEntity<?> orderBook(@RequestBody ReturnAndBorrowBooksResponse returnAndBorrowBooksResponse) {
        Users users = customUserDetailsService.getCurrentUser();
        Books books = bookService.findById(returnAndBorrowBooksResponse.getBookId());
        ReturnAndBorrowBooks id = returnAndBorrowBookService.findById(returnAndBorrowBooksResponse.getBookId());
        if (id.getBookId() != null ){
            ReturnAndBorrowBooks returnAndBorrow = ReturnAndBorrowBooks.builder()
                    .returnAt(new Date())
                    .borrowAt(new Date())
                    .reason("")
                    .status("LOADING")
                    .userId(users)
                    .bookId(books)
                    .quantity(returnAndBorrowBooksResponse.getQuantity())
                    .build();
            returnAndBorrowBookService.save(returnAndBorrow);
        }


//        returnAndBorrowBookService.save(returnAndBorrow);
        // Nếu không thuộc các trạng thái trên, trả về HttpStatus.CREATED
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
