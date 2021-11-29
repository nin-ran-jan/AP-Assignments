import java.util.ArrayList;

public class Bucket {

    private ArrayList<SoftToy> softToysWon;
    private int numberOfToys;

    public Bucket(){
        this.softToysWon = new ArrayList<SoftToy>();
        this.numberOfToys = 0;
    }

    public void addToBucket(SoftToy st){
        this.softToysWon.add(st);
        this.numberOfToys++;
    }

    public void printSoftToys(){
        int count = 1;
        for(SoftToy st : this.softToysWon){
            if(count == numberOfToys){
                System.out.println(st.getName());
            }
            else{
                System.out.print(st.getName() + ", ");
                count++;
            }
        }
    }

}
