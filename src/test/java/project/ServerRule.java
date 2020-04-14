package project;

import org.junit.rules.ExternalResource;
import project.dao.DaoManager;
import project.dao.DatabaseDao;
import project.dao.TipDao;
import project.database.DatabaseImp;
import project.main.Main;

import spark.Spark;


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
        DaoManager dao = new DatabaseDao(new TipDao(db));
        dao.addTip("Testi", "tekijä", "onpahan jotain", "www.linkki.com");
        Main.setDao(dao);
        Main.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
        db.refresh();
    }
}
 