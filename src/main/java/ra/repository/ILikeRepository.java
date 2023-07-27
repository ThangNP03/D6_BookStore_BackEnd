package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.LikesAndDisLike;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<LikesAndDisLike, Long> {
    @Query("select  count (l) from LikesAndDisLike l where l.id =: bookId")
    Long countLikesByBookId(@Param("bookId") Long bookId);
    Optional<LikesAndDisLike> findByBooksIdAndUsersUserId(Long bookId,Long userId);
    @Modifying
    @Transactional
    @Query(value = "delete from subscription where sub_id =?1", nativeQuery = true)
    void deleteByDislike(Long id);
}
