package project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import project.domain.Tag;

import project.domain.Tip;

/**
 *
 * @author chenhuiz
 */
public class DatabaseDao implements DaoManager {

    private TipDao tipDao;
    private TagDao tagDao;
    private TipTagDao tipTagDao;

    public DatabaseDao(TipDao tipDao, TagDao tagDao, TipTagDao tipTagDao) {
        this.tipDao = tipDao;
        this.tagDao = tagDao;
        this.tipTagDao = tipTagDao;
    }

    @Override
    public void addTag(String name) {
        this.tagDao.add(name);
    }

    @Override
    public void addTipTag(Tip tip, int tag_id) {

        this.tipTagDao.addTipTag(tip, tag_id);
    }
    
    @Override
    public Tag findTag(String name) throws SQLException {
        return this.tagDao.findOne(-1, name);
    }
    
    @Override
    public Tip findTip(String tipId) throws SQLException {
        int tip_id = Integer.parseInt(tipId);
        return this.tipDao.findOne(tip_id);
    }

    @Override
    public List<Tip> listAllTips() throws SQLException {
        List<Tip> list = new ArrayList<>();

        this.tipDao.listAll().forEach(t -> list.add(t));

        return list;
    }

    @Override
    public void addTip(String title, String author, String description, String url) throws SQLException {

        if (!(url.length() == 0)) {
            String sub = url.substring(0, 4);
            if (!sub.equals("http")) {
                url = "https://" + url;
            }
        }

        this.tipDao.add(title, author, description, url);
    }

    @Override
    public void deleteTip(String id) {
        try {
            this.tipDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void markTipRead(String id){
        this.tipDao.markAsRead(id);

    }
}
