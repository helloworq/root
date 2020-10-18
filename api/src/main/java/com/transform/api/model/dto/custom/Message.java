package com.transform.api.model.dto.custom;

import lombok.Data;

@Data
public class Message {
    public Message(){}

    public Message(String momentId, String postTime, String poster){
        this.momentId=momentId;
        this.postTime=postTime;
        this.poster=poster;
    };

    public String momentId;
    public String postTime;
    public String poster;
}
