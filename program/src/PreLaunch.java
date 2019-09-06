import java.io.*;

public class PreLaunch {
    public static void todo() {
        dirs();
        errordetour();
    }
    private static void dirs() {
        File directory = new File(System.getenv("LOCALAPPDATA") + "\\smjsproducts");
        if (!directory.exists()) {
            directory.mkdir();
        }
        directory = new File(Main.currentdir + "\\r");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    private static void errordetour() {
        try {
            File file = new File(System.getenv("LOCALAPPDATA") + "\\smjsproducts\\logs.txt");
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setErr(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}