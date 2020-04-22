package project.main;

import java.sql.SQLException;
import spark.ModelAndView;
import spark.Spark;

import java.util.HashMap;
import project.dao.DaoManager;
import project.dao.DatabaseDao;
import project.dao.TagDao;
import project.dao.TipDao;
import project.dao.TipTagDao;
import project.database.Database;
import project.database.DatabaseImp;
import project.domain.Tag;
import project.domain.Tip;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    private static Database db;
    private static DaoManager dao;

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");

        if (dao == null) {
            String databaseUrl;
            if (System.getenv("JDBC_DATABASE_URL") != null) {
                databaseUrl = System.getenv("JDBC_DATABASE_URL");
            } else {
                databaseUrl = "jdbc:sqlite:lukuvinkki.db";
            }
            
            db = new DatabaseImp(databaseUrl);
            TagDao tagDao = new TagDao(db);
            dao = new DatabaseDao(new TipDao(db, tagDao), tagDao, new TipTagDao(db));
        }

        Spark.post("/newTag/:id", (req, res) -> {
            String name = req.queryParams("name");
            String tip_id = req.params(":id");

            dao.addTag(name);
            Tip tip = dao.findTip(tip_id);
//                int tipId = dao.findTip(tip_id).getId();
            int tagId = dao.findTag(name).getId();

//                dao.addTipTag(tipId, tagId);
            dao.addTipTag(tip, tagId);

            res.redirect("/");
            return "New tag added";
        });

        Spark.get("/", (req, res) -> {
            HashMap data = new HashMap<>();
            data.put("tips", dao.listAllTips());

            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/addNewTip", (req, res) -> {
            HashMap map = new HashMap<>();

            return new ModelAndView(map, "tipList");
        }, new ThymeleafTemplateEngine());

        Spark.post("/newTip", (req, res) -> {
            dao.addTip(req.queryParams("title"), req.queryParams("author"), req.queryParams("description"), req.queryParams("url"));
            res.redirect("/");
            return "New tip added";
        });

        Spark.get("/allTips/delete/:id", (req, res) -> {
            dao.deleteTip(req.params("id"));
            res.redirect("/");
            return "Tip deleted";
        });
    }

    public static void setDao(DaoManager dao) {
        Main.dao = dao;
    }
}
