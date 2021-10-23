public abstract class Floor {

    protected abstract void updateDetails(Player p1);

    protected void printDetails(Player p1, String className){
        System.out.println("Player position Floor-" + p1.getFloor());
        System.out.println(p1.getName() + " has reached a" + className +" Floor");
        System.out.println("Total points " + p1.getPoints());
    }

}
