package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public List<Tip> listAll() {

        try {
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

        } catch (SQLException ex) {
            Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void add(String title, String author, String description, String url) {
        if (title.isEmpty() || author.isEmpty() || description.isEmpty() || url.isEmpty()) {
            return;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Tip (title, author, description, url, checked) "
                    + "VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, description);
            stmt.setString(4, url);
            stmt.setBoolean(5, false);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean markAsRead(String title) {

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Tip SET checked = ? WHERE title = ? ");

            stmt.setBoolean(1, true);
            stmt.setString(2, title);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void delete(String id) throws Exception {
    	try {
    		Integer.parseInt(id);
    	} catch (Throwable t) {
    		return;
    	}
    	PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tip WHERE id = ?");
    	stmt.setInt(1,  Integer.parseInt(id));
    	stmt.execute();
    }
}
