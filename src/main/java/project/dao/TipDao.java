package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import project.database.Database;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class TipDao {

    private Connection conn;

    public TipDao(Database db) throws SQLException {
        this.conn = db.getConnection();
    }

    public List<Tip> listAll() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Tip");

        List<Tip> tips = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String author = result.getString("author");
            String description = result.getString("description");
            String url = result.getString("url");
            boolean checked = result.getBoolean("checked");

            Tip tip = new Tip(id, title, author, description, url, checked);
            tips.add(tip);
        }

        return tips;
    }

    public void add(String title, String author, String description, String url) throws SQLException {
        if (title.isEmpty() | author.isEmpty() | description.isEmpty() | url.isEmpty()) {
            return;
        }
        
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Tip (title, author, description, url, checked) "
                + "VALUES (?, ?, ?, ?, 0)");
        stmt.setString(1, title);
        stmt.setString(2, author);
        stmt.setString(3, description);
        stmt.setString(4, url);

        stmt.executeUpdate();

    }

}
