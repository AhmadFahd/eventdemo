package com.ahmadfahd.Services;

import com.ahmadfahd.dto.FeedsDTO;
import com.ahmadfahd.dto.FollowDTO;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.FollowEntity;

import java.util.List;

public interface FeedService {

    List<FeedsDTO> findUsersFeeds(Long id);
    List<FeedsDTO> findMyFeeds(Long id);
}
