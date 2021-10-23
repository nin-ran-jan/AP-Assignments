import java.util.Random;

public class Dice {

    private static final int NUMFACES = 2;
    private static int _faceValue;

    {
        _faceValue = 0;
    }

    public Dice(){}

    public static void roll() {
        Random rand = new Random();
        setFaceValue(1 + rand.nextInt(NUMFACES));
    }

    private static void setFaceValue (int value) {
        if (value <= NUMFACES){
            _faceValue = value;
        }
    }

    public static int getFaceValue() {
        return _faceValue;
    }
    
}
