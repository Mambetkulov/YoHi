package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Image;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.repositery.PostRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepoImpl implements PostRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Post> getAllPost() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public Post getById(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void save(Post post, User user) {
        post.setUser(user);
        entityManager.persist(post);
    }

    @Override
    public void update(User user, Post post, String imageUrl) {
        post.getImage().setImageURL(imageUrl);

    }

    @Override
    public void delete(User user, Post post) {
        Image image = post.getImage();
        image.setPost(null);
        if(image.getUser() == null){
          entityManager.createQuery("delete from Image i where i.id = :id").setParameter("id", image.getId());
        }
        user.getPosts().remove(post);
        entityManager.remove(post);
    }
}
