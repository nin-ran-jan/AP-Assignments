public class Player {

    private String _name;
    private int _floorNumber;
    private int _points;

    public Player(String name){
        this._name = name;
        this._floorNumber = -1;
        this._points = 0;
    }

    public int getFloor(){
        return this._floorNumber;
    }

    public String getName(){
        return this._name;
    }

    public int getPoints(){
        return this._points;
    }

    public void setFloorNum(int offsetFloor){
        this._floorNumber += offsetFloor;
    }

    public void setPoints(int offsetPoints){
        this._points += offsetPoints;
    }

}
