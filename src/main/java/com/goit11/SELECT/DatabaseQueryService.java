package com.goit11.SELECT;

import com.goit11.Database.Database;
import com.goit11.resource.reading.exceptions.DbInitException;
import ex.MaxProjectCountClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DatabaseQueryService {
    private static String sqlSelect = "SELECT client.name, COUNT(project.id) as PROJECT_COUNT\n" +
            "FROM project\n" +
            "JOIN client ON client.id = project.client_id \n" +
            "GROUP BY client.id\n" +
            "HAVING COUNT(project.client_id) =\n" +
            "(\n" +
            "    SELECT COUNT(ID)\n" +
            "    FROM project\n" +
            "    GROUP BY CLIENT_ID\n" +
            "    ORDER BY COUNT(ID) DESC\n" +
            "    LIMIT 1\n" +
            ")";
    public Database database;



    public DatabaseQueryService(Database database){
        super();
        this.database = database;
    }
    public List<MaxProjectCountClient> findMaxProjectsClient(){
        List<MaxProjectCountClient> list = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlSelect);
            while(rs.next()){
                String nameWorker  = rs.getString("name");
                int projectCount  = rs.getInt("PROJECT_COUNT");
                MaxProjectCountClient variant = new MaxProjectCountClient(nameWorker,projectCount);
                list.add(variant);
            }
            rs.close();
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
        return list;
    }
}
