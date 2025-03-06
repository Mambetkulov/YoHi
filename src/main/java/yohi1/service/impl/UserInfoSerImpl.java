package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yohi1.enums.Gender;
import yohi1.models.UserInfo;
import yohi1.repositery.UserInfoRepo;
import yohi1.service.UserInfoService;

@Service
@RequiredArgsConstructor
public class UserInfoSerImpl implements UserInfoService {
    private final UserInfoRepo userInfoRepo;

    @Override
    public String update(UserInfo userInfo) {
        userInfoRepo.update(userInfo);
        return "successfully updated";
    }

    @Override
    public UserInfo findById(Long id) {
        return userInfoRepo.findById(id);
    }

    @Override
    public String changeImage(String url ,Long id) {
        userInfoRepo.change(url,id);
        return "successfully changed";

    }

    @Override
    public String deleteImage(Long id) {
        userInfoRepo.deleteImage(id);
        return "successfully deleted";
    }

    @Override
    public UserInfo getDefaultValue() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFullName("?");
        userInfo.setBiography("?");
        userInfo.setGender(Gender.MALE);
        userInfo.setImage("https://i.pinimg.com/736x/ce/65/5f/ce655f63e2068dd590aa19ec301a3c27.jpg");
        userInfoRepo.save(userInfo);
        return userInfo;
    }
}
