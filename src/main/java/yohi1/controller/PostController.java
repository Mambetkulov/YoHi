package yohi1.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yohi1.models.*;
import yohi1.service.CommentService;
import yohi1.service.LikeService;
import yohi1.service.PostService;
import yohi1.service.UserService;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    @PersistenceContext
    private final EntityManager entityManager;
    private Long copy;


    @GetMapping("/getAll/{id}")
    public String getAllCars(@PathVariable("id")Long id, Model model) {
        List<Post> posts = postService.getAllPost();
       for (Post post : posts) {
           System.err.println(  post.getLikes().size());
       }

        model.addAttribute("posts", posts);
        model.addAttribute("userid", id);
        model.addAttribute("comments", commentService.getAll());
        return "/posts/post";
    }

    @Transactional
    @GetMapping("/getPost/{id}/{own}")
    public String getPost(@PathVariable("id")Long id ,@PathVariable("own")Long ownId, Model model) {
        Post post = postService.getById(id);
        User user = userService.findById(ownId);


        if(post.getComments().size() == 0){
            Comment comment = new Comment();
            comment.setComment("hello");
            entityManager.persist(comment);
            post.getComments().add(comment);
            System.err.println(post.getComments().size());

        }

        copy = user.getId();
        model.addAttribute("post", post);
        model.addAttribute("owner", user);
        model.addAttribute("comments", post.getComments() );

        return "/posts/post2";
    }

    @GetMapping("/create-post-form/{id}")
        public String createPostForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("post", new Post());
        model.addAttribute("image", "");
        model.addAttribute("users", user);
        return "/posts/create-post";
        }


    @PostMapping("/save/{userId}")
    public String savePost(@PathVariable("userId") Long userId,
                           @ModelAttribute Post post,
                           @RequestParam("imageUrl") String imageUrl) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }

        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Image image = new Image();
        image.setImageURL(imageUrl);
        image.setPost(post);
        post.setImage(image);

        postService.save(post, user);

        return "redirect:/auth/getProfile/" + user.getId();
    }

    @GetMapping("/post-update-form/{postId}/{userId}")
    String updateForm(@PathVariable("postId")Long id,@PathVariable("userId")Long id2, Model model){
        Post byId = postService.getById(id);
        model.addAttribute("post", byId);
        model.addAttribute("userId", id2);
        return "user/update-user-info";
    }

    @GetMapping("/update-post")
   String updatePost(@PathVariable("userId") Long userId,
                     @ModelAttribute Post post,
                     @RequestParam("imageUrl") String imageUrl,Model model) {
        User user = userService.findById(userId);
        postService.update(user,post,imageUrl);
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        return "redirect:/posts/post2/";
    }

    @GetMapping("/delete-post/{postId}/{userId}")
    String deletePost(@PathVariable("userId") Long userId,@PathVariable("postId")Long postId, Model model) {
        User user = userService.findById(userId);
        Post post = postService.getById(postId);
        postService.delete(user,post);
         return"redirect:/posts/getAll/"+ user.getId();
    }

    @GetMapping("/getCounts/{postId}")
    @ResponseBody
    public Map<String, Object> getCounts(@PathVariable("postId") Long postId) {
       Post post = postService.getById(postId);// Получаем список постов
        Map<String, Object> response = new HashMap<>();
        response.put("likes", post.getLikes());
        response.put("comments", post.getComments());

        return response;
    }

}
