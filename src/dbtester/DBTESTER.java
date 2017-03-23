/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import classes.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Rav
 */
public class DBTESTER {

   //public static void main(String[] args) {
    // JDBC Driver name and database URL
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://lockerdb.csexedpueeai.eu-west-1.rds.amazonaws.com:3306/clgt3DB?autoReconnect=true&useSSL=false";
    // final String DB_Name = "awsrds";
    //final String DB_URL = "jdbc:mysql://localhost/";
    final String USER_NAME = "root";
    final String PASSWORD = "Pomerm.70";
    /* final String USER_NAME = "project";
     final String PASSWORD = "teamproject";*/

    Connection conn = null;
    
    public DBTESTER(){
        Connection conn = null;
    }
    
    public Connection connect(){
        try {
	    // STEP 1 - Load the JDBC driver and Initialize a driver to open a communications channel with the database.
            Class.forName(JDBC_DRIVER);
            System.out.println("STEP 1 COMPLETE - Driver Registered...");

            // STEP 2 - Open a connection.
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("STEP 2 COMPLETE - Connection obtained...");

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver.\n" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } 
        return null;
    }
    
     // disconnect database
    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("STEP 6 COMPLETE - Connection closed.");
                conn = null;
            } catch (SQLException e) {
                System.out.println("Could not close connection.\n" + e.getMessage());
            }
        }
    }
        
    public  void updateStudent(String sqlQuery) {
        try {
             connect();
             
            // STEP 3 - Create Statement object		    
            Statement stmt = conn.createStatement();
            System.out.println("STEP 3 COMPLETE - Statement object created...");

            //connect with the database
            stmt.executeUpdate("USE clgt3DB");
            System.out.println("STEP 4 COMPLETE - Query executed and table added to database...");

            //send the query with one you pass.
            stmt.executeUpdate(sqlQuery);
            //ResultSet rs =  stmt.executeQuery("select * from StudentTester");
            System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table...");
        }  catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } finally {
            disconnect();
        }
    }
    
    public ArrayList<Student> returnStudent(String sqlQuery){
      ArrayList<Student> myList = new ArrayList<>(); 
      try {
             connect();
            // STEP 3 - Create Statement object		    
            Statement stmt = conn.createStatement();
            System.out.println("STEP 3 COMPLETE - Statement object created...");

            //connect with the database
            stmt.executeQuery("USE clgt3DB");
            System.out.println("STEP 4 COMPLETE - Query executed and connected with database");

            //send the query with one you pass.
            ResultSet rs = stmt.executeQuery(sqlQuery);
             int count =0;
                while (rs.next()){
                //myList.add((i-1), new Student (rs.getString(1),rs.getString(2),rs.getString(3),Integer.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6),Integer.valueOf(rs.getString(7)),rs.getString(8),Double.valueOf(rs.getString(9))));
               myList.add(count, new Student (rs.getString(1),rs.getString(2),rs.getString(3),Integer.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6),Integer.valueOf(rs.getString(7)),rs.getString(8),Double.valueOf(rs.getString(9))));
               count++;
            }
             System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table...");
        }
        catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        }finally {
            disconnect();
        }
      return myList;
    }
    
    public String getPassword(int givenID){
        String password = "it is not a right password.";
        boolean found = false;
      try {
             connect();
            // STEP 3 - Create Statement object		    
            Statement stmt = conn.createStatement();
            //System.out.println("STEP 3 COMPLETE - Statement object created...");

            //connect with the database
            stmt.executeQuery("USE clgt3DB");
           // System.out.println("STEP 4 COMPLETE - Query executed and connected with database");

            //send the query with one you pass.
            ResultSet rs = stmt.executeQuery("select * from Password");
                while (rs.next()){
                //myList.add((i-1), new Student (rs.getString(1),rs.getString(2),rs.getString(3),Integer.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6),Integer.valueOf(rs.getString(7)),rs.getString(8),Double.valueOf(rs.getString(9))));
               if (givenID == Integer.valueOf((rs.getString(2)))){
                   password = rs.getString(1);
                   found = true;
               }    
            }
                 if(!found)
                   JOptionPane.showMessageDialog(null, "Id not found. Please type valid ID");
            // System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table...");
            
        }
        catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Student Id cannot be empty"+e.getMessage());
            System.out.println("catched");
        }
      finally {
            disconnect();
        }
      return password;  
    }
    
    public String getNextId(){
        String id = "noting at the moment";
         try {
             connect();
            // STEP 3 - Create Statement object		    
            Statement stmt = conn.createStatement();
            System.out.println("STEP 3 COMPLETE - Statement object created...");

            //connect with the database
            stmt.executeQuery("USE clgt3DB");
            System.out.println("STEP 4 COMPLETE - Query executed and connected with database");

            //send the query with one you pass.
            ResultSet rs = stmt.executeQuery("select * from StudentTester");
                while (rs.next()){
                //myList.add((i-1), new Student (rs.getString(1),rs.getString(2),rs.getString(3),Integer.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6),Integer.valueOf(rs.getString(7)),rs.getString(8),Double.valueOf(rs.getString(9))));
               if (rs.isLast()){
                   id = rs.getString(9);
               }    
            }
             System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table...");
            
        }
        catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Student Id cannot be empty"+e.getMessage());
            System.out.println("catched");
        }
      finally {
            disconnect();
        }
      return id;
    }
    
       public  void updatePassword(String sqlQuery) {
        try {
             connect();
             
            // STEP 3 - Create Statement object		    
            Statement stmt = conn.createStatement();
            System.out.println("STEP 3 COMPLETE - Statement object created...");

            //connect with the database
            stmt.executeUpdate("USE clgt3DB");
            System.out.println("STEP 4 COMPLETE - Query executed and table added to database...");

            //send the query with one you pass.
            stmt.executeUpdate(sqlQuery);
           // ResultSet rs =  stmt.executeQuery("select * from StudentTester");
            System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table...");
        }  catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } finally {
            disconnect();
        }
    }
    
    

    public static void main(String[] args) {
        DBTESTER mydb = new DBTESTER();
      String myQuery;
        myQuery = "INSERT INTO StudentTester VALUES('L00100001','Johnny','Oneill','Letterkenny','Applied', 3, 'completed',20.0, null) ";
//          myQuery = "select * from StudentTester ";
       mydb.updateStudent(myQuery);
//         ArrayList<Student> myList =  mydb.returnStudent(myQuery);
//          for (Student stu: myList){
////              System.out.println(stu.toString());
////          }
  //      String value = mydb.getNextId();
////       int value = 0;
////        //System.out.println(Integer.valueOf(value));
  //      System.out.println(value);
          
    }

}
