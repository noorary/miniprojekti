package project.dao;

import java.sql.SQLException;
import java.util.List;
import project.domain.Tag;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public interface DaoManager {

    List<Tip> listAllTips() throws SQLException;

    void addTip(String title, String author, String description, String url) throws SQLException;

    void deleteTip(String id) throws SQLException;

    void markTipRead(String id) throws SQLException;

    void addTag(String name) throws SQLException;

    void addTipTag(int tip_id, int tag_id, List<Tag> tags) throws SQLException;

    Tag findTag(String name) throws SQLException;
    
    List<Tag> findTags(int tip_id) throws SQLException;

    Tip findTip(String tipId) throws SQLException;

    List<Tip> getTipsWithTag(String name) throws SQLException;
}
