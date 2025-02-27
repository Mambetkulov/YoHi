package yohi1.repositery;

import yohi1.models.User;

import java.util.List;

public interface FollowerRepo {

    void follow(Long uId, Long id);


    int[] followCount(Long uId);

     boolean followCheck(Long uId,Long id);

    void cancelFollow(Long uId, Long id);

    List<User> getFollowingList(Long id);
}
