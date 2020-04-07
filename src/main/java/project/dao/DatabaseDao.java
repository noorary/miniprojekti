package project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.xpath.SourceTree;

import java.util.*;
import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class DatabaseDao {
    
    private BookDao bookDao;
    private VideoDao videoDao;
    
    public DatabaseDao(BookDao bookDao, VideoDao videoDao) {
        this.bookDao = bookDao;
        this.videoDao = videoDao;
    }
    
    public List<Tip> listAllTypes() {
        List<Tip> list = new ArrayList<>();
        this.bookDao.listAll().forEach(b -> list.add(b));
        this.videoDao.listAll().forEach(v -> list.add(v));
        
        return list;
    }

    public List<Tip> listByTitle() {
        List<Tip> titleList = new ArrayList<>();

        titleList = listAllTypes();
        Collections.sort(titleList);

        return titleList;
    }
}
