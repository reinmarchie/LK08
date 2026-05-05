import java.io.*;
public class FileHelper {

    public static void write(String file, String data) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error tulis file");
        }
    }

    public static void read(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error baca file");
        }
    }
}