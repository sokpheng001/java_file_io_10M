package fileIO.controller;

import fileIO.model.Student;
import fileIO.model.service.StudentService;
import fileIO.model.service.StudentServiceImp;

import java.util.List;
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
        return service.checkIsDataAvailableInTransaction();
    }
    public void commitDataFromTransaction(){
        service.commitFromTransaction();
    }
    public Student searchStudentById(String id) throws NoSuchElementException {
        return service.searchStudentById(id);
    }
    public List<Student> deleteStudentById(String id){
        return service.deleteStudentById(id);
    }
    public Student updateStudentById(String id){
        return service.updateStudentById(id);
    }
}
