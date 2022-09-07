package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment); //- pridanie nového komentára

    List<Comment> getComments(String game); //- všetky komentáre k hre, od najnovších po najstaršie (podľa commented_at)

    void reset(); //- vymazanie tabuľky comment
}

