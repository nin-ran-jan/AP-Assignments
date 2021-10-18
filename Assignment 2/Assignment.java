public class Assignment implements Assessments{
    private String _ps;
    private int _maxMarks;
    private boolean _closed;

    public Assignment(String ps, int maxMarks){
        this._ps = ps;
        this._maxMarks = maxMarks;
        this._closed = false;
    }

    @Override
    public void view(int index){
        this.viewAssignment(index);
    }

    @Override
    public boolean getClose(){
        return this._closed;
    }

    @Override
    public void close(){
        this._closed = true;
    }

    private void viewAssignment(int index){
        System.out.println("ID: " + index + " | Assignment: " + this._ps + " | Max Marks: " + this._maxMarks);
    }

}
