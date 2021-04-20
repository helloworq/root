package com.transform.base.constant;

import java.util.HashMap;
import java.util.Map;

public class Global_Constant {

    public final static Map<String, Integer> SOCIAL_RELATION;

    static {
        SOCIAL_RELATION = new HashMap<>();
        SOCIAL_RELATION.put("FriendsList", 1);
        SOCIAL_RELATION.put("banList", 2);
        SOCIAL_RELATION.put("blackList", 3);
        //SOCIAL_RELATION.put("requestList", 0);
        SOCIAL_RELATION.put("friendSquareList", 5);
    }

}
