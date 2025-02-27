package yohi1.repositery;

import yohi1.models.User;

import java.util.List;

public interface UserRepo {

    void save(User user);

    boolean existEmail(String email);

    User findByEmail(String email);

    User findById(Long userId);

    void update(User user,String url,Long id);

    void delete(User user);

    List<User> findAll();

    List<User> findByName(String name);

    List<User> getAllFollowers(User user1);
}
