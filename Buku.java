import java.io.*;
public class Buku {
    private String kode, judul, jenis;

    public Buku(String kode, String judul, String jenis) {
        this.kode = kode;
        this.judul = judul;
        this.jenis = jenis;
    }

    public String toFile() {
        return kode + "|" + judul + "|" + jenis;
    }

    public static void tambah(String kode, String judul, String jenis) {
        Buku b = new Buku(kode, judul, jenis);
        FileHelper.write("buku.txt", b.toFile());
    }

    public static java.util.List<String[]> getAll() {
        java.util.List<String[]> list = new java.util.ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("buku.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split("\\|"));
            }
            br.close();
        } catch (Exception e) {}
        return list;
    }
}