package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import project.database.Database;
import project.domain.Tag;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class TipTagDao {

    private Connection conn;

    public TipTagDao(Database db) throws SQLException {
        this.conn = db.getConnection();
    }

    public void addTipTag(int tip_id, int tag_id, List<Tag> tags) throws SQLException {
        
        for (Tag tag : tags) {
            if (tag.getId() == tag_id) {
                return;
            }
        }

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Tip_tag (tip_id, tag_id) "
                + "VALUES (?, ?)");

        stmt.setInt(1, tip_id);
        stmt.setInt(2, tag_id);

        stmt.executeUpdate();
    }

    

}
