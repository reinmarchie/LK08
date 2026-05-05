import javax.swing.*;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menu");
        setSize(300,300);
        setLayout(null);

        JButton p = new JButton("Pegawai");
        JButton s = new JButton("Siswa");
        JButton b = new JButton("Buku");
        JButton t = new JButton("Transaksi");

        add(p).setBounds(80,180,120,30);
        add(s).setBounds(80,30,120,30);
        add(b).setBounds(80,80,120,30);
        add(t).setBounds(80,130,120,30);

        s.addActionListener(e -> new SiswaGUI());
        b.addActionListener(e -> new BukuGUI());
        t.addActionListener(e -> new TransaksiGUI());
        p.addActionListener(e -> new PegawaiGUI());

        setVisible(true);
    }
}