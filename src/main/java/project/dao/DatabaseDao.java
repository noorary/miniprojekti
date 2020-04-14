package project.dao;

import java.util.ArrayList;
import java.util.List;

import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class DatabaseDao implements DaoManager {

    private TipDao tipDao;

    public DatabaseDao(TipDao tipDao) {
        this.tipDao = tipDao;
    }


    @Override
    public List<Tip> listAll() {
        List<Tip> list = new ArrayList<>();

        this.tipDao.listAll().forEach(t -> list.add(t));

        return list;
    }

    @Override
    public void addTip(String title, String author, String description, String url) {
                
        this.tipDao.add(title, author, description, url);
    }
}
