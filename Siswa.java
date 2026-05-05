import java.io.*;
public class Siswa {
    private String nis, nama, alamat;

    public Siswa(String nis, String nama, String alamat) {
        this.nis = nis;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String toFile() {
        return nis + "|" + nama + "|" + alamat;
    }

    public static void tambah(String nis, String nama, String alamat) {
        Siswa s = new Siswa(nis, nama, alamat);
        FileHelper.write("siswa.txt", s.toFile());
    }

    public static java.util.List<String[]> getAll() {
        java.util.List<String[]> list = new java.util.ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("siswa.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split("\\|"));
            }
            br.close();
        } catch (Exception e) {}
        return list;
    }
}