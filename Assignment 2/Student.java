import java.util.LinkedList;

public class Student implements UserID{

    private String _stuName;
    private LinkedList<Integer> _grades;
    private LinkedList<Boolean> _pending;
    private LinkedList<String> _submissions;
    private LinkedList<Instructor> _gradedBy;
    
    public Student(String name){
        this._stuName = name;
        _grades = new LinkedList<Integer>();
        _pending = new LinkedList<Boolean>();
        _submissions = new LinkedList<String>();
        _gradedBy = new LinkedList<Instructor>();
    }

    @Override
    public String getName(){
        return this._stuName;
    }

    public String getSubName(int index){
        return this._submissions.get(index);
    }

    public void addPendingAndGrades(){
        this._grades.add(-1);
        this._pending.add(true);
        this._submissions.add("No submission");
        this._gradedBy.add(null);
    }

    public void setMarks(int marks, int choice, Instructor i){
        this._grades.set(choice, marks);
        this._gradedBy.set(choice, i);
    }

    public void setPendingFalse(int assID, Instructor i){
        this._pending.set(assID, false);
        if (this._gradedBy.get(assID) == null){
            this._gradedBy.set(assID, i);
        }
    }

    public LinkedList <Boolean> getPending(){
        return this._pending;
    }

    public void addFile(String fileName, int subID){
        this._submissions.set(subID, fileName);
        this._pending.set(subID, false);
    }

    public void viewSubmissions(LinkedList <Assessments> _assessments){
        System.out.println("Graded Submissions: ");
        for (int i = 0; i < this._grades.size(); i++){
            if (!this._submissions.get(i).equals("No submission") && this._grades.get(i) != -1){
                System.out.println("Submission: " + this._submissions.get(i));
                System.out.println("Marks scored: " + this._grades.get(i));
                System.out.println("Graded by: " + this._gradedBy.get(i).getName() + "\n");
            }
        }

        System.out.println("\nUngraded Submissions: ");
        for (int i = 0; i < this._grades.size(); i++){
            if (!this._submissions.get(i).equals("No submission") && this._grades.get(i) == -1){
                System.out.println("Submission: " + this._submissions.get(i));
            }
        }
    }

    public boolean viewUngradedSubmissions(int choice){
        if (!this._submissions.get(choice).equals("No submission") && this._grades.get(choice) == -1){
            return true;
        }
        return false;
    }

}
