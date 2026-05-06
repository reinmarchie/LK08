# LK08 - Aplikasi PERPUSTAKAAN
## Class
### A. Admin.java
Class untuk melakukan verifikasi login pegawai dengan mencocokkan NIP dan nama dari file.

Atribut:
- tidak ada atribut instance; hanya digunakan sebagai utilitas login statis

Method:
- static boolean login(String nip, String nama) → membuka file `pegawai.txt` dengan BufferedReader, membaca setiap baris, memisahkan data berdasarkan "|", dan mengembalikan `true` jika ditemukan pasangan NIP dan nama yang cocok. Jika terjadi error, method mengembalikan `false`.

### B. Buku.java
Class untuk menyimpan data buku dan mengelola penyimpanan ke file.

Atribut:
- String kode → menyimpan kode unik buku
- String judul → menyimpan judul buku
- String jenis → menyimpan jenis atau kategori buku

Method:
- Buku(String kode, String judul, String jenis) → konstruktor untuk membuat objek Buku baru
- String toFile() → mengubah data buku menjadi format string yang dipisah dengan karakter "|" untuk disimpan ke file
- static void tambah(String kode, String judul, String jenis) → membuat objek Buku baru dan menulisnya ke file `buku.txt` dengan memanggil `FileHelper.write("buku.txt", b.toFile())`
- static java.util.List<String[]> getAll() → membaca semua baris dari file `buku.txt` menggunakan BufferedReader, memisahkan setiap baris berdasarkan "|", lalu mengembalikan daftar data buku

### C. FileHelper.java
Class utilitas untuk membantu operasi file sederhana yang dapat digunakan di class-class lainnya.

Method:
- static void write(String file, String data) → menulis `data` ke file yang ditentukan dalam mode append, menambahkan newline, dan menangani `IOException` dengan menampilkan pesan error.
- static void read(String file) → membaca isi file baris per baris menggunakan BufferedReader dan mencetak setiap baris ke konsol. Jika terjadi `IOException`, menampilkan pesan error.

## GUI
### A. Frame: Login
Title: "Login"

Size: 300 x 200

Layout: null -> posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Label & Input
JLabel "NIP" -> Label untuk field NIP
JTextField nip -> Input untuk memasukkan NIP pegawai
JLabel "Nama" -> Label untuk field nama
JTextField nama -> Input untuk memasukkan nama pegawai

2. Button
JButton login -> Mengirim data login

Fungsi: Mengirim data login
Event: ActionListener → memanggil `Admin.login(nip.getText(), nama.getText())`

Jika login berhasil, membuka frame `Menu` dan menutup frame login.
Jika gagal, menampilkan dialog "Login gagal".

#### Method
1. Login()
Method untuk membangun antarmuka login.

#### Proses
Membuat field NIP dan Nama
Menambahkan tombol Login
Mengatur posisi komponen dengan setBounds()
Menambahkan ActionListener pada tombol Login

2. main(String[] args)
Method entry point aplikasi yang membuat instance `Login`.

### B. Frame: Menu
Title: "Menu"

Size: 300 x 300

Layout: null -> posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tombol Menu
JButton s (Siswa) -> Membuka `SiswaGUI`
Event: ActionListener -> new SiswaGUI()
JButton b (Buku) -> Membuka `BukuGUI`
Event: ActionListener -> new BukuGUI()
JButton t (Transaksi) -> Membuka `TransaksiGUI`
Event: ActionListener -> new TransaksiGUI()
JButton p (Pegawai) -> Membuka `PegawaiGUI`
Event: ActionListener -> new PegawaiGUI()

#### Method
1. Menu()
Method untuk membangun frame menu utama.

#### Proses
Membuat keempat tombol menu
Menempatkan tombol dengan setBounds()
Menambahkan ActionListener untuk setiap tombol
Menampilkan frame dengan setVisible(true)

### C. Frame: BukuGUI
Title: "Data Buku"

Size: 450 x 300

Layout: null -> posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tabel Data
JTable table -> Menampilkan data buku
DefaultTableModel model -> Menyimpan dan mengelola data tabel
JScrollPane sp -> Membuat tabel dapat di-scroll jika data banyak

2. Button
JButton tambah -> Menambahkan data buku baru
Fungsi: Menambahkan data buku baru
Event: ActionListener -> membuka dialog input untuk Kode, Judul, dan Jenis

#### Method
1. void loadData()
Method untuk memuat data buku ke tabel.

Proses:

Reset tabel (model.setRowCount(0))
Memanggil Buku.getAll() untuk mengambil semua data buku
Menambahkan setiap baris data ke model tabel

#### Event Handling
ActionListener (Button)

tambah → menampilkan dialog input dan memanggil Buku.tambah(kode, judul, jenis)
loadData() dipanggil ulang untuk memperbarui tabel setelah penambahan data

