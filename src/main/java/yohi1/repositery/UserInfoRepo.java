package yohi1.repositery;

import yohi1.models.UserInfo;

public interface UserInfoRepo {

    void save(UserInfo userInfo);

     void update(UserInfo userInfo);

    UserInfo findById(Long id);


    void change(String url,Long id);

    void deleteImage(Long id);
}
