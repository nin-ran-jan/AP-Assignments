public class Floor {

    protected int _FloorPoints;
    protected String _FloorClassName;
    protected int _FloorSetter;

    protected void updateDetails(Player p){
        p.setPoints(this._FloorPoints);
        System.out.println("Player position Floor-" + p.getFloor());
        System.out.println(p.getName() + " has reached a" + this._FloorClassName +" Floor");
        System.out.println("Total points " + p.getPoints());
        p.setFloorNum(this._FloorSetter);
    }
    
}
