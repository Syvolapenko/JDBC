package com.goit11.SELECT;

import com.goit11.Database.Database;
import com.goit11.resource.reading.exceptions.DbInitException;
import ex.LongestProject;
import ex.MaxProjectCountClient;
import ex.MaxSalaryWorker;
import ex.YoungestEldestWorkers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    private static String sqlSelect1 = "SELECT client.name, COUNT(project.id) as PROJECT_COUNT\n" +
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
    private static String sqlSelect2 = "SELECT name, salary\n" +
            "FROM WORKER \n" +
            "ORDER BY salary  DESC LIMIT 1";
    private static String sqlSelect3 = "SELECT id AS NAME, DATEDIFF(month,project.start_date , project.finish_date) AS MONTH_COUNT\n" +
            "FROM project\n" +
            "ORDER BY MONTH_COUNT DESC LIMIT 1";
    private static String SqlSelect4 = "SELECT* FROM YOUNGEST_ELDEST_WORKERS";
    public Database database;


    public DatabaseQueryService(Database database) {
        this.database = database;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> list = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlSelect1);
            while (rs.next()) {
                String nameWorker = rs.getString("name");
                int projectCount = rs.getInt("PROJECT_COUNT");
                MaxProjectCountClient variant = new MaxProjectCountClient(nameWorker, projectCount);
                list.add(variant);
            }
            rs.close();
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> list2 = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlSelect2);
            while (rs.next()) {
                String nameWorker = rs.getString("name");
                int salary = rs.getInt("salary");
                MaxSalaryWorker variant2 = new MaxSalaryWorker(nameWorker, salary);
                list2.add(variant2);
            }
            rs.close();
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
        return list2;
    }

    public List<LongestProject>findLongestProject(){
        List<LongestProject>list3 = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlSelect3);
            while (rs.next()) {
                String name = rs.getString("name");
                int month = rs.getInt("MONTH_COUNT");
                LongestProject variant3 = new LongestProject(name, month);
                list3.add(variant3);
            }
            rs.close();
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
        return list3;
    }

    public List<YoungestEldestWorkers>findYoungestEldestWorkers(){
        List<YoungestEldestWorkers>list4 = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SqlSelect4);
            while (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
                YoungestEldestWorkers variant4 = new YoungestEldestWorkers(type,name,birthday);
                list4.add(variant4);
            }
            rs.close();
        } catch (Exception e) {
            throw new DbInitException("Db init failed", e);
        }
        return list4;
    }
    }

