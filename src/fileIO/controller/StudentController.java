package fileIO.controller;

import fileIO.model.Student;
import fileIO.model.service.StudentService;
import fileIO.model.service.StudentServiceImp;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentController {
    private final StudentService service = new StudentServiceImp();
    public void addNewStudent(Student student){
        service.addNewStudent(student);
    }
    public List<Student> listAllStudents(){
        return service.listAllStudents();
    }
    public void commitData(){
        service.commitDataToFile();
    }
    public void generateObjects(){
        service.generateDesignedObjectForWriting();
    }
    public void destroyData(){
        System.out.print("[!] Are you sure to delete all data in file [Y/N]: ");
        String opt = new Scanner(System.in).nextLine();
        if(opt.equalsIgnoreCase("Y")){
            service.destroyData();
        }else {
            System.out.println("[*] Your deleting data process has been canceled.");
        }
    }
    public Boolean checkDataIsAvailableInTransaction(){
        String [] fileToRead = {"transaction-addNew.dat","transaction-update.dat","transaction-delete.dat"};
        return service.checkIsDataAvailableInTransaction(fileToRead[0])
                || service.checkIsDataAvailableInTransaction(fileToRead[1])
                || service.checkIsDataAvailableInTransaction(fileToRead[2]);
    }
    public void commitDataFromTransaction(){
        service.commitFromTransaction();
    }
    public Student searchStudentById(String id) throws NoSuchElementException {
        return service.searchStudentById(id).getFirst();
    }
    public List<Student> searchStudentByName(String name){
        return service.searchStudentByName(name);
    }
    public Student deleteStudentById(String id){
        return service.deleteStudentById(id);
    }
    public Student updateStudentById(String id, Student student){
        return service.updateStudentById(id, student);
    }
//
    public void backUp(String fileName){
        service.backUpData(fileName);
    }
    public Map<Integer, String> restoreFile(){
        return service.restoreData();
    }
}
