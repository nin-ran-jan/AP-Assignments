import java.util.ArrayList;
import java.util.Random;

public class Player {

    private int numberOfChances;
    private ArrayList<SoftToy> bucket;
    private int numberOfToys;

    public Player(){
        this.numberOfChances = 5;
        this.bucket = new ArrayList<SoftToy>();
        this.numberOfToys = 0;
    }

    public int getNoOfJumps(){
        return this.numberOfChances;
    }

    public void printSoftToys(){
        int count = 1;
        for(SoftToy st : this.bucket){
            if(count == numberOfToys){
                System.out.println(st.getName());
            }
            else{
                System.out.print(st.getName() + ", ");
                count++;
            }
        }
    }

    public int getChancesLeft(){
        return this.numberOfChances;
    }
    
    public int hop(){
        numberOfChances--;
        Random r = new Random();
        int jumpPosition = r.nextInt(22) + 1; //tiles 21 and 22 are mud puddles
        // int jumpPosition = sc.nextInt();
        // sc.nextLine();
        return jumpPosition;
    }

    public void addToBucket(SoftToy st){
        this.bucket.add(st);
        this.numberOfToys++;
    }

}
