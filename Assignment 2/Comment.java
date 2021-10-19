public class Comment {
    private String _content;
    private UserID _user;
    private String _time;

    public Comment(String content, UserID user, String time){
        this._content = content;
        this._time = time;
        this._user = user;
    }

    public String getDate(){
        return this._time;
    }

    public String getContent(){
        return this._content;
    }

    public String getUserName(){
        return this._user.getName();
    }
}
