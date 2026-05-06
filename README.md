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

### D. Pegawai.java
Mengelola data pegawai dan menyimpannya ke dalam file teks.

Atribut:
- String nip Nomor → Induk Pegawai sebagai identitas unik.
- String nama Nama → lengkap pegawai.
- Date tanggalLahir → Objek tanggal yang menyimpan waktu lahir

Method:
- Pegawai(String nip, String nama, Date tanggalLahir) → Membuat objek Pegawai baru dengan data awal (NIP, Nama, Tanggal).
- toFile() → Mengubah data objek menjadi satu baris String (format: NIP|Nama|Timestamp) untuk siap ditulis ke file.
- tambah(String nip, String nama) → Menginstansiasi objek Pegawai dan langsung menyimpannya ke file menggunakan bantuan FileHelper.
- getAll() → Membaca file pegawai.txt baris demi baris, memecahnya berdasarkan tanda `.

### E. Siswa.java
Model data sekaligus penyedia layanan untuk mengelola informasi siswa.

Atribut:
- nis (String) → Menyimpan Nomor Induk Siswa sebagai identitas unik.
- nama (String) → Menyimpan nama lengkap siswa.
- alamat (String) → Menyimpan informasi alamat tempat tinggal siswa.

Method:
- Siswa(String nis, String nama, String alamat) → Method khusus yang dipanggil saat pembuatan objek baru untuk mengisi data awal nis, nama, dan alamat.

- toFile() → Mengubah data objek menjadi satu baris teks dengan format pipe-delimited (nis|nama|alamat). Format ini digunakan agar data mudah disimpan dan dibaca kembali dari file teks.

- tambah(String nis, String nama, String alamat) → Mempermudah penambahan data baru. Method ini akan membuat objek Siswa dan langsung menyimpannya ke dalam file siswa.txt melalui bantuan class FileHelper.

- getAll() → Mengambil seluruh data dari file siswa.txt. Method ini membaca file baris demi baris, memecah (split) teks berdasarkan karakter |, dan memasukkannya ke dalam sebuah List berisi array String agar bisa ditampilkan kembali (misalnya ke dalam tabel GUI).

### F. Transaksi.java
Class ini bertugas mengatur aturan main transaksi, melakukan validasi (pembatasan), serta memperbarui status data pada file penyimpanan.

Atribut:
- tidak ada atribut instance, semua datanya bersifat dinamis dan langsung diproses di dalam method secara static.

Method:
- pinjam(String kode, String nis, String kb, String tgl) → Validasi: Mengecek file transaksi.txt untuk menghitung berapa banyak buku yang sedang dipinjam oleh siswa (nis) tersebut. Aturan: Jika siswa sudah meminjam 2 buku dan belum dikembalikan (status "0"), maka peminjaman baru akan ditolak (return false). Penyimpanan: Jika validasi lolos, data disimpan dengan format: kode|nis|kode_buku|tgl_pinjam|-|0 (angka 0 menandakan buku belum kembali).
- kembali(String kode, String tgl) → Pencarian: Mencari baris transaksi berdasarkan kode transaksi. Update: Mengubah tanggal kembali (dari - menjadi tanggal asli) dan mengubah status dari 0 (dipinjam) menjadi 1 (kembali). Manipulasi File: Karena file teks tidak bisa diedit langsung per baris, method ini membuat file sementara (temp.txt), menyalin data lama yang sudah diubah, lalu menggantikan file asli dengan file baru tersebut.

## GUI
### A. Frame: Login
Title: "Login"

Size: 300 x 200

Layout: null → posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Label & Input
JLabel "NIP" → Label untuk field NIP
JTextField nip → Input untuk memasukkan NIP pegawai
JLabel "Nama" → Label untuk field nama
JTextField nama → Input untuk memasukkan nama pegawai

2. Button
JButton login → Mengirim data login

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

Layout: null → posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tombol Menu
JButton s (Siswa) → Membuka `SiswaGUI`
Event: ActionListener → new SiswaGUI()
JButton b (Buku) → Membuka `BukuGUI`
Event: ActionListener → new BukuGUI()
JButton t (Transaksi) → Membuka `TransaksiGUI`
Event: ActionListener → new TransaksiGUI()
JButton p (Pegawai) → Membuka `PegawaiGUI`
Event: ActionListener → new PegawaiGUI()

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

Layout: null → posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tabel Data
JTable table → Menampilkan data buku
DefaultTableModel model → Menyimpan dan mengelola data tabel
JScrollPane sp → Membuat tabel dapat di-scroll jika data banyak

2. Button
JButton tambah → Menambahkan data buku baru
Fungsi: Menambahkan data buku baru
Event: ActionListener → membuka dialog input untuk Kode, Judul, dan Jenis

#### Method
1. void loadData()
Method untuk memuat data buku ke tabel.

#### Proses

Reset tabel (model.setRowCount(0))
Memanggil Buku.getAll() untuk mengambil semua data buku
Menambahkan setiap baris data ke model tabel

#### Event Handling
ActionListener (Button)

tambah → menampilkan dialog input dan memanggil Buku.tambah(kode, judul, jenis)
loadData() dipanggil ulang untuk memperbarui tabel setelah penambahan data

### D. Frame: PegawaiGUI
Title: "Data Pegawai"

Size: 450 x 300

Layout: null → posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tabel Data
JTable table: Menampilkan daftar data pegawai (NIP, Nama, Tanggal).
DefaultTableModel model: Objek yang menyimpan data baris dan kolom tabel.
JScrollPane sp: Kontainer yang membungkus tabel agar memiliki fitur scroll (gulir).

2. Button
JButton tambah: Tombol untuk memicu proses penambahan data.
Fungsi: Memasukkan data pegawai baru ke dalam sistem.
Event: ActionListener → Memunculkan JOptionPane.showInputDialog untuk input NIP dan Nama, melakukan validasi kosong, lalu memanggil Pegawai.tambah().

#### Method
void loadData()
Method untuk menyegarkan (refresh) tampilan tabel dengan data terbaru dari file.

#### Proses
Reset Tabel: Menghapus semua baris lama di tabel menggunakan model.setRowCount(0).
Ambil Data: Memanggil method static Pegawai.getAll() yang membaca data dari file teks.  
Update Tampilan: Melakukan perulangan (looping) untuk memasukkan setiap baris data yang didapat ke dalam model tabel agar muncul di layar.

### E. Frame: SiswaGUI
Title: "Data Siswa"

Size: 400 x 300

Layout: null → posisi komponen diatur secara manual dengan setBounds().

#### Komponen
1. Tabel Data
JTable table: Menampilkan daftar data siswa (NIS, Nama, Alamat).
DefaultTableModel model: Mengelola baris dan kolom data pada tabel.
JScrollPane sp: Panel gulir agar tabel tetap rapi jika jumlah data siswa bertambah banyak.

2. Button
JButton tambah: Tombol untuk memproses data baru.
Fungsi: Menambahkan data siswa ke dalam file.
Event: ActionListener → Membuka urutan dialog input (JOptionPane) untuk NIS, Nama, dan Alamat, lalu menjalankan fungsi Siswa.tambah().

#### Method
void loadData()
Method untuk memperbarui isi tabel secara otomatis agar sinkron dengan data di file siswa.txt.

#### Proses
Reset Tabel: Mengosongkan tampilan tabel lama dengan model.setRowCount(0).
Ambil Data: Memanggil Siswa.getAll() untuk mengambil list data terbaru.
Iterasi: Melakukan perulangan untuk memasukkan setiap baris data siswa ke dalam model tabel.

### F. Frame: TransaksiGUI
Title: "Transaksi"

Size: 350 x 300

Layout: null → posisi komponen diatur secara manual menggunakan setBounds().

#### Komponen
1. Input Data (Fields & ComboBox)
JTextField txtKode: Input teks untuk memasukkan kode unik transaksi.
JComboBox cbSiswa: Menu dropdown untuk memilih siswa (mengambil data dari Siswa.getAll()).
JComboBox cbBuku: Menu dropdown untuk memilih buku (mengambil data dari Buku.getAll()).
JTextField txtTanggal: Input teks untuk memasukkan tanggal transaksi.

2. Button
JButton btnPinjam: Tombol untuk menjalankan fungsi peminjaman.
JButton btnKembali: Tombol untuk menjalankan fungsi pengembalian buku.

#### Method
1. void loadSiswa(): Mengambil daftar siswa dari file dan memasukkannya ke dalam dropdown cbSiswa dengan format "NIS - Nama".

2. void loadBuku(): Mengambil daftar buku dari file dan memasukkannya ke dalam dropdown cbBuku dengan format "Kode - Judul".

#### Proses
1. Peminjaman (Pinjam):
Mengambil data dari inputan dan pilihan dropdown (hanya mengambil kode/NIS di depan tanda " - ").
Memanggil Transaksi.pinjam().
Jika sukses, muncul pesan berhasil; jika gagal (melebihi kuota 2 buku), muncul pesan peringatan.

2. Pengembalian (Kembali):
Mengambil kode transaksi dan tanggal kembali.
Memanggil Transaksi.kembali() untuk memperbarui status data di file.
Menampilkan pesan konfirmasi pengembalian.
