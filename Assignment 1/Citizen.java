import java.util.LinkedList;

public class Citizen {

    private String _name;
    private int _age;
    private long _uniqueID;
    private String _vaccinationStatus;
    private Vaccine _vac;
    private int _vacCount;
    private int _vacDay;
    private static LinkedList<Citizen> citizens;

    public Citizen(String name, int age, long uniqueID){
        this._name = name;
        this._age = age;
        this._uniqueID = uniqueID;
        this._vaccinationStatus = "REGISTERED";
        this._vac = null;
        this._vacCount = 0;
        this._vacDay = 0;
    }

    static{
        citizens = new LinkedList<Citizen>();
    }

    public static void bookSlot(long uID, int option, LinkedList <Integer> checkArr){

        Slot chosenSlot = null;

        if (checkArr.contains(option)){ 
            chosenSlot = Slot.getSlot(option, checkArr);
        }
        else{
            System.out.println("ERROR! Choose from the given choices.\n---------------------------------");
            return;
        }

        for (Citizen curCit : citizens){
            if (curCit._uniqueID == uID){
                Vaccine vac = chosenSlot.getVaccine();
                if (curCit._vaccinationStatus.equals("REGISTERED")){
                    curCit._vac = vac;
                    curCit._vacCount += 1;
                    curCit._vacDay = chosenSlot.getDayNum();
                    chosenSlot.quantNegate();
                    if (vac.getDoseNumber() == 1){
                        curCit._vaccinationStatus = "FULLY VACCINATED";
                    }
                    else{
                        curCit._vaccinationStatus = "PARTIALLY VACCINATED";
                    }
                    System.out.println(curCit._name+" vaccinated with "+vac.getName()+"\n---------------------------------");

                }
                else if (curCit._vaccinationStatus.equals("PARTIALLY VACCINATED")){
                    if (chosenSlot.getDayNum() - curCit._vacDay < vac.getDoseGap()){
                        System.out.println("ERROR! You need to wait a bit more for your turn.\n---------------------------------");
                        return;
                    }
                    else{
                        curCit._vacCount += 1;
                        curCit._vacDay = chosenSlot.getDayNum();
                        chosenSlot.quantNegate();
                        if(vac.getDoseNumber() == curCit._vacCount){
                            curCit._vaccinationStatus = "FULLY VACCINATED";
                        }
                        else{
                            curCit._vaccinationStatus = "PARTIALLY VACCINATED";
                        }  
                    }
                    System.out.println(curCit._name+" vaccinated with "+vac.getName()+"\n---------------------------------");
                }
                else{
                    System.out.println("ERROR! You are already vaccinated.\n---------------------------------");
                    return;
                }
                break;
            }
        }

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
        System.out.println("Citizen Name: "+name+", Age: "+c._age+", Unique ID: "+uniqueID+"\n---------------------------------");
        return;
    }

    public static boolean checkID(long uID){
        for (Citizen curCit : citizens){
            if (curCit._uniqueID == uID){
                return true;
            }
        }
        return false;
    }

    public static Vaccine getVaccine(long uID){
        for (Citizen curCit : citizens){
            if (curCit._uniqueID == uID){
                return curCit._vac;
            }
        }
        return null;
    }
}
