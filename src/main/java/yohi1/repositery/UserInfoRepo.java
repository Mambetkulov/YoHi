package yohi1.repositery;

import yohi1.models.UserInfo;

public interface UserInfoRepo {

     void update(UserInfo userInfo);

    UserInfo findById(Long id);


    void change(UserInfo info,Long id);

    void deleteImage(Long id);
}
