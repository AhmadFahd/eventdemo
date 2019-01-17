package com.ahmadfahd.Services;

import com.ahmadfahd.dto.CommentsDTO;
import com.ahmadfahd.entity.CommentsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    void AddComment(CommentsDTO commentsDTO, Long eventid, Long userid);
    List<CommentsDTO> findTheComments(Long eid);

    }
