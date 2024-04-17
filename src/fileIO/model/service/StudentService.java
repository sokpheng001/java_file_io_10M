package fileIO.model.service;

import fileIO.model.Student;

import java.util.List;

public interface StudentService {
    void generateDesignedObjectForWriting();
    int addNewStudent(Student student);
    List<Student> listAllStudents();
    void commitDataToFile();
    void commitFromTransaction();
    void destroyData();
    Boolean checkIsDataAvailableInTransaction();
    Student searchStudentById(String id);
    List<Student> deleteStudentById(String id);
    Student updateStudentById(String id);
}
