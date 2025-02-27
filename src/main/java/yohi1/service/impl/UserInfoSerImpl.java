package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public String changeImage(UserInfo info ,Long id) {
        userInfoRepo.change(info,id);
        return "successfully changed";

    }

    @Override
    public String deleteImage(Long id) {
        userInfoRepo.deleteImage(id);
        return "successfully deleted";
    }
}
