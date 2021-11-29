public class SoftToy implements Cloneable{

    private String nameOfSoftToy;

    public SoftToy(String name){
        this.nameOfSoftToy = name;
    }

    public String getName(){
        return this.nameOfSoftToy;
    }

    public SoftToy clone() throws CloneNotSupportedException{
        try{
            return (SoftToy) super.clone();
        }
        catch(CloneNotSupportedException cnse){
            return null; //This will never happen
        }
    }

}
