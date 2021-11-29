public class TileCarpet {
    
    private SoftToy st;
    
    public TileCarpet(String softToyName){
        this.st = new SoftToy(softToyName);
    }
    
    public SoftToy clone() throws CloneNotSupportedException{
        return st.clone();
    }
    
}
