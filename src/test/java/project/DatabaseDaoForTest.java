//package project;
//
//import java.util.ArrayList;
//import java.util.List;
//import project.dao.DaoManager;
//import project.dao.TipDao;
//import project.domain.Tip;
//
///**
// *
// * @author chenhuiz
// */
//public class DatabaseDaoForTest implements DaoManager {
//
////    private List<Tip> tips;
//    private TipDao tipDao;
//    
//    public DatabaseDaoForTest() {
////        this.tips = new ArrayList<>();
//        this.tipDao = tipDao;
//    }
//    
//
//    @Override
//    public List<Tip> listAll() {
//        List<Tip> list = new ArrayList<>();
//        
//        tips.forEach(t -> list.add(t));
//
//        return list;
//    }
//
//    @Override
//    public void addTip(String title, String author, String description, String url) {
//        if (title.isEmpty() || author.isEmpty() || description.isEmpty()) {
//            return;
//        }
//        
//        tips.add(new Tip(tips.size()+1, title, author, description, url, false));
//    }
//
//}
