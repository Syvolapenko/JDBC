package com.goit11;

import com.goit11.Database.Database;
import com.goit11.DbInitializer.DatabaseInitService;
import com.goit11.DbInitializer.DatabasePopulateService;
import com.goit11.SELECT.DatabaseQueryService;
import com.goit11.resource.reading.Reader;
import ex.LongestProject;
import ex.MaxProjectCountClient;
import ex.MaxSalaryWorker;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        System.out.println(database.check());
//        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService(database).findMaxProjectsClient();
//        System.out.println(maxProjectCountClients);
//        List<MaxSalaryWorker> maxSalaryWorkers = new DatabaseQueryService(database).findMaxSalaryWorker();
//        System.out.println(maxSalaryWorkers);
        List<LongestProject>longestProjects = new DatabaseQueryService(database).findLongestProject();
        System.out.println(longestProjects);
    }
    }
