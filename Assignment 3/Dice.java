import java.util.Random;

public class Dice {

    private final int NUMFACES = 2;
    private int _faceValue;

    public Dice(){
        this._faceValue = 0;
    }

    public void roll() {
        Random rand = new Random();
        setFaceValue(1 + rand.nextInt(this.NUMFACES));
    }

    private void setFaceValue (int value) {
        if (value <= this.NUMFACES){
            this._faceValue = value;
        }
    }

    public int getFaceValue() {
        return this._faceValue;
    }
    
}
