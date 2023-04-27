package com.goit11.DbInitializer;

import com.goit11.Database.Database;
import com.goit11.resource.reading.Reader;
import com.goit11.resource.reading.exceptions.DbInitException;

import java.sql.Connection;
import java.sql.Statement;

public class DatabasePopulateService {
    Database database;
    Reader reader;

    public DatabasePopulateService(Database database, Reader reader) {
        this.database = database;
        this.reader = reader;
    }

    public static void main(String[] args) {
        Database database2 = Database.getInstance();
        Reader reader2 = new Reader("sql/populate_db.sql");
        DatabasePopulateService databasePopulateService = new DatabasePopulateService(database2,reader2);
        databasePopulateService.populate();
    }

    public void populate(){
        String sql = reader.read();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
    }
}
