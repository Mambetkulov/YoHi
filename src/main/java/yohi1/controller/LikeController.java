package yohi1.controller;

import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yohi1.models.User;
import yohi1.service.LikeService;

import java.util.HashMap;

@Controller
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


  @GetMapping("/like/{id}/{postId}")
    public String like(@PathVariable("id") Long id,@PathVariable("postId") Long postId) {
      likeService.likeIt(id,postId);
      return "redirect:/posts/getAll/" + id;
  }

}
