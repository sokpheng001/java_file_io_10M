package fileIO.utils;

import fileIO.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DataAction {
    private final static String fileName = "transaction/transaction-addNew.dat";
    public static Integer numberOfDataHasBeenStoredInTransactionFile;
    public static void addDataToTransaction(List<Student> students, String transactionFileName){
        try(ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(STR."transaction/\{transactionFileName}")
                )
        )
        ){
                    objectOutputStream.writeObject(students);
        }catch (IOException problem){
            System.out.println(STR."[!] Problem during writing hero to a file: \{problem.getMessage()}");
        }
    }
    public static List<Student> readFromTransaction(String fileNameToReadData){
        try(ObjectInputStream objectInputStream
                    = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(STR."transaction/\{fileNameToReadData}")
                )
        )){
            @SuppressWarnings("unchecked")
            List<Student> newStudents = (List<Student>) objectInputStream.readObject();
            if(Objects.equals(newStudents, null)){
                return null;
            }else {
//                long endedTime = System.currentTimeMillis();
                numberOfDataHasBeenStoredInTransactionFile = newStudents==null ? 1:newStudents.size();
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
