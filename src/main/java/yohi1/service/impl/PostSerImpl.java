package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Image;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.repositery.PostRepo;
import yohi1.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSerImpl implements PostService {
    private final PostRepo repo;

    @Transactional
    @Override
    public List<Post> getAllPost() {
        List<Post>  posts = repo.getAllPost();
        for (Post post : posts) {
            if(post.getLikes() == null){
                post.setLikes(new ArrayList<>());
            }
        }
        return posts;
    }

    @Override
    public Post getById(Long id) {
        return repo.getById(id);
    }

    @Override
    public void save(Post post, User user) {
        repo.save( post, user);
    }

    @Override
    public void update(User user, Post post, String imageUrl) {
        repo.update(user,post,imageUrl);
    }

    @Override
    public void delete(User user, Post post) {
        repo.delete(user,post);
    }
}
