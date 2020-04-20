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
    
    void addTag(String name);
    
    void addTipTag(Tip tip, int tag_id) throws SQLException;
    
    Tag findTag(String name) throws SQLException;
    
    Tip findTip(String tipId) throws SQLException;
}
