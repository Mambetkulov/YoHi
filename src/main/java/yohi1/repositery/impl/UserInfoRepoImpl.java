package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.User;
import yohi1.models.UserInfo;
import yohi1.repositery.UserInfoRepo;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserInfoRepoImpl implements UserInfoRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void update(UserInfo userInfo) {
        entityManager.merge(userInfo);
    }

    @Override
    public UserInfo findById(Long id) {
       return entityManager.find(UserInfo.class, id);
    }

    @Override
    public void change(UserInfo info,Long id) {
       User user = entityManager.find(User.class, id);
       UserInfo userInfo = user.getUserInfo();
       userInfo.setImage(info.getImage());
        entityManager.merge(userInfo);
    }

    @Override
    public void deleteImage(Long id) {
        User user = entityManager.find(User.class, id);
        UserInfo userInfo = user.getUserInfo();
        userInfo.setImage("https://i.pinimg.com/736x/f2/01/1b/f2011bfb4e87a2e5219bd4c2fb02a5e9.jpg");
        entityManager.merge(userInfo);
    }
}
