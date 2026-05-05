import javax.swing.*;

public class TransaksiGUI extends JFrame {

    JComboBox<String> cbSiswa, cbBuku;
    JTextField txtKode, txtTanggal;

    public TransaksiGUI() {
        setTitle("Transaksi");
        setSize(350,300);
        setLayout(null);

        // ===== KOMPONEN =====
        add(new JLabel("Kode")).setBounds(20,20,100,25);
        txtKode = new JTextField();
        add(txtKode).setBounds(120,20,180,25);

        add(new JLabel("Siswa")).setBounds(20,60,100,25);
        cbSiswa = new JComboBox<>();
        add(cbSiswa).setBounds(120,60,180,25);

        add(new JLabel("Buku")).setBounds(20,100,100,25);
        cbBuku = new JComboBox<>();
        add(cbBuku).setBounds(120,100,180,25);

        add(new JLabel("Tanggal")).setBounds(20,140,100,25);
        txtTanggal = new JTextField();
        add(txtTanggal).setBounds(120,140,180,25);

        JButton btnPinjam = new JButton("Pinjam");
        JButton btnKembali = new JButton("Kembali");

        add(btnPinjam).setBounds(50,190,100,30);
        add(btnKembali).setBounds(170,190,100,30);

        // ===== LOAD DATA DROPDOWN =====
        loadSiswa();
        loadBuku();

        // ===== ACTION PINJAM =====
        btnPinjam.addActionListener(e -> {
            String kode = txtKode.getText();
            String nis = cbSiswa.getSelectedItem().toString().split(" - ")[0];
            String buku = cbBuku.getSelectedItem().toString().split(" - ")[0];
            String tgl = txtTanggal.getText();

            boolean sukses = Transaksi.pinjam(kode, nis, buku, tgl);

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Berhasil pinjam!");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal! Max 2 buku");
            }
        });

        // ===== ACTION KEMBALI =====
        btnKembali.addActionListener(e -> {
            String kode = txtKode.getText();
            String tgl = txtTanggal.getText();

            Transaksi.kembali(kode, tgl);
            JOptionPane.showMessageDialog(this, "Buku dikembalikan!");
        });

        setVisible(true);
    }

    // ===== LOAD SISWA =====
    void loadSiswa() {
        cbSiswa.removeAllItems();
        for (String[] d : Siswa.getAll()) {
            cbSiswa.addItem(d[0] + " - " + d[1]); // NIS - Nama
        }
    }

    // ===== LOAD BUKU =====
    void loadBuku() {
        cbBuku.removeAllItems();
        for (String[] d : Buku.getAll()) {
            cbBuku.addItem(d[0] + " - " + d[1]); // Kode - Judul
        }
    }
}