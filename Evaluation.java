import java.io.*;
import java.util.*;

public class Evaluation {
    public static void main(String[] args) {
        ArrayList<String> readf = new ArrayList<String>();
        String line = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("questions.qs"));
            while ((line = br.readLine()) != null) {
                readf.add(line);
            }
            for (int i = 0; i < readf.size(); i++) {
                if (readf.get(i).charAt(0) == '#' && readf.get(i).charAt(1) == '#') {
                    String vide = readf.get(i).replace("#", "");
                    readf.set(i, vide);

                } else if (readf.get(i).charAt(0) == '#' && readf.get(i).charAt(1) != '#') {
                    String vide = readf.get(i).replace("#", "");
                    readf.set(i, vide);
                } else if (readf.get(i).charAt(0) == '-') {
                    String vide = readf.get(i).replace("-", "");
                    readf.set(i, vide);
                }
                System.out.println(readf.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
