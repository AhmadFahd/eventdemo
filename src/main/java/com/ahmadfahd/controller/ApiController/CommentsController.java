package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.Services.CommentService;
import com.ahmadfahd.dto.CommentsDTO;
import com.ahmadfahd.entity.CommentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController
{
    @Autowired
    private CommentService commentService;


    @PostMapping("/add/{eventid}/{userid}")
    public ResponseEntity AddComment(@RequestBody CommentsDTO commentsDTO, @PathVariable Long eventid, @PathVariable Long userid)
    {
        return commentService.AddComment(commentsDTO,eventid,userid);
    }
}
