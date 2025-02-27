package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Follower;
import yohi1.models.User;
import yohi1.repositery.FollowerRepo;
import yohi1.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowerRepoImpl implements FollowerRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    private final UserService userService;

    @Override
    public void follow(Long uId, Long id) {
        User user = userService.findById(id);
        User followUser = userService.findById(uId);


        user.getFollower().getSubscribtion().add(uId);
        followUser.getFollower().getSubscribers().add(id);

        entityManager.merge(user);
        entityManager.merge(followUser);
    }


    @Override
    public int[] followCount(Long uId ) {
        int[] counts = new int[2];
        counts[0] = 0;
        counts[1] = 0;
        User followUser = userService.findById(uId);

        if (followUser.getFollower() == null) {
            followUser.setFollower(new Follower());
        }
        if (followUser.getFollower().getSubscribtion() == null) {
            followUser.getFollower().setSubscribtion(new ArrayList<>());
        }
        if(followUser.getFollower().getSubscribers() == null){
            followUser.getFollower().setSubscribers(new ArrayList<>());
        }

        if(followUser.getFollower().getSubscribtion().size() != 0){
            counts[0] = followUser.getFollower().getSubscribtion().size();
        }
        if(followUser.getFollower().getSubscribers().size() != 0){
            counts[1] = followUser.getFollower().getSubscribers().size();
        }
        return counts;
    }


    @Override
    public boolean followCheck(Long uId,Long id) {
        User user = userService.findById(id);
        User followUser = userService.findById(uId);

        if (followUser.getFollower() == null) {
            followUser.setFollower(new Follower());
        }
        if (followUser.getFollower().getSubscribers() == null) {
            followUser.getFollower().setSubscribers(new ArrayList<>());
        }

        if (user.getFollower() == null) {
            user.setFollower(new Follower());
        }
        if (user.getFollower().getSubscribtion() == null) {
            user.getFollower().setSubscribtion(new ArrayList<>());
        }

        for(Long i : followUser.getFollower().getSubscribers()){
            if(i == id){
                return false;
            }
        }
        return true;
    }

    @Override
    public void cancelFollow(Long uId, Long id) {
        User user = userService.findById(id);
        User followUser = userService.findById(uId);

        for(Long i : followUser.getFollower().getSubscribers()){
            if(i == id){
                followUser.getFollower().getSubscribers().remove(i);
                entityManager.merge(followUser);
                user.getFollower().getSubscribtion().remove(uId);
                entityManager.merge(user);
                break;
            }
        }
    }


    @Override
    public List<User> getFollowingList(Long id) {
        List<User> followings = new ArrayList<>();
        User user = userService.findById(id);
        for(Long id2 : user.getFollower().getSubscribtion()){
            User user2 = entityManager.find(User.class, id2);
            followings.add(user2);
        }
        return followings;
    }
}
