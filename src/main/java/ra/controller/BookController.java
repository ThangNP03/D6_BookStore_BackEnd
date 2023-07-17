package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.BookRespone;
import ra.dto.respone.ResponseMessage;
import ra.model.Books;
import ra.service.IBookService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookStore/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping
    public List<Books> findAll(){
        List<Books> listBooks = bookService.findAll();
        return listBooks;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody  BookRespone bookRespone){
                Books books = Books.builder()
                .bookName(bookRespone.getBookName())
                .description(bookRespone.getDescription())
                .loanPrice(bookRespone.getLoanPrice())
                .depositFee(bookRespone.getDepositFee())
                .author(bookRespone.getAuthor())
                .nxb(bookRespone.getNxb())
                .numberOfPage(bookRespone.getNumberOfPage())
                .translator(bookRespone.getTranslator())
                .quantity(bookRespone.getQuantity())
                        .image(bookRespone.getImage())
                .build();
        bookService.save(books);
        return ResponseEntity.ok(new ResponseMessage("Thêm mới thành công !!"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody BookRespone bookRespone, @PathVariable Long id ){
        Long bookId = bookService.findById(id).getId();
        Books books = Books.builder()
                .id(bookId)
                .bookName(bookRespone.getBookName())
                .description(bookRespone.getDescription())
                .loanPrice(bookRespone.getLoanPrice())
                .depositFee(bookRespone.getDepositFee())
                .author(bookRespone.getAuthor())
                .nxb(bookRespone.getNxb())
                .numberOfPage(bookRespone.getNumberOfPage())
                .translator(bookRespone.getTranslator())
                .quantity(bookRespone.getQuantity())
                .image(bookRespone.getImage())
                .build();
        bookService.save(books);
        return ResponseEntity.ok(new ResponseMessage("Chỉnh sửa thông sách thành công  !!"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id ){
        Long idBook = bookService.findById(id).getId();
        bookService.deleteById(idBook);
        return ResponseEntity.ok("Xóa thành công !!!!");
    }
    @GetMapping("/searchByAuthor/{author}")
    public List<Books> searchAuthor(@PathVariable String author){

        return  bookService.findBooksByAuthorContains(author);
    }
    @GetMapping("/searchByBookName/{bookName}")
    public List<Books> searchByBookName(@PathVariable String bookName ){
        return  bookService.findBooksByBookNameContains(bookName );
    }

}
