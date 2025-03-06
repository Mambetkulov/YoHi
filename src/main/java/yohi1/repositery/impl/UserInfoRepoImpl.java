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
    public void change(String url,Long id) {
       User user = entityManager.find(User.class, id);
       UserInfo userInfo = user.getUserInfo();
       userInfo.setImage(url);
       entityManager.merge(userInfo);
    }

    @Override
    public void deleteImage(Long id) {
        User user = entityManager.find(User.class, id);
        UserInfo userInfo = user.getUserInfo();
        userInfo.setImage("https://i.pinimg.com/736x/ce/65/5f/ce655f63e2068dd590aa19ec301a3c27.jpg");
        entityManager.merge(userInfo);
    }

    @Override
    public void save(UserInfo userInfo) {
        entityManager.persist(userInfo);
    }
}
