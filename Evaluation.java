import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

/**
 * @author Sébastien JAVARY
 */

public class Evaluation {
    // the read function is used to read the file and add it in a arraylist
    public static ArrayList<String> read() {
        ArrayList<String> readfile = new ArrayList<>();
        String line;
        BufferedReader br = null;

        try {
            //opening of the file
            br = new BufferedReader(new FileReader("./questions.qs"));
            //loop in the file while the end of file is not reached
            while ((line = br.readLine()) != null)
                //add the line in the arraylist readfile
                readfile.add(line);
        //manage errors from the reading of the file
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    //closing of the file
                    br.close();
            //manage errors from the closing of the file
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return readfile;
    }
    //scan answers and save them in the arraylist questions
    public static ArrayList<String> reponses(ArrayList<String> questions) {
        //scan input
        Scanner sc = new Scanner(System.in);
        int i = 0; 

        //loop while the index(i) is inferior to the size of the arraylist questions
        while (i < questions.size() - 1) {
            // create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // get a java.util.Date from the calendar instance.
        // this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

            //java current time (now) instance
        java.sql.Timestamp ts = new java.sql.Timestamp(now.getTime());
            // if the first char of the line at the index(i) is "-"
            if (questions.get(i).charAt(0) == '-') {
                //if the first char of the next line is "-"
                if (questions.get(i + 1).charAt(0) == '-')
                    i++;
                else {
                    //scan answer 
                    String answer = sc.nextLine();
                    //add it to the arraylist with the timestamp
                    questions.add(i + 1,ts+" "+answer);
                    i += 2;
                }
            } else {
                // if the first char and the second char of the line at the index(i) are "#"
                if (questions.get(i).charAt(0) == '#' && questions.get(i).charAt(1) != '#') {
                     // if the first char of the line at the index(i) is "#" and the first char of the next line is "-" 
                    if (questions.get(i).charAt(0) == '#' && questions.get(i + 1).charAt(0) == '-')
                        i++;
                    else {
                        //scan answer 
                        String answer = sc.nextLine();
                        //add it to the arraylist with the timestamp
                        questions.add(i + 1,ts+" "+answer);
                        i += 2;
                    }
                } else
                i++;
            }
            System.out.println(questions.get(i));
        }
        // create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // get a java.util.Date from the calendar instance.
        // this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

        java.sql.Timestamp ts = new java.sql.Timestamp(now.getTime());
        //scan answer from the last question of the file
        String answer = sc.nextLine();
        //add it to the arraylist with the timestamp
        questions.add(ts+" "+answer);
        return questions;
    }
    // replace useless special char by html tags
    public static ArrayList<String> rewrite(ArrayList<String> tab) {
        //while the index(i) is inferior to the size of the arraylist, the index(i) is incremented
        for (int i = 0; i < tab.size(); i++) {
            // if the first char and the second char of the line at the index(i) are "#"
            if (tab.get(i).charAt(0) == '#' && tab.get(i).charAt(1) == '#') {
                // if the index(i) is different from 0 and the previous line contains "/setion"
                if (i != 0 && tab.get(i-1).contains("/section")) {
                    //then we add the html tag at the end of the line
                    String vide = tab.get(i-1).concat("</article>");
                    // and we "put" it in the arraylist tab 
                    tab.set(i-1, vide);
                }
                //replace the # by the html tags and add it at the end of the line 
                String vide = tab.get(i).replace("##", "<article><h3>").concat("</h3>");
                // and we "put" it in the arraylist tab 
                tab.set(i, vide);
            // if the first char of the line at the index(i) is "#" and the second char is different from "#"
            } else if (tab.get(i).charAt(0) == '#' && tab.get(i).charAt(1) != '#') {
                //replace the # by the html tags and add it at the end of the line 
                String vide = tab.get(i).replace("#", "<section><h4>").concat("</h4>");
                //if the first char of the next line is "-"
                if (tab.get(i + 1).charAt(0) == '-')
                    //then add the html tag 
                    vide = vide.concat("<ol>");
                //and we "put" it in the arraylist tab 
                tab.set(i, vide);
            //if the first char of the line is "-"
            } else if (tab.get(i).charAt(0) == '-') {
                 //replace the "-" by the html tags and add it at the end of the line 
                String vide = tab.get(i).replace("-", "<li>").concat("</li>");
                //and we "put" it in the arraylist tab 
                tab.set(i, vide);
            } else {
                // if the previous line contains the html closing tag list item
                if (tab.get(i-1).contains("</li>")) {
                    //then we add the html closing tag of the ordonned list 
                    String vide = tab.get(i-1).concat("</ol>");
                    //and we "put" it in the arraylist tab 
                    tab.set(i-1, vide);
                }
                //add the html tag
                String vide = "<p>";
                // and add the html closing tags at the end of the line
                vide += tab.get(i).concat("</p></section>");
                //and we "put" it in the arraylist tab 
                tab.set(i, vide);   
            }
        }
        return tab;
    }
    //creation of the html file and write in it
    public static void write(ArrayList<String> writef) {

        BufferedWriter bw = null;
        //creation of the header  
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
        // closing of html tags
        String close = "</article></body></html>";
        ArrayList<String> rewrite = rewrite(writef);
        try {
            // creation of the html file
            bw = new BufferedWriter(new FileWriter("./resultat/index.html"));
            //write in the html file
            bw.write(header);
            ////while the index(i) is inferior to the size of the arraylist, the index(i) is incremented
            for (int i = 0; i < rewrite.size(); i++)
                //writing of the line
                bw.write(rewrite.get(i));
            //writing the closing html tags of the file 
            bw.write(close);
        //manage errors from the writing of the file
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    //closing of the file
                    bw.close();
            //manage errors from the closing of the file
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // return the arraylist from read function and save it in the questions arraylist
        ArrayList<String> questions = read();
        // return the array question from the reponses function and save it in the reponses arraylist
        ArrayList<String> reponses = reponses(questions);
        //call the write function with reponses in parameters
        write(reponses);
    }
}

