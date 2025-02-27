package yohi1.service;

public interface LikeService {

    boolean likeIt(Long id, Long postId);


    void likeCount(Long postId);
}
