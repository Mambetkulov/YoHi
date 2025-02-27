package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yohi1.models.Comment;
import yohi1.models.User;
import yohi1.service.CommentService;
import yohi1.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;



    @PostMapping("/add/{postId}/{userId}")
    public String addComment(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId, @RequestParam("text") String text) {
        User user = userService.findById(userId);
        commentService.addComment(postId, text, user);
        return "redirect:/posts/getPost/" + postId + "/" + userId;
    }

    @PostMapping("/delete/{postId}/{commentId}/{userId}")
    public String deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        commentService.deleteComment(commentId, postId, userId);
        return "redirect:/posts/getPost/" + postId + "/" + userId;
    }

}
