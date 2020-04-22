package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static spark.Spark.port;

/**
 *
 * @author chenhuiz
 */
public class DatabaseImp implements Database {

    private String databaseUrl;
    private Connection connection;

    public DatabaseImp(String databaseUrl) throws SQLException {
        this.databaseUrl = databaseUrl;
        init();
    }

    @Override
    public void init() throws SQLException {
        Integer port;

        ProcessBuilder process = new ProcessBuilder();
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4566;
        }

        port(port);
        createTables();
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            if (System.getenv("JDBC_DATABASE_URL") == null) {
                connection = DriverManager.getConnection(databaseUrl);
            } else {
                String dbstring = System.getenv("JDBC_DATABASE_URL");
                return DriverManager.getConnection(dbstring);
            }
        }

        return connection;
    }

    private void createTables() throws SQLException {

        String createTipTable;
        String createTagTable;
        String createTipTagTable;

        if (System.getenv("JDBC_DATABASE_URL") == null) {

            createTipTable = "CREATE TABLE IF NOT EXISTS Tip(\n"
                    + "     id integer PRIMARY KEY,\n"
                    + "     title varchar(144) NOT NULL,\n"
                    + "     author varchar(144) NOT NULL,\n"
                    + "     description varchar(1000) NOT NULL,\n"
                    + "     url varchar(1000),\n"
                    + "     checked boolean,\n"
                    +"      checkedtime timestamp\n"
                    + ");";
            createTagTable = "CREATE TABLE IF NOT EXISTS Tag(\n"
                    + "     id integer PRIMARY KEY,\n"
                    + "     name varchar(144) NOT NULL UNIQUE\n"
                    + ");";
            createTipTagTable = "CREATE TABLE IF NOT EXISTS Tip_tag(\n"
                    + "    tip_id integer,\n"
                    + "    tag_id integer,\n"
                    + "    FOREIGN KEY(tip_id) REFERENCES Tip(id),\n"
                    + "    FOREIGN KEY(tag_id) REFERENCES Tag(id)\n"
                    + ");";

        } else {

            createTipTable = "CREATE TABLE IF NOT EXISTS Tip(\n"
                    + "     id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,\n"
                    + "     title varchar(144) NOT NULL,\n"
                    + "     author varchar(144) NOT NULL,\n"
                    + "     description varchar(1000) NOT NULL,\n"
                    + "     url varchar(1000),\n"
                    + "     checked boolean,\n"
                    +"      checkedtime timestamp\n"
                    + ");";
            createTagTable = "CREATE TABLE IF NOT EXISTS Tag(\n"
                    + "     id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,\n,\n"
                    + "     name varchar(144) NOT NULL UNIQUE\n"
                    + ");";
            createTipTagTable = "CREATE TABLE IF NOT EXISTS Tip_tag(\n"
                    + "    tip_id integer,\n"
                    + "    tag_id integer,\n"
                    + "    FOREIGN KEY(tip_id) REFERENCES Tip(id),\n"
                    + "    FOREIGN KEY(tag_id) REFERENCES Tag(id)\n"
                    + ");";
        }

        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate(createTipTable);
        stmt.executeUpdate(createTagTable);
        stmt.executeUpdate(createTipTagTable);

    }
}
