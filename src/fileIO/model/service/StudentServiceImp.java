package fileIO.model.service;
import fileIO.model.Student;
import fileIO.utils.DataAction;
import fileIO.utils.SoundUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
            SoundUtils.alertSound();
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
            System.out.println(STR."[*] Number of record in data source file: \{newStudents.size()}".toUpperCase(Locale.ROOT));// data table
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
                        new String[]{"DevOps, Blockchains"},
                        new String[]{"Cyber security, DevOps, Blockchains"},
                        LocalDate.now().toString());
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
        DataAction.addDataToTransaction(tempoList,"transaction-addNew.dat");
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
                SoundUtils.windowsRingSound();
            }else {
                try{
                    Collections.reverse(students);
                    commitObjectToFile(students,students.size());
                    DataAction.addDataToTransaction(new ArrayList<>(),"transaction-addNew.dat");
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
        String [] fileToRead = {"transaction-addNew.dat","transaction-update.dat","transaction-delete.dat"};
        boolean isDataFileContainData = false;
        for(String fileName: fileToRead ){
            if(!fileName.equalsIgnoreCase(fileToRead[0])){
                if(checkIsDataAvailableInTransaction(fileName)){
                    students.clear();
                    for(Student student: Objects.requireNonNull(DataAction.readFromTransaction(fileName))){
                        if(!students.contains(student)){
                            students.add(student);
                            Collections.reverse(students);
                        }
                    }
                }
            }else {
                if(checkIsDataAvailableInTransaction(fileName)){
                    for(Student student: Objects.requireNonNull(DataAction.readFromTransaction(fileName))){
                        if(!students.contains(student)){
                            students.add(student);
                            Collections.reverse(students);
                        }
                    }
                }
            }
        }
        try{
            commitObjectToFile(students,DataAction.numberOfDataHasBeenStoredInTransactionFile);
        }catch (Exception ignore){}
    }

    @Override
    public void destroyData() {
        commitObjectToFile(new ArrayList<>());
        System.out.println("[!] Data has been cleared successfully.".toUpperCase(Locale.ROOT));
    }

    @Override
    public Boolean checkIsDataAvailableInTransaction(String fileToCheck) {
        return DataAction.readFromTransaction(fileToCheck)!=null;
    }

    @Override
    public List<Student> searchStudentById(String id) throws NoSuchElementException{
        try{
            return students.stream().filter(e->e.getId().equals(id)).toList();
        }catch (NullPointerException exception){
            System.out.println(STR."[!] Problem: \{exception.getMessage()}");
            return new ArrayList<>();
        }
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        return students.stream()
                .filter(e->e.getStudentName().toLowerCase().contains(name.toLowerCase())
                        || e.getStudentName().toLowerCase().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Student deleteStudentById(String id) {
        if(checkIsStudentExistedById(id)){
            List<Student> deletedStudent = searchStudentById(id);
            if(!deletedStudent.isEmpty()){
                SoundUtils.alertSound();
                System.out.print("[+] Are sure to delete the student information[Y/n]: ");
                String opt = new Scanner(System.in).nextLine();
                if(opt.equalsIgnoreCase("y")){
                    students.removeAll(deletedStudent);
                    DataAction.addDataToTransaction(students,"transaction-delete.dat");
                    System.out.println("[+] User data has been deleted temporarily successfully.");
                }
                return deletedStudent.getFirst();
            }
        }
        System.out.println(".".repeat(40));
        throw new NoSuchElementException();
    }

    @Override
    public Boolean checkIsStudentExistedById(String id) {
        return students.contains(students.stream().filter(e->e.getId().equals(id)).findAny().get());
    }

    @Override
    public Student updateStudentById(String id, Student student) {
        if(checkIsStudentExistedById(id)){
            students.stream().filter(e->e.getId().equals(id)).forEach(e->{
                e.setStudentName(student.getStudentName());
                e.setStudentDateOfBirth(student.getStudentDateOfBirth());
                e.setStudentClasses(student.getStudentClasses());
                e.setStudentSubjects(student.getStudentSubjects());
                e.setCreatedAt(LocalDate.now().toString());
            });
            DataAction.addDataToTransaction(students, "transaction-update.dat");
            return student;
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void backUpData(String fileName) {
        DataAction.backUp(readObjectFromFile(),fileName);
    }

    @Override
    public Map<Integer, String> restoreData() {
        Path path = Paths.get("backUp");
        File [] files = path.toFile().listFiles();
        Map<Integer, String> fileMap  = new HashMap<>();
        int no = 1;
        assert files != null;
        for(File file: files){
            fileMap.put(no,file.getName());
            no++;
        }
        return fileMap;
    }
}
