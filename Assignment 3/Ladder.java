public class Ladder extends Floor{

    private int _ladderFloorPoints;
    private int _ladderFloorSetter;
    private String _ladderFloorClassName;

    public Ladder(){
        super();
        this._ladderFloorPoints = 2;
        this._ladderFloorSetter = 4;
        this._ladderFloorClassName = " Ladder";
    }

    @Override
    protected void updateDetails(Player p1){
        p1.setPoints(_ladderFloorPoints);
        this.printDetails(p1, _ladderFloorClassName);
        p1.setFloorNum(_ladderFloorSetter);
    }
    
}
