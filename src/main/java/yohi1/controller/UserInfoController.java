package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yohi1.models.User;
import yohi1.models.UserInfo;
import yohi1.service.UserInfoService;
import yohi1.service.UserService;

@Controller
@RequestMapping("/usersInfo")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserService userService;
    private Long idCopy;





    @GetMapping("/getUserInfo/{userId}")
    String getUserInfo(@PathVariable("userId")Long id, Model model){
        User user = userService.findById(id);
        idCopy = id;
        model.addAttribute("user", user);
        return "user/user-info";
    }

    @GetMapping("/update-info")
    String updateUserInfo(@ModelAttribute("userInfo") UserInfo user, Model model){
        userInfoService.update(user);
        model.addAttribute("userInfo", user);
        return "redirect:/set/setting/" + idCopy;
    }

    @GetMapping("/change-image-form/{id}")
    String changeImageForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/change-image" ;
    }


    @GetMapping("/changeImage")
    String changeImage(@RequestParam("image") String url, @RequestParam("id") Long userId){
        userInfoService.changeImage(url,userId);
        return "redirect:/auth/getProfile/"+ userId;
    }

    @GetMapping("/deleteImage")
    String deleteImage(){
        userInfoService.deleteImage(idCopy);
        return "redirect:/auth/getProfile/"+ idCopy;
    }
}
