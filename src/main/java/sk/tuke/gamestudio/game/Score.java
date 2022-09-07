package sk.tuke.gamestudio.game;

import java.sql.*;
import java.util.ArrayList;

public class Score {
    public void databaseConnectionSave(Timestamp timestamp,String databaseName) throws SQLException {
        try (var connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/"+databaseName, "postgres", "postgres");
             var statement = connection.createStatement();
             var prepareStamenet=connection.prepareStatement("INSERT INTO score (ident, meno, time) VALUES (?,?,?)");
        ) {

            Connection c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/"+databaseName, "postgres", "postgres");
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs=dbm.getTables(null,null,"score",null);
            if(!rs.next()){
                statement.executeUpdate("CREATE TABLE score ( \n" +
                        "  game VARCHAR(64) NOT NULL, \n" +
                        "  username VARCHAR(64) NOT NULL, \n" +
                        "  points INTEGER NOT NULL, \n" +
                        "  played_at TIMESTAMP NOT NULL \n" +
                        ");\n");
            }

            var tmp= statement.executeQuery("Select MAX(ident) from score");
            int id = 0;
            while(tmp.next()){
                id=tmp.getInt(1);
            }
            id+=1;

            prepareStamenet.setInt(1,id);
            prepareStamenet.setString(2,System.getProperty("user.name"));
            prepareStamenet.setTimestamp(3, timestamp);
            prepareStamenet.executeUpdate();


        }
    }

    public void ConnectLoad(String databaseName) throws SQLException {
        ArrayList arrayList=new ArrayList<>();
        try (var connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/"+databaseName, "postgres", "postgres");
             var statement = connection.createStatement())
        {
            var result=statement.executeQuery( "SELECT ident, meno, time FROM score Order by time LIMIT 10");

            while (result.next()){

                System.out.printf("%d %s %s\n",result.getInt(1),result.getString(2),result.getTimestamp(3));
            }

            printResultSet(result);

        }

    }

    private void printResultSet(ResultSet result) throws SQLException {
        while (result.next()){
            System.out.printf("%d %s %s\n",result.getInt(1),result.getString(2),result.getTimestamp(3));
        }
    }
}
