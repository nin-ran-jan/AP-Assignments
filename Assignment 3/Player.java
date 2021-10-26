public class Player {

    private String _name;
    private int _floorNumber;
    private int _points;
    private boolean _gameCompletionStatus;
    private boolean _flag;

    public Player(String name){
        this._name = name;
        this._floorNumber = -1;
        this._points = 0;
        this._gameCompletionStatus = false;
        this._flag = false;
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

    public void gameCompleted(){
        this._gameCompletionStatus = true;
    }

    public boolean getCompletionStatus(){
        return this._gameCompletionStatus;
    }

    public void setFlag(){
        this._flag = true;
    }

    public boolean getFlag(){
        return this._flag;
    }

}
