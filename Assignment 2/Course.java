import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Course{

    //java error

    private static LinkedList<Instructor> _instructors;
    private static LinkedList <ClassMaterial> _material;
    private static LinkedList <Student> _students;
    private static LinkedList<Assessments> _assessments;
    private static LinkedList<Comment> _comments;


    {
        _instructors = new LinkedList<Instructor>();
        _students = new LinkedList<Student>();
        _material = new LinkedList<ClassMaterial>();
        _assessments = new LinkedList<Assessments>();
        _comments = new LinkedList<Comment>();
        Instructor i0 = new Instructor("I0");
        _instructors.add(i0);
        Instructor i1 = new Instructor("I1");
        _instructors.add(i1);
        Student s0 = new Student("S0");
        _students.add(s0);
        Student s1 = new Student("S1");
        _students.add(s1);
        Student s2 = new Student("S2");
        _students.add(s2);
    }


    public Course(){
        BackpackInput();
    }


    private static void BackpackInput(){

        while(true){

            System.out.println("Welcome to Backpack\n1. Enter as instructor\n2. Enter as student\n3. Exit");
            Scanner sc = new Scanner(System.in);
            int mainInput = sc.nextInt();
            switch (mainInput) {
                case 1:
                    displayInstructors();
                    System.out.print("Choose ID: ");
                    int instID = sc.nextInt();
                    System.out.println("----------------------------------------");
                    instructorFunctions(instID);
                    break;

                case 2:
                    displayStudents();
                    System.out.print("Choose ID: ");
                    int stuID = sc.nextInt();
                    System.out.println("----------------------------------------");
                    studentFunctions(stuID);
                    break;

                case 3:
                    System.out.println("Thank you for using backpack!");
                    return;
            
                default:
                    System.out.println("ERROR! Invalid input.");
                    break;
            }

            System.out.println("----------------------------------------");
        }
        
    }


    private static void displayInstructors(){
        System.out.println("Instructors:");
        int count = 0;
        for (Instructor i : _instructors){
            System.out.println(count + " - " + i.getName());
            count++;
        }
        return;
    }

    private static void displayStudents(){
        System.out.println("Students: ");
        int count = 0;
        for (Student s : _students){
            System.out.println(count + " - " + s.getName());
            count++;
        }
    }


    private static void instructorFunctions(int instID){

        Instructor i = _instructors.get(instID);
        Scanner scan = new Scanner(System.in);

        while (true){

            System.out.println("Welcome " + i.getName());
            System.out.println("INSTRUCTOR MENU\n1. Add class material\n2. Add assessments\n3. View lecture materials\n4. View assessments\n5. Grade assessments\n6. Close assessment\n7. View comments\n8. Add comments\n9. Logout");
            int instChoice = scan.nextInt();
            scan.nextLine();

            switch (instChoice) {

                case 1:
                    System.out.println("1. Add Lecture Slide\n2. Add Lecture Video");
                    instChoice = scan.nextInt();
                    scan.nextLine();


                    if (instChoice == 1){

                        System.out.print("Enter topic of slides: ");
                        String slideTopic = scan.nextLine();

                        System.out.print("Enter number of slides: ");
                        int slideNum = scan.nextInt();
                        scan.nextLine();

                        System.out.println("Enter content of slides");
                        String slideContents [] = new String [slideNum];

                        for (int x = 0; x < slideNum; x++){
                            System.out.print("Content of slide " + (int) (x+1) + ": ");
                            slideContents[x] = scan.nextLine();
                        }

                        LectureSlide ls = new LectureSlide(slideTopic, slideContents, i, getTime());
                        _material.add(ls);
                    }

                    else if (instChoice == 2){

                        System.out.print("Enter topic of video: ");
                        String videoTopic = scan.nextLine();
                        
                        System.out.print("Enter filename of video: ");
                        String videoName = scan.next();

                        if (!(videoName.length() > 4 && videoName.substring(videoName.length()-4).equals(".mp4"))){
                            System.out.println("ERROR! File format incorrect.");
                            break;
                        }

                        LectureVideo lv = new LectureVideo(videoTopic, videoName, i, getTime());
                        _material.add(lv);
                    }

                    else{
                        System.out.println("ERROR! Invalid choice.");
                    }

                    break;

                case 2:

                    System.out.println("1. Add Assignment\n2. Add Quiz");
                    int assChoice = scan.nextInt();
                    scan.nextLine();
                    
                    if (assChoice == 1){
                        System.out.print("Enter problem statement: ");
                        String ps = scan.nextLine();
                        System.out.print("Enter max marks: ");
                        int maxMarks = scan.nextInt();
                        Assignment assign = new Assignment(ps, maxMarks);
                        _assessments.add(assign);
                        for (Student s : _students){
                            s.addPendingAndGrades();
                        }
                    }

                    else if (assChoice == 2){
                        System.out.print("Enter quiz question: ");
                        String qq = scan.nextLine();
                        Quiz q = new Quiz(qq);
                        _assessments.add(q);
                        for (Student s : _students){
                            s.addPendingAndGrades();
                        }
                    }

                    else{
                        System.out.println("ERROR! Invalid choice.");
                    }
                    
                    break;

                case 3:
                    
                    viewLectureMaterial();

                    break;

                case 4:

                    viewAssessments();
                    
                    break;

                case 5:

                    System.out.println("List of assessments: ");

                    if (viewAssessments()){

                        System.out.print("Enter ID of assessment to view submissions: ");
                        instChoice = scan.nextInt();
                        System.out.println("Choose ID from these ungraded submissions: ");

                        int iter = 0;
                        boolean flag = false;
                        for (Student s : _students){
                            if (s.viewUngradedSubmissions(instChoice)){
                                System.out.println(iter + ". " + s.getName());
                                flag = true;
                            }

                            iter++;
                        }

                        if (flag == false){
                            System.out.println("Sorry! No ungraded submissions");
                            break;
                        }


                        int sub = scan.nextInt();

                        System.out.println("Submission: " + _students.get(sub).getSubName(instChoice));
                        System.out.println("----------------------------------------");
                        System.out.println("Max Marks: " + _assessments.get(instChoice).getMaxMarks());
                        System.out.print("Marks scored: ");
                        int marksObtained = scan.nextInt();
                        
                        _students.get(sub).setMarks(marksObtained, instChoice, i);
                    }

                
                    
                    break;

                case 6:

                    System.out.println("List of Open Assignments:");

                    instChoice = 0;
                    for (Assessments ass : _assessments){
                        if (!ass.getClose()){
                            ass.view(instChoice);
                            System.out.println();
                        }
                        instChoice++;
                    }

                    if (instChoice == 0){
                        break;
                    }

                    System.out.print("Enter ID of the assignment you want to close: ");
                    int assID = scan.nextInt();
                    _assessments.get(assID).close();

                    for (Student s : _students){
                        s.setPendingFalse(assID, i);
                    }
                    
                    break;

                case 7:

                    readComments();
                    
                    break;

                case 8:

                    System.out.print("Enter comment: ");
                    String content = scan.nextLine();    
                    addComments(content, i);
                    
                    break;
                
                case 9:
                    return;
            
                default:
                    System.out.println("ERROR! Invalid input.");
                    break;
            }

            System.out.println("----------------------------------------");
        }
    }

    private static void studentFunctions(int stuID){
        Student s = _students.get(stuID);
        Scanner scan = new Scanner(System.in);

        while (true){

            System.out.println("Welcome " + s.getName());
            System.out.println("STUDENT MENU\n1. View lecture materials\n2. View assessments\n3. Submit assessment\n4. View grades\n5. View comments\n6. Add comments\n7. Logout");
            int stuChoice = scan.nextInt();
            scan.nextLine();

            switch (stuChoice) {
                case 1:
                    
                    viewLectureMaterial();

                    break;

                case 2:

                    viewAssessments();
                    
                    break;

                case 3:

                    //FIX for empty

                    System.out.println("Pending Assessments");
                    int choice = 0;
                    boolean flag = false;
                    LinkedList <Boolean> pending =  s.getPending();
                    for (Assessments ass : _assessments){
                        if (pending.get(choice)){
                            ass.view(choice);
                            System.out.println();
                            flag = true;
                        }
                        choice++;
                    }

                    if (!flag){
                        System.out.println("No pending assessments");
                        break;
                    }

                    System.out.print("Enter ID of assessment: ");
                    int subID = scan.nextInt();
                    scan.nextLine();

                    if (_assessments.get(subID).getClass().getName().equals("Assignment")){  //
                        System.out.print("Enter filename of assignment: ");
                        String fileName = scan.next();
                        if (!(fileName.length() > 4 && fileName.substring(fileName.length()-4).equals(".zip"))){
                            System.out.println("ERROR! File format incorrect.");
                            break;
                        }
                        s.addFile(fileName, subID);
                        
                    }

                    else if (_assessments.get(subID).getClass().getName().equals("Quiz")){
                        Quiz q = (Quiz) _assessments.get(subID);
                        System.out.print(q.getQuestion() + " ");
                        String answer = scan.next();
                        s.addFile(answer, subID);
                    }

                    
                    break;

                case 4:

                    s.viewSubmissions(_assessments);

                    break;

                case 5:

                    readComments();
                    
                    break;

                case 6:

                    System.out.print("Enter comment: ");
                    String content = scan.nextLine();    
                    addComments(content, s);
                    
                    break;

                case 7:
                    return;
            
                default:

                    System.out.println("ERROR! Invalid input.");
                    break;
            }

            System.out.println("----------------------------------------");
        }
    }

    private static String getTime(){
        LocalDateTime timeObject = LocalDateTime.now();
        DateTimeFormatter formattingObject = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss ");
        String time = timeObject.format(formattingObject);
        TimeZone curzone = TimeZone.getDefault();
        time += curzone.getDisplayName(false, 0);
        formattingObject = DateTimeFormatter.ofPattern(" yyyy");
        time += timeObject.format(formattingObject);
        return time;
    }

    private static void viewLectureMaterial(){
        for (ClassMaterial cm : _material){
            cm.view();
            System.out.println();
        }
    }

    private static boolean viewAssessments(){
        int choice = 0;
        for (Assessments ass : _assessments){
            ass.view(choice);
            System.out.println();
            choice++;
        }
        if (choice == 0){
            return false;
        }
        return true;
    }

    private static void readComments() {
        for (Comment c : _comments){
            System.out.println(c.getContent() + " - " + c.getUserName());
            System.out.println(c.getDate()); 
            System.out.println();
        }
    }

    private static void addComments(String content, UserID u){
        Comment c = new Comment(content, u, getTime()); 
        _comments.add(c);
    }

}