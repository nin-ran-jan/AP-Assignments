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
        
        String vacName = Vaccine.getName(vac);

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
                System.out.println("Day: "+curSlot._dayNum+" Vaccine: "+Vaccine.getName(curSlot._vac)+" Available Qty: "+curSlot._quant);
            }
        }
        System.out.println("---------------------------------");
        return;
    }

}