package project.dao;

import java.util.List;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public interface DaoManager {

    List<Tip> listAll();

    void addTip(String title, String author, String description, String url);
}
