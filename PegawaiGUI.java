import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PegawaiGUI extends JFrame {

    DefaultTableModel model;

    public PegawaiGUI() {
        setTitle("Data Pegawai");
        setSize(450,300);
        setLayout(null);

        model = new DefaultTableModel(new String[]{"NIP","Nama","Tanggal"},0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp).setBounds(20,20,400,150);

        loadData();

        JButton tambah = new JButton("Tambah");
        add(tambah).setBounds(170,200,100,30);

        tambah.addActionListener(e -> {
            String nip = JOptionPane.showInputDialog(this, "NIP");
            String nama = JOptionPane.showInputDialog(this, "Nama");

            if (nip == null || nama == null || nip.isEmpty() || nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!");
                return;
            }

            Pegawai.tambah(nip, nama);
            loadData();
        });

        setVisible(true);
    }

    void loadData() {
        model.setRowCount(0);
        for (String[] d : Pegawai.getAll()) {
            model.addRow(d);
        }
    }
}