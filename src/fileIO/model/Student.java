package fileIO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 8683452581122892180L;
    private String id;
    private String studentName;
    private String studentDateOfBirth;
    private String[] studentClasses;
    private String[] studentSubjects;
    private String createdAt;
}
