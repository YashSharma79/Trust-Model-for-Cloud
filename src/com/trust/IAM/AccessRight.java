package com.trust.IAM ;
public class AccessRight{
    protected String right;//rwx
    protected boolean val;
    protected double cost;
    protected double memory;
    protected double proPower;
    
    public AccessRight(String right,boolean val){
        this.right = right;
        this.val = val;
    }

    public AccessRight(String right,boolean val,double cost,double memory,double proPower){
        this.right = right;
        this.val = val;
        this.cost = cost;
        this.memory = memory;
        this.proPower = proPower;
    }
    
}