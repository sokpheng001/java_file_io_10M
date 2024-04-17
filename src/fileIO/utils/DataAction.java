package fileIO.utils;

import fileIO.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DataAction {
    private final static String fileName = "transaction/transaction.dat";
    public static Integer numberOfDataHasBeenStoredInTransactionFile;
    public static void addDataToTransaction(List<Student> students){
        try(ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(fileName)
                )
        )
        ){
                    long startedTime = System.currentTimeMillis();
//                    students.addAll(data);
                    objectOutputStream.writeObject(students);
                    long endedTime = System.currentTimeMillis();
//                    if(!Objects.equals(students, new ArrayList<>())){
//                        System.out.println(STR."[+] Spent time for writing data: \{(double)(endedTime - startedTime) / 1000} s".toUpperCase(Locale.ROOT));
//                        System.out.println(STR."[+] Wrote data \{students.size()} record successfully.".toUpperCase(Locale.ROOT));
//                    }
        }catch (IOException problem){
            System.out.println(STR."[!] Problem during writing hero to a file: \{problem.getMessage()}");
//            System.out.println(problem.getMessage());
        }
    }
    public static List<Student> readFromTransaction(){
        try(ObjectInputStream objectInputStream
                    = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(fileName)
                )
        )){
            @SuppressWarnings("unchecked")
            List<Student> newStudents = (List<Student>) objectInputStream.readObject();
            if(Objects.equals(newStudents, new ArrayList<>())){
                return null;
            }else {
//                long endedTime = System.currentTimeMillis();
                numberOfDataHasBeenStoredInTransactionFile = newStudents.size();
                return newStudents;
            }
//            // data table
        }catch (IOException | ClassNotFoundException exception){
            if(exception.getMessage()==null){
                System.out.println("[!] No Data to read from transaction".toUpperCase(Locale.ROOT));
            }else {
                System.out.println(STR."[!] Problem during reading all objects in a transaction file: \{exception.getMessage()}".toUpperCase(Locale.ROOT));
            }
        }
        return null;
    }
}
