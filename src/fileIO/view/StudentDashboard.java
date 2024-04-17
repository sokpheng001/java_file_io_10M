package fileIO.view;

import fileIO.controller.StudentController;

import fileIO.model.Student;
import fileIO.utils.DataAction;
import fileIO.utils.StudentDataTable;

import java.time.LocalDate;
import java.util.*;

public class StudentDashboard {
    private final static StudentController studentController = new StudentController();
    private static Student insertNewStudentsInfo(){
        System.out.println("> Insert student's info".toUpperCase(Locale.ROOT));
        System.out.print("[+] Insert student's name: ");
        String name = new Scanner(System.in).nextLine();
//
        System.out.println("[+] Student date of birth ".toUpperCase(Locale.ROOT));
        System.out.print("> Year (number): ");
        int year = new Scanner(System.in).nextInt();
        System.out.print("> Month (number): ");
        int month  =new Scanner(System.in).nextInt();
        System.out.print("> Day (number): ");
        int day = new Scanner(System.in).nextInt();
//
        System.out.println("[!] You can insert multi classes by splitting [,] symbol (c1,c2).".toUpperCase(Locale.ROOT));
        System.out.print("[+] Student's class: ");
        String studentClass = new Scanner(System.in).nextLine();
        String[] classes  =studentClass.split(",");
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
        return new Student(STR."\{new Random().nextInt(10000)}CSTAD",name, LocalDate.of(year,month,day).toString(),classes,subjects);
    }
    private static void options(){
        System.out.println("=".repeat(100));
        System.out.print("\t\t1. Add new student\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("2. List all students\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("3. Commit data to file\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t4. Search Student by ID\t\t".toUpperCase(Locale.ROOT));
        System.out.print("5. Update students' Info by ID\t".toUpperCase(Locale.ROOT));
        System.out.print("6. Delete student's data by ID.\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t7. Generate Data to File\t".toUpperCase(Locale.ROOT));
        System.out.print("8. Restore Data\t\t\t\t\t".toUpperCase(Locale.ROOT));
        System.out.print("9. Delete/Clear all data.\n".toUpperCase(Locale.ROOT));
        System.out.print("\t\t0, 99. Exit");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCopyright-CSTAD-2024");
        System.out.println("=".repeat(100));
    }
    private static void checkInTransaction(){
        if(studentController.checkDataIsAvailableInTransaction()){
            System.out.print(STR."> Commit your \{DataAction.numberOfDataHasBeenStoredInTransactionFile} data record before hand [Y/N]: ");
            String opt = new Scanner(System.in).nextLine();
            if(opt.equalsIgnoreCase("Y")){
                studentController.commitDataFromTransaction();
            }
            DataAction.addDataToTransaction(new ArrayList<>());
        }
    }
    public static void view(){
        checkInTransaction();
        while (true){
            options();
            System.out.print("> Insert option: ");
            String opt = new Scanner(System.in).nextLine();
            System.out.println(".".repeat(30));
            switch (Integer.parseInt(opt.trim())){
//                System.out.println(".".repeat(30));
                case 0,99->{
                    checkInTransaction();
                    System.out.println("[*] System closed !!! :)".toUpperCase(Locale.ROOT));
                    System.exit(0);
                }
                case 1->{
                    studentController.addNewStudent((insertNewStudentsInfo()));
                }
                case 2->{
                    System.out.println("[*] Students' Data".toUpperCase(Locale.ROOT));
                    StudentDataTable.dataTable(studentController.listAllStudents());
                }
                case 3->{
                    studentController.commitData();
                }
                case 4->{
                    System.out.println("[*] Search student by ID".toUpperCase(Locale.ROOT));
                    System.out.print("> Insert student's ID: ");
                    String id = new Scanner(System.in).nextLine();
                    try{
                        System.out.println("[*] Student's Info.".toUpperCase(Locale.ROOT));
                        StudentDataTable.dataTable(new ArrayList<>(
                                List.of(studentController.searchStudentById(id.trim()))
                        ));
                    }catch (NoSuchElementException exception){
                        StudentDataTable.dataTable(new ArrayList<>(),STR." No such a student you found with ID \"\{id}\"".toUpperCase(Locale.CANADA));
                    }
                }
                case 5->{
                    System.out.println("[*] Update student by ID".toUpperCase(Locale.ROOT));
                    System.out.print("> Insert student's ID: ");
                    String id = new Scanner(System.in).nextLine();
                    try{
                        System.out.println("[*] Student's Info.".toUpperCase(Locale.ROOT));
                        StudentDataTable.dataTable(new ArrayList<>(
                                List.of(studentController.updateStudentById(id.trim()))
                        ));
                    }catch (NoSuchElementException exception){
                        StudentDataTable.dataTable(new ArrayList<>(),STR." No such a student you found with ID \"\{id}\"".toUpperCase(Locale.CANADA));
                    }
                }
                case 6->{
                    System.out.println("[*] Delete student by ID".toUpperCase(Locale.ROOT));
                    System.out.print("> Insert student's ID: ");
                    String id = new Scanner(System.in).nextLine();
                    try{
                        System.out.println("[*] Student Info you have deleted.".toUpperCase(Locale.ROOT));
                        StudentDataTable.dataTable(studentController.deleteStudentById((id.trim())));
                    }catch (NoSuchElementException exception){
                        StudentDataTable.dataTable(new ArrayList<>(), STR." No such a student you found with ID \"\{id}\"".toUpperCase(Locale.CANADA));
                    }
                }
                case 7->{
                    studentController.generateObjects();
                }
                case 8->{

                }
                case 9->{
                    studentController.destroyData();
                }
                default -> {
                    System.out.println("[!] No Option :(.".toUpperCase(Locale.ROOT));
                }
            }
        }
    }
}
