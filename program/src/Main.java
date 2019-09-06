import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
TODO
-Make the auto update
-Auto folder generator
-error stack detouring
 */

public class Main extends JPanel {
    public static String currentdir = System.getProperty("user.dir");
    private static int curoffset = 0;
    public static String currentpack = InjectMod.checkpack();
    public static JLabel curpack = new JLabel("<html><h3>Current pack: " + currentpack + "</h3></html>", SwingConstants.RIGHT);
    public static JButton defaultmod = FrameUtils.buttonsfordays("Default", 420, 490, 160, 30);
    private static ArrayList<JLabel> packs = new ArrayList<>();

    public static void main(String[] args) {
        Updater.checkupdate();
        if (currentpack.equals("Resources")) {
            defaultmod.setText("Enabled");
        }
        Main jclass = new Main();
        JFrame frame = new JFrame("Texture Pack Injector By SMJS");
        String[] containers = InjectMod.getsubdirs(currentdir + "\\r\\");
        if (containers.length != 0) {
            int y = 60;
            int x = 90;
            for (int i = 0; i < containers.length; i++) {
                if (i % 2 == 0 && i != 0) {
                    y = 60;
                    x += 220;
                }
                if (x <= 750) {
                    packs.add(FrameUtils.packimg(x, y, 150, 150, containers, i));
                    frame.add(packs.get(packs.size() - 1));
                    packs.add(FrameUtils.packname(x, y, 150, 30, containers, i));
                    frame.add(packs.get(packs.size() - 1));
                    y += 220;
                } else {
                    packs.add(FrameUtils.packimg(0, 0, 0, 0, containers, i));
                    frame.add(packs.get(packs.size() - 1));
                    packs.add(FrameUtils.packname(0, 0, 0, 0, containers, i));
                    frame.add(packs.get(packs.size() - 1));
                }
            }
        }
        curpack.setForeground(Color.WHITE);
        curpack.setBounds(500, 5, 480, 25);
        frame.add(curpack);

        JButton previous = FrameUtils.buttonsfordays("Previous", 300, 490, 100, 30);
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (curoffset > 0) {
                    curoffset -= 16;
                    FrameUtils.pageswitch(packs, curoffset);
                }
            }
        });

        defaultmod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InjectMod.moddefault(true);
                defaultmod.setText("Enabled");
                currentpack = InjectMod.checkpack();
                curpack.setText("<html><h3>Current pack: " + currentpack + "</h3></html>");
            }
        });

        JButton next = FrameUtils.buttonsfordays("Next", 600, 490, 100, 30);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (curoffset + 18 <= packs.size()) {
                    curoffset += 16;
                    FrameUtils.pageswitch(packs, curoffset);
                }
            }
        });


        frame.add(previous);
        frame.add(defaultmod);
        frame.add(next);

        FrameUtils.frameclose(frame, jclass, 1000, 600);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(60, 60, 60));
        g.fillRect(0, 0, 1000, 600);

        g.setColor(new Color(40, 40, 40));
        g.fillRoundRect(50, 35, 900, 500, 50, 50);
    }
}