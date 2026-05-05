import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BukuGUI extends JFrame {

    DefaultTableModel model;

    public BukuGUI() {
        setTitle("Data Buku");
        setSize(450,300);
        setLayout(null);

        model = new DefaultTableModel(new String[]{"Kode","Judul","Jenis"},0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp).setBounds(20,20,400,150);

        loadData();

        JButton tambah = new JButton("Tambah");
        add(tambah).setBounds(170,200,100,30);

        tambah.addActionListener(e -> {
            String kode = JOptionPane.showInputDialog(this, "Kode");
            String judul = JOptionPane.showInputDialog(this, "Judul");
            String jenis = JOptionPane.showInputDialog(this, "Jenis");

            if (kode == null || judul == null || jenis == null ||
                kode.isEmpty() || judul.isEmpty() || jenis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!");
                return;
            }

            Buku.tambah(kode, judul, jenis);
            loadData();
        });

        setVisible(true);
    }

    void loadData() {
        model.setRowCount(0);
        for (String[] d : Buku.getAll()) {
            model.addRow(d);
        }
    }
}