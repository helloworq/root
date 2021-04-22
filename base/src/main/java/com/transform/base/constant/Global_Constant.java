package com.transform.base.constant;

import java.util.HashMap;
import java.util.Map;

public class Global_Constant {

    public final static Map<String, Integer> SOCIAL_RELATION_URL_MAP;

    public final static Map<String, Integer> SOCIAL_RELATION_STATUS;

    static {
        SOCIAL_RELATION_URL_MAP = new HashMap<>();
        SOCIAL_RELATION_URL_MAP.put("friendsList", 1);
        SOCIAL_RELATION_URL_MAP.put("banList", 2);
        SOCIAL_RELATION_URL_MAP.put("blackList", 3);
        SOCIAL_RELATION_URL_MAP.put("friendSquareList", 5);
        //SOCIAL_RELATION.put("requestList", 0);

        SOCIAL_RELATION_STATUS = new HashMap<>();
        SOCIAL_RELATION_STATUS.put("noneRelation", 0);
        SOCIAL_RELATION_STATUS.put("friend", 1);
        SOCIAL_RELATION_STATUS.put("ban", 2);
        SOCIAL_RELATION_STATUS.put("black", 3);
    }

}
