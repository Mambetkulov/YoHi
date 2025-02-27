package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.repositery.LikeRepo;

import java.util.ArrayList;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikeRepoImpl implements LikeRepo {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public boolean likeCheck(Long id, Long postId) {
        Post post = em.find(Post.class, postId);
        if(post.getLikes() == null) {
            post.setLikes(new ArrayList<>());
            return true;
        }
        if(post.getLikes().contains(id)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean likeIt(Long id, Long postId) {
        Post post = em.find(Post.class, postId);
        post.getLikes().add(id);
        em.merge(post);
        return true;
    }

    @Override
    public boolean cancelLike(Long id, Long postId) {
         Post post = em.find(Post.class, postId);
         post.getLikes().remove(id);
         em.merge(post);
         return false;
    }

    @Override
    public int likeCount(Long postId) {
        Post post = em.find(Post.class, postId);
        return post.getLikes().size();
    }
}
