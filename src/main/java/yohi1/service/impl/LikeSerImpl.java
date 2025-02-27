package yohi1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yohi1.repositery.LikeRepo;
import yohi1.service.LikeService;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeSerImpl implements LikeService {
    private final LikeRepo likeRepo;



    @Override
    public boolean likeIt(Long id, Long postId) {
        if(likeRepo.likeCheck(id, postId)){
            return likeRepo.likeIt(id,postId);
        }else{
           return likeRepo.cancelLike(id,postId);
        }
    }

    @Override
    public void likeCount(Long postId) {
        likeRepo.likeCount(postId);
    }
}
