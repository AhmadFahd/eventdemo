package com.ahmadfahd.Services;

import com.ahmadfahd.entity.CommentsEntity;

public interface CommentService {

    void AddComment(CommentsEntity commentsEntity, Long eventid, Long userid);
}
