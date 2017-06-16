package com.qcloud;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yang on 2017/6/6.
 */
public class GeneralInfoData {
    public Set<String> item;

    public GeneralInfoData(){
        item = new HashSet<String>();
    }
    public void print(){
        Iterator<String> iter = item.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
