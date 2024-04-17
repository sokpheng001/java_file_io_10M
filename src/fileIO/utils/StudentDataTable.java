package fileIO.utils;

import fileIO.model.Student;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StudentDataTable {
    public static void dataTable(List<Student> anyObjectList, String...message){
        System.out.println(".".repeat(40));
        if(anyObjectList.equals(new ArrayList<Student>())){
            if(message.length==0){
                System.out.println("[!] \t\t\t\t\t\t\t\t\t\t> No Data <".toUpperCase(Locale.ROOT));
            }else {
                System.out.println(STR."[!]\{message[0]}".toUpperCase(Locale.CANADA));
            }
        }else {
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell("ID",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("STUDENT'S NAME",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("STUDENT'S DATE OF BIRTH",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("STUDENT CLASS",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("STUDENTS' SUBJECT",new CellStyle(CellStyle.HorizontalAlign.CENTER));
//
            for(int i=0;i<5;i++){
                table.setColumnWidth(i,30,50);
            }
//        data
            int i=0;
            for(Student hero: anyObjectList){
                table.addCell(hero.getId().toString(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(hero.getStudentName(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(hero.getStudentDateOfBirth(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(hero.getStudentClasses()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(hero.getStudentSubjects()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                if(anyObjectList.size()>5){
                    i++;
                    if(i==5){
                        break;
                    }
                }
            }
            System.out.println(table.render());
        }
    }
}
