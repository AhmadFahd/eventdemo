package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.FollowService;
import com.ahmadfahd.dto.FollowDTO;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.UserGetDto;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.FollowEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.FeedRepository;
import com.ahmadfahd.repository.FollowRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImp implements FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FeedRepository feedRepository;

    @Override
    public void follow(Long uid, Long fid) {
        FollowEntity followEntity0 = followRepository.findByUserIdAndFollowedId(uid, fid);
        if (followEntity0 != null) {
            followEntity0.setStatus(true);
            followRepository.save(followEntity0);
        } else {
            FollowEntity followEntity = new FollowEntity();
            followEntity.setUser(usersRepository.findById(uid).get());
            followEntity.setFollowed(usersRepository.findById(fid).get());
            followEntity.setStatus(true);
            FeedEntity feedEntity = new FeedEntity();
            feedEntity.setUser(usersRepository.findById(uid).get());
            feedEntity.setAction(ACTIONS.FOLLOW.toString());
            feedEntity.setTime(LocalDateTime.now());
            followRepository.save(followEntity);
            feedEntity.setTargetUser(usersRepository.findById(fid).get());
            feedRepository.save(feedEntity);

        }
    }

    @Override
    public List<FollowDTO> myFollowing(Long uid) {
        List<FollowEntity> followEntityList = followRepository.findByUserIdAndStatusTrue(uid);
        List<FollowDTO> followDTOList = ObjectMapperUtils.mapAll(followEntityList, FollowDTO.class);
        return followDTOList;
    }

    @Override
    public List<FollowDTO> myFollowers(Long fid) {
        List<FollowEntity> followEntityList = followRepository.findByFollowedIdAndStatusTrue(fid);
        List<FollowDTO> followDTOList = ObjectMapperUtils.mapAll(followEntityList, FollowDTO.class);
        return followDTOList;
    }

    public void unFollow(Long uid, Long fid) {
        FollowEntity followEntity = followRepository.findByUserIdAndFollowedId(uid, fid);
        followEntity.setStatus(false);
        followRepository.save(followEntity);
    }

    @Override
    public boolean isFollowed(Long uid, Long fid) {
        return followRepository.existsByUserIdAndFollowedIdAndStatusTrue(uid, fid);
    }

    @Override
    public List<UserGetDto> myFollowingList(Long uid) {
        List<FollowEntity> followEntityList = followRepository.findByUserIdAndStatusTrue(uid);
        List<Long> ids = new ArrayList<>();
        for (FollowEntity followEntity : followEntityList) {
            ids.add(followEntity.getFollowed().getId());
        }
        List<UsersEntity> usersEntities= usersRepository.findByIdIn(ids);
        List<UserGetDto> userGetDtos = ObjectMapperUtils.mapAll(usersEntities,UserGetDto.class);
        return userGetDtos;
    }
}
