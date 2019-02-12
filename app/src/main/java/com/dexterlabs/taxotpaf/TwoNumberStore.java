package com.dexterlabs.taxotpaf;

public class TwoNumberStore {
    String id;
    String clientName;
    String num1;
    String num2;

    public TwoNumberStore() {

    }

    public TwoNumberStore(String id, String clientName, String num1, String num2) {
        this.id = id;
        this.clientName = clientName;
        this.num1 = num1;
        this.num2 = num2;
    }

    public String getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }
}
