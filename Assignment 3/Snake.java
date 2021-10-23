public class Snake extends Floor{

    private int _snakeFloorPoints;
    private int _snakeFloorSetter;
    private String _snakeFloorClassName;

    public Snake(){
        super();
        this._snakeFloorClassName = " Snake";
        this._snakeFloorPoints = -2;
        this._snakeFloorSetter = -4;
    }

    @Override
    protected void updateDetails(Player p1){
        p1.setPoints(this._snakeFloorPoints);
        super.printDetails(p1, this._snakeFloorClassName);
        p1.setFloorNum(this._snakeFloorSetter);
    }
    
}
