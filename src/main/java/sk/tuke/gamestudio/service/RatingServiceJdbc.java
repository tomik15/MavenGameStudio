package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.exceptions.ServiceException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

//CREATE TABLE rating (
//        game VARCHAR(64) NOT NULL,
//        username VARCHAR(64) NOT NULL,
//        rating INTEGER NOT NULL CHECK(rating BETWEEN 1 and 5),
//        rated_at TIMESTAMP NOT NULL,
//        CONSTRAINT game_username UNIQUE (game,username)
//
//        );
public class RatingServiceJdbc implements RatingService{
    private static final String JDBC_URL = "jdbc:postgresql://localhost/GameStudio";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";

    @Override
    public void setRating(sk.tuke.gamestudio.entity.Rating rating) {
        final String STATEMENT_SET_RATING = "INSERT INTO  rating VALUES (?, ?, ?, ?)";
        final String STATEMENT_GET_RATING = "SELECT rating FROM rating WHERE game= ? and username= ? ";
        final String STATEMENT_UPDATE = "UPDATE rating SET rating=?,rated_at=? WHERE game= ? and username= ? ";

        try(
                var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
                var statement = connection.prepareStatement(STATEMENT_SET_RATING);
                var stamenntSelect=connection.prepareStatement(STATEMENT_GET_RATING);
                var statemntUpdate=connection.prepareStatement(STATEMENT_UPDATE);
        )
        {
            stamenntSelect.setString(1,rating.getGame());
            stamenntSelect.setString(2,rating.getUsername());
            try(var rs = stamenntSelect.executeQuery()){
                int ratingint = 0;
                while(rs.next()) {
                 ratingint=rs.getInt(1);
                }
                if(ratingint!=0){
                    statemntUpdate.setInt(1,rating.getRating());
                    statemntUpdate.setTimestamp(2,new Timestamp(rating.getRated_at().getTime()));
                    statemntUpdate.setString(3,rating.getGame());
                    statemntUpdate.setString(4,rating.getUsername());
                    statemntUpdate.executeUpdate();
                }
                else {
                    statement.setString(1, rating.getGame());
                    statement.setString(2, rating.getUsername());
                    statement.setInt(3, rating.getRating());
                    statement.setTimestamp(4,  new Timestamp(rating.getRated_at().getTime()));
                    statement.executeUpdate();
                }
            }
        }catch(SQLException e){
            throw  new ServiceException(e);
        }

    }

    @Override
    public int getAverageRating(String game) {
        final String STATEMENT_GET_RATING = "SELECT AVG(rating) FROM rating WHERE game= ?";
        try( var connection =DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.prepareStatement(STATEMENT_GET_RATING)
        )
        {
            statement.setString(1,game);
            try(var rs = statement.executeQuery()){
                int rating = 0;
                while(rs.next()) {
                     rating = rs.getInt(1);
                }
                if(rating!=0){
                    return rating;
                }
                else {
                    return 0;
                }
            }
        }catch (SQLException e) {
            throw  new ServiceException(e);

        }
    }

    @Override
    public int getRating(String game, String username) {
        final String STATEMENT_GET_RATING = "SELECT rating FROM rating WHERE game= ? and username= ? ";
        try( var connection =DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.prepareStatement(STATEMENT_GET_RATING)
        )
        {
            statement.setString(1,game);
            statement.setString(2,username);
            try(var rs = statement.executeQuery()){
                int rating = 0;
                while(rs.next()) {
                    rating = rs.getInt(1);
                }
               if(rating!=0){
                   return rating;
               }
               else {
                   return 0;
               }
            }
        }catch (SQLException e) {
            throw  new ServiceException(e);

        }
    }

    @Override
    public void reset() {
        final String STATEMENT_RESET = "DELETE FROM rating";
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
