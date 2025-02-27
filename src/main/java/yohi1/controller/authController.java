package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yohi1.models.Post;
import yohi1.models.User;
import yohi1.service.FollowerService;
import yohi1.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {
    private final UserService userService;
    private final FollowerService followerService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "auth/register";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, Model model) {
        String message = userService.save(user,true);
        model.addAttribute("message", message);
        return "redirect:/auth/getProfile/"+user.getId();
    }

    @GetMapping("/signIn")
    public String  signIn(Model model) {
        System.err.println("signIn");
        model.addAttribute("newUser",new User());
        return "user/sign-in";
    }


    @PostMapping("/saveSignIn")
    public String saveSignIn(@ModelAttribute("newUser") User user, Model model) {
        User us = userService.authenticate(user.getEmail(), user.getPassword());
             if(us != null){
                return "redirect:/auth/getProfile/" + us.getId();
            } else {
                model.addAttribute("message", "Invalid email or password");
            }
        return "redirect:/sign-in";
    }


    @GetMapping("/getProfile/{userId}")
    String getProfile(@PathVariable("userId") Long userId, Model model) {
        User user1 = userService.findById(userId);
        int[] numbers = followerService.followCount(userId);
        model.addAttribute("user",user1);
        model.addAttribute("array",numbers);
       return "/user/home-page";
    }
}
