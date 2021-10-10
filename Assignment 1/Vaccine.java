import java.util.LinkedList;

public class Vaccine {

    private String _name;
    private int _doseNumber;
    private int _doseGap;
    private static LinkedList<Vaccine> vaccines;

    static{
        vaccines = new LinkedList<Vaccine>(); 
    }

    public Vaccine(String name, int doseNumber, int doseGap){  
        this._name = name;
        this._doseNumber = doseNumber;
        this._doseGap = doseGap;
    }

    public static Vaccine getVaccine(int vacChoice){
        if (vacChoice < 0){
            return null;
        }
        else if (vacChoice >= vaccines.size()){
            return null;
        }
        else{
            return vaccines.get(vacChoice);
        }
    }

    public static Vaccine getVaccine(String vaccineName){
        for (Vaccine curVac : vaccines){
            if (curVac._name.equals(vaccineName)){
                return curVac;
            }
        }
        return null;
    }

    public String getName(){
        return this._name;
    }

    public int getDoseNumber(){
        return this._doseNumber;
    }

    public int getDoseGap(){
        return this._doseGap;
    }

    public static void addVaccine(String name, int doseNumber, int doseGap){

        for (Vaccine curVac : vaccines){

            if (name.equals(curVac._name)){
                System.out.println("ERROR! Vaccines cannot have the same name."+"\n---------------------------------");
                return;
            }
        }

        if (doseNumber < 1){
            System.out.println("ERROR! Vaccines can only have positive dose values."+"\n---------------------------------");
            return;
        }

        if (doseNumber > 1 && doseGap < 1){
            System.out.println("ERROR! Need to have positive value for gap between doses."+"\n---------------------------------");
            return;
        }

        Vaccine v = new Vaccine(name, doseNumber, doseGap);
        vaccines.add(v);
        System.out.println("Vaccine Name: "+name+", Number of Doses: "+doseNumber+", Gap between Doses: "+doseGap+"\n---------------------------------");
        return;
    }

    public static boolean showChoices(){
        int count = 0;

        if (vaccines.size() == 0){
            System.out.println("ERROR! There are no Vaccines to choose from.");
            return false;
        }

        for (Vaccine curVac : vaccines){
            System.out.println(count+". "+curVac._name);
            count++;
        }

        return true;
    }


}
