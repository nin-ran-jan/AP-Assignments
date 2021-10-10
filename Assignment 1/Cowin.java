import java.util.LinkedList;
import java.util.Scanner;

public class Cowin {

    public Cowin(){
        System.out.println("CoWin Portal initialized...\n---------------------------------");
    }

    public static void createCowin(){

        while(true){

            int choice = getInput();
            if (choice == 8){
                break;
            }

            Scanner sc = new Scanner(System.in);

            switch (choice) {
                
                case 1:
                    System.out.print("Vaccine Name: ");
                    String vacName = sc.nextLine();

                    System.out.print("Number of Doses: ");
                    int doseNumber = sc.nextInt();
            
                    int doseGap = 0;
                    if (doseNumber > 1){
                        System.out.print("Gap between Doses: ");
                        doseGap = sc.nextInt();
                    }

                    Vaccine.addVaccine(vacName, doseNumber, doseGap);                
                    break;

                case 2:
                    System.out.print("Hospital Name: ");
                    String hospName = sc.nextLine();

                    System.out.print("PinCode: ");
                    int pinCode = sc.nextInt();

                    Hospital.register(hospName, pinCode);
                    break;

                case 3:
                    System.out.print("Citizen Name: ");
                    String citName = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();
        
                    System.out.print("Unique ID: ");
                    long uniqueID = sc.nextLong();

                    Citizen.register(citName, age, uniqueID);
                    break;

                case 4:     
                //In this case, it is a NECESSITY for the user to know
                //whether the hospital exists or not AND if the vaccines exist or not.
                //Otherwise, they may go into a loop, which is laborious to get out of.

                //Thus, I am calling some methods from Hospital and Vaccine directly from the main function.
                //This may compromise on the encapsulation a bit. But it is necessary, and there is no work around.
                    
                    System.out.print("Enter Hospital ID: ");
                    int enteredID = sc.nextInt();
                    Hospital hosp = Hospital.checkHospID(enteredID);
                    if(hosp == null){
                        System.out.println("ERROR! Hospital ID is not present in the database."+"\n---------------------------------");
                        break;
                    }

                    System.out.print("Enter the number of Slots to be added: ");
                    int slotNum = sc.nextInt();

                    for(int i = 0; i < slotNum; i++){
                        System.out.print("Enter Day Number: ");
                        int dayNum = sc.nextInt();

                        System.out.print("Enter Quantity: ");
                        int quant = sc.nextInt();
                        
                        boolean contains = Vaccine.showChoices();
                        if (contains){
                            System.out.println("Select Vaccine");
                            int vacChoice = sc.nextInt();
                            Slot.createSlots(enteredID, dayNum, quant, vacChoice, hosp);
                        }
                        else{
                            System.out.println("ERROR! There are no Vaccines to choose from.");
                            break;
                        }

                    }

                    System.out.println("---------------------------------");

                    break;
                
                case 5:
                    
                    break;

                case 6:

                    System.out.print("Enter Hospital ID: ");
                    int hospID = sc.nextInt();

                    Slot.slotsAvailableWithHosp(hospID);
                    
                    break;

                case 7:
                    
                    break;
            
                default:
                    System.out.println("ERROR! Enter a valid choice."+"\n---------------------------------");
                    break;
            }
        }
        System.out.println("Thank you for using CoWin!");
    }

    public static int getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n---------------------------------");
        int choice = sc.nextInt();
        return choice;
    }
}