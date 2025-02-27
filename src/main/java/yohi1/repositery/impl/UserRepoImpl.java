package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Image;
import yohi1.models.User;
import yohi1.repositery.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepoImpl implements UserRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean existEmail(String email) {
        return entityManager.createQuery("select count(u) > 0 from User u where u.email = :email",Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }


    @Override
    public User findByEmail(String email) {
        try {
            return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new RuntimeException("email not found");
        }
    }


    @Override
    public User findById(Long userId) {
        try {
            return entityManager.createQuery("select u from User u where u.id = :userId",User.class)
                    .setParameter("userId",userId).getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("id not found");
        }
    }

    @Override
    public void update(User user,String url,Long id) {
        User user1 = entityManager.find(User.class, id);
        if(user1.getImage() == null) {
            user1.setImage(new Image());

        }
        user1.getImage().setImageURL(url);
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        entityManager.merge(user1);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<User> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(:username)",
                        User.class
                )
                .setParameter("username", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<User> getAllFollowers(User user1) {
        List<User> followers = new ArrayList<>();
        for(Long id : user1.getFollower().getSubscribers()){
            User user = entityManager.find(User.class, id);
            followers.add(user);
        }
        return followers;
    }
}
