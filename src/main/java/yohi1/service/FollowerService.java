package yohi1.service;

import yohi1.models.User;

import java.util.List;

public interface FollowerService {

    void follow(Long uId, Long id);

    int[] followCount(Long uId);

   boolean  followCheck(Long uId,Long id);

    void cancelFollow(Long uId, Long id);

    List<User> findFollowings(Long id);
}
