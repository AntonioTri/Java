package Lezione20_11_23;

public class Request {
    private int value;
    private String description;

    public Request(String descString, int value){
        this.value = value;
        description = descString;
    }

    public int getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }
}
