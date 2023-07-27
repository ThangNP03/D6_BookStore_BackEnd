package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.LikeResponse;
import ra.model.Books;
import ra.model.LikesAndDisLike;
import ra.service.IBookService;
import ra.service.ILikeService;
import ra.service.IUserService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookStore/like")
public class LikeController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private IUserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getLikeByBookId(@PathVariable Long id ){
        Long countLike = likeService.countLikesByBookId(id);
        return ResponseEntity.ok(countLike);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody LikeResponse likeResponse) {
        Books books = bookService.findById(likeResponse.getBookId());
        Optional<LikesAndDisLike> likesOptional = likeService.findByBooksIdAndUsersUserId(likeResponse.getBookId(), likeResponse.getUserId());
        if (likesOptional.isPresent()) {
            LikesAndDisLike likes = likesOptional.get();
            if (likes.isStatusLike()) {
                // đang like

                books.setLikes(books.getLikes() - 1);
                likes.setStatusLike(false);
                likeService.deleteByDislike(likes.getId());
                bookService.save(books);
                return ResponseEntity.ok("Bạn đã bỏ  yêu thích sách!!!");
            }

        } else {
            LikesAndDisLike likes = LikesAndDisLike.builder()
                    .users(userService.findById(likeResponse.getUserId()))
                    .books(books)
                    .statusLike(true)
                    .build();
            likeService.save(likes);
            books.setLikes(books.getLikes() + 1);
            bookService.save(books);
        }
        return ResponseEntity.ok("Bạn đã yêu thích sách!!!");

    }
}
