package com.goit11.DatabaseInit;

import com.goit11.Database.Database;
import com.goit11.resource.reading.Reader;
import com.goit11.resource.reading.exceptions.DbInitException;

import java.sql.Connection;
import java.sql.Statement;

public class DbInitializer {
    Database database;
    Reader reader;

    public DbInitializer(Database database, Reader reader) {
        this.database = database;
        this.reader = reader;
    }

    public void init()  {
        String sql = reader.read();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
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
