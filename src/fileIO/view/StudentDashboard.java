package fileIO.view;

import fileIO.controller.StudentController;

import fileIO.model.Student;

import fileIO.utils.DataAction;
import fileIO.utils.SoundUtils;
import fileIO.utils.StudentDataTable;

import javax.xml.transform.sax.SAXSource;
import java.time.LocalDate;
import java.util.*;

public class StudentDashboard {
    private final static StudentController studentController = new StudentController();
    private static String insertStudentDateOfBirth(){
        String year = null;
        String month = null;
        String day = null;
        boolean isInserted = true;
        while (isInserted){
            System.out.print("> Year (number): ");
            year = new Scanner(System.in).nextLine();
            System.out.print("> Month (number): ");
            month = new Scanner(System.in).nextLine();
            System.out.print("> Day (number): ");
            day = new Scanner(System.in).nextLine();
            isInserted = false;
        }
        return LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)).toString();
    }
    private static Student viewForUpdateStudentInfo(Student student){
//
        String data;
//
        System.out.println("=".repeat(62));
        System.out.println("[+] Update Student's Information: ".toUpperCase(Locale.ROOT));
        System.out.println("1. Update Student's Name");
        System.out.println("2. Update Student's Date of birth");
        System.out.println("3. Update Student's Class");
        System.out.println("4. Update Student's Subject");
        System.out.println(".".repeat(20));
        System.out.print("> Insert option: ");
        SoundUtils.alertSound();
        String opt = new Scanner(System.in).next();
        switch (Integer.parseInt(opt)){
            case 1->{
                System.out.print("[+] Insert NEw student's name: ");
                data = new Scanner(System.in).nextLine();
                student.setStudentName(data);
            }
            case 2->{
                System.out.println("[+] Insert NEw student's date of birth: ");
                student.setStudentDateOfBirth(insertStudentDateOfBirth());
            }
            case 3->{
                System.out.println("[!] You can insert multi classes by splitting [,] symbol (c1,c2).".toUpperCase(Locale.ROOT));
                System.out.print("[+] Insert NEw student's class: ");
                String classStudied = new Scanner(System.in).nextLine();
                String [] classes = classStudied.split(",");
                for(int i=0;i<classes.length;i++){
                    classes[i] = classes[i].trim();
                }
                student.setStudentClasses(classes);
            }
            case 4->{
                System.out.println("[!] You can insert multi subjects by splitting [,] symbol (s1,s2).".toUpperCase(Locale.ROOT));
                System.out.print("[+] Insert NEw student's Subject studied: ");
                String studentSubject = new Scanner(System.in).nextLine();
                // remove space from insert for any subject
                String[] subjects = studentSubject.split(",");
                for(int i=0;i<subjects.length;i++){
                    subjects[i] = subjects[i].trim();
                }
                student.setStudentSubjects(subjects);
            }
            default -> System.out.println("[!] Invalid Option. :(");
        }
        return student;
    }
    private static Student insertNewStudentsInfo(){
        System.out.println("> Insert student's info".toUpperCase(Locale.ROOT));
        System.out.print("[+] Insert student's name: ");
        String name = new Scanner(System.in).nextLine();
//
        System.out.println("[+] Student date of birth ".toUpperCase(Locale.ROOT));
        String dateOfBirth = insertStudentDateOfBirth();
//
        System.out.println("[!] You can insert multi classes by splitting [,] symbol (c1,c2).".toUpperCase(Locale.ROOT));
        System.out.print("[+] Student's class: ");
        String studentClass = new Scanner(System.in).nextLine();
        String[] classes  = studentClass.split(",");
//
        for(int i=0;i<classes.length;i++){
            classes[i] = classes[i].trim();
        }
        System.out.println("[!] You can insert multi subjects by splitting [,] symbol (s1,s2).".toUpperCase(Locale.ROOT));
        System.out.print("[+] Subject studied: ");
        String studentSubject = new Scanner(System.in).nextLine();
        // remove space from insert for any subject
        String[] subjects = studentSubject.split(",");
        for(int i=0;i<subjects.length;i++){
            subjects[i] = subjects[i].trim();
        }
        return new Student(STR."\{new Random().nextInt(10000)}CSTAD",name, dateOfBirth,classes,subjects);
    }
    private static void options(){
        System.out.println("=".repeat(100));
        System.out.print("\t\t1. Add new student\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("2. List all students\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("3. Commit data to file\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t4. Search Student\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("5. Update students' Info by ID\t".toUpperCase(Locale.ROOT));
        System.out.print("6. Delete student's data by ID.\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t7. Generate Data to File\t".toUpperCase(Locale.ROOT));
        System.out.print("8. Back Up Data\t\t\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("9. Restore Data\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t10. Delete/Clear all data from Data STORE".toUpperCase(Locale.CANADA));
        System.out.print("\t\t0, 99. Exit");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCopyright-CSTAD-2024");
        System.out.println("=".repeat(100));
    }
    private static void checkInTransaction(){
        if(studentController.checkDataIsAvailableInTransaction()){
            boolean isCommitted = true;
            SoundUtils.alertSound();
            while (isCommitted){
                System.out.print(STR."> Commit your \{DataAction.numberOfDataHasBeenStoredInTransactionFile} data record before hand [Y/N]: ");
                String opt = new Scanner(System.in).nextLine();
                if(opt.equalsIgnoreCase("Y")){
                    studentController.commitDataFromTransaction();
                    isCommitted = false;
                    DataAction.addDataToTransaction(null,"transaction-addNew.dat");
                    DataAction.addDataToTransaction(null,"transaction-update.dat");
                    DataAction.addDataToTransaction(null,"transaction-delete.dat");
                }else if(opt.equalsIgnoreCase("n")){
                    isCommitted = false;
                }else {
                    SoundUtils.windowsRingSound();
                }
            }
        }
    }
//    pagination
    private static void pagination(List<Student> studentList){
        StudentDataTable.studentDataTable(studentList,4,0,1, "No such a student found");
        try{
            int start = 0;
            if(!studentList.isEmpty()){
                int page=1;
                int numberOfRecordForEachPage = 4;
//
                if(studentList.size()<=numberOfRecordForEachPage){
                    System.out.println("[!] LAST PAGE <<");
                }
//
                while (true) {
                    System.out.print("[+] Insert to Navigate [p/N]: ");
                    String op = new Scanner(System.in).nextLine();
                    if(op.isEmpty()){
                        SoundUtils.windowsRingSound();
                    }
                    if(op.equalsIgnoreCase("b") || op.equalsIgnoreCase("back")){
                        break;
                    }else if(op.isEmpty()){
                        SoundUtils.windowsRingSound();
                        continue;
                    }
                    if(op.equalsIgnoreCase("n") || op.equalsIgnoreCase("next")){
                        start+=4;
                        if(start+numberOfRecordForEachPage>studentList.size()){
                            start-=4;
                            System.out.println("[!] LAST PAGE <<");
                            SoundUtils.windowsRingSound();
                        }else if(start>=studentList.size()){
                            start = studentList.size()-1;
                        }
                        StudentDataTable.studentDataTable(studentList,numberOfRecordForEachPage, start,page, "No such a student found");
                        page++;
                    }else if ((op.equalsIgnoreCase("p")) || op.equalsIgnoreCase("previous") ){
                        if(start!=0){
                            start-=4;
                        }
                        if(page>1){
                            page--;
                        }
                        StudentDataTable.studentDataTable(studentList,numberOfRecordForEachPage, start,page,"No such a student found");
                    }
                }
            }
        }catch (VirtualMachineError | Exception problem){
            SoundUtils.windowsRingSound();
            System.out.println("[!] Problem during listing data.".toUpperCase(Locale.ROOT));
        }
    }

    public static void view(){
        checkInTransaction();
        while (true){
            options();
            System.out.print("> Insert option: ");
            String opt = new Scanner(System.in).nextLine();
            System.out.println(".".repeat(30));
            switch ((opt.trim())){
//                System.out.println(".".repeat(30));
                case "0", "99"->{
                    checkInTransaction();
                    System.out.println("[*] System closed !!! :)".toUpperCase(Locale.ROOT));
                    System.exit(0);
                }
                case "1"-> studentController.addNewStudent((insertNewStudentsInfo()));
                case "2"->{
                    List<Student> studentList = studentController.listAllStudents();
                    pagination(studentList);
                }
                case "3"-> studentController.commitData();
                case "4"->{
                    boolean isFour = true;
                    while (isFour){
                        System.out.println("[+] Searching student".toUpperCase(Locale.CANADA));
                        System.out.println(".".repeat(20));
                        System.out.println("1. Search by Name".toUpperCase());
                        System.out.println("2. Search by ID".toUpperCase());
                        System.out.println("- (back/b) to Back".toUpperCase());
                        System.out.println(".".repeat(20));
                        System.out.print(">> Insert option: ");
                        String o = new Scanner(System.in).nextLine();
                        o = o.toLowerCase(Locale.CANADA);
                        System.out.println("...");
                        switch (o){
                            case "1"->{
                                System.out.println("[*] Search student by Name".toUpperCase(Locale.ROOT));
                                System.out.print(">>> Insert student's NAME: ");
                                String name = new Scanner(System.in).nextLine();
                                pagination(studentController.searchStudentByName(name));
                            }
                            case "2"->{
                                System.out.println("[*] Search student by ID".toUpperCase(Locale.ROOT));
                                System.out.print(">>> Insert student's ID: ");
                                String id = new Scanner(System.in).nextLine();
                                try{
                                    StudentDataTable.tableFromSearchedResult(new ArrayList<>(
                                            List.of(studentController.searchStudentById(id.trim()))
                                    ));
                                }catch (NoSuchElementException exception){
                                    StudentDataTable.studentDataTable(new ArrayList<>(),null,0,1, "No such a student found".toUpperCase(Locale.CANADA));
                                    SoundUtils.windowsRingSound();
                                }
                            }
                            case "b","back"->{
                                isFour = false;
                            }
                            default -> {
                                System.out.println("[!] No Option :(.".toUpperCase(Locale.ROOT));
                                System.out.println(".".repeat(20));
                                SoundUtils.windowsRingSound();
                            }
                        }
                    }

                }
                case "5"->{
                    System.out.println("[*] Update student by ID".toUpperCase(Locale.ROOT));
                    System.out.print("> Insert student's ID: ");
                    String id = new Scanner(System.in).nextLine();
                    try{
                        System.out.println("[*] Student's Info.".toUpperCase(Locale.ROOT));
                        Student student  = studentController.searchStudentById(id.trim());
                        if(student!=null){
                            StudentDataTable.tableFromSearchedResult(new ArrayList<>(
                                    List.of(studentController.searchStudentById(id.trim())
                                    )));
                            StudentDataTable.tableFromSearchedResult(new ArrayList<>(
                                    List.of(studentController.updateStudentById(id.trim(),viewForUpdateStudentInfo(student)))
                            ));
                        }
                        System.out.print("[+] Updated successfully, press to continue...".toUpperCase(Locale.ROOT));
                        SoundUtils.alertSound();
                        new Scanner(System.in).nextLine();
                    }catch (NoSuchElementException exception){
                        StudentDataTable.studentDataTable(new ArrayList<>(),null,1,0,STR." No such a student you found with ID \"\{id}\"".toUpperCase(Locale.CANADA));
                    }
                }
                case "6"->{
                    System.out.println("[*] Delete student by ID".toUpperCase(Locale.ROOT));
                    System.out.print(">> Insert student's ID: ");
                    String id = new Scanner(System.in).nextLine();
                    try{
                        StudentDataTable.tableFromSearchedResult(new ArrayList<>(
                                List.of(studentController.searchStudentById(id.trim()))
                        ));
                        id = id.trim();
                        studentController.deleteStudentById(id);
                    }catch (NoSuchElementException exception){
                        SoundUtils.windowsRingSound();
                        StudentDataTable.studentDataTable(new ArrayList<>(),null,1,0 ,STR." No such a student you found with ID \"\{id}\"".toUpperCase(Locale.CANADA));
                    }
                }
                case "7"-> studentController.generateObjects();
                case "8"->{
                    System.out.println("[+] BackUp date process".toUpperCase(Locale.ROOT));
                    System.out.println("......");
                    System.out.print("[+] Insert file backup name: ");
                    SoundUtils.alertSound();
                    String fileName = new Scanner(System.in).nextLine();
                    studentController.backUp(fileName);
                }
                case "9"->{
                    if(!studentController.restoreFile().isEmpty()){
                        System.out.println("[+] List of Restoring File".toUpperCase(Locale.ROOT));
                        System.out.println("........");
                        System.out.println(                        studentController.restoreFile().get(2));
                    }else {
                        System.out.println(">> No restoring file ".toUpperCase(Locale.ROOT));
                        SoundUtils.windowsRingSound();
                    }
                }
                case "10"->{
                    SoundUtils.alertSound();
                    studentController.destroyData();
                }
                default -> {
                    SoundUtils.windowsRingSound();
                    System.out.println("[!] No Option :(.".toUpperCase(Locale.ROOT));
                }
            }
        }
    }
}
