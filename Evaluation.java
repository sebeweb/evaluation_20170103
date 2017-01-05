
import java.io.*;

import java.util.*;

public class Evaluation {

    public static ArrayList<String> read() {
        ArrayList<String> readfile = new ArrayList<String>();
        String line = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("questions.qs"));
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
    public static void reponses(ArrayList<String> questions) {
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
            } else if (questions.size() == i) {
                System.out.println("qsdfghj");
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
        System.out.println(questions);
    }
    /*public static void replace(ArrayList<String> tab){
		for (int i = 0; i < readfile.size(); i++ )
			{
				if(readfile.get(i).charAt(0) == '#' && readfile.get(i).charAt(1) == '#')
				{
					String vide = readfile.get(i).replace("#","");
					readfile.set(i,vide);
				}
				else if(readfile.get(i).charAt(0) == '#' && readfile.get(i).charAt(1) != '#')
				{	
					String vide = readfile.get(i).replace("#","");
					readfile.set(i, vide);
				}
				else if(readfile.get(i).charAt(0) == '-')
				{
					String vide = readfile.get(i).replace("-","");
					readfile.set(i, vide);
				}
				//System.out.println(readfile.get(i));
			}					
		}*/
    public static void main(String[] args) {
        //read();
        ArrayList<String> questions = read();
        reponses(questions);
        //System.out.println(questions);
    }

}
