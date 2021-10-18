public class LectureSlide implements ClassMaterial{

    private String _slideTopic;
    private String [] _slideContents;
    private Instructor _uploadingInst;
    private String _slideDateTime;

    public LectureSlide(String slideTopic, String slideContents [], Instructor uploadingInst, String time){
        this._slideContents = slideContents;
        this._slideTopic  = slideTopic;
        this._uploadingInst = uploadingInst;
        this._slideDateTime = time;
    }

    @Override
    public void view(){
        this.viewSlides();
    }

    private void viewSlides(){
        System.out.println("Title: " + this._slideTopic);
        for (int x = 0; x < _slideContents.length; x++){
            System.out.println("Slide " + (int) (x+1) + ": " + this._slideContents[x]);
        }
        System.out.println("Number of slides: " + this._slideContents.length);
        System.out.println("Date of upload: " + this._slideDateTime);
        System.out.println("Uploaded by: " + this._uploadingInst.getName());
    }
}
