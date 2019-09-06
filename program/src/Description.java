import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Description extends JPanel {
    public static void secondaryframe(String dir) {
        JFrame frame = new JFrame("Description of the " + dir + " pack");
        Description jclass = new Description();
        frame.add(FrameUtils.image(Main.currentdir + "\\r\\" + dir + "\\icon.png", 45, 35, 295, 295));
        frame.add(FrameUtils.title(dir, 350, 35, 400, 30));
        frame.add(FrameUtils.description(Main.currentdir + "\\r\\" + dir + "\\info.data", 350, 70, 400, 220));
        JButton enablepack;
        if (dir.equals(Main.currentpack)) {
            enablepack = FrameUtils.buttonsfordays("Enabled", 350, 300, 400, 30);
        } else {
            enablepack = FrameUtils.buttonsfordays("Use this texture pack", 350, 300, 400, 30);
            enablepack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enablepack.setText(InjectMod.change(dir));
                    Main.currentpack = InjectMod.checkpack();
                    Main.curpack.setText("<html><h3>Current pack: " + Main.currentpack + "</h3></html>");
                    Main.defaultmod.setText("Default");
                }
            });
        }
        frame.add(enablepack);
        FrameUtils.frameclose(frame, jclass, 800, 400);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(60, 60, 60));
        g.fillRect(0, 0, 800, 400);

        g.setColor(new Color(40, 40, 40));
        g.fillRoundRect(25, 15, 750, 335, 50, 50);
    }
}
