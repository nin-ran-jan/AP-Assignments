public class Elevator extends Ladder{

    private int _elevatorFloorPoints;
    private int _elevatorFloorSetter;
    private String _elevatorFloorClassName;

    public Elevator(){
        super();
        this._elevatorFloorClassName = "n Elevator";
        this._elevatorFloorPoints = 4;
        this._elevatorFloorSetter = 8;
    }

    @Override
    protected void updateDetails(Player p1){
        p1.setPoints(this._elevatorFloorPoints);
        this.printDetails(p1, this._elevatorFloorClassName);
        p1.setFloorNum(this._elevatorFloorSetter);
    }
    
}
