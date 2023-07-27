package ra.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.LikesAndDisLike;
import ra.repository.ILikeRepository;
import ra.service.ILikeService;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;
    @Override
    public List<LikesAndDisLike> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void save(LikesAndDisLike like) {
        likeRepository.save(like);
    }

    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    public LikesAndDisLike findById(Long id) {
        return likeRepository.findById(id).get();
    }

    @Override
    public Long countLikesByBookId(Long bookId) {
        return likeRepository.countLikesByBookId(bookId);
    }

    @Override
    public Optional<LikesAndDisLike> findByBooksIdAndUsersUserId(Long userId, Long bookId) {
        return likeRepository.findByBooksIdAndUsersUserId(bookId, userId);
    }

    @Override
    public void deleteByDislike(Long id) {
        likeRepository.deleteByDislike(id);
    }
}
