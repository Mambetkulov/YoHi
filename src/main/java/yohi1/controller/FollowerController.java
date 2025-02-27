package yohi1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yohi1.models.User;
import yohi1.service.FollowerService;
import yohi1.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/followers")
@RequiredArgsConstructor
public class FollowerController {
    private final UserService userService;
    private final FollowerService followerService;

    @GetMapping("/getAll/{id}")
    public String getAllCars( @PathVariable("id")Long id, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users );
        model.addAttribute("id",id);
        return "/follower/all-users";
    }


    @GetMapping("/UserProfile/{id}/{owner}")
    public String getOtherUserProfile(@PathVariable("id")Long uId,@PathVariable("owner")Long id, Model model) {
        prepareUserProfileModel(uId, id, model);
        return"follower/user-profile";
    }

    @GetMapping ("/follow/{id}/{owner}")
    public String follow(@PathVariable("id")Long uId ,@PathVariable("owner")Long id,Model model) {

        if(followerService.followCheck(uId, id)){
            followerService.follow(uId,id);
        }else{
            followerService.cancelFollow(uId,id);
        }
        prepareUserProfileModel(uId, id, model);
        return "follower/user-profile";

    }

    @GetMapping("/followers-list/{id}")
    public String getFollowersList(@PathVariable("id")Long id, Model model) {
        User user1 = userService.findById(id);
        List<User> users = userService.findAllFollowers(user1);
        System.err.println("User: " + user1);
        System.err.println("Followers: " + users);


        model.addAttribute("user", user1);
        model.addAttribute("follow",users);
        return "follower/follower-list";
    }

    @GetMapping("/following-list/{id}")
    public String getFollowingList(@PathVariable("id")Long id, Model model) {
        List<User> users = followerService.findFollowings(id);
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("followings",users);
        return "follower/following-list";
    }




    private void prepareUserProfileModel(Long uId, Long id, Model model) {
        User user = userService.findById(uId);
        User owner = userService.findById(id);
        int[] numbers = followerService.followCount(uId);
        boolean result = followerService.followCheck(uId, id);

        model.addAttribute("user", user);
        model.addAttribute("own", owner);
        model.addAttribute("array", numbers);
        model.addAttribute("isFollowing", result);
    }


}
