import java.util.ArrayList;

public class Bucket {

    private ArrayList<SoftToy> softToysWon;

    public Bucket(){
        softToysWon = new ArrayList<SoftToy>();
    }

    public void addToBucket(SoftToy st){
        softToysWon.add(st);
    }

}
