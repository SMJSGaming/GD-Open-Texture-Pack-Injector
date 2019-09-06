import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FrameUtils {
    public static void frameclose(JFrame frame, JPanel jclass, int width, int height) {
        frame.add(jclass);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static JLabel image(String path, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image scaleimg;
        if (width == 0) {
            scaleimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        } else {
            scaleimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        ImageIcon result = new ImageIcon(scaleimg);
        JLabel label = new JLabel(result);
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JLabel name(String name, int x, int y, int width, int height) {
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JLabel title(String dir, int x, int y, int width, int height) {
        JLabel title = new JLabel("<html><h1>" + dir + "</h1></html>");
        title.setBounds(x, y, width, height);
        title.setForeground(Color.white);
        return title;
    }
    public static JLabel description(String root, int x, int y, int width, int height) {
        JLabel desc = new JLabel("");
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(root));
            String result = new String(bytes);
            desc.setText("<html>" + result + "</html>");
            desc.setForeground(Color.white);
            desc.setBounds(x, y, width, height);
            desc.setVerticalAlignment(JLabel.TOP);
            desc.setHorizontalAlignment(JLabel.LEFT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desc;
    }
    public static JLabel packname(int x, int y, int width, int height, String[] containers, int i) {
        File dir = new File(containers[i]);
        JLabel name = name(dir.getName(), x, y, width, height + 145);
        //yes I know that this is a reaaaally bad way of making a hitbox for the click but shut up because I tried almost every possible way to make the image use the right directory without error after the load
        name.setVerticalAlignment(SwingConstants.BOTTOM);
        name.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String dir = name.getText();
                Description.secondaryframe(dir);
            }
        });
        return name;
    }
    public static JLabel packimg(int x, int y, int width, int height, String[] containers, int i) {
        JLabel img = image(containers[i] + "\\icon.png", x, y, width, height);
        return img;
    }
    public static JButton buttonsfordays(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setForeground(Color.white);
        button.setBackground(new Color(80, 80, 80));
        button.setBounds(x, y,width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
    public static void pageswitch(ArrayList<JLabel> packs, int curoffset) {
        int y = 60;
        int x = 90;
        for (int i = 0; i < packs.size() - 1; i++) {
            packs.get(i).setBounds(0, 0, 0, 0);
        }
        for (int i = 0; i <= 15 && i + curoffset < packs.size(); i++) {
            if (i % 4 == 0 && i != 0) {
                y = 60;
                x += 220;
            }
            if (i % 2 == 0) {
                packs.get(curoffset + i).setBounds(x, y, 150, 150);
            } else {
                packs.get(curoffset + i).setBounds(x, y, 150, 175);
                y += 220;
            }
        }
    }
}
