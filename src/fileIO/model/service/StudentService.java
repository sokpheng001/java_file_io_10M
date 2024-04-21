package fileIO.model.service;

import fileIO.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void generateDesignedObjectForWriting();
    int addNewStudent(Student student);
    List<Student> listAllStudents();
    void commitDataToFile();
    void commitFromTransaction();
    void destroyData();
    Boolean checkIsDataAvailableInTransaction(String fileToCheck);
    List<Student> searchStudentById(String id);
    Student deleteStudentById(String id);
    Boolean checkIsStudentExistedById(String id);
    Student updateStudentById(String id, Student student);
//
    void backUpData(String fileName);
    Map<Integer, String> restoreData();
}
