public class GenericCalculator <T> {

    public GenericCalculator(){}

    public boolean check(T a, T b){
        if(a.equals(b)){
            System.out.println("Correct Answer");
            return true;
        }
        System.out.println("Incorrect Answer");
        return false;
    }
}
