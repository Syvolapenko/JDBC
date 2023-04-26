package com.goit11;

import com.goit11.Database.Database;
import com.goit11.DatabaseInit.DbInitializer;
import com.goit11.SELECT.DatabaseQueryService;
import com.goit11.resource.reading.Reader;
import ex.MaxProjectCountClient;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Reader reader = new Reader("sql/populate_db.sql");
        Database database = Database.getInstance();
        System.out.println(database.check());
        DbInitializer dbInitializer = new DbInitializer(database,reader);
        dbInitializer.init();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService(database);
        databaseQueryService.findMaxProjectsClient();
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService(database).findMaxProjectsClient();
        System.out.println(maxProjectCountClients);
    }
    }
