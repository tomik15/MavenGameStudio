package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {
    private final CommentService commentService=new CommentServiceJdbc();

    @Test
    public void testCommentreset(){
        commentService.addComment(new Comment("mines","tomas","the game was incredible",new Date()));
        commentService.reset();
      //  assertEquals(0,commentService.getComments("mines").size());
        assertEquals(0,commentService.getComments("mines").size());
    }

    @Test
    public void addComment(){
        commentService.reset();
        var date=new Date();
        final String gameName="mines";

        commentService.addComment(new Comment(gameName,"Jaro","great game",date));
        var comments=commentService.getComments(gameName);
        assertEquals(1,comments.size());
        assertEquals(gameName,comments.get(0).getGame());
        assertEquals("Jaro",comments.get(0).getUsername());
        assertEquals("great game",comments.get(0).getCommet());
        assertEquals(date,comments.get(0).getCommented_at());
    }
    @Test
    public void testGetComments(){
        var date=new Date();
        commentService.reset();
        commentService.addComment(new Comment("mines","robert","great game",date));
        commentService.addComment(new Comment("mines","tomas"," poor game",date));
        commentService.addComment(new Comment("mines","jozef","broken game",date));
        commentService.addComment(new Comment("mines","dano","excelent implementied",date));
        commentService.addComment(new Comment("mines","janka","test game",date));
        commentService.addComment(new Comment("mines","peter","new game",date));
        commentService.addComment(new Comment("mines","frantisek","best game",date));

        var comments=commentService.getComments("mines");
        assertEquals(7,comments.size());
        assertEquals(7,commentService.getComments("mines").size());

//        for (Comment comment : comments) {
//            System.out.println(comment);
//        }
//
//        assertEquals("mines",comments.get(0).getGame());
//        assertEquals("robert",comments.get(0).getUsername());
//        assertEquals("great game",comments.get(2).getCommet());
//        assertEquals(date,comments.get(0).getCommented_at());
//
//        assertEquals("mines",comments.get(1).getGame());
//        assertEquals("robert",comments.get(1).getUsername());
//        assertEquals(120,comments.get(1).getPoints());
//        assertEquals(date,comments.get(1).getPlayedAt());
//
//        assertEquals("mines",comments.get(2).getGame());
//        assertEquals("peter",comments.get(2).getUsername());
//        assertEquals(100,comments.get(2).getPoints());
//        assertEquals(date,comments.get(2).getPlayedAt());
//
//        assertEquals("mines",comments.get(3).getGame());
//        assertEquals("janka",comments.get(3).getUsername());
//        assertEquals(70,comments.get(3).getPoints());
//        assertEquals(date,comments.get(3).getPlayedAt());
//
//        assertEquals("mines",comments.get(4).getGame());
//        assertEquals("dano",comments.get(4).getUsername());
//        assertEquals(60,comments.get(4).getPoints());
//        assertEquals(date,comments.get(4).getPlayedAt());

    }
}
