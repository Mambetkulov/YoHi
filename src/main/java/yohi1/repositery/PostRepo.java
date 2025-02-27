package yohi1.repositery;

import yohi1.models.Image;
import yohi1.models.Post;
import yohi1.models.User;

import java.util.List;

public interface PostRepo {


    List<Post> getAllPost();

    Post getById(Long id);

    void save(Post post, User user);

    void update(User user, Post post, String imageUrl);

    void delete(User user, Post post);
}
