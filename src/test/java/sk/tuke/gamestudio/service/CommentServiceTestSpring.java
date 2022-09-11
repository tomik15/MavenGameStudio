package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Date;

import static org.junit.Assert.assertEquals;

//import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class CommentServiceTestSpring {
//    private final CommentService commentService=new CommentServiceJdbc();
    @Autowired
    CommentService commentService;

    @org.junit.Test
    public void testCommentreset(){
        commentService.addComment(new Comment("mines","tomas","the game was incredible",new Date()));
        commentService.reset();
        //  assertEquals(0,commentService.getComments("mines").size());
        assertEquals(0,commentService.getComments("mines").size());
     //   assertEquals()
    }

    @org.junit.Test
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
    @org.junit.Test
    public void testGetComments() throws InterruptedException {
        var date=new Date();
        Thread.sleep(5000);
        var date2=new Date();
        Thread.sleep(1000);
        var date3=new Date();
        Thread.sleep(1000);
        var date4=new Date();
        Thread.sleep(1000);
        var date5=new Date();
        Thread.sleep(1000);
        var date6=new Date();
        Thread.sleep(1000);
        var date7=new Date();
        commentService.reset();
        commentService.addComment(new Comment("mines","robert","great game",date));
        commentService.addComment(new Comment("mines","jozef","broken game",date3));
        commentService.addComment(new Comment("mines","tomas"," poor game",date2));
        commentService.addComment(new Comment("mines","janka","test game",date5));
        commentService.addComment(new Comment("mines","dano","excelent implementied",date4));
        commentService.addComment(new Comment("mines","peter","new game",date6));
        commentService.addComment(new Comment("mines","frantisek","best game",date7));

        var comments=commentService.getComments("mines");
        assertEquals(7,comments.size());
        assertEquals(7,commentService.getComments("mines").size());

//        for (Comment comment : comments) {
//            System.out.println(comment);
//        }
//
        assertEquals("mines",comments.get(0).getGame());
        assertEquals("frantisek",comments.get(0).getUsername());
        assertEquals("best game",comments.get(0).getCommet());
        assertEquals(date7,comments.get(0).getCommented_at());

        assertEquals("mines",comments.get(1).getGame());
        assertEquals("peter",comments.get(1).getUsername());
        assertEquals("new game",comments.get(1).getCommet());
        assertEquals(date6,comments.get(1).getCommented_at());

        assertEquals("mines",comments.get(2).getGame());
        assertEquals("janka",comments.get(2).getUsername());
        assertEquals("test game",comments.get(2).getCommet());
        assertEquals(date5,comments.get(2).getCommented_at());


    }
}
