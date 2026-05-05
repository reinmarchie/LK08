import java.io.*;
import java.util.*;

public class Pegawai {
    private String nip, nama;
    private Date tanggalLahir;

    public Pegawai(String nip, String nama, Date tanggalLahir) {
        this.nip = nip;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
    }

    public String toFile() {
        return nip + "|" + nama + "|" + tanggalLahir.getTime();
    }

    // tambah pegawai (GUI friendly)
    public static void tambah(String nip, String nama) {
        Pegawai p = new Pegawai(nip, nama, new Date());
        FileHelper.write("pegawai.txt", p.toFile());
    }

    // ambil semua data
    public static List<String[]> getAll() {
        List<String[]> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("pegawai.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split("\\|"));
            }
            br.close();
        } catch (Exception e) {}
        return list;
    }
}