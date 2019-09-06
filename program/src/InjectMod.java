import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class InjectMod {
    public static void moddefault(boolean addresource) {
        try {
            File path = new File(Main.currentdir + "\\GeometryDash.exe");
            byte[] content = Files.readAllBytes(path.toPath());
            //push  offset aHttpWwwBoomlin_1 (becomes Resources)
            content[245182] = -92;
            content[245183] = -77;
            content[245184] = 105;

            content[843363] = -92;
            content[843364] = -77;
            content[843365] = 105;

            //push  offset aHttpWwwRobtopg_3
            content[650599] = -88;
            content[650600] = -41;
            content[650601] = 108;

            //Resources......... etc
            if (addresource) {
                content[2729380] = 82;
                content[2729381] = 101;
                content[2729382] = 115;
                content[2729383] = 111;
                content[2729384] = 117;
                content[2729385] = 114;
                content[2729386] = 99;
                content[2729387] = 101;
                content[2729388] = 115;
                for (int i = 2729389; i <= 2729435; i++) {
                    content[i] = 0;
                }
            } else {
                for (int i = 2729380; i <= 2729435; i++) {
                    content[i] = 0;
                }
            }
            patch(content, Main.currentdir + "\\GeometryDash.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String change(String newdir) {
        boolean error = false;
        try {
            moddefault(false);
            File path = new File(Main.currentdir + "\\GeometryDash.exe");
            byte[] content = Files.readAllBytes(path.toPath());
            String dir = "r\\" + newdir + "\\";
            byte[] target = dir.getBytes();
            for (int i = 0; i < target.length; i++) {
                if (i <= 40) {
                    content[2729380 + i] = target[i];
                } else {
                    error = true;
                }
            }
            if (!error) {
                patch(content, Main.currentdir + "\\GeometryDash.exe");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!error) {
            return "Enabled";
        } else {
            return "Too long name";
        }
    }
    public static String checkpack() {
        String check = "error";
        try {
            File path = new File(Main.currentdir + "\\GeometryDash.exe");
            byte[] content = Files.readAllBytes(path.toPath());
            char chchar = (char)content[2729380];
            if (chchar != 'h') {
                if (chchar != 'R') {
                    String output = "";
                    for (int i = 2729382; content[i] != 92; i++) {
                        output += String.valueOf((char)content[i]);
                    }
                    check = output;
                } else {
                    check = "Resources";
                }
            } else {
                check = "Mod is not enabled yet";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }
    private static void patch(byte[] bytes, String path) {
        try {
            File someFile = new File(path);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String[] getsubdirs(String target) {
        File folder = new File(target);
        ArrayList<String> packs = new ArrayList<String>();
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                String path = listOfFiles[i].getAbsolutePath();
                File checkicon = new File(path + "\\icon.png");
                File checkinfo = new File(path + "\\info.data");
                if (checkicon.exists() && checkinfo.exists() && listOfFiles[i].getName().length() < 35) {
                    packs.add(path);
                }
            }
        }
        return packs.toArray(new String[packs.size()]);
    }
}
