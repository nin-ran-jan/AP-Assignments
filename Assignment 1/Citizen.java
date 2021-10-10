import java.util.LinkedList;
import java.util.Scanner;

public class Citizen {

    private String _name;
    private int _age;
    private long _uniqueID;
    private String _vaccinationStatus;
    private static LinkedList<Citizen> citizens;

    public Citizen(String name, int age, long uniqueID){
        this._name = name;
        this._age = age;
        this._uniqueID = uniqueID;
        this._vaccinationStatus = "REGISTERED";
    }

    static{
        citizens = new LinkedList<Citizen>();
    }

    public static void register(String name, int age, long uniqueID){

        if (uniqueID<100000000000L || uniqueID > 999999999999L){
            System.out.println("ERROR! Invalid ID."+"\n---------------------------------");
            return;
        }

        for (Citizen curCit : citizens){
            if (uniqueID == curCit._uniqueID){
                System.out.println("ERROR! Your Unique ID is not unique."+"\n---------------------------------");
                return;
            }
        }

       
        if (age < 18){
            System.out.println("ERROR! Only above 18 are allowed."+"\n---------------------------------");
            return;
        }

        Citizen c = new Citizen(name, age, uniqueID);
        citizens.add(c);        
        System.out.println("Citizen Name: "+name+", Age: "+age+", Unique ID: "+uniqueID+"\n---------------------------------");
        return;
    }
}
