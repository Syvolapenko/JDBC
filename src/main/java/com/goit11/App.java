package com.goit11;

import com.goit11.Database.Database;
import com.goit11.DbInitializer.DatabaseInitService;
import com.goit11.DbInitializer.DatabasePopulateService;
import com.goit11.SELECT.DatabaseQueryService;
import com.goit11.resource.reading.Reader;
import ex.MaxProjectCountClient;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Reader reader1 = new Reader("sql/init_db.sql");
        Database database = Database.getInstance();
        System.out.println(database.check());
        DatabaseInitService databaseInitService = new DatabaseInitService(database,reader1);
        databaseInitService.init();
        Reader reader2 = new Reader("sql/populate_db.sql");
        DatabasePopulateService databasePopulateService = new DatabasePopulateService(database,reader2);
        databasePopulateService.populate();
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService(database).findMaxProjectsClient();
        System.out.println(maxProjectCountClients);
    }
    }
