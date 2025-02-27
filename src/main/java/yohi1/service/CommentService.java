package yohi1.service;

import yohi1.models.Comment;
import yohi1.models.Post;
import yohi1.models.User;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    void addComment(Long postId, String text, User user);

    void deleteComment(Long commentId, Long postId, Long userId);
}
