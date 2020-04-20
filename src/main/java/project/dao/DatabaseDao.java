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
        
        if(!(url.length() == 0)) {
            String sub = url.substring(0,4);
            if(!sub.equals("http")) {
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
