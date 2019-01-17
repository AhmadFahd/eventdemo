package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.FeedService;
import com.ahmadfahd.dto.FeedsDTO;
import com.ahmadfahd.dto.FollowDTO;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.FollowEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.FeedRepository;
import com.ahmadfahd.repository.FollowRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImp implements FeedService {
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<FeedsDTO> findUsersFeeds(Long id) {
        // FIXME: 1/3/2019
        List<FeedEntity> feedEntities = feedRepository.findByUserId(id);
        List<FeedsDTO> feedsDTOList = ObjectMapperUtils.mapAll(feedEntities, FeedsDTO.class);
        return feedsDTOList;
    }

    @Override
    public List<FeedsDTO> findMyFeeds(Long id) {
        List<FollowEntity> followEntities = followRepository.findByUserIdAndStatusTrue(id);
        List<Long> ids = new ArrayList<>();
        for (FollowEntity followEntity: followEntities) {
                ids.add(followEntity.getFollowed().getId());
        }
        List<FeedEntity> feedEntities = feedRepository.findByUserIdIn(ids);
        List<FeedsDTO> feedsDTOS = ObjectMapperUtils.mapAll(feedEntities,FeedsDTO.class);
    return feedsDTOS;
    }
}