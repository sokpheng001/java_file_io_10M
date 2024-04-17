package fileIO.utils;

public class LoadingAnimation {
    public static void loadingAnimation() throws InterruptedException {
        String[] animationFrames = {"|", "/", "-", "\\"};
        int i = 0;
        System.out.print("Loading");
        while (true) {
            System.out.print(".");
            Thread.sleep(250); // Adjust the sleep time to control the speed of the animation
            System.out.print("\b"); // Move the cursor back
            System.out.print(STR."\{animationFrames[i % 4]}\b"); // Print the animation frame
            i++;
        }
    }
}
