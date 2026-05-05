import java.io.*;
public class Admin {

    public static boolean login(String nip, String nama) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("pegawai.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d[0].equals(nip) && d[1].equals(nama)) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error login");
        }
        return false;
    }
}