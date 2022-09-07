package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.exceptions.ServiceException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJdbc implements CommentService {
    private static final String JDBC_URL = "jdbc:postgresql://localhost/GameStudio";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";

    @Override
    public void addComment(Comment comment) {
        final String STATEMENT_ADD_SCORE = "INSERT INTO  comment VALUES (?, ?, ?, ?)";

        try(
                var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
                var statement = connection.prepareStatement(STATEMENT_ADD_SCORE);
        )
        {
            statement.setString(1, comment.getGame());
            statement.setString(2, comment.getUsername());
            statement.setString(3, comment.getCommet());
            statement.setTimestamp(4,  new Timestamp(comment.getCommented_at().getTime()));
            statement.executeUpdate();
        }catch(SQLException e){
            throw  new ServiceException(e);
        }

    }

    @Override
    public List<Comment> getComments(String game) {
        final String STATEMENT_BEST_SCORES = "SELECT game, username, comment, commented_at FROM comment WHERE game= ? ORDER BY commented_at DESC";

        try( var connection =DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.prepareStatement(STATEMENT_BEST_SCORES)
        )
        {
            statement.setString(1,game);
            try(var rs = statement.executeQuery()){
                var comments= new ArrayList<Comment>();
                while(rs.next()) {
                    comments.add(new Comment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4)));
                }
                return comments;
            }
        }catch (SQLException e) {
            throw  new ServiceException(e);

        }

    }

    @Override
    public void reset() {
        final String STATEMENT_RESET = "DELETE FROM comment";
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.createStatement()
        )
        {
            statement.executeUpdate(STATEMENT_RESET);
        }catch(SQLException e){
            throw  new ServiceException(e);
        }
    }
}
