import java.io.*;
public class Transaksi {
    public static boolean pinjam(String kode, String nis, String kb, String tgl) {
        try {
            int count = 0;
            BufferedReader br = new BufferedReader(new FileReader("transaksi.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d[1].equals(nis) && d[5].equals("0")) count++;
            }
            br.close();

            if (count >= 2) return false;

            String data = kode + "|" + nis + "|" + kb + "|" + tgl + "|-|0";
            FileHelper.write("transaksi.txt", data);
            return true;

        } catch (Exception e) {}
        return false;
    }

    public static void kembali(String kode, String tgl) {
        try {
            File in = new File("transaksi.txt");
            File temp = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(in));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d[0].equals(kode)) {
                    line = d[0]+"|"+d[1]+"|"+d[2]+"|"+d[3]+"|"+tgl+"|1";
                }
                bw.write(line+"\n");
            }

            br.close();
            bw.close();
            in.delete();
            temp.renameTo(in);

        } catch (Exception e) {}
    }
}