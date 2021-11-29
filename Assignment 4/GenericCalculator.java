public class GenericCalculator <T> {

    public GenericCalculator(){}

    public boolean solve(T a, T b, T answer){
        if(a instanceof Integer && b instanceof Integer){
            int numA = (Integer) a;
            int numB = (Integer) b;
            int calculatedAnswer = numA/numB;
            if(((Integer) answer).equals(calculatedAnswer)){
                System.out.println("Correct Answer");
                return true;
            }
            System.out.println("Incorrect Answer");
            return false;
        }
        String strA = (String) a;
        String strB = (String) b;
        String calculatedAnswer = strA + strB;
        if(((String) answer).equals(calculatedAnswer)){
            System.out.println("Correct Answer");
            return true;
        }
        System.out.println("Incorrect Answer");
        return false;
    }

    public boolean checkPossible(T a, T b) throws InvalidTypeException, ArithmeticException, ClassCastException{
        if (a instanceof Integer && b instanceof Integer){
            try{
                int numA = (Integer) a;
                int numB = (Integer) b;
                int calculatedAnswer = numA/numB;
                return true;
            }
            catch(ArithmeticException ae){
                return false;
            }
            catch(ClassCastException cce){
                return false;
            }
        }
        else if (a instanceof String && b instanceof String){
            return true;
        }
        try{
            throw new InvalidTypeException("The type is neither an Integer nor a String");
        }
        catch(InvalidTypeException ite){
            return false;
        }
    }

}
