package project.main;

import java.sql.SQLException;
import spark.ModelAndView;
import spark.Spark;

import java.util.HashMap;
import project.dao.DaoManager;
import project.dao.DatabaseDao;
import project.dao.TipDao;
import project.database.Database;
import project.database.DatabaseImp;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    private static Database db;
    private static DaoManager dao;

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");

        if (System.getenv("JDBC_DATABASE_URL") != null) {
            db = new DatabaseImp(System.getenv("JDBC_DATABASE_URL"));

            dao = new DatabaseDao(new TipDao(db));
        } else if (dao == null) {

            db = new DatabaseImp("jdbc:sqlite:lukuvinkki.db");

            dao = new DatabaseDao(new TipDao(db));
        }

        Spark.get("/", (req, res) -> {
            HashMap data = new HashMap<>();

            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/allTips", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("tips", dao.listAll());

            return new ModelAndView(map, "tipList");
        }, new ThymeleafTemplateEngine());

        Spark.post("/newTip", (req, res) -> {
            dao.addTip(req.queryParams("title"), req.queryParams("author"), req.queryParams("description"), req.queryParams("url"));
            res.redirect("/allTips");
            return "New tip added";
        });
    }

    public static void setDao(DaoManager dao) {
        Main.dao = dao;
    }

}
