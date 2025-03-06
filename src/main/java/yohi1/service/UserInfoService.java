package yohi1.service;

import yohi1.models.UserInfo;

public interface UserInfoService {



    UserInfo getDefaultValue();

    String update(UserInfo userInfo);

    UserInfo findById(Long id);


    String changeImage(String url,Long id);

    String deleteImage(Long id);
}
