package com.dexterlabs.taxotpaf;

public class MessageNameSave {
    String id;
    String messageNm;
    String time;

    public MessageNameSave() {

    }

    public MessageNameSave(String id, String messageNm,String time) {
        this.id = id;
        this.messageNm = messageNm;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getMessageNm() {
        return messageNm;
    }

    public String getTime() {
        return time;
    }
}
