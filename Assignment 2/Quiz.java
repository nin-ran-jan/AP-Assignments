public class Quiz implements Assessments{
    private String _qq;
    private int _quizMaxMarks;
    private boolean _closed;

    public Quiz(String qq){
        this._qq = qq;
        this._quizMaxMarks = 1;
        this._closed = false;
    }

    @Override
    public void view(int index){
        this.viewQuiz(index);
    }

    @Override
    public boolean getClose(){
        return this._closed;
    }

    @Override
    public void close(){
        this._closed = true;
    }

    private void viewQuiz(int index){
        System.out.println("ID: " + index + " | Question: " + this._qq);
    }

}
