import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sébastien JAVARY
 */
public class Evaluation {

    public static ArrayList<String> read() {
        ArrayList<String> readfile = new ArrayList<>();
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("./questions.qs"));

            while ((line = br.readLine()) != null) {
                readfile.add(line);
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
        return readfile;
    }

    public static ArrayList<String> reponses(ArrayList<String> questions) {

        Scanner sc = new Scanner(System.in);
        int i = 0;

        while (i < questions.size() - 1) {
            if (questions.get(i).charAt(0) == '-') {
                if (questions.get(i + 1).charAt(0) == '-') {
                    i++;
                } else {
                    String answer = sc.nextLine();
                    questions.add(i + 1, answer);
                    i += 2;

                }
            } else {
                if (questions.get(i).charAt(0) == '#' && questions.get(i).charAt(1) != '#') {
                    if (questions.get(i).charAt(0) == '#' && questions.get(i + 1).charAt(0) == '-') {
                        i++;
                    } else {
                        String answer = sc.nextLine();
                        questions.add(i + 1, answer);
                        i += 2;
                    }
                } else {
                    i++;
                }
            }
            System.out.println(questions.get(i));
        }
        String answer = sc.nextLine();
        questions.add(answer);
        return questions;

    }

    public static ArrayList<String> rewrite(ArrayList<String> tab) {
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).charAt(0) == '#' && tab.get(i).charAt(1) == '#') {
                if (i != 0 && tab.get(i - 1).contains("/section")) {
                    String vide = tab.get(i - 1).concat("</article>");
                    tab.set(i - 1, vide);
                    //System.out.println(tab.get(i-1));
                }
                String vide = tab.get(i).replace("##", "<article><h3>").concat("</h3>");
                tab.set(i, vide);
            } else if (tab.get(i).charAt(0) == '#' && tab.get(i).charAt(1) != '#') {
                String vide = tab.get(i).replace("#", "<section><h4>").concat("</h4>");
                if (tab.get(i + 1).charAt(0) == '-') {
                    vide = vide.concat("<ol>");
                }
                tab.set(i, vide);

            } else if (tab.get(i).charAt(0) == '-') {
                String vide = tab.get(i).replace("-", "<li>").concat("</li>");
                tab.set(i, vide);
            } else {
                if (tab.get(i - 1).contains("</li>")) {
                    String vide = tab.get(i - 1).concat("</ol>");
                    tab.set(i - 1, vide);
                    //System.out.println(tab.get(i-1));
                }
                String vide = "<p>";
                vide += tab.get(i).concat("</p></section>");
                tab.set(i, vide);

            }
            // System.out.println(tab.get(i));
        }
        return tab;
    }

    public static void write(ArrayList<String> writef) {
        BufferedWriter bw = null;

        String header = "<!DOCTYPE html>";
        header += "<!--index.html Fichier crée par \"Sébastien Javary\" \"05/01/2017\"-->";
        header += "<html>";
        header += "<head>";
        header += "<meta http-equiv=\"content-type\" content=\"text/html\" charset=\"utf-8\" />";
        header += "<title>evaluations du 03 janvier 2017 – Lunel</title>";
        header += "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">";
        header += "</head>";
        header += "<body>";
        header += "<h1>evaluation du 03 janvier 2017 - Lunel</h1>";
        header += "<h2>Sébastien Javary</h2>";
        String close = "</article></body></html>";
        ArrayList<String> rewrite = rewrite(writef);
        //String line;
        try {
            bw = new BufferedWriter(new FileWriter("./resultat/index.html"));
            bw.write(header);
            for (int i = 0; i < rewrite.size(); i++) {
                bw.write(rewrite.get(i));
            }
            bw.write(close);
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //read();
        ArrayList<String> questions = read();
        ArrayList<String> reponses = reponses(questions);
        write(reponses);
        //ArrayList<String> finale = rewrite(reponses);
        //System.out.println(reponses);

    }
}
