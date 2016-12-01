import java.sql.*;

/**
 * Created by Pascale on 30/11/2016.
 */
public class Bdd {
    private volatile static Bdd bdd;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement statement;

    private Bdd() throws SQLException {
        String url = "jdbc:mysql://localhost/pendu";
        String user = "root";
        String passwd = "";
        System.out.println("connexion réussie");


         conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion à la bdd réussie");


    }


    public static Bdd getMySql() throws SQLException {
        if (bdd == null) {
            bdd = new Bdd();
        }
        return bdd;
    }

    public String getMot(int id) throws SQLException {
        statement = conn.prepareStatement("SELECT mot FROM Dictionnaire WHERE id=?");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        String mot = rs.getString(1);
        rs.close();
        statement.close();
        return mot;
    }

    public int getNbMot() throws SQLException {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM dictionnaire");
        ResultSetMetaData resultMeta = rs.getMetaData();
        rs.first();
        int nbMots = rs.getInt(1);
        rs.close();
        stmt.close();
        return nbMots;
    }


}