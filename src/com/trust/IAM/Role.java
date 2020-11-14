package com.trust.IAM;

public class Role{
    private String roleId;
    private String password;
    private Permission permission;
    private int time_sec;
    
    public Role(String roleId,String password,Permission permission,int time_sec){
        this.roleId = roleId;
        this.password = password;
        this.permission = permission;
        this.time_sec = time_sec;
    }   

   
}