import java.util.LinkedList;

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

    public int getUID(){
        return this._uniqueID;
    }

    public String getName(){
        return this._name;
    }

    public static boolean displayHospitals(int pincode){
        if (hospitals.size() == 0){
            System.out.println("ERROR! Register a hospital first.\n---------------------------------");
            return false;
        }
        boolean flag = false;
        for (Hospital curHosp : hospitals){
            if (curHosp._pinCode == pincode){
                flag = true;
                System.out.println(curHosp._uniqueID+" "+curHosp._name);
            }
        }
        if (!flag){
            System.out.println("Sorry! There aren't any hospitals in the area.\n---------------------------------");
        }
        return flag;
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
        System.out.println("ERROR! Hospital ID is not present in the database."+"\n---------------------------------");
        return null;
    }
}
