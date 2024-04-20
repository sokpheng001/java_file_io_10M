package fileIO.utils;

import fileIO.model.Student;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class StudentDataTable {
    public static void studentDataTable(List<Student> anyObjectList, Integer actualRecordNumberInOnce ,String...message){
        if(actualRecordNumberInOnce==null || actualRecordNumberInOnce==0|| actualRecordNumberInOnce<0 || actualRecordNumberInOnce>anyObjectList.size() ){
            actualRecordNumberInOnce = 3;
        }
//        System.out.println(".".repeat(40));
        if(anyObjectList.equals(new ArrayList<Student>())){
            if(message.length==0){
                System.out.println("[!] \t\t\t\t\t\t\t\t\t\t> No Data <".toUpperCase(Locale.ROOT));
                SoundUtils.windowsRingSound();
            }else {
                System.out.println(STR."[!]\{message[0]}".toUpperCase(Locale.CANADA));
            }
        }else {
            System.out.println("[*] Students' Data".toUpperCase(Locale.ROOT));
//
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
                table.addCell(hero.getId(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(hero.getStudentName(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(hero.getStudentDateOfBirth(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(hero.getStudentClasses()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(hero.getStudentSubjects()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                if(anyObjectList.size()>actualRecordNumberInOnce){
                    i++;
                    if(i==actualRecordNumberInOnce){
                        break;
                    }
                }
            }
            System.out.println(table.render());
//            pagination
            int page = anyObjectList.size()/actualRecordNumberInOnce;
            System.out.println("-".repeat(146));
            System.out.print(STR."+ Number of page: \{(page)==0? 1:page } \t\t+ Actual record: \{actualRecordNumberInOnce}\t\t+ All Record: \{anyObjectList.size()}\t\t\t\t\t\t-> Previous (P/p) \t\t Next (n/N) <-\n");
            System.out.println("-".repeat(80));
        }
    }
    public static void tableFromSearchedResult(List<Student> anyObjectList, String...message){
        System.out.println(".".repeat(40));
        if(anyObjectList.equals(new ArrayList<Student>())){
            if(message.length==0){
                System.out.println("[!] \t\t\t\t\t\t\t\t\t\t> No Data <".toUpperCase(Locale.ROOT));
                SoundUtils.windowsRingSound();
            }else {
                System.out.println(STR."[!]\{message[0]}".toUpperCase(Locale.CANADA));
            }
        }else {
            System.out.println("[*] Student's Info.".toUpperCase(Locale.ROOT));
//
            Table table = new Table(2, BorderStyle.CLASSIC_COMPATIBLE, ShownBorders.ALL);
            table.addCell("STUDENT'S INFORMATION", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("DATA", new CellStyle(CellStyle.HorizontalAlign.CENTER));
//
            for (int i = 0; i < 2; i++) {
                table.setColumnWidth(i, 30, 50);
            }
//        data
            for (Student hero : anyObjectList) {
                table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(hero.getId(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("NAME", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(hero.getStudentName(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
//
                table.addCell("BIRTH", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(hero.getStudentDateOfBirth(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("CLASS", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(Arrays.toString(hero.getStudentClasses()), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("SUBJECT", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(Arrays.toString(hero.getStudentSubjects()), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
            }
            System.out.println(table.render());
        }
    }
}
