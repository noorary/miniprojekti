package project.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
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
    private Boolean read;
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
                Timestamp checkedtime = result.getTimestamp("checkedtime");

                Tip tip = new Tip(id, title, author, description, url, checked, checkedtime);
                tips.add(tip);
            }

            return tips;

        } catch (SQLException ex) {
            Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void add(String title, String author, String description, String url) {
        if (title.isEmpty() || author.isEmpty() || description.isEmpty()) {
            return;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Tip (title, author, description, url, checked, checkedtime) "
                    + "VALUES (?, ?, ?, ?, ?, null)");

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

    public void markAsRead(String id) {
        try {
            Integer.parseInt(id);
        } catch (Throwable t) {
            return;
        }
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Tip WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                read = res.getBoolean("checked");
            }

        }catch(SQLException ex){
            Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (read == false) {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Tip SET checked = ?, checkedtime = ? WHERE id = ? ");

                LocalDateTime now = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(now);
                stmt.setBoolean(1, true);
                stmt.setTimestamp(2, timestamp);
                stmt.setInt(3, Integer.parseInt(id));
                stmt.execute();

            } catch (SQLException ex) {
                Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Tip SET checked = ?, checkedtime = ? WHERE id = ? ");

                stmt.setBoolean(1, false);
                stmt.setTimestamp(2, null);
                stmt.setInt(3, Integer.parseInt(id));
                stmt.execute();

            } catch (SQLException ex) {
                Logger.getLogger(TipDao.class.getName()).log(Level.SEVERE, null, ex);
            }

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
