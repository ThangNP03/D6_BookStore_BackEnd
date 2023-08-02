package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.BookRespone;
import ra.dto.respone.ResponseMessage;
import ra.model.Books;
import ra.service.IBookService;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/detallBook/{id}")
    public ResponseEntity<?> detallBookById(@PathVariable Long id){
        Optional<Books> detalls = bookService.getBooksById(id);
        if (!detalls.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT );
        }else {

            return new ResponseEntity<>(detalls, HttpStatus.OK);
        }
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
                        .status(bookRespone.isStatus())
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
                .status(bookRespone.isStatus())
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
        if (bookName == ""){
            return    bookService.findAll();
        }else {
        return  bookService.findBooksByBookNameContains(bookName );
    }}

}
