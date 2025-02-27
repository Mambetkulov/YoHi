package yohi1.repositery;

import yohi1.models.Comment;
import yohi1.models.User;

import java.util.List;

public interface CommentRepo {

    List<Comment> getAll();

    void deleteComment(Long commentId, Long postId,Long userId);

    void addComment(Comment comment);
}
