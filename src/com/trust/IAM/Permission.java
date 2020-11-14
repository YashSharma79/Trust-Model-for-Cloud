package com.trust.IAM;

import java.util.ArrayList;

public class Permission{

    ArrayList<AccessRight> ar;
    //permission is an array of access rights
    
    //default with no permissions at all
    public Permission(){
        ar = new ArrayList<>();
    }
    
    public void addAccessRight(String right,boolean val){
        AccessRight a = new AccessRight(right,val);
        ar.add(a);
    }
    
    public void addAccessRight(String right,boolean val,double cost,double memory,double proPower){
        AccessRight a = new AccessRight(right,val,cost,memory,proPower);
        ar.add(a);
    }

    //USER,GROUP OR ROLE COULD BE ASSIGNED PERMISSION
    //RESOURCES CAN BE ATTACHED PERMISSIONS
    
    public void editRight(String right,boolean val){
        for(AccessRight a : ar){
            if(a.right.equals(right)){
                a.val = val;
                break;
            }
        }
    }
    
    public void deleteRight(String right){
        for(AccessRight a : ar){
            if(a.right.equals(right)){
                ar.remove(a);
                break;
            }
        }
    }
}