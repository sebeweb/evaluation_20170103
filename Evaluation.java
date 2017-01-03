import java.io.*;
import java.util.*;
import java.lang.*;

public class Evaluation
{
	public static void main(String[] args)
	{
		BufferedReader br = null;
		FileReader fr = null;
		String line;
		String theme = "";
		ArrayList<String> qcm = new ArrayList<String>(); 
		Scanner sc = new Scanner(System.in);
		HashMap <String, ArrayList<String>> questions = new HashMap<>();
		try {

			fr = new FileReader("questions.qs");
			br = new BufferedReader(fr);
			br = new BufferedReader(new FileReader("questions.qs"));
			while ((line = br.readLine()) != null) {
				if(line.charAt(0) == '#' && line.charAt(1) == '#' ) {
					theme = line.replace("#", "");
					System.out.println(theme);
				}
				else if(line.charAt(0) == '#' && line.charAt(1) != '#' ) {
					theme = line.replace("#", "");
					System.out.println(theme);
					if(line.charAt(line.length()-1) == '?') {
						String reponse = sc.nextLine();
					}
				}
				else if(line.charAt(0) == '-') {
					theme = line.replace("-", "");
					System.out.println(theme);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
	}
}
