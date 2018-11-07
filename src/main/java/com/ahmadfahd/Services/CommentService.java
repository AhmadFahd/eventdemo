package com.ahmadfahd.Services;

import com.ahmadfahd.dto.CommentsDTO;
import com.ahmadfahd.entity.CommentsEntity;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity AddComment(CommentsDTO commentsDTO, Long eventid, Long userid);
}
