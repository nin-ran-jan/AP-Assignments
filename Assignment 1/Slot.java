import java.util.LinkedList;

public class Slot {
    private Hospital _hosp;
    private int _dayNum;
    private int _quant;
    private Vaccine _vac;
    private static LinkedList <Slot> slots;

    public Slot(Hospital hosp, int dayNum, int quant, Vaccine vac){
        this._hosp = hosp;
        this._dayNum = dayNum;
        this._quant = quant;
        this._vac = vac;
    }

    static{
        slots = new LinkedList<Slot>();
    }

    public static LinkedList <Integer> displaySlots(int hospID, boolean flag, long uID, int option, String vaccineName){
        LinkedList <Integer> countArr = new LinkedList<Integer>();

        if (!flag){
            System.out.println("ERROR! Your Unique ID does not exist!\n---------------------------------");
            return countArr;
        }

        Hospital hosp = Hospital.getHospital(hospID);
        if(hosp == null){
            System.out.println("ERROR! You have entered an incorrect Hospital ID.\n---------------------------------");
            return countArr;
        }

        Vaccine vac;

        if (vaccineName.equals("0")){
            vac = Citizen.getVaccine(uID);
        }

        else{
            vac = Vaccine.getVaccine(vaccineName);
        }
                

        int count = 0;
        if (option == 1 || vac == null){
            for (Slot curSlot : slots){
                if (curSlot._hosp == hosp && vac == null || curSlot._hosp == hosp && curSlot._vac == vac && Citizen.getVacDay(uID)+vac.getDoseGap() <= curSlot._dayNum){
                    countArr.add(count);
                    System.out.println(count+"-> Day: "+curSlot._dayNum+" Available Qty: "+curSlot._quant+" Vaccine: "+curSlot._vac.getName());
                }
                count++;
            }
        }
        else if (option == 2){
            for (Slot curSlot : slots){
                if (curSlot._hosp == hosp && curSlot._vac == vac && (Citizen.getVacDay(uID)+vac.getDoseGap() <= curSlot._dayNum || Citizen.getVacDay(uID) == 0)){
                    countArr.add(count);
                    System.out.println(count+"-> Day: "+curSlot._dayNum+" Available Qty: "+curSlot._quant+" Vaccine: "+curSlot._vac.getName());
                }
                count++;
            }
        }
        
        if (countArr.size() == 0){
            System.out.println("Sorry! There weren't any slots available.\n---------------------------------");
        }
        return countArr;
    }
    

    public int getDayNum(){
        return this._dayNum;
    }

    public int quantNegate(){
        if (this._quant == 0){
            System.out.println("ERROR! Cannot use this vaccine, as quantity is 0.\n---------------------------------");
        }
        this._quant -= 1;
        return this._quant;
    }

    public void quantAdd(){
        this._quant++;
    }

    public static Slot getSlot(int option, LinkedList <Integer> checkArr){
        return slots.get(option);
    }

    public Vaccine getVaccine(){
        return this._vac;
    }

    public static boolean displayHospitals(String vaccineName){
        if (slots.size() == 0){
            System.out.println("ERROR! Create a slot first.\n---------------------------------");
            return false;
        }

        LinkedList <Hospital> checker = new LinkedList<Hospital>();

        boolean flag = false;
        for (Slot curSlot : slots){
            if (curSlot._vac.getName().equals(vaccineName) && !checker.contains(curSlot._hosp)){
                flag = true;
                checker.add(curSlot._hosp);
                System.out.println(curSlot._hosp.getUID()+" "+curSlot._hosp.getName());
            }
        }
        if (!flag){
            System.out.println("Sorry! There aren't any slots at the moment.\n---------------------------------");
        }
        return flag;
    }

    public static void createSlots(int enteredID, int dayNum, int quant, int vacChoice, Hospital hosp){

        if(dayNum < 1){
            System.out.println("ERROR! Day entered has to be positive.");
            return;
        }

        if (quant < 0){
            System.out.println("ERROR! Quantity of vaccines cannot be negative.");
            return;
        }

        Vaccine vac = Vaccine.getVaccine(vacChoice);

        if (vac == null){
            System.out.println("ERROR! Vaccine selected is not amongst the given choices.");
            return;
        }

        for (Slot curSlot : slots){
            if(hosp == curSlot._hosp && dayNum == curSlot._dayNum && vac == curSlot._vac){
                System.out.println("ERROR! You cannot update the vaccine quantity once specified.");
                return;
            }
        }
        
        String vacName = vac.getName();

        Slot s = new Slot(hosp, dayNum, quant, vac);
        slots.add(s);
        System.out.println("Slot added by Hospital "+enteredID+" for Day: "+dayNum+", Available Quantity: "+quant+" of Vaccine "+vacName);
        return;

    }

    public static void slotsAvailableWithHosp(int hospID){
        Hospital hosp =  Hospital.getHospital(hospID);
        if (hosp == null){
            System.out.println("ERROR! There is no hospital with this Hospital ID."+"\n---------------------------------");
            return;
        }
        for (Slot curSlot : slots){
            if (hosp == curSlot._hosp){
                System.out.println("Day: "+curSlot._dayNum+" Vaccine: "+curSlot._vac.getName()+" Available Qty: "+curSlot._quant);
            }
        }
        System.out.println("---------------------------------");
        return;
    }

}