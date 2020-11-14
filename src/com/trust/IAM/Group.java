//package com.cloud.eng;
package com.trust.IAM;
import java.util.ArrayList;
public class Group{
    ArrayList<Integer> users;
    protected int groupId;
    private String password;
    private Permission permission;
    private String groupName;
    
    public Group(){
        users = new ArrayList<>();
    }
    
    public void addToGroup(int uid){
        users.add(uid);
    }
    
    public void deleteFromGroup(int uid){
        
    }
    
    
    
}