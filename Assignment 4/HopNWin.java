import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HopNWin {

    private Player p;
    private TileCarpet tileCarpetArray [];
    private GenericCalculator <Integer> intCalc;
    private GenericCalculator <String> strCalc;

    public HopNWin() throws CloneNotSupportedException, InvalidTypeException{
        this.p = new Player();
        this.tileCarpetArray = new TileCarpet [20];
        this.intCalc = new GenericCalculator<Integer>();
        this.strCalc = new GenericCalculator<String>();
        this.tileCarpetArray[0] = new TileCarpet("Mickey Mouse");
        this.tileCarpetArray[1] = new TileCarpet("Minnie Mouse");
        this.tileCarpetArray[2] = new TileCarpet("Donald Duck");
        this.tileCarpetArray[3] = new TileCarpet("Daisy Duck");
        this.tileCarpetArray[4] = new TileCarpet("Goofy");
        this.tileCarpetArray[5] = new TileCarpet("Pluto");
        this.tileCarpetArray[6] = new TileCarpet("Noddy");
        this.tileCarpetArray[7] = new TileCarpet("Oswald");
        this.tileCarpetArray[8] = new TileCarpet("Kipper");
        this.tileCarpetArray[9] = new TileCarpet("Captain America");
        this.tileCarpetArray[10] = new TileCarpet("Iron Man");
        this.tileCarpetArray[11] = new TileCarpet("Hulk");
        this.tileCarpetArray[12] = new TileCarpet("Thor");
        this.tileCarpetArray[13] = new TileCarpet("Loki");
        this.tileCarpetArray[14] = new TileCarpet("Hawk Eye");
        this.tileCarpetArray[15] = new TileCarpet("Black Widow");
        this.tileCarpetArray[16] = new TileCarpet("Captain Marvel");
        this.tileCarpetArray[17] = new TileCarpet("Vision");
        this.tileCarpetArray[18] = new TileCarpet("Ant Man");
        this.tileCarpetArray[19] = new TileCarpet("Doctor Strange");
        this.startGame();
    }

    private void startGame() throws IndexOutOfBoundsException, CloneNotSupportedException, NullPointerException, IllegalArgumentException, InvalidTypeException, ArithmeticException, InputMismatchException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Hit enter to initialize the game ");
        sc.nextLine();
        System.out.println("Game is ready");
        while(p.getNoOfJumps() != 0){
            int pos = p.hop();
            TileCarpet curTile = null;
            try{
                curTile = this.tileCarpetArray[pos-1];
            }
            catch(IndexOutOfBoundsException iobe){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
                continue;
            }
            catch(NullPointerException npe){
                System.out.println("ERROR!"); //This will never happen
                continue;
            }
            System.out.println("You landed on tile " + pos);
            if(pos % 2 == 0){
                SoftToy curSoftToy = null;
                do {
                    curSoftToy = curTile.clone();
                } while(curSoftToy == null); //handling the CloneNotSupportedException returned with the null pointer
                p.addToBucket(curSoftToy);
                System.out.println("You won a " + curSoftToy.getName() + " soft toy");
            }
            else{
                boolean isInputCorrect = false;
                String output; 
                do {
                    System.out.println("Question answer round. Integer or String? (all small)");
                    output = sc.nextLine();
                    try{
                        if(!output.equals("integer") && !output.equals("string")){
                            throw new InvalidTypeException("The type is neither an Integer nor a String");
                        }
                        else{
                            isInputCorrect = true;
                        }
                    }
                    catch(InvalidTypeException ite){
                        System.out.println("Try again!");
                    }
                } while (!isInputCorrect);
                if(output.equals("integer")){ //Will immediately get initialized
                    isInputCorrect = false;
                    boolean isRandomNumberCorrect = false;
                    int numA, numB, answer, calculatedAnswer;
                    answer = -1234567890;
                    calculatedAnswer = -1234567890;
                    //The above 2 integers are stored with this junk value. Their value will change through the course of the program.
                    do {
                        numA = this.generateRandomNumber();
                        numB = this.generateRandomNumber();
                        // System.out.println("Enter");
                        // numA = sc.nextInt();
                        // numB = sc.nextInt();
                        // sc.nextLine();
                        try{
                            calculatedAnswer = numA/numB;
                            isRandomNumberCorrect = true;
                        }
                        catch(ArithmeticException ae){
                            //Try again
                        }
                    } while(!isRandomNumberCorrect);
                    do {
                        System.out.println("Calculate the result of " + numA + " divided by " + numB);
                        try{
                            answer = sc.nextInt();
                            isInputCorrect = true;
                        }
                        catch(InputMismatchException ime){
                            System.out.println("Try again! Enter integers please.");
                        }
                        sc.nextLine();
                    } while(!isInputCorrect);
                    if(this.intCalc.check(calculatedAnswer, answer)){
                        SoftToy curSoftToy = null;
                        do {
                            curSoftToy = curTile.clone();
                        } while(curSoftToy == null); //handling the CloneNotSupportedException returned with the null pointer
                        p.addToBucket(curSoftToy);
                        System.out.println("You won a " + curSoftToy.getName() + " soft toy");
                    }
                }
                else if(output.equals("string")){
                    String strA, strB, answer, calculatedAnswer;
                    strA = this.generateRandomString();
                    strB = this.generateRandomString();
                    calculatedAnswer = strA + strB;
                    System.out.println("Calculate the concatenation of strings " + strA + " and " + strB);
                    answer = sc.nextLine();
                    if(this.strCalc.check(calculatedAnswer, answer)){
                        SoftToy curSoftToy = null;
                        do {
                            curSoftToy = curTile.clone();
                        } while(curSoftToy == null); //handling the CloneNotSupportedException returned with the null pointer
                        p.addToBucket(curSoftToy);
                        System.out.println("You won a " + curSoftToy.getName() + " soft toy");
                    }
                }
            }
        }
        System.out.println("Game Over\nSoft toys won by you are:");
        p.printSoftToys();

    }

    private int generateRandomNumber(){
        Random r = new Random();
        return r.nextInt();
    }

    private String generateRandomString(){
        Random r = new Random();
        String retString = "";
        for (int i = 0; i < 4; i++){
            int temp = 65 + r.nextInt(26);
            if(r.nextInt(2) == 1){
                temp = 97 + r.nextInt(26);
            }
            retString += (char) temp;
        }
        return retString;
    }

    private void listDetails(){

    }
}
