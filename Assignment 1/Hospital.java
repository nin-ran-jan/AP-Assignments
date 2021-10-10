import java.util.LinkedList;
import java.util.Scanner;

public class Hospital {

    private String _name;
    private int _pinCode;
    private int _uniqueID;
    private static int uniqueIDcounter;
    private static LinkedList<Hospital> hospitals;

    static{
        uniqueIDcounter = 99999;
        hospitals = new LinkedList<Hospital>();
    }

    public Hospital(String name, int pinCode, int uniqueID){
        this._name = name;
        this._pinCode = pinCode;
        this._uniqueID = uniqueID;
    }

    public static Hospital getHospital(int uID){
        for (Hospital curHosp : hospitals){
            if(uID == curHosp._uniqueID){
                return curHosp;
            }
        }
        return null;
    }

    public static void register(String name, int pinCode){
        
        if(pinCode < 100000 || pinCode > 999999){
            System.out.println("ERROR! PinCode is invalid."+"\n---------------------------------");
            return;
        }

        uniqueIDcounter++;
        if(uniqueIDcounter>999999){
            System.out.println("ERROR! Maximum hospital value reached."+"\n---------------------------------");
            return;
        }
        
        Hospital h = new Hospital(name, pinCode, uniqueIDcounter);
        hospitals.add(h);
        System.out.println("Hospital Name: "+name+", PinCode: "+pinCode+", Unique ID: "+uniqueIDcounter+"\n---------------------------------");
        return;
    }

    public static Hospital checkHospID(int enteredID){
        for (Hospital curHosp : hospitals){
            if(enteredID == curHosp._uniqueID){
                return curHosp;
            }
        }
        return null;
    }
}
