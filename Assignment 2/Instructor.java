public class Instructor implements UserID{

    private String _instructorName;
    
    public Instructor(String instructorName){
        this._instructorName = instructorName;
    }

    @Override
    public String getName(){
        return this._instructorName;
    }

}
