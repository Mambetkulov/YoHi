package yohi1.service;

import yohi1.models.UserInfo;

public interface UserInfoService {

    String update(UserInfo userInfo);

    UserInfo findById(Long id);


    String changeImage(UserInfo info,Long id);

    String deleteImage(Long id);
}
