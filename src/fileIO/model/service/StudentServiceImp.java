package fileIO.model.service;
import fileIO.model.Student;
import fileIO.utils.DataAction;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class StudentServiceImp implements StudentService{
    private List<Student> students = readObjectFromFile();
    private List<Student> tempoList = new ArrayList<>();
    private final String outputFilePath = "output/data.bin";
    private void writeDataTempo(Student student){
        try{
//            students = readObjectFromFile();
//            assert students != null;
            students.add(student);
            System.out.println("[*] Student has been add successfully. \n[!] To store data permanently, please commit it (Start option 3).".toUpperCase(Locale.ROOT));
        }catch (Exception ignore){}
    }
    private void commitObjectToFile(List<Student> data,int ...numberOfObject){
        try(ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(outputFilePath)
                )
        )
        ){
            if(students==null){
                students = new ArrayList<>();
                objectOutputStream.writeObject(students);
            }else {
                if(Objects.equals(data, new ArrayList<>())){
                    students.clear();
//                    students.addAll(data);
                    objectOutputStream.writeObject(students);
                }else {
                    long startedTime = System.currentTimeMillis();
//                    students.addAll(data);
                    objectOutputStream.writeObject(students);
                    long endedTime = System.currentTimeMillis();
                    System.out.println(STR."[+] Spent time for writing data: \{(double)(endedTime - startedTime) / 1000} s".toUpperCase(Locale.ROOT));
                    System.out.println(STR."[+] Wrote data \{numberOfObject[0]} record successfully.".toUpperCase(Locale.ROOT));
                }
            }
        }catch (IOException problem){
            System.out.println(STR."Problem during writing hero to a file: \{problem.getMessage()}");
//            System.out.println(problem.getMessage());
        }
////
    }
    private List<Student> readObjectFromFile(){
        try(ObjectInputStream objectInputStream
                    = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(outputFilePath)
                )
        )){

            long startedTime = System.currentTimeMillis();
            @SuppressWarnings("unchecked")
            List<Student> newStudents = (List<Student>) objectInputStream.readObject();
            long endedTime = System.currentTimeMillis();
            System.out.println(STR."[*] Spent time for reading data: \{(double)(endedTime - startedTime) / 1000}s".toUpperCase(Locale.ROOT));
            System.out.println(STR."[*] Number of record: \{newStudents.size()}".toUpperCase(Locale.ROOT));// data table
            return newStudents;
        }catch (IOException | ClassNotFoundException exception){
            if(exception.getMessage()==null){
                System.out.println("[!] No Data to read".toUpperCase(Locale.ROOT));
            }else {
                System.out.println(STR."Problem during reading all objects in a file: \{exception.getMessage()}".toUpperCase(Locale.ROOT));
            }
        }
        return null;
    }
    private static void updateProgress(double progress) {
        int width = 50; // width of the progress bar
        int completed = (int) (progress * width / 100);
        System.out.print("\r[");
        for (int i = 0; i < completed; i++) {
            System.out.print("=");
        }
        for (int i = completed; i < width; i++) {
            System.out.print(" ");
        }
        System.out.printf("] %.2f%%", progress);
    }
    @Override
    public void generateDesignedObjectForWriting() {
        int number = -1;
        while ((number<0) || (number>(1_000_000_000))){
            System.out.print("[+] Number of objects you want to generate (100M - 1_000_000_000): ");
            number = new Scanner(System.in).nextInt();
        }
        long startedTime = System.currentTimeMillis();
        List<Student> heroes = new ArrayList<>();
        long endedTime = System.currentTimeMillis();
//        System.out.println("Time initialized: " +(double) (endedTime-startedTime)/1000 + "s");
        Student student = new Student
                (STR."\{new Random().nextInt(10000)}CSTAD",
                        "Kim Chansokpheng",
                        LocalDate.of(2004,6,21).toString(),
                        new String[]{"DevOps, Blockchains"},new String[]{"Cyber security, DevOps, Blockchains"});
        for(int i=0;i<number;i++){
            heroes.add(student);
//                loading animation
        }
        if(students==null){
            students = new ArrayList<>();
        }
        students.addAll(heroes);
        commitObjectToFile(students,number);
    }
    @Override
    public int addNewStudent(Student student) {
        tempoList.add(student);
//
        DataAction.addDataToTransaction(tempoList);
//
        writeDataTempo(student);
        return 0;
    }
    @Override
    public List<Student> listAllStudents() {
        Collections.reverse(students);
        return students;
    }
    @Override
    public void commitDataToFile() {
        if(students != null){
            if(tempoList.isEmpty()){
                System.out.println("[!] No Data to commit.".toUpperCase(Locale.ROOT));
            }else {
                try{
                    Collections.reverse(students);
                    commitObjectToFile(students,students.size());
                    DataAction.addDataToTransaction(new ArrayList<>());
//
                }catch (Exception exception){
                    System.out.println(STR."[!] Problem during committing data: \{exception.getMessage()}");
                }
            }
        }else {
            System.out.println("[!] No Data to commit".toUpperCase(Locale.ROOT));
        }
    }

    @Override
    public void commitFromTransaction() {
        try{
            if(students.isEmpty()){
                students.addAll(0,Objects.requireNonNull(DataAction.readFromTransaction()));
            }else {
                for(Student student: Objects.requireNonNull(DataAction.readFromTransaction())){
                    if(!students.contains(student)){
                        students.add(student);
                    }
                }
//                students.addAll(Objects.requireNonNull(DataAction.readFromTransaction()));
            }
            commitObjectToFile(students,DataAction.numberOfDataHasBeenStoredInTransactionFile);
        }catch (Exception ignore){}
    }

    @Override
    public void destroyData() {
        commitObjectToFile(new ArrayList<>());
        System.out.println("[!] Data has been cleared successfully.".toUpperCase(Locale.ROOT));
    }

    @Override
    public Boolean checkIsDataAvailableInTransaction() {
        return DataAction.readFromTransaction() !=null;
    }

    @Override
    public Student searchStudentById(String id) throws NoSuchElementException{
        try{
            return students.stream().filter(e->e.getId().equals(id)).findAny().get();
        }catch (NullPointerException exception){
            System.out.println(STR."[!] Problem: \{exception.getMessage()}");
            return new Student();
        }
    }

    @Override
    public List<Student> deleteStudentById(String id) {
        System.out.print("[!] Are you sure to delete [Y/N]: ");
        String opt = new Scanner(System.in).nextLine();
        Student deletedStudent;
        if(opt.equalsIgnoreCase("y")){
            if((students.contains(students.stream().filter(e->e.getId().equals(id)).findAny().get()))){
//                deletedStudent = students.stream().filter(e->e.getId().equals(id)).findAny().get();
                students.removeIf(e->e.getId().equals(id));
                System.out.println(STR."[!] Length of list after deleted: \{students.size()}");
                if(students.isEmpty()){

                }else {
                    tempoList.addAll(students);
                }
                DataAction.addDataToTransaction(tempoList);
            }else {
                throw new NoSuchElementException();
            }
            return students;
        }else {
            System.out.println("[+] Deleting data was canceled.".toUpperCase(Locale.ROOT));
            return students;
        }
    }

    @Override
    public Student updateStudentById(String id) {
        if((students.contains(students.stream().filter(e->e.getId().equals(id)).findAny().get()))){
            Student student = students.stream().filter(e->e.getId().equals(id)).findAny().get();
            student.setStudentName("Kim Chi");
            return student;
        }else {
            throw new NoSuchElementException();
        }
    }
}
