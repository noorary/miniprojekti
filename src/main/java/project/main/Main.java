package project.main;

import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.port;

import java.util.HashMap;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");
        
        port(4569);
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();

            return new ModelAndView(map, "lomake");
        }, new ThymeleafTemplateEngine());
    }
    
}
