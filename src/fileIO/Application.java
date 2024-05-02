package fileIO;

import fileIO.view.StudentDashboard;


public class Application {
//
    public static void main(String[] args) {
        System.out.println("""
                  \s
                   ██████╗███████╗████████╗ █████╗ ██████╗    \s
                  ██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗   \s
                  ██║     ███████╗   ██║   ███████║██║  ██║   \s
                  ██║     ╚════██║   ██║   ██╔══██║██║  ██║   \s
                  ╚██████╗███████║   ██║   ██║  ██║██████╔╝   \s
                   ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝    \s
               \s""");
        StudentDashboard.view();
    }
}