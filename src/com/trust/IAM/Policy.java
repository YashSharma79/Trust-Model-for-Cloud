/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yash
 */
package com.trust.IAM;
public class Policy {
    private boolean rootAccess;
    private boolean MFA;
    private boolean IAMenforced;
    private String resourceId;
    private String userId;
    private String groupId;
    private String roleId;
    
    public Policy(boolean rootAccess,String resourceId,String userId,String groupId){
      this.rootAccess = rootAccess;
      this.resourceId = resourceId;
      this.userId = userId;
      this.groupId = groupId;
    }
    
    public void showPolicy(){
            System.out.println("RootAccess:"+rootAccess);
            System.out.println("resourceId:"+resourceId);
            System.out.println("User Id"+userId);
            System.out.println("Group Id:"+groupId);

    }
    
    public String getUserId(){
        return userId;
    }
    
    public String getGroupId(){
        return groupId;
    }
    
    public String getResourceId(){
        return resourceId;
    }
    
    
    public static void main(String[] args){
        Policy p = new Policy(false,"myCloud/r_94","u_04051995","g_931");
        p.showPolicy();   
    }
}