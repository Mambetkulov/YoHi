package yohi1.repositery;

public interface LikeRepo {

    boolean likeCheck(Long id, Long postId);

    boolean likeIt(Long id, Long postId);

    boolean cancelLike(Long id, Long postId);

     int likeCount(Long postId);
}
