//package com.cloud.eng;
package com.trust.IAM;

public class User{
    protected int userId;
    private String name;
    private String password;
    private Permission permission;
    private String groupId;
    
    //initially permission object with no right assigned
    public User(int userId,String name,String password,String groupId,Permission permission){
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.groupId = groupId;
        this.permission = permission;
    }
    
    
}