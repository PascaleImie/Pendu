package Utilitaires;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pierre on 30/11/2016.
 */
public class Bdd {

    private volatile static Bdd mysqlExecute;
    private Connection conn;

    private Bdd() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/pendu";
        String user = "root";
        String passwd = "";

        conn = (Connection) DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion à la base de donnée réussie");
    }

    public static synchronized Bdd getBdd() throws SQLException, ClassNotFoundException {
        if (mysqlExecute == null) {
            mysqlExecute = new Bdd();
        }
        return mysqlExecute;
    }

    public String getMot(int id) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT mot FROM dictionnaire WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.setMaxRows(1);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        String mot = resultSet.getString("mot");
        preparedStatement.close();
        return mot;
    }

    public int getNbMots() throws SQLException {
        ResultSet result = conn.createStatement().executeQuery("SELECT COUNT(*) FROM dictionnaire");
        ResultSetMetaData resultMetaData = (ResultSetMetaData) result.getMetaData();
        result.first();
        int nbMots = result.getInt(1);
        result . close () ;
        return nbMots;
    }

    public int getBestScore(String hostAddress) throws SQLException {
        ResultSet result = conn.createStatement().executeQuery("SELECT score FROM bestscore WHERE ip = '"+ hostAddress + "'");
        result.first();
        int bestScore = result.getInt(1);
        result.close () ;
        return bestScore;
    }

    public int[] getTenBestsScores() throws SQLException {
        int [] bestsScores = new int[5];
        int i = 0;
        ResultSet result = conn.createStatement().executeQuery("SELECT score FROM bestscore ORDER BY score DESC LIMIT 5");
        ResultSetMetaData resultMetaData = (ResultSetMetaData) result.getMetaData();

        while(result.next()){
            bestsScores[i]=result.getInt("score");
            i++;
        }
        result.close();

        return bestsScores;
    }

    public void insertPlayerScore(String ip, int score) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO bestscore VALUES ( ?, ? )");
        preparedStatement.setString(1, ip);
        preparedStatement.setInt(2, score);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void setBestScore(String ip, int score) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE bestscore SET score = ? WHERE ip = ? ");
        preparedStatement.setInt(1, score);
        preparedStatement.setString(2, ip);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
