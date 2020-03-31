package project.main;

import java.sql.SQLException;
import spark.ModelAndView;
import spark.Spark;

import java.util.HashMap;
import project.dao.TipDao;
import project.database.Database;
import project.database.DatabaseImp;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");

        Database db = new DatabaseImp("jdbc:sqlite:lukuvinkki.db");

        TipDao tipDao = new TipDao(db);

        Spark.get("/", (req, res) -> {
            HashMap data = new HashMap<>();

            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/list", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("tips", tipDao.listAll());
            
            return new ModelAndView(map, "tipList");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/list", (req, res) -> {
            tipDao.add(req.queryParams("title"), req.queryParams("author"), req.queryParams("description"), req.queryParams("url"));
            res.redirect("/list");
            return "Tip add new OK";
        });
    }

}
