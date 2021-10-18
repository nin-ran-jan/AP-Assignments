import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Course{


    private static LinkedList<Instructor> _instructors;
    private static LinkedList <ClassMaterial> _material;
    private static LinkedList <Student> _students;
    private static LinkedList<Assessments> _assessments;


    {
        _instructors = new LinkedList<Instructor>();
        _students = new LinkedList<Student>();
        _material = new LinkedList<ClassMaterial>();
        _assessments = new LinkedList<Assessments>();
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
        this.BackpackInput();
    }


    public void BackpackInput(){

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
                    System.out.println("----------------------------------------");
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


    public void displayInstructors(){
        System.out.println("Instructors:");
        int count = 0;
        for (Instructor i : _instructors){
            System.out.println(count + " - " + i.getName());
            count++;
        }
        return;
    }


    public void instructorFunctions(int instID){

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
                    
                    for (ClassMaterial cm : _material){
                        cm.view();
                        System.out.println();
                    }

                    break;

                case 4:

                    instChoice = 0;
                    for (Assessments ass : _assessments){
                        ass.view(instChoice);
                        System.out.println();
                        instChoice++;
                    }
                    
                    break;

                case 5:
                    
                    break;

                case 6:

                    instChoice = 0;
                    for (Assessments ass : _assessments){
                        if (!ass.getClose()){
                            ass.view(instChoice);
                            System.out.println();
                        }
                        instChoice++;
                    }

                    System.out.print("Enter ID of the assignment you want to close: ");
                    int assID = scan.nextInt();
                    _assessments.get(assID).close();

                    for (Student s : _students){
                        s.setPendingFalse(assID);
                    }
                    
                    break;

                case 7:
                    
                    break;

                case 8:
                    
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

    public static String getTime(){
        LocalDateTime timeObject = LocalDateTime.now();
        DateTimeFormatter formattingObject = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss ");
        String time = timeObject.format(formattingObject);
        TimeZone curzone = TimeZone.getDefault();
        time += curzone.getDisplayName(false, 0);
        formattingObject = DateTimeFormatter.ofPattern(" yyyy");
        time += timeObject.format(formattingObject);
        return time;
    }

}