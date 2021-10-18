public class LectureVideo implements ClassMaterial{

    private String _videoTopic;
    private String _videoName;
    private Instructor _uploadingInst;
    private String _videoDateTime;

    public LectureVideo(String videoTopic, String videoName, Instructor uploadingInst, String time){
        this._videoName = videoName;
        this._videoTopic = videoTopic;
        this._uploadingInst = uploadingInst;
        this._videoDateTime = time;
    }


    @Override
    public void view(){
        this.viewVideos();
    }

    public void viewVideos(){
        System.out.println("Title of video: " + this._videoTopic);
        System.out.println("Video file: " + this._videoName);
        System.out.println("Date of upload: " + this._videoDateTime);
        System.out.println("Uploaded by: " + this._uploadingInst.getName());
    }

}
