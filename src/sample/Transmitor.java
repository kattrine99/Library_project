package sample;

import java.lang.annotation.Target;

public class Transmitor {
    private static Transmitor instance;
    private String Name;
    private  String ID;
    private Transmitor(){}
    public static synchronized Transmitor getInstance(){
        if(instance == null){
            instance = new Transmitor();
        }
        return instance;
    }
    public void SetName(String text){Name = text;}
    public String GetName(){return Name;}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
