import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private int number_of_chances;
    private ArrayList<SoftToy> bucket;

    public Player(){
        this.number_of_chances = 5;
        this.bucket = new ArrayList<SoftToy>();
    }

    public int getNoOfJumps(){
        return this.number_of_chances;
    }
    
    public int hop(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Hit enter for hop number " + (6 - number_of_chances) + " ");
        number_of_chances--;
        sc.nextLine();
        Random r = new Random();
        int jumpPosition = r.nextInt(22) + 1; //tiles 21 and 22 are mud puddles
        // int jumpPosition = sc.nextInt();
        // sc.nextLine();
        return jumpPosition;
    }

    public void addToBucket(SoftToy st){
        this.bucket.add(st);
    }

}
