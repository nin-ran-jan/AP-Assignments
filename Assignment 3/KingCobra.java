public class KingCobra extends Snake{

    private int _kingCobraFloorPoints;
    private int _kingCobraFloorSetter;
    private String _kingCobraFloorClassName;

    public KingCobra(){
        super();
        this._kingCobraFloorClassName = " King Cobra";
        this._kingCobraFloorPoints = -4;
        this._kingCobraFloorSetter = -8;
    }

    @Override
    protected void updateDetails(Player p1){
        p1.setPoints(this._kingCobraFloorPoints);
        super.printDetails(p1, this._kingCobraFloorClassName);
        p1.setFloorNum(this._kingCobraFloorSetter);
    }
    
}
