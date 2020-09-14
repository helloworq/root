package com.transform.service.util;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ListUtil {
    public static String listToString(List list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    public static List<String> stringToList(String str){
        String[] s=str.split(",");
        List<String> list= Arrays.asList(s);
        return list;
    }
}
