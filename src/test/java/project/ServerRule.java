package project;

import org.junit.rules.ExternalResource;
import project.dao.DaoManager;
import project.dao.DatabaseDao;
import project.dao.TagDao;
import project.dao.TipDao;
import project.dao.TipTagDao;
import project.database.DatabaseImp;
import project.main.Main;

import spark.Spark;

import java.io.File;


public class ServerRule extends ExternalResource {

    private final int port;
    private DatabaseImp db;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Spark.port(port);
        
        db = new DatabaseImp("jdbc:sqlite:test.db");
        
        TagDao tagDao = new TagDao(db);
        DaoManager dao = new DatabaseDao(new TipDao(db, tagDao), tagDao, new TipTagDao(db));
        dao.addTip("Kukkakaali", "tekijä", "onpahan jotain", "www.linkki.com");
        dao.addTip("Keräkaali", "tekijä", "onpahan jotain", "www.linkki.com");
        dao.addTip("Ruusukaali", "tekijä", "onpahan jotain", "www.linkki.com");
        dao.addTip("Porkkana", "tekijä", "onpahan jotain", "www.linkki.com");
        dao.addTip("Refactoring To Patterns", "Joshua Kerievsky", "refaktoroinnista", "https://martinfowler.com/books/r2p.html");
        Main.setDao(dao);
        Main.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
        File file = new File("test.db");
        file.delete();
    }
}
 