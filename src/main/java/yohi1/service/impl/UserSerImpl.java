package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Image;
import yohi1.models.User;
import yohi1.repositery.ImageRepo;
import yohi1.repositery.UserRepo;
import yohi1.service.UserInfoService;
import yohi1.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSerImpl implements UserService {
    private final UserRepo userRepo;
    private final UserInfoService userInfoService;
    private final ImageRepo imageRepo;

    @Override
    public String save(User user, boolean isNew) {
        boolean exist = userRepo.existEmail(user.getEmail());
        if (exist && isNew) {
            return "User with this email already exist";
        }
            user.setUserInfo(userInfoService.getDefaultValue());
            Image image = new Image();
            image.setUser(user);
            imageRepo.save(image);
            user.setImage(image);
            userRepo.save(user);
            return "User saved successfully";
    }

    @Override
    public User authenticate(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User findById(Long id) {
      return   userRepo.findById(id);
    }

    @Override
    public String update(User user , String url,Long id) {
        userRepo.update(user,url,id);
        return "successfully updated";
    }

    @Override
    public String delete(User user) {
        userRepo.delete(user);
        return "successfully deleted";
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findByUsername(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public List<User> findAllFollowers(User user1) {
        return userRepo.getAllFollowers(user1);
    }
}
