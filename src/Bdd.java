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
}
