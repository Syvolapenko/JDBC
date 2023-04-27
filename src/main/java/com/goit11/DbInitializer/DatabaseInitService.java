package com.goit11.DbInitializer;

import com.goit11.Database.Database;
import com.goit11.resource.reading.Reader;
import com.goit11.resource.reading.exceptions.DbInitException;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {
    Database database;
    Reader reader;

    public DatabaseInitService(Database database, Reader reader) {
        this.database = database;
        this.reader = reader;
    }

    public static void main(String[] args) {
        Database database1 = Database.getInstance();
        Reader reader1 = new Reader("sql/init_db.sql");
        DatabaseInitService databaseInitService = new DatabaseInitService(database1,reader1);
        databaseInitService.init();
    }
        public void init () {
            String sql = reader.read();
            try (Connection connection = database.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.execute(sql);
            } catch (Exception e) {
                throw new DbInitException("Db init failed", e);
            }
        }
}