import javax.swing.*;
public class Login extends JFrame {
    JTextField nip, nama;

    public Login() {
        setTitle("Login");
        setSize(300,200);
        setLayout(null);

        nip = new JTextField();
        nama = new JTextField();

        add(new JLabel("NIP")).setBounds(20,20,80,25);
        add(nip).setBounds(100,20,150,25);

        add(new JLabel("Nama")).setBounds(20,60,80,25);
        add(nama).setBounds(100,60,150,25);

        JButton login = new JButton("Login");
        add(login).setBounds(100,100,100,30);

        login.addActionListener(e -> {
            if (Admin.login(nip.getText(), nama.getText())) {
                new Menu();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login gagal");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}