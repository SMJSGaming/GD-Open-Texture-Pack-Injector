import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

public class Updater extends JPanel {
    public static void checkupdate() {
        String filename = new java.io.File(Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
        String [] versionbad = filename.split("-V");
        if (versionbad.length >= 2) {
            String [] version = versionbad[1].split(".jar");
            
        }
    }
    private static void Updater(String version, String oldversion ,String urlstring) {
        JFrame frame = new JFrame("Auto TP Injector Updater");
        JPanel panel = panel();
        JProgressBar progress = progress();

        JLabel text1 = text1("Currently updating the TP injector.");
        JLabel text2 = text2();

        frameclose(frame, text1, text2, progress, panel);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                text2.setText("<html><b>downloading file...</b></html>");
                tempdownload(urlstring, "TP-Injector-V" + version + ".jar");
                progress.setValue(80);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            text2.setText("<html><b>deleting old file...</b></html>");
                            progress.setValue(100);
                            Runtime.getRuntime().exec("cmd /c ping 127.0.0.1 -n 3 > nul && del TP-Injector-V" + oldversion + ".jar");
                            System.exit(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    private static void frameclose(JFrame frame, JLabel text1, JLabel text2, JProgressBar progress, JPanel panel) {
        frame.add(text1);
        frame.add(text2);
        frame.add(progress);
        frame.add(panel);
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private static JPanel panel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 60, 60));
        return panel;
    }
    private static JProgressBar progress() {
        JProgressBar progress = new JProgressBar();
        progress.setBounds(50,120,500,20);
        progress.setMaximum(100);
        progress.setValue(0);
        return progress;
    }
    private static void tempdownload(String url, String filename) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFile(String filename, String text) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            fos.write(text.getBytes("UTF-8"));
        } catch (IOException e) {
            fos.close();
            e.printStackTrace();
        }
    }
    private static JLabel text1(String holdtext) {
        JLabel text = new JLabel();
        text.setBounds(20,0,200,80);
        text.setText("<html><body style='color: white;'><h2>Updating</h2><p>" + holdtext + "</p><p>Please do not close the program.</p></body></html>");
        return text;
    }
    private static JLabel text2() {
        JLabel text = new JLabel();
        text.setBounds(50,55,200,100);
        text.setForeground(Color.white);
        text.setText("<html><b>getting packages...</b></html>");
        return text;
    }
}
