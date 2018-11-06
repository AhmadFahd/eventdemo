package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.Services.CommentService;
import com.ahmadfahd.entity.CommentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController
{
    @Autowired
    private CommentService commentService;


    @PostMapping("/add/{eventid}/{userid}")
    public void AddComment(@RequestBody CommentsEntity commentsEntity, @PathVariable Long eventid, @PathVariable Long userid)
    {
        commentService.AddComment(commentsEntity,eventid,userid);
    }
}
