package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yohi1.models.User;
import yohi1.service.UserService;

@Controller
@RequestMapping("/set")
@RequiredArgsConstructor
public class SettingController {
    private final UserService userService;

    @GetMapping("/setting/{id}")
    public String setting(@PathVariable("id") Long id, Model model) {
        User user1 = userService.findById(id);
        model.addAttribute("user", user1);
        return "posts/setting";

    }
}
