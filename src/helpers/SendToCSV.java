package helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SendToCSV {
    public static File writeToCsv(HashMap<String,Integer> courses) throws IOException {
        File file = null;
        FileWriter csvWriter = null;
        try {
            String localDir = System.getProperty("user.dir");
            file = new File(localDir + "\\out.csv");

            csvWriter = new FileWriter(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //builds .csv file from hashmap of courses
        csvWriter.write("COURSE NAME & NUMBER,ESTIMATED LOAD\n");
        for (String s : courses.keySet()) {
            StringBuilder line = new StringBuilder();
            line.append(s);
            line.append(",");
            line.append(courses.get(s));
            line.append("\n");
            csvWriter.write(line.toString());
        }
        csvWriter.close();
        return file;
    }
}
