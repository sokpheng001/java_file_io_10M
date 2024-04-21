package fileIO.utils;

import fileIO.model.Student;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class StudentDataTable {
    public static void studentDataTable(List<Student> studentList
, Integer actualRecordNumberInOnce, int navigateStart ,int pageNumber,String...message){
        if(actualRecordNumberInOnce==null || actualRecordNumberInOnce==0|| actualRecordNumberInOnce<0 || actualRecordNumberInOnce>studentList.size() ){
            actualRecordNumberInOnce = Math.min(studentList.size(), 3);
        }
//        System.out.println(".".repeat(40));
        if(studentList.equals(new ArrayList<Student>())){
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
            if(navigateStart>=studentList.size()){
                navigateStart = studentList.size()-4;
            }
//            if(navigateStart+actualRecordNumberInOnce>studentList.size()){
//                navigateStart = actualRecordNumberInOnce;
//            }
            for(int n=navigateStart;n<studentList.size();n++){
                table.addCell(studentList.get(n).getId(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(studentList.get(n).getStudentName(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(studentList.get(n).getStudentDateOfBirth(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(studentList.get(n).getStudentClasses()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                table.addCell(Arrays.toString(studentList.get(n).getStudentSubjects()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
                if(studentList.size()>actualRecordNumberInOnce){
                    i++;
                    if(i==actualRecordNumberInOnce){
                        break;
                    }
                }
            }
//            for(Student hero: studentList){
//                table.addCell(hero.getId(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
//                table.addCell(hero.getStudentName(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
//                table.addCell(hero.getStudentDateOfBirth(),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
//                table.addCell(Arrays.toString(hero.getStudentClasses()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
//                table.addCell(Arrays.toString(hero.getStudentSubjects()),new CellStyle(CellStyle.HorizontalAlign.CENTER),1);
//                if(studentList.size()>actualRecordNumberInOnce){
//                    i++;
//                    if(i==actualRecordNumberInOnce){
//                        break;
//                    }
//                }
//            }
            System.out.println(table.render());
//            pagination
            System.out.println("-".repeat(146));
            System.out.print(STR."[*] Page Number: \{pageNumber}\t [*] Actual record: \{actualRecordNumberInOnce}\t[*] All Record: \{studentList.size()}\t\t\t\t\t\t\t\t\t\t [+] Previous (P/p) - [+] Next (n/N) - [+] Back (B/b)\n");
            System.out.println("-".repeat(80));
        }
    }
    public static void tableFromSearchedResult(List<Student> studentList, String...message){
        System.out.println(".".repeat(40));
        if(studentList.equals(new ArrayList<Student>())){
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
            for (Student student : studentList) {
                table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(student.getId(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("NAME", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(student.getStudentName(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
//
                table.addCell("BIRTH", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(student.getStudentDateOfBirth(), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("CLASS", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(Arrays.toString(student.getStudentClasses()), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell("SUBJECT", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
                table.addCell(Arrays.toString(student.getStudentSubjects()), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
            }
            System.out.println(table.render());
        }
    }
}
