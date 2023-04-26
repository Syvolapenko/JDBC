package com.goit11;

import com.goit11.Database.Database;
import com.goit11.DatabaseInit.DbInitializer;
import com.goit11.SELECT.DatabaseQueryService;
import com.goit11.resource.reading.Reader;

public class App {
    public static void main(String[] args) {
        Reader reader = new Reader("sql/populate_db.sql");
        Database database = Database.getInstance();
        System.out.println(database.check());
        DbInitializer dbInitializer = new DbInitializer(database,reader);
        dbInitializer.init();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService(database);
        databaseQueryService.findMaxProjectsClient();
    }
    }
