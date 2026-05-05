import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SiswaGUI extends JFrame {
    DefaultTableModel model;

    public SiswaGUI() {
        setTitle("Data Siswa");
        setSize(400,300);
        setLayout(null);

        model = new DefaultTableModel(new String[]{"NIS","Nama","Alamat"},0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp).setBounds(20,20,350,150);

        loadData();

        JButton tambah = new JButton("Tambah");
        add(tambah).setBounds(150,200,100,30);

        tambah.addActionListener(e -> {
            String nis = JOptionPane.showInputDialog(this, "NIS");
            String nama = JOptionPane.showInputDialog(this, "Nama");
            String alamat = JOptionPane.showInputDialog(this, "Alamat");

            Siswa.tambah(nis,nama,alamat);
            loadData();
        });

        setVisible(true);
    }

    void loadData() {
        model.setRowCount(0);
        for (String[] d : Siswa.getAll()) {
            model.addRow(d);
        }
    }
}