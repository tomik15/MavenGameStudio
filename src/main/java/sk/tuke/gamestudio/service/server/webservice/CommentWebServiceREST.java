package sk.tuke.gamestudio.service.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.List;

@RestController ///instancie triedy ako komponenty REST sluzby
@RequestMapping("/api/comment")
public class CommentWebServiceREST {

    @Autowired
    private CommentService commentService;

    //localhost:8080/api/comment/

    @PostMapping
    void addScore(@RequestBody Comment comment){
        commentService.addComment(comment);

    }

    @GetMapping("/{game}")
    List<Comment> getComments(@PathVariable String game){
        return commentService.getComments(game);
    }
}
