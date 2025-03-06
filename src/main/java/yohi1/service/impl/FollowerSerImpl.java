package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yohi1.models.User;
import yohi1.repositery.FollowerRepo;
import yohi1.service.FollowerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerSerImpl implements FollowerService {
    private final FollowerRepo followerRepo;

    @Override
    public void follow(Long uId, Long id) {
        followerRepo.follow(uId,id);
    }

    @Override
    public int[] followCount(Long uId ) {
        return followerRepo.followCount(uId );
    }

    @Override
    public boolean followCheck(Long uId,Long id) {
       return followerRepo.followCheck(uId,id);
    }

    @Override
    public void cancelFollow(Long uId, Long id) {
        followerRepo.cancelFollow(uId,id);
    }

    @Override
    public List<User> findFollowings(Long id) {
        return followerRepo.getFollowingList(id);
    }
}
