package project.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import project.database.Database;
import project.domain.Tag;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class TipDao {

    private Boolean read;
    private Connection conn;
    private TagDao tagDao;

    public TipDao(Database db, TagDao tagDao) throws SQLException {
        this.conn = db.getConnection();
        this.tagDao = tagDao;
    }

    public Tip findOne(String title) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tip WHERE title = ?");
        stmt.setString(1, title);

        ResultSet result = stmt.executeQuery();

        boolean hasOne = result.next();
        if (!hasOne) {
            return null;
        }

        int id = result.getInt("id");
        String author = result.getString("author");
        String description = result.getString("description");
        String url = result.getString("url");
        boolean checked = result.getBoolean("checked");
        Timestamp checkedtime = result.getTimestamp("checkedtime");

        Tip tip = new Tip(id, title, author, description, url, checked, checkedtime);
        tip.setTags(findTags(id));

        return tip;
    }
    
    public List<Tag> findTags(int tip_id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tip_tag WHERE tip_id = ?");
        stmt.setInt(1, tip_id);

        ResultSet result = stmt.executeQuery();

        List<Tag> tags = new ArrayList<>();

        while (result.next()) {
            int tag_id = result.getInt("tag_id");

            Tag tag = this.tagDao.findOne(tag_id, null);

            tags.add(tag);
        }

        return tags;
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
            Timestamp checkedtime = result.getTimestamp("checkedtime");

            Tip tip = new Tip(id, title, author, description, url, checked, checkedtime);

            tip.setTags(findTags(id));
            tips.add(tip);
        }

        return tips;

    }

    public void add(String title, String author, String description, String url) throws SQLException {
        if (title.isEmpty() || author.isEmpty() || description.isEmpty()) {
            return;
        }
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Tip (title, author, description, url, checked, checkedtime) "
                + "VALUES (?, ?, ?, ?, ?, null)");

        stmt.setString(1, title);
        stmt.setString(2, author);
        stmt.setString(3, description);
        stmt.setString(4, url);
        stmt.setBoolean(5, false);
        stmt.executeUpdate();

    }

    public void markAsRead(String id) throws SQLException {
        try {
            Integer.parseInt(id);
        } catch (Throwable t) {
            return;
        }

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Tip WHERE id = ?");
        stmt.setInt(1, Integer.parseInt(id));
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            read = res.getBoolean("checked");
        }

        if (read == false) {
            PreparedStatement stmt2 = conn.prepareStatement(
                    "UPDATE Tip SET checked = ?, checkedtime = ? WHERE id = ? ");

            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            stmt2.setBoolean(1, true);
            stmt2.setTimestamp(2, timestamp);
            stmt2.setInt(3, Integer.parseInt(id));
            stmt2.execute();

        } else {
            PreparedStatement stmt2 = conn.prepareStatement(
                    "UPDATE Tip SET checked = ?, checkedtime = ? WHERE id = ? ");

            stmt2.setBoolean(1, false);
            stmt2.setTimestamp(2, null);
            stmt2.setInt(3, Integer.parseInt(id));
            stmt2.execute();

        }
    }

    public void delete(String id) throws SQLException {
        try {
            Integer.parseInt(id);
        } catch (Throwable t) {
            return;
        }

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tip WHERE id = ?");
        stmt.setInt(1, Integer.parseInt(id));
        stmt.execute();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Tip_tag WHERE tip_id = ?");
        stmt2.setInt(1, Integer.parseInt(id));
        stmt2.execute();
    }
}
