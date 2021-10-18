import java.util.LinkedList;

public class Student {
    private String _stuName;
    private LinkedList<Integer> _grades;
    private LinkedList<Boolean> _pending;

    {
        _grades = new LinkedList<Integer>();
        _pending = new LinkedList<Boolean>();
    }

    
    public Student(String name){
        this._stuName = name;
    }

    public void addPendingAndGrades(){
        this._grades.add(0);
        this._pending.add(true);
    }

    public void setPendingFalse(int assID){
        this._pending.set(assID, false);
    }

}
