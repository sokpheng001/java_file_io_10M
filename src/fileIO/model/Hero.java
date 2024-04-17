package fileIO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Hero implements Serializable {
    private Integer id;
    private String name;
    private String[] heroTypes;
    private Integer maxHealth;
}
