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
    public void addTag(String name) throws SQLException {
        this.tagDao.add(name);
    }

    @Override
    public void addTipTag(int tip_id, int tag_id, List<Tag> tags) throws SQLException {
        this.tipTagDao.addTipTag(tip_id, tag_id, tags);
    }

    @Override
    public Tag findTag(String name) throws SQLException {
        return this.tagDao.findOne(-1, name);
    }

    @Override
    public List<Tag> findTags(int tip_id) throws SQLException {
        return this.tipDao.findTags(tip_id);
    }

    @Override
    public Tip findTip(String title) throws SQLException {
        return this.tipDao.findOne(title);
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
    public void deleteTip(String id) throws SQLException {
        this.tipDao.delete(id);
    }

    @Override
    public void markTipRead(String id) throws SQLException {
        this.tipDao.markAsRead(id);

    }

    @Override
    public List<Tip> getTipsWithTag(String tag) throws SQLException{
        List<Tip> tips = this.tipDao.getTipsWithTag(tag);
        return tips;
    }
}
