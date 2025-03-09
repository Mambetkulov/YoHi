package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yohi1.models.User;
import yohi1.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Transactional
public class UserController {
    private final UserService userService;


    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute("user") User user, @ModelAttribute("url")String url, Model model) {
        userService.update(user,url,id);
        User user1 = userService.findById(id);
        return "redirect:/auth/getProfile/" + user1.getId();
    }

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long userId) {
        User foundUser = userService.findById(userId);
        userService.delete(foundUser);
        return"redirect:/auth/signIn";
    }

    @GetMapping("/find/{id}")
    public String find(@PathVariable("id")Long uId,@RequestParam("query") String textQ, Model model) {
        List<User> users = userService.findByUsername(textQ);
        System.err.println(textQ + "ha");
        model.addAttribute("users",users);
        model.addAttribute("userId",uId);
        return"/user/search";
    }
}
