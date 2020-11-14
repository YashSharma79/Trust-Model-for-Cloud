package com.trust.IAM;

import java.sql.Connection;
import java.sql.*;

public class IdentityManager{
    private Connection conn;
    private Database db;
    PreparedStatement ps = null;
    Statement s = null;
    ResultSet rs = null;
    
    private static final String defaultGroupId = "0";
    private static final String defaultPass = "vlv@cp9p";

    private final Permission defaultPerm;
    
    public IdentityManager(){
        defaultPerm = new Permission();
    }
    
    public void check(){
        try{
            db = new Database();
            conn = db.connect();
            if(conn == null)
                System.out.println("NULL");
            s = conn.createStatement();
            String sql = "CREATE TABLE users " +
                   "(id INTEGER not NULL, " +
                   " name VARCHAR(255), " + 
                   " password VARCHAR(255), " + 
                   " PRIMARY KEY ( id ))"; 

            s.executeUpdate(sql);

        }
        
        catch(SQLException sqlException){
        }
        
        finally{
      //finally block used to close resources
            try{
               if(s != null)
                  conn.close();
            }catch(SQLException se){
            }
        }
    }
                            
    public static void main(String[] args){
            IdentityManager idm = new IdentityManager();
            idm.check();
    }
    
    public void createUsers(int num_of_users){
        //create users and pass this as a task
        //store in database
        for(int i = 0;i<num_of_users;i++){
            createUser(i,"U"+i,defaultPass,defaultGroupId, defaultPerm);
        }
        
    }
    
    public void createUser(int id,String name,String pass,String groupId,Permission p){
        User u = new User(id,name,pass,groupId,p);
        //store in database
        //insert statement
        //find out how to insert in batch
        
        
    }
    
    public void createGroup(int num_of_users){
        Group g = new Group();
        for(int i = 0;i<num_of_users;i++){
            g.addToGroup(i);
        }
        //write function to calculate size of object OR there might already be one
    }
    
    public void createRole(int num_of_roles){
       // Role r = new Role();
        for(int i = 0;i<num_of_roles;i++){
            
        }
    }
    
    public void deleteUser(){
        
    }
    
    public void deleteGroup(){
        
    }
    
    public void deleteRole(){
        
    }
}
