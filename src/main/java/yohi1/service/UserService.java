package yohi1.service;

import yohi1.models.User;

import java.util.List;

public interface UserService {

    String save(User user,boolean isNew);

    User authenticate(String email, String password);

    User findById(Long id);

    String update(User user , String url,Long id );

    String delete(User user);

    List<User> findAll();

    List<User> findByUsername(String name);

    List<User> findAllFollowers(User user1);


}
