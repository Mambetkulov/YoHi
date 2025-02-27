package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Comment;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.repositery.CommentRepo;
import yohi1.service.CommentService;
import yohi1.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentSerImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final PostService postService;

    @Override
    public List<Comment> getAll() {
        return commentRepo.getAll();
    }

    @Override
    @Transactional
    public void addComment(Long postId, String text, User user) {
        Post post = postService.getById(postId);
        Comment comment = new Comment();
        comment.setComment(text);
        comment.setUser(user);
        post.getComments().add(comment);
        commentRepo.addComment(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long postId,Long userId) {
         commentRepo.deleteComment(commentId,postId,userId);
    }


}
