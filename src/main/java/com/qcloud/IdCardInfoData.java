package com.qcloud;

/**
 * Created by yang on 2017/6/6.
 */
public class IdCardInfoData {
    public String name;
    public String sex;
    public String nation;
    public String birth;
    public String address;
    public String id;

    public IdCardInfoData(){
        name="";
        sex="";
        nation="";
        birth="";
        address="";
        id="";
    }

    public void print(){
        System.out.println("name="+name);
        System.out.println("sex="+sex);
        System.out.println("nation="+nation);
        System.out.println("birth="+birth);
        System.out.println("address="+address);
        System.out.println("id="+id);
    }
}
