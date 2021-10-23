public class Floor {

    private int _emptyFloorPoints;
    private String _emptyFloorClassName;
    
    public Floor(){
        this._emptyFloorPoints = 1;
        this._emptyFloorClassName = "n Empty";
    }

    protected void printDetails(Player p1, String className){
        System.out.println("Player position Floor-" + p1.getFloor());
        System.out.println(p1.getName() + " has reached a" + className +" Floor");
        System.out.println("Total points " + p1.getPoints());
    }

    protected void updateDetails(Player p1){
        p1.setPoints(_emptyFloorPoints);
        this.printDetails(p1, _emptyFloorClassName);
    }

}
