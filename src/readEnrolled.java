import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class readEnrolled {
    public static HashMap<String, Major> readCsv() throws FileNotFoundException {
        // Initialize and add Filepath. TODO: MAKE FILEPATH DYNAMIC
        HashMap<String, Major> majors = new HashMap<>();
        File file = new File("C:\\Users\\brady\\IdeaProjects\\Provost Office\\src\\Fall 2022 New Student Enrollment by Major.csv");
        Scanner read = new Scanner(file);
        read.nextLine();

        // Reads in all lines and adds majors.
        while(read.hasNextLine()) {
            String[] temp = read.nextLine().split(",");
            if('"' == (temp[0].charAt(0))) {
                String line = "";
                String num = temp[temp.length - 1];
                for(int x = 0; x < temp.length - 1; x++) {
                    line = line + temp[x];
                }
                temp = new String[2];
                temp[0] = line;
                temp[1] = num;
            }

            // Throw out unnecessary lines. TODO: Make more efficient
            if("(blank)".equals(temp[0]) || "Grand Total".equals(temp[0]) || temp.length < 2) {
                continue;
            }
            else {
                Major major = new Major(temp[0], Integer.parseInt(temp[1]));

                // If the major name is the same, aggregate enrolled size
                if(majors.containsKey(temp[0])) {
                    Major test = majors.get(temp[0]);
                    major.setEnrolled(major.getEnrolled() + test.getEnrolled());
                }
                majors.put(temp[0], major);
            }
        }
        read.close();
        return majors;
    }
}
