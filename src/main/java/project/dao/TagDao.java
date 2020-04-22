package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.database.Database;
import project.domain.Tag;

/**
 *
 * @author chenhuiz
 */
public class TagDao {

    private Connection conn;

    public TagDao(Database db) throws SQLException {
        this.conn = db.getConnection();
    }

    public Tag findOne(int tag_id, String name) throws SQLException {
        // Tägeä haetaan id:n mukaan
        if (tag_id != -1) {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tag WHERE id = ?");
            stmt.setInt(1, tag_id);

            ResultSet result = stmt.executeQuery();
            boolean hasOne = result.next();

            if (!hasOne) {
                return null;
            }

            int id = result.getInt("id");
            String tagName = result.getString("name");

            Tag tag = new Tag(id, tagName);

            return tag;
        }

        // Tägeä haetaan nimen mukaan
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tag WHERE name = ?");
        stmt.setString(1, name);

        ResultSet result = stmt.executeQuery();
        boolean hasOne = result.next();

        if (!hasOne) {
            return null;
        }

        int id = result.getInt("id");
        String tagName = result.getString("name");

        Tag tag = new Tag(id, tagName);

        return tag;
    }

    public void add(String name) {
        if (name.isEmpty()) {
            return;
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Tag (name) VALUES (?)");
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TagDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
