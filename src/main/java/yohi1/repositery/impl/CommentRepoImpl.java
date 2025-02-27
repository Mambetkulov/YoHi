package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Comment;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.repositery.CommentRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepoImpl implements CommentRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Comment> getAll() {
        List<Comment> comments  = entityManager.createQuery("select c from Comment c", Comment.class).getResultList();
        return comments != null ? comments : new ArrayList<Comment>();
    }

    @Override
    public void deleteComment(Long commentId, Long postId,Long userId) {
        Comment comment = entityManager.find(Comment.class, commentId);
        User user = entityManager.find(User.class, userId);
        user.getComments().remove(comment);
        Post post = entityManager.find(Post.class, postId);
        post.getComments().remove(comment);
        entityManager.remove(comment);
    }

    @Override
    public void addComment(Comment comment) {
       entityManager.persist(comment);
    }
}
