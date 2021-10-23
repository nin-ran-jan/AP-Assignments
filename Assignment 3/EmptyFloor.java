public class EmptyFloor extends Floor {
    
    private int _emptyFloorPoints;
    private String _emptyFloorClassName;

    public EmptyFloor(){
        this._emptyFloorPoints = 1;
        this._emptyFloorClassName = "n Empty";
    }

    @Override
    protected void updateDetails(Player p1){
        p1.setPoints(_emptyFloorPoints);
        super.printDetails(p1, _emptyFloorClassName);
    }

}
