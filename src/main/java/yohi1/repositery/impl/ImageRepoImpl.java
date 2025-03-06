package yohi1.repositery.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yohi1.models.Image;
import yohi1.models.UserInfo;
import yohi1.repositery.ImageRepo;

@Repository
@Transactional
@RequiredArgsConstructor
public class ImageRepoImpl implements ImageRepo {
    private final EntityManager em;

    @Override
    public Image save(Image image) {
        image.setImageURL("https://www.solidbackgrounds.com/images/1920x1080/1920x1080-vivid-sky-blue-solid-color-background.jpg");
        em.persist(image);
        return image;
    }
}
