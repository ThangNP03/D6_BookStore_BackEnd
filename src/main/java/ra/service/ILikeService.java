package ra.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.model.LikesAndDisLike;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ILikeService extends IGeneric<LikesAndDisLike, Long>{
    @Query("select  count (l) from LikesAndDisLike l where l.id =: bookId")
    Long countLikesByBookId(@Param("bookId") Long bookId);
    Optional<LikesAndDisLike> findByBooksIdAndUsersUserId(Long userId, Long bookId);
    @Modifying
    @Transactional
    @Query(value = "delete from subscription where sub_id =?1", nativeQuery = true)
    void deleteByDislike(Long id);
}
