package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;

/**
 * @author perpustakaan
 */
public class RMPenilaianAwalKeperawatanRanapKritis extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeMasalah, tabModeDetailMasalah, tabModeRencana, tabModeDetailRencana;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0, jml = 0, index = 0;

    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    private DlgCariDokter dokter = new DlgCariDokter(null, false);

    private StringBuilder htmlContent;

    private String pilihan = "", pilih1 = "", pilih2 = "", pilih3 = "", pilih4 = "", pilih5 = "", pilih6 = "",
            pilih7 = "", pilih8 = "";

    private boolean[] pilih;

    private String[] kode, masalah;

    private String masalahkeperawatan = "", finger = "";

    private File file;

    private FileWriter fileWriter;

    private String iyem;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode root;

    private JsonNode response;

    private FileReader myObj;

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMPenilaianAwalKeperawatanRanapKritis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{"No.Rawat", "No.RM", "Nama Pasien", "Tgl.Lahir", "J.K.",
            "NIP Pengkaji 1", "Nama Pengkaji 1", "NIP Pengkaji 2", "Nama Pengkaji 2", "Kode DPJP", "Nama DPJP",
            "Tgl.Asuhan", "Macam Kasus", "Anamnesis", "Tiba Di Ruang Rawat", "Cara Masuk", "Keluhan Utama",
            "Riwayat Penyakit Saat Ini", "Riwayat Penyakit Dahulu", "Riwayat Penyakit Keluarga",
            "Riwayat Penggunaan Obat", "Riwayat Pembedahan", "Riwayat Dirawat Di RS", "Alat Bantu Yang Dipakai",
            "Dalam Keadaan Hamil/Sedang Menyusui", "Riwayat Transfusi Darah", "Riwayat Alergi", "Merokok",
            "Batang/Hari", "Alkohol", "Gelas/Hari", "Obat Tidur", "Olah Raga", "Kesadaran Mental", "Keadaan Umum",
            "GCS(E,V,M)", "TD(mmHg)", "Nadi(x/menit)", "RR(x/menit)", "Suhu(°C)", "SpO2(%)", "BB(Kg)", "TB(cm)",
            "A. Jalan Nafas", "A. Berupa", "A. Pernapasan", "A. Dengan", "A. ETT/Tracheocanule", "A. Cuff",
            "A. Frekuensi", "A. Irama", "A. Kedalaman", "A. Sputum", "A. Konsistensi", "A. Nafas Bunyi",
            "A. Terdapat Darah", "A. Jumlah", "A. Suara Nafas", "A. A.G.D PH", "A. A.G.D pCO2", "A. A.G.D pO2",
            "A. A.G.D Sat O2", "B. Nadi", "B. Irama", "B. EKG", "B. Tekanan Darah S", "B. MAP", "B. CVP", "B. IBP",
            "B. Akral", "B. Distensi Vena Jugulari", "B. Suhu", "B. Warna Kulit", "B. Pengisian Kaplier",
            "B. Edema", "B. Pada", "B. Jantung Irama", "B. Bunyi", "B. Keluhan", "B. Karakteristik",
            "B. Sakit Dada", "B. Timbul", "B. HB", "B. HT", "B. Eritrosit", "B. Leukosit", "B. Trombosit",
            "B. Pendarahan", "B. CT/BT", "B. PTT/APTT", "C. Tingkat Kesadaran", "C. Pupil",
            "C. Reaksi Terhadap Cahaya", "C. GCS E", "C. GCS V", "C. GCS M", "C. Jumlah", "C. Terjadi", "C. Bagian",
            "C. ICP", "C. CPP", "C. SOD", "C. EUD", "C. Palkososial", "D. BAK Rutin", "D. BAK Saat Ini",
            "D. BAK Terkontrol", "D. Produksi Urine", "D. Warna", "D. Sakit Waktu", "D. Distensi",
            "D. Keluhan Sakit Pinggang", "E. BAB Rutin", "E. BAB Saat Ini", "E. Konsistensi", "E. Warna",
            "E. Lendir", "E. Mual/Muntah", "E. Kembung", "E. Distensi", "E. Nyeri Tekan", "E. NGT", "E. Intake",
            "F. Tugor Kulit", "F. Keadaan Kulit", "F. Lokasi", "F. Keadaan Luka", "F. Sulit Dalam Gerak",
            "F. Fraktur", "F. Area", "F. Odema", "F. Kekuatan Otot", "Usia Ibu Saat Hamil", "Gravida Ke",
            "Gangguan Hamil", "Tipe Persalinan", "BB Lahir", "Tinggi Badan", "Lingkar Kepala", "BB Saat Di Kaji",
            "Tinggi Badan Saat Di Kaji", "Imunisasi", "Imunisasi Belum", "Tengkurap", "Berdiri", "Bicara", "Duduk",
            "Berjalan", "Tumbuh Gigi", "Mandi", "Makan/Minum", "Berpakaian", "Eliminasi", "Berpindah",
            "Porsi Makan", "Frekuensi Makan", "Jenis Makanan", "Lama Tidur", "Gangguan Tidur",
            "a. Aktifitas Sehari-hari", "b. Berjalan", "c. Aktifitas", "d. Alat Ambulasi", "e. Ekstremitas Atas",
            "f. Ekstremitas Bawah", "g. Kemampuan Menggenggam", "h. Kemampuan Koordinasi",
            "i. Kesimpulan Gangguan Fungsi", "a. Kondisi Psikologis", "b. Adakah Perilaku",
            "c. Gangguan Jiwa di Masa Lalu", "d. Hubungan Pasien", "e. Agama", "f. Tinggal Dengan", "g. Pekerjaan",
            "h. Pembayaran", "i. Nilai-nilai Kepercayaan", "j. Bahasa Sehari-hari", "k. Pendidikan Pasien",
            "l. Pendidikan P.J.", "m. Edukasi Diberikan Kepada", "n. Wajib", "o. Halangan Lain", "p. Toharah",
            "q. Sholat", "r. Motivasi Kesembuhan & Ibadah", "Nyeri", "Penyebab Nyeri", "Kualitas Nyeri",
            "Lokasi Nyeri", "Nyeri Menyebar", "Skala Nyeri", "Waktu / Durasi", "Nyeri Hilang Bila",
            "Diberitahukan Pada Dokter", "Skala Humty Dumpty 1", "N.H.D 1", "Skala Humty Dumpty 2", "N.H.D 2",
            "Skala Humty Dumpty 3", "N.H.D 3", "Skala Humty Dumpty 4", "N.H.D 4", "Skala Humty Dumpty 5", "N.H.D 5",
            "Skala Humty Dumpty 6", "N.H.D 6", "Skala Humty Dumpty 7", "N.H.D 7", "T.H.D", "Skala Morse 1",
            "N.M. 1", "Skala Morse 2", "N.M. 2", "Skala Morse 3", "N.M. 3", "Skala Morse 4", "N.M. 4",
            "Skala Morse 5", "N.M. 5", "Skala Morse 6", "N.M. 6", "T.M.", "1. Apakah pasien tampak kurus ?",
            "Skor 1",
            "2. Apakah terdapat penurunan berat badan selama satu bulan terakhir (berdasarkan penilaian objektif data berat badan bila ada penilaian objektif orang tua pasien atau bayi <1 tahun berat badan tidak naik    selama 3 bulan terakhir) ?",
            "Skor 2",
            "3. Apakah terdapat salah satu dari kondisi berikut ? ( diare > 5 kali / hari dan muntah > 3 kali / hari dalam seminggu terakhir atau asupan makanan berkurang selama 1 minggu terakhir )",
            "Skor 3",
            "4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien beresiko mengalami malnutrisi ?",
            "Skor 4", "Total Skor", "Pasien dengan diagnosis khusus", "Keterangan Diagnosa Khusus",
            "Sudah dibaca dan diketahui oleh Dietisen", "Jam Dibaca Dietisen", "Umur > 65 Tahun",
            "Keterbatasan mobilitas", "Perawatan atau pengobatan lanjutan",
            "Bantuan untuk melakukan aktifitas sehari-hari", "Perencanaan 1", "Perencanaan 2", "Perencanaan 3",
            "Perencanaan 4", "Perencanaan 5", "Perencanaan 6", "Perencanaan 7", "Perencanaan 8",
            "Rencana Keperawatan Lainnya"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbObat.setModel(tabMode);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 193; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(25);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(117);
            } else if (i == 12) {
                column.setPreferredWidth(78);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(220);
            } else if (i == 17) {
                column.setPreferredWidth(220);
            } else if (i == 18) {
                column.setPreferredWidth(170);
            } else if (i == 19) {
                column.setPreferredWidth(170);
            } else if (i == 20) {
                column.setPreferredWidth(170);
            } else if (i == 21) {
                column.setPreferredWidth(150);
            } else if (i == 22) {
                column.setPreferredWidth(150);
            } else if (i == 23) {
                column.setPreferredWidth(125);
            } else if (i == 24) {
                column.setPreferredWidth(210);
            } else if (i == 25) {
                column.setPreferredWidth(130);
            } else if (i == 26) {
                column.setPreferredWidth(130);
            } else if (i == 27) {
                column.setPreferredWidth(48);
            } else if (i == 28) {
                column.setPreferredWidth(65);
            } else if (i == 29) {
                column.setPreferredWidth(44);
            } else if (i == 30) {
                column.setPreferredWidth(59);
            } else if (i == 31) {
                column.setPreferredWidth(61);
            } else if (i == 32) {
                column.setPreferredWidth(59);
            } else if (i == 33) {
                column.setPreferredWidth(120);
            } else if (i == 34) {
                column.setPreferredWidth(85);
            } else if (i == 35) {
                column.setPreferredWidth(64);
            } else if (i == 36) {
                column.setPreferredWidth(60);
            } else if (i == 37) {
                column.setPreferredWidth(74);
            } else if (i == 38) {
                column.setPreferredWidth(67);
            } else if (i == 39) {
                column.setPreferredWidth(52);
            } else if (i == 40) {
                column.setPreferredWidth(52);
            } else if (i == 41) {
                column.setPreferredWidth(44);
            } else if (i == 42) {
                column.setPreferredWidth(44);
            } else if (i == 43) {
                column.setPreferredWidth(150);
            } else if (i == 44) {
                column.setPreferredWidth(150);
            } else if (i == 45) {
                column.setPreferredWidth(106);
            } else if (i == 46) {
                column.setPreferredWidth(130);
            } else if (i == 47) {
                column.setPreferredWidth(65);
            } else if (i == 48) {
                column.setPreferredWidth(50);
            } else if (i == 49) {
                column.setPreferredWidth(130);
            } else if (i == 50) {
                column.setPreferredWidth(72);
            } else if (i == 51) {
                column.setPreferredWidth(54);
            } else if (i == 52) {
                column.setPreferredWidth(63);
            } else if (i == 53) {
                column.setPreferredWidth(69);
            } else if (i == 54) {
                column.setPreferredWidth(97);
            } else if (i == 55) {
                column.setPreferredWidth(75);
            } else if (i == 56) {
                column.setPreferredWidth(170);
            } else if (i == 57) {
                column.setPreferredWidth(70);
            } else if (i == 58) {
                column.setPreferredWidth(140);
            } else if (i == 59) {
                column.setPreferredWidth(140);
            } else if (i == 60) {
                column.setPreferredWidth(140);
            } else if (i == 61) {
                column.setPreferredWidth(140);
            } else if (i == 62) {
                column.setPreferredWidth(140);
            } else if (i == 63) {
                column.setPreferredWidth(111);
            } else if (i == 64) {
                column.setPreferredWidth(60);
            } else if (i == 65) {
                column.setPreferredWidth(60);
            } else if (i == 66) {
                column.setPreferredWidth(140);
            } else if (i == 67) {
                column.setPreferredWidth(119);
            } else if (i == 68) {
                column.setPreferredWidth(65);
            } else if (i == 69) {
                column.setPreferredWidth(74);
            } else if (i == 70) {
                column.setPreferredWidth(140);
            } else if (i == 71) {
                column.setPreferredWidth(41);
            } else if (i == 72) {
                column.setPreferredWidth(91);
            } else if (i == 73) {
                column.setPreferredWidth(66);
            } else if (i == 74) {
                column.setPreferredWidth(44);
            } else if (i == 75) {
                column.setPreferredWidth(159);
            } else if (i == 76) {
                column.setPreferredWidth(140);
            } else if (i == 77) {
                column.setPreferredWidth(94);
            } else if (i == 78) {
                column.setPreferredWidth(79);
            } else if (i == 79) {
                column.setPreferredWidth(140);
            } else if (i == 80) {
                column.setPreferredWidth(140);
            } else if (i == 81) {
                column.setPreferredWidth(79);
            } else if (i == 82) {
                column.setPreferredWidth(80);
            } else if (i == 83) {
                column.setPreferredWidth(85);
            } else if (i == 84) {
                column.setPreferredWidth(80);
            } else if (i == 85) {
                column.setPreferredWidth(79);
            } else if (i == 86) {
                column.setPreferredWidth(80);
            } else if (i == 87) {
                column.setPreferredWidth(80);
            } else if (i == 88) {
                column.setPreferredWidth(80);
            } else if (i == 89) {
                column.setPreferredWidth(103);
            } else if (i == 90) {
                column.setPreferredWidth(103);
            } else if (i == 91) {
                column.setPreferredWidth(103);
            } else if (i == 92) {
                column.setPreferredWidth(103);
            } else if (i == 93) {
                column.setPreferredWidth(103);
            } else if (i == 94) {
                column.setPreferredWidth(68);
            } else if (i == 95) {
                column.setPreferredWidth(90);
            } else if (i == 96) {
                column.setPreferredWidth(140);
            } else if (i == 97) {
                column.setPreferredWidth(65);
            } else if (i == 98) {
                column.setPreferredWidth(108);
            } else if (i == 99) {
                column.setPreferredWidth(120);
            } else if (i == 100) {
                column.setPreferredWidth(180);
            } else if (i == 101) {
                column.setPreferredWidth(67);
            } else if (i == 102) {
                column.setPreferredWidth(104);
            } else if (i == 103) {
                column.setPreferredWidth(140);
            } else if (i == 104) {
                column.setPreferredWidth(140);
            } else if (i == 105) {
                column.setPreferredWidth(170);
            } else if (i == 106) {
                column.setPreferredWidth(170);
            } else if (i == 107) {
                column.setPreferredWidth(161);
            } else if (i == 108) {
                column.setPreferredWidth(106);
            } else if (i == 109) {
                column.setPreferredWidth(250);
            } else if (i == 110) {
                column.setPreferredWidth(157);
            } else if (i == 111) {
                column.setPreferredWidth(105);
            } else if (i == 112) {
                column.setPreferredWidth(55);
            } else if (i == 113) {
                column.setPreferredWidth(140);
            } else if (i == 114) {
                column.setPreferredWidth(90);
            } else if (i == 115) {
                column.setPreferredWidth(90);
            } else if (i == 116) {
                column.setPreferredWidth(150);
            } else if (i == 117) {
                column.setPreferredWidth(110);
            } else if (i == 118) {
                column.setPreferredWidth(110);
            } else if (i == 119) {
                column.setPreferredWidth(95);
            } else if (i == 120) {
                column.setPreferredWidth(150);
            } else if (i == 121) {
                column.setPreferredWidth(80);
            } else if (i == 122) {
                column.setPreferredWidth(140);
            } else if (i == 123) {
                column.setPreferredWidth(140);
            } else if (i == 124) {
                column.setPreferredWidth(100);
            } else if (i == 125) {
                column.setPreferredWidth(85);
            } else if (i == 126) {
                column.setPreferredWidth(65);
            } else if (i == 127) {
                column.setPreferredWidth(80);
            } else if (i == 128) {
                column.setPreferredWidth(140);
            } else if (i == 129) {
                column.setPreferredWidth(140);
            } else if (i == 130) {
                column.setPreferredWidth(77);
            } else if (i == 131) {
                column.setPreferredWidth(40);
            } else if (i == 132) {
                column.setPreferredWidth(77);
            } else if (i == 133) {
                column.setPreferredWidth(40);
            } else if (i == 134) {
                column.setPreferredWidth(177);
            } else if (i == 135) {
                column.setPreferredWidth(40);
            } else if (i == 136) {
                column.setPreferredWidth(77);
            } else if (i == 137) {
                column.setPreferredWidth(40);
            } else if (i == 138) {
                column.setPreferredWidth(162);
            } else if (i == 139) {
                column.setPreferredWidth(40);
            } else if (i == 140) {
                column.setPreferredWidth(162);
            } else if (i == 141) {
                column.setPreferredWidth(40);
            } else if (i == 142) {
                column.setPreferredWidth(40);
            } else if (i == 143) {
                column.setPreferredWidth(82);
            } else if (i == 144) {
                column.setPreferredWidth(40);
            } else if (i == 145) {
                column.setPreferredWidth(82);
            } else if (i == 146) {
                column.setPreferredWidth(40);
            } else if (i == 147) {
                column.setPreferredWidth(82);
            } else if (i == 148) {
                column.setPreferredWidth(40);
            } else if (i == 149) {
                column.setPreferredWidth(82);
            } else if (i == 150) {
                column.setPreferredWidth(40);
            } else if (i == 151) {
                column.setPreferredWidth(82);
            } else if (i == 152) {
                column.setPreferredWidth(40);
            } else if (i == 153) {
                column.setPreferredWidth(82);
            } else if (i == 154) {
                column.setPreferredWidth(40);
            } else if (i == 155) {
                column.setPreferredWidth(82);
            } else if (i == 156) {
                column.setPreferredWidth(40);
            } else if (i == 157) {
                column.setPreferredWidth(82);
            } else if (i == 158) {
                column.setPreferredWidth(40);
            } else if (i == 159) {
                column.setPreferredWidth(82);
            } else if (i == 160) {
                column.setPreferredWidth(40);
            } else if (i == 161) {
                column.setPreferredWidth(86);
            } else if (i == 162) {
                column.setPreferredWidth(44);
            } else if (i == 163) {
                column.setPreferredWidth(86);
            } else if (i == 164) {
                column.setPreferredWidth(44);
            } else if (i == 165) {
                column.setPreferredWidth(40);
            } else if (i == 166) {
                column.setPreferredWidth(380);
            } else if (i == 167) {
                column.setPreferredWidth(40);
            } else if (i == 168) {
                column.setPreferredWidth(317);
            } else if (i == 169) {
                column.setPreferredWidth(40);
            } else if (i == 170) {
                column.setPreferredWidth(58);
            } else if (i == 171) {
                column.setPreferredWidth(165);
            } else if (i == 172) {
                column.setPreferredWidth(149);
            } else if (i == 173) {
                column.setPreferredWidth(209);
            } else if (i == 174) {
                column.setPreferredWidth(107);
            } else if (i == 175) {
                column.setPreferredWidth(107);
            } else if (i == 176) {
                column.setPreferredWidth(107);
            } else if (i == 177) {
                column.setPreferredWidth(107);
            } else if (i == 178) {
                column.setPreferredWidth(107);
            } else if (i == 179) {
                column.setPreferredWidth(107);
            } else if (i == 180) {
                column.setPreferredWidth(107);
            } else if (i == 181) {
                column.setPreferredWidth(107);
            } else if (i == 182) {
                column.setPreferredWidth(107);
            } else if (i == 183) {
                column.setPreferredWidth(107);
            } else if (i == 184) {
                column.setPreferredWidth(107);
            } else if (i == 185) {
                column.setPreferredWidth(107);
            } else if (i == 186) {
                column.setPreferredWidth(107);
            } else if (i == 187) {
                column.setPreferredWidth(200);
            } else if (i == 188) {
                column.setPreferredWidth(200);
            } else if (i == 189) {
                column.setPreferredWidth(200);
            } else if (i == 190) {
                column.setPreferredWidth(200);
            } else if (i == 191) {
                column.setPreferredWidth(200);
            } else if (i == 192) {
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        KetAnamnesis.setDocument(new batasInput((byte) 30).getKata(KetAnamnesis));
        KeluhanUtama.setDocument(new batasInput(300).getKata(KeluhanUtama));
        RPS.setDocument(new batasInput(300).getKata(RPS));
        RPD.setDocument(new batasInput(100).getKata(RPD));
        RPK.setDocument(new batasInput(100).getKata(RPK));
        RPO.setDocument(new batasInput(100).getKata(RPO));
        RPembedahan.setDocument(new batasInput((byte) 40).getKata(RPembedahan));
        RDirawatRS.setDocument(new batasInput((byte) 40).getKata(RDirawatRS));
        RTranfusi.setDocument(new batasInput((byte) 40).getKata(RTranfusi));
        Alergi.setDocument(new batasInput((byte) 40).getKata(Alergi));
        KetSedangMenyusui.setDocument(new batasInput((byte) 30).getKata(KetSedangMenyusui));
        KebiasaanJumlahRokok.setDocument(new batasInput((byte) 5).getKata(KebiasaanJumlahRokok));
        KebiasaanJumlahAlkohol.setDocument(new batasInput((byte) 5).getKata(KebiasaanJumlahAlkohol));
        KesadaranMental.setDocument(new batasInput((byte) 40).getKata(KesadaranMental));
        GCS.setDocument(new batasInput((byte) 10).getKata(GCS));
        TD.setDocument(new batasInput((byte) 8).getKata(TD));
        Nadi.setDocument(new batasInput((byte) 5).getKata(Nadi));
        RR.setDocument(new batasInput((byte) 5).getKata(RR));
        Suhu.setDocument(new batasInput((byte) 5).getKata(Suhu));
        SpO2.setDocument(new batasInput((byte) 5).getKata(SpO2));
        BB.setDocument(new batasInput((byte) 5).getKata(BB));
        TB.setDocument(new batasInput((byte) 5).getKata(TB));
        PolaNutrisiPorsi.setDocument(new batasInput((byte) 3).getKata(PolaNutrisiPorsi));
        PolaNutrisiFrekuensi.setDocument(new batasInput((byte) 3).getKata(PolaNutrisiFrekuensi));
        PolaNutrisiJenis.setDocument(new batasInput((byte) 20).getKata(PolaNutrisiJenis));
        PolaTidurLama.setDocument(new batasInput((byte) 3).getKata(PolaTidurLama));
        KeteranganBerjalan.setDocument(new batasInput((byte) 40).getKata(KeteranganBerjalan));
        KeteranganEkstrimitasAtas.setDocument(new batasInput((byte) 40).getKata(KeteranganEkstrimitasAtas));
        KeteranganEkstrimitasBawah.setDocument(new batasInput((byte) 40).getKata(KeteranganEkstrimitasBawah));
        KeteranganKemampuanMenggenggam.setDocument(new batasInput((byte) 40).getKata(KeteranganKemampuanMenggenggam));
        KeteranganKemampuanKoordinasi.setDocument(new batasInput((byte) 40).getKata(KeteranganKemampuanKoordinasi));
        KeteranganAdakahPerilaku.setDocument(new batasInput((byte) 40).getKata(KeteranganAdakahPerilaku));
        KeteranganTinggalDengan.setDocument(new batasInput((byte) 40).getKata(KeteranganTinggalDengan));
        KeteranganNilaiKepercayaan.setDocument(new batasInput((byte) 40).getKata(KeteranganNilaiKepercayaan));
        KeteranganEdukasiPsikologis.setDocument(new batasInput((byte) 40).getKata(KeteranganEdukasiPsikologis));
        KetProvokes.setDocument(new batasInput((byte) 50).getKata(KetProvokes));
        KetQuality.setDocument(new batasInput((byte) 50).getKata(KetQuality));
        Lokasi.setDocument(new batasInput((byte) 50).getKata(Lokasi));
        Durasi.setDocument(new batasInput((byte) 5).getKata(Durasi));
        KetNyeri.setDocument(new batasInput((byte) 50).getKata(KetNyeri));
        KetPadaDokter.setDocument(new batasInput((byte) 10).getKata(KetPadaDokter));
        KeteranganDiagnosaKhususGizi.setDocument(new batasInput((byte) 50).getKata(KeteranganDiagnosaKhususGizi));
        KeteranganDiketahuiDietisen.setDocument(new batasInput((byte) 10).getKata(KeteranganDiketahuiDietisen));
        Rencana.setDocument(new batasInput(200).getKata(Rencana));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

            });

            TCariMasalah.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

            });
        }

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    if (i == 1) {
                        KdPetugas
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    } else {
                        KdPetugas2
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas2
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }

                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        tabModeMasalah = new DefaultTableModel(null, new Object[]{"P", "KODE", "MASALAH KEPERAWATAN"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class};

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbMasalahKeperawatan.setModel(tabModeMasalah);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahKeperawatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMasalahKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbMasalahKeperawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }
        tbMasalahKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRencana = new DefaultTableModel(null, new Object[]{"P", "KODE", "RENCANA KEPERAWATAN"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class};

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbRencanaKeperawatan.setModel(tabModeRencana);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaKeperawatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRencanaKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaKeperawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }
        tbRencanaKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailMasalah = new DefaultTableModel(null, new Object[]{"Kode", "Masalah Keperawatan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbMasalahDetail.setModel(tabModeDetailMasalah);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahDetail.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMasalahDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasalahDetail.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbMasalahDetail.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailRencana = new DefaultTableModel(null, new Object[]{"Kode", "Rencana Keperawatan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbRencanaDetail.setModel(tabModeDetailRencana);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaDetail.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRencanaDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbRencanaDetail.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbRencanaDetail.setDefaultRenderer(Object.class, new WarnaTable());

        ChkAccor.setSelected(false);
        isMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel36 = new widget.Label();
        Anamnesis = new widget.ComboBox();
        TglAsuhan = new widget.Tanggal();
        NmPetugas2 = new widget.TextBox();
        BtnPetugas2 = new widget.Button();
        KdPetugas2 = new widget.TextBox();
        label15 = new widget.Label();
        label16 = new widget.Label();
        KdDPJP = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        BtnDPJP = new widget.Button();
        TibadiRuang = new widget.ComboBox();
        jLabel37 = new widget.Label();
        CaraMasuk = new widget.ComboBox();
        jLabel38 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel39 = new widget.Label();
        Alergi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        RPK = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        RPD = new widget.TextArea();
        jLabel32 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RPO = new widget.TextArea();
        jSeparator2 = new javax.swing.JSeparator();
        MacamKasus = new widget.ComboBox();
        jLabel41 = new widget.Label();
        KetAnamnesis = new widget.TextBox();
        jLabel40 = new widget.Label();
        RDirawatRS = new widget.TextBox();
        RPembedahan = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        AlatBantuDipakai = new widget.ComboBox();
        SedangMenyusui = new widget.ComboBox();
        jLabel44 = new widget.Label();
        KetSedangMenyusui = new widget.TextBox();
        jLabel45 = new widget.Label();
        RTranfusi = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel124 = new widget.Label();
        KebiasaanMerokok = new widget.ComboBox();
        KebiasaanJumlahRokok = new widget.TextBox();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        KebiasaanAlkohol = new widget.ComboBox();
        KebiasaanJumlahAlkohol = new widget.TextBox();
        jLabel127 = new widget.Label();
        KebiasaanNarkoba = new widget.ComboBox();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        OlahRaga = new widget.ComboBox();
        jLabel95 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel47 = new widget.Label();
        KesadaranMental = new widget.TextBox();
        jLabel130 = new widget.Label();
        KeadaanMentalUmum = new widget.ComboBox();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel17 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel26 = new widget.Label();
        RR = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel24 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel15 = new widget.Label();
        TB = new widget.TextBox();
        jLabel48 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel131 = new widget.Label();
        BreathingJalanNafas = new widget.ComboBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel169 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel170 = new widget.Label();
        PolaAktifitasEliminasi = new widget.ComboBox();
        jLabel171 = new widget.Label();
        jLabel172 = new widget.Label();
        jLabel173 = new widget.Label();
        jLabel174 = new widget.Label();
        PolaAktifitasMandi = new widget.ComboBox();
        PolaAktifitasMakan = new widget.ComboBox();
        PolaAktifitasBerpakaian = new widget.ComboBox();
        PolaAktifitasBerpindah = new widget.ComboBox();
        jLabel55 = new widget.Label();
        jLabel121 = new widget.Label();
        PolaNutrisiPorsi = new widget.TextBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        PolaNutrisiFrekuensi = new widget.TextBox();
        jLabel175 = new widget.Label();
        jLabel177 = new widget.Label();
        PolaNutrisiJenis = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel176 = new widget.Label();
        PolaTidurLama = new widget.TextBox();
        jLabel178 = new widget.Label();
        PolaTidurGangguan = new widget.ComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel180 = new widget.Label();
        jLabel179 = new widget.Label();
        AktifitasSehari2 = new widget.ComboBox();
        jLabel181 = new widget.Label();
        Berjalan = new widget.ComboBox();
        KeteranganBerjalan = new widget.TextBox();
        jLabel182 = new widget.Label();
        Aktifitas = new widget.ComboBox();
        jLabel183 = new widget.Label();
        AlatAmbulasi = new widget.ComboBox();
        jLabel184 = new widget.Label();
        EkstrimitasAtas = new widget.ComboBox();
        KeteranganEkstrimitasAtas = new widget.TextBox();
        jLabel185 = new widget.Label();
        EkstrimitasBawah = new widget.ComboBox();
        KeteranganEkstrimitasBawah = new widget.TextBox();
        jLabel186 = new widget.Label();
        KemampuanMenggenggam = new widget.ComboBox();
        KeteranganKemampuanMenggenggam = new widget.TextBox();
        jLabel187 = new widget.Label();
        KemampuanKoordinasi = new widget.ComboBox();
        KeteranganKemampuanKoordinasi = new widget.TextBox();
        jLabel188 = new widget.Label();
        KesimpulanGangguanFungsi = new widget.ComboBox();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel189 = new widget.Label();
        jLabel190 = new widget.Label();
        KondisiPsikologis = new widget.ComboBox();
        jLabel191 = new widget.Label();
        AdakahPerilaku = new widget.ComboBox();
        KeteranganAdakahPerilaku = new widget.TextBox();
        jLabel192 = new widget.Label();
        GangguanJiwa = new widget.ComboBox();
        jLabel193 = new widget.Label();
        HubunganAnggotaKeluarga = new widget.ComboBox();
        jLabel194 = new widget.Label();
        Agama = new widget.TextBox();
        jLabel195 = new widget.Label();
        TinggalDengan = new widget.ComboBox();
        KeteranganTinggalDengan = new widget.TextBox();
        jLabel196 = new widget.Label();
        PekerjaanPasien = new widget.TextBox();
        jLabel197 = new widget.Label();
        CaraBayar = new widget.TextBox();
        jLabel198 = new widget.Label();
        NilaiKepercayaan = new widget.ComboBox();
        KeteranganNilaiKepercayaan = new widget.TextBox();
        jLabel199 = new widget.Label();
        Bahasa = new widget.TextBox();
        jLabel200 = new widget.Label();
        PendidikanPasien = new widget.TextBox();
        jLabel201 = new widget.Label();
        PendidikanPJ = new widget.ComboBox();
        jLabel202 = new widget.Label();
        EdukasiPsikolgis = new widget.ComboBox();
        KeteranganEdukasiPsikologis = new widget.TextBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel203 = new widget.Label();
        jSeparator9 = new javax.swing.JSeparator();
        PanelWall = new usu.widget.glass.PanelGlass();
        Nyeri = new widget.ComboBox();
        jLabel204 = new widget.Label();
        Provokes = new widget.ComboBox();
        KetProvokes = new widget.TextBox();
        jLabel205 = new widget.Label();
        Quality = new widget.ComboBox();
        KetQuality = new widget.TextBox();
        jLabel206 = new widget.Label();
        jLabel207 = new widget.Label();
        Lokasi = new widget.TextBox();
        jLabel208 = new widget.Label();
        Menyebar = new widget.ComboBox();
        jLabel209 = new widget.Label();
        jLabel210 = new widget.Label();
        SkalaNyeri = new widget.ComboBox();
        jLabel211 = new widget.Label();
        Durasi = new widget.TextBox();
        jLabel212 = new widget.Label();
        jLabel213 = new widget.Label();
        NyeriHilang = new widget.ComboBox();
        KetNyeri = new widget.TextBox();
        jLabel214 = new widget.Label();
        PadaDokter = new widget.ComboBox();
        jLabel215 = new widget.Label();
        KetPadaDokter = new widget.TextBox();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel216 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel217 = new widget.Label();
        SkalaResiko1 = new widget.ComboBox();
        jLabel218 = new widget.Label();
        NilaiResiko1 = new widget.TextBox();
        jLabel219 = new widget.Label();
        jLabel220 = new widget.Label();
        jLabel221 = new widget.Label();
        SkalaResiko2 = new widget.ComboBox();
        jLabel222 = new widget.Label();
        NilaiResiko2 = new widget.TextBox();
        jLabel223 = new widget.Label();
        jLabel224 = new widget.Label();
        SkalaResiko3 = new widget.ComboBox();
        jLabel225 = new widget.Label();
        NilaiResiko3 = new widget.TextBox();
        jLabel226 = new widget.Label();
        jLabel227 = new widget.Label();
        SkalaResiko4 = new widget.ComboBox();
        jLabel228 = new widget.Label();
        NilaiResiko4 = new widget.TextBox();
        jLabel229 = new widget.Label();
        jLabel230 = new widget.Label();
        SkalaResiko5 = new widget.ComboBox();
        jLabel231 = new widget.Label();
        NilaiResiko5 = new widget.TextBox();
        jLabel232 = new widget.Label();
        jLabel233 = new widget.Label();
        SkalaResiko6 = new widget.ComboBox();
        jLabel234 = new widget.Label();
        NilaiResiko6 = new widget.TextBox();
        jLabel235 = new widget.Label();
        NilaiResikoTotal = new widget.TextBox();
        TingkatResiko = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel271 = new widget.Label();
        jLabel272 = new widget.Label();
        SkalaGizi1 = new widget.ComboBox();
        jLabel273 = new widget.Label();
        NilaiGizi1 = new widget.TextBox();
        jLabel274 = new widget.Label();
        SkalaGizi2 = new widget.ComboBox();
        jLabel275 = new widget.Label();
        NilaiGizi2 = new widget.TextBox();
        jLabel276 = new widget.Label();
        NilaiGiziTotal = new widget.TextBox();
        jLabel277 = new widget.Label();
        DiagnosaKhususGizi = new widget.ComboBox();
        KeteranganDiagnosaKhususGizi = new widget.TextBox();
        jLabel278 = new widget.Label();
        DiketahuiDietisen = new widget.ComboBox();
        jLabel279 = new widget.Label();
        KeteranganDiketahuiDietisen = new widget.TextBox();
        jSeparator12 = new javax.swing.JSeparator();
        Scroll6 = new widget.ScrollPane();
        tbMasalahKeperawatan = new widget.Table();
        TabRencanaKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa1 = new widget.PanelBiasa();
        Scroll8 = new widget.ScrollPane();
        tbRencanaKeperawatan = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        BtnTambahMasalah = new widget.Button();
        BtnAllMasalah = new widget.Button();
        BtnCariMasalah = new widget.Button();
        TCariMasalah = new widget.TextBox();
        BtnTambahRencana = new widget.Button();
        BtnAllRencana = new widget.Button();
        BtnCariRencana = new widget.Button();
        label13 = new widget.Label();
        TCariRencana = new widget.TextBox();
        label12 = new widget.Label();
        jLabel280 = new widget.Label();
        jLabel281 = new widget.Label();
        jLabel282 = new widget.Label();
        jLabel283 = new widget.Label();
        jLabel284 = new widget.Label();
        jLabel285 = new widget.Label();
        jLabel286 = new widget.Label();
        jLabel287 = new widget.Label();
        jLabel288 = new widget.Label();
        jLabel59 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        Kriteria1 = new widget.ComboBox();
        Kriteria2 = new widget.ComboBox();
        Kriteria3 = new widget.ComboBox();
        Kriteria4 = new widget.ComboBox();
        jLabel65 = new widget.Label();
        pilihan1 = new javax.swing.JCheckBox();
        pilihan2 = new javax.swing.JCheckBox();
        pilihan3 = new javax.swing.JCheckBox();
        pilihan4 = new javax.swing.JCheckBox();
        pilihan5 = new javax.swing.JCheckBox();
        pilihan6 = new javax.swing.JCheckBox();
        pilihan7 = new javax.swing.JCheckBox();
        pilihan8 = new javax.swing.JCheckBox();
        jLabel289 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel132 = new widget.Label();
        BreathingJalanNafasBerupa = new widget.ComboBox();
        jLabel133 = new widget.Label();
        BreathingPernapasan = new widget.ComboBox();
        jLabel134 = new widget.Label();
        BreathingPernapasanDengan = new widget.ComboBox();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        BreathingIrama = new widget.ComboBox();
        BreathingETT = new widget.TextBox();
        BreathingCuff = new widget.TextBox();
        BreathingFrekuensi = new widget.TextBox();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        BreathingKedalaman = new widget.ComboBox();
        jLabel141 = new widget.Label();
        BreathingSpulum = new widget.ComboBox();
        jLabel142 = new widget.Label();
        BreathingKonsistensi = new widget.ComboBox();
        jLabel143 = new widget.Label();
        BreathingNafasBunyi = new widget.ComboBox();
        jLabel144 = new widget.Label();
        BreathingTerdapatDarah = new widget.ComboBox();
        jLabel146 = new widget.Label();
        BreathingSuaraNafas = new widget.ComboBox();
        jLabel148 = new widget.Label();
        BreathingTerdapatDarahJumlah = new widget.TextBox();
        jLabel145 = new widget.Label();
        BreathingAnalisaGasDarahPH = new widget.TextBox();
        jLabel149 = new widget.Label();
        BreathingAnalisaGasDarahpCO2 = new widget.TextBox();
        jLabel150 = new widget.Label();
        BreathingAnalisaGasDarahpO2 = new widget.TextBox();
        jLabel151 = new widget.Label();
        jLabel152 = new widget.Label();
        jLabel153 = new widget.Label();
        jLabel154 = new widget.Label();
        BreathingAnalisaGasDarahSatO2 = new widget.TextBox();
        jLabel155 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel147 = new widget.Label();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        BloodSirkulasiPeriferIrama = new widget.ComboBox();
        BloodSirkulasiPeriferNadi = new widget.TextBox();
        jLabel158 = new widget.Label();
        BloodSirkulasiPeriferTekananDarah = new widget.TextBox();
        jLabel160 = new widget.Label();
        BloodSirkulasiPeriferMAP = new widget.TextBox();
        jLabel161 = new widget.Label();
        jLabel162 = new widget.Label();
        BloodSirkulasiPeriferCVP = new widget.TextBox();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        BloodSirkulasiPeriferIBP = new widget.TextBox();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        jLabel167 = new widget.Label();
        BloodSirkulasiPeriferAkral = new widget.ComboBox();
        jLabel168 = new widget.Label();
        BloodSirkulasiPeriferDistensiVenaJugulari = new widget.ComboBox();
        jLabel291 = new widget.Label();
        BloodSirkulasiPeriferWarnaKulit = new widget.ComboBox();
        jLabel292 = new widget.Label();
        BloodSirkulasiPeriferSuhu = new widget.TextBox();
        jLabel293 = new widget.Label();
        jLabel290 = new widget.Label();
        BloodSirkulasiPeriferPengisianKaplier = new widget.ComboBox();
        jLabel294 = new widget.Label();
        BloodSirkulasiPeriferEdema = new widget.ComboBox();
        jLabel295 = new widget.Label();
        BloodSirkulasiPeriferEdemaPada = new widget.ComboBox();
        jLabel296 = new widget.Label();
        BloodSirkulasiPeriferEKG = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel297 = new widget.Label();
        BloodSirkulasiJantungJantungIrama = new widget.ComboBox();
        jLabel298 = new widget.Label();
        BloodSirkulasiJantungBunyi = new widget.ComboBox();
        jLabel299 = new widget.Label();
        BloodSirkulasiJantungKeluhan = new widget.ComboBox();
        jLabel300 = new widget.Label();
        BloodSirkulasiJantungKarakteristik = new widget.ComboBox();
        jLabel301 = new widget.Label();
        BloodSirkulasiJantungSakitDada = new widget.ComboBox();
        jLabel302 = new widget.Label();
        BloodSirkulasiJantungSakitDadaTimbul = new widget.ComboBox();
        jLabel68 = new widget.Label();
        jLabel303 = new widget.Label();
        jLabel304 = new widget.Label();
        BloodHematologiHB = new widget.TextBox();
        jLabel305 = new widget.Label();
        BloodHematologiHt = new widget.TextBox();
        jLabel307 = new widget.Label();
        BloodHematologiEritrosit = new widget.TextBox();
        jLabel308 = new widget.Label();
        jLabel309 = new widget.Label();
        BloodHematologiLeukosit = new widget.TextBox();
        jLabel310 = new widget.Label();
        jLabel311 = new widget.Label();
        BloodHematologiTrombosit = new widget.TextBox();
        jLabel312 = new widget.Label();
        jLabel306 = new widget.Label();
        BloodHematologiPendarahan = new widget.ComboBox();
        jLabel313 = new widget.Label();
        BloodHematologiCT = new widget.TextBox();
        jLabel314 = new widget.Label();
        BloodHematologiPTT = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel315 = new widget.Label();
        BrainSirkulasiSerebralTingkatKesadaran = new widget.ComboBox();
        jLabel316 = new widget.Label();
        BrainSirkulasiSerebralPupil = new widget.ComboBox();
        jLabel317 = new widget.Label();
        BrainSirkulasiSerebralReaksiTerhadapCahaya = new widget.ComboBox();
        jLabel318 = new widget.Label();
        BrainSirkulasiSerebralGCSE = new widget.TextBox();
        jLabel320 = new widget.Label();
        BrainSirkulasiSerebralGCSV = new widget.TextBox();
        jLabel321 = new widget.Label();
        BrainSirkulasiSerebralGCSM = new widget.TextBox();
        jLabel322 = new widget.Label();
        jLabel323 = new widget.Label();
        BrainSirkulasiSerebralGCSJumlah = new widget.TextBox();
        jLabel319 = new widget.Label();
        BrainSirkulasiSerebralTerjadi = new widget.ComboBox();
        jLabel324 = new widget.Label();
        BrainSirkulasiSerebralTerjadiBagian = new widget.ComboBox();
        jLabel325 = new widget.Label();
        jLabel326 = new widget.Label();
        BrainSirkulasiSerebralICP = new widget.TextBox();
        jLabel327 = new widget.Label();
        BrainSirkulasiSerebralCPP = new widget.TextBox();
        jLabel328 = new widget.Label();
        BrainSirkulasiSerebralSOD = new widget.TextBox();
        jLabel329 = new widget.Label();
        BrainSirkulasiSerebralPalkososial = new widget.ComboBox();
        jLabel330 = new widget.Label();
        jLabel331 = new widget.Label();
        jLabel332 = new widget.Label();
        BrainSirkulasiSerebralEUD = new widget.TextBox();
        jLabel333 = new widget.Label();
        jLabel334 = new widget.Label();
        jLabel335 = new widget.Label();
        BowelBAKPolaRutin = new widget.TextBox();
        jLabel336 = new widget.Label();
        BowelBAKSaatIni = new widget.TextBox();
        jLabel337 = new widget.Label();
        jLabel338 = new widget.Label();
        jLabel339 = new widget.Label();
        BowelKonsistensi = new widget.ComboBox();
        jLabel340 = new widget.Label();
        BowelWarna = new widget.ComboBox();
        jLabel341 = new widget.Label();
        BowelLendir = new widget.ComboBox();
        jLabel342 = new widget.Label();
        BowelMual = new widget.ComboBox();
        BowelKembung = new widget.ComboBox();
        jLabel343 = new widget.Label();
        jLabel344 = new widget.Label();
        BowelDistensi = new widget.ComboBox();
        jLabel345 = new widget.Label();
        BowelNyeriTekan = new widget.ComboBox();
        jLabel346 = new widget.Label();
        BowelNGT = new widget.ComboBox();
        jLabel347 = new widget.Label();
        BowelIntake = new widget.TextBox();
        jLabel348 = new widget.Label();
        jLabel349 = new widget.Label();
        jLabel350 = new widget.Label();
        BladderBAKPolaRutin = new widget.TextBox();
        jLabel351 = new widget.Label();
        BladderBAKSaatIni = new widget.TextBox();
        jLabel352 = new widget.Label();
        jLabel353 = new widget.Label();
        BladderBAKTerkontrol = new widget.ComboBox();
        jLabel354 = new widget.Label();
        BladderProduksiUrin = new widget.TextBox();
        jLabel355 = new widget.Label();
        jLabel356 = new widget.Label();
        BladderWarna = new widget.ComboBox();
        jLabel357 = new widget.Label();
        BladderDistensi = new widget.ComboBox();
        jLabel358 = new widget.Label();
        BladderSakitWaktuBAK = new widget.ComboBox();
        jLabel359 = new widget.Label();
        BladderSakitPinggang = new widget.ComboBox();
        jLabel360 = new widget.Label();
        BoneTugorKulit = new widget.ComboBox();
        jLabel361 = new widget.Label();
        BoneKeadaanKulit = new widget.ComboBox();
        jLabel362 = new widget.Label();
        jLabel363 = new widget.Label();
        BoneKeadaanLuka = new widget.ComboBox();
        BoneLokasi = new widget.TextBox();
        jLabel364 = new widget.Label();
        BoneSulitDalamGerak = new widget.ComboBox();
        jLabel365 = new widget.Label();
        BoneFraktur = new widget.ComboBox();
        jLabel366 = new widget.Label();
        BoneArea = new widget.TextBox();
        jLabel367 = new widget.Label();
        BoneOdema = new widget.ComboBox();
        jLabel368 = new widget.Label();
        BoneKekuatanOtot = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel369 = new widget.Label();
        UsiaIbuSaatHamil = new widget.TextBox();
        jLabel370 = new widget.Label();
        GravidaKe = new widget.TextBox();
        jLabel371 = new widget.Label();
        GangguanHamil = new widget.TextBox();
        jLabel372 = new widget.Label();
        TipePersalinan = new widget.ComboBox();
        jLabel373 = new widget.Label();
        BBLahir = new widget.TextBox();
        jLabel374 = new widget.Label();
        jLabel375 = new widget.Label();
        TBLahir = new widget.TextBox();
        jLabel376 = new widget.Label();
        jLabel377 = new widget.Label();
        jLabel378 = new widget.Label();
        LingkarKepalaLahir = new widget.TextBox();
        jLabel379 = new widget.Label();
        BBDiKaji = new widget.TextBox();
        jLabel380 = new widget.Label();
        jLabel381 = new widget.Label();
        TBDiKaji = new widget.TextBox();
        jLabel382 = new widget.Label();
        jLabel383 = new widget.Label();
        ImunisasiDasar = new widget.ComboBox();
        jLabel384 = new widget.Label();
        ImunisasiBelum = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel385 = new widget.Label();
        TengkurapUsia = new widget.TextBox();
        jLabel386 = new widget.Label();
        BerdiriUsia = new widget.TextBox();
        jLabel387 = new widget.Label();
        BicaraUsia = new widget.TextBox();
        jLabel388 = new widget.Label();
        DudukUsia = new widget.TextBox();
        jLabel389 = new widget.Label();
        BerjalanUsia = new widget.TextBox();
        jLabel390 = new widget.Label();
        TumbuhGigiUsia = new widget.TextBox();
        jLabel259 = new widget.Label();
        jLabel256 = new widget.Label();
        jLabel253 = new widget.Label();
        jLabel250 = new widget.Label();
        jLabel247 = new widget.Label();
        jLabel243 = new widget.Label();
        jLabel240 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel241 = new widget.Label();
        jLabel244 = new widget.Label();
        jLabel248 = new widget.Label();
        jLabel251 = new widget.Label();
        jLabel254 = new widget.Label();
        jLabel257 = new widget.Label();
        jLabel260 = new widget.Label();
        SkalaHumptyDumpty7 = new widget.ComboBox();
        SkalaHumptyDumpty6 = new widget.ComboBox();
        SkalaHumptyDumpty5 = new widget.ComboBox();
        SkalaHumptyDumpty4 = new widget.ComboBox();
        SkalaHumptyDumpty3 = new widget.ComboBox();
        SkalaHumptyDumpty2 = new widget.ComboBox();
        SkalaHumptyDumpty1 = new widget.ComboBox();
        TingkatHumptyDumpty = new widget.Label();
        jLabel242 = new widget.Label();
        jLabel246 = new widget.Label();
        jLabel249 = new widget.Label();
        jLabel252 = new widget.Label();
        jLabel255 = new widget.Label();
        jLabel258 = new widget.Label();
        jLabel261 = new widget.Label();
        NilaiHumptyDumpty7 = new widget.TextBox();
        NilaiHumptyDumpty6 = new widget.TextBox();
        NilaiHumptyDumpty5 = new widget.TextBox();
        NilaiHumptyDumpty4 = new widget.TextBox();
        NilaiHumptyDumpty3 = new widget.TextBox();
        NilaiHumptyDumpty2 = new widget.TextBox();
        NilaiHumptyDumpty1 = new widget.TextBox();
        jLabel270 = new widget.Label();
        NilaiHumptyDumptyTotal = new widget.TextBox();
        jLabel391 = new widget.Label();
        jLabel392 = new widget.Label();
        jLabel393 = new widget.Label();
        jLabel394 = new widget.Label();
        jLabel395 = new widget.Label();
        jLabel396 = new widget.Label();
        jLabel397 = new widget.Label();
        jLabel398 = new widget.Label();
        SkalaGizi3 = new widget.ComboBox();
        jLabel399 = new widget.Label();
        NilaiGizi3 = new widget.TextBox();
        SkalaGizi4 = new widget.ComboBox();
        jLabel400 = new widget.Label();
        NilaiGizi4 = new widget.TextBox();
        MotivasiKesembuhanIbadah = new widget.ComboBox();
        jLabel401 = new widget.Label();
        Toharah = new widget.ComboBox();
        jLabel402 = new widget.Label();
        Sholat = new widget.ComboBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        Wajib = new widget.ComboBox();
        KetHalanganLain = new widget.TextBox();
        jLabel403 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        BtnPrint1 = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetail = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSemua.setText("Pilih Semua");
        ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSemua.setName("ppSemua"); // NOI18N
        ppSemua.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Keperawatan Rawat Inap Kritis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(954, 3861));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Pengkaji 1 :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(74, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(176, 40, 210, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(388, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(438, 70, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel36.setText("Anamnesis :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 100, 70, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(74, 100, 130, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024 10:56:34" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(512, 70, 135, 23);

        NmPetugas2.setEditable(false);
        NmPetugas2.setName("NmPetugas2"); // NOI18N
        NmPetugas2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas2);
        NmPetugas2.setBounds(614, 40, 210, 23);

        BtnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2.setMnemonic('2');
        BtnPetugas2.setToolTipText("Alt+2");
        BtnPetugas2.setName("BtnPetugas2"); // NOI18N
        BtnPetugas2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2ActionPerformed(evt);
            }
        });
        BtnPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugas2KeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas2);
        BtnPetugas2.setBounds(826, 40, 28, 23);

        KdPetugas2.setEditable(false);
        KdPetugas2.setName("KdPetugas2"); // NOI18N
        KdPetugas2.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugas2KeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas2);
        KdPetugas2.setBounds(512, 40, 100, 23);

        label15.setText("Pengkaji 2 :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(438, 40, 70, 23);

        label16.setText("DPJP :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 70, 70, 23);

        KdDPJP.setEditable(false);
        KdDPJP.setName("KdDPJP"); // NOI18N
        KdDPJP.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDPJPKeyPressed(evt);
            }
        });
        FormInput.add(KdDPJP);
        KdDPJP.setBounds(74, 70, 110, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setName("NmDPJP"); // NOI18N
        NmDPJP.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(186, 70, 230, 23);

        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('2');
        BtnDPJP.setToolTipText("Alt+2");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        BtnDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDPJPKeyPressed(evt);
            }
        });
        FormInput.add(BtnDPJP);
        BtnDPJP.setBounds(418, 70, 28, 23);

        TibadiRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jalan Tanpa Bantuan", "Kursi Roda", "Brankar" }));
        TibadiRuang.setName("TibadiRuang"); // NOI18N
        TibadiRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TibadiRuangKeyPressed(evt);
            }
        });
        FormInput.add(TibadiRuang);
        TibadiRuang.setBounds(516, 100, 155, 23);

        jLabel37.setText("Tiba Di Ruang Rawat :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(392, 100, 120, 23);

        CaraMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Poli", "IGD", "Lain-lain" }));
        CaraMasuk.setSelectedIndex(2);
        CaraMasuk.setName("CaraMasuk"); // NOI18N
        CaraMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraMasukKeyPressed(evt);
            }
        });
        FormInput.add(CaraMasuk);
        CaraMasuk.setBounds(759, 100, 95, 23);

        jLabel38.setText("Cara Masuk :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(685, 100, 70, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("I. RIWAYAT KESEHATAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 130, 180, 23);

        jLabel9.setText("Riwayat Penggunaan Obat :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(450, 210, 150, 23);

        jLabel39.setText("Riwayat Alergi :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(480, 370, 90, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(580, 370, 280, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(RPS);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(180, 210, 280, 43);

        jLabel30.setText("Riwayat Penyakit Saat Ini :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 210, 175, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        RPK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPK.setColumns(20);
        RPK.setRows(5);
        RPK.setName("RPK"); // NOI18N
        RPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPKKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(RPK);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(180, 260, 280, 43);

        jLabel31.setText("Riwayat Penyakit Keluarga :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 260, 175, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        RPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPD.setColumns(20);
        RPD.setRows(5);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(RPD);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(610, 160, 250, 43);

        jLabel32.setText("Riwayat Penyakit Dahulu :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(460, 160, 140, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RPO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPO.setColumns(20);
        RPO.setRows(5);
        RPO.setName("RPO"); // NOI18N
        RPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPOKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RPO);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(610, 210, 250, 43);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 130, 880, 1);

        MacamKasus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trauma", "Non Trauma" }));
        MacamKasus.setSelectedIndex(1);
        MacamKasus.setName("MacamKasus"); // NOI18N
        MacamKasus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MacamKasusKeyPressed(evt);
            }
        });
        FormInput.add(MacamKasus);
        MacamKasus.setBounds(742, 70, 112, 23);

        jLabel41.setText("Macam Kasus :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(658, 70, 80, 23);

        KetAnamnesis.setFocusTraversalPolicyProvider(true);
        KetAnamnesis.setName("KetAnamnesis"); // NOI18N
        KetAnamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetAnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(KetAnamnesis);
        KetAnamnesis.setBounds(208, 100, 175, 23);

        jLabel40.setText("Riwayat Dirawat Di RS :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(440, 310, 130, 23);

        RDirawatRS.setFocusTraversalPolicyProvider(true);
        RDirawatRS.setName("RDirawatRS"); // NOI18N
        RDirawatRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RDirawatRSKeyPressed(evt);
            }
        });
        FormInput.add(RDirawatRS);
        RDirawatRS.setBounds(580, 310, 280, 23);

        RPembedahan.setFocusTraversalPolicyProvider(true);
        RPembedahan.setName("RPembedahan"); // NOI18N
        RPembedahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPembedahanKeyPressed(evt);
            }
        });
        FormInput.add(RPembedahan);
        RPembedahan.setBounds(160, 310, 280, 23);

        jLabel42.setText("Riwayat Pembedahan :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 310, 156, 23);

        jLabel43.setText("Alat Bantu Yang Dipakai :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 340, 168, 23);

        AlatBantuDipakai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kacamata", "Prothesa", "Alat Bantu Dengar", "Lain-lain" }));
        AlatBantuDipakai.setSelectedIndex(3);
        AlatBantuDipakai.setName("AlatBantuDipakai"); // NOI18N
        AlatBantuDipakai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatBantuDipakaiKeyPressed(evt);
            }
        });
        FormInput.add(AlatBantuDipakai);
        AlatBantuDipakai.setBounds(180, 340, 140, 23);

        SedangMenyusui.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SedangMenyusui.setName("SedangMenyusui"); // NOI18N
        SedangMenyusui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SedangMenyusuiKeyPressed(evt);
            }
        });
        FormInput.add(SedangMenyusui);
        SedangMenyusui.setBounds(580, 340, 80, 23);

        jLabel44.setText("Apakah Dalam Keadaan Hamil / Sedang Menyusui :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(320, 340, 260, 23);

        KetSedangMenyusui.setFocusTraversalPolicyProvider(true);
        KetSedangMenyusui.setName("KetSedangMenyusui"); // NOI18N
        KetSedangMenyusui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSedangMenyusuiKeyPressed(evt);
            }
        });
        FormInput.add(KetSedangMenyusui);
        KetSedangMenyusui.setBounds(670, 340, 190, 23);

        jLabel45.setText("Riwayat Transfusi Darah :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 370, 170, 23);

        RTranfusi.setFocusTraversalPolicyProvider(true);
        RTranfusi.setName("RTranfusi"); // NOI18N
        RTranfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RTranfusiKeyPressed(evt);
            }
        });
        FormInput.add(RTranfusi);
        RTranfusi.setBounds(180, 370, 300, 23);

        jLabel46.setText("Kebiasaan :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 400, 101, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("batang/hari");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(250, 420, 70, 23);

        KebiasaanMerokok.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanMerokok.setName("KebiasaanMerokok"); // NOI18N
        KebiasaanMerokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanMerokokKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanMerokok);
        KebiasaanMerokok.setBounds(120, 420, 80, 23);

        KebiasaanJumlahRokok.setFocusTraversalPolicyProvider(true);
        KebiasaanJumlahRokok.setName("KebiasaanJumlahRokok"); // NOI18N
        KebiasaanJumlahRokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanJumlahRokokKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanJumlahRokok);
        KebiasaanJumlahRokok.setBounds(210, 420, 40, 23);

        jLabel125.setText("Merokok :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 420, 116, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("gelas/hari");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(490, 420, 60, 23);

        KebiasaanAlkohol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanAlkohol.setName("KebiasaanAlkohol"); // NOI18N
        KebiasaanAlkohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanAlkoholKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanAlkohol);
        KebiasaanAlkohol.setBounds(370, 420, 80, 23);

        KebiasaanJumlahAlkohol.setFocusTraversalPolicyProvider(true);
        KebiasaanJumlahAlkohol.setName("KebiasaanJumlahAlkohol"); // NOI18N
        KebiasaanJumlahAlkohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanJumlahAlkoholKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanJumlahAlkohol);
        KebiasaanJumlahAlkohol.setBounds(450, 420, 40, 23);

        jLabel127.setText("Alkohol :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(320, 420, 50, 23);

        KebiasaanNarkoba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanNarkoba.setName("KebiasaanNarkoba"); // NOI18N
        KebiasaanNarkoba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanNarkobaKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanNarkoba);
        KebiasaanNarkoba.setBounds(620, 420, 80, 23);

        jLabel128.setText("Obat Tidur :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(550, 420, 70, 23);

        jLabel129.setText("Olah Raga :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(700, 420, 70, 23);

        OlahRaga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        OlahRaga.setName("OlahRaga"); // NOI18N
        OlahRaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OlahRagaKeyPressed(evt);
            }
        });
        FormInput.add(OlahRaga);
        OlahRaga.setBounds(780, 420, 80, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("II. PEMERIKSAAN FISIK");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 450, 180, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 450, 880, 1);

        jLabel47.setText("Kesadaran Mental :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 470, 138, 23);

        KesadaranMental.setFocusTraversalPolicyProvider(true);
        KesadaranMental.setName("KesadaranMental"); // NOI18N
        KesadaranMental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranMentalKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranMental);
        KesadaranMental.setBounds(150, 470, 175, 23);

        jLabel130.setText("Keadaan Umum :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(350, 470, 90, 23);

        KeadaanMentalUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Buruk" }));
        KeadaanMentalUmum.setName("KeadaanMentalUmum"); // NOI18N
        KeadaanMentalUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanMentalUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanMentalUmum);
        KeadaanMentalUmum.setBounds(440, 470, 90, 23);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(560, 470, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(630, 470, 75, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(730, 470, 30, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(760, 470, 65, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(830, 470, 40, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 500, 73, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(80, 500, 50, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(130, 500, 50, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(190, 500, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(240, 500, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(290, 500, 50, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(370, 500, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(410, 500, 50, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(460, 500, 30, 23);

        jLabel24.setText("SpO2 :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(500, 500, 40, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        FormInput.add(SpO2);
        SpO2.setBounds(550, 500, 50, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("%");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(600, 500, 30, 23);

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(640, 500, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(670, 500, 50, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(720, 500, 30, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(760, 500, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(790, 500, 50, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("cm");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(840, 500, 30, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("A. BREATHING :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(50, 530, 187, 23);

        jLabel131.setText("Jalan Nafas :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(0, 550, 109, 23);

        BreathingJalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Sumbatan" }));
        BreathingJalanNafas.setName("BreathingJalanNafas"); // NOI18N
        BreathingJalanNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingJalanNafasKeyPressed(evt);
            }
        });
        FormInput.add(BreathingJalanNafas);
        BreathingJalanNafas.setBounds(120, 550, 103, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("- Sirkulasi Perifer");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(50, 740, 129, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("C. BRAIN :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(50, 1080, 98, 23);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("D. BLADDER :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(50, 1250, 108, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("E. BOWEL :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(50, 1350, 122, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("F. BONE :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(50, 1480, 95, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 1770, 880, 1);

        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel169.setText("III. POLA KEHIDUPAN SEHARI - HARI ");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(10, 1770, 490, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("b. Pola Nutrisi :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(40, 1870, 100, 23);

        jLabel170.setText("Mandi :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(0, 1810, 109, 23);

        PolaAktifitasEliminasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasEliminasi.setName("PolaAktifitasEliminasi"); // NOI18N
        PolaAktifitasEliminasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasEliminasiKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasEliminasi);
        PolaAktifitasEliminasi.setBounds(110, 1840, 164, 23);

        jLabel171.setText("Makan/Minum :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(300, 1810, 100, 23);

        jLabel172.setText("Berpakaian :");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(600, 1810, 80, 23);

        jLabel173.setText("Eliminasi :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(0, 1840, 109, 23);

        jLabel174.setText("Berpindah :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(300, 1840, 100, 23);

        PolaAktifitasMandi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasMandi.setName("PolaAktifitasMandi"); // NOI18N
        PolaAktifitasMandi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasMandiKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasMandi);
        PolaAktifitasMandi.setBounds(110, 1810, 164, 23);

        PolaAktifitasMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasMakan.setName("PolaAktifitasMakan"); // NOI18N
        PolaAktifitasMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasMakanKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasMakan);
        PolaAktifitasMakan.setBounds(400, 1810, 164, 23);

        PolaAktifitasBerpakaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasBerpakaian.setName("PolaAktifitasBerpakaian"); // NOI18N
        PolaAktifitasBerpakaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasBerpakaianKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasBerpakaian);
        PolaAktifitasBerpakaian.setBounds(680, 1810, 164, 23);

        PolaAktifitasBerpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasBerpindah.setName("PolaAktifitasBerpindah"); // NOI18N
        PolaAktifitasBerpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasBerpindahKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasBerpindah);
        PolaAktifitasBerpindah.setBounds(400, 1840, 164, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("a. Pola Aktifitas :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(40, 1790, 128, 23);

        jLabel121.setText("Porsi Makan");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(100, 1870, 73, 23);

        PolaNutrisiPorsi.setHighlighter(null);
        PolaNutrisiPorsi.setName("PolaNutrisiPorsi"); // NOI18N
        PolaNutrisiPorsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiPorsiKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiPorsi);
        PolaNutrisiPorsi.setBounds(180, 1870, 50, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("porsi,");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(230, 1870, 31, 23);

        jLabel123.setText("Frekuensi Makan");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(250, 1870, 94, 23);

        PolaNutrisiFrekuensi.setHighlighter(null);
        PolaNutrisiFrekuensi.setName("PolaNutrisiFrekuensi"); // NOI18N
        PolaNutrisiFrekuensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiFrekuensiKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiFrekuensi);
        PolaNutrisiFrekuensi.setBounds(350, 1870, 50, 23);

        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel175.setText("x/hari,");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(410, 1870, 40, 23);

        jLabel177.setText("Jenis Makanan");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(420, 1870, 92, 23);

        PolaNutrisiJenis.setHighlighter(null);
        PolaNutrisiJenis.setName("PolaNutrisiJenis"); // NOI18N
        PolaNutrisiJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiJenisKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiJenis);
        PolaNutrisiJenis.setBounds(520, 1870, 328, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("c. Pola Tidur :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(40, 1900, 80, 23);

        jLabel176.setText("Lama Tidur");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(100, 1900, 60, 23);

        PolaTidurLama.setHighlighter(null);
        PolaTidurLama.setName("PolaTidurLama"); // NOI18N
        PolaTidurLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaTidurLamaKeyPressed(evt);
            }
        });
        FormInput.add(PolaTidurLama);
        PolaTidurLama.setBounds(170, 1900, 50, 23);

        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel178.setText(" jam/hari,");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(220, 1900, 51, 23);

        PolaTidurGangguan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Gangguan", "Insomnia" }));
        PolaTidurGangguan.setName("PolaTidurGangguan"); // NOI18N
        PolaTidurGangguan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaTidurGangguanKeyPressed(evt);
            }
        });
        FormInput.add(PolaTidurGangguan);
        PolaTidurGangguan.setBounds(280, 1900, 164, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1930, 880, 1);

        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel180.setText("IV. PENGKAJIAN FUNGSI");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(10, 1930, 180, 23);

        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel179.setText("a. Kemampuan Aktifitas Sehari-hari");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(40, 1950, 180, 23);

        AktifitasSehari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan minimal", "Bantuan Sebagian", "Ketergantungan Total" }));
        AktifitasSehari2.setName("AktifitasSehari2"); // NOI18N
        AktifitasSehari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasSehari2KeyPressed(evt);
            }
        });
        FormInput.add(AktifitasSehari2);
        AktifitasSehari2.setBounds(220, 1950, 158, 23);

        jLabel181.setText("b. Berjalan :");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(390, 1950, 70, 23);

        Berjalan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Penurunan Kekuatan/ROM", "Paralisis", "Sering Jatuh", "Deformitas", "Hilang keseimbangan", "Riwayat Patah Tulang", "Lain-lain" }));
        Berjalan.setName("Berjalan"); // NOI18N
        Berjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerjalanKeyPressed(evt);
            }
        });
        FormInput.add(Berjalan);
        Berjalan.setBounds(460, 1950, 178, 23);

        KeteranganBerjalan.setFocusTraversalPolicyProvider(true);
        KeteranganBerjalan.setName("KeteranganBerjalan"); // NOI18N
        KeteranganBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganBerjalan);
        KeteranganBerjalan.setBounds(640, 1950, 204, 23);

        jLabel182.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel182.setText("c. Aktifitas");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(40, 1980, 60, 23);

        Aktifitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tirah Baring", "Duduk", "Berjalan" }));
        Aktifitas.setName("Aktifitas"); // NOI18N
        Aktifitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasKeyPressed(evt);
            }
        });
        FormInput.add(Aktifitas);
        Aktifitas.setBounds(100, 1980, 110, 23);

        jLabel183.setText("d. Alat Ambulasi :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(220, 1980, 100, 23);

        AlatAmbulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Walker", "Tongkat", "Kursi Roda", "Tidak Menggunakan" }));
        AlatAmbulasi.setName("AlatAmbulasi"); // NOI18N
        AlatAmbulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatAmbulasiKeyPressed(evt);
            }
        });
        FormInput.add(AlatAmbulasi);
        AlatAmbulasi.setBounds(320, 1980, 147, 23);

        jLabel184.setText("e. Ekstremitas Atas :");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(470, 1980, 110, 23);

        EkstrimitasAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Lemah", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasAtas.setName("EkstrimitasAtas"); // NOI18N
        EkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasAtas);
        EkstrimitasAtas.setBounds(590, 1980, 120, 23);

        KeteranganEkstrimitasAtas.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasAtas.setName("KeteranganEkstrimitasAtas"); // NOI18N
        KeteranganEkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasAtas);
        KeteranganEkstrimitasAtas.setBounds(710, 1980, 137, 23);

        jLabel185.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel185.setText("f. Ekstremitas Bawah");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(40, 2010, 110, 23);

        EkstrimitasBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Varises", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasBawah.setName("EkstrimitasBawah"); // NOI18N
        EkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasBawah);
        EkstrimitasBawah.setBounds(150, 2010, 120, 23);

        KeteranganEkstrimitasBawah.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasBawah.setName("KeteranganEkstrimitasBawah"); // NOI18N
        KeteranganEkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasBawah);
        KeteranganEkstrimitasBawah.setBounds(280, 2010, 137, 23);

        jLabel186.setText("g. Kemampuan Menggenggam :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(420, 2010, 160, 23);

        KemampuanMenggenggam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Terakhir", "Lain-lain" }));
        KemampuanMenggenggam.setName("KemampuanMenggenggam"); // NOI18N
        KemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanMenggenggam);
        KemampuanMenggenggam.setBounds(580, 2010, 147, 23);

        KeteranganKemampuanMenggenggam.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanMenggenggam.setName("KeteranganKemampuanMenggenggam"); // NOI18N
        KeteranganKemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanMenggenggam);
        KeteranganKemampuanMenggenggam.setBounds(730, 2010, 115, 23);

        jLabel187.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel187.setText("h. Kemampuan Koordinasi");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(40, 2040, 140, 23);

        KemampuanKoordinasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Ada Masalah" }));
        KemampuanKoordinasi.setName("KemampuanKoordinasi"); // NOI18N
        KemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanKoordinasi);
        KemampuanKoordinasi.setBounds(180, 2040, 147, 23);

        KeteranganKemampuanKoordinasi.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanKoordinasi.setName("KeteranganKemampuanKoordinasi"); // NOI18N
        KeteranganKemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanKoordinasi);
        KeteranganKemampuanKoordinasi.setBounds(330, 2040, 147, 23);

        jLabel188.setText("i. Kesimpulan Gangguan Fungsi :");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(480, 2040, 170, 23);

        KesimpulanGangguanFungsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak (Tidak Perlu Co DPJP)", "Ya (Co DPJP)" }));
        KesimpulanGangguanFungsi.setName("KesimpulanGangguanFungsi"); // NOI18N
        KesimpulanGangguanFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanGangguanFungsiKeyPressed(evt);
            }
        });
        FormInput.add(KesimpulanGangguanFungsi);
        KesimpulanGangguanFungsi.setBounds(650, 2040, 195, 23);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 2070, 880, 1);

        jLabel189.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel189.setText("V. RIWAYAT PSIKOLOGIS – SOSIAL – EKONOMI – BUDAYA – SPIRITUAL");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(10, 2070, 490, 23);

        jLabel190.setText(":");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(140, 2090, 10, 23);

        KondisiPsikologis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Marah", "Takut", "Depresi", "Cepat Lelah", "Cemas", "Gelisah", "Sulit Tidur", "Lain-lain" }));
        KondisiPsikologis.setName("KondisiPsikologis"); // NOI18N
        KondisiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KondisiPsikologis);
        KondisiPsikologis.setBounds(150, 2090, 142, 23);

        jLabel191.setText("b. Adakah Perilaku :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(290, 2090, 110, 23);

        AdakahPerilaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Perilaku Kekerasan", "Gangguan Efek", "Gangguan Memori", "Halusinasi", "Kecenderungan Percobaan Bunuh Diri", "Lain-lain" }));
        AdakahPerilaku.setName("AdakahPerilaku"); // NOI18N
        AdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(AdakahPerilaku);
        AdakahPerilaku.setBounds(410, 2090, 235, 23);

        KeteranganAdakahPerilaku.setFocusTraversalPolicyProvider(true);
        KeteranganAdakahPerilaku.setName("KeteranganAdakahPerilaku"); // NOI18N
        KeteranganAdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganAdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganAdakahPerilaku);
        KeteranganAdakahPerilaku.setBounds(650, 2090, 202, 23);

        jLabel192.setText(":");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(190, 2120, 10, 23);

        GangguanJiwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        GangguanJiwa.setName("GangguanJiwa"); // NOI18N
        GangguanJiwa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanJiwaKeyPressed(evt);
            }
        });
        FormInput.add(GangguanJiwa);
        GangguanJiwa.setBounds(200, 2120, 77, 23);

        jLabel193.setText("d. Hubungan Pasien dengan Anggota Keluarga :");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(280, 2120, 240, 23);

        HubunganAnggotaKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Harmonis", "Kurang Harmonis", "Tidak Harmonis", "Konflik Besar" }));
        HubunganAnggotaKeluarga.setName("HubunganAnggotaKeluarga"); // NOI18N
        HubunganAnggotaKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganAnggotaKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(HubunganAnggotaKeluarga);
        HubunganAnggotaKeluarga.setBounds(530, 2120, 133, 23);

        jLabel194.setText("e. Agama :");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(660, 2120, 60, 23);

        Agama.setEditable(false);
        Agama.setFocusTraversalPolicyProvider(true);
        Agama.setName("Agama"); // NOI18N
        FormInput.add(Agama);
        Agama.setBounds(730, 2120, 120, 23);

        jLabel195.setText(":");
        jLabel195.setName("jLabel195"); // NOI18N
        FormInput.add(jLabel195);
        jLabel195.setBounds(120, 2150, 10, 23);

        TinggalDengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sendiri", "Orang Tua", "Suami/Istri", "Keluarga", "Lain-lain" }));
        TinggalDengan.setName("TinggalDengan"); // NOI18N
        TinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(TinggalDengan);
        TinggalDengan.setBounds(140, 2150, 105, 23);

        KeteranganTinggalDengan.setFocusTraversalPolicyProvider(true);
        KeteranganTinggalDengan.setName("KeteranganTinggalDengan"); // NOI18N
        KeteranganTinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganTinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganTinggalDengan);
        KeteranganTinggalDengan.setBounds(250, 2150, 137, 23);

        jLabel196.setText("g. Pekerjaan :");
        jLabel196.setName("jLabel196"); // NOI18N
        FormInput.add(jLabel196);
        jLabel196.setBounds(380, 2150, 83, 23);

        PekerjaanPasien.setEditable(false);
        PekerjaanPasien.setFocusTraversalPolicyProvider(true);
        PekerjaanPasien.setName("PekerjaanPasien"); // NOI18N
        FormInput.add(PekerjaanPasien);
        PekerjaanPasien.setBounds(470, 2150, 140, 23);

        jLabel197.setText("h. Pembayaran :");
        jLabel197.setName("jLabel197"); // NOI18N
        FormInput.add(jLabel197);
        jLabel197.setBounds(610, 2150, 90, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setFocusTraversalPolicyProvider(true);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(710, 2150, 140, 23);

        jLabel198.setText(":");
        jLabel198.setName("jLabel198"); // NOI18N
        FormInput.add(jLabel198);
        jLabel198.setBounds(320, 2180, 10, 23);

        NilaiKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        NilaiKepercayaan.setName("NilaiKepercayaan"); // NOI18N
        NilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(NilaiKepercayaan);
        NilaiKepercayaan.setBounds(330, 2180, 105, 23);

        KeteranganNilaiKepercayaan.setFocusTraversalPolicyProvider(true);
        KeteranganNilaiKepercayaan.setName("KeteranganNilaiKepercayaan"); // NOI18N
        KeteranganNilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganNilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganNilaiKepercayaan);
        KeteranganNilaiKepercayaan.setBounds(440, 2180, 160, 23);

        jLabel199.setText("j. Bahasa Sehari-hari :");
        jLabel199.setName("jLabel199"); // NOI18N
        FormInput.add(jLabel199);
        jLabel199.setBounds(600, 2180, 120, 23);

        Bahasa.setEditable(false);
        Bahasa.setFocusTraversalPolicyProvider(true);
        Bahasa.setName("Bahasa"); // NOI18N
        FormInput.add(Bahasa);
        Bahasa.setBounds(730, 2180, 120, 23);

        jLabel200.setText(":");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(140, 2210, 10, 23);

        PendidikanPasien.setEditable(false);
        PendidikanPasien.setFocusTraversalPolicyProvider(true);
        PendidikanPasien.setName("PendidikanPasien"); // NOI18N
        FormInput.add(PendidikanPasien);
        PendidikanPasien.setBounds(150, 2210, 100, 23);

        jLabel201.setText("l. Pendidikan P.J. :");
        jLabel201.setName("jLabel201"); // NOI18N
        FormInput.add(jLabel201);
        jLabel201.setBounds(250, 2210, 100, 23);

        PendidikanPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA", "SLTA/SEDERAJAT", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        PendidikanPJ.setName("PendidikanPJ"); // NOI18N
        PendidikanPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PendidikanPJKeyPressed(evt);
            }
        });
        FormInput.add(PendidikanPJ);
        PendidikanPJ.setBounds(350, 2210, 135, 23);

        jLabel202.setText("m. Edukasi Diberikan Kepada :");
        jLabel202.setName("jLabel202"); // NOI18N
        FormInput.add(jLabel202);
        jLabel202.setBounds(490, 2210, 160, 23);

        EdukasiPsikolgis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien", "Keluarga" }));
        EdukasiPsikolgis.setName("EdukasiPsikolgis"); // NOI18N
        EdukasiPsikolgis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiPsikolgisKeyPressed(evt);
            }
        });
        FormInput.add(EdukasiPsikolgis);
        EdukasiPsikolgis.setBounds(650, 2210, 95, 23);

        KeteranganEdukasiPsikologis.setFocusTraversalPolicyProvider(true);
        KeteranganEdukasiPsikologis.setName("KeteranganEdukasiPsikologis"); // NOI18N
        KeteranganEdukasiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEdukasiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEdukasiPsikologis);
        KeteranganEdukasiPsikologis.setBounds(750, 2210, 99, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 2300, 880, 1);

        jLabel203.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel203.setText("VI. PENILAIAN TINGKAT NYERI");
        jLabel203.setName("jLabel203"); // NOI18N
        FormInput.add(jLabel203);
        jLabel203.setBounds(10, 2300, 380, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(360, 2310, 1, 140);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(30, 2320, 320, 130);

        Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Nyeri", "Nyeri Akut", "Nyeri Kronis" }));
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Nyeri);
        Nyeri.setBounds(370, 2320, 130, 23);

        jLabel204.setText("Penyebab :");
        jLabel204.setName("jLabel204"); // NOI18N
        FormInput.add(jLabel204);
        jLabel204.setBounds(500, 2320, 60, 23);

        Provokes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proses Penyakit", "Benturan", "Lain-lain" }));
        Provokes.setName("Provokes"); // NOI18N
        Provokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProvokesKeyPressed(evt);
            }
        });
        FormInput.add(Provokes);
        Provokes.setBounds(570, 2320, 130, 23);

        KetProvokes.setFocusTraversalPolicyProvider(true);
        KetProvokes.setName("KetProvokes"); // NOI18N
        KetProvokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProvokesKeyPressed(evt);
            }
        });
        FormInput.add(KetProvokes);
        KetProvokes.setBounds(700, 2320, 146, 23);

        jLabel205.setText("Kualitas :");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(360, 2350, 55, 23);

        Quality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seperti Tertusuk", "Berdenyut", "Teriris", "Tertindih", "Tertiban", "Lain-lain" }));
        Quality.setName("Quality"); // NOI18N
        Quality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QualityKeyPressed(evt);
            }
        });
        FormInput.add(Quality);
        Quality.setBounds(420, 2350, 140, 23);

        KetQuality.setFocusTraversalPolicyProvider(true);
        KetQuality.setName("KetQuality"); // NOI18N
        KetQuality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetQualityKeyPressed(evt);
            }
        });
        FormInput.add(KetQuality);
        KetQuality.setBounds(570, 2350, 280, 23);

        jLabel206.setText("Wilayah :");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(360, 2380, 55, 23);

        jLabel207.setText("Lokasi :");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(390, 2400, 60, 23);

        Lokasi.setFocusTraversalPolicyProvider(true);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput.add(Lokasi);
        Lokasi.setBounds(450, 2400, 220, 23);

        jLabel208.setText("Menyebar :");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(680, 2400, 79, 23);

        Menyebar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Menyebar.setName("Menyebar"); // NOI18N
        Menyebar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenyebarKeyPressed(evt);
            }
        });
        FormInput.add(Menyebar);
        Menyebar.setBounds(770, 2400, 80, 23);

        jLabel209.setText("Severity :");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(360, 2430, 55, 23);

        jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel210.setText("Skala Nyeri");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(420, 2430, 60, 23);

        SkalaNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(490, 2430, 70, 23);

        jLabel211.setText("Waktu / Durasi :");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(620, 2430, 90, 23);

        Durasi.setFocusTraversalPolicyProvider(true);
        Durasi.setName("Durasi"); // NOI18N
        Durasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DurasiKeyPressed(evt);
            }
        });
        FormInput.add(Durasi);
        Durasi.setBounds(720, 2430, 90, 23);

        jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel212.setText("Menit");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(810, 2430, 35, 23);

        jLabel213.setText("Nyeri hilang bila :");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(0, 2460, 130, 23);

        NyeriHilang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Istirahat", "Medengar Musik", "Minum Obat" }));
        NyeriHilang.setName("NyeriHilang"); // NOI18N
        NyeriHilang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriHilangKeyPressed(evt);
            }
        });
        FormInput.add(NyeriHilang);
        NyeriHilang.setBounds(130, 2460, 130, 23);

        KetNyeri.setFocusTraversalPolicyProvider(true);
        KetNyeri.setName("KetNyeri"); // NOI18N
        KetNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNyeriKeyPressed(evt);
            }
        });
        FormInput.add(KetNyeri);
        KetNyeri.setBounds(260, 2460, 150, 23);

        jLabel214.setText("Diberitahukan pada dokter ?");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(470, 2460, 150, 23);

        PadaDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PadaDokter.setName("PadaDokter"); // NOI18N
        PadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(PadaDokter);
        PadaDokter.setBounds(630, 2460, 80, 23);

        jLabel215.setText("Jam  :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(710, 2460, 50, 23);

        KetPadaDokter.setFocusTraversalPolicyProvider(true);
        KetPadaDokter.setName("KetPadaDokter"); // NOI18N
        KetPadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(KetPadaDokter);
        KetPadaDokter.setBounds(770, 2460, 80, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 2490, 880, 1);

        jLabel216.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel216.setText("VII. PENILAIAN RESIKO JATUH");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(10, 2490, 380, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Skala Morse :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(30, 2780, 80, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1. Riwayat Jatuh");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(50, 2800, 300, 23);

        SkalaResiko1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        SkalaResiko1.setName("SkalaResiko1"); // NOI18N
        SkalaResiko1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko1ItemStateChanged(evt);
            }
        });
        SkalaResiko1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko1);
        SkalaResiko1.setBounds(410, 2800, 280, 23);

        jLabel218.setText("Nilai :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(700, 2800, 75, 23);

        NilaiResiko1.setEditable(false);
        NilaiResiko1.setFocusTraversalPolicyProvider(true);
        NilaiResiko1.setName("NilaiResiko1"); // NOI18N
        FormInput.add(NilaiResiko1);
        NilaiResiko1.setBounds(780, 2800, 60, 23);

        jLabel219.setText("Skala :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(320, 2800, 80, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Diagnosis Sekunder (≥ 2 Diagnosis Medis)");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(50, 2830, 300, 23);

        jLabel221.setText("Skala :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(320, 2830, 80, 23);

        SkalaResiko2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        SkalaResiko2.setName("SkalaResiko2"); // NOI18N
        SkalaResiko2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko2ItemStateChanged(evt);
            }
        });
        SkalaResiko2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko2);
        SkalaResiko2.setBounds(410, 2830, 280, 23);

        jLabel222.setText("Nilai :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(700, 2830, 75, 23);

        NilaiResiko2.setEditable(false);
        NilaiResiko2.setFocusTraversalPolicyProvider(true);
        NilaiResiko2.setName("NilaiResiko2"); // NOI18N
        FormInput.add(NilaiResiko2);
        NilaiResiko2.setBounds(780, 2830, 60, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Alat Bantu");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(50, 2860, 300, 23);

        jLabel224.setText("Skala :");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(320, 2860, 80, 23);

        SkalaResiko3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Ada/Kursi Roda/Perawat/Tirah Baring", "Tongkat/Alat Penopang", "Berpegangan Pada Perabot" }));
        SkalaResiko3.setName("SkalaResiko3"); // NOI18N
        SkalaResiko3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko3ItemStateChanged(evt);
            }
        });
        SkalaResiko3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko3);
        SkalaResiko3.setBounds(410, 2860, 280, 23);

        jLabel225.setText("Nilai :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(700, 2860, 75, 23);

        NilaiResiko3.setEditable(false);
        NilaiResiko3.setFocusTraversalPolicyProvider(true);
        NilaiResiko3.setName("NilaiResiko3"); // NOI18N
        FormInput.add(NilaiResiko3);
        NilaiResiko3.setBounds(780, 2860, 60, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Terpasang Infuse");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(50, 2890, 300, 23);

        jLabel227.setText("Skala :");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(320, 2890, 80, 23);

        SkalaResiko4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        SkalaResiko4.setName("SkalaResiko4"); // NOI18N
        SkalaResiko4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko4ItemStateChanged(evt);
            }
        });
        SkalaResiko4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko4);
        SkalaResiko4.setBounds(410, 2890, 280, 23);

        jLabel228.setText("Nilai :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(700, 2890, 75, 23);

        NilaiResiko4.setEditable(false);
        NilaiResiko4.setFocusTraversalPolicyProvider(true);
        NilaiResiko4.setName("NilaiResiko4"); // NOI18N
        FormInput.add(NilaiResiko4);
        NilaiResiko4.setBounds(780, 2890, 60, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("5. Gaya Berjalan");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(50, 2920, 300, 23);

        jLabel230.setText("Skala :");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(320, 2920, 80, 23);

        SkalaResiko5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal/Tirah Baring/Imobilisasi", "Lemah", "Terganggu" }));
        SkalaResiko5.setName("SkalaResiko5"); // NOI18N
        SkalaResiko5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko5ItemStateChanged(evt);
            }
        });
        SkalaResiko5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko5);
        SkalaResiko5.setBounds(410, 2920, 280, 23);

        jLabel231.setText("Nilai :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(700, 2920, 75, 23);

        NilaiResiko5.setEditable(false);
        NilaiResiko5.setFocusTraversalPolicyProvider(true);
        NilaiResiko5.setName("NilaiResiko5"); // NOI18N
        FormInput.add(NilaiResiko5);
        NilaiResiko5.setBounds(780, 2920, 60, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("6. Status Mental");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(50, 2950, 300, 23);

        jLabel233.setText("Skala :");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(320, 2950, 80, 23);

        SkalaResiko6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sadar Akan Kemampuan Diri Sendiri", "Sering Lupa Akan Keterbatasan Yang Dimiliki" }));
        SkalaResiko6.setName("SkalaResiko6"); // NOI18N
        SkalaResiko6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko6ItemStateChanged(evt);
            }
        });
        SkalaResiko6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko6);
        SkalaResiko6.setBounds(410, 2950, 280, 23);

        jLabel234.setText("Nilai :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(700, 2950, 75, 23);

        NilaiResiko6.setEditable(false);
        NilaiResiko6.setFocusTraversalPolicyProvider(true);
        NilaiResiko6.setName("NilaiResiko6"); // NOI18N
        FormInput.add(NilaiResiko6);
        NilaiResiko6.setBounds(780, 2950, 60, 23);

        jLabel235.setText("Total :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(700, 2980, 75, 23);

        NilaiResikoTotal.setEditable(false);
        NilaiResikoTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiResikoTotal.setText("0");
        NilaiResikoTotal.setFocusTraversalPolicyProvider(true);
        NilaiResikoTotal.setName("NilaiResikoTotal"); // NOI18N
        FormInput.add(NilaiResikoTotal);
        NilaiResikoTotal.setBounds(780, 2980, 60, 23);

        TingkatResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko.setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        TingkatResiko.setToolTipText("");
        TingkatResiko.setName("TingkatResiko"); // NOI18N
        FormInput.add(TingkatResiko);
        TingkatResiko.setBounds(50, 2980, 640, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 3020, 880, 1);

        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel271.setText("VIII. SKRINING GIZI");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(10, 3020, 380, 23);

        jLabel272.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel272.setText("1. Apakah pasien tampak kurus ?");
        jLabel272.setName("jLabel272"); // NOI18N
        FormInput.add(jLabel272);
        jLabel272.setBounds(40, 3040, 380, 23);

        SkalaGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada penurunan berat badan", "Tidak yakin/ tidak tahu/ terasa baju lebih longgar", "Ya 1-5 kg", "Ya 6-10 kg", "Ya 11-15 kg", "Ya > 15 kg" }));
        SkalaGizi1.setName("SkalaGizi1"); // NOI18N
        SkalaGizi1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi1ItemStateChanged(evt);
            }
        });
        SkalaGizi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi1);
        SkalaGizi1.setBounds(420, 3040, 320, 23);

        jLabel273.setText("Skor :");
        jLabel273.setName("jLabel273"); // NOI18N
        FormInput.add(jLabel273);
        jLabel273.setBounds(740, 3040, 40, 23);

        NilaiGizi1.setEditable(false);
        NilaiGizi1.setFocusTraversalPolicyProvider(true);
        NilaiGizi1.setName("NilaiGizi1"); // NOI18N
        FormInput.add(NilaiGizi1);
        NilaiGizi1.setBounds(790, 3040, 60, 23);

        jLabel274.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel274.setText("2. Apakah terdapat penurunan berat badan selama satu bulan terakhir");
        jLabel274.setName("jLabel274"); // NOI18N
        FormInput.add(jLabel274);
        jLabel274.setBounds(40, 3070, 380, 23);

        SkalaGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaGizi2.setName("SkalaGizi2"); // NOI18N
        SkalaGizi2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi2ItemStateChanged(evt);
            }
        });
        SkalaGizi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi2);
        SkalaGizi2.setBounds(420, 3070, 320, 23);

        jLabel275.setText("Skor :");
        jLabel275.setName("jLabel275"); // NOI18N
        FormInput.add(jLabel275);
        jLabel275.setBounds(740, 3070, 40, 23);

        NilaiGizi2.setEditable(false);
        NilaiGizi2.setFocusTraversalPolicyProvider(true);
        NilaiGizi2.setName("NilaiGizi2"); // NOI18N
        FormInput.add(NilaiGizi2);
        NilaiGizi2.setBounds(790, 3070, 60, 23);

        jLabel276.setText("Total Skor :");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(670, 3250, 110, 23);

        NilaiGiziTotal.setEditable(false);
        NilaiGiziTotal.setFocusTraversalPolicyProvider(true);
        NilaiGiziTotal.setName("NilaiGiziTotal"); // NOI18N
        FormInput.add(NilaiGiziTotal);
        NilaiGiziTotal.setBounds(790, 3250, 60, 23);

        jLabel277.setText("Pasien dengan diagnosis khusus : ");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(0, 3290, 206, 23);

        DiagnosaKhususGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DiagnosaKhususGizi.setName("DiagnosaKhususGizi"); // NOI18N
        DiagnosaKhususGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKhususGiziKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaKhususGizi);
        DiagnosaKhususGizi.setBounds(210, 3290, 80, 23);

        KeteranganDiagnosaKhususGizi.setFocusTraversalPolicyProvider(true);
        KeteranganDiagnosaKhususGizi.setName("KeteranganDiagnosaKhususGizi"); // NOI18N
        KeteranganDiagnosaKhususGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDiagnosaKhususGiziKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDiagnosaKhususGizi);
        KeteranganDiagnosaKhususGizi.setBounds(290, 3290, 150, 23);

        jLabel278.setText("Sudah dibaca dan diketahui oleh Dietisen :");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(440, 3290, 220, 23);

        DiketahuiDietisen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DiketahuiDietisen.setName("DiketahuiDietisen"); // NOI18N
        DiketahuiDietisen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiketahuiDietisenKeyPressed(evt);
            }
        });
        FormInput.add(DiketahuiDietisen);
        DiketahuiDietisen.setBounds(660, 3290, 80, 23);

        jLabel279.setText("Jam  :");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(740, 3290, 40, 23);

        KeteranganDiketahuiDietisen.setFocusTraversalPolicyProvider(true);
        KeteranganDiketahuiDietisen.setName("KeteranganDiketahuiDietisen"); // NOI18N
        KeteranganDiketahuiDietisen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDiketahuiDietisenKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDiketahuiDietisen);
        KeteranganDiketahuiDietisen.setBounds(790, 3290, 60, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 3320, 880, 1);

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbMasalahKeperawatan.setName("tbMasalahKeperawatan"); // NOI18N
        tbMasalahKeperawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatanMouseClicked(evt);
            }
        });
        tbMasalahKeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyReleased(evt);
            }
        });
        Scroll6.setViewportView(tbMasalahKeperawatan);

        FormInput.add(Scroll6);
        Scroll6.setBounds(0, 3630, 400, 143);

        TabRencanaKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        TabRencanaKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabRencanaKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabRencanaKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRencanaKeperawatan.setName("TabRencanaKeperawatan"); // NOI18N

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setLayout(new java.awt.BorderLayout());

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbRencanaKeperawatan.setComponentPopupMenu(Popup);
        tbRencanaKeperawatan.setName("tbRencanaKeperawatan"); // NOI18N
        Scroll8.setViewportView(tbRencanaKeperawatan);

        panelBiasa1.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan", panelBiasa1);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Rencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Rencana.setColumns(20);
        Rencana.setRows(5);
        Rencana.setName("Rencana"); // NOI18N
        Rencana.setOpaque(true);
        Rencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Rencana);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Lainnya", scrollPane5);

        FormInput.add(TabRencanaKeperawatan);
        TabRencanaKeperawatan.setBounds(430, 3630, 420, 143);

        BtnTambahMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah.setMnemonic('3');
        BtnTambahMasalah.setToolTipText("Alt+3");
        BtnTambahMasalah.setName("BtnTambahMasalah"); // NOI18N
        BtnTambahMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(360, 3780, 28, 23);

        BtnAllMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllMasalah.setMnemonic('2');
        BtnAllMasalah.setToolTipText("2Alt+2");
        BtnAllMasalah.setName("BtnAllMasalah"); // NOI18N
        BtnAllMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllMasalahActionPerformed(evt);
            }
        });
        BtnAllMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllMasalah);
        BtnAllMasalah.setBounds(330, 3780, 28, 23);

        BtnCariMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariMasalah.setMnemonic('1');
        BtnCariMasalah.setToolTipText("Alt+1");
        BtnCariMasalah.setName("BtnCariMasalah"); // NOI18N
        BtnCariMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariMasalahActionPerformed(evt);
            }
        });
        BtnCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariMasalah);
        BtnCariMasalah.setBounds(290, 3780, 28, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(70, 3780, 215, 23);

        BtnTambahRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahRencana.setMnemonic('3');
        BtnTambahRencana.setToolTipText("Alt+3");
        BtnTambahRencana.setName("BtnTambahRencana"); // NOI18N
        BtnTambahRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahRencana);
        BtnTambahRencana.setBounds(800, 3780, 28, 23);

        BtnAllRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllRencana.setMnemonic('2');
        BtnAllRencana.setToolTipText("2Alt+2");
        BtnAllRencana.setName("BtnAllRencana"); // NOI18N
        BtnAllRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllRencanaActionPerformed(evt);
            }
        });
        BtnAllRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllRencana);
        BtnAllRencana.setBounds(770, 3780, 28, 23);

        BtnCariRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariRencana.setMnemonic('1');
        BtnCariRencana.setToolTipText("Alt+1");
        BtnCariRencana.setName("BtnCariRencana"); // NOI18N
        BtnCariRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariRencanaActionPerformed(evt);
            }
        });
        BtnCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariRencana);
        BtnCariRencana.setBounds(740, 3780, 28, 23);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(430, 3780, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(500, 3780, 235, 23);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(10, 3780, 60, 23);

        jLabel280.setText(":");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(160, 2040, 10, 23);

        jLabel281.setText(":");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(140, 2010, 10, 23);

        jLabel282.setText(":");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(90, 1980, 10, 23);

        jLabel283.setText(":");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(210, 1950, 10, 23);

        jLabel284.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel284.setText("a. Kondisi Psikologis");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(40, 2090, 110, 23);

        jLabel285.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel285.setText("c. Gangguan Jiwa di Masa Lalu");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(40, 2120, 160, 23);

        jLabel286.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel286.setText("f. Tinggal Dengan");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(40, 2150, 100, 23);

        jLabel287.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel287.setText("i. Nilai-nilai Kepercayaan/Budaya Yang Perlu Diperhatikan");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(40, 2180, 290, 23);

        jLabel288.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel288.setText("k. Pendidikan Pasien");
        jLabel288.setName("jLabel288"); // NOI18N
        FormInput.add(jLabel288);
        jLabel288.setBounds(40, 2210, 110, 23);

        jLabel59.setText("Keluhan Utama :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 160, 175, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(180, 160, 280, 43);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Kriteria Discharge Planning :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(30, 3350, 590, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("1. Umur > 65 Tahun");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(30, 3370, 260, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("2. Keterbatasan Mobilitas");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(30, 3400, 260, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("3. Perawatan Atau Pengobatan Lanjutan");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(30, 3430, 260, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("4. Bantuan Untuk Melakukan Aktifitas Sehari-Hari");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(30, 3460, 260, 23);

        Kriteria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria1.setName("Kriteria1"); // NOI18N
        FormInput.add(Kriteria1);
        Kriteria1.setBounds(320, 3370, 80, 23);

        Kriteria2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria2.setName("Kriteria2"); // NOI18N
        FormInput.add(Kriteria2);
        Kriteria2.setBounds(320, 3400, 80, 23);

        Kriteria3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria3.setName("Kriteria3"); // NOI18N
        FormInput.add(Kriteria3);
        Kriteria3.setBounds(320, 3430, 80, 23);

        Kriteria4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria4.setName("Kriteria4"); // NOI18N
        FormInput.add(Kriteria4);
        Kriteria4.setBounds(320, 3460, 80, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Apabila salah satu jawaban YA dari kriteria diatas, maka akan dilanjutkan dengan perencanaan sebagai berikut :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(30, 3510, 570, 23);

        pilihan1.setBackground(new java.awt.Color(255, 255, 255));
        pilihan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan1.setForeground(new java.awt.Color(50, 50, 50));
        pilihan1.setText("Perawatan diri (Mandi, BAB, BAK)");
        pilihan1.setName("pilihan1"); // NOI18N
        FormInput.add(pilihan1);
        pilihan1.setBounds(40, 3540, 200, 19);

        pilihan2.setBackground(new java.awt.Color(255, 255, 255));
        pilihan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan2.setForeground(new java.awt.Color(50, 50, 50));
        pilihan2.setText("Pemantauan pemberian obat");
        pilihan2.setName("pilihan2"); // NOI18N
        FormInput.add(pilihan2);
        pilihan2.setBounds(40, 3560, 180, 19);

        pilihan3.setBackground(new java.awt.Color(255, 255, 255));
        pilihan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan3.setForeground(new java.awt.Color(50, 50, 50));
        pilihan3.setText("Pemantauan diet");
        pilihan3.setName("pilihan3"); // NOI18N
        FormInput.add(pilihan3);
        pilihan3.setBounds(40, 3580, 120, 19);

        pilihan4.setBackground(new java.awt.Color(255, 255, 255));
        pilihan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan4.setForeground(new java.awt.Color(50, 50, 50));
        pilihan4.setText("Bantuan medis / perawatan di rumah (Homecare)");
        pilihan4.setName("pilihan4"); // NOI18N
        FormInput.add(pilihan4);
        pilihan4.setBounds(40, 3600, 280, 19);

        pilihan5.setBackground(new java.awt.Color(255, 255, 255));
        pilihan5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan5.setForeground(new java.awt.Color(50, 50, 50));
        pilihan5.setText("Perawatan luka");
        pilihan5.setName("pilihan5"); // NOI18N
        FormInput.add(pilihan5);
        pilihan5.setBounds(320, 3540, 120, 19);

        pilihan6.setBackground(new java.awt.Color(255, 255, 255));
        pilihan6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan6.setForeground(new java.awt.Color(50, 50, 50));
        pilihan6.setText("Latihan fisik lanjutan");
        pilihan6.setName("pilihan6"); // NOI18N
        FormInput.add(pilihan6);
        pilihan6.setBounds(320, 3560, 130, 19);

        pilihan7.setBackground(new java.awt.Color(255, 255, 255));
        pilihan7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan7.setForeground(new java.awt.Color(50, 50, 50));
        pilihan7.setText("Pendampingan tenaga khusus di rumah");
        pilihan7.setName("pilihan7"); // NOI18N
        FormInput.add(pilihan7);
        pilihan7.setBounds(320, 3580, 220, 19);

        pilihan8.setBackground(new java.awt.Color(255, 255, 255));
        pilihan8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan8.setForeground(new java.awt.Color(50, 50, 50));
        pilihan8.setText("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)");
        pilihan8.setName("pilihan8"); // NOI18N
        FormInput.add(pilihan8);
        pilihan8.setBounds(320, 3600, 370, 19);

        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel289.setText("IX. SKRINING PERENCANAAN PEMULANGAN");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(10, 3330, 380, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 3620, 880, 1);

        jLabel132.setText(", Berupa :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(230, 550, 60, 23);

        BreathingJalanNafasBerupa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sputum/benda", "Benda asing", "Darah", "Lidah" }));
        BreathingJalanNafasBerupa.setName("BreathingJalanNafasBerupa"); // NOI18N
        BreathingJalanNafasBerupa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingJalanNafasBerupaKeyPressed(evt);
            }
        });
        FormInput.add(BreathingJalanNafasBerupa);
        BreathingJalanNafasBerupa.setBounds(290, 550, 120, 23);

        jLabel133.setText("Pernapasan : Sesak");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(409, 550, 150, 23);

        BreathingPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        BreathingPernapasan.setName("BreathingPernapasan"); // NOI18N
        BreathingPernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingPernapasanKeyPressed(evt);
            }
        });
        FormInput.add(BreathingPernapasan);
        BreathingPernapasan.setBounds(570, 550, 103, 23);

        jLabel134.setText(", Dengan :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(670, 550, 60, 23);

        BreathingPernapasanDengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aktivitas", "Tanpa aktivitas" }));
        BreathingPernapasanDengan.setName("BreathingPernapasanDengan"); // NOI18N
        BreathingPernapasanDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingPernapasanDenganKeyPressed(evt);
            }
        });
        FormInput.add(BreathingPernapasanDengan);
        BreathingPernapasanDengan.setBounds(740, 550, 120, 23);

        jLabel135.setText("ETT/Tracheocanule :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(0, 580, 109, 23);

        jLabel136.setText("Cuff :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(220, 580, 60, 23);

        jLabel137.setText("Frekeunsi :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(420, 580, 70, 23);

        jLabel138.setText("Irama :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(670, 580, 60, 23);

        BreathingIrama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak teratur" }));
        BreathingIrama.setName("BreathingIrama"); // NOI18N
        BreathingIrama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingIramaKeyPressed(evt);
            }
        });
        FormInput.add(BreathingIrama);
        BreathingIrama.setBounds(740, 580, 120, 23);

        BreathingETT.setFocusTraversalPolicyProvider(true);
        BreathingETT.setName("BreathingETT"); // NOI18N
        BreathingETT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingETTKeyPressed(evt);
            }
        });
        FormInput.add(BreathingETT);
        BreathingETT.setBounds(120, 580, 130, 23);

        BreathingCuff.setFocusTraversalPolicyProvider(true);
        BreathingCuff.setName("BreathingCuff"); // NOI18N
        BreathingCuff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingCuffKeyPressed(evt);
            }
        });
        FormInput.add(BreathingCuff);
        BreathingCuff.setBounds(290, 580, 130, 23);

        BreathingFrekuensi.setFocusTraversalPolicyProvider(true);
        BreathingFrekuensi.setName("BreathingFrekuensi"); // NOI18N
        BreathingFrekuensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingFrekuensiKeyPressed(evt);
            }
        });
        FormInput.add(BreathingFrekuensi);
        BreathingFrekuensi.setBounds(500, 580, 130, 23);

        jLabel139.setText("Menit");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(630, 580, 40, 23);

        jLabel140.setText("Kedalaman :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(0, 610, 109, 23);

        BreathingKedalaman.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Dangkal" }));
        BreathingKedalaman.setName("BreathingKedalaman"); // NOI18N
        BreathingKedalaman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingKedalamanKeyPressed(evt);
            }
        });
        FormInput.add(BreathingKedalaman);
        BreathingKedalaman.setBounds(120, 610, 103, 23);

        jLabel141.setText("Sputum :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(220, 610, 60, 23);

        BreathingSpulum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Putih", "Kuning/abu" }));
        BreathingSpulum.setName("BreathingSpulum"); // NOI18N
        BreathingSpulum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingSpulumKeyPressed(evt);
            }
        });
        FormInput.add(BreathingSpulum);
        BreathingSpulum.setBounds(290, 610, 120, 23);

        jLabel142.setText("Konsistensi :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(410, 610, 90, 23);

        BreathingKonsistensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kental", "Encer" }));
        BreathingKonsistensi.setName("BreathingKonsistensi"); // NOI18N
        BreathingKonsistensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingKonsistensiKeyPressed(evt);
            }
        });
        FormInput.add(BreathingKonsistensi);
        BreathingKonsistensi.setBounds(510, 610, 103, 23);

        jLabel143.setText("Nafas Bunyi :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(610, 610, 120, 23);

        BreathingNafasBunyi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BreathingNafasBunyi.setName("BreathingNafasBunyi"); // NOI18N
        BreathingNafasBunyi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingNafasBunyiKeyPressed(evt);
            }
        });
        FormInput.add(BreathingNafasBunyi);
        BreathingNafasBunyi.setBounds(740, 610, 120, 23);

        jLabel144.setText("Terdapat Darah :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(0, 640, 109, 23);

        BreathingTerdapatDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BreathingTerdapatDarah.setName("BreathingTerdapatDarah"); // NOI18N
        BreathingTerdapatDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingTerdapatDarahKeyPressed(evt);
            }
        });
        FormInput.add(BreathingTerdapatDarah);
        BreathingTerdapatDarah.setBounds(120, 640, 103, 23);

        jLabel146.setText("Suara Nafas :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(410, 640, 90, 23);

        BreathingSuaraNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ronchi", "Wheesing", "Vesikuler" }));
        BreathingSuaraNafas.setName("BreathingSuaraNafas"); // NOI18N
        BreathingSuaraNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingSuaraNafasKeyPressed(evt);
            }
        });
        FormInput.add(BreathingSuaraNafas);
        BreathingSuaraNafas.setBounds(510, 640, 140, 23);

        jLabel148.setText("Jumlah :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(220, 640, 60, 23);

        BreathingTerdapatDarahJumlah.setFocusTraversalPolicyProvider(true);
        BreathingTerdapatDarahJumlah.setName("BreathingTerdapatDarahJumlah"); // NOI18N
        BreathingTerdapatDarahJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingTerdapatDarahJumlahKeyPressed(evt);
            }
        });
        FormInput.add(BreathingTerdapatDarahJumlah);
        BreathingTerdapatDarahJumlah.setBounds(290, 640, 130, 23);

        jLabel145.setText("Analisa Gas Darah :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(0, 670, 109, 23);

        BreathingAnalisaGasDarahPH.setFocusTraversalPolicyProvider(true);
        BreathingAnalisaGasDarahPH.setName("BreathingAnalisaGasDarahPH"); // NOI18N
        BreathingAnalisaGasDarahPH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingAnalisaGasDarahPHKeyPressed(evt);
            }
        });
        FormInput.add(BreathingAnalisaGasDarahPH);
        BreathingAnalisaGasDarahPH.setBounds(180, 670, 100, 23);

        jLabel149.setText("mmHg");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(440, 670, 40, 23);

        BreathingAnalisaGasDarahpCO2.setFocusTraversalPolicyProvider(true);
        BreathingAnalisaGasDarahpCO2.setName("BreathingAnalisaGasDarahpCO2"); // NOI18N
        BreathingAnalisaGasDarahpCO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingAnalisaGasDarahpCO2KeyPressed(evt);
            }
        });
        FormInput.add(BreathingAnalisaGasDarahpCO2);
        BreathingAnalisaGasDarahpCO2.setBounds(340, 670, 100, 23);

        jLabel150.setText("pO2 :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(480, 670, 40, 23);

        BreathingAnalisaGasDarahpO2.setFocusTraversalPolicyProvider(true);
        BreathingAnalisaGasDarahpO2.setName("BreathingAnalisaGasDarahpO2"); // NOI18N
        BreathingAnalisaGasDarahpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingAnalisaGasDarahpO2KeyPressed(evt);
            }
        });
        FormInput.add(BreathingAnalisaGasDarahpO2);
        BreathingAnalisaGasDarahpO2.setBounds(530, 670, 100, 23);

        jLabel151.setText("mmHg");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(630, 670, 40, 23);

        jLabel152.setText("pCO2 :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(290, 670, 40, 23);

        jLabel153.setText("PH :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(140, 670, 30, 23);

        jLabel154.setText("Sat O2 :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(680, 670, 50, 23);

        BreathingAnalisaGasDarahSatO2.setFocusTraversalPolicyProvider(true);
        BreathingAnalisaGasDarahSatO2.setName("BreathingAnalisaGasDarahSatO2"); // NOI18N
        BreathingAnalisaGasDarahSatO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BreathingAnalisaGasDarahSatO2KeyPressed(evt);
            }
        });
        FormInput.add(BreathingAnalisaGasDarahSatO2);
        BreathingAnalisaGasDarahSatO2.setBounds(740, 670, 100, 23);

        jLabel155.setText("%");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(840, 670, 20, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("B. BLOOD :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(50, 710, 129, 23);

        jLabel147.setText("Nadi :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 770, 109, 23);

        jLabel156.setText("x/menit");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(220, 770, 50, 23);

        jLabel157.setText("Irama :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(270, 770, 50, 23);

        BloodSirkulasiPeriferIrama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak teratur" }));
        BloodSirkulasiPeriferIrama.setName("BloodSirkulasiPeriferIrama"); // NOI18N
        BloodSirkulasiPeriferIrama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferIramaKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferIrama);
        BloodSirkulasiPeriferIrama.setBounds(330, 770, 103, 23);

        BloodSirkulasiPeriferNadi.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferNadi.setName("BloodSirkulasiPeriferNadi"); // NOI18N
        BloodSirkulasiPeriferNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferNadiKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferNadi);
        BloodSirkulasiPeriferNadi.setBounds(120, 770, 100, 23);

        jLabel158.setText("Tekanan Darah :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(0, 800, 109, 23);

        BloodSirkulasiPeriferTekananDarah.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferTekananDarah.setName("BloodSirkulasiPeriferTekananDarah"); // NOI18N
        FormInput.add(BloodSirkulasiPeriferTekananDarah);
        BloodSirkulasiPeriferTekananDarah.setBounds(120, 800, 90, 23);

        jLabel160.setText("MAP :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(270, 800, 40, 23);

        BloodSirkulasiPeriferMAP.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferMAP.setName("BloodSirkulasiPeriferMAP"); // NOI18N
        BloodSirkulasiPeriferMAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferMAPKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferMAP);
        BloodSirkulasiPeriferMAP.setBounds(320, 800, 100, 23);

        jLabel161.setText("mmHg");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(420, 800, 40, 23);

        jLabel162.setText("CVP :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(470, 800, 40, 23);

        BloodSirkulasiPeriferCVP.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferCVP.setName("BloodSirkulasiPeriferCVP"); // NOI18N
        BloodSirkulasiPeriferCVP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferCVPKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferCVP);
        BloodSirkulasiPeriferCVP.setBounds(520, 800, 100, 23);

        jLabel163.setText("mmHg");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(620, 800, 40, 23);

        jLabel164.setText("IBP :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(670, 800, 40, 23);

        BloodSirkulasiPeriferIBP.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferIBP.setName("BloodSirkulasiPeriferIBP"); // NOI18N
        BloodSirkulasiPeriferIBP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferIBPKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferIBP);
        BloodSirkulasiPeriferIBP.setBounds(720, 800, 100, 23);

        jLabel165.setText("mmHg");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(820, 800, 40, 23);

        jLabel166.setText("mmHg");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(210, 800, 40, 23);

        jLabel167.setText("Akral :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(0, 830, 109, 23);

        BloodSirkulasiPeriferAkral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hangat", "Dingin" }));
        BloodSirkulasiPeriferAkral.setName("BloodSirkulasiPeriferAkral"); // NOI18N
        BloodSirkulasiPeriferAkral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferAkralKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferAkral);
        BloodSirkulasiPeriferAkral.setBounds(120, 830, 103, 23);

        jLabel168.setText("Distensi Vena Jugulari :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(220, 830, 130, 23);

        BloodSirkulasiPeriferDistensiVenaJugulari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        BloodSirkulasiPeriferDistensiVenaJugulari.setName("BloodSirkulasiPeriferDistensiVenaJugulari"); // NOI18N
        BloodSirkulasiPeriferDistensiVenaJugulari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferDistensiVenaJugulariKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferDistensiVenaJugulari);
        BloodSirkulasiPeriferDistensiVenaJugulari.setBounds(350, 830, 120, 23);

        jLabel291.setText("Warna Kulit :");
        jLabel291.setName("jLabel291"); // NOI18N
        FormInput.add(jLabel291);
        jLabel291.setBounds(640, 830, 90, 23);

        BloodSirkulasiPeriferWarnaKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pucat", "Kemerahan", "Sianosis" }));
        BloodSirkulasiPeriferWarnaKulit.setName("BloodSirkulasiPeriferWarnaKulit"); // NOI18N
        BloodSirkulasiPeriferWarnaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferWarnaKulitKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferWarnaKulit);
        BloodSirkulasiPeriferWarnaKulit.setBounds(740, 830, 120, 23);

        jLabel292.setText("Suhu :");
        jLabel292.setName("jLabel292"); // NOI18N
        FormInput.add(jLabel292);
        jLabel292.setBounds(470, 830, 40, 23);

        BloodSirkulasiPeriferSuhu.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferSuhu.setName("BloodSirkulasiPeriferSuhu"); // NOI18N
        BloodSirkulasiPeriferSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferSuhuKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferSuhu);
        BloodSirkulasiPeriferSuhu.setBounds(520, 830, 100, 23);

        jLabel293.setText("C");
        jLabel293.setName("jLabel293"); // NOI18N
        FormInput.add(jLabel293);
        jLabel293.setBounds(620, 830, 10, 23);

        jLabel290.setText("Pengisian Kaplier :");
        jLabel290.setName("jLabel290"); // NOI18N
        FormInput.add(jLabel290);
        jLabel290.setBounds(0, 860, 109, 23);

        BloodSirkulasiPeriferPengisianKaplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< 3 detik", "> 3 detik" }));
        BloodSirkulasiPeriferPengisianKaplier.setName("BloodSirkulasiPeriferPengisianKaplier"); // NOI18N
        BloodSirkulasiPeriferPengisianKaplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferPengisianKaplierKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferPengisianKaplier);
        BloodSirkulasiPeriferPengisianKaplier.setBounds(120, 860, 103, 23);

        jLabel294.setText("Edema :");
        jLabel294.setName("jLabel294"); // NOI18N
        FormInput.add(jLabel294);
        jLabel294.setBounds(230, 860, 60, 23);

        BloodSirkulasiPeriferEdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        BloodSirkulasiPeriferEdema.setName("BloodSirkulasiPeriferEdema"); // NOI18N
        BloodSirkulasiPeriferEdema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferEdemaKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferEdema);
        BloodSirkulasiPeriferEdema.setBounds(300, 860, 120, 23);

        jLabel295.setText(", Pada :");
        jLabel295.setName("jLabel295"); // NOI18N
        FormInput.add(jLabel295);
        jLabel295.setBounds(419, 860, 60, 23);

        BloodSirkulasiPeriferEdemaPada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Muka", "Tungkai atas", "Tungkai bawah", "Seluruh tubuh" }));
        BloodSirkulasiPeriferEdemaPada.setName("BloodSirkulasiPeriferEdemaPada"); // NOI18N
        BloodSirkulasiPeriferEdemaPada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferEdemaPadaKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferEdemaPada);
        BloodSirkulasiPeriferEdemaPada.setBounds(490, 860, 103, 23);

        jLabel296.setText("EKG :");
        jLabel296.setName("jLabel296"); // NOI18N
        FormInput.add(jLabel296);
        jLabel296.setBounds(440, 770, 40, 23);

        BloodSirkulasiPeriferEKG.setFocusTraversalPolicyProvider(true);
        BloodSirkulasiPeriferEKG.setName("BloodSirkulasiPeriferEKG"); // NOI18N
        BloodSirkulasiPeriferEKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiPeriferEKGKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiPeriferEKG);
        BloodSirkulasiPeriferEKG.setBounds(490, 770, 370, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("- Sirkulasi Jantung");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(50, 890, 129, 23);

        jLabel297.setText("Jantung Irama :");
        jLabel297.setName("jLabel297"); // NOI18N
        FormInput.add(jLabel297);
        jLabel297.setBounds(0, 920, 109, 23);

        BloodSirkulasiJantungJantungIrama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak teratur" }));
        BloodSirkulasiJantungJantungIrama.setName("BloodSirkulasiJantungJantungIrama"); // NOI18N
        BloodSirkulasiJantungJantungIrama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungJantungIramaKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungJantungIrama);
        BloodSirkulasiJantungJantungIrama.setBounds(120, 920, 103, 23);

        jLabel298.setText("Bunyi :");
        jLabel298.setName("jLabel298"); // NOI18N
        FormInput.add(jLabel298);
        jLabel298.setBounds(230, 920, 50, 23);

        BloodSirkulasiJantungBunyi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BJ I", "BJ II", "Murmur", "Gallop" }));
        BloodSirkulasiJantungBunyi.setName("BloodSirkulasiJantungBunyi"); // NOI18N
        BloodSirkulasiJantungBunyi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungBunyiKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungBunyi);
        BloodSirkulasiJantungBunyi.setBounds(280, 920, 120, 23);

        jLabel299.setText("Keluhan :");
        jLabel299.setName("jLabel299"); // NOI18N
        FormInput.add(jLabel299);
        jLabel299.setBounds(400, 920, 60, 23);

        BloodSirkulasiJantungKeluhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lelah", "Berdebar-debar", "Kesemutan", "Keringat dingin", "Gemetaran", "Tidak ada" }));
        BloodSirkulasiJantungKeluhan.setName("BloodSirkulasiJantungKeluhan"); // NOI18N
        BloodSirkulasiJantungKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungKeluhanKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungKeluhan);
        BloodSirkulasiJantungKeluhan.setBounds(460, 920, 120, 23);

        jLabel300.setText("Karakteristik :");
        jLabel300.setName("jLabel300"); // NOI18N
        FormInput.add(jLabel300);
        jLabel300.setBounds(580, 920, 80, 23);

        BloodSirkulasiJantungKarakteristik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seperti ditusuk-tusuk", "Seperti terbakar", "Menyebar", "Seperti tertimpa beban berat", "Tidak ada atau min" }));
        BloodSirkulasiJantungKarakteristik.setName("BloodSirkulasiJantungKarakteristik"); // NOI18N
        BloodSirkulasiJantungKarakteristik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungKarakteristikKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungKarakteristik);
        BloodSirkulasiJantungKarakteristik.setBounds(660, 920, 200, 23);

        jLabel301.setText("Sakit Dada :");
        jLabel301.setName("jLabel301"); // NOI18N
        FormInput.add(jLabel301);
        jLabel301.setBounds(0, 950, 109, 23);

        BloodSirkulasiJantungSakitDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BloodSirkulasiJantungSakitDada.setName("BloodSirkulasiJantungSakitDada"); // NOI18N
        BloodSirkulasiJantungSakitDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungSakitDadaKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungSakitDada);
        BloodSirkulasiJantungSakitDada.setBounds(120, 950, 103, 23);

        jLabel302.setText(", Timbul :");
        jLabel302.setName("jLabel302"); // NOI18N
        FormInput.add(jLabel302);
        jLabel302.setBounds(230, 950, 60, 23);

        BloodSirkulasiJantungSakitDadaTimbul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Saat aktifitas", "Tanpa aktifitas" }));
        BloodSirkulasiJantungSakitDadaTimbul.setName("BloodSirkulasiJantungSakitDadaTimbul"); // NOI18N
        BloodSirkulasiJantungSakitDadaTimbul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodSirkulasiJantungSakitDadaTimbulKeyPressed(evt);
            }
        });
        FormInput.add(BloodSirkulasiJantungSakitDadaTimbul);
        BloodSirkulasiJantungSakitDadaTimbul.setBounds(300, 950, 120, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("- Hematologi");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(50, 980, 129, 23);

        jLabel303.setText("HB :");
        jLabel303.setName("jLabel303"); // NOI18N
        FormInput.add(jLabel303);
        jLabel303.setBounds(0, 1010, 109, 23);

        jLabel304.setText("gr/dl");
        jLabel304.setName("jLabel304"); // NOI18N
        FormInput.add(jLabel304);
        jLabel304.setBounds(190, 1010, 30, 23);

        BloodHematologiHB.setFocusTraversalPolicyProvider(true);
        BloodHematologiHB.setName("BloodHematologiHB"); // NOI18N
        BloodHematologiHB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiHBKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiHB);
        BloodHematologiHB.setBounds(120, 1010, 64, 23);

        jLabel305.setText("Ht :");
        jLabel305.setName("jLabel305"); // NOI18N
        FormInput.add(jLabel305);
        jLabel305.setBounds(230, 1010, 40, 23);

        BloodHematologiHt.setFocusTraversalPolicyProvider(true);
        BloodHematologiHt.setName("BloodHematologiHt"); // NOI18N
        BloodHematologiHt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiHtKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiHt);
        BloodHematologiHt.setBounds(280, 1010, 64, 23);

        jLabel307.setText("Eritrosit :");
        jLabel307.setName("jLabel307"); // NOI18N
        FormInput.add(jLabel307);
        jLabel307.setBounds(350, 1010, 60, 23);

        BloodHematologiEritrosit.setFocusTraversalPolicyProvider(true);
        BloodHematologiEritrosit.setName("BloodHematologiEritrosit"); // NOI18N
        BloodHematologiEritrosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiEritrositKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiEritrosit);
        BloodHematologiEritrosit.setBounds(420, 1010, 64, 23);

        jLabel308.setText("jt/ul");
        jLabel308.setName("jLabel308"); // NOI18N
        FormInput.add(jLabel308);
        jLabel308.setBounds(490, 1010, 30, 23);

        jLabel309.setText("Leukosit :");
        jLabel309.setName("jLabel309"); // NOI18N
        FormInput.add(jLabel309);
        jLabel309.setBounds(520, 1010, 60, 23);

        BloodHematologiLeukosit.setFocusTraversalPolicyProvider(true);
        BloodHematologiLeukosit.setName("BloodHematologiLeukosit"); // NOI18N
        BloodHematologiLeukosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiLeukositKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiLeukosit);
        BloodHematologiLeukosit.setBounds(590, 1010, 64, 23);

        jLabel310.setText("rb/ul");
        jLabel310.setName("jLabel310"); // NOI18N
        FormInput.add(jLabel310);
        jLabel310.setBounds(660, 1010, 30, 23);

        jLabel311.setText("Trombosit :");
        jLabel311.setName("jLabel311"); // NOI18N
        FormInput.add(jLabel311);
        jLabel311.setBounds(690, 1010, 60, 23);

        BloodHematologiTrombosit.setFocusTraversalPolicyProvider(true);
        BloodHematologiTrombosit.setName("BloodHematologiTrombosit"); // NOI18N
        BloodHematologiTrombosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiTrombositKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiTrombosit);
        BloodHematologiTrombosit.setBounds(760, 1010, 64, 23);

        jLabel312.setText("rb/ul");
        jLabel312.setName("jLabel312"); // NOI18N
        FormInput.add(jLabel312);
        jLabel312.setBounds(830, 1010, 30, 23);

        jLabel306.setText("Pendarahan :");
        jLabel306.setName("jLabel306"); // NOI18N
        FormInput.add(jLabel306);
        jLabel306.setBounds(0, 1040, 109, 23);

        BloodHematologiPendarahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gusi mudah berdarah", "Mimisan", "Petechie", "Echimosis", "Terus menerus", "Lemah", "Pucat", "Tidak ada atau min" }));
        BloodHematologiPendarahan.setName("BloodHematologiPendarahan"); // NOI18N
        BloodHematologiPendarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiPendarahanKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiPendarahan);
        BloodHematologiPendarahan.setBounds(120, 1040, 150, 23);

        jLabel313.setText("CT/BT :");
        jLabel313.setName("jLabel313"); // NOI18N
        FormInput.add(jLabel313);
        jLabel313.setBounds(290, 1040, 50, 23);

        BloodHematologiCT.setFocusTraversalPolicyProvider(true);
        BloodHematologiCT.setName("BloodHematologiCT"); // NOI18N
        BloodHematologiCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiCTKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiCT);
        BloodHematologiCT.setBounds(350, 1040, 200, 23);

        jLabel314.setText("PTT/APTT :");
        jLabel314.setName("jLabel314"); // NOI18N
        FormInput.add(jLabel314);
        jLabel314.setBounds(590, 1040, 60, 23);

        BloodHematologiPTT.setFocusTraversalPolicyProvider(true);
        BloodHematologiPTT.setName("BloodHematologiPTT"); // NOI18N
        BloodHematologiPTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BloodHematologiPTTKeyPressed(evt);
            }
        });
        FormInput.add(BloodHematologiPTT);
        BloodHematologiPTT.setBounds(660, 1040, 200, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("- Sirkulasi Serebral");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(50, 1110, 129, 23);

        jLabel315.setText("Tingkat Kesadaran :");
        jLabel315.setName("jLabel315"); // NOI18N
        FormInput.add(jLabel315);
        jLabel315.setBounds(0, 1140, 109, 23);

        BrainSirkulasiSerebralTingkatKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Composmentis", "Apatis", "Somnolen", "Delirium", "Sopor", "Coma" }));
        BrainSirkulasiSerebralTingkatKesadaran.setName("BrainSirkulasiSerebralTingkatKesadaran"); // NOI18N
        BrainSirkulasiSerebralTingkatKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralTingkatKesadaranKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralTingkatKesadaran);
        BrainSirkulasiSerebralTingkatKesadaran.setBounds(120, 1140, 120, 23);

        jLabel316.setText("Pupil :");
        jLabel316.setName("jLabel316"); // NOI18N
        FormInput.add(jLabel316);
        jLabel316.setBounds(240, 1140, 50, 23);

        BrainSirkulasiSerebralPupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Isokor", "Unisokor", "Miosis", "Midriasis" }));
        BrainSirkulasiSerebralPupil.setName("BrainSirkulasiSerebralPupil"); // NOI18N
        BrainSirkulasiSerebralPupil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralPupilKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralPupil);
        BrainSirkulasiSerebralPupil.setBounds(290, 1140, 120, 23);

        jLabel317.setText("Reaksi Terhadap Cahaya :");
        jLabel317.setName("jLabel317"); // NOI18N
        FormInput.add(jLabel317);
        jLabel317.setBounds(410, 1140, 150, 23);

        BrainSirkulasiSerebralReaksiTerhadapCahaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Positif", "Negatif" }));
        BrainSirkulasiSerebralReaksiTerhadapCahaya.setName("BrainSirkulasiSerebralReaksiTerhadapCahaya"); // NOI18N
        BrainSirkulasiSerebralReaksiTerhadapCahaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralReaksiTerhadapCahayaKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralReaksiTerhadapCahaya);
        BrainSirkulasiSerebralReaksiTerhadapCahaya.setBounds(570, 1140, 120, 23);

        jLabel318.setText("GCS :");
        jLabel318.setName("jLabel318"); // NOI18N
        FormInput.add(jLabel318);
        jLabel318.setBounds(0, 1170, 109, 23);

        BrainSirkulasiSerebralGCSE.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralGCSE.setName("BrainSirkulasiSerebralGCSE"); // NOI18N
        FormInput.add(BrainSirkulasiSerebralGCSE);
        BrainSirkulasiSerebralGCSE.setBounds(140, 1170, 64, 23);

        jLabel320.setText("E :");
        jLabel320.setName("jLabel320"); // NOI18N
        FormInput.add(jLabel320);
        jLabel320.setBounds(110, 1170, 20, 23);

        BrainSirkulasiSerebralGCSV.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralGCSV.setName("BrainSirkulasiSerebralGCSV"); // NOI18N
        BrainSirkulasiSerebralGCSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralGCSVKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralGCSV);
        BrainSirkulasiSerebralGCSV.setBounds(240, 1170, 64, 23);

        jLabel321.setText("M :");
        jLabel321.setName("jLabel321"); // NOI18N
        FormInput.add(jLabel321);
        jLabel321.setBounds(310, 1170, 20, 23);

        BrainSirkulasiSerebralGCSM.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralGCSM.setName("BrainSirkulasiSerebralGCSM"); // NOI18N
        BrainSirkulasiSerebralGCSM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralGCSMKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralGCSM);
        BrainSirkulasiSerebralGCSM.setBounds(340, 1170, 64, 23);

        jLabel322.setText("V :");
        jLabel322.setName("jLabel322"); // NOI18N
        FormInput.add(jLabel322);
        jLabel322.setBounds(210, 1170, 20, 23);

        jLabel323.setText("Jumlah :");
        jLabel323.setName("jLabel323"); // NOI18N
        FormInput.add(jLabel323);
        jLabel323.setBounds(410, 1170, 60, 23);

        BrainSirkulasiSerebralGCSJumlah.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralGCSJumlah.setName("BrainSirkulasiSerebralGCSJumlah"); // NOI18N
        BrainSirkulasiSerebralGCSJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralGCSJumlahKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralGCSJumlah);
        BrainSirkulasiSerebralGCSJumlah.setBounds(480, 1170, 64, 23);

        jLabel319.setText("Terjadi :");
        jLabel319.setName("jLabel319"); // NOI18N
        FormInput.add(jLabel319);
        jLabel319.setBounds(550, 1170, 50, 23);

        BrainSirkulasiSerebralTerjadi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kejang", "Lumpuh", "Tidak ada atau min" }));
        BrainSirkulasiSerebralTerjadi.setName("BrainSirkulasiSerebralTerjadi"); // NOI18N
        BrainSirkulasiSerebralTerjadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralTerjadiKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralTerjadi);
        BrainSirkulasiSerebralTerjadi.setBounds(610, 1170, 90, 23);

        jLabel324.setText(", Bagian :");
        jLabel324.setName("jLabel324"); // NOI18N
        FormInput.add(jLabel324);
        jLabel324.setBounds(700, 1170, 50, 23);

        BrainSirkulasiSerebralTerjadiBagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kanan", "Kiri", "Pola", "Mulut menceng", "Aphasia" }));
        BrainSirkulasiSerebralTerjadiBagian.setName("BrainSirkulasiSerebralTerjadiBagian"); // NOI18N
        BrainSirkulasiSerebralTerjadiBagian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralTerjadiBagianKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralTerjadiBagian);
        BrainSirkulasiSerebralTerjadiBagian.setBounds(760, 1170, 110, 23);

        jLabel325.setText("ICP :");
        jLabel325.setName("jLabel325"); // NOI18N
        FormInput.add(jLabel325);
        jLabel325.setBounds(0, 1200, 109, 23);

        jLabel326.setText("H2O");
        jLabel326.setName("jLabel326"); // NOI18N
        FormInput.add(jLabel326);
        jLabel326.setBounds(190, 1200, 30, 23);

        BrainSirkulasiSerebralICP.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralICP.setName("BrainSirkulasiSerebralICP"); // NOI18N
        FormInput.add(BrainSirkulasiSerebralICP);
        BrainSirkulasiSerebralICP.setBounds(120, 1200, 64, 23);

        jLabel327.setText("CPP :");
        jLabel327.setName("jLabel327"); // NOI18N
        FormInput.add(jLabel327);
        jLabel327.setBounds(220, 1200, 40, 23);

        BrainSirkulasiSerebralCPP.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralCPP.setName("BrainSirkulasiSerebralCPP"); // NOI18N
        BrainSirkulasiSerebralCPP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralCPPKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralCPP);
        BrainSirkulasiSerebralCPP.setBounds(270, 1200, 64, 23);

        jLabel328.setText("mmHg");
        jLabel328.setName("jLabel328"); // NOI18N
        FormInput.add(jLabel328);
        jLabel328.setBounds(330, 1200, 40, 23);

        BrainSirkulasiSerebralSOD.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralSOD.setName("BrainSirkulasiSerebralSOD"); // NOI18N
        BrainSirkulasiSerebralSOD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralSODKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralSOD);
        BrainSirkulasiSerebralSOD.setBounds(420, 1200, 64, 23);

        jLabel329.setText("Palkososial :");
        jLabel329.setName("jLabel329"); // NOI18N
        FormInput.add(jLabel329);
        jLabel329.setBounds(630, 1200, 70, 23);

        BrainSirkulasiSerebralPalkososial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sedih/murung", "Menyendiri", "Kebersihan diri kurang", "Ekspresi wajah datar", "Ekspresi wajah tegang", "Mata merah", "Marah-marah", "Bicara sendiri", "Gelisah/mondar-mandir" }));
        BrainSirkulasiSerebralPalkososial.setName("BrainSirkulasiSerebralPalkososial"); // NOI18N
        BrainSirkulasiSerebralPalkososial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralPalkososialKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralPalkososial);
        BrainSirkulasiSerebralPalkososial.setBounds(700, 1200, 170, 23);

        jLabel330.setText("SOD :");
        jLabel330.setName("jLabel330"); // NOI18N
        FormInput.add(jLabel330);
        jLabel330.setBounds(370, 1200, 40, 23);

        jLabel331.setText("ml");
        jLabel331.setName("jLabel331"); // NOI18N
        FormInput.add(jLabel331);
        jLabel331.setBounds(480, 1200, 20, 23);

        jLabel332.setText("EUD :");
        jLabel332.setName("jLabel332"); // NOI18N
        FormInput.add(jLabel332);
        jLabel332.setBounds(500, 1200, 40, 23);

        BrainSirkulasiSerebralEUD.setFocusTraversalPolicyProvider(true);
        BrainSirkulasiSerebralEUD.setName("BrainSirkulasiSerebralEUD"); // NOI18N
        BrainSirkulasiSerebralEUD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BrainSirkulasiSerebralEUDKeyPressed(evt);
            }
        });
        FormInput.add(BrainSirkulasiSerebralEUD);
        BrainSirkulasiSerebralEUD.setBounds(550, 1200, 64, 23);

        jLabel333.setText("ml");
        jLabel333.setName("jLabel333"); // NOI18N
        FormInput.add(jLabel333);
        jLabel333.setBounds(610, 1200, 20, 23);

        jLabel334.setText("BAB :");
        jLabel334.setName("jLabel334"); // NOI18N
        FormInput.add(jLabel334);
        jLabel334.setBounds(0, 1380, 109, 23);

        jLabel335.setText("Pola Rutin :");
        jLabel335.setName("jLabel335"); // NOI18N
        FormInput.add(jLabel335);
        jLabel335.setBounds(110, 1380, 60, 23);

        BowelBAKPolaRutin.setFocusTraversalPolicyProvider(true);
        BowelBAKPolaRutin.setName("BowelBAKPolaRutin"); // NOI18N
        FormInput.add(BowelBAKPolaRutin);
        BowelBAKPolaRutin.setBounds(180, 1380, 64, 23);

        jLabel336.setText("x/hari");
        jLabel336.setName("jLabel336"); // NOI18N
        FormInput.add(jLabel336);
        jLabel336.setBounds(240, 1380, 40, 23);

        BowelBAKSaatIni.setFocusTraversalPolicyProvider(true);
        BowelBAKSaatIni.setName("BowelBAKSaatIni"); // NOI18N
        BowelBAKSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelBAKSaatIniKeyPressed(evt);
            }
        });
        FormInput.add(BowelBAKSaatIni);
        BowelBAKSaatIni.setBounds(340, 1380, 64, 23);

        jLabel337.setText("Saat Ini :");
        jLabel337.setName("jLabel337"); // NOI18N
        FormInput.add(jLabel337);
        jLabel337.setBounds(280, 1380, 50, 23);

        jLabel338.setText("x/hari");
        jLabel338.setName("jLabel338"); // NOI18N
        FormInput.add(jLabel338);
        jLabel338.setBounds(400, 1380, 40, 23);

        jLabel339.setText("Konsistensi :");
        jLabel339.setName("jLabel339"); // NOI18N
        FormInput.add(jLabel339);
        jLabel339.setBounds(440, 1380, 80, 23);

        BowelKonsistensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Padat", "Encer/cair", "Lunak" }));
        BowelKonsistensi.setName("BowelKonsistensi"); // NOI18N
        BowelKonsistensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelKonsistensiKeyPressed(evt);
            }
        });
        FormInput.add(BowelKonsistensi);
        BowelKonsistensi.setBounds(530, 1380, 120, 23);

        jLabel340.setText("Warna :");
        jLabel340.setName("jLabel340"); // NOI18N
        FormInput.add(jLabel340);
        jLabel340.setBounds(660, 1380, 80, 23);

        BowelWarna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuning", "Hitam", "Merah", "Dempul/pucat" }));
        BowelWarna.setName("BowelWarna"); // NOI18N
        BowelWarna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelWarnaKeyPressed(evt);
            }
        });
        FormInput.add(BowelWarna);
        BowelWarna.setBounds(750, 1380, 120, 23);

        jLabel341.setText("Lendir :");
        jLabel341.setName("jLabel341"); // NOI18N
        FormInput.add(jLabel341);
        jLabel341.setBounds(0, 1410, 109, 23);

        BowelLendir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelLendir.setName("BowelLendir"); // NOI18N
        BowelLendir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelLendirKeyPressed(evt);
            }
        });
        FormInput.add(BowelLendir);
        BowelLendir.setBounds(110, 1410, 70, 23);

        jLabel342.setText("Mual/Muntah :");
        jLabel342.setName("jLabel342"); // NOI18N
        FormInput.add(jLabel342);
        jLabel342.setBounds(180, 1410, 80, 23);

        BowelMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelMual.setName("BowelMual"); // NOI18N
        BowelMual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelMualKeyPressed(evt);
            }
        });
        FormInput.add(BowelMual);
        BowelMual.setBounds(260, 1410, 70, 23);

        BowelKembung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelKembung.setName("BowelKembung"); // NOI18N
        BowelKembung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelKembungKeyPressed(evt);
            }
        });
        FormInput.add(BowelKembung);
        BowelKembung.setBounds(390, 1410, 70, 23);

        jLabel343.setText("Kembung :");
        jLabel343.setName("jLabel343"); // NOI18N
        FormInput.add(jLabel343);
        jLabel343.setBounds(330, 1410, 60, 23);

        jLabel344.setText("Distensi :");
        jLabel344.setName("jLabel344"); // NOI18N
        FormInput.add(jLabel344);
        jLabel344.setBounds(460, 1410, 50, 23);

        BowelDistensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelDistensi.setName("BowelDistensi"); // NOI18N
        BowelDistensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelDistensiKeyPressed(evt);
            }
        });
        FormInput.add(BowelDistensi);
        BowelDistensi.setBounds(510, 1410, 70, 23);

        jLabel345.setText("Nyeri Tekan :");
        jLabel345.setName("jLabel345"); // NOI18N
        FormInput.add(jLabel345);
        jLabel345.setBounds(580, 1410, 70, 23);

        BowelNyeriTekan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelNyeriTekan.setName("BowelNyeriTekan"); // NOI18N
        BowelNyeriTekan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelNyeriTekanKeyPressed(evt);
            }
        });
        FormInput.add(BowelNyeriTekan);
        BowelNyeriTekan.setBounds(650, 1410, 70, 23);

        jLabel346.setText("NGT :");
        jLabel346.setName("jLabel346"); // NOI18N
        FormInput.add(jLabel346);
        jLabel346.setBounds(720, 1410, 40, 23);

        BowelNGT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BowelNGT.setName("BowelNGT"); // NOI18N
        BowelNGT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BowelNGTKeyPressed(evt);
            }
        });
        FormInput.add(BowelNGT);
        BowelNGT.setBounds(760, 1410, 70, 23);

        jLabel347.setText("Intake :");
        jLabel347.setName("jLabel347"); // NOI18N
        FormInput.add(jLabel347);
        jLabel347.setBounds(0, 1440, 109, 23);

        BowelIntake.setFocusTraversalPolicyProvider(true);
        BowelIntake.setName("BowelIntake"); // NOI18N
        FormInput.add(BowelIntake);
        BowelIntake.setBounds(120, 1440, 64, 23);

        jLabel348.setText("ml/jam");
        jLabel348.setName("jLabel348"); // NOI18N
        FormInput.add(jLabel348);
        jLabel348.setBounds(190, 1440, 40, 23);

        jLabel349.setText("BAK :");
        jLabel349.setName("jLabel349"); // NOI18N
        FormInput.add(jLabel349);
        jLabel349.setBounds(0, 1280, 109, 23);

        jLabel350.setText("Pola Rutin :");
        jLabel350.setName("jLabel350"); // NOI18N
        FormInput.add(jLabel350);
        jLabel350.setBounds(110, 1280, 60, 23);

        BladderBAKPolaRutin.setFocusTraversalPolicyProvider(true);
        BladderBAKPolaRutin.setName("BladderBAKPolaRutin"); // NOI18N
        FormInput.add(BladderBAKPolaRutin);
        BladderBAKPolaRutin.setBounds(170, 1280, 40, 23);

        jLabel351.setText("Saat Ini :");
        jLabel351.setName("jLabel351"); // NOI18N
        FormInput.add(jLabel351);
        jLabel351.setBounds(250, 1280, 50, 23);

        BladderBAKSaatIni.setFocusTraversalPolicyProvider(true);
        BladderBAKSaatIni.setName("BladderBAKSaatIni"); // NOI18N
        BladderBAKSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderBAKSaatIniKeyPressed(evt);
            }
        });
        FormInput.add(BladderBAKSaatIni);
        BladderBAKSaatIni.setBounds(300, 1280, 40, 23);

        jLabel352.setText("x/hari");
        jLabel352.setName("jLabel352"); // NOI18N
        FormInput.add(jLabel352);
        jLabel352.setBounds(340, 1280, 40, 23);

        jLabel353.setText("x/hari");
        jLabel353.setName("jLabel353"); // NOI18N
        FormInput.add(jLabel353);
        jLabel353.setBounds(210, 1280, 40, 23);

        BladderBAKTerkontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terkontrol", "Tidak terkontrol" }));
        BladderBAKTerkontrol.setName("BladderBAKTerkontrol"); // NOI18N
        BladderBAKTerkontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderBAKTerkontrolKeyPressed(evt);
            }
        });
        FormInput.add(BladderBAKTerkontrol);
        BladderBAKTerkontrol.setBounds(390, 1280, 120, 23);

        jLabel354.setText("Produksi Urin :");
        jLabel354.setName("jLabel354"); // NOI18N
        FormInput.add(jLabel354);
        jLabel354.setBounds(510, 1280, 80, 23);

        BladderProduksiUrin.setFocusTraversalPolicyProvider(true);
        BladderProduksiUrin.setName("BladderProduksiUrin"); // NOI18N
        BladderProduksiUrin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderProduksiUrinKeyPressed(evt);
            }
        });
        FormInput.add(BladderProduksiUrin);
        BladderProduksiUrin.setBounds(600, 1280, 40, 23);

        jLabel355.setText("/jam");
        jLabel355.setName("jLabel355"); // NOI18N
        FormInput.add(jLabel355);
        jLabel355.setBounds(640, 1280, 30, 23);

        jLabel356.setText("Warna :");
        jLabel356.setName("jLabel356"); // NOI18N
        FormInput.add(jLabel356);
        jLabel356.setBounds(670, 1280, 50, 23);

        BladderWarna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuning jernih", "Kuning kental/coklat", "Merah", "Bening" }));
        BladderWarna.setName("BladderWarna"); // NOI18N
        BladderWarna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderWarnaKeyPressed(evt);
            }
        });
        FormInput.add(BladderWarna);
        BladderWarna.setBounds(720, 1280, 150, 23);

        jLabel357.setText("Sakit Waktu BAK :");
        jLabel357.setName("jLabel357"); // NOI18N
        FormInput.add(jLabel357);
        jLabel357.setBounds(0, 1310, 109, 23);

        BladderDistensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BladderDistensi.setName("BladderDistensi"); // NOI18N
        BladderDistensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderDistensiKeyPressed(evt);
            }
        });
        FormInput.add(BladderDistensi);
        BladderDistensi.setBounds(490, 1310, 120, 23);

        jLabel358.setText("Distensi/Ketegangan Kandung Kemih Kencing :");
        jLabel358.setName("jLabel358"); // NOI18N
        FormInput.add(jLabel358);
        jLabel358.setBounds(240, 1310, 250, 23);

        BladderSakitWaktuBAK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BladderSakitWaktuBAK.setName("BladderSakitWaktuBAK"); // NOI18N
        BladderSakitWaktuBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderSakitWaktuBAKKeyPressed(evt);
            }
        });
        FormInput.add(BladderSakitWaktuBAK);
        BladderSakitWaktuBAK.setBounds(120, 1310, 120, 23);

        jLabel359.setText("Keluhan Sakit Pinggang :");
        jLabel359.setName("jLabel359"); // NOI18N
        FormInput.add(jLabel359);
        jLabel359.setBounds(610, 1310, 130, 23);

        BladderSakitPinggang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BladderSakitPinggang.setName("BladderSakitPinggang"); // NOI18N
        BladderSakitPinggang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BladderSakitPinggangKeyPressed(evt);
            }
        });
        FormInput.add(BladderSakitPinggang);
        BladderSakitPinggang.setBounds(750, 1310, 120, 23);

        jLabel360.setText("Tugor Kulit :");
        jLabel360.setName("jLabel360"); // NOI18N
        FormInput.add(jLabel360);
        jLabel360.setBounds(0, 1510, 109, 23);

        BoneTugorKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buruk", "Baik/elastic" }));
        BoneTugorKulit.setName("BoneTugorKulit"); // NOI18N
        BoneTugorKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneTugorKulitKeyPressed(evt);
            }
        });
        FormInput.add(BoneTugorKulit);
        BoneTugorKulit.setBounds(110, 1510, 110, 23);

        jLabel361.setText("Keadaan Kulit :");
        jLabel361.setName("jLabel361"); // NOI18N
        FormInput.add(jLabel361);
        jLabel361.setBounds(220, 1510, 80, 23);

        BoneKeadaanKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Terdapat luka operasi" }));
        BoneKeadaanKulit.setName("BoneKeadaanKulit"); // NOI18N
        BoneKeadaanKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneKeadaanKulitKeyPressed(evt);
            }
        });
        FormInput.add(BoneKeadaanKulit);
        BoneKeadaanKulit.setBounds(300, 1510, 130, 23);

        jLabel362.setText(", Lokasi :");
        jLabel362.setName("jLabel362"); // NOI18N
        FormInput.add(jLabel362);
        jLabel362.setBounds(430, 1510, 60, 23);

        jLabel363.setText("Keadaan Luka :");
        jLabel363.setName("jLabel363"); // NOI18N
        FormInput.add(jLabel363);
        jLabel363.setBounds(650, 1510, 90, 23);

        BoneKeadaanLuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Infeksi/gangren", "Tidak ada atau min" }));
        BoneKeadaanLuka.setName("BoneKeadaanLuka"); // NOI18N
        BoneKeadaanLuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneKeadaanLukaKeyPressed(evt);
            }
        });
        FormInput.add(BoneKeadaanLuka);
        BoneKeadaanLuka.setBounds(740, 1510, 130, 23);

        BoneLokasi.setFocusTraversalPolicyProvider(true);
        BoneLokasi.setName("BoneLokasi"); // NOI18N
        BoneLokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneLokasiKeyPressed(evt);
            }
        });
        FormInput.add(BoneLokasi);
        BoneLokasi.setBounds(490, 1510, 160, 23);

        jLabel364.setText("Sulit Dalam Gerak :");
        jLabel364.setName("jLabel364"); // NOI18N
        FormInput.add(jLabel364);
        jLabel364.setBounds(0, 1540, 109, 23);

        BoneSulitDalamGerak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BoneSulitDalamGerak.setName("BoneSulitDalamGerak"); // NOI18N
        BoneSulitDalamGerak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneSulitDalamGerakKeyPressed(evt);
            }
        });
        FormInput.add(BoneSulitDalamGerak);
        BoneSulitDalamGerak.setBounds(110, 1540, 72, 23);

        jLabel365.setText("Fraktur :");
        jLabel365.setName("jLabel365"); // NOI18N
        FormInput.add(jLabel365);
        jLabel365.setBounds(190, 1540, 60, 23);

        BoneFraktur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BoneFraktur.setName("BoneFraktur"); // NOI18N
        BoneFraktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneFrakturKeyPressed(evt);
            }
        });
        FormInput.add(BoneFraktur);
        BoneFraktur.setBounds(250, 1540, 72, 23);

        jLabel366.setText(", Area :");
        jLabel366.setName("jLabel366"); // NOI18N
        FormInput.add(jLabel366);
        jLabel366.setBounds(330, 1540, 40, 23);

        BoneArea.setFocusTraversalPolicyProvider(true);
        BoneArea.setName("BoneArea"); // NOI18N
        BoneArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneAreaKeyPressed(evt);
            }
        });
        FormInput.add(BoneArea);
        BoneArea.setBounds(380, 1540, 160, 23);

        jLabel367.setText("Odema :");
        jLabel367.setName("jLabel367"); // NOI18N
        FormInput.add(jLabel367);
        jLabel367.setBounds(540, 1540, 50, 23);

        BoneOdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BoneOdema.setName("BoneOdema"); // NOI18N
        BoneOdema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneOdemaKeyPressed(evt);
            }
        });
        FormInput.add(BoneOdema);
        BoneOdema.setBounds(590, 1540, 72, 23);

        jLabel368.setText("Kekuatan Otot :");
        jLabel368.setName("jLabel368"); // NOI18N
        FormInput.add(jLabel368);
        jLabel368.setBounds(660, 1540, 90, 23);

        BoneKekuatanOtot.setFocusTraversalPolicyProvider(true);
        BoneKekuatanOtot.setName("BoneKekuatanOtot"); // NOI18N
        BoneKekuatanOtot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BoneKekuatanOtotKeyPressed(evt);
            }
        });
        FormInput.add(BoneKekuatanOtot);
        BoneKekuatanOtot.setBounds(760, 1540, 110, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("RIWAYAT KEHAMILAH/PERSALINAN, IMUNISASI DAN TUMBUH KEMBANG");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(50, 1580, 390, 23);

        jLabel369.setText("Usia Ibu Saat Hamil :");
        jLabel369.setName("jLabel369"); // NOI18N
        FormInput.add(jLabel369);
        jLabel369.setBounds(0, 1610, 109, 23);

        UsiaIbuSaatHamil.setFocusTraversalPolicyProvider(true);
        UsiaIbuSaatHamil.setName("UsiaIbuSaatHamil"); // NOI18N
        FormInput.add(UsiaIbuSaatHamil);
        UsiaIbuSaatHamil.setBounds(114, 1610, 130, 23);

        jLabel370.setText("Gravida Ke :");
        jLabel370.setName("jLabel370"); // NOI18N
        FormInput.add(jLabel370);
        jLabel370.setBounds(249, 1610, 70, 23);

        GravidaKe.setFocusTraversalPolicyProvider(true);
        GravidaKe.setName("GravidaKe"); // NOI18N
        FormInput.add(GravidaKe);
        GravidaKe.setBounds(320, 1610, 70, 23);

        jLabel371.setText("Gangguan Hamil (Trimester 1) :");
        jLabel371.setName("jLabel371"); // NOI18N
        FormInput.add(jLabel371);
        jLabel371.setBounds(390, 1610, 170, 23);

        GangguanHamil.setFocusTraversalPolicyProvider(true);
        GangguanHamil.setName("GangguanHamil"); // NOI18N
        FormInput.add(GangguanHamil);
        GangguanHamil.setBounds(560, 1610, 310, 23);

        jLabel372.setText("Tipe Persalinan :");
        jLabel372.setName("jLabel372"); // NOI18N
        FormInput.add(jLabel372);
        jLabel372.setBounds(0, 1640, 109, 23);

        TipePersalinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Induksi", "Forcep", "Vacum", "SC" }));
        TipePersalinan.setName("TipePersalinan"); // NOI18N
        TipePersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipePersalinanKeyPressed(evt);
            }
        });
        FormInput.add(TipePersalinan);
        TipePersalinan.setBounds(110, 1640, 110, 23);

        jLabel373.setText("kg");
        jLabel373.setName("jLabel373"); // NOI18N
        FormInput.add(jLabel373);
        jLabel373.setBounds(350, 1640, 20, 23);

        BBLahir.setFocusTraversalPolicyProvider(true);
        BBLahir.setName("BBLahir"); // NOI18N
        FormInput.add(BBLahir);
        BBLahir.setBounds(280, 1640, 70, 23);

        jLabel374.setText("BB Lahir :");
        jLabel374.setName("jLabel374"); // NOI18N
        FormInput.add(jLabel374);
        jLabel374.setBounds(220, 1640, 60, 23);

        jLabel375.setText("TB :");
        jLabel375.setName("jLabel375"); // NOI18N
        FormInput.add(jLabel375);
        jLabel375.setBounds(370, 1640, 30, 23);

        TBLahir.setFocusTraversalPolicyProvider(true);
        TBLahir.setName("TBLahir"); // NOI18N
        FormInput.add(TBLahir);
        TBLahir.setBounds(400, 1640, 70, 23);

        jLabel376.setText("kg");
        jLabel376.setName("jLabel376"); // NOI18N
        FormInput.add(jLabel376);
        jLabel376.setBounds(470, 1640, 20, 23);

        jLabel377.setText("Lingkar Kepala :");
        jLabel377.setName("jLabel377"); // NOI18N
        FormInput.add(jLabel377);
        jLabel377.setBounds(490, 1640, 90, 23);

        jLabel378.setText("cm");
        jLabel378.setName("jLabel378"); // NOI18N
        FormInput.add(jLabel378);
        jLabel378.setBounds(650, 1640, 20, 23);

        LingkarKepalaLahir.setFocusTraversalPolicyProvider(true);
        LingkarKepalaLahir.setName("LingkarKepalaLahir"); // NOI18N
        FormInput.add(LingkarKepalaLahir);
        LingkarKepalaLahir.setBounds(580, 1640, 70, 23);

        jLabel379.setText("BB Saat Dikaji :");
        jLabel379.setName("jLabel379"); // NOI18N
        FormInput.add(jLabel379);
        jLabel379.setBounds(0, 1670, 109, 23);

        BBDiKaji.setFocusTraversalPolicyProvider(true);
        BBDiKaji.setName("BBDiKaji"); // NOI18N
        FormInput.add(BBDiKaji);
        BBDiKaji.setBounds(110, 1670, 70, 23);

        jLabel380.setText("kg");
        jLabel380.setName("jLabel380"); // NOI18N
        FormInput.add(jLabel380);
        jLabel380.setBounds(180, 1670, 20, 23);

        jLabel381.setText("TB :");
        jLabel381.setName("jLabel381"); // NOI18N
        FormInput.add(jLabel381);
        jLabel381.setBounds(200, 1670, 30, 23);

        TBDiKaji.setFocusTraversalPolicyProvider(true);
        TBDiKaji.setName("TBDiKaji"); // NOI18N
        FormInput.add(TBDiKaji);
        TBDiKaji.setBounds(230, 1670, 70, 23);

        jLabel382.setText("kg");
        jLabel382.setName("jLabel382"); // NOI18N
        FormInput.add(jLabel382);
        jLabel382.setBounds(300, 1670, 20, 23);

        jLabel383.setText("Imunisasi Dasar :");
        jLabel383.setName("jLabel383"); // NOI18N
        FormInput.add(jLabel383);
        jLabel383.setBounds(330, 1670, 100, 23);

        ImunisasiDasar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Lengkap", "Tidak pernah", "Tidak lengkap" }));
        ImunisasiDasar.setName("ImunisasiDasar"); // NOI18N
        ImunisasiDasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ImunisasiDasarKeyPressed(evt);
            }
        });
        FormInput.add(ImunisasiDasar);
        ImunisasiDasar.setBounds(430, 1670, 140, 23);

        jLabel384.setText("Sebutkan Yang Belum :");
        jLabel384.setName("jLabel384"); // NOI18N
        FormInput.add(jLabel384);
        jLabel384.setBounds(570, 1670, 120, 23);

        ImunisasiBelum.setFocusTraversalPolicyProvider(true);
        ImunisasiBelum.setName("ImunisasiBelum"); // NOI18N
        FormInput.add(ImunisasiBelum);
        ImunisasiBelum.setBounds(690, 1670, 180, 23);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("RIWAYAT TUMBUH KEMBANG (DIKAJI PADA PASIEN USIA <= 3 TAHUN)");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(50, 1700, 390, 23);

        jLabel385.setText("Tengkurap, Usia :");
        jLabel385.setName("jLabel385"); // NOI18N
        FormInput.add(jLabel385);
        jLabel385.setBounds(0, 1730, 109, 23);

        TengkurapUsia.setFocusTraversalPolicyProvider(true);
        TengkurapUsia.setName("TengkurapUsia"); // NOI18N
        FormInput.add(TengkurapUsia);
        TengkurapUsia.setBounds(110, 1730, 40, 23);

        jLabel386.setText("Berdiri, Usia :");
        jLabel386.setName("jLabel386"); // NOI18N
        FormInput.add(jLabel386);
        jLabel386.setBounds(150, 1730, 80, 23);

        BerdiriUsia.setFocusTraversalPolicyProvider(true);
        BerdiriUsia.setName("BerdiriUsia"); // NOI18N
        FormInput.add(BerdiriUsia);
        BerdiriUsia.setBounds(230, 1730, 40, 23);

        jLabel387.setText("Bicara, Usia :");
        jLabel387.setName("jLabel387"); // NOI18N
        FormInput.add(jLabel387);
        jLabel387.setBounds(270, 1730, 80, 23);

        BicaraUsia.setFocusTraversalPolicyProvider(true);
        BicaraUsia.setName("BicaraUsia"); // NOI18N
        FormInput.add(BicaraUsia);
        BicaraUsia.setBounds(350, 1730, 40, 23);

        jLabel388.setText("Duduk, Usia :");
        jLabel388.setName("jLabel388"); // NOI18N
        FormInput.add(jLabel388);
        jLabel388.setBounds(390, 1730, 80, 23);

        DudukUsia.setFocusTraversalPolicyProvider(true);
        DudukUsia.setName("DudukUsia"); // NOI18N
        FormInput.add(DudukUsia);
        DudukUsia.setBounds(470, 1730, 40, 23);

        jLabel389.setText("Berjalan, Usia :");
        jLabel389.setName("jLabel389"); // NOI18N
        FormInput.add(jLabel389);
        jLabel389.setBounds(510, 1730, 80, 23);

        BerjalanUsia.setFocusTraversalPolicyProvider(true);
        BerjalanUsia.setName("BerjalanUsia"); // NOI18N
        FormInput.add(BerjalanUsia);
        BerjalanUsia.setBounds(590, 1730, 40, 23);

        jLabel390.setText("Tumbuh Gigi, Usia :");
        jLabel390.setName("jLabel390"); // NOI18N
        FormInput.add(jLabel390);
        jLabel390.setBounds(630, 1730, 100, 23);

        TumbuhGigiUsia.setFocusTraversalPolicyProvider(true);
        TumbuhGigiUsia.setName("TumbuhGigiUsia"); // NOI18N
        FormInput.add(TumbuhGigiUsia);
        TumbuhGigiUsia.setBounds(730, 1730, 40, 23);

        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel259.setText("7. Penggunaan Medikamentosa");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(50, 2710, 300, 23);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("6. Respon Terhadap Pembedahan Sedasi / Anastesi");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(50, 2680, 300, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("5. Faktor Lingkungan");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(50, 2650, 300, 23);

        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel250.setText("4. Gangguan Kognitif");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(50, 2620, 300, 23);

        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel247.setText("3. Diagnosis");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(50, 2590, 300, 23);

        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel243.setText("2. Jenis Kelamin");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(50, 2560, 300, 23);

        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel240.setText("1. Usia");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(50, 2530, 300, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Skala Humpty Dumpty :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(30, 2510, 120, 23);

        jLabel241.setText("Skala :");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(310, 2530, 80, 23);

        jLabel244.setText("Skala :");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(310, 2560, 80, 23);

        jLabel248.setText("Skala :");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(310, 2590, 80, 23);

        jLabel251.setText("Skala :");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(310, 2620, 80, 23);

        jLabel254.setText("Skala :");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(310, 2650, 80, 23);

        jLabel257.setText("Skala :");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(310, 2680, 80, 23);

        jLabel260.setText("Skala :");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(310, 2710, 80, 23);

        SkalaHumptyDumpty7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Penggunaan multiple obat", "Penggunaan salah satu obat", "Tidak ada medikasi" }));
        SkalaHumptyDumpty7.setName("SkalaHumptyDumpty7"); // NOI18N
        SkalaHumptyDumpty7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty7ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty7KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty7);
        SkalaHumptyDumpty7.setBounds(390, 2710, 350, 23);

        SkalaHumptyDumpty6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dalam 24 jam", "Dalam 48 jam", "1>48 jam/tidak menjalani pembedahan/sedasi/anestesi" }));
        SkalaHumptyDumpty6.setName("SkalaHumptyDumpty6"); // NOI18N
        SkalaHumptyDumpty6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty6ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty6);
        SkalaHumptyDumpty6.setBounds(390, 2680, 350, 23);

        SkalaHumptyDumpty5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Riwayat jatuh/ditempat tidur dewasa", "Pasien/bayi menggunakan alat bantu/tempat tidur bayi", "Pasien/bayi diletakan di tempat tidur standart", "Area luar RS" }));
        SkalaHumptyDumpty5.setName("SkalaHumptyDumpty5"); // NOI18N
        SkalaHumptyDumpty5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty5ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty5);
        SkalaHumptyDumpty5.setBounds(390, 2650, 350, 23);

        SkalaHumptyDumpty4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak menyadari keterbatasan diri", "Lupa akan adanya keterbatasan", "Orientasi baik" }));
        SkalaHumptyDumpty4.setName("SkalaHumptyDumpty4"); // NOI18N
        SkalaHumptyDumpty4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty4ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty4);
        SkalaHumptyDumpty4.setBounds(390, 2620, 350, 23);

        SkalaHumptyDumpty3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Diagnosis neurologi", "Perubahan oksigenasi", "Gangguan prilaku", "Diagnosis lainnya" }));
        SkalaHumptyDumpty3.setName("SkalaHumptyDumpty3"); // NOI18N
        SkalaHumptyDumpty3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty3ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty3);
        SkalaHumptyDumpty3.setBounds(390, 2590, 350, 23);

        SkalaHumptyDumpty2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Laki-laki", "Perempuan" }));
        SkalaHumptyDumpty2.setName("SkalaHumptyDumpty2"); // NOI18N
        SkalaHumptyDumpty2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty2ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty2);
        SkalaHumptyDumpty2.setBounds(390, 2560, 350, 23);

        SkalaHumptyDumpty1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 3 Tahun", "3-7 Tahun", "7-13 Tahun", ">13 Tahun" }));
        SkalaHumptyDumpty1.setName("SkalaHumptyDumpty1"); // NOI18N
        SkalaHumptyDumpty1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty1ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty1);
        SkalaHumptyDumpty1.setBounds(390, 2530, 350, 23);

        TingkatHumptyDumpty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatHumptyDumpty.setText("Tingkat Resiko : Risiko Tinggi >12, Tindakan : Intervensi pencegahan risiko jatuh standar");
        TingkatHumptyDumpty.setName("TingkatHumptyDumpty"); // NOI18N
        FormInput.add(TingkatHumptyDumpty);
        TingkatHumptyDumpty.setBounds(50, 2740, 650, 23);

        jLabel242.setText("Nilai :");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(700, 2530, 75, 23);

        jLabel246.setText("Nilai :");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(700, 2560, 75, 23);

        jLabel249.setText("Nilai :");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(700, 2590, 75, 23);

        jLabel252.setText("Nilai :");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(700, 2620, 75, 23);

        jLabel255.setText("Nilai :");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(700, 2650, 75, 23);

        jLabel258.setText("Nilai :");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(700, 2680, 75, 23);

        jLabel261.setText("Nilai :");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(700, 2710, 75, 23);

        NilaiHumptyDumpty7.setEditable(false);
        NilaiHumptyDumpty7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty7.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty7.setName("NilaiHumptyDumpty7"); // NOI18N
        FormInput.add(NilaiHumptyDumpty7);
        NilaiHumptyDumpty7.setBounds(780, 2710, 60, 23);

        NilaiHumptyDumpty6.setEditable(false);
        NilaiHumptyDumpty6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty6.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty6.setName("NilaiHumptyDumpty6"); // NOI18N
        FormInput.add(NilaiHumptyDumpty6);
        NilaiHumptyDumpty6.setBounds(780, 2680, 60, 23);

        NilaiHumptyDumpty5.setEditable(false);
        NilaiHumptyDumpty5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty5.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty5.setName("NilaiHumptyDumpty5"); // NOI18N
        FormInput.add(NilaiHumptyDumpty5);
        NilaiHumptyDumpty5.setBounds(780, 2650, 60, 23);

        NilaiHumptyDumpty4.setEditable(false);
        NilaiHumptyDumpty4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty4.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty4.setName("NilaiHumptyDumpty4"); // NOI18N
        FormInput.add(NilaiHumptyDumpty4);
        NilaiHumptyDumpty4.setBounds(780, 2620, 60, 23);

        NilaiHumptyDumpty3.setEditable(false);
        NilaiHumptyDumpty3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty3.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty3.setName("NilaiHumptyDumpty3"); // NOI18N
        FormInput.add(NilaiHumptyDumpty3);
        NilaiHumptyDumpty3.setBounds(780, 2590, 60, 23);

        NilaiHumptyDumpty2.setEditable(false);
        NilaiHumptyDumpty2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty2.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty2.setName("NilaiHumptyDumpty2"); // NOI18N
        FormInput.add(NilaiHumptyDumpty2);
        NilaiHumptyDumpty2.setBounds(780, 2560, 60, 23);

        NilaiHumptyDumpty1.setEditable(false);
        NilaiHumptyDumpty1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty1.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty1.setName("NilaiHumptyDumpty1"); // NOI18N
        FormInput.add(NilaiHumptyDumpty1);
        NilaiHumptyDumpty1.setBounds(780, 2530, 60, 23);

        jLabel270.setText("Total :");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(700, 2740, 75, 23);

        NilaiHumptyDumptyTotal.setEditable(false);
        NilaiHumptyDumptyTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumptyTotal.setText("0");
        NilaiHumptyDumptyTotal.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumptyTotal.setName("NilaiHumptyDumptyTotal"); // NOI18N
        FormInput.add(NilaiHumptyDumptyTotal);
        NilaiHumptyDumptyTotal.setBounds(780, 2740, 60, 23);

        jLabel391.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel391.setText("    (berdasarkan penilaian objektif data berat badan bila ada penilaian");
        jLabel391.setName("jLabel391"); // NOI18N
        FormInput.add(jLabel391);
        jLabel391.setBounds(40, 3090, 380, 23);

        jLabel392.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel392.setText("    objektif orang tua pasien atau bayi <1 tahun berat badan tidak naik");
        jLabel392.setName("jLabel392"); // NOI18N
        FormInput.add(jLabel392);
        jLabel392.setBounds(40, 3110, 380, 23);

        jLabel393.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel393.setText("    selama 3 bulan terakhir) ?");
        jLabel393.setName("jLabel393"); // NOI18N
        FormInput.add(jLabel393);
        jLabel393.setBounds(40, 3130, 380, 23);

        jLabel394.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel394.setText("3. Apakah terdapat salah satu dari kondisi berikut ? ( diare > 5 kali / hari");
        jLabel394.setName("jLabel394"); // NOI18N
        FormInput.add(jLabel394);
        jLabel394.setBounds(40, 3150, 380, 23);

        jLabel395.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel395.setText("    dan muntah > 3 kali / hari dalam seminggu terakhir atau asupan");
        jLabel395.setName("jLabel395"); // NOI18N
        FormInput.add(jLabel395);
        jLabel395.setBounds(40, 3170, 380, 23);

        jLabel396.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel396.setText("    makanan berkurang selama 1 minggu terakhir )");
        jLabel396.setName("jLabel396"); // NOI18N
        FormInput.add(jLabel396);
        jLabel396.setBounds(40, 3190, 380, 23);

        jLabel397.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel397.setText("4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien");
        jLabel397.setName("jLabel397"); // NOI18N
        FormInput.add(jLabel397);
        jLabel397.setBounds(40, 3210, 380, 23);

        jLabel398.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel398.setText("    beresiko mengalami malnutrisi ?");
        jLabel398.setName("jLabel398"); // NOI18N
        FormInput.add(jLabel398);
        jLabel398.setBounds(40, 3230, 380, 23);

        SkalaGizi3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaGizi3.setName("SkalaGizi3"); // NOI18N
        SkalaGizi3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi3ItemStateChanged(evt);
            }
        });
        SkalaGizi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi3);
        SkalaGizi3.setBounds(420, 3150, 320, 23);

        jLabel399.setText("Skor :");
        jLabel399.setName("jLabel399"); // NOI18N
        FormInput.add(jLabel399);
        jLabel399.setBounds(740, 3150, 40, 23);

        NilaiGizi3.setEditable(false);
        NilaiGizi3.setFocusTraversalPolicyProvider(true);
        NilaiGizi3.setName("NilaiGizi3"); // NOI18N
        FormInput.add(NilaiGizi3);
        NilaiGizi3.setBounds(790, 3150, 60, 23);

        SkalaGizi4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaGizi4.setName("SkalaGizi4"); // NOI18N
        SkalaGizi4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi4ItemStateChanged(evt);
            }
        });
        SkalaGizi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi4);
        SkalaGizi4.setBounds(420, 3210, 320, 23);

        jLabel400.setText("Skor :");
        jLabel400.setName("jLabel400"); // NOI18N
        FormInput.add(jLabel400);
        jLabel400.setBounds(740, 3210, 40, 23);

        NilaiGizi4.setEditable(false);
        NilaiGizi4.setFocusTraversalPolicyProvider(true);
        NilaiGizi4.setName("NilaiGizi4"); // NOI18N
        FormInput.add(NilaiGizi4);
        NilaiGizi4.setBounds(790, 3210, 60, 23);

        MotivasiKesembuhanIbadah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        MotivasiKesembuhanIbadah.setName("MotivasiKesembuhanIbadah"); // NOI18N
        MotivasiKesembuhanIbadah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotivasiKesembuhanIbadahKeyPressed(evt);
            }
        });
        FormInput.add(MotivasiKesembuhanIbadah);
        MotivasiKesembuhanIbadah.setBounds(230, 2270, 60, 23);

        jLabel401.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel401.setText("n. wajib :");
        jLabel401.setName("jLabel401"); // NOI18N
        FormInput.add(jLabel401);
        jLabel401.setBounds(40, 2240, 50, 23);

        Toharah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tayamum", "Wudhu", "Lain-lain" }));
        Toharah.setName("Toharah"); // NOI18N
        Toharah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToharahKeyPressed(evt);
            }
        });
        FormInput.add(Toharah);
        Toharah.setBounds(580, 2240, 100, 23);

        jLabel402.setText("o. halangan lain :");
        jLabel402.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel402.setName("jLabel402"); // NOI18N
        FormInput.add(jLabel402);
        jLabel402.setBounds(200, 2240, 90, 23);

        Sholat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdiri", "Duduk", "Berbaring" }));
        Sholat.setName("Sholat"); // NOI18N
        Sholat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SholatKeyPressed(evt);
            }
        });
        FormInput.add(Sholat);
        Sholat.setBounds(750, 2240, 100, 23);

        jLabel73.setText("p. toharah :");
        jLabel73.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(510, 2240, 60, 23);

        jLabel74.setText("q. sholat :");
        jLabel74.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(690, 2240, 50, 23);

        Wajib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baligh", "Non baligh" }));
        Wajib.setName("Wajib"); // NOI18N
        Wajib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WajibKeyPressed(evt);
            }
        });
        FormInput.add(Wajib);
        Wajib.setBounds(100, 2240, 100, 23);

        KetHalanganLain.setFocusTraversalPolicyProvider(true);
        KetHalanganLain.setName("KetHalanganLain"); // NOI18N
        KetHalanganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetHalanganLainKeyPressed(evt);
            }
        });
        FormInput.add(KetHalanganLain);
        KetHalanganLain.setBounds(300, 2240, 210, 23);

        jLabel403.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel403.setText("r. motivasi kesembuhan dan ibadah :");
        jLabel403.setName("jLabel403"); // NOI18N
        jLabel403.setPreferredSize(new java.awt.Dimension(180, 14));
        FormInput.add(jLabel403);
        jLabel403.setBounds(40, 2270, 190, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel34.setText("Pasien :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(55, 23));
        FormMenu.add(jLabel34);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        TNoRM1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormMenu.add(TNoRM1);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(250, 23));
        FormMenu.add(TPasien1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrint1);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(3, 0, 1, 1));

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahDetail.setName("tbMasalahDetail"); // NOI18N
        Scroll7.setViewportView(tbMasalahDetail);

        FormMasalahRencana.add(Scroll7);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbRencanaDetail.setName("tbRencanaDetail"); // NOI18N
        Scroll9.setViewportView(tbRencanaDetail);

        FormMasalahRencana.add(Scroll9);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Rencana Keperawatan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        DetailRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailRencana.setColumns(20);
        DetailRencana.setRows(5);
        DetailRencana.setName("DetailRencana"); // NOI18N
        DetailRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetailRencanaKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(DetailRencana);

        FormMasalahRencana.add(scrollPane6);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().isEmpty()) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (KdPetugas.getText().trim().isEmpty() || NmPetugas.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Pengkaji 1");
        } else if (KdPetugas2.getText().trim().isEmpty() || NmPetugas2.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas2, "Pegkaji 2");
        } else if (KdDPJP.getText().trim().isEmpty() || NmDPJP.getText().trim().
                isEmpty()) {
            Valid.textKosong(BtnDPJP, "DPJP");
        } else if (RPS.getText().trim().isEmpty()) {
            Valid.textKosong(RPS, "Riwayat Penyakit Sekarang");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else {
            if (pilihan1.isSelected() == true) {
                pilih1 = pilihan1.getText();
            }
            if (pilihan2.isSelected() == true) {
                pilih2 = pilihan2.getText();
            }
            if (pilihan3.isSelected() == true) {
                pilih3 = pilihan3.getText();
            }
            if (pilihan4.isSelected() == true) {
                pilih4 = pilihan4.getText();
            }
            if (pilihan5.isSelected() == true) {
                pilih5 = pilihan5.getText();
            }
            if (pilihan6.isSelected() == true) {
                pilih6 = pilihan6.getText();
            }
            if (pilihan7.isSelected() == true) {
                pilih7 = pilihan7.getText();
            }
            if (pilihan8.isSelected() == true) {
                pilih8 = pilihan8.getText();
            }
            if (Sequel.menyimpantf("penilaian_awal_keperawatan_ranap_kritis",
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                    "No.Rawat", 252, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglAsuhan.
                        getSelectedItem() + "") + " " + TglAsuhan.
                                getSelectedItem().toString().substring(11, 19),
                        Anamnesis.getSelectedItem().toString(), KetAnamnesis.
                        getText(), TibadiRuang.getSelectedItem().toString(),
                        MacamKasus.getSelectedItem().toString(),
                        CaraMasuk.getSelectedItem().toString(), KeluhanUtama.
                        getText(), RPS.getText(), RPD.getText(), RPK.getText(),
                        RPO.getText(), RPembedahan.getText(), RDirawatRS.
                        getText(), AlatBantuDipakai.getSelectedItem().toString(),
                        SedangMenyusui.getSelectedItem().toString(),
                        KetSedangMenyusui.getText(), RTranfusi.getText(),
                        Alergi.getText(), KebiasaanMerokok.getSelectedItem().
                        toString(), KebiasaanJumlahRokok.getText(),
                        KebiasaanAlkohol.getSelectedItem().toString(),
                        KebiasaanJumlahAlkohol.getText(), KebiasaanNarkoba.
                        getSelectedItem().toString(),
                        OlahRaga.getSelectedItem().toString(), KesadaranMental.
                        getText(),
                        KeadaanMentalUmum.getSelectedItem().toString(), GCS.
                        getText(), TD.getText(), Nadi.getText(), RR.getText(),
                        Suhu.getText(), SpO2.getText(), BB.getText(), TB.
                        getText(), BreathingJalanNafas.getSelectedItem().
                                toString(), BreathingJalanNafasBerupa.
                                getSelectedItem().toString(),
                        BreathingPernapasan.getSelectedItem().toString(),
                        BreathingPernapasanDengan.getSelectedItem().toString(),
                        BreathingETT.getText(), BreathingCuff.getText(),
                        BreathingFrekuensi.getText(), BreathingIrama.
                        getSelectedItem().toString(), BreathingKedalaman.
                                getSelectedItem().toString(), BreathingSpulum.
                                getSelectedItem().toString(),
                        BreathingKonsistensi.getSelectedItem().toString(),
                        BreathingNafasBunyi.getSelectedItem().toString(),
                        BreathingTerdapatDarah.getSelectedItem().toString(),
                        BreathingTerdapatDarahJumlah.getText(),
                        BreathingSuaraNafas.getSelectedItem().toString(),
                        BreathingAnalisaGasDarahPH.getText(),
                        BreathingAnalisaGasDarahpCO2.getText(),
                        BreathingAnalisaGasDarahpO2.getText(),
                        BreathingAnalisaGasDarahSatO2.getText(),
                        BloodSirkulasiPeriferNadi.getText(),
                        BloodSirkulasiPeriferIrama.getSelectedItem().toString(),
                        BloodSirkulasiPeriferEKG.getText(),
                        BloodSirkulasiPeriferTekananDarah.getText(),
                        BloodSirkulasiPeriferMAP.getText(),
                        BloodSirkulasiPeriferCVP.getText(),
                        BloodSirkulasiPeriferIBP.getText(),
                        BloodSirkulasiPeriferAkral.getSelectedItem().toString(),
                        BloodSirkulasiPeriferDistensiVenaJugulari.
                                getSelectedItem().toString(),
                        BloodSirkulasiPeriferSuhu.getText(),
                        BloodSirkulasiPeriferWarnaKulit.getSelectedItem().
                                toString(),
                        BloodSirkulasiPeriferPengisianKaplier.getSelectedItem().
                                toString(), BloodSirkulasiPeriferEdema.
                                getSelectedItem().toString(),
                        BloodSirkulasiPeriferEdemaPada.getSelectedItem().
                                toString(),
                        BloodSirkulasiJantungJantungIrama.getSelectedItem().
                                toString(), BloodSirkulasiJantungBunyi.
                                getSelectedItem().toString(),
                        BloodSirkulasiJantungKeluhan.getSelectedItem().
                                toString(), BloodSirkulasiJantungKarakteristik.
                                getSelectedItem().toString(),
                        BloodSirkulasiJantungSakitDada.getSelectedItem().
                                toString(),
                        BloodSirkulasiJantungSakitDadaTimbul.getSelectedItem().
                                toString(),
                        BloodHematologiHB.getText(), BloodHematologiHt.getText(),
                        BloodHematologiEritrosit.getText(),
                        BloodHematologiLeukosit.getText(),
                        BloodHematologiTrombosit.getText(),
                        BloodHematologiPendarahan.getSelectedItem().toString(),
                        BloodHematologiCT.getText(), BloodHematologiPTT.
                        getText(), BrainSirkulasiSerebralTingkatKesadaran.
                                getSelectedItem().toString(),
                        BrainSirkulasiSerebralPupil.getSelectedItem().toString(),
                        BrainSirkulasiSerebralReaksiTerhadapCahaya.
                                getSelectedItem().toString(),
                        BrainSirkulasiSerebralGCSE.getText(),
                        BrainSirkulasiSerebralGCSV.getText(),
                        BrainSirkulasiSerebralGCSM.getText(),
                        BrainSirkulasiSerebralGCSJumlah.getText(),
                        BrainSirkulasiSerebralTerjadi.getSelectedItem().
                                toString(), BrainSirkulasiSerebralTerjadiBagian.
                                getSelectedItem().toString(),
                        BrainSirkulasiSerebralICP.getText(),
                        BrainSirkulasiSerebralCPP.getText(),
                        BrainSirkulasiSerebralSOD.getText(),
                        BrainSirkulasiSerebralEUD.getText(),
                        BrainSirkulasiSerebralPalkososial.getSelectedItem().
                                toString(), BladderBAKPolaRutin.getText(),
                        BladderBAKSaatIni.getText(), BladderBAKTerkontrol.
                        getSelectedItem().toString(), BladderProduksiUrin.
                                getText(), BladderWarna.getSelectedItem().
                                toString(), BladderSakitWaktuBAK.
                                getSelectedItem().toString(), BladderDistensi.
                                getSelectedItem().toString(),
                        BladderSakitPinggang.getSelectedItem().toString(),
                        BowelBAKPolaRutin.getText(), BowelBAKSaatIni.getText(),
                        BowelKonsistensi.getSelectedItem().toString(),
                        BowelWarna.getSelectedItem().toString(), BowelLendir.
                        getSelectedItem().toString(), BowelMual.
                                getSelectedItem().toString(), BowelKembung.
                                getSelectedItem().toString(), BowelDistensi.
                                getSelectedItem().toString(), BowelNyeriTekan.
                                getSelectedItem().toString(), BowelNGT.
                                getSelectedItem().toString(), BowelIntake.
                                getText(),
                        BoneTugorKulit.getSelectedItem().toString(),
                        BoneKeadaanKulit.getSelectedItem().toString(),
                        BoneLokasi.getText(), BoneKeadaanLuka.getSelectedItem().
                        toString(), BoneSulitDalamGerak.getSelectedItem().
                                toString(), BoneFraktur.getSelectedItem().
                                toString(), BoneArea.getText(), BoneOdema.
                        getSelectedItem().toString(), BoneKekuatanOtot.getText(),
                        UsiaIbuSaatHamil.getText(), GravidaKe.getText(),
                        GangguanHamil.getText(), TipePersalinan.
                        getSelectedItem().toString(), BBLahir.getText(),
                        TBLahir.getText(), LingkarKepalaLahir.getText(),
                        BBDiKaji.getText(), TBDiKaji.getText(), ImunisasiDasar.
                        getSelectedItem().toString(), ImunisasiBelum.getText(),
                        TengkurapUsia.getText(), BerdiriUsia.getText(),
                        BicaraUsia.getText(), DudukUsia.getText(), BerjalanUsia.
                        getText(), TumbuhGigiUsia.getText(), PolaAktifitasMandi.
                        getSelectedItem().toString(), PolaAktifitasMakan.
                                getSelectedItem().toString(),
                        PolaAktifitasEliminasi.getSelectedItem().toString(),
                        PolaAktifitasBerpakaian.getSelectedItem().toString(),
                        PolaAktifitasBerpindah.getSelectedItem().toString(),
                        PolaNutrisiFrekuensi.getText(), PolaNutrisiJenis.
                        getText(), PolaNutrisiPorsi.getText(), PolaTidurLama.
                        getText(), PolaTidurGangguan.getSelectedItem().
                                toString(), AktifitasSehari2.getSelectedItem().
                                toString(),
                        Aktifitas.getSelectedItem().toString(), Berjalan.
                        getSelectedItem().toString(), KeteranganBerjalan.
                                getText(), AlatAmbulasi.getSelectedItem().
                                toString(), EkstrimitasAtas.getSelectedItem().
                                toString(), KeteranganEkstrimitasAtas.getText(),
                        EkstrimitasBawah.getSelectedItem().toString(),
                        KeteranganEkstrimitasBawah.getText(),
                        KemampuanMenggenggam.getSelectedItem().toString(),
                        KeteranganKemampuanMenggenggam.getText(),
                        KemampuanKoordinasi.getSelectedItem().toString(),
                        KeteranganKemampuanKoordinasi.getText(),
                        KesimpulanGangguanFungsi.getSelectedItem().toString(),
                        KondisiPsikologis.getSelectedItem().toString(),
                        GangguanJiwa.getSelectedItem().toString(),
                        AdakahPerilaku.getSelectedItem().toString(),
                        KeteranganAdakahPerilaku.getText(),
                        HubunganAnggotaKeluarga.getSelectedItem().toString(),
                        TinggalDengan.getSelectedItem().toString(),
                        KeteranganTinggalDengan.getText(),
                        NilaiKepercayaan.getSelectedItem().toString(),
                        KeteranganNilaiKepercayaan.getText(), PendidikanPJ.
                        getSelectedItem().toString(), EdukasiPsikolgis.
                                getSelectedItem().toString(),
                        KeteranganEdukasiPsikologis.getText(), Wajib.
                        getSelectedItem().toString(),
                        KetHalanganLain.getText(), Toharah.getSelectedItem().
                        toString(), Sholat.getSelectedItem().toString(),
                        MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                        Nyeri.getSelectedItem().toString(),
                        Provokes.getSelectedItem().toString(), KetProvokes.
                        getText(),
                        Quality.getSelectedItem().toString(), KetQuality.
                        getText(), Lokasi.getText(), Menyebar.getSelectedItem().
                        toString(), SkalaNyeri.getSelectedItem().toString(),
                        Durasi.getText(), NyeriHilang.getSelectedItem().
                        toString(), KetNyeri.getText(), PadaDokter.
                        getSelectedItem().toString(),
                        KetPadaDokter.getText(), SkalaHumptyDumpty1.
                        getSelectedItem().toString(), NilaiHumptyDumpty1.
                                getText(), SkalaHumptyDumpty2.getSelectedItem().
                                toString(), NilaiHumptyDumpty2.getText(),
                        SkalaHumptyDumpty3.getSelectedItem().toString(),
                        NilaiHumptyDumpty3.getText(), SkalaHumptyDumpty4.
                        getSelectedItem().toString(), NilaiHumptyDumpty4.
                                getText(), SkalaHumptyDumpty5.getSelectedItem().
                                toString(), NilaiHumptyDumpty5.getText(),
                        SkalaHumptyDumpty6.getSelectedItem().toString(),
                        NilaiHumptyDumpty6.getText(), SkalaHumptyDumpty7.
                        getSelectedItem().toString(), NilaiHumptyDumpty7.
                                getText(), NilaiHumptyDumptyTotal.getText(),
                        SkalaResiko1.getSelectedItem().toString(), NilaiResiko1.
                        getText(), SkalaResiko2.getSelectedItem().toString(),
                        NilaiResiko2.getText(), SkalaResiko3.getSelectedItem().
                        toString(), NilaiResiko3.getText(), SkalaResiko4.
                        getSelectedItem().toString(), NilaiResiko4.getText(),
                        SkalaResiko5.getSelectedItem().toString(), NilaiResiko5.
                        getText(), SkalaResiko6.getSelectedItem().toString(),
                        NilaiResiko6.getText(), NilaiResikoTotal.getText(),
                        SkalaGizi1.getSelectedItem().toString(), NilaiGizi1.
                        getText(), SkalaGizi2.getSelectedItem().toString(),
                        NilaiGizi2.getText(), SkalaGizi3.getSelectedItem().
                        toString(), NilaiGizi3.getText(), SkalaGizi4.
                        getSelectedItem().toString(), NilaiGizi4.getText(),
                        NilaiGiziTotal.getText(), DiagnosaKhususGizi.
                        getSelectedItem().toString(),
                        KeteranganDiagnosaKhususGizi.getText(),
                        DiketahuiDietisen.getSelectedItem().toString(),
                        KeteranganDiketahuiDietisen.getText(), Kriteria1.
                        getSelectedItem().toString(), Kriteria2.
                                getSelectedItem().toString(), Kriteria3.
                                getSelectedItem().toString(), Kriteria4.
                                getSelectedItem().toString(),
                        pilih1, pilih2, pilih3, pilih4, pilih5, pilih6, pilih7,
                        pilih8, Rencana.getText(), KdPetugas.getText(),
                        KdPetugas2.getText(), KdDPJP.getText()
                    }) == true) {
                for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                    if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                            "true")) {
                        Sequel.menyimpan2(
                                "penilaian_awal_keperawatan_ranap_masalah_kritis",
                                "?,?,?", 3, new String[]{TNoRw.getText(),
                                    tbMasalahKeperawatan.getValueAt(i, 1).
                                            toString(), Valid.SetTgl(TglAsuhan.
                                            getSelectedItem() + "") + " " + TglAsuhan.
                                            getSelectedItem().toString().
                                            substring(11, 19)});
                    }
                }
                for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                    if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals(
                            "true")) {
                        Sequel.menyimpan2(
                                "penilaian_awal_keperawatan_ranap_rencana_kritis",
                                "?,?,?", 3, new String[]{TNoRw.getText(),
                                    tbRencanaKeperawatan.getValueAt(i, 1).
                                            toString(), Valid.SetTgl(TglAsuhan.
                                            getSelectedItem() + "") + " " + TglAsuhan.
                                            getSelectedItem().toString().
                                            substring(11, 19)});
                    }
                }
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Rencana, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (KdPetugas.getText().equals(tbObat.getValueAt(tbObat.
                        getSelectedRow(), 5).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }

}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRM.getText().trim().isEmpty()) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (KdPetugas.getText().trim().isEmpty() || NmPetugas.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Pengkaji 1");
        } else if (KdPetugas2.getText().trim().isEmpty() || NmPetugas2.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas2, "Pegkaji 2");
        } else if (KdDPJP.getText().trim().isEmpty() || NmDPJP.getText().trim().
                isEmpty()) {
            Valid.textKosong(BtnDPJP, "DPJP");
        } else if (RPS.getText().trim().isEmpty()) {
            Valid.textKosong(RPS, "Riwayat Penyakit Sekarang");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KdPetugas.getText().equals(tbObat.getValueAt(tbObat.
                            getSelectedRow(), 5).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            try {
                File g = new File("file2.css");
                try (BufferedWriter bg = new BufferedWriter(new FileWriter(g))) {
                    bg.write(
                            ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"
                            + ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    );
                }

                File f;
                BufferedWriter bw;

                ps = koneksi.prepareStatement(
                        "select penilaian_awal_keperawatan_ranap_kritis.no_rawat,penilaian_awal_keperawatan_ranap_kritis.tanggal,penilaian_awal_keperawatan_ranap_kritis.informasi,penilaian_awal_keperawatan_ranap_kritis.ket_informasi,penilaian_awal_keperawatan_ranap_kritis.tiba_diruang_rawat,"
                        + "penilaian_awal_keperawatan_ranap_kritis.kasus_trauma,penilaian_awal_keperawatan_ranap_kritis.cara_masuk,penilaian_awal_keperawatan_ranap_kritis.rps,penilaian_awal_keperawatan_ranap_kritis.rpd,penilaian_awal_keperawatan_ranap_kritis.rpk,penilaian_awal_keperawatan_ranap_kritis.rpo,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_kritis.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_kritis.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_kritis.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_kritis.riwayat_alergi,penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_narkoba,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_olahraga,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gcs,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_td,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_suhu,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_pola_nafas,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_volume_pernafasan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_irama_nafas,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_peistatik_usus,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_motorik,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_turgor,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_lainlain,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpakaian,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_porsi_makan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_kritis.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_aktifitas,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ambulasi,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kesimpulan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_kualitas,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_skala,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_diberitahukan_dokter,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai1,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala3,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai4,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala6,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala1,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai2,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala4,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai5,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala7,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai8,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala10,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai11,"
                        + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi1,penilaian_awal_keperawatan_ranap_kritis.nilai_gizi1,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi2,"
                        + "penilaian_awal_keperawatan_ranap_kritis.nilai_gizi2,penilaian_awal_keperawatan_ranap_kritis.nilai_total_gizi,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_ket_diagnosa_khusus,"
                        + "penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.rencana,penilaian_awal_keperawatan_ranap_kritis.nip1,"
                        + "penilaian_awal_keperawatan_ranap_kritis.nip2,penilaian_awal_keperawatan_ranap_kritis.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                        + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                        + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join penilaian_awal_keperawatan_ranap_kritis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_kritis.no_rawat "
                        + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_kritis.nip1=pengkaji1.nip "
                        + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_kritis.nip2=pengkaji2.nip "
                        + "inner join dokter on penilaian_awal_keperawatan_ranap_kritis.kd_dokter=dokter.kd_dokter "
                        + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                        + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                        + "penilaian_awal_keperawatan_ranap_kritis.tanggal between ? and ? "
                        + (TCari.getText().trim().isEmpty() ? "" : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_ranap_kritis.nip1 like ? or "
                        + "pengkaji1.nama like ? or penilaian_awal_keperawatan_ranap_kritis.kd_dokter like ? or dokter.nm_dokter like ?)")
                        + " order by penilaian_awal_keperawatan_ranap_kritis.tanggal");

                try {
                    ps.setString(1, Valid.
                            SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.
                            SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    if (!TCari.getText().isEmpty()) {
                        ps.setString(3, "%" + TCari.getText() + "%");
                        ps.setString(4, "%" + TCari.getText() + "%");
                        ps.setString(5, "%" + TCari.getText() + "%");
                        ps.setString(6, "%" + TCari.getText() + "%");
                        ps.setString(7, "%" + TCari.getText() + "%");
                        ps.setString(8, "%" + TCari.getText() + "%");
                        ps.setString(9, "%" + TCari.getText() + "%");
                    }
                    rs = ps.executeQuery();
                    pilihan = (String) JOptionPane.showInputDialog(null,
                            "Silahkan pilih laporan..!", "Pilihan Cetak",
                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{
                                "Laporan 1 (HTML)", "Laporan 2 (WPS)",
                                "Laporan 3 (CSV)"}, "Laporan 1 (HTML)");
                    switch (pilihan) {
                        case "Laporan 1 (HTML)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>No.Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.RM</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tgl.Lahir</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='25px'>J.K.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>NIP Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>NIP Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kode DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='117px'>Tgl.Asuhan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='78px'>Macam Kasus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anamnesis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Tiba Di Ruang Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Cara Masuk</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='220px'>Riwayat Penyakit Saat Ini</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Dahulu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penggunaan Obat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Pembedahan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Dirawat Di RS</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Alat Bantu Yang Dipakai</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='210px'>Dalam Keadaan Hamil/Sedang Menyusui</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Transfusi Darah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Alergi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='48px'>Merokok</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Batang/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>Alkohol</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Gelas/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='61px'>Obat Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Olah Raga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keadaan Umum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='64px'>GCS(E,V,M)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>TD(mmHg)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='74px'>Nadi(x/menit)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='67px'>RR(x/menit)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='52px'>Suhu(°C)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='52px'>SpO2(%)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>BB(Kg)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>TB(cm)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kepala</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Wajah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Leher</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kejang</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sensorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Pulsasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sirkulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='72px'>Denyut Nadi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='54px'>Retraksi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>Pola Nafas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='69px'>Suara Nafas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='97px'>Batuk & Sekresi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='75px'>Volume</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jenis Pernafasaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Irama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mulut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lidah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gigi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tenggorokan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Abdomen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Peistatik Usus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sensorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penglihatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Alat Bantu Penglihatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pendengaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Bicara</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Otot</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kulit</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna Kulit</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Turgor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Resiko Decubitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Oedema</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pergerakan Sendi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kekuatan Otot</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fraktur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Sendi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>x/</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Konsistensi BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>x/</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lain-lain BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mandi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Makan/Minum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Berpakaian</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Eliminasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Berpindah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Porsi Makan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi Makan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jenis Makanan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lama Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>a. Aktifitas Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>b. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>c. Aktifitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>d. Alat Ambulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>e. Ekstremitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>f. Ekstremitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>g. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>h. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>i. Kesimpulan Gangguan Fungsi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>a. Kondisi Psikologis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>b. Adakah Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>c. Gangguan Jiwa di Masa Lalu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>d. Hubungan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>e. Agama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>f. Tinggal Dengan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>g. Pekerjaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>h. Pembayaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>i. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>j. Bahasa Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>k. Pendidikan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>l. Pendidikan P.J.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>m. Edukasi Diberikan Kepada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penyebab Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kualitas Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lokasi Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Menyebar</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Waktu / Durasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Hilang Bila</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diberitahukan Pada Dokter</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>T.M.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 7</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 7</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 8</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 8</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 9</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 9</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 10</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 10</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 11</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 11</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>T.S.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skor 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skor 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Total Skor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pasien dengan diagnosis khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keterangan Diagnosa Khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sudah dibaca dan diketahui oleh Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jam Dibaca Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Rencana Keperawatan Lainnya</td>"
                                    + "</tr>"
                            );
                            while (rs.next()) {
                                htmlContent.append(
                                        "<tr class='isi'><td valign='top'>").
                                        append(rs.getString("no_rawat")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("no_rkm_medis")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nm_pasien")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("tgl_lahir")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("jk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nip1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pengkaji1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nip2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pengkaji2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("kd_dokter")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nm_dokter")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("tanggal")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("kasus_trauma")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("informasi")).append(", ").
                                        append(rs.getString("ket_informasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("tiba_diruang_rawat")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("cara_masuk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rps")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpd")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpo")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_pembedahan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_dirawat_dirs")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("alat_bantu_dipakai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_kehamilan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_kehamilan_perkiraan")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString("riwayat_tranfusi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_alergi")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_merokok")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_merokok_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_alkohol")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_alkohol_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_narkoba")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_olahraga")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_mental")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_keadaan_umum")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_gcs")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_td")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_nadi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_rr")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_suhu")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_spo2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_bb")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_tb")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_kepala")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_kepala_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_wajah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pemeriksaan_susunan_leher")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_kejang")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_kejang_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_sensorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_pulsasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_sirkulasi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_denyut_nadi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_retraksi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_pola_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_suara_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_batuk")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_volume_pernafasan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_jenis_pernafasan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_irama_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_mulut")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_mulut_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_lidah")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_lidah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_gigi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_gigi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_tenggorokan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_abdomen")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_abdomen_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_peistatik_usus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_anus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_sensorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_pengelihatan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_neurologi_pengelihatan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_alat_bantu_penglihatan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_motorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_pendengaran")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_bicara")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_neurologi_bicara_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_kekuatan_otot")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_kulit")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_warnakulit")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_turgor")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_dekubitas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_oedema")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_oedema_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_pergerakan_sendi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_kekauatan_otot")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_fraktur")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_fraktur_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_nyeri_sendi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_durasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_konsistensi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_warna")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_durasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_warna")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_lainlain")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_mandi")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pola_aktifitas_makanminum")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pola_aktifitas_berpakaian")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_eliminasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_berpindah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_nutrisi_porsi_makan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pola_nutrisi_frekuesi_makan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pola_nutrisi_jenis_makanan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_tidur_lama_tidur")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_tidur_gangguan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_kemampuan_sehari")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_berjalan")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_berjalan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_aktifitas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ambulasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_atas")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_bawah")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_menggenggam")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_menggenggam_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_koordinasi")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_koordinasi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_kesimpulan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_kondisi_psiko")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_psiko_perilaku")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_perilaku_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_gangguan_jiwa")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_hubungan_keluarga")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("agama")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_tinggal")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_tinggal_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pekerjaan")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("png_jawab")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_nilai_kepercayaan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_nilai_kepercayaan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nama_bahasa")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pnd")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_pendidikan_pj")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_edukasi_diberikan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_edukasi_diberikan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "penilaian_nyeri_penyebab")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_penyebab")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_kualitas")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_kualitas")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_lokasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_menyebar")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_skala")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_waktu")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_hilang")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_hilang")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_diberitahukan_dokter")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_jam_diberitahukan_dokter")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_totalnilai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala7")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai7")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala8")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai8")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala9")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai9")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala10")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai10")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala11")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai11")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_totalnilai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("skrining_gizi1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nilai_gizi1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("skrining_gizi2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nilai_gizi2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nilai_total_gizi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_diagnosa_khusus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_ket_diagnosa_khusus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_diketahui_dietisen")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_jam_diketahui_dietisen")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("rencana")).append(
                                        "</td></tr>");
                            }
                            f = new File("RMPenilaianAwalKeperawatanRanap.html");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write("<html>"
                                    + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                    + "<body>"
                                    + "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + htmlContent.toString()
                                    + "</table>"
                                    + "</body>"
                                    + "</html>"
                            );

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 2 (WPS)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>No.Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.RM</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tgl.Lahir</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='25px'>J.K.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>NIP Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>NIP Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kode DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='117px'>Tgl.Asuhan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='78px'>Macam Kasus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anamnesis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Tiba Di Ruang Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Cara Masuk</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='220px'>Riwayat Penyakit Saat Ini</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Dahulu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penggunaan Obat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Pembedahan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Dirawat Di RS</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Alat Bantu Yang Dipakai</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='210px'>Dalam Keadaan Hamil/Sedang Menyusui</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Transfusi Darah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Alergi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='48px'>Merokok</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Batang/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>Alkohol</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Gelas/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='61px'>Obat Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Olah Raga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keadaan Umum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='64px'>GCS(E,V,M)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>TD(mmHg)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='74px'>Nadi(x/menit)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='67px'>RR(x/menit)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='52px'>Suhu(°C)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='52px'>SpO2(%)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>BB(Kg)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='44px'>TB(cm)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kepala</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Wajah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Leher</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kejang</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sensorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Pulsasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sirkulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='72px'>Denyut Nadi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='54px'>Retraksi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>Pola Nafas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='69px'>Suara Nafas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='97px'>Batuk & Sekresi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='75px'>Volume</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jenis Pernafasaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Irama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mulut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lidah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gigi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tenggorokan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Abdomen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Peistatik Usus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sensorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penglihatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Alat Bantu Penglihatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pendengaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Bicara</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Otot</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kulit</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna Kulit</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Turgor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Resiko Decubitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Oedema</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pergerakan Sendi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kekuatan Otot</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fraktur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Sendi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>x/</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Konsistensi BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna BAB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>x/</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Warna BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lain-lain BAK</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mandi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Makan/Minum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Berpakaian</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Eliminasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Berpindah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Porsi Makan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Frekuensi Makan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jenis Makanan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lama Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Tidur</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>a. Aktifitas Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>b. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>c. Aktifitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>d. Alat Ambulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>e. Ekstremitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>f. Ekstremitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>g. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>h. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>i. Kesimpulan Gangguan Fungsi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>a. Kondisi Psikologis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>b. Adakah Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>c. Gangguan Jiwa di Masa Lalu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>d. Hubungan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>e. Agama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>f. Tinggal Dengan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>g. Pekerjaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>h. Pembayaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>i. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>j. Bahasa Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>k. Pendidikan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>l. Pendidikan P.J.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>m. Edukasi Diberikan Kepada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penyebab Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kualitas Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Lokasi Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Menyebar</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Waktu / Durasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nyeri Hilang Bila</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diberitahukan Pada Dokter</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Morse 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.M. 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>T.M.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 7</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 7</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 8</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 8</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 9</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 9</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 10</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 10</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skala Sydney 11</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>N.S. 11</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>T.S.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skor 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Skor 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Total Skor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pasien dengan diagnosis khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keterangan Diagnosa Khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sudah dibaca dan diketahui oleh Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Jam Dibaca Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center'>Rencana Keperawatan Lainnya</td>"
                                    + "</tr>"
                            );
                            while (rs.next()) {
                                htmlContent.append(
                                        "<tr class='isi'><td valign='top'>").
                                        append(rs.getString("no_rawat")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("no_rkm_medis")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nm_pasien")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("tgl_lahir")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("jk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nip1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pengkaji1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nip2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pengkaji2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("kd_dokter")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nm_dokter")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("tanggal")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("kasus_trauma")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("informasi")).append(", ").
                                        append(rs.getString("ket_informasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("tiba_diruang_rawat")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("cara_masuk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rps")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpd")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpk")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("rpo")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_pembedahan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_dirawat_dirs")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("alat_bantu_dipakai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_kehamilan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_kehamilan_perkiraan")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString("riwayat_tranfusi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_alergi")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_merokok")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_merokok_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_alkohol")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_alkohol_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_narkoba")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("riwayat_olahraga")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_mental")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_keadaan_umum")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_gcs")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_td")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_nadi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_rr")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_suhu")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_spo2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pemeriksaan_bb")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pemeriksaan_tb")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_kepala")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_kepala_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_wajah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pemeriksaan_susunan_leher")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_kejang")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_kejang_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_susunan_sensorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_pulsasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_sirkulasi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_kardiovaskuler_denyut_nadi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_retraksi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_pola_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_suara_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_batuk")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_volume_pernafasan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_jenis_pernafasan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_respirasi_irama_nafas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_mulut")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_mulut_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_lidah")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_lidah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_gigi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_gigi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_tenggorokan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_abdomen")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_abdomen_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_peistatik_usus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_gastrointestinal_anus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_sensorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_pengelihatan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_neurologi_pengelihatan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_alat_bantu_penglihatan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_motorik")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_pendengaran")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_bicara")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_neurologi_bicara_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_neurologi_kekuatan_otot")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_kulit")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_warnakulit")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_turgor")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_integument_dekubitas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_oedema")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_oedema_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_pergerakan_sendi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_kekauatan_otot")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_fraktur")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_fraktur_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_muskuloskletal_nyeri_sendi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_durasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_konsistensi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bab_warna")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_jumlah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_durasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_warna")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pemeriksaan_eliminasi_bak_lainlain")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_mandi")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pola_aktifitas_makanminum")).
                                        append("</td><td valign='top'>").append(
                                        rs.
                                                getString(
                                                        "pola_aktifitas_berpakaian")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_eliminasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_aktifitas_berpindah")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_nutrisi_porsi_makan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pola_nutrisi_frekuesi_makan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pola_nutrisi_jenis_makanan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_tidur_lama_tidur")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pola_tidur_gangguan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_kemampuan_sehari")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_berjalan")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_berjalan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_aktifitas")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ambulasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_atas")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_bawah")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_menggenggam")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_menggenggam_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_koordinasi")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_koordinasi_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "pengkajian_fungsi_kesimpulan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_kondisi_psiko")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("riwayat_psiko_perilaku")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_perilaku_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_gangguan_jiwa")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_hubungan_keluarga")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("agama")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_tinggal")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_tinggal_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("pekerjaan")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("png_jawab")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_nilai_kepercayaan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_nilai_kepercayaan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nama_bahasa")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("pnd")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "riwayat_psiko_pendidikan_pj")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "riwayat_psiko_edukasi_diberikan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_edukasi_diberikan_keterangan")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString(
                                                        "penilaian_nyeri_penyebab")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_penyebab")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_kualitas")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_kualitas")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_lokasi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_menyebar")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_skala")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_waktu")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("penilaian_nyeri_hilang")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_hilang")).append(
                                                "</td><td valign='top'>").
                                        append(rs.getString(
                                                "penilaian_nyeri_diberitahukan_dokter")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_jam_diberitahukan_dokter")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_skala6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_nilai6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhmorse_totalnilai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai3")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai4")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai5")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai6")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala7")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai7")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala8")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai8")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala9")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai9")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala10")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai10")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_skala11")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_nilai11")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "penilaian_jatuhsydney_totalnilai")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("skrining_gizi1")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nilai_gizi1")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("skrining_gizi2")).append(
                                        "</td><td valign='top'>").append(rs.
                                                getString("nilai_gizi2")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("nilai_total_gizi")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_diagnosa_khusus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_ket_diagnosa_khusus")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_diketahui_dietisen")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString(
                                                "skrining_gizi_jam_diketahui_dietisen")).
                                        append("</td><td valign='top'>").append(
                                        rs.getString("rencana")).append(
                                        "</td></tr>");
                            }
                            f = new File("RMPenilaianAwalKeperawatanRanap.wps");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write("<html>"
                                    + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                    + "<body>"
                                    + "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + htmlContent.toString()
                                    + "</table>"
                                    + "</body>"
                                    + "</html>"
                            );

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 3 (CSV)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "\"No.Rawat\";\"No.RM\";\"Nama Pasien\";\"Tgl.Lahir\";\"J.K.\";\"NIP Pengkaji 1\";\"Nama Pengkaji 1\";\"NIP Pengkaji 2\";\"Nama Pengkaji 2\";\"Kode DPJP\";\"Nama DPJP\";\"Tgl.Asuhan\";\"Macam Kasus\";\"Anamnesis\";\"Tiba Di Ruang Rawat\";\"Cara Masuk\";\"Riwayat Penyakit Saat Ini\";\"Riwayat Penyakit Dahulu\";\"Riwayat Penyakit Keluarga\";\"Riwayat Penggunaan Obat\";\"Riwayat Pembedahan\";\"Riwayat Dirawat Di RS\";\"Alat Bantu Yang Dipakai\";\"Dalam Keadaan Hamil/Sedang Menyusui\";\"Riwayat Transfusi Darah\";\"Riwayat Alergi\";\"Merokok\";\"Batang/Hari\";\"Alkohol\";\"Gelas/Hari\";\"Obat Tidur\";\"Olah Raga\";\"Kesadaran Mental\";\"Keadaan Umum\";\"GCS(E,V,M)\";\"TD(mmHg)\";\"Nadi(x/menit)\";\"RR(x/menit)\";\"Suhu(°C)\";\"SpO2(%)\";\"BB(Kg)\";\"TB(cm)\";\"Kepala\";\"Wajah\";\"Leher\";\"Kejang\";\"Sensorik\";\"Pulsasi\";\"Sirkulasi\";\"Denyut Nadi\";\"Retraksi\";\"Pola Nafas\";\"Suara Nafas\";\"Batuk & Sekresi\";\"Volume\";\"Jenis Pernafasaan\";\"Irama\";\"Mulut\";\"Lidah\";\"Gigi\";\"Tenggorokan\";\"Abdomen\";\"Peistatik Usus\";\"Anus\";\"Sensorik\";\"Penglihatan\";\"Alat Bantu Penglihatan\";\"Motorik\";\"Pendengaran\";\"Bicara\";\"Otot\";\"Kulit\";\"Warna Kulit\";\"Turgor\";\"Resiko Decubitas\";\"Oedema\";\"Pergerakan Sendi\";\"Kekuatan Otot\";\"Fraktur\";\"Nyeri Sendi\";\"Frekuensi BAB\";\"x/\";\"Konsistensi BAB\";\"Warna BAB\";\"Frekuensi BAK\";\"x/\";\"Warna BAK\";\"Lain-lain BAK\";\"Mandi\";\"Makan/Minum\";\"Berpakaian\";\"Eliminasi\";\"Berpindah\";\"Porsi Makan\";\"Frekuensi Makan\";\"Jenis Makanan\";\"Lama Tidur\";\"Gangguan Tidur\";\"a. Aktifitas Sehari-hari\";\"b. Berjalan\";\"c. Aktifitas\";\"d. Alat Ambulasi\";\"e. Ekstremitas Atas\";\"f. Ekstremitas Bawah\";\"g. Kemampuan Menggenggam\";\"h. Kemampuan Koordinasi\";\"i. Kesimpulan Gangguan Fungsi\";\"a. Kondisi Psikologis\";\"b. Adakah Perilaku\";\"c. Gangguan Jiwa di Masa Lalu\";\"d. Hubungan Pasien\";\"e. Agama\";\"f. Tinggal Dengan\";\"g. Pekerjaan\";\"h. Pembayaran\";\"i. Nilai-nilai Kepercayaan\";\"j. Bahasa Sehari-hari\";\"k. Pendidikan Pasien\";\"l. Pendidikan P.J.\";\"m. Edukasi Diberikan Kepada\";\"Nyeri\";\"Penyebab Nyeri\";\"Kualitas Nyeri\";\"Lokasi Nyeri\";\"Nyeri Menyebar\";\"Skala Nyeri\";\"Waktu / Durasi\";\"Nyeri Hilang Bila\";\"Diberitahukan Pada Dokter\";\"Skala Morse 1\";\"N.M. 1\";\"Skala Morse 2\";\"N.M. 2\";\"Skala Morse 3\";\"N.M. 3\";\"Skala Morse 4\";\"N.M. 4\";\"Skala Morse 5\";\"N.M. 5\";\"Skala Morse 6\";\"N.M. 6\";\"T.M.\";\"Skala Sydney 1\";\"N.S. 1\";\"Skala Sydney 2\";\"N.S. 2\";\"Skala Sydney 3\";\"N.S. 3\";\"Skala Sydney 4\";\"N.S. 4\";\"Skala Sydney 5\";\"N.S. 5\";\"Skala Sydney 6\";\"N.S. 6\";\"Skala Sydney 7\";\"N.S. 7\";\"Skala Sydney 8\";\"N.S. 8\";\"Skala Sydney 9\";\"N.S. 9\";\"Skala Sydney 10\";\"N.S. 10\";\"Skala Sydney 11\";\"N.S. 11\";\"T.S.\";\"1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?\";\"Skor 1\";\"2. Apakah asupan makan berkurang karena tidak nafsu makan ?\";\"Skor 2\";\"Total Skor\";\"Pasien dengan diagnosis khusus\";\"Keterangan Diagnosa Khusus\";\"Sudah dibaca dan diketahui oleh Dietisen\";\"Jam Dibaca Dietisen\";\"Rencana Keperawatan Lainnya\"\n"
                            );
                            while (rs.next()) {
                                htmlContent.append("\"").append(rs.getString(
                                        "no_rawat")).append("\";\"").append(rs.
                                        getString("no_rkm_medis")).append(
                                        "\";\"").append(rs.
                                                getString("nm_pasien")).append(
                                        "\";\"").append(rs.
                                                getString("tgl_lahir")).append(
                                        "\";\"").append(rs.getString("jk")).
                                        append("\";\"").append(rs.getString(
                                        "nip1")).append("\";\"").append(rs.
                                        getString("pengkaji1")).append("\";\"").
                                        append(rs.getString("nip2")).append(
                                        "\";\"").append(rs.
                                                getString("pengkaji2")).append(
                                        "\";\"").append(rs.
                                                getString("kd_dokter")).append(
                                        "\";\"").append(rs.
                                                getString("nm_dokter")).append(
                                        "\";\"").append(rs.getString("tanggal")).
                                        append("\";\"").append(rs.getString(
                                        "kasus_trauma")).append("\";\"").append(
                                        rs.getString("informasi")).append(", ").
                                        append(rs.getString("ket_informasi")).
                                        append("\";\"").append(rs.getString(
                                        "tiba_diruang_rawat")).append("\";\"").
                                        append(rs.getString("cara_masuk")).
                                        append("\";\"").append(rs.getString(
                                        "rps")).append("\";\"").append(rs.
                                        getString("rpd")).append("\";\"").
                                        append(rs.getString("rpk")).append(
                                        "\";\"").append(rs.getString("rpo")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_pembedahan")).append("\";\"").
                                        append(rs.getString(
                                                "riwayat_dirawat_dirs")).append(
                                                "\";\"").append(rs.getString(
                                                "alat_bantu_dipakai")).append(
                                                "\";\"").append(rs.getString(
                                                "riwayat_kehamilan")).append(
                                                ", ").append(rs.getString(
                                                "riwayat_kehamilan_perkiraan")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_tranfusi")).append("\";\"").
                                        append(rs.getString("riwayat_alergi")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_merokok")).append("\";\"").
                                        append(rs.getString(
                                                "riwayat_merokok_jumlah")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_alkohol")).append("\";\"").
                                        append(rs.getString(
                                                "riwayat_alkohol_jumlah")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_narkoba")).append("\";\"").
                                        append(rs.getString("riwayat_olahraga")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_mental")).append("\";\"").
                                        append(rs.getString(
                                                "pemeriksaan_keadaan_umum")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gcs")).append("\";\"").
                                        append(rs.getString("pemeriksaan_td")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_nadi")).append("\";\"").
                                        append(rs.getString("pemeriksaan_rr")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_suhu")).append("\";\"").
                                        append(rs.getString("pemeriksaan_spo2")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_bb")).append("\";\"").
                                        append(rs.getString("pemeriksaan_tb")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_susunan_kepala")).append(
                                                ", ").append(rs.getString(
                                                "pemeriksaan_susunan_kepala_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_susunan_wajah")).append(
                                                ", ").append(rs.getString(
                                                "pemeriksaan_susunan_wajah_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_susunan_leher")).append(
                                                "\";\"").append(rs.getString(
                                                "pemeriksaan_susunan_kejang")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_susunan_kejang_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_susunan_sensorik")).append(
                                                "\";\"").append(rs.getString(
                                                "pemeriksaan_kardiovaskuler_pulsasi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_kardiovaskuler_sirkulasi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_kardiovaskuler_denyut_nadi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_retraksi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_pola_nafas")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_suara_nafas")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_batuk")).append(
                                                "\";\"").append(rs.getString(
                                                "pemeriksaan_respirasi_volume_pernafasan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_jenis_pernafasan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_respirasi_irama_nafas")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_mulut")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_mulut_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_lidah")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_lidah_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_gigi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_gigi_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_tenggorokan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_abdomen")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_abdomen_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_peistatik_usus")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_gastrointestinal_anus")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_sensorik")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_pengelihatan")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_neurologi_pengelihatan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_alat_bantu_penglihatan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_motorik")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_pendengaran")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_bicara")).append(
                                                ", ").append(rs.getString(
                                                "pemeriksaan_neurologi_bicara_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_neurologi_kekuatan_otot")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_integument_kulit")).append(
                                                "\";\"").append(rs.getString(
                                                "pemeriksaan_integument_warnakulit")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_integument_turgor")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_integument_dekubitas")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_oedema")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_oedema_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_pergerakan_sendi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_kekauatan_otot")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_fraktur")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_fraktur_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_nyeri_sendi")).
                                        append(", ").append(rs.getString(
                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bab_frekuensi_jumlah")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bab_frekuensi_durasi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bab_konsistensi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bab_warna")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bak_frekuensi_jumlah")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bak_frekuensi_durasi")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bak_warna")).
                                        append("\";\"").append(rs.getString(
                                        "pemeriksaan_eliminasi_bak_lainlain")).
                                        append("\";\"").append(rs.getString(
                                        "pola_aktifitas_mandi")).append("\";\"").
                                        append(rs.getString(
                                                "pola_aktifitas_makanminum")).
                                        append("\";\"").append(rs.getString(
                                        "pola_aktifitas_berpakaian")).append(
                                                "\";\"").append(rs.getString(
                                                "pola_aktifitas_eliminasi")).
                                        append("\";\"").append(rs.getString(
                                        "pola_aktifitas_berpindah")).append(
                                                "\";\"").append(rs.getString(
                                                "pola_nutrisi_porsi_makan")).
                                        append("\";\"").append(rs.getString(
                                        "pola_nutrisi_frekuesi_makan")).append(
                                                "\";\"").append(rs.getString(
                                                "pola_nutrisi_jenis_makanan")).
                                        append("\";\"").append(rs.getString(
                                        "pola_tidur_lama_tidur")).
                                        append("\";\"").append(rs.getString(
                                        "pola_tidur_gangguan")).append("\";\"").
                                        append(rs.getString(
                                                "pengkajian_fungsi_kemampuan_sehari")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_berjalan")).append(
                                                ", ").append(rs.getString(
                                                "pengkajian_fungsi_berjalan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_aktifitas")).append(
                                                "\";\"").append(rs.getString(
                                                "pengkajian_fungsi_ambulasi")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_atas")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_bawah")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_menggenggam")).
                                        append(", ").append(rs.getString(
                                        "pengkajian_fungsi_menggenggam_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_koordinasi")).append(
                                                ", ").append(rs.getString(
                                                "pengkajian_fungsi_koordinasi_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pengkajian_fungsi_kesimpulan")).append(
                                                "\";\"").append(rs.getString(
                                                "riwayat_psiko_kondisi_psiko")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_psiko_perilaku")).append(", ").
                                        append(rs.getString(
                                                "riwayat_psiko_perilaku_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_psiko_gangguan_jiwa")).append(
                                                "\";\"").append(rs.getString(
                                                "riwayat_psiko_hubungan_keluarga")).
                                        append("\";\"").append(rs.getString(
                                        "agama")).append("\";\"").append(rs.
                                        getString("riwayat_psiko_tinggal")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_tinggal_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "pekerjaan")).append("\";\"").append(rs.
                                        getString("png_jawab")).append("\";\"").
                                        append(rs.getString(
                                                "riwayat_psiko_nilai_kepercayaan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_nilai_kepercayaan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "nama_bahasa")).append("\";\"").append(
                                        rs.getString("pnd")).append("\";\"").
                                        append(rs.getString(
                                                "riwayat_psiko_pendidikan_pj")).
                                        append("\";\"").append(rs.getString(
                                        "riwayat_psiko_edukasi_diberikan")).
                                        append(", ").append(rs.getString(
                                        "riwayat_psiko_edukasi_diberikan_keterangan")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_nyeri")).append("\";\"").
                                        append(rs.getString(
                                                "penilaian_nyeri_penyebab")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_penyebab")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_nyeri_kualitas")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_ket_kualitas")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_nyeri_lokasi")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_nyeri_menyebar")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_nyeri_skala")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_nyeri_waktu")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_nyeri_hilang")).append(", ").
                                        append(rs.getString(
                                                "penilaian_nyeri_ket_hilang")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_nyeri_diberitahukan_dokter")).
                                        append(", ").append(rs.getString(
                                        "penilaian_nyeri_jam_diberitahukan_dokter")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala1")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai1")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala2")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai2")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala3")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai3")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala4")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai4")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala5")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai5")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_skala6")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhmorse_nilai6")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhmorse_totalnilai")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala1")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai1")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala2")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai2")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala3")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai3")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala4")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai4")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala5")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai5")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala6")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai6")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala7")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai7")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala8")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai8")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala9")).append(
                                                "\";\"").append(rs.getString(
                                                "penilaian_jatuhsydney_nilai9")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala10")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_nilai10")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_skala11")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_nilai11")).
                                        append("\";\"").append(rs.getString(
                                        "penilaian_jatuhsydney_totalnilai")).
                                        append("\";\"").append(rs.getString(
                                        "skrining_gizi1")).append("\";\"").
                                        append(rs.getString("nilai_gizi1")).
                                        append("\";\"").append(rs.getString(
                                        "skrining_gizi2")).append("\";\"").
                                        append(rs.getString("nilai_gizi2")).
                                        append("\";\"").append(rs.getString(
                                        "nilai_total_gizi")).append("\";\"").
                                        append(rs.getString(
                                                "skrining_gizi_diagnosa_khusus")).
                                        append("\";\"").append(rs.getString(
                                        "skrining_gizi_ket_diagnosa_khusus")).
                                        append("\";\"").append(rs.getString(
                                        "skrining_gizi_diketahui_dietisen")).
                                        append("\";\"").append(rs.getString(
                                        "skrining_gizi_jam_diketahui_dietisen")).
                                        append("\";\"").append(rs.getString(
                                        "rencana")).append("\"\n");
                            }
                            f = new File("RMPenilaianAwalKeperawatanRanap.csv");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write(htmlContent.toString());

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                    }
                } catch (HeadlessException | IOException | SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (IOException | SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbObat.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if (Valid.daysOld("./cache/masalahkeperawatan.iyem") < 8) {
                tampilMasalah2();
            } else {
                tampilMasalah();
            }
        } catch (Exception e) {
        }

        try {
            if (Valid.daysOld("./cache/rencanakeperawatan.iyem") >= 7) {
                tampilRencana();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            isMenu();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            param.put("nyeri", Sequel.cariGambar(
                    "select gambar.nyeri from gambar"));
            finger = Sequel.cariIsi(
                    "select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            param.put("finger",
                    "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.
                    getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 6).toString() + "\nID " + (finger.
                    isEmpty() ? tbObat.getValueAt(tbObat.getSelectedRow(), 5).
                                    toString() : finger) + "\n" + Valid.SetTgl3(
                            tbObat.getValueAt(tbObat.getSelectedRow(), 11).
                                    toString()));

            try {
                masalahkeperawatan = "";
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_kritis on penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_kritis.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        masalahkeperawatan = rs.getString("nama_masalah") + ", " + masalahkeperawatan;
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            param.put("masalah", masalahkeperawatan);
            try {
                masalahkeperawatan = "";
                ps = koneksi.prepareStatement(
                        "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_kritis on penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_kritis.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        masalahkeperawatan = rs.getString("rencana_keperawatan") + ", " + masalahkeperawatan;
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            param.put("rencana", masalahkeperawatan);
            Valid.MyReportqry("rptCetakPenilaianAwalKeperawatanRanap.jasper",
                    "report",
                    "::[ Laporan Penilaian Awal Keperawatan Rawat Inap ]::",
                    "select penilaian_awal_keperawatan_ranap_kritis.no_rawat,penilaian_awal_keperawatan_ranap_kritis.tanggal,penilaian_awal_keperawatan_ranap_kritis.informasi,penilaian_awal_keperawatan_ranap_kritis.ket_informasi,penilaian_awal_keperawatan_ranap_kritis.tiba_diruang_rawat,"
                    + "penilaian_awal_keperawatan_ranap_kritis.kasus_trauma,penilaian_awal_keperawatan_ranap_kritis.cara_masuk,penilaian_awal_keperawatan_ranap_kritis.rps,penilaian_awal_keperawatan_ranap_kritis.rpd,penilaian_awal_keperawatan_ranap_kritis.rpk,penilaian_awal_keperawatan_ranap_kritis.rpo,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_kritis.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_kritis.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_kritis.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_kritis.riwayat_alergi,penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_narkoba,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_olahraga,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gcs,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_td,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_suhu,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_pola_nafas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_volume_pernafasan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_irama_nafas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_peistatik_usus,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_motorik,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_turgor,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_lainlain,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpakaian,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_porsi_makan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_kritis.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_aktifitas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ambulasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kesimpulan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_kualitas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_skala,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala3,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai4,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala6,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai2,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala4,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai5,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala7,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai8,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala10,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai11,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi1,penilaian_awal_keperawatan_ranap_kritis.nilai_gizi1,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi2,"
                    + "penilaian_awal_keperawatan_ranap_kritis.nilai_gizi2,penilaian_awal_keperawatan_ranap_kritis.nilai_total_gizi,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_ket_diagnosa_khusus,"
                    + "penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.rencana,penilaian_awal_keperawatan_ranap_kritis.nip1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.nip2,penilaian_awal_keperawatan_ranap_kritis.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_kritis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_kritis.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_kritis.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_kritis.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_kritis.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);

            Valid.MyReportqry("rptCetakPenilaianAwalKeperawatanRanap2.jasper",
                    "report",
                    "::[ Laporan Penilaian Awal Keperawatan Rawat Inap ]::",
                    "select penilaian_awal_keperawatan_ranap_kritis.no_rawat,penilaian_awal_keperawatan_ranap_kritis.tanggal,penilaian_awal_keperawatan_ranap_kritis.informasi,penilaian_awal_keperawatan_ranap_kritis.ket_informasi,penilaian_awal_keperawatan_ranap_kritis.tiba_diruang_rawat,"
                    + "penilaian_awal_keperawatan_ranap_kritis.kasus_trauma,penilaian_awal_keperawatan_ranap_kritis.cara_masuk,penilaian_awal_keperawatan_ranap_kritis.rps,penilaian_awal_keperawatan_ranap_kritis.rpd,penilaian_awal_keperawatan_ranap_kritis.rpk,penilaian_awal_keperawatan_ranap_kritis.rpo,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_kritis.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_kritis.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_kritis.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_kritis.riwayat_alergi,penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol,penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_kritis.riwayat_narkoba,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_olahraga,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gcs,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_td,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_suhu,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_wajah_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_kejang_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_pola_nafas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_volume_pernafasan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_irama_nafas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_mulut_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_peistatik_usus,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pengelihatan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_motorik,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_turgor,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_eliminasi_bak_lainlain,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpakaian,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_porsi_makan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_kritis.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_aktifitas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ambulasi,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kesimpulan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_kualitas,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_skala,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala3,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai4,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala6,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai2,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala4,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai5,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala7,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai8,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala10,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_nilai11,"
                    + "penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi1,penilaian_awal_keperawatan_ranap_kritis.nilai_gizi1,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi2,"
                    + "penilaian_awal_keperawatan_ranap_kritis.nilai_gizi2,penilaian_awal_keperawatan_ranap_kritis.nilai_total_gizi,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_ket_diagnosa_khusus,"
                    + "penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_kritis.rencana,penilaian_awal_keperawatan_ranap_kritis.nip1,"
                    + "penilaian_awal_keperawatan_ranap_kritis.nip2,penilaian_awal_keperawatan_ranap_kritis.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_kritis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_kritis.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_kritis.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_kritis.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_kritis.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void DetailRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetailRencanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetailRencanaKeyPressed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
            tbRencanaKeperawatan.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
            tbRencanaKeperawatan.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah2(evt, CaraMasuk, RPD);
    }
//GEN-FIRST:event_KeluhanUtamaKeyPressed
		private void TCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeluhanUtamaKeyPressed
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        tampilRencana2();
                    } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                            getKeyCode() == KeyEvent.VK_TAB)) {//GEN-FIRST:event_TCariRencanaKeyPressed
                        BtnCariRencana.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                        TCariMasalah.requestFocus();
                    }
  }//GEN-LAST:event_TCariRencanaKeyPressed

    private void BtnCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilRencana2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {//GEN-FIRST:event_BtnCariRencanaKeyPressed
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCariRencana.requestFocus();
        }
  }//GEN-LAST:event_BtnCariRencanaKeyPressed

    private void BtnCariRencanaActionPerformed(java.awt.event.ActionEvent evt) {
        tampilRencana2();
    }
//GEN-FIRST:event_BtnCariRencanaActionPerformed
		private void BtnAllRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BtnCariRencanaActionPerformed
                    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                        BtnAllRencanaActionPerformed(null);
                    } else {
          Valid.pindah(evt, BtnCariRencana, TCariRencana);//GEN-FIRST:event_BtnAllRencanaKeyPressed
                    }
  }//GEN-LAST:event_BtnAllRencanaKeyPressed

    private void BtnAllRencanaActionPerformed(java.awt.event.ActionEvent evt) {
        TCariRencana.setText("");
        tampilRencana();
        tampilRencana2();
  }//GEN-FIRST:event_BtnAllRencanaActionPerformed
//GEN-LAST:event_BtnAllRencanaActionPerformed
    private void BtnTambahRencanaActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterRencanaKeperawatan form = new MasterRencanaKeperawatan(null, false);
        form.isCek();
      form.setSize(internalFrame1.getWidth() - 20,//GEN-FIRST:event_BtnTambahRencanaActionPerformed
              internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
  }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {//GEN-FIRST:event_TCariMasalahKeyPressed
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            KeteranganDiketahuiDietisen.requestFocus();
        }
  }//GEN-LAST:event_TCariMasalahKeyPressed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            KeteranganDiketahuiDietisen.requestFocus();
        }
  }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {
        tampilMasalah2();
    }
//GEN-FIRST:event_BtnCariMasalahActionPerformed
		private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BtnCariMasalahActionPerformed
                    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                        BtnAllMasalahActionPerformed(null);
                    } else {
          Valid.pindah(evt, BtnCariMasalah, TCariMasalah);//GEN-FIRST:event_BtnAllMasalahKeyPressed
                    }
  }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {
        TCari.setText("");
        tampilMasalah();
    }
//GEN-FIRST:event_BtnAllMasalahActionPerformed
	private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_BtnAllMasalahActionPerformed
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            MasterMasalahKeperawatan form = new MasterMasalahKeperawatan(null, false);
            form.isCek();
      form.setSize(internalFrame1.getWidth() - 20,//GEN-FIRST:event_BtnTambahMasalahActionPerformed
              internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
  }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah2(evt, TCariMasalah, BtnSimpan);
    }
//GEN-FIRST:event_RencanaKeyPressed
		private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RencanaKeyPressed
                    if (tabModeMasalah.getRowCount() != 0) {
                        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
                                || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
              try {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
                                tampilRencana2();
                            } catch (java.lang.NullPointerException e) {
                            }
                        }
                    }
  }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {
        if (tabModeMasalah.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCariMasalah.setText("");
              TCariMasalah.requestFocus();//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
            }
        }
  }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {
        if (tabModeMasalah.getRowCount() != 0) {
            try {
                tampilRencana2();
          } catch (java.lang.NullPointerException e) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
            }
        }
  }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void KeteranganDiketahuiDietisenKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, DiketahuiDietisen, TCariMasalah);
    }
//GEN-FIRST:event_KeteranganDiketahuiDietisenKeyPressed
		private void DiketahuiDietisenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganDiketahuiDietisenKeyPressed
        Valid.pindah(evt, KeteranganDiagnosaKhususGizi, KeteranganDiketahuiDietisen);
    }
//GEN-FIRST:event_DiketahuiDietisenKeyPressed
	private void KeteranganDiagnosaKhususGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_DiketahuiDietisenKeyPressed
        Valid.pindah(evt, DiagnosaKhususGizi, DiketahuiDietisen);
    }
//GEN-FIRST:event_KeteranganDiagnosaKhususGiziKeyPressed
		private void DiagnosaKhususGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganDiagnosaKhususGiziKeyPressed
        Valid.pindah(evt, SkalaGizi2, KeteranganDiagnosaKhususGizi);
    }
//GEN-FIRST:event_DiagnosaKhususGiziKeyPressed
		private void SkalaGizi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_DiagnosaKhususGiziKeyPressed
        Valid.pindah(evt, SkalaGizi1, DiagnosaKhususGizi);
    }
//GEN-FIRST:event_SkalaGizi2KeyPressed
		private void SkalaGizi2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaGizi2KeyPressed
                    if (SkalaGizi2.getSelectedIndex() == 0) {
                        NilaiGizi2.setText("0");
                    } else if (SkalaGizi2.getSelectedIndex() == 1) {
          NilaiGizi2.setText("1");//GEN-FIRST:event_SkalaGizi2ItemStateChanged
                    }
                    isTotalGizi();
  }//GEN-LAST:event_SkalaGizi2ItemStateChanged

    private void SkalaGizi1KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaGizi1KeyPressed
  }//GEN-LAST:event_SkalaGizi1KeyPressed

    private void SkalaGizi1ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaGizi1.getSelectedIndex() == 0) {
            NilaiGizi1.setText("0");
        } else if (SkalaGizi1.getSelectedIndex() == 1) {
          NilaiGizi1.setText("2");//GEN-FIRST:event_SkalaGizi1ItemStateChanged
        } else if (SkalaGizi1.getSelectedIndex() == 2) {
            NilaiGizi1.setText("1");
        } else if (SkalaGizi1.getSelectedIndex() == 3) {
            NilaiGizi1.setText("2");
        } else if (SkalaGizi1.getSelectedIndex() == 4) {
            NilaiGizi1.setText("3");
        } else if (SkalaGizi1.getSelectedIndex() == 5) {
            NilaiGizi1.setText("4");
        }
        isTotalGizi();
  }//GEN-LAST:event_SkalaGizi1ItemStateChanged

    private void SkalaResiko6KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaResiko6KeyPressed
  }//GEN-LAST:event_SkalaResiko6KeyPressed

    private void SkalaResiko6ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaResiko6.getSelectedIndex() == 0) {
            NilaiResiko6.setText("");
        } else if (SkalaResiko6.getSelectedIndex() == 1) {
          NilaiResiko6.setText("0");//GEN-FIRST:event_SkalaResiko6ItemStateChanged
        } else {
            NilaiResiko6.setText("15");
        }
        isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko6ItemStateChanged

    private void SkalaResiko5KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaResiko4, SkalaResiko6);
    }
//GEN-FIRST:event_SkalaResiko5KeyPressed
		private void SkalaResiko5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaResiko5KeyPressed
                    if (SkalaResiko5.getSelectedIndex() == 0) {
                        NilaiResiko5.setText("");
                    } else if (SkalaResiko5.getSelectedIndex() == 1) {
          NilaiResiko5.setText("0");//GEN-FIRST:event_SkalaResiko5ItemStateChanged
                    } else if (SkalaResiko5.getSelectedIndex() == 2) {
                        NilaiResiko5.setText("10");
                    } else {
                        NilaiResiko5.setText("20");
                    }
                    isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko5ItemStateChanged

    private void SkalaResiko4KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaResiko3, SkalaResiko5);
    }
//GEN-FIRST:event_SkalaResiko4KeyPressed
		private void SkalaResiko4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaResiko4KeyPressed
                    if (SkalaResiko4.getSelectedIndex() == 0) {
                        NilaiResiko4.setText("");
                    } else if (SkalaResiko4.getSelectedIndex() == 1) {
          NilaiResiko4.setText("0");//GEN-FIRST:event_SkalaResiko4ItemStateChanged
                    } else {
                        NilaiResiko4.setText("20");
                    }
                    isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko4ItemStateChanged

    private void SkalaResiko3KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaResiko2, SkalaResiko4);
    }
//GEN-FIRST:event_SkalaResiko3KeyPressed
		private void SkalaResiko3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaResiko3KeyPressed
                    if (SkalaResiko3.getSelectedIndex() == 0) {
                        NilaiResiko3.setText("");
                    } else if (SkalaResiko3.getSelectedIndex() == 1) {
          NilaiResiko3.setText("0");//GEN-FIRST:event_SkalaResiko3ItemStateChanged
                    } else if (SkalaResiko3.getSelectedIndex() == 2) {
                        NilaiResiko3.setText("15");
                    } else {
                        NilaiResiko3.setText("30");
                    }
                    isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko3ItemStateChanged

    private void SkalaResiko2KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaResiko1, SkalaResiko3);
    }
//GEN-FIRST:event_SkalaResiko2KeyPressed
		private void SkalaResiko2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaResiko2KeyPressed
                    if (SkalaResiko2.getSelectedIndex() == 0) {
                        NilaiResiko2.setText("");
                    } else if (SkalaResiko2.getSelectedIndex() == 1) {
          NilaiResiko2.setText("0");//GEN-FIRST:event_SkalaResiko2ItemStateChanged
                    } else {
                        NilaiResiko2.setText("15");
                    }
                    isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko2ItemStateChanged

    private void SkalaResiko1KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, KetPadaDokter, SkalaResiko2);
    }
//GEN-FIRST:event_SkalaResiko1KeyPressed
		private void SkalaResiko1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaResiko1KeyPressed
                    if (SkalaResiko1.getSelectedIndex() == 0) {
                        NilaiResiko1.setText("");
                    } else if (SkalaResiko1.getSelectedIndex() == 1) {
          NilaiResiko1.setText("0");//GEN-FIRST:event_SkalaResiko1ItemStateChanged
                    } else {
                        NilaiResiko1.setText("25");
                    }
                    isTotalResikoJatuh();
  }//GEN-LAST:event_SkalaResiko1ItemStateChanged

    private void KetPadaDokterKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, PadaDokter, SkalaResiko1);
    }
//GEN-FIRST:event_KetPadaDokterKeyPressed
		private void PadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetPadaDokterKeyPressed
        Valid.pindah(evt, KetNyeri, KetPadaDokter);
    }
//GEN-FIRST:event_PadaDokterKeyPressed
		private void KetNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PadaDokterKeyPressed
        Valid.pindah(evt, NyeriHilang, PadaDokter);
    }
//GEN-FIRST:event_KetNyeriKeyPressed
		private void NyeriHilangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetNyeriKeyPressed
        Valid.pindah(evt, Durasi, KetNyeri);
    }
//GEN-FIRST:event_NyeriHilangKeyPressed
		private void DurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NyeriHilangKeyPressed
        Valid.pindah(evt, SkalaNyeri, NyeriHilang);
    }
//GEN-FIRST:event_DurasiKeyPressed
		private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_DurasiKeyPressed
        Valid.pindah(evt, Menyebar, Durasi);
    }
//GEN-FIRST:event_SkalaNyeriKeyPressed
		private void MenyebarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaNyeriKeyPressed
        Valid.pindah(evt, Lokasi, SkalaNyeri);
    }
//GEN-FIRST:event_MenyebarKeyPressed
		private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_MenyebarKeyPressed
        Valid.pindah(evt, KetQuality, Menyebar);
    }
//GEN-FIRST:event_LokasiKeyPressed
		private void KetQualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_LokasiKeyPressed
        Valid.pindah(evt, Quality, Lokasi);
    }
//GEN-FIRST:event_KetQualityKeyPressed
		private void QualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetQualityKeyPressed
        Valid.pindah(evt, KetProvokes, KetQuality);
    }
//GEN-FIRST:event_QualityKeyPressed
		private void KetProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_QualityKeyPressed
        Valid.pindah(evt, Provokes, Quality);
    }
//GEN-FIRST:event_KetProvokesKeyPressed
		private void ProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetProvokesKeyPressed
        Valid.pindah(evt, Nyeri, KetProvokes);
    }
//GEN-FIRST:event_ProvokesKeyPressed
		private void NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_ProvokesKeyPressed
        Valid.pindah(evt, KeteranganEdukasiPsikologis, Provokes);
    }
//GEN-FIRST:event_NyeriKeyPressed
		private void KeteranganEdukasiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NyeriKeyPressed
        Valid.pindah(evt, EdukasiPsikolgis, Nyeri);
    }
//GEN-FIRST:event_KeteranganEdukasiPsikologisKeyPressed
		private void EdukasiPsikolgisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganEdukasiPsikologisKeyPressed
        Valid.pindah(evt, PendidikanPJ, KeteranganEdukasiPsikologis);
    }
//GEN-FIRST:event_EdukasiPsikolgisKeyPressed
		private void PendidikanPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_EdukasiPsikolgisKeyPressed
        Valid.pindah(evt, KeteranganNilaiKepercayaan, EdukasiPsikolgis);
    }
//GEN-FIRST:event_PendidikanPJKeyPressed
		private void KeteranganNilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PendidikanPJKeyPressed
        Valid.pindah(evt, NilaiKepercayaan, PendidikanPJ);
    }
//GEN-FIRST:event_KeteranganNilaiKepercayaanKeyPressed
		private void NilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganNilaiKepercayaanKeyPressed
        Valid.pindah(evt, KeteranganTinggalDengan, KeteranganNilaiKepercayaan);
    }
//GEN-FIRST:event_NilaiKepercayaanKeyPressed
		private void KeteranganTinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NilaiKepercayaanKeyPressed
        Valid.pindah(evt, TinggalDengan, NilaiKepercayaan);
    }
//GEN-FIRST:event_KeteranganTinggalDenganKeyPressed
		private void TinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganTinggalDenganKeyPressed
        Valid.pindah(evt, HubunganAnggotaKeluarga, KeteranganTinggalDengan);
    }
//GEN-FIRST:event_TinggalDenganKeyPressed
		private void HubunganAnggotaKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TinggalDenganKeyPressed
        Valid.pindah(evt, GangguanJiwa, TinggalDengan);
    }
//GEN-FIRST:event_HubunganAnggotaKeluargaKeyPressed
		private void GangguanJiwaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_HubunganAnggotaKeluargaKeyPressed
        Valid.pindah(evt, KeteranganAdakahPerilaku, HubunganAnggotaKeluarga);
    }
//GEN-FIRST:event_GangguanJiwaKeyPressed
		private void KeteranganAdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_GangguanJiwaKeyPressed
        Valid.pindah(evt, AdakahPerilaku, GangguanJiwa);
    }
//GEN-FIRST:event_KeteranganAdakahPerilakuKeyPressed
		private void AdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganAdakahPerilakuKeyPressed
        Valid.pindah(evt, KondisiPsikologis, KeteranganAdakahPerilaku);
    }
//GEN-FIRST:event_AdakahPerilakuKeyPressed
		private void KondisiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AdakahPerilakuKeyPressed
        Valid.pindah(evt, KesimpulanGangguanFungsi, AdakahPerilaku);
    }
//GEN-FIRST:event_KondisiPsikologisKeyPressed
		private void KesimpulanGangguanFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KondisiPsikologisKeyPressed
        Valid.pindah(evt, KeteranganKemampuanKoordinasi, KondisiPsikologis);
    }
//GEN-FIRST:event_KesimpulanGangguanFungsiKeyPressed
		private void KeteranganKemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KesimpulanGangguanFungsiKeyPressed
        Valid.pindah(evt, KemampuanKoordinasi, KesimpulanGangguanFungsi);
    }
//GEN-FIRST:event_KeteranganKemampuanKoordinasiKeyPressed
		private void KemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganKemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KeteranganKemampuanMenggenggam, KeteranganKemampuanKoordinasi);
    }
//GEN-FIRST:event_KemampuanKoordinasiKeyPressed
	private void KeteranganKemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KemampuanMenggenggam, KemampuanKoordinasi);
    }
//GEN-FIRST:event_KeteranganKemampuanMenggenggamKeyPressed
		private void KemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganKemampuanMenggenggamKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasBawah, KeteranganKemampuanMenggenggam);
    }
//GEN-FIRST:event_KemampuanMenggenggamKeyPressed
	private void KeteranganEkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KemampuanMenggenggamKeyPressed
        Valid.pindah(evt, EkstrimitasBawah, KemampuanMenggenggam);
    }
//GEN-FIRST:event_KeteranganEkstrimitasBawahKeyPressed
		private void EkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganEkstrimitasBawahKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasAtas, KeteranganEkstrimitasBawah);
    }
//GEN-FIRST:event_EkstrimitasBawahKeyPressed
		private void KeteranganEkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_EkstrimitasBawahKeyPressed
        Valid.pindah(evt, EkstrimitasAtas, EkstrimitasBawah);
    }
//GEN-FIRST:event_KeteranganEkstrimitasAtasKeyPressed
		private void EkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganEkstrimitasAtasKeyPressed
        Valid.pindah(evt, AlatAmbulasi, KeteranganEkstrimitasAtas);
    }
//GEN-FIRST:event_EkstrimitasAtasKeyPressed
		private void AlatAmbulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_EkstrimitasAtasKeyPressed
        Valid.pindah(evt, Aktifitas, EkstrimitasAtas);
    }
//GEN-FIRST:event_AlatAmbulasiKeyPressed
		private void AktifitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AlatAmbulasiKeyPressed
        Valid.pindah(evt, KeteranganBerjalan, AlatAmbulasi);
    }
//GEN-FIRST:event_AktifitasKeyPressed
		private void KeteranganBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AktifitasKeyPressed
        Valid.pindah(evt, Berjalan, Aktifitas);
    }
//GEN-FIRST:event_KeteranganBerjalanKeyPressed
		private void BerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeteranganBerjalanKeyPressed
        Valid.pindah(evt, AktifitasSehari2, KeteranganBerjalan);
    }
//GEN-FIRST:event_BerjalanKeyPressed
		private void AktifitasSehari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BerjalanKeyPressed
        Valid.pindah(evt, PolaTidurGangguan, Berjalan);
    }
//GEN-FIRST:event_AktifitasSehari2KeyPressed
		private void PolaTidurGangguanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AktifitasSehari2KeyPressed
        Valid.pindah(evt, PolaTidurLama, AktifitasSehari2);
    }
//GEN-FIRST:event_PolaTidurGangguanKeyPressed
		private void PolaTidurLamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaTidurGangguanKeyPressed
        Valid.pindah(evt, PolaNutrisiJenis, PolaTidurGangguan);
    }
//GEN-FIRST:event_PolaTidurLamaKeyPressed
		private void PolaNutrisiJenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaTidurLamaKeyPressed
        Valid.pindah(evt, PolaNutrisiFrekuensi, PolaTidurLama);
    }
//GEN-FIRST:event_PolaNutrisiJenisKeyPressed
		private void PolaNutrisiFrekuensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaNutrisiJenisKeyPressed
        Valid.pindah(evt, PolaNutrisiPorsi, PolaNutrisiJenis);
    }
//GEN-FIRST:event_PolaNutrisiFrekuensiKeyPressed
		private void PolaNutrisiPorsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaNutrisiFrekuensiKeyPressed
        Valid.pindah(evt, PolaAktifitasBerpindah, PolaNutrisiFrekuensi);
    }
//GEN-FIRST:event_PolaNutrisiPorsiKeyPressed
		private void PolaAktifitasBerpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaNutrisiPorsiKeyPressed
        Valid.pindah(evt, PolaAktifitasEliminasi, PolaNutrisiPorsi);
    }
//GEN-FIRST:event_PolaAktifitasBerpindahKeyPressed
		private void PolaAktifitasBerpakaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaAktifitasBerpindahKeyPressed
        Valid.pindah(evt, PolaAktifitasMakan, PolaAktifitasEliminasi);
    }
//GEN-FIRST:event_PolaAktifitasBerpakaianKeyPressed
		private void PolaAktifitasMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaAktifitasBerpakaianKeyPressed
        Valid.pindah(evt, PolaAktifitasMandi, PolaAktifitasBerpakaian);
    }
//GEN-FIRST:event_PolaAktifitasMakanKeyPressed
		private void PolaAktifitasMandiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaAktifitasMakanKeyPressed
//GEN-FIRST:event_PolaAktifitasMandiKeyPressed
  }//GEN-LAST:event_PolaAktifitasMandiKeyPressed

    private void PolaAktifitasEliminasiKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, PolaAktifitasBerpakaian, PolaAktifitasBerpindah);
    }
//GEN-FIRST:event_PolaAktifitasEliminasiKeyPressed
		private void BreathingJalanNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_PolaAktifitasEliminasiKeyPressed
//GEN-FIRST:event_BreathingJalanNafasKeyPressed
  }//GEN-LAST:event_BreathingJalanNafasKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, BB, BreathingJalanNafas);
    }
//GEN-FIRST:event_TBKeyPressed
		private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TBKeyPressed
        Valid.pindah(evt, SpO2, TB);
    }
//GEN-FIRST:event_BBKeyPressed
		private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BBKeyPressed
        Valid.pindah(evt, Suhu, BB);
    }
//GEN-FIRST:event_SpO2KeyPressed
		private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SpO2KeyPressed
        Valid.pindah(evt, RR, SpO2);
    }
//GEN-FIRST:event_SuhuKeyPressed
		private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SuhuKeyPressed
        Valid.pindah(evt, Nadi, Suhu);
    }
//GEN-FIRST:event_RRKeyPressed
		private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RRKeyPressed
        Valid.pindah(evt, TD, RR);
    }
//GEN-FIRST:event_NadiKeyPressed
		private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NadiKeyPressed
        Valid.pindah(evt, GCS, Nadi);
    }
//GEN-FIRST:event_TDKeyPressed
		private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TDKeyPressed
        Valid.pindah(evt, KeadaanMentalUmum, TD);
    }
//GEN-FIRST:event_GCSKeyPressed
		private void KeadaanMentalUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_GCSKeyPressed
        Valid.pindah(evt, KesadaranMental, GCS);
    }
//GEN-FIRST:event_KeadaanMentalUmumKeyPressed
		private void KesadaranMentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KeadaanMentalUmumKeyPressed
        Valid.pindah(evt, OlahRaga, KeadaanMentalUmum);
    }
//GEN-FIRST:event_KesadaranMentalKeyPressed
		private void OlahRagaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KesadaranMentalKeyPressed
        Valid.pindah(evt, KebiasaanNarkoba, KesadaranMental);
    }
//GEN-FIRST:event_OlahRagaKeyPressed
		private void KebiasaanNarkobaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_OlahRagaKeyPressed
        Valid.pindah(evt, KebiasaanJumlahAlkohol, OlahRaga);
    }
//GEN-FIRST:event_KebiasaanNarkobaKeyPressed
		private void KebiasaanJumlahAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KebiasaanNarkobaKeyPressed
        Valid.pindah(evt, KebiasaanAlkohol, KebiasaanNarkoba);
    }
//GEN-FIRST:event_KebiasaanJumlahAlkoholKeyPressed
		private void KebiasaanAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KebiasaanJumlahAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanJumlahRokok, KebiasaanJumlahAlkohol);
    }
//GEN-FIRST:event_KebiasaanAlkoholKeyPressed
		private void KebiasaanJumlahRokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KebiasaanAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanMerokok, KebiasaanAlkohol);
    }
//GEN-FIRST:event_KebiasaanJumlahRokokKeyPressed
		private void KebiasaanMerokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KebiasaanJumlahRokokKeyPressed
        Valid.pindah(evt, Alergi, KebiasaanJumlahRokok);
    }
//GEN-FIRST:event_KebiasaanMerokokKeyPressed
		private void RTranfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KebiasaanMerokokKeyPressed
        Valid.pindah(evt, KetSedangMenyusui, Alergi);
    }
//GEN-FIRST:event_RTranfusiKeyPressed
		private void KetSedangMenyusuiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RTranfusiKeyPressed
        Valid.pindah(evt, SedangMenyusui, RTranfusi);
    }
//GEN-FIRST:event_KetSedangMenyusuiKeyPressed
		private void SedangMenyusuiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetSedangMenyusuiKeyPressed
        Valid.pindah(evt, AlatBantuDipakai, KetSedangMenyusui);
    }
//GEN-FIRST:event_SedangMenyusuiKeyPressed
		private void AlatBantuDipakaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SedangMenyusuiKeyPressed
        Valid.pindah(evt, RDirawatRS, SedangMenyusui);
    }
//GEN-FIRST:event_AlatBantuDipakaiKeyPressed
		private void RPembedahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AlatBantuDipakaiKeyPressed
        Valid.pindah(evt, RPO, RDirawatRS);
    }
//GEN-FIRST:event_RPembedahanKeyPressed
		private void RDirawatRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RPembedahanKeyPressed
        Valid.pindah(evt, RPembedahan, AlatBantuDipakai);
    }
//GEN-FIRST:event_RDirawatRSKeyPressed
		private void KetAnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RDirawatRSKeyPressed
        Valid.pindah(evt, Anamnesis, TibadiRuang);
    }
//GEN-FIRST:event_KetAnamnesisKeyPressed
		private void MacamKasusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetAnamnesisKeyPressed
        Valid.pindah(evt, BtnDPJP, Anamnesis);
    }
//GEN-FIRST:event_MacamKasusKeyPressed
		private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_MacamKasusKeyPressed
        Valid.pindah2(evt, RPK, RPembedahan);
    }
//GEN-FIRST:event_RPOKeyPressed
		private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RPOKeyPressed
        Valid.pindah2(evt, RPS, RPK);
    }
//GEN-FIRST:event_RPDKeyPressed
		private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RPDKeyPressed
        Valid.pindah2(evt, RPD, RPO);
    }
//GEN-FIRST:event_RPKKeyPressed
		private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RPKKeyPressed
        Valid.pindah2(evt, CaraMasuk, RPD);
    }
//GEN-FIRST:event_RPSKeyPressed
		private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_RPSKeyPressed
        Valid.pindah(evt, RTranfusi, KebiasaanMerokok);
    }
//GEN-FIRST:event_AlergiKeyPressed
		private void CaraMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AlergiKeyPressed
        Valid.pindah(evt, TibadiRuang, RPS);
    }
//GEN-FIRST:event_CaraMasukKeyPressed
		private void TibadiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_CaraMasukKeyPressed
        Valid.pindah(evt, KetAnamnesis, CaraMasuk);
    }
//GEN-FIRST:event_TibadiRuangKeyPressed
		private void BtnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TibadiRuangKeyPressed
        Valid.pindah(evt, BtnPetugas2, MacamKasus);
    }
//GEN-FIRST:event_BtnDPJPKeyPressed
		private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_BtnDPJPKeyPressed
                    dokter.isCek();
                    dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
      dokter.setLocationRelativeTo(internalFrame1);//GEN-FIRST:event_BtnDPJPActionPerformed
                    dokter.setAlwaysOnTop(false);
                    dokter.setVisible(true);
  }//GEN-LAST:event_BtnDPJPActionPerformed

    private void KdDPJPKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
//GEN-FIRST:event_KdDPJPKeyPressed
		private void KdPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KdDPJPKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_KdPetugas2KeyPressed
		private void BtnPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KdPetugas2KeyPressed
        Valid.pindah(evt, BtnPetugas, BtnDPJP);
    }
//GEN-FIRST:event_BtnPetugas2KeyPressed
		private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_BtnPetugas2KeyPressed
                    i = 2;
                    petugas.isCek();
                    petugas.setSize(internalFrame1.getWidth() - 20,
                            internalFrame1.getHeight() - 20);//GEN-FIRST:event_BtnPetugas2ActionPerformed
                    petugas.setLocationRelativeTo(internalFrame1);
                    petugas.setAlwaysOnTop(false);
                    petugas.setVisible(true);
  }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, BtnDPJP, MacamKasus);
    }
//GEN-FIRST:event_TglAsuhanKeyPressed
		private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TglAsuhanKeyPressed
        Valid.pindah(evt, MacamKasus, KetAnamnesis);
    }
//GEN-FIRST:event_AnamnesisKeyPressed
		private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AnamnesisKeyPressed
        Valid.pindah(evt, BtnSimpan, BtnPetugas2);
    }
//GEN-FIRST:event_BtnPetugasKeyPressed
		private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_BtnPetugasKeyPressed
                    i = 1;
                    petugas.isCek();
                    petugas.setSize(internalFrame1.getWidth() - 20,
                            internalFrame1.getHeight() - 20);//GEN-FIRST:event_BtnPetugasActionPerformed
                    petugas.setLocationRelativeTo(internalFrame1);
                    petugas.setAlwaysOnTop(false);
                    petugas.setVisible(true);
  }//GEN-LAST:event_BtnPetugasActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_KdPetugasKeyPressed
  }//GEN-LAST:event_KdPetugasKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
          Valid.pindah(evt, TCari, BtnPetugas);//GEN-FIRST:event_TNoRwKeyPressed
        }
  }//GEN-LAST:event_TNoRwKeyPressed

    private void BreathingJalanNafasBerupaKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingJalanNafasBerupaKeyPressed
		private void BreathingPernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingJalanNafasBerupaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingPernapasanKeyPressed
		private void BreathingPernapasanDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingPernapasanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingPernapasanDenganKeyPressed
		private void BreathingIramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingPernapasanDenganKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingIramaKeyPressed
		private void BreathingETTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingIramaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingETTKeyPressed
		private void BreathingCuffKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingETTKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingCuffKeyPressed
		private void BreathingFrekuensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingCuffKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingFrekuensiKeyPressed
		private void BreathingKedalamanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingFrekuensiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingKedalamanKeyPressed
		private void BreathingSpulumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingKedalamanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingSpulumKeyPressed
		private void BreathingKonsistensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingSpulumKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingKonsistensiKeyPressed
		private void BreathingNafasBunyiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingKonsistensiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingNafasBunyiKeyPressed
		private void BreathingTerdapatDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingNafasBunyiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingTerdapatDarahKeyPressed
		private void BreathingSuaraNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingTerdapatDarahKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingSuaraNafasKeyPressed
		private void BreathingTerdapatDarahJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingSuaraNafasKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingTerdapatDarahJumlahKeyPressed
		private void BreathingAnalisaGasDarahPHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingTerdapatDarahJumlahKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingAnalisaGasDarahPHKeyPressed
		private void BreathingAnalisaGasDarahpCO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingAnalisaGasDarahPHKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingAnalisaGasDarahpCO2KeyPressed
		private void BreathingAnalisaGasDarahpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingAnalisaGasDarahpCO2KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingAnalisaGasDarahpO2KeyPressed
		private void BreathingAnalisaGasDarahSatO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingAnalisaGasDarahpO2KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BreathingAnalisaGasDarahSatO2KeyPressed
		private void BloodSirkulasiPeriferIramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BreathingAnalisaGasDarahSatO2KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferIramaKeyPressed
		private void BloodSirkulasiPeriferNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferIramaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferNadiKeyPressed
		private void BloodSirkulasiPeriferMAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferNadiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferMAPKeyPressed
		private void BloodSirkulasiPeriferCVPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferMAPKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferCVPKeyPressed
		private void BloodSirkulasiPeriferIBPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferCVPKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferIBPKeyPressed
		private void BloodSirkulasiPeriferAkralKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferIBPKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferAkralKeyPressed
		private void BloodSirkulasiPeriferDistensiVenaJugulariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferAkralKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferDistensiVenaJugulariKeyPressed
		private void BloodSirkulasiPeriferWarnaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferDistensiVenaJugulariKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferWarnaKulitKeyPressed
		private void BloodSirkulasiPeriferSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferWarnaKulitKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferSuhuKeyPressed
		private void BloodSirkulasiPeriferPengisianKaplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferSuhuKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferPengisianKaplierKeyPressed
		private void BloodSirkulasiPeriferEdemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferPengisianKaplierKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferEdemaKeyPressed
		private void BloodSirkulasiPeriferEdemaPadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferEdemaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferEdemaPadaKeyPressed
		private void BloodSirkulasiPeriferEKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferEdemaPadaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiPeriferEKGKeyPressed
		private void BloodSirkulasiJantungJantungIramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiPeriferEKGKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungJantungIramaKeyPressed
		private void BloodSirkulasiJantungBunyiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungJantungIramaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungBunyiKeyPressed
		private void BloodSirkulasiJantungKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungBunyiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungKeluhanKeyPressed
		private void BloodSirkulasiJantungKarakteristikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungKeluhanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungKarakteristikKeyPressed
		private void BloodSirkulasiJantungSakitDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungKarakteristikKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungSakitDadaKeyPressed
		private void BloodSirkulasiJantungSakitDadaTimbulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungSakitDadaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodSirkulasiJantungSakitDadaTimbulKeyPressed
		private void BloodHematologiHBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodSirkulasiJantungSakitDadaTimbulKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiHBKeyPressed
		private void BloodHematologiHtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiHBKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiHtKeyPressed
		private void BloodHematologiEritrositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiHtKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiEritrositKeyPressed
		private void BloodHematologiLeukositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiEritrositKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiLeukositKeyPressed
		private void BloodHematologiTrombositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiLeukositKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiTrombositKeyPressed
		private void BloodHematologiPendarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiTrombositKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiPendarahanKeyPressed
		private void BloodHematologiCTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiPendarahanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiCTKeyPressed
		private void BloodHematologiPTTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiCTKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BloodHematologiPTTKeyPressed
		private void BrainSirkulasiSerebralTingkatKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BloodHematologiPTTKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralTingkatKesadaranKeyPressed
		private void BrainSirkulasiSerebralPupilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralTingkatKesadaranKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralPupilKeyPressed
		private void BrainSirkulasiSerebralReaksiTerhadapCahayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralPupilKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralReaksiTerhadapCahayaKeyPressed
		private void BrainSirkulasiSerebralGCSVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralReaksiTerhadapCahayaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralGCSVKeyPressed
		private void BrainSirkulasiSerebralGCSMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralGCSVKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralGCSMKeyPressed
		private void BrainSirkulasiSerebralGCSJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralGCSMKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralGCSJumlahKeyPressed
		private void BrainSirkulasiSerebralTerjadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralGCSJumlahKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralTerjadiKeyPressed
		private void BrainSirkulasiSerebralTerjadiBagianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralTerjadiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralTerjadiBagianKeyPressed
		private void BrainSirkulasiSerebralCPPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralTerjadiBagianKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralCPPKeyPressed
		private void BrainSirkulasiSerebralSODKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralCPPKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralSODKeyPressed
		private void BrainSirkulasiSerebralPalkososialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralSODKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralPalkososialKeyPressed
		private void BrainSirkulasiSerebralEUDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralPalkososialKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BrainSirkulasiSerebralEUDKeyPressed
		private void BowelBAKSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BrainSirkulasiSerebralEUDKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelBAKSaatIniKeyPressed
		private void BowelKonsistensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelBAKSaatIniKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelKonsistensiKeyPressed
		private void BowelWarnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelKonsistensiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelWarnaKeyPressed
		private void BowelLendirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelWarnaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelLendirKeyPressed
		private void BowelMualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelLendirKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelMualKeyPressed
		private void BowelKembungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelMualKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelKembungKeyPressed
		private void BowelDistensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelKembungKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelDistensiKeyPressed
		private void BowelNyeriTekanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelDistensiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelNyeriTekanKeyPressed
		private void BowelNGTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelNyeriTekanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BowelNGTKeyPressed
		private void BladderBAKSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BowelNGTKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderBAKSaatIniKeyPressed
		private void BladderBAKTerkontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderBAKSaatIniKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderBAKTerkontrolKeyPressed
		private void BladderProduksiUrinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderBAKTerkontrolKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderProduksiUrinKeyPressed
		private void BladderWarnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderProduksiUrinKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderWarnaKeyPressed
		private void BladderDistensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderWarnaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderDistensiKeyPressed
		private void BladderSakitWaktuBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderDistensiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderSakitWaktuBAKKeyPressed
		private void BladderSakitPinggangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderSakitWaktuBAKKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BladderSakitPinggangKeyPressed
		private void BoneTugorKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BladderSakitPinggangKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneTugorKulitKeyPressed
		private void BoneKeadaanKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneTugorKulitKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneKeadaanKulitKeyPressed
		private void BoneKeadaanLukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneKeadaanKulitKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneKeadaanLukaKeyPressed
		private void BoneLokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneKeadaanLukaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneLokasiKeyPressed
		private void BoneSulitDalamGerakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneLokasiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneSulitDalamGerakKeyPressed
		private void BoneFrakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneSulitDalamGerakKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneFrakturKeyPressed
		private void BoneAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneFrakturKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneAreaKeyPressed
		private void BoneOdemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneAreaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneOdemaKeyPressed
		private void BoneKekuatanOtotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneOdemaKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BoneKekuatanOtotKeyPressed
		private void TipePersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BoneKekuatanOtotKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_TipePersalinanKeyPressed
		private void ImunisasiDasarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_TipePersalinanKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_ImunisasiDasarKeyPressed
		private void SkalaHumptyDumpty7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_ImunisasiDasarKeyPressed
                    if (SkalaHumptyDumpty7.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty7.setText("");
                    } else if (SkalaHumptyDumpty7.getSelectedIndex() == 1) {
          NilaiHumptyDumpty7.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty7ItemStateChanged
                    } else if (SkalaHumptyDumpty7.getSelectedIndex() == 2) {
                        NilaiHumptyDumpty7.setText("2");
                    } else {
                        NilaiHumptyDumpty7.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty7ItemStateChanged

    private void SkalaHumptyDumpty7KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaHumptyDumpty7KeyPressed
  }//GEN-LAST:event_SkalaHumptyDumpty7KeyPressed

    private void SkalaHumptyDumpty6ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaHumptyDumpty6.getSelectedIndex() == 0) {
            NilaiHumptyDumpty6.setText("");
        } else if (SkalaHumptyDumpty6.getSelectedIndex() == 1) {
          NilaiHumptyDumpty6.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty6ItemStateChanged
        } else if (SkalaHumptyDumpty6.getSelectedIndex() == 2) {
            NilaiHumptyDumpty6.setText("2");
        } else {
            NilaiHumptyDumpty6.setText("1");
        }
        isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty6ItemStateChanged

    private void SkalaHumptyDumpty6KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty5, SkalaHumptyDumpty7);
    }
//GEN-FIRST:event_SkalaHumptyDumpty6KeyPressed
		private void SkalaHumptyDumpty5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty6KeyPressed
                    if (SkalaHumptyDumpty5.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty5.setText("");
                    } else if (SkalaHumptyDumpty5.getSelectedIndex() == 1) {
          NilaiHumptyDumpty5.setText("4");//GEN-FIRST:event_SkalaHumptyDumpty5ItemStateChanged
                    } else if (SkalaHumptyDumpty5.getSelectedIndex() == 2) {
                        NilaiHumptyDumpty5.setText("3");
                    } else if (SkalaHumptyDumpty5.getSelectedIndex() == 3) {
                        NilaiHumptyDumpty5.setText("2");
                    } else {
                        NilaiHumptyDumpty5.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty5ItemStateChanged

    private void SkalaHumptyDumpty5KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty4, SkalaHumptyDumpty6);
    }
//GEN-FIRST:event_SkalaHumptyDumpty5KeyPressed
		private void SkalaHumptyDumpty4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty5KeyPressed
                    if (SkalaHumptyDumpty4.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty4.setText("");
                    } else if (SkalaHumptyDumpty4.getSelectedIndex() == 1) {
          NilaiHumptyDumpty4.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty4ItemStateChanged
                    } else if (SkalaHumptyDumpty4.getSelectedIndex() == 2) {
                        NilaiHumptyDumpty4.setText("2");
                    } else {
                        NilaiHumptyDumpty4.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty4ItemStateChanged

    private void SkalaHumptyDumpty4KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty3, SkalaHumptyDumpty5);
    }
//GEN-FIRST:event_SkalaHumptyDumpty4KeyPressed
		private void SkalaHumptyDumpty3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty4KeyPressed
                    if (SkalaHumptyDumpty3.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty3.setText("");
                    } else if (SkalaHumptyDumpty3.getSelectedIndex() == 1) {
          NilaiHumptyDumpty3.setText("4");//GEN-FIRST:event_SkalaHumptyDumpty3ItemStateChanged
                    } else if (SkalaHumptyDumpty3.getSelectedIndex() == 2) {
                        NilaiHumptyDumpty3.setText("3");
                    } else if (SkalaHumptyDumpty3.getSelectedIndex() == 3) {
                        NilaiHumptyDumpty3.setText("2");
                    } else {
                        NilaiHumptyDumpty3.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty3ItemStateChanged

    private void SkalaHumptyDumpty3KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty2, SkalaHumptyDumpty4);
    }
//GEN-FIRST:event_SkalaHumptyDumpty3KeyPressed
		private void SkalaHumptyDumpty2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty3KeyPressed
                    if (SkalaHumptyDumpty2.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty2.setText("");
                    } else if (SkalaHumptyDumpty2.getSelectedIndex() == 1) {
          NilaiHumptyDumpty2.setText("2");//GEN-FIRST:event_SkalaHumptyDumpty2ItemStateChanged
                    } else {
                        NilaiHumptyDumpty2.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty2ItemStateChanged

    private void SkalaHumptyDumpty2KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty1, SkalaHumptyDumpty3);
    }
//GEN-FIRST:event_SkalaHumptyDumpty2KeyPressed
		private void SkalaHumptyDumpty1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty2KeyPressed
                    if (SkalaHumptyDumpty1.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty1.setText("");
                    } else if (SkalaHumptyDumpty1.getSelectedIndex() == 1) {
          NilaiHumptyDumpty1.setText("4");//GEN-FIRST:event_SkalaHumptyDumpty1ItemStateChanged
                    } else if (SkalaHumptyDumpty1.getSelectedIndex() == 2) {
                        NilaiHumptyDumpty1.setText("3");
                    } else if (SkalaHumptyDumpty1.getSelectedIndex() == 3) {
                        NilaiHumptyDumpty1.setText("2");
                    } else {
                        NilaiHumptyDumpty1.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty1ItemStateChanged

    private void SkalaHumptyDumpty1KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaHumptyDumpty1KeyPressed
  }//GEN-LAST:event_SkalaHumptyDumpty1KeyPressed

    private void SkalaGizi3ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaGizi3.getSelectedIndex() == 0) {
            NilaiGizi3.setText("0");
        } else if (SkalaGizi3.getSelectedIndex() == 1) {
          NilaiGizi3.setText("1");//GEN-FIRST:event_SkalaGizi3ItemStateChanged
        }
        isTotalGizi();
  }//GEN-LAST:event_SkalaGizi3ItemStateChanged

    private void SkalaGizi3KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
//GEN-FIRST:event_SkalaGizi3KeyPressed
		private void SkalaGizi4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaGizi3KeyPressed
                    if (SkalaGizi4.getSelectedIndex() == 0) {
                        NilaiGizi4.setText("0");
                    } else if (SkalaGizi4.getSelectedIndex() == 1) {
          NilaiGizi4.setText("1");//GEN-FIRST:event_SkalaGizi4ItemStateChanged
                    }
                    isTotalGizi();
  }//GEN-LAST:event_SkalaGizi4ItemStateChanged

    private void SkalaGizi4KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
//GEN-FIRST:event_SkalaGizi4KeyPressed
    private void WajibKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaGizi4KeyPressed
        // TODO add your handling code here://GEN-FIRST:event_WajibKeyPressed
    }//GEN-LAST:event_WajibKeyPressed

    private void KetHalanganLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetHalanganLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetHalanganLainKeyPressed

    private void ToharahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToharahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ToharahKeyPressed

    private void SholatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SholatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SholatKeyPressed

    private void MotivasiKesembuhanIbadahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MotivasiKesembuhanIbadahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotivasiKesembuhanIbadahKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalKeperawatanRanapKritis dialog = new RMPenilaianAwalKeperawatanRanapKritis(
                    new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }

            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ComboBox AdakahPerilaku;
    private widget.TextBox Agama;
    private widget.ComboBox Aktifitas;
    private widget.ComboBox AktifitasSehari2;
    private widget.ComboBox AlatAmbulasi;
    private widget.ComboBox AlatBantuDipakai;
    private widget.TextBox Alergi;
    private widget.ComboBox Anamnesis;
    private widget.TextBox BB;
    private widget.TextBox BBDiKaji;
    private widget.TextBox BBLahir;
    private widget.TextBox Bahasa;
    private widget.TextBox BerdiriUsia;
    private widget.ComboBox Berjalan;
    private widget.TextBox BerjalanUsia;
    private widget.TextBox BicaraUsia;
    private widget.TextBox BladderBAKPolaRutin;
    private widget.TextBox BladderBAKSaatIni;
    private widget.ComboBox BladderBAKTerkontrol;
    private widget.ComboBox BladderDistensi;
    private widget.TextBox BladderProduksiUrin;
    private widget.ComboBox BladderSakitPinggang;
    private widget.ComboBox BladderSakitWaktuBAK;
    private widget.ComboBox BladderWarna;
    private widget.TextBox BloodHematologiCT;
    private widget.TextBox BloodHematologiEritrosit;
    private widget.TextBox BloodHematologiHB;
    private widget.TextBox BloodHematologiHt;
    private widget.TextBox BloodHematologiLeukosit;
    private widget.TextBox BloodHematologiPTT;
    private widget.ComboBox BloodHematologiPendarahan;
    private widget.TextBox BloodHematologiTrombosit;
    private widget.ComboBox BloodSirkulasiJantungBunyi;
    private widget.ComboBox BloodSirkulasiJantungJantungIrama;
    private widget.ComboBox BloodSirkulasiJantungKarakteristik;
    private widget.ComboBox BloodSirkulasiJantungKeluhan;
    private widget.ComboBox BloodSirkulasiJantungSakitDada;
    private widget.ComboBox BloodSirkulasiJantungSakitDadaTimbul;
    private widget.ComboBox BloodSirkulasiPeriferAkral;
    private widget.TextBox BloodSirkulasiPeriferCVP;
    private widget.ComboBox BloodSirkulasiPeriferDistensiVenaJugulari;
    private widget.TextBox BloodSirkulasiPeriferEKG;
    private widget.ComboBox BloodSirkulasiPeriferEdema;
    private widget.ComboBox BloodSirkulasiPeriferEdemaPada;
    private widget.TextBox BloodSirkulasiPeriferIBP;
    private widget.ComboBox BloodSirkulasiPeriferIrama;
    private widget.TextBox BloodSirkulasiPeriferMAP;
    private widget.TextBox BloodSirkulasiPeriferNadi;
    private widget.ComboBox BloodSirkulasiPeriferPengisianKaplier;
    private widget.TextBox BloodSirkulasiPeriferSuhu;
    private widget.TextBox BloodSirkulasiPeriferTekananDarah;
    private widget.ComboBox BloodSirkulasiPeriferWarnaKulit;
    private widget.TextBox BoneArea;
    private widget.ComboBox BoneFraktur;
    private widget.ComboBox BoneKeadaanKulit;
    private widget.ComboBox BoneKeadaanLuka;
    private widget.TextBox BoneKekuatanOtot;
    private widget.TextBox BoneLokasi;
    private widget.ComboBox BoneOdema;
    private widget.ComboBox BoneSulitDalamGerak;
    private widget.ComboBox BoneTugorKulit;
    private widget.TextBox BowelBAKPolaRutin;
    private widget.TextBox BowelBAKSaatIni;
    private widget.ComboBox BowelDistensi;
    private widget.TextBox BowelIntake;
    private widget.ComboBox BowelKembung;
    private widget.ComboBox BowelKonsistensi;
    private widget.ComboBox BowelLendir;
    private widget.ComboBox BowelMual;
    private widget.ComboBox BowelNGT;
    private widget.ComboBox BowelNyeriTekan;
    private widget.ComboBox BowelWarna;
    private widget.TextBox BrainSirkulasiSerebralCPP;
    private widget.TextBox BrainSirkulasiSerebralEUD;
    private widget.TextBox BrainSirkulasiSerebralGCSE;
    private widget.TextBox BrainSirkulasiSerebralGCSJumlah;
    private widget.TextBox BrainSirkulasiSerebralGCSM;
    private widget.TextBox BrainSirkulasiSerebralGCSV;
    private widget.TextBox BrainSirkulasiSerebralICP;
    private widget.ComboBox BrainSirkulasiSerebralPalkososial;
    private widget.ComboBox BrainSirkulasiSerebralPupil;
    private widget.ComboBox BrainSirkulasiSerebralReaksiTerhadapCahaya;
    private widget.TextBox BrainSirkulasiSerebralSOD;
    private widget.ComboBox BrainSirkulasiSerebralTerjadi;
    private widget.ComboBox BrainSirkulasiSerebralTerjadiBagian;
    private widget.ComboBox BrainSirkulasiSerebralTingkatKesadaran;
    private widget.TextBox BreathingAnalisaGasDarahPH;
    private widget.TextBox BreathingAnalisaGasDarahSatO2;
    private widget.TextBox BreathingAnalisaGasDarahpCO2;
    private widget.TextBox BreathingAnalisaGasDarahpO2;
    private widget.TextBox BreathingCuff;
    private widget.TextBox BreathingETT;
    private widget.TextBox BreathingFrekuensi;
    private widget.ComboBox BreathingIrama;
    private widget.ComboBox BreathingJalanNafas;
    private widget.ComboBox BreathingJalanNafasBerupa;
    private widget.ComboBox BreathingKedalaman;
    private widget.ComboBox BreathingKonsistensi;
    private widget.ComboBox BreathingNafasBunyi;
    private widget.ComboBox BreathingPernapasan;
    private widget.ComboBox BreathingPernapasanDengan;
    private widget.ComboBox BreathingSpulum;
    private widget.ComboBox BreathingSuaraNafas;
    private widget.ComboBox BreathingTerdapatDarah;
    private widget.TextBox BreathingTerdapatDarahJumlah;
    private widget.Button BtnAll;
    private widget.Button BtnAllMasalah;
    private widget.Button BtnAllRencana;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariMasalah;
    private widget.Button BtnCariRencana;
    private widget.Button BtnDPJP;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahRencana;
    private widget.TextBox CaraBayar;
    private widget.ComboBox CaraMasuk;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DetailRencana;
    private widget.ComboBox DiagnosaKhususGizi;
    private widget.ComboBox DiketahuiDietisen;
    private widget.TextBox DudukUsia;
    private widget.TextBox Durasi;
    private widget.ComboBox EdukasiPsikolgis;
    private widget.ComboBox EkstrimitasAtas;
    private widget.ComboBox EkstrimitasBawah;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox GCS;
    private widget.TextBox GangguanHamil;
    private widget.ComboBox GangguanJiwa;
    private widget.TextBox GravidaKe;
    private widget.ComboBox HubunganAnggotaKeluarga;
    private widget.TextBox ImunisasiBelum;
    private widget.ComboBox ImunisasiDasar;
    private widget.TextBox Jk;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas2;
    private widget.ComboBox KeadaanMentalUmum;
    private widget.ComboBox KebiasaanAlkohol;
    private widget.TextBox KebiasaanJumlahAlkohol;
    private widget.TextBox KebiasaanJumlahRokok;
    private widget.ComboBox KebiasaanMerokok;
    private widget.ComboBox KebiasaanNarkoba;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox KemampuanKoordinasi;
    private widget.ComboBox KemampuanMenggenggam;
    private widget.TextBox KesadaranMental;
    private widget.ComboBox KesimpulanGangguanFungsi;
    private widget.TextBox KetAnamnesis;
    private widget.TextBox KetHalanganLain;
    private widget.TextBox KetNyeri;
    private widget.TextBox KetPadaDokter;
    private widget.TextBox KetProvokes;
    private widget.TextBox KetQuality;
    private widget.TextBox KetSedangMenyusui;
    private widget.TextBox KeteranganAdakahPerilaku;
    private widget.TextBox KeteranganBerjalan;
    private widget.TextBox KeteranganDiagnosaKhususGizi;
    private widget.TextBox KeteranganDiketahuiDietisen;
    private widget.TextBox KeteranganEdukasiPsikologis;
    private widget.TextBox KeteranganEkstrimitasAtas;
    private widget.TextBox KeteranganEkstrimitasBawah;
    private widget.TextBox KeteranganKemampuanKoordinasi;
    private widget.TextBox KeteranganKemampuanMenggenggam;
    private widget.TextBox KeteranganNilaiKepercayaan;
    private widget.TextBox KeteranganTinggalDengan;
    private widget.ComboBox KondisiPsikologis;
    private widget.ComboBox Kriteria1;
    private widget.ComboBox Kriteria2;
    private widget.ComboBox Kriteria3;
    private widget.ComboBox Kriteria4;
    private widget.Label LCount;
    private widget.TextBox LingkarKepalaLahir;
    private widget.TextBox Lokasi;
    private widget.ComboBox MacamKasus;
    private widget.ComboBox Menyebar;
    private widget.ComboBox MotivasiKesembuhanIbadah;
    private widget.TextBox Nadi;
    private widget.TextBox NilaiGizi1;
    private widget.TextBox NilaiGizi2;
    private widget.TextBox NilaiGizi3;
    private widget.TextBox NilaiGizi4;
    private widget.TextBox NilaiGiziTotal;
    private widget.TextBox NilaiHumptyDumpty1;
    private widget.TextBox NilaiHumptyDumpty2;
    private widget.TextBox NilaiHumptyDumpty3;
    private widget.TextBox NilaiHumptyDumpty4;
    private widget.TextBox NilaiHumptyDumpty5;
    private widget.TextBox NilaiHumptyDumpty6;
    private widget.TextBox NilaiHumptyDumpty7;
    private widget.TextBox NilaiHumptyDumptyTotal;
    private widget.ComboBox NilaiKepercayaan;
    private widget.TextBox NilaiResiko1;
    private widget.TextBox NilaiResiko2;
    private widget.TextBox NilaiResiko3;
    private widget.TextBox NilaiResiko4;
    private widget.TextBox NilaiResiko5;
    private widget.TextBox NilaiResiko6;
    private widget.TextBox NilaiResikoTotal;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas2;
    private widget.ComboBox Nyeri;
    private widget.ComboBox NyeriHilang;
    private widget.ComboBox OlahRaga;
    private widget.ComboBox PadaDokter;
    private widget.PanelBiasa PanelAccor;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox PekerjaanPasien;
    private widget.ComboBox PendidikanPJ;
    private widget.TextBox PendidikanPasien;
    private widget.ComboBox PolaAktifitasBerpakaian;
    private widget.ComboBox PolaAktifitasBerpindah;
    private widget.ComboBox PolaAktifitasEliminasi;
    private widget.ComboBox PolaAktifitasMakan;
    private widget.ComboBox PolaAktifitasMandi;
    private widget.TextBox PolaNutrisiFrekuensi;
    private widget.TextBox PolaNutrisiJenis;
    private widget.TextBox PolaNutrisiPorsi;
    private widget.ComboBox PolaTidurGangguan;
    private widget.TextBox PolaTidurLama;
    private javax.swing.JPopupMenu Popup;
    private widget.ComboBox Provokes;
    private widget.ComboBox Quality;
    private widget.TextBox RDirawatRS;
    private widget.TextArea RPD;
    private widget.TextArea RPK;
    private widget.TextArea RPO;
    private widget.TextArea RPS;
    private widget.TextBox RPembedahan;
    private widget.TextBox RR;
    private widget.TextBox RTranfusi;
    private widget.TextArea Rencana;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox SedangMenyusui;
    private widget.ComboBox Sholat;
    private widget.ComboBox SkalaGizi1;
    private widget.ComboBox SkalaGizi2;
    private widget.ComboBox SkalaGizi3;
    private widget.ComboBox SkalaGizi4;
    private widget.ComboBox SkalaHumptyDumpty1;
    private widget.ComboBox SkalaHumptyDumpty2;
    private widget.ComboBox SkalaHumptyDumpty3;
    private widget.ComboBox SkalaHumptyDumpty4;
    private widget.ComboBox SkalaHumptyDumpty5;
    private widget.ComboBox SkalaHumptyDumpty6;
    private widget.ComboBox SkalaHumptyDumpty7;
    private widget.ComboBox SkalaNyeri;
    private widget.ComboBox SkalaResiko1;
    private widget.ComboBox SkalaResiko2;
    private widget.ComboBox SkalaResiko3;
    private widget.ComboBox SkalaResiko4;
    private widget.ComboBox SkalaResiko5;
    private widget.ComboBox SkalaResiko6;
    private widget.TextBox SpO2;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TBDiKaji;
    private widget.TextBox TBLahir;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextBox TCariRencana;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRencanaKeperawatan;
    private widget.TextBox TengkurapUsia;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox TibadiRuang;
    private widget.ComboBox TinggalDengan;
    private widget.Label TingkatHumptyDumpty;
    private widget.Label TingkatResiko;
    private widget.ComboBox TipePersalinan;
    private widget.ComboBox Toharah;
    private widget.TextBox TumbuhGigiUsia;
    private widget.TextBox UsiaIbuSaatHamil;
    private widget.ComboBox Wajib;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel176;
    private widget.Label jLabel177;
    private widget.Label jLabel178;
    private widget.Label jLabel179;
    private widget.Label jLabel18;
    private widget.Label jLabel180;
    private widget.Label jLabel181;
    private widget.Label jLabel182;
    private widget.Label jLabel183;
    private widget.Label jLabel184;
    private widget.Label jLabel185;
    private widget.Label jLabel186;
    private widget.Label jLabel187;
    private widget.Label jLabel188;
    private widget.Label jLabel189;
    private widget.Label jLabel19;
    private widget.Label jLabel190;
    private widget.Label jLabel191;
    private widget.Label jLabel192;
    private widget.Label jLabel193;
    private widget.Label jLabel194;
    private widget.Label jLabel195;
    private widget.Label jLabel196;
    private widget.Label jLabel197;
    private widget.Label jLabel198;
    private widget.Label jLabel199;
    private widget.Label jLabel20;
    private widget.Label jLabel200;
    private widget.Label jLabel201;
    private widget.Label jLabel202;
    private widget.Label jLabel203;
    private widget.Label jLabel204;
    private widget.Label jLabel205;
    private widget.Label jLabel206;
    private widget.Label jLabel207;
    private widget.Label jLabel208;
    private widget.Label jLabel209;
    private widget.Label jLabel21;
    private widget.Label jLabel210;
    private widget.Label jLabel211;
    private widget.Label jLabel212;
    private widget.Label jLabel213;
    private widget.Label jLabel214;
    private widget.Label jLabel215;
    private widget.Label jLabel216;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel22;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel23;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel24;
    private widget.Label jLabel240;
    private widget.Label jLabel241;
    private widget.Label jLabel242;
    private widget.Label jLabel243;
    private widget.Label jLabel244;
    private widget.Label jLabel246;
    private widget.Label jLabel247;
    private widget.Label jLabel248;
    private widget.Label jLabel249;
    private widget.Label jLabel25;
    private widget.Label jLabel250;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel255;
    private widget.Label jLabel256;
    private widget.Label jLabel257;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
    private widget.Label jLabel26;
    private widget.Label jLabel260;
    private widget.Label jLabel261;
    private widget.Label jLabel27;
    private widget.Label jLabel270;
    private widget.Label jLabel271;
    private widget.Label jLabel272;
    private widget.Label jLabel273;
    private widget.Label jLabel274;
    private widget.Label jLabel275;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
    private widget.Label jLabel279;
    private widget.Label jLabel28;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel283;
    private widget.Label jLabel284;
    private widget.Label jLabel285;
    private widget.Label jLabel286;
    private widget.Label jLabel287;
    private widget.Label jLabel288;
    private widget.Label jLabel289;
    private widget.Label jLabel29;
    private widget.Label jLabel290;
    private widget.Label jLabel291;
    private widget.Label jLabel292;
    private widget.Label jLabel293;
    private widget.Label jLabel294;
    private widget.Label jLabel295;
    private widget.Label jLabel296;
    private widget.Label jLabel297;
    private widget.Label jLabel298;
    private widget.Label jLabel299;
    private widget.Label jLabel30;
    private widget.Label jLabel300;
    private widget.Label jLabel301;
    private widget.Label jLabel302;
    private widget.Label jLabel303;
    private widget.Label jLabel304;
    private widget.Label jLabel305;
    private widget.Label jLabel306;
    private widget.Label jLabel307;
    private widget.Label jLabel308;
    private widget.Label jLabel309;
    private widget.Label jLabel31;
    private widget.Label jLabel310;
    private widget.Label jLabel311;
    private widget.Label jLabel312;
    private widget.Label jLabel313;
    private widget.Label jLabel314;
    private widget.Label jLabel315;
    private widget.Label jLabel316;
    private widget.Label jLabel317;
    private widget.Label jLabel318;
    private widget.Label jLabel319;
    private widget.Label jLabel32;
    private widget.Label jLabel320;
    private widget.Label jLabel321;
    private widget.Label jLabel322;
    private widget.Label jLabel323;
    private widget.Label jLabel324;
    private widget.Label jLabel325;
    private widget.Label jLabel326;
    private widget.Label jLabel327;
    private widget.Label jLabel328;
    private widget.Label jLabel329;
    private widget.Label jLabel330;
    private widget.Label jLabel331;
    private widget.Label jLabel332;
    private widget.Label jLabel333;
    private widget.Label jLabel334;
    private widget.Label jLabel335;
    private widget.Label jLabel336;
    private widget.Label jLabel337;
    private widget.Label jLabel338;
    private widget.Label jLabel339;
    private widget.Label jLabel34;
    private widget.Label jLabel340;
    private widget.Label jLabel341;
    private widget.Label jLabel342;
    private widget.Label jLabel343;
    private widget.Label jLabel344;
    private widget.Label jLabel345;
    private widget.Label jLabel346;
    private widget.Label jLabel347;
    private widget.Label jLabel348;
    private widget.Label jLabel349;
    private widget.Label jLabel350;
    private widget.Label jLabel351;
    private widget.Label jLabel352;
    private widget.Label jLabel353;
    private widget.Label jLabel354;
    private widget.Label jLabel355;
    private widget.Label jLabel356;
    private widget.Label jLabel357;
    private widget.Label jLabel358;
    private widget.Label jLabel359;
    private widget.Label jLabel36;
    private widget.Label jLabel360;
    private widget.Label jLabel361;
    private widget.Label jLabel362;
    private widget.Label jLabel363;
    private widget.Label jLabel364;
    private widget.Label jLabel365;
    private widget.Label jLabel366;
    private widget.Label jLabel367;
    private widget.Label jLabel368;
    private widget.Label jLabel369;
    private widget.Label jLabel37;
    private widget.Label jLabel370;
    private widget.Label jLabel371;
    private widget.Label jLabel372;
    private widget.Label jLabel373;
    private widget.Label jLabel374;
    private widget.Label jLabel375;
    private widget.Label jLabel376;
    private widget.Label jLabel377;
    private widget.Label jLabel378;
    private widget.Label jLabel379;
    private widget.Label jLabel38;
    private widget.Label jLabel380;
    private widget.Label jLabel381;
    private widget.Label jLabel382;
    private widget.Label jLabel383;
    private widget.Label jLabel384;
    private widget.Label jLabel385;
    private widget.Label jLabel386;
    private widget.Label jLabel387;
    private widget.Label jLabel388;
    private widget.Label jLabel389;
    private widget.Label jLabel39;
    private widget.Label jLabel390;
    private widget.Label jLabel391;
    private widget.Label jLabel392;
    private widget.Label jLabel393;
    private widget.Label jLabel394;
    private widget.Label jLabel395;
    private widget.Label jLabel396;
    private widget.Label jLabel397;
    private widget.Label jLabel398;
    private widget.Label jLabel399;
    private widget.Label jLabel40;
    private widget.Label jLabel400;
    private widget.Label jLabel401;
    private widget.Label jLabel402;
    private widget.Label jLabel403;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JCheckBox pilihan1;
    private javax.swing.JCheckBox pilihan2;
    private javax.swing.JCheckBox pilihan3;
    private javax.swing.JCheckBox pilihan4;
    private javax.swing.JCheckBox pilihan5;
    private javax.swing.JCheckBox pilihan6;
    private javax.swing.JCheckBox pilihan7;
    private javax.swing.JCheckBox pilihan8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppSemua;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbMasalahDetail;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaKeperawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select penilaian_awal_keperawatan_ranap_kritis.no_rawat,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.tanggal,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.informasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.ket_informasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.tiba_diruang_rawat,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kasus_trauma,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.cara_masuk,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.keluhan_utama,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.rps,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.rpd,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.rpk,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.rpo,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_pembedahan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_dirawat_dirs,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.alat_bantu_dipakai,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_kehamilan_perkiraan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_tranfusi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_alergi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_merokok_jumlah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_alkohol_jumlah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_narkoba,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_olahraga,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_mental,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_keadaan_umum,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_gcs,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_td,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_nadi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_rr,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_suhu,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_spo2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_bb,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pemeriksaan_tb,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_jalan_nafas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_jalan_nafas_berupa,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_pernapasan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_pernapasan_dengan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_ett,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_cuff,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_frekuensi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_irama,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_kedalaman,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_spulum,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_konsistensi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_nafas_bunyi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_terdapat_darah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_jumlah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_suara_nafas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_ph,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_pco2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_po2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.breathing_sato2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_nadi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_irama,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_ekg,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_tekanan_darah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_map,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_cvp,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_ibp,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_akral,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_distensi_vena_jugulari,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_suhu,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_warna_kulit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_pengisian_kapiler,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_edema,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_perifer_edema_pada,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_irama,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_bunyi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_keluhan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_karakteristik,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_sakit_dada,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_sirkulasi_jantung_timbul,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_hb,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_ht,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_eritrosit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_leukosit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_trombosit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_pendarahan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_ct_bt,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.blood_hematologi_ptt_aptt,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_tingkat_kesadaran,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_pupil,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_reaksi_terhadap_cahaya,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_gcs_e,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_gcs_v,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_gcs_m,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_jumlah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_terjadi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_bagian,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_icp,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_cpp,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_sod,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_eud,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.brain_plakososial,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_pola_rutin,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_saat_ini,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_bak_ket,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_produksi_urin,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_warna,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_sakit_bak,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_distensi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bladder_keluhan_sakit_pinggang,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_pola_rutin,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_saat_ini,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_kosistensi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_warna,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_lendir,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_mual,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_kembung,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_distensi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_nyeri_tekan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_ngt,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bowel_intake,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_tugor_kulit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_keadaan_kulit,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_lokasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_keadaan_luka,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_sulit_dalam_gerak,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_fraktur,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_area,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_odema,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bone_kekuatan_otot,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_ibu_hamil,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.gravida_ke,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.gangguan_hamil,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.tipe_persalinan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bb_lahir,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.tb_lahir,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.lingkar_kepala_lahir,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.bb_saat_dikaji,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.tb_saat_dikaji,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.imunisasi_dasar,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.ket_belum,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_tengkurap,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_berdiri,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_bicara,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_duduk,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_berjalan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.usia_tumbuh_gigi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_mandi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_makan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_eliminasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpakaian,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_aktifitas_berpindah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_frekuesi_makan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_jenis_makanan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_nutrisi_porsi_makan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_tidur_lama_tidur,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pola_tidur_gangguan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kemampuan_sehari,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_aktifitas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_berjalan_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ambulasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_atas_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_ekstrimitas_bawah_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_menggenggam_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_koordinasi_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pengkajian_fungsi_kesimpulan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_kondisi_psiko,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_gangguan_jiwa,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_perilaku_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_hubungan_keluarga,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_tinggal_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_pendidikan_pj,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + " penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_wajib,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_ket_halangan_lain,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_toharah,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_sholat,penilaian_awal_keperawatan_ranap_kritis.riwayat_psiko_motivasi_kesembuhan_ibadah,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_penyebab,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_penyebab,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_kualitas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_kualitas,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_lokasi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_menyebar,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_skala,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_waktu,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_hilang,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_ket_hilang,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_diberitahukan_dokter,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_nyeri_jam_diberitahukan_dokter,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_usia,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_usia,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_jk,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_jk,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_diagnosis,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_diagnosis,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_gangguan_kognitif,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_gangguan_kognitif,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_faktor_lingkungan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_faktor_lingkungan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_respon_pembedahan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_respon_pembedahan,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.resiko_jatuh_medikamentosa,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_resiko_jatuh_medikamentosa,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.total_hasil_resiko_jatuh,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala5,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai5,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_skala6,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_nilai6,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.penilaian_jatuhmorse_totalnilai,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_gizi1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_gizi2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_gizi3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_gizi4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nilai_total_gizi,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diagnosa_khusus,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_ket_diagnosa_khusus,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_diketahui_dietisen,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.skrining_gizi_jam_diketahui_dietisen,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kriteria1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kriteria2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kriteria3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kriteria4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan3,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan4,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan5,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan6,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan7,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.pilihan8,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.rencana,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nip1,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.nip2,"
                    + "	penilaian_awal_keperawatan_ranap_kritis.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_kritis on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_kritis.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_kritis.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_kritis.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_kritis.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                    + "penilaian_awal_keperawatan_ranap_kritis.tanggal between ? and ? "
                    + (TCari.getText().trim().isEmpty() ? ""
                    : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_ranap_kritis.nip1 like ? or "
                    + "pengkaji1.nama like ? or penilaian_awal_keperawatan_ranap_kritis.kd_dokter like ? or dokter.nm_dokter like ?)")
                    + " order by penilaian_awal_keperawatan_ranap_kritis.tanggal");

            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                if (!TCari.getText().isEmpty()) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{rs.getString("no_rawat"), rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"),
                        rs.getString("nip1"), rs.getString("pengkaji1"), rs.getString("nip2"),
                        rs.getString("pengkaji2"), rs.getString("kd_dokter"), rs.getString("nm_dokter"),
                        rs.getString("tanggal"), rs.getString("kasus_trauma"),
                        rs.getString("informasi") + ", " + rs.getString("ket_informasi"),
                        rs.getString("tiba_diruang_rawat"), rs.getString("cara_masuk"),
                        rs.getString("keluhan_utama"), rs.getString("rps"), rs.getString("rpd"),
                        rs.getString("rpk"), rs.getString("rpo"), rs.getString("riwayat_pembedahan"),
                        rs.getString("riwayat_dirawat_dirs"), rs.getString("alat_bantu_dipakai"),
                        rs.getString("riwayat_kehamilan") + ", " + rs.getString("riwayat_kehamilan_perkiraan"),
                        rs.getString("riwayat_tranfusi"), rs.getString("riwayat_alergi"),
                        rs.getString("riwayat_merokok"), rs.getString("riwayat_merokok_jumlah"),
                        rs.getString("riwayat_alkohol"), rs.getString("riwayat_alkohol_jumlah"),
                        rs.getString("riwayat_narkoba"), rs.getString("riwayat_olahraga"),
                        rs.getString("pemeriksaan_mental"), rs.getString("pemeriksaan_keadaan_umum"),
                        rs.getString("pemeriksaan_gcs"), rs.getString("pemeriksaan_td"),
                        rs.getString("pemeriksaan_nadi"), rs.getString("pemeriksaan_rr"),
                        rs.getString("pemeriksaan_suhu"), rs.getString("pemeriksaan_spo2"),
                        rs.getString("pemeriksaan_bb"), rs.getString("pemeriksaan_tb"),
                        rs.getString("breathing_jalan_nafas"), rs.getString("breathing_jalan_nafas_berupa"),
                        rs.getString("breathing_pernapasan"), rs.getString("breathing_pernapasan_dengan"),
                        rs.getString("breathing_ett"), rs.getString("breathing_cuff"),
                        rs.getString("breathing_frekuensi"), rs.getString("breathing_irama"),
                        rs.getString("breathing_kedalaman"), rs.getString("breathing_spulum"),
                        rs.getString("breathing_konsistensi"), rs.getString("breathing_nafas_bunyi"),
                        rs.getString("breathing_terdapat_darah"), rs.getString("breathing_jumlah"),
                        rs.getString("breathing_suara_nafas"), rs.getString("breathing_ph"),
                        rs.getString("breathing_pco2"), rs.getString("breathing_po2"),
                        rs.getString("breathing_sato2"), rs.getString("blood_sirkulasi_perifer_nadi"),
                        rs.getString("blood_sirkulasi_perifer_irama"), rs.getString("blood_sirkulasi_perifer_ekg"),
                        rs.getString("blood_sirkulasi_perifer_tekanan_darah"),
                        rs.getString("blood_sirkulasi_perifer_map"), rs.getString("blood_sirkulasi_perifer_cvp"),
                        rs.getString("blood_sirkulasi_perifer_ibp"), rs.getString("blood_sirkulasi_perifer_akral"),
                        rs.getString("blood_sirkulasi_perifer_distensi_vena_jugulari"),
                        rs.getString("blood_sirkulasi_perifer_suhu"),
                        rs.getString("blood_sirkulasi_perifer_warna_kulit"),
                        rs.getString("blood_sirkulasi_perifer_pengisian_kapiler"),
                        rs.getString("blood_sirkulasi_perifer_edema"),
                        rs.getString("blood_sirkulasi_perifer_edema_pada"),
                        rs.getString("blood_sirkulasi_jantung_irama"),
                        rs.getString("blood_sirkulasi_jantung_bunyi"),
                        rs.getString("blood_sirkulasi_jantung_keluhan"),
                        rs.getString("blood_sirkulasi_jantung_karakteristik"),
                        rs.getString("blood_sirkulasi_jantung_sakit_dada"),
                        rs.getString("blood_sirkulasi_jantung_timbul"), rs.getString("blood_hematologi_hb"),
                        rs.getString("blood_hematologi_ht"), rs.getString("blood_hematologi_eritrosit"),
                        rs.getString("blood_hematologi_leukosit"), rs.getString("blood_hematologi_trombosit"),
                        rs.getString("blood_hematologi_pendarahan"), rs.getString("blood_hematologi_ct_bt"),
                        rs.getString("blood_hematologi_ptt_aptt"), rs.getString("brain_tingkat_kesadaran"),
                        rs.getString("brain_pupil"), rs.getString("brain_reaksi_terhadap_cahaya"),
                        rs.getString("brain_gcs_e"), rs.getString("brain_gcs_v"), rs.getString("brain_gcs_m"),
                        rs.getString("brain_jumlah"), rs.getString("brain_terjadi"), rs.getString("brain_bagian"),
                        rs.getString("brain_icp"), rs.getString("brain_cpp"), rs.getString("brain_sod"),
                        rs.getString("brain_eud"), rs.getString("brain_plakososial"),
                        rs.getString("bladder_pola_rutin"), rs.getString("bladder_saat_ini"),
                        rs.getString("bladder_bak_ket"), rs.getString("bladder_produksi_urin"),
                        rs.getString("bladder_warna"), rs.getString("bladder_sakit_bak"),
                        rs.getString("bladder_distensi"), rs.getString("bladder_keluhan_sakit_pinggang"),
                        rs.getString("bowel_pola_rutin"), rs.getString("bowel_saat_ini"),
                        rs.getString("bowel_kosistensi"), rs.getString("bowel_warna"), rs.getString("bowel_lendir"),
                        rs.getString("bowel_mual"), rs.getString("bowel_kembung"), rs.getString("bowel_distensi"),
                        rs.getString("bowel_nyeri_tekan"), rs.getString("bowel_ngt"), rs.getString("bowel_intake"),
                        rs.getString("bone_tugor_kulit"), rs.getString("bone_keadaan_kulit"),
                        rs.getString("bone_lokasi"), rs.getString("bone_keadaan_luka"),
                        rs.getString("bone_sulit_dalam_gerak"), rs.getString("bone_fraktur"),
                        rs.getString("bone_area"), rs.getString("bone_odema"), rs.getString("bone_kekuatan_otot"),
                        rs.getString("usia_ibu_hamil"), rs.getString("gravida_ke"), rs.getString("gangguan_hamil"),
                        rs.getString("tipe_persalinan"), rs.getString("bb_lahir"), rs.getString("tb_lahir"),
                        rs.getString("lingkar_kepala_lahir"), rs.getString("bb_saat_dikaji"),
                        rs.getString("tb_saat_dikaji"), rs.getString("imunisasi_dasar"), rs.getString("ket_belum"),
                        rs.getString("usia_tengkurap"), rs.getString("usia_berdiri"), rs.getString("usia_bicara"),
                        rs.getString("usia_duduk"), rs.getString("usia_berjalan"), rs.getString("usia_tumbuh_gigi"),
                        rs.getString("pola_aktifitas_mandi"), rs.getString("pola_aktifitas_makan"),
                        rs.getString("pola_aktifitas_berpakaian"), rs.getString("pola_aktifitas_eliminasi"),
                        rs.getString("pola_aktifitas_berpindah"), rs.getString("pola_nutrisi_porsi_makan"),
                        rs.getString("pola_nutrisi_frekuesi_makan"), rs.getString("pola_nutrisi_jenis_makanan"),
                        rs.getString("pola_tidur_lama_tidur"), rs.getString("pola_tidur_gangguan"),
                        rs.getString("pengkajian_fungsi_kemampuan_sehari"),
                        rs.getString("pengkajian_fungsi_berjalan") + ", "
                        + rs.getString("pengkajian_fungsi_berjalan_keterangan"),
                        rs.getString("pengkajian_fungsi_aktifitas"), rs.getString("pengkajian_fungsi_ambulasi"),
                        rs.getString("pengkajian_fungsi_ekstrimitas_atas") + ", "
                        + rs.getString("pengkajian_fungsi_ekstrimitas_atas_keterangan"),
                        rs.getString("pengkajian_fungsi_ekstrimitas_bawah") + ", "
                        + rs.getString("pengkajian_fungsi_ekstrimitas_bawah_keterangan"),
                        rs.getString("pengkajian_fungsi_menggenggam") + ", "
                        + rs.getString("pengkajian_fungsi_menggenggam_keterangan"),
                        rs.getString("pengkajian_fungsi_koordinasi") + ", "
                        + rs.getString("pengkajian_fungsi_koordinasi_keterangan"),
                        rs.getString("pengkajian_fungsi_kesimpulan"), rs.getString("riwayat_psiko_kondisi_psiko"),
                        rs.getString("riwayat_psiko_perilaku") + ", "
                        + rs.getString("riwayat_psiko_perilaku_keterangan"),
                        rs.getString("riwayat_psiko_gangguan_jiwa"),
                        rs.getString("riwayat_psiko_hubungan_keluarga"), rs.getString("agama"),
                        rs.getString("riwayat_psiko_tinggal") + ", "
                        + rs.getString("riwayat_psiko_tinggal_keterangan"),
                        rs.getString("pekerjaan"), rs.getString("png_jawab"),
                        rs.getString("riwayat_psiko_nilai_kepercayaan") + ", "
                        + rs.getString("riwayat_psiko_nilai_kepercayaan_keterangan"),
                        rs.getString("nama_bahasa"), rs.getString("pnd"),
                        rs.getString("riwayat_psiko_pendidikan_pj"),
                        rs.getString("riwayat_psiko_edukasi_diberikan") + ", "
                        + rs.getString("riwayat_psiko_edukasi_diberikan_keterangan"),
                        rs.getString("riwayat_psiko_wajib"), rs.getString("riwayat_psiko_ket_halangan_lain"),
                        rs.getString("riwayat_psiko_toharah"), rs.getString("riwayat_psiko_sholat"),
                        rs.getString("riwayat_psiko_motivasi_kesembuhan_ibadah"), rs.getString("penilaian_nyeri"),
                        rs.getString("penilaian_nyeri_penyebab") + ", "
                        + rs.getString("penilaian_nyeri_ket_penyebab"),
                        rs.getString("penilaian_nyeri_kualitas") + ", "
                        + rs.getString("penilaian_nyeri_ket_kualitas"),
                        rs.getString("penilaian_nyeri_lokasi"), rs.getString("penilaian_nyeri_menyebar"),
                        rs.getString("penilaian_nyeri_skala"), rs.getString("penilaian_nyeri_waktu"),
                        rs.getString("penilaian_nyeri_hilang") + ", " + rs.getString("penilaian_nyeri_ket_hilang"),
                        rs.getString("penilaian_nyeri_diberitahukan_dokter") + ", "
                        + rs.getString("penilaian_nyeri_jam_diberitahukan_dokter"),
                        rs.getString("resiko_jatuh_usia"), rs.getString("nilai_resiko_jatuh_usia"),
                        rs.getString("resiko_jatuh_jk"), rs.getString("nilai_resiko_jatuh_jk"),
                        rs.getString("resiko_jatuh_diagnosis"), rs.getString("nilai_resiko_jatuh_diagnosis"),
                        rs.getString("resiko_jatuh_gangguan_kognitif"),
                        rs.getString("nilai_resiko_jatuh_gangguan_kognitif"),
                        rs.getString("resiko_jatuh_faktor_lingkungan"),
                        rs.getString("nilai_resiko_jatuh_faktor_lingkungan"),
                        rs.getString("resiko_jatuh_respon_pembedahan"),
                        rs.getString("nilai_resiko_jatuh_respon_pembedahan"),
                        rs.getString("resiko_jatuh_medikamentosa"),
                        rs.getString("nilai_resiko_jatuh_medikamentosa"), rs.getString("total_hasil_resiko_jatuh"),
                        rs.getString("penilaian_jatuhmorse_skala1"), rs.getString("penilaian_jatuhmorse_nilai1"),
                        rs.getString("penilaian_jatuhmorse_skala2"), rs.getString("penilaian_jatuhmorse_nilai2"),
                        rs.getString("penilaian_jatuhmorse_skala3"), rs.getString("penilaian_jatuhmorse_nilai3"),
                        rs.getString("penilaian_jatuhmorse_skala4"), rs.getString("penilaian_jatuhmorse_nilai4"),
                        rs.getString("penilaian_jatuhmorse_skala5"), rs.getString("penilaian_jatuhmorse_nilai5"),
                        rs.getString("penilaian_jatuhmorse_skala6"), rs.getString("penilaian_jatuhmorse_nilai6"),
                        rs.getString("penilaian_jatuhmorse_totalnilai"), rs.getString("skrining_gizi1"),
                        rs.getString("nilai_gizi1"), rs.getString("skrining_gizi2"), rs.getString("nilai_gizi2"),
                        rs.getString("skrining_gizi3"), rs.getString("nilai_gizi3"), rs.getString("skrining_gizi4"),
                        rs.getString("nilai_gizi4"), rs.getString("nilai_total_gizi"),
                        rs.getString("skrining_gizi_diagnosa_khusus"),
                        rs.getString("skrining_gizi_ket_diagnosa_khusus"),
                        rs.getString("skrining_gizi_diketahui_dietisen"),
                        rs.getString("skrining_gizi_jam_diketahui_dietisen"), rs.getString("kriteria1"),
                        rs.getString("kriteria2"), rs.getString("kriteria3"), rs.getString("kriteria4"),
                        rs.getString("pilihan1"), rs.getString("pilihan2"), rs.getString("pilihan3"),
                        rs.getString("pilihan4"), rs.getString("pilihan5"), rs.getString("pilihan6"),
                        rs.getString("pilihan7"), rs.getString("pilihan8"), rs.getString("rencana")});
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TglAsuhan.setDate(new Date());
        Anamnesis.setSelectedIndex(0);
        KetAnamnesis.setText("");
        TibadiRuang.setSelectedIndex(0);
        CaraMasuk.setSelectedIndex(0);
        MacamKasus.setSelectedIndex(0);
        KeluhanUtama.setText("");
        RPS.setText("");
        RPD.setText("");
        RPK.setText("");
        RPO.setText("");
        RPembedahan.setText("");
        RDirawatRS.setText("");
        AlatBantuDipakai.setSelectedIndex(0);
        SedangMenyusui.setSelectedIndex(0);
        KetSedangMenyusui.setText("");
        RTranfusi.setText("");
        Alergi.setText("");
        KebiasaanMerokok.setSelectedIndex(0);
        KebiasaanJumlahRokok.setText("");
        KebiasaanAlkohol.setSelectedIndex(0);
        KebiasaanJumlahAlkohol.setText("");
        KebiasaanNarkoba.setSelectedIndex(0);
        OlahRaga.setSelectedIndex(0);
        KesadaranMental.setText("");
        KeadaanMentalUmum.setSelectedIndex(0);
        GCS.setText("");
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        SpO2.setText("");
        BB.setText("");
        TB.setText("");
        BreathingJalanNafas.setSelectedIndex(0);
        PolaAktifitasMandi.setSelectedIndex(0);
        PolaAktifitasMakan.setSelectedIndex(0);
        PolaAktifitasBerpakaian.setSelectedIndex(0);
        PolaAktifitasEliminasi.setSelectedIndex(0);
        PolaAktifitasBerpindah.setSelectedIndex(0);
        PolaNutrisiPorsi.setText("");
        PolaNutrisiFrekuensi.setText("");
        PolaNutrisiJenis.setText("");
        PolaTidurLama.setText("");
        PolaTidurGangguan.setSelectedIndex(0);
        AktifitasSehari2.setSelectedIndex(0);
        Berjalan.setSelectedIndex(0);
        KeteranganBerjalan.setText("");
        Aktifitas.setSelectedIndex(0);
        AlatAmbulasi.setSelectedIndex(0);
        EkstrimitasAtas.setSelectedIndex(0);
        KeteranganEkstrimitasAtas.setText("");
        EkstrimitasBawah.setSelectedIndex(0);
        KeteranganEkstrimitasBawah.setText("");
        KemampuanMenggenggam.setSelectedIndex(0);
        KeteranganKemampuanMenggenggam.setText("");
        KemampuanKoordinasi.setSelectedIndex(0);
        KeteranganKemampuanKoordinasi.setText("");
        KesimpulanGangguanFungsi.setSelectedIndex(0);
        KondisiPsikologis.setSelectedIndex(0);
        AdakahPerilaku.setSelectedIndex(0);
        KeteranganAdakahPerilaku.setText("");
        GangguanJiwa.setSelectedIndex(0);
        HubunganAnggotaKeluarga.setSelectedIndex(0);
        TinggalDengan.setSelectedIndex(0);
        KeteranganTinggalDengan.setText("");
        NilaiKepercayaan.setSelectedIndex(0);
        KeteranganNilaiKepercayaan.setText("");
        PendidikanPJ.setSelectedIndex(0);
        EdukasiPsikolgis.setSelectedIndex(0);
        KeteranganEdukasiPsikologis.setText("");
        Wajib.setSelectedIndex(0);
        KetHalanganLain.setText("");
        Toharah.setSelectedIndex(0);
        Sholat.setSelectedIndex(0);
        MotivasiKesembuhanIbadah.setSelectedIndex(0);
        Nyeri.setSelectedIndex(0);
        Provokes.setSelectedIndex(0);
        KetProvokes.setText("");
        Quality.setSelectedIndex(0);
        KetQuality.setText("");
        Lokasi.setText("");
        Menyebar.setSelectedIndex(0);
        SkalaNyeri.setSelectedIndex(0);
        Durasi.setText("");
        NyeriHilang.setSelectedIndex(0);
        KetNyeri.setText("");
        PadaDokter.setSelectedIndex(0);
        KetPadaDokter.setText("");
        SkalaHumptyDumpty1.setSelectedIndex(0);
        NilaiHumptyDumpty1.setText("");
        SkalaHumptyDumpty2.setSelectedIndex(0);
        NilaiHumptyDumpty2.setText("");
        SkalaHumptyDumpty3.setSelectedIndex(0);
        NilaiHumptyDumpty3.setText("");
        SkalaHumptyDumpty4.setSelectedIndex(0);
        NilaiHumptyDumpty4.setText("");
        SkalaHumptyDumpty5.setSelectedIndex(0);
        NilaiHumptyDumpty5.setText("");
        SkalaHumptyDumpty6.setSelectedIndex(0);
        NilaiHumptyDumpty6.setText("");
        SkalaHumptyDumpty7.setSelectedIndex(0);
        NilaiHumptyDumpty7.setText("");
        NilaiHumptyDumptyTotal.setText("");
        TingkatHumptyDumpty
                .setText("Tingkat Resiko : Risiko Tinggi >12, Tindakan : Intervensi pencegahan risiko jatuh standar");
        SkalaResiko1.setSelectedIndex(0);
        NilaiResiko1.setText("");
        SkalaResiko2.setSelectedIndex(0);
        NilaiResiko2.setText("");
        SkalaResiko3.setSelectedIndex(0);
        NilaiResiko3.setText("");
        SkalaResiko4.setSelectedIndex(0);
        NilaiResiko4.setText("");
        SkalaResiko5.setSelectedIndex(0);
        NilaiResiko5.setText("");
        SkalaResiko6.setSelectedIndex(0);
        NilaiResiko6.setText("");
        NilaiResikoTotal.setText("");
        TingkatResiko
                .setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        SkalaGizi1.setSelectedIndex(0);
        NilaiGizi1.setText("0");
        SkalaGizi2.setSelectedIndex(0);
        NilaiGizi2.setText("0");
        SkalaGizi3.setSelectedIndex(0);
        NilaiGizi3.setText("0");
        SkalaGizi4.setSelectedIndex(0);
        NilaiGizi4.setText("0");
        NilaiGiziTotal.setText("0");
        DiagnosaKhususGizi.setSelectedIndex(0);
        KeteranganDiagnosaKhususGizi.setText("");
        DiketahuiDietisen.setSelectedIndex(0);
        KeteranganDiketahuiDietisen.setText("");
        Kriteria1.setSelectedIndex(0);
        Kriteria2.setSelectedIndex(0);
        Kriteria3.setSelectedIndex(0);
        Kriteria4.setSelectedIndex(0);
        pilihan1.setSelected(false);
        pilihan2.setSelected(false);
        pilihan3.setSelected(false);
        pilihan4.setSelected(false);
        pilihan5.setSelected(false);
        pilihan6.setSelected(false);
        pilihan7.setSelected(false);
        pilihan8.setSelected(false);
        Rencana.setText("");
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false, i, 0);
        }
        Valid.tabelKosong(tabModeRencana);
        TabRawat.setSelectedIndex(0);
        MacamKasus.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            KdPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NmPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            KdDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            MacamKasus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString().contains("Autoanamnesis")) {
                Anamnesis.setSelectedItem("Autoanamnesis");
            } else {
                Anamnesis.setSelectedItem("Alloanamnesis");
            }
            KetAnamnesis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13)
                    .toString()
                    .replaceAll(Anamnesis.getSelectedItem().toString() + ", ", ""));
            TibadiRuang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            CaraMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            RPembedahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            RDirawatRS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            AlatBantuDipakai.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString().contains("Ya")) {
                SedangMenyusui.setSelectedItem("Ya");
            } else {
                SedangMenyusui.setSelectedItem("Tidak");
            }
            KetSedangMenyusui.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24)
                    .toString()
                    .replaceAll(SedangMenyusui.getSelectedItem().toString() + ", ", ""));
            RTranfusi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
            KebiasaanMerokok.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
            KebiasaanJumlahRokok.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString());
            KebiasaanAlkohol.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString());
            KebiasaanJumlahAlkohol.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString());
            KebiasaanNarkoba.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString());
            OlahRaga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString());
            KesadaranMental.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 33).toString());
            KeadaanMentalUmum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 35).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 36).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 37).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 38).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 39).toString());
            SpO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 40).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 41).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 42).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString().contains("Bersih")) {
                BreathingJalanNafas.setSelectedItem("Bersih");
            } else {
                BreathingJalanNafas.setSelectedItem("Sumbatan");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString().contains("Sputum/benda")) {
                BreathingJalanNafasBerupa.setSelectedItem("Sputum/benda");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString().contains("Benda asing")) {
                BreathingJalanNafasBerupa.setSelectedItem("Benda asing");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString().contains("Darah")) {
                BreathingJalanNafasBerupa.setSelectedItem("Darah");
            } else {
                BreathingJalanNafasBerupa.setSelectedItem("Lidah");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 45).toString().contains("Tidak")) {
                BreathingPernapasan.setSelectedItem("Tidak");
            } else {
                BreathingPernapasan.setSelectedItem("Ya");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString().contains("")) {
                BreathingPernapasanDengan.setSelectedItem("");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString().contains("Aktivitas")) {
                BreathingPernapasanDengan.setSelectedItem("Aktivitas");
            } else {
                BreathingPernapasanDengan.setSelectedItem("Tanpa aktivitas");
            }
            BreathingETT.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString());
            BreathingCuff.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 48).toString());
            BreathingFrekuensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 50).toString().contains("Teratur")) {
                BreathingIrama.setSelectedItem("Teratur");
            } else {
                BreathingIrama.setSelectedItem("Tidak teratur");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 51).toString().contains("Teratur")) {
                BreathingKedalaman.setSelectedItem("Teratur");
            } else {
                BreathingKedalaman.setSelectedItem("Dangkal");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 52).toString().contains("Putih")) {
                BreathingSpulum.setSelectedItem("Putih");
            } else {
                BreathingSpulum.setSelectedItem("Kuning/abu");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 53).toString().contains("Kental")) {
                BreathingKonsistensi.setSelectedItem("Kental");
            } else {
                BreathingKonsistensi.setSelectedItem("Encer");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 54).toString().contains("Ya")) {
                BreathingNafasBunyi.setSelectedItem("Ya");
            } else {
                BreathingNafasBunyi.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 55).toString().contains("Ya")) {
                BreathingTerdapatDarah.setSelectedItem("Ya");
            } else {
                BreathingTerdapatDarah.setSelectedItem("Tidak");
            }
            BreathingTerdapatDarahJumlah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 56).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString().contains("Ronchi")) {
                BreathingSuaraNafas.setSelectedItem("Ronchi");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString().contains("Wheesing")) {
                BreathingSuaraNafas.setSelectedItem("Wheesing");
            } else {
                BreathingSuaraNafas.setSelectedItem("Vesikuler");
            }
            BreathingAnalisaGasDarahPH.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString());
            BreathingAnalisaGasDarahpCO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString());
            BreathingAnalisaGasDarahpO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString());
            BreathingAnalisaGasDarahSatO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString());
            BloodSirkulasiPeriferNadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString().contains("Teratur")) {
                BloodSirkulasiPeriferIrama.setSelectedItem("Teratur");
            } else {
                BloodSirkulasiPeriferIrama.setSelectedItem("Tidak teratur");
            }
            BloodSirkulasiPeriferEKG.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 64).toString());
            BloodSirkulasiPeriferTekananDarah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 65).toString());
            BloodSirkulasiPeriferMAP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 66).toString());
            BloodSirkulasiPeriferCVP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 67).toString());
            BloodSirkulasiPeriferIBP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 68).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 69).toString().contains("Hangat")) {
                BloodSirkulasiPeriferAkral.setSelectedItem("Hangat");
            } else {
                BloodSirkulasiPeriferAkral.setSelectedItem("Dingin");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString().contains("Tidak")) {
                BloodSirkulasiPeriferDistensiVenaJugulari.setSelectedItem("Tidak");
            } else {
                BloodSirkulasiPeriferDistensiVenaJugulari.setSelectedItem("Ya");
            }
            BloodSirkulasiPeriferSuhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString().contains("Pucat")) {
                BloodSirkulasiPeriferWarnaKulit.setSelectedItem("Pucat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString().contains("Kemerahan")) {
                BloodSirkulasiPeriferWarnaKulit.setSelectedItem("Kemerahan");
            } else {
                BloodSirkulasiPeriferWarnaKulit.setSelectedItem("Sianosis");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString().contains("< 3 detik")) {
                BloodSirkulasiPeriferPengisianKaplier.setSelectedItem("< 3 detik");
            } else {
                BloodSirkulasiPeriferPengisianKaplier.setSelectedItem("> 3 detik");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString().contains("Tidak")) {
                BloodSirkulasiPeriferEdema.setSelectedItem("Tidak");
            } else {
                BloodSirkulasiPeriferEdema.setSelectedItem("Ya");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString().contains("")) {
                BloodSirkulasiPeriferEdemaPada.setSelectedItem("");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString().contains("Muka")) {
                BloodSirkulasiPeriferEdemaPada.setSelectedItem("Muka");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString().contains("Tungkai atas")) {
                BloodSirkulasiPeriferEdemaPada.setSelectedItem("Tungkai atas");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString().contains("Tungkai bawah")) {
                BloodSirkulasiPeriferEdemaPada.setSelectedItem("Tungkai bawah");
            } else {
                BloodSirkulasiPeriferEdemaPada.setSelectedItem("Seluruh tubuh");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString().contains("Teratur")) {
                BloodSirkulasiJantungJantungIrama.setSelectedItem("Teratur");
            } else {
                BloodSirkulasiJantungJantungIrama.setSelectedItem("Tidak teratur");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString().contains("BJ I")) {
                BloodSirkulasiJantungBunyi.setSelectedItem("BJ I");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString().contains("BJ II")) {
                BloodSirkulasiJantungBunyi.setSelectedItem("BJ II");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString().contains("Murmum")) {
                BloodSirkulasiJantungBunyi.setSelectedItem("Murmur");
            } else {
                BloodSirkulasiJantungBunyi.setSelectedItem("Gallop");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString().contains("Lelah")) {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Lelah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString().contains("Berdebar-debar")) {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Berdebar-debar");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString().contains("Kesemutan")) {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Kesemutan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString().contains("Keringat dingin")) {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Keringat dingin");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString().contains("Gemetaran")) {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Gemetaran");
            } else {
                BloodSirkulasiJantungKeluhan.setSelectedItem("Tidak ada");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString().contains("Seperti ditusuk-tusuk")) {
                BloodSirkulasiJantungKarakteristik.setSelectedItem("Seperti ditusuk-tusuk");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString().contains("Seperti terbakar")) {
                BloodSirkulasiJantungKarakteristik.setSelectedItem("Seperti terbakar");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString().contains("Menyebar")) {
                BloodSirkulasiJantungKarakteristik.setSelectedItem("Menyebar");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 79)
                    .toString()
                    .contains("Seperti tertimpa beban berat")) {
                BloodSirkulasiJantungKarakteristik.setSelectedItem("Seperti tertimpa beban berat");
            } else {
                BloodSirkulasiJantungKarakteristik.setSelectedItem("Tidak ada atau min");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 80).toString().contains("Ya")) {
                BloodSirkulasiJantungSakitDada.setSelectedItem("Ya");
            } else {
                BloodSirkulasiJantungSakitDada.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString().contains("")) {
                BloodSirkulasiJantungSakitDadaTimbul.setSelectedItem("");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString().contains("Saat aktifitas")) {
                BloodSirkulasiJantungSakitDadaTimbul.setSelectedItem("Saat aktifitas");
            } else {
                BloodSirkulasiJantungSakitDadaTimbul.setSelectedItem("Tanpa aktifitas");
            }
            BloodHematologiHB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 82).toString());
            BloodHematologiHt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 83).toString());
            BloodHematologiEritrosit.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 84).toString());
            BloodHematologiLeukosit.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 85).toString());
            BloodHematologiTrombosit.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 86).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Gusi mudah berdarah")) {
                BloodHematologiPendarahan.setSelectedItem("Gusi mudah berdarah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Mimisan")) {
                BloodHematologiPendarahan.setSelectedItem("Mimisan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Petechie")) {
                BloodHematologiPendarahan.setSelectedItem("Petechie");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Echimosis")) {
                BloodHematologiPendarahan.setSelectedItem("Echimosis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Terus menerus")) {
                BloodHematologiPendarahan.setSelectedItem("Terus menerus");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Lemah")) {
                BloodHematologiPendarahan.setSelectedItem("Lemah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString().contains("Pucat")) {
                BloodHematologiPendarahan.setSelectedItem("Pucat");
            } else {
                BloodHematologiPendarahan.setSelectedItem("Tanpa ada atau min");
            }
            BloodHematologiCT.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 88).toString());
            BloodHematologiPTT.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 89).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString().contains("Composmentis")) {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Composmentis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString().contains("Apatis")) {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Apatis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString().contains("Somnolen")) {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Somnolen");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString().contains("Delirium")) {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Delirium");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString().contains("Sopor")) {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Sopor");
            } else {
                BrainSirkulasiSerebralTingkatKesadaran.setSelectedItem("Coma");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString().contains("Isokor")) {
                BrainSirkulasiSerebralPupil.setSelectedItem("Isokor");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString().contains("Unisokor")) {
                BrainSirkulasiSerebralPupil.setSelectedItem("Unisokor");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString().contains("Miosis")) {
                BrainSirkulasiSerebralPupil.setSelectedItem("Miosis");
            } else {
                BrainSirkulasiSerebralPupil.setSelectedItem("Midriasis");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 92).toString().contains("Positif")) {
                BrainSirkulasiSerebralReaksiTerhadapCahaya.setSelectedItem("Positif");
            } else {
                BrainSirkulasiSerebralReaksiTerhadapCahaya.setSelectedItem("Negatif");
            }
            BrainSirkulasiSerebralGCSE.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 93).toString());
            BrainSirkulasiSerebralGCSV.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 94).toString());
            BrainSirkulasiSerebralGCSM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 95).toString());
            BrainSirkulasiSerebralGCSJumlah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 96).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 97).toString().contains("Kejang")) {
                BrainSirkulasiSerebralTerjadi.setSelectedItem("Kejang");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 97).toString().contains("Lumpuh")) {
                BrainSirkulasiSerebralTerjadi.setSelectedItem("Lumpuh");
            } else {
                BrainSirkulasiSerebralTerjadi.setSelectedItem("Tidak ada atau min");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString().contains("")) {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString().contains("Kanan")) {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("Kanan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString().contains("Kiri")) {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("Kiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString().contains("Pola")) {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("Pola");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString().contains("Mulut menceng")) {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("Mulut menceng");
            } else {
                BrainSirkulasiSerebralTerjadiBagian.setSelectedItem("Aphasia");
            }
            BrainSirkulasiSerebralICP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 99).toString());
            BrainSirkulasiSerebralCPP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 100).toString());
            BrainSirkulasiSerebralSOD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 101).toString());
            BrainSirkulasiSerebralEUD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 102).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Sedih/murung")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Sedih/murung");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Menyendiri")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Menyendiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Kebersihan diri kurang")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Kebersihan diri kurang");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Ekspresi wajah datar")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Ekspresi wajah datar");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Ekspresi wajah tegang")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Ekspresi wajah tegang");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Mata merah")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Mata merah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Marah-marah")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Marah-marah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString().contains("Bicara sendiri")) {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Bicara sendiri");
            } else {
                BrainSirkulasiSerebralPalkososial.setSelectedItem("Gelisah/mondar-mandir");
            }
            BladderBAKPolaRutin.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 104).toString());
            BladderBAKSaatIni.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 105).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 106).toString().contains("Terkontrol")) {
                BladderBAKTerkontrol.setSelectedItem("Terkontrol");
            } else {
                BladderBAKTerkontrol.setSelectedItem("Tidak terkontrol");
            }
            BladderProduksiUrin.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 107).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 108).toString().contains("Kuning jernih")) {
                BladderWarna.setSelectedItem("Kuning jernih");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 108).toString().contains("Kuning kental/coklat")) {
                BladderWarna.setSelectedItem("Kuning kental/coklat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 108).toString().contains("Merah")) {
                BladderWarna.setSelectedItem("Merah");
            } else {
                BladderWarna.setSelectedItem("Bening");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 109).toString().contains("Ya")) {
                BladderSakitWaktuBAK.setSelectedItem("Ya");
            } else {
                BladderSakitWaktuBAK.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 110).toString().contains("Ya")) {
                BladderDistensi.setSelectedItem("Ya");
            } else {
                BladderDistensi.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 111).toString().contains("Ya")) {
                BladderSakitPinggang.setSelectedItem("Ya");
            } else {
                BladderSakitPinggang.setSelectedItem("Tidak");
            }
            BowelBAKPolaRutin.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 112).toString());
            BowelBAKSaatIni.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 113).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 114).toString().contains("Padat")) {
                BowelKonsistensi.setSelectedItem("Padat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 114).toString().contains("Encer/cair")) {
                BowelKonsistensi.setSelectedItem("Encer/cair");
            } else {
                BowelKonsistensi.setSelectedItem("Lunak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 115).toString().contains("Kuning")) {
                BowelWarna.setSelectedItem("Kuning");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 115).toString().contains("Hitam")) {
                BowelWarna.setSelectedItem("Hitam");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 115).toString().contains("Merah")) {
                BowelWarna.setSelectedItem("Merah");
            } else {
                BowelWarna.setSelectedItem("Dempul/pucat");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Ya")) {
                BowelLendir.setSelectedItem("Ya");
            } else {
                BowelLendir.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 117).toString().contains("Ya")) {
                BowelMual.setSelectedItem("Ya");
            } else {
                BowelMual.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 118).toString().contains("Ya")) {
                BowelKembung.setSelectedItem("Ya");
            } else {
                BowelKembung.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString().contains("Ya")) {
                BowelDistensi.setSelectedItem("Ya");
            } else {
                BowelDistensi.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString().contains("Ya")) {
                BowelKonsistensi.setSelectedItem("Ya");
            } else {
                BowelKonsistensi.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 121).toString().contains("Ya")) {
                BowelNGT.setSelectedItem("Ya");
            } else {
                BowelNGT.setSelectedItem("Tidak");
            }
            BowelIntake.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 122).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 123).toString().contains("Buruk")) {
                BoneTugorKulit.setSelectedItem("Buruk");
            } else {
                BoneTugorKulit.setSelectedItem("Baik/elastic");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 124).toString().contains("Baik")) {
                BoneKeadaanKulit.setSelectedItem("Baik");
            } else {
                BoneKeadaanKulit.setSelectedItem("Terdapat luka operasi");
            }
            BoneLokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 126).toString().contains("Baik")) {
                BoneKeadaanLuka.setSelectedItem("Baik");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 126).toString().contains("Infeksi/gangren")) {
                BoneKeadaanLuka.setSelectedItem("Infeksi/gangren");
            } else {
                BoneKeadaanLuka.setSelectedItem("Tidak ada atau min");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 127).toString().contains("Ya")) {
                BoneSulitDalamGerak.setSelectedItem("Ya");
            } else {
                BoneSulitDalamGerak.setSelectedItem("Tidak");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 128).toString().contains("Ya")) {
                BoneFraktur.setSelectedItem("Ya");
            } else {
                BoneFraktur.setSelectedItem("Tidak");
            }
            BoneArea.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 130).toString().contains("Ya")) {
                BoneOdema.setSelectedItem("Ya");
            } else {
                BoneOdema.setSelectedItem("Tidak");
            }
            BoneKekuatanOtot.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 131).toString());
            UsiaIbuSaatHamil.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 132).toString());
            GravidaKe.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 133).toString());
            GangguanHamil.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 134).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString().contains("Spontan")) {
                TipePersalinan.setSelectedItem("Spontan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString().contains("Induksi")) {
                TipePersalinan.setSelectedItem("Induksi");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString().contains("Forcep")) {
                TipePersalinan.setSelectedItem("Forcep");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString().contains("Vacum")) {
                TipePersalinan.setSelectedItem("Vacum");
            } else {
                TipePersalinan.setSelectedItem("SC");
            }
            BBLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 136).toString());
            TBLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 137).toString());
            LingkarKepalaLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 138).toString());
            BBDiKaji.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 139).toString());
            TBDiKaji.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 140).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 141).toString().contains("")) {
                ImunisasiDasar.setSelectedItem("");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 141).toString().contains("Lengkap")) {
                ImunisasiDasar.setSelectedItem("Lengkap");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 141).toString().contains("Tidak pernah")) {
                ImunisasiDasar.setSelectedItem("Tidak pernah");
            } else {
                ImunisasiDasar.setSelectedItem("Tidak lengkap");
            }
            ImunisasiBelum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 142).toString());
            TengkurapUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 143).toString());
            BerdiriUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 144).toString());
            BicaraUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 145).toString());
            DudukUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 146).toString());
            BerjalanUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 147).toString());
            TumbuhGigiUsia.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 148).toString());

            PolaAktifitasMandi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 149).toString());
            PolaAktifitasMakan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 150).toString());
            PolaAktifitasBerpakaian.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 151).toString());
            PolaAktifitasEliminasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 152).toString());
            PolaAktifitasBerpindah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 153).toString());
            PolaNutrisiPorsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 154).toString());
            PolaNutrisiFrekuensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString());
            PolaNutrisiJenis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 156).toString());
            PolaTidurLama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 157).toString());
            PolaTidurGangguan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 158).toString());
            AktifitasSehari2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 159).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("TAK")) {
                Berjalan.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Penurunan Kekuatan/ROM")) {
                Berjalan.setSelectedItem("Penurunan Kekuatan/ROM");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Paralisis")) {
                Berjalan.setSelectedItem("Paralisis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Sering Jatuh")) {
                Berjalan.setSelectedItem("Sering Jatuh");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Deformitas")) {
                Berjalan.setSelectedItem("Deformitas");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Hilang keseimbangan")) {
                Berjalan.setSelectedItem("Hilang keseimbangan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Riwayat Patah Tulang")) {
                Berjalan.setSelectedItem("Riwayat Patah Tulang");
            } else {
                Berjalan.setSelectedItem("Lain-lain");
            }
            KeteranganBerjalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 160)
                    .toString()
                    .replaceAll(Berjalan.getSelectedItem().toString() + ", ", ""));
            Aktifitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 161).toString());
            AlatAmbulasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 162).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString().contains("TAK")) {
                EkstrimitasAtas.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString().contains("Lemah")) {
                EkstrimitasAtas.setSelectedItem("Lemah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString().contains("Oedema")) {
                EkstrimitasAtas.setSelectedItem("Oedema");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString().contains("Tidak Simetris")) {
                EkstrimitasAtas.setSelectedItem("Tidak Simetris");
            } else {
                EkstrimitasAtas.setSelectedItem("Lain-lain");
            }
            KeteranganEkstrimitasAtas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 163)
                    .toString()
                    .replaceAll(EkstrimitasAtas.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 164).toString().contains("TAK")) {
                EkstrimitasBawah.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 164).toString().contains("Varises")) {
                EkstrimitasBawah.setSelectedItem("Varises");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 164).toString().contains("Oedema")) {
                EkstrimitasBawah.setSelectedItem("Oedema");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 164).toString().contains("Tidak Simetris")) {
                EkstrimitasBawah.setSelectedItem("Tidak Simetris");
            } else {
                EkstrimitasBawah.setSelectedItem("Lain-lain");
            }
            KeteranganEkstrimitasBawah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 164)
                    .toString()
                    .replaceAll(EkstrimitasBawah.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 165).toString().contains("Tidak Ada Kesulitan")) {
                KemampuanMenggenggam.setSelectedItem("Tidak Ada Kesulitan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 165).toString().contains("Terakhir")) {
                KemampuanMenggenggam.setSelectedItem("Terakhir");
            } else {
                KemampuanMenggenggam.setSelectedItem("Lain-lain");
            }
            KeteranganKemampuanMenggenggam.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 165)
                    .toString()
                    .replaceAll(KemampuanMenggenggam.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 166).toString().contains("Tidak Ada Kesulitan")) {
                KemampuanKoordinasi.setSelectedItem("Tidak Ada Kesulitan");
            } else {
                KemampuanKoordinasi.setSelectedItem("Ada Masalah");
            }
            KeteranganKemampuanKoordinasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 166)
                    .toString()
                    .replaceAll(KemampuanKoordinasi.getSelectedItem().toString() + ", ", ""));
            KesimpulanGangguanFungsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 167).toString());
            KondisiPsikologis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 168).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString().contains("Tidak Ada Masalah")) {
                AdakahPerilaku.setSelectedItem("Tidak Ada Masalah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString().contains("Perilaku Kekerasan")) {
                AdakahPerilaku.setSelectedItem("Perilaku Kekerasan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString().contains("Gangguan Efek")) {
                AdakahPerilaku.setSelectedItem("Gangguan Efek");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString().contains("Gangguan Memori")) {
                AdakahPerilaku.setSelectedItem("Gangguan Memori");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString().contains("Halusinasi")) {
                AdakahPerilaku.setSelectedItem("Halusinasi");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 169)
                    .toString()
                    .contains("Kecenderungan Percobaan Bunuh Diri")) {
                AdakahPerilaku.setSelectedItem("Kecenderungan Percobaan Bunuh Diri");
            } else {
                AdakahPerilaku.setSelectedItem("Lain-lain");
            }
            KeteranganAdakahPerilaku.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 169)
                    .toString()
                    .replaceAll(AdakahPerilaku.getSelectedItem().toString() + ", ", ""));
            GangguanJiwa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 170).toString());
            HubunganAnggotaKeluarga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 171).toString());
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 172).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString().contains("Sendiri")) {
                TinggalDengan.setSelectedItem("Sendiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString().contains("Orang Tua")) {
                TinggalDengan.setSelectedItem("Orang Tua");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString().contains("Suami/Istri")) {
                TinggalDengan.setSelectedItem("Suami/Istri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString().contains("Keluarga")) {
                TinggalDengan.setSelectedItem("Keluarga");
            } else {
                TinggalDengan.setSelectedItem("Lain-lain");
            }
            KeteranganTinggalDengan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 173)
                    .toString()
                    .replaceAll(TinggalDengan.getSelectedItem().toString() + ", ", ""));
            PekerjaanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 174).toString());
            CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 175).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 176).toString().contains("Tidak Ada")) {
                NilaiKepercayaan.setSelectedItem("Tidak Ada");
            } else {
                NilaiKepercayaan.setSelectedItem("Ada");
            }
            KeteranganNilaiKepercayaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 176)
                    .toString()
                    .replaceAll(NilaiKepercayaan.getSelectedItem().toString() + ", ", ""));
            Bahasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 177).toString());
            PendidikanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 178).toString());
            PendidikanPJ.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 179).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 180).toString().contains("Pasien")) {
                EdukasiPsikolgis.setSelectedItem("Pasien");
            } else {
                EdukasiPsikolgis.setSelectedItem("Keluarga");
            }
            KeteranganEdukasiPsikologis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 180)
                    .toString()
                    .replaceAll(EdukasiPsikolgis.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 181).toString().contains("Baligh")) {
                Wajib.setSelectedItem("Baligh");
            } else {
                Wajib.setSelectedItem("Non baligh");
            }
            KetHalanganLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 182).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 183).toString().contains("Tayamum")) {
                Toharah.setSelectedItem("Tayamum");
            } else {
                Toharah.setSelectedItem("Wudhu");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 184).toString().contains("Berdiri")) {
                Sholat.setSelectedItem("Berdiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 184).toString().contains("Duduk")) {
                Sholat.setSelectedItem("Duduk");
            } else {
                Sholat.setSelectedItem("Berbaring");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 185).toString().contains("Ya")) {
                MotivasiKesembuhanIbadah.setSelectedItem("Ya");
            } else {
                MotivasiKesembuhanIbadah.setSelectedItem("Tidak");
            }
            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 186).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 187).toString().contains("Proses Penyakit")) {
                Provokes.setSelectedItem("Proses Penyakit");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 187).toString().contains("Benturan")) {
                Provokes.setSelectedItem("Benturan");
            } else {
                Provokes.setSelectedItem("Lain-lain");
            }
            KetProvokes.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 187)
                    .toString()
                    .replaceAll(Provokes.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().contains("Seperti Tertusuk")) {
                Quality.setSelectedItem("Seperti Tertusuk");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().contains("Berdenyut")) {
                Quality.setSelectedItem("Berdenyut");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().contains("Teriris")) {
                Quality.setSelectedItem("Teriris");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().contains("Tertindih")) {
                Quality.setSelectedItem("Tertindih");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().contains("Tertiban")) {
                Quality.setSelectedItem("Tertiban");
            } else {
                Quality.setSelectedItem("Lain-lain");
            }
            KetQuality.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 188)
                    .toString()
                    .replaceAll(Quality.getSelectedItem().toString() + ", ", ""));
            Lokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 189).toString());
            Menyebar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 190).toString());
            SkalaNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 191).toString());
            Durasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 192).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 193).toString().contains("Istirahat")) {
                NyeriHilang.setSelectedItem("Istirahat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 193).toString().contains("Medengar Musik")) {
                NyeriHilang.setSelectedItem("Medengar Musik");
            } else {
                NyeriHilang.setSelectedItem("Minum Obat");
            }
            KetNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 193)
                    .toString()
                    .replaceAll(NyeriHilang.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 194).toString().contains("Ya")) {
                PadaDokter.setSelectedItem("Ya");
            } else {
                PadaDokter.setSelectedItem("Tidak");
            }
            KetPadaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 194)
                    .toString()
                    .replaceAll(PadaDokter.getSelectedItem().toString() + ", ", ""));
            SkalaHumptyDumpty1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 195).toString());
            SkalaHumptyDumpty2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 197).toString());
            SkalaHumptyDumpty3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 199).toString());
            SkalaHumptyDumpty4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 201).toString());
            SkalaHumptyDumpty5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 203).toString());
            SkalaHumptyDumpty6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 205).toString());
            SkalaHumptyDumpty7.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 207).toString());
            isTotalResikoHumptyDumpty();
            SkalaResiko1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 210).toString());
            NilaiResiko1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 211).toString());
            SkalaResiko2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 212).toString());
            NilaiResiko2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 213).toString());
            SkalaResiko3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 214).toString());
            NilaiResiko3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 215).toString());
            SkalaResiko4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 216).toString());
            NilaiResiko4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 217).toString());
            SkalaResiko5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 218).toString());
            NilaiResiko5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 219).toString());
            SkalaResiko6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 220).toString());
            NilaiResiko6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 221).toString());
            NilaiResikoTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 222).toString());
            isTotalResikoJatuh();

            SkalaGizi1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 223).toString());
            NilaiGizi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 224).toString());
            SkalaGizi2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 225).toString());
            NilaiGizi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 226).toString());
            SkalaGizi3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 227).toString());
            NilaiGizi3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 228).toString());
            SkalaGizi4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 229).toString());
            NilaiGizi4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 230).toString());
            NilaiGiziTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 231).toString());
            DiagnosaKhususGizi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 232).toString());
            KeteranganDiagnosaKhususGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 233).toString());
            DiketahuiDietisen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 234).toString());
            KeteranganDiketahuiDietisen.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 235).toString());
            Kriteria1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 236).toString());
            Kriteria2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 237).toString());
            Kriteria3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 238).toString());
            Kriteria4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 239).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 240).toString().equals("Perawatan diri (Mandi, BAB, BAK)")) {
                pilihan1.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 241).toString().equals("Pemantauan pemberian obat")) {
                pilihan2.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 242).toString().equals("Pemantauan diet")) {
                pilihan3.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 243)
                    .toString()
                    .equals("Bantuan medis / perawatan di rumah (Homecare)")) {
                pilihan4.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 244).toString().equals("Perawatan luka")) {
                pilihan5.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 245).toString().equals("Latihan fisik lanjutan")) {
                pilihan6.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 246)
                    .toString()
                    .equals("Pendampingan tenaga khusus di rumah")) {
                pilihan7.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 247)
                    .toString()
                    .equals("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)")) {
                pilihan8.setSelected(true);
            }
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 248).toString());

            try {
                Valid.tabelKosong(tabModeMasalah);

                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_kritis on penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_kritis.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeMasalah.addRow(new Object[]{true, rs.getString(1), rs.getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            try {
                Valid.tabelKosong(tabModeRencana);

                ps = koneksi.prepareStatement(
                        "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_kritis on penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_kritis.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeRencana.addRow(new Object[]{true, rs.getString(1), rs.getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }

            Valid.SetTgl2(TglAsuhan, tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,"
                    + "bahasa_pasien.nama_bahasa,pasien.pnd,pasien.pekerjaan,penjab.png_jawab "
                    + "from pasien inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "INNER JOIN reg_periksa ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Agama.setText(rs.getString("agama"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    PendidikanPasien.setText(rs.getString("pnd"));
                    PekerjaanPasien.setText(rs.getString("pekerjaan"));
                    CaraBayar.setText(rs.getString("png_jawab"));
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notif : " + e);
        }
    }

    public void setNoRm(String norwt, Date tgl2, String norm) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='" + norwt + "'",
                DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ranap());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ranap());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranap());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranap());
        BtnTambahMasalah.setEnabled(akses.getmaster_masalah_keperawatan());
        BtnTambahRencana.setEnabled(akses.getmaster_rencana_keperawatan());
        if (akses.getjml2() >= 1) {
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if (NmPetugas.getText().isEmpty()) {
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan petugas...!!");
            }
        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470, HEIGHT));
            FormMenu.setVisible(true);
            FormMasalahRencana.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            FormMenu.setVisible(false);
            FormMasalahRencana.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void getMasalah() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 248).toString());

            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_kritis on penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_kritis.no_rawat=? and penilaian_awal_keperawatan_ranap_masalah_kritis.tanggal=? order by penilaian_awal_keperawatan_ranap_masalah_kritis.kode_masalah");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailMasalah.addRow(new Object[]{rs.getString(1), rs.getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            try {
                Valid.tabelKosong(tabModeDetailRencana);
                ps = koneksi.prepareStatement(
                        "select master_rencana_keperawatan.kode_rencana,master_rencana_keperawatan.rencana_keperawatan from master_rencana_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_kritis on penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_kritis.no_rawat=? and penilaian_awal_keperawatan_ranap_rencana_kritis.tanggal=? order by penilaian_awal_keperawatan_ranap_rencana_kritis.kode_rencana");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailRencana.addRow(new Object[]{rs.getString(1), rs.getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_ranap_kritis where no_rawat=? and tanggal=?", 2,
                new String[]{tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString()}) == true) {
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_kritis", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_kritis", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            ChkAccor.setSelected(false);
            isMenu();
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if (pilihan1.isSelected() == true) {
            pilih1 = pilihan1.getText();
        }
        if (pilihan2.isSelected() == true) {
            pilih2 = pilihan2.getText();
        }
        if (pilihan3.isSelected() == true) {
            pilih3 = pilihan3.getText();
        }
        if (pilihan4.isSelected() == true) {
            pilih4 = pilihan4.getText();
        }
        if (pilihan5.isSelected() == true) {
            pilih5 = pilihan5.getText();
        }
        if (pilihan6.isSelected() == true) {
            pilih6 = pilihan6.getText();
        }
        if (pilihan7.isSelected() == true) {
            pilih7 = pilihan7.getText();
        }
        if (pilihan8.isSelected() == true) {
            pilih8 = pilihan8.getText();
        }
        if (Sequel.mengedittf("penilaian_awal_keperawatan_ranap_kritis", "no_rawat=?",
                "no_rawat=?,tanggal=?,informasi=?,ket_informasi=?,tiba_diruang_rawat=?,kasus_trauma=?,cara_masuk=?,keluhan_utama=?,rps=?,rpd=?,rpk=?,rpo=?,riwayat_pembedahan=?,riwayat_dirawat_dirs=?,alat_bantu_dipakai=?,riwayat_kehamilan=?,riwayat_kehamilan_perkiraan=?,riwayat_tranfusi=?,riwayat_alergi=?,riwayat_merokok=?,riwayat_merokok_jumlah=?,riwayat_alkohol=?,riwayat_alkohol_jumlah=?,riwayat_narkoba=?,riwayat_olahraga=?,pemeriksaan_mental=?,pemeriksaan_keadaan_umum=?,pemeriksaan_gcs=?,pemeriksaan_td=?,pemeriksaan_nadi=?,pemeriksaan_rr=?,pemeriksaan_suhu=?,pemeriksaan_spo2=?,pemeriksaan_bb=?,pemeriksaan_tb=?, breathing_jalan_nafas=?,breathing_jalan_nafas_berupa=?,"
                + "breathing_pernapasan=?, breathing_pernapasan_dengan=?, breathing_ett=?, breathing_cuff=?, breathing_frekuensi=?, breathing_irama=?, breathing_kedalaman=?, breathing_spulum=?, breathing_konsistensi=?, breathing_nafas_bunyi=?, breathing_terdapat_darah=?, breathing_jumlah=?, breathing_suara_nafas=?, breathing_ph=?, breathing_pco2=?, breathing_po2=?, breathing_sato2=?, blood_sirkulasi_perifer_nadi=?, blood_sirkulasi_perifer_irama=?, blood_sirkulasi_perifer_ekg=?, blood_sirkulasi_perifer_tekanan_darah=?, blood_sirkulasi_perifer_map=?, blood_sirkulasi_perifer_cvp=?, blood_sirkulasi_perifer_ibp=?, blood_sirkulasi_perifer_akral=?, blood_sirkulasi_perifer_distensi_vena_jugulari=?,"
                + "blood_sirkulasi_perifer_suhu=?, blood_sirkulasi_perifer_warna_kulit=?, blood_sirkulasi_perifer_pengisian_kapiler=?, blood_sirkulasi_perifer_edema=?, blood_sirkulasi_perifer_edema_pada=?, blood_sirkulasi_jantung_irama=?, blood_sirkulasi_jantung_bunyi=?, blood_sirkulasi_jantung_keluhan=?, blood_sirkulasi_jantung_karakteristik=?, blood_sirkulasi_jantung_sakit_dada=?, blood_sirkulasi_jantung_timbul=?, blood_hematologi_hb=?, blood_hematologi_ht=?, blood_hematologi_eritrosit=?, blood_hematologi_leukosit=?, blood_hematologi_trombosit=?, blood_hematologi_pendarahan=?, blood_hematologi_ct_bt=?, blood_hematologi_ptt_aptt=?, brain_tingkat_kesadaran=?, brain_pupil=?,"
                + "brain_reaksi_terhadap_cahaya=?, brain_gcs_e=?, brain_gcs_v=?, brain_gcs_m=?, brain_jumlah=?, brain_terjadi=?, brain_bagian=?, brain_icp=?, brain_cpp=?, brain_sod=?, brain_eud=?, brain_plakososial=?, bladder_pola_rutin=?, bladder_saat_ini=?, bladder_bak_ket=?, bladder_produksi_urin=?, bladder_warna=?, bladder_sakit_bak=?, bladder_distensi=?, bladder_keluhan_sakit_pinggang=?, bowel_pola_rutin=?, bowel_saat_ini=?, bowel_kosistensi=?, bowel_warna=?, bowel_lendir=?, bowel_mual=?, bowel_kembung=?, bowel_distensi=?, bowel_nyeri_tekan=?, bowel_ngt=?, bowel_intake=?, bone_tugor_kulit=?, bone_keadaan_kulit=?, bone_lokasi=?, bone_keadaan_luka=?, bone_sulit_dalam_gerak=?,"
                + "bone_fraktur=?, bone_area=?, bone_odema=?, bone_kekuatan_otot=?, usia_ibu_hamil=?, gravida_ke=?, gangguan_hamil=?, tipe_persalinan=?, bb_lahir=?, tb_lahir=?, lingkar_kepala_lahir=?, bb_saat_dikaji=?, tb_saat_dikaji=?, imunisasi_dasar=?, ket_belum=?, usia_tengkurap=?, usia_berdiri=?, usia_bicara=?, usia_duduk=?, usia_berjalan=?, usia_tumbuh_gigi=?,"
                + "pola_aktifitas_mandi=?,pola_aktifitas_makan=?,pola_aktifitas_eliminasi=?,pola_aktifitas_berpakaian=?,pola_aktifitas_berpindah=?,pola_nutrisi_frekuesi_makan=?,pola_nutrisi_jenis_makanan=?,pola_nutrisi_porsi_makan=?,pola_tidur_lama_tidur=?,pola_tidur_gangguan=?,pengkajian_fungsi_kemampuan_sehari=?,pengkajian_fungsi_aktifitas=?,pengkajian_fungsi_berjalan=?,pengkajian_fungsi_berjalan_keterangan=?,pengkajian_fungsi_ambulasi=?,pengkajian_fungsi_ekstrimitas_atas=?,pengkajian_fungsi_ekstrimitas_atas_keterangan=?,pengkajian_fungsi_ekstrimitas_bawah=?,pengkajian_fungsi_ekstrimitas_bawah_keterangan=?,pengkajian_fungsi_menggenggam=?,pengkajian_fungsi_menggenggam_keterangan=?,"
                + "pengkajian_fungsi_koordinasi=?,pengkajian_fungsi_koordinasi_keterangan=?,pengkajian_fungsi_kesimpulan=?,riwayat_psiko_kondisi_psiko=?,riwayat_psiko_gangguan_jiwa=?,riwayat_psiko_perilaku=?,riwayat_psiko_perilaku_keterangan=?,riwayat_psiko_hubungan_keluarga=?,riwayat_psiko_tinggal=?,riwayat_psiko_tinggal_keterangan=?,riwayat_psiko_nilai_kepercayaan=?,riwayat_psiko_nilai_kepercayaan_keterangan=?,riwayat_psiko_pendidikan_pj=?,riwayat_psiko_edukasi_diberikan=?,riwayat_psiko_edukasi_diberikan_keterangan=?,riwayat_psiko_wajib=?,riwayat_psiko_ket_halangan_lain=?,riwayat_psiko_toharah=?,riwayat_psiko_sholat=?,riwayat_psiko_motivasi_kesembuhan_ibadah=?,penilaian_nyeri=?,penilaian_nyeri_penyebab=?,penilaian_nyeri_ket_penyebab=?,penilaian_nyeri_kualitas=?,penilaian_nyeri_ket_kualitas=?,penilaian_nyeri_lokasi=?,"
                + "penilaian_nyeri_menyebar=?,penilaian_nyeri_skala=?,penilaian_nyeri_waktu=?,penilaian_nyeri_hilang=?,penilaian_nyeri_ket_hilang=?,penilaian_nyeri_diberitahukan_dokter=?,penilaian_nyeri_jam_diberitahukan_dokter=?, resiko_jatuh_usia=?, nilai_resiko_jatuh_usia=?, resiko_jatuh_jk=?, nilai_resiko_jatuh_jk=?, resiko_jatuh_diagnosis=?, nilai_resiko_jatuh_diagnosis=?, resiko_jatuh_gangguan_kognitif=?, nilai_resiko_jatuh_gangguan_kognitif=?, resiko_jatuh_faktor_lingkungan=?, nilai_resiko_jatuh_faktor_lingkungan=?, resiko_jatuh_respon_pembedahan=?, nilai_resiko_jatuh_respon_pembedahan=?, resiko_jatuh_medikamentosa=?, nilai_resiko_jatuh_medikamentosa=?, total_hasil_resiko_jatuh=?,"
                + "penilaian_jatuhmorse_skala1=?,penilaian_jatuhmorse_nilai1=?,penilaian_jatuhmorse_skala2=?,penilaian_jatuhmorse_nilai2=?,penilaian_jatuhmorse_skala3=?,penilaian_jatuhmorse_nilai3=?,penilaian_jatuhmorse_skala4=?,penilaian_jatuhmorse_nilai4=?,penilaian_jatuhmorse_skala5=?,penilaian_jatuhmorse_nilai5=?,penilaian_jatuhmorse_skala6=?,penilaian_jatuhmorse_nilai6=?,penilaian_jatuhmorse_totalnilai=?,skrining_gizi1=?,nilai_gizi1=?,skrining_gizi2=?,nilai_gizi2=?, skrining_gizi3=?, nilai_gizi3=?, skrining_gizi4=?, nilai_gizi4=?,nilai_total_gizi=?,skrining_gizi_diagnosa_khusus=?,skrining_gizi_ket_diagnosa_khusus=?,skrining_gizi_diketahui_dietisen=?,skrining_gizi_jam_diketahui_dietisen=?,"
                + "kriteria1=?,kriteria2=?,kriteria3=?,kriteria4=?,pilihan1=?,pilihan2=?,pilihan3=?,pilihan4=?,pilihan5=?,pilihan6=?,pilihan7=?,pilihan8=?,rencana=?,nip1=?,nip2=?,kd_dokter=?",
                253,
                new String[]{TNoRw.getText(),
                    Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                    + TglAsuhan.getSelectedItem().toString().substring(11, 19),
                    Anamnesis.getSelectedItem().toString(), KetAnamnesis.getText(),
                    TibadiRuang.getSelectedItem().toString(), MacamKasus.getSelectedItem().toString(),
                    CaraMasuk.getSelectedItem().toString(), KeluhanUtama.getText(), RPS.getText(), RPD.getText(),
                    RPK.getText(), RPO.getText(), RPembedahan.getText(), RDirawatRS.getText(),
                    AlatBantuDipakai.getSelectedItem().toString(), SedangMenyusui.getSelectedItem().toString(),
                    KetSedangMenyusui.getText(), RTranfusi.getText(), Alergi.getText(),
                    KebiasaanMerokok.getSelectedItem().toString(), KebiasaanJumlahRokok.getText(),
                    KebiasaanAlkohol.getSelectedItem().toString(), KebiasaanJumlahAlkohol.getText(),
                    KebiasaanNarkoba.getSelectedItem().toString(), OlahRaga.getSelectedItem().toString(),
                    KesadaranMental.getText(), KeadaanMentalUmum.getSelectedItem().toString(), GCS.getText(),
                    TD.getText(), Nadi.getText(), RR.getText(), Suhu.getText(), SpO2.getText(), BB.getText(),
                    TB.getText(), BreathingJalanNafas.getSelectedItem().toString(),
                    BreathingJalanNafasBerupa.getSelectedItem().toString(),
                    BreathingPernapasan.getSelectedItem().toString(),
                    BreathingPernapasanDengan.getSelectedItem().toString(), BreathingETT.getText(),
                    BreathingCuff.getText(), BreathingFrekuensi.getText(),
                    BreathingIrama.getSelectedItem().toString(), BreathingKedalaman.getSelectedItem().toString(),
                    BreathingSpulum.getSelectedItem().toString(), BreathingKonsistensi.getSelectedItem().toString(),
                    BreathingNafasBunyi.getSelectedItem().toString(),
                    BreathingTerdapatDarah.getSelectedItem().toString(), BreathingTerdapatDarahJumlah.getText(),
                    BreathingSuaraNafas.getSelectedItem().toString(), BreathingAnalisaGasDarahPH.getText(),
                    BreathingAnalisaGasDarahpCO2.getText(), BreathingAnalisaGasDarahpO2.getText(),
                    BreathingAnalisaGasDarahSatO2.getText(), BloodSirkulasiPeriferNadi.getText(),
                    BloodSirkulasiPeriferIrama.getSelectedItem().toString(), BloodSirkulasiPeriferEKG.getText(),
                    BloodSirkulasiPeriferTekananDarah.getText(), BloodSirkulasiPeriferMAP.getText(),
                    BloodSirkulasiPeriferCVP.getText(), BloodSirkulasiPeriferIBP.getText(),
                    BloodSirkulasiPeriferAkral.getSelectedItem().toString(),
                    BloodSirkulasiPeriferDistensiVenaJugulari.getSelectedItem().toString(),
                    BloodSirkulasiPeriferSuhu.getText(),
                    BloodSirkulasiPeriferWarnaKulit.getSelectedItem().toString(),
                    BloodSirkulasiPeriferPengisianKaplier.getSelectedItem().toString(),
                    BloodSirkulasiPeriferEdema.getSelectedItem().toString(),
                    BloodSirkulasiPeriferEdemaPada.getSelectedItem().toString(),
                    BloodSirkulasiJantungJantungIrama.getSelectedItem().toString(),
                    BloodSirkulasiJantungBunyi.getSelectedItem().toString(),
                    BloodSirkulasiJantungKeluhan.getSelectedItem().toString(),
                    BloodSirkulasiJantungKarakteristik.getSelectedItem().toString(),
                    BloodSirkulasiJantungSakitDada.getSelectedItem().toString(),
                    BloodSirkulasiJantungSakitDadaTimbul.getSelectedItem().toString(), BloodHematologiHB.getText(),
                    BloodHematologiHt.getText(), BloodHematologiEritrosit.getText(),
                    BloodHematologiLeukosit.getText(), BloodHematologiTrombosit.getText(),
                    BloodHematologiPendarahan.getSelectedItem().toString(), BloodHematologiCT.getText(),
                    BloodHematologiPTT.getText(),
                    BrainSirkulasiSerebralTingkatKesadaran.getSelectedItem().toString(),
                    BrainSirkulasiSerebralPupil.getSelectedItem().toString(),
                    BrainSirkulasiSerebralReaksiTerhadapCahaya.getSelectedItem().toString(),
                    BrainSirkulasiSerebralGCSE.getText(), BrainSirkulasiSerebralGCSV.getText(),
                    BrainSirkulasiSerebralGCSM.getText(), BrainSirkulasiSerebralGCSJumlah.getText(),
                    BrainSirkulasiSerebralTerjadi.getSelectedItem().toString(),
                    BrainSirkulasiSerebralTerjadiBagian.getSelectedItem().toString(),
                    BrainSirkulasiSerebralICP.getText(), BrainSirkulasiSerebralCPP.getText(),
                    BrainSirkulasiSerebralSOD.getText(), BrainSirkulasiSerebralEUD.getText(),
                    BrainSirkulasiSerebralPalkososial.getSelectedItem().toString(), BladderBAKPolaRutin.getText(),
                    BladderBAKSaatIni.getText(), BladderBAKTerkontrol.getSelectedItem().toString(),
                    BladderProduksiUrin.getText(), BladderWarna.getSelectedItem().toString(),
                    BladderSakitWaktuBAK.getSelectedItem().toString(), BladderDistensi.getSelectedItem().toString(),
                    BladderSakitPinggang.getSelectedItem().toString(), BowelBAKPolaRutin.getText(),
                    BowelBAKSaatIni.getText(), BowelKonsistensi.getSelectedItem().toString(),
                    BowelWarna.getSelectedItem().toString(), BowelLendir.getSelectedItem().toString(),
                    BowelMual.getSelectedItem().toString(), BowelKembung.getSelectedItem().toString(),
                    BowelDistensi.getSelectedItem().toString(), BowelNyeriTekan.getSelectedItem().toString(),
                    BowelNGT.getSelectedItem().toString(), BowelIntake.getText(),
                    BoneTugorKulit.getSelectedItem().toString(), BoneKeadaanKulit.getSelectedItem().toString(),
                    BoneLokasi.getText(), BoneKeadaanLuka.getSelectedItem().toString(),
                    BoneSulitDalamGerak.getSelectedItem().toString(), BoneFraktur.getSelectedItem().toString(),
                    BoneArea.getText(), BoneOdema.getSelectedItem().toString(), BoneKekuatanOtot.getText(),
                    UsiaIbuSaatHamil.getText(), GravidaKe.getText(), GangguanHamil.getText(),
                    TipePersalinan.getSelectedItem().toString(), BBLahir.getText(), TBLahir.getText(),
                    LingkarKepalaLahir.getText(), BBDiKaji.getText(), TBDiKaji.getText(),
                    ImunisasiDasar.getSelectedItem().toString(), ImunisasiBelum.getText(), TengkurapUsia.getText(),
                    BerdiriUsia.getText(), BicaraUsia.getText(), DudukUsia.getText(), BerjalanUsia.getText(),
                    TumbuhGigiUsia.getText(), PolaAktifitasMandi.getSelectedItem().toString(),
                    PolaAktifitasMakan.getSelectedItem().toString(),
                    PolaAktifitasEliminasi.getSelectedItem().toString(),
                    PolaAktifitasBerpakaian.getSelectedItem().toString(),
                    PolaAktifitasBerpindah.getSelectedItem().toString(), PolaNutrisiFrekuensi.getText(),
                    PolaNutrisiJenis.getText(), PolaNutrisiPorsi.getText(), PolaTidurLama.getText(),
                    PolaTidurGangguan.getSelectedItem().toString(), AktifitasSehari2.getSelectedItem().toString(),
                    Aktifitas.getSelectedItem().toString(), Berjalan.getSelectedItem().toString(),
                    KeteranganBerjalan.getText(), AlatAmbulasi.getSelectedItem().toString(),
                    EkstrimitasAtas.getSelectedItem().toString(), KeteranganEkstrimitasAtas.getText(),
                    EkstrimitasBawah.getSelectedItem().toString(), KeteranganEkstrimitasBawah.getText(),
                    KemampuanMenggenggam.getSelectedItem().toString(), KeteranganKemampuanMenggenggam.getText(),
                    KemampuanKoordinasi.getSelectedItem().toString(), KeteranganKemampuanKoordinasi.getText(),
                    KesimpulanGangguanFungsi.getSelectedItem().toString(),
                    KondisiPsikologis.getSelectedItem().toString(), GangguanJiwa.getSelectedItem().toString(),
                    AdakahPerilaku.getSelectedItem().toString(), KeteranganAdakahPerilaku.getText(),
                    HubunganAnggotaKeluarga.getSelectedItem().toString(),
                    TinggalDengan.getSelectedItem().toString(), KeteranganTinggalDengan.getText(),
                    NilaiKepercayaan.getSelectedItem().toString(), KeteranganNilaiKepercayaan.getText(),
                    PendidikanPJ.getSelectedItem().toString(), EdukasiPsikolgis.getSelectedItem().toString(),
                    KeteranganEdukasiPsikologis.getText(), Wajib.getSelectedItem().toString(),
                    KetHalanganLain.getText(), Toharah.getSelectedItem().toString(),
                    Sholat.getSelectedItem().toString(), MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                    Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
                    KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(),
                    Lokasi.getText(), Menyebar.getSelectedItem().toString(),
                    SkalaNyeri.getSelectedItem().toString(), Durasi.getText(),
                    NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(),
                    PadaDokter.getSelectedItem().toString(), KetPadaDokter.getText(),
                    SkalaHumptyDumpty1.getSelectedItem().toString(), NilaiHumptyDumpty1.getText(),
                    SkalaHumptyDumpty2.getSelectedItem().toString(), NilaiHumptyDumpty2.getText(),
                    SkalaHumptyDumpty3.getSelectedItem().toString(), NilaiHumptyDumpty3.getText(),
                    SkalaHumptyDumpty4.getSelectedItem().toString(), NilaiHumptyDumpty4.getText(),
                    SkalaHumptyDumpty5.getSelectedItem().toString(), NilaiHumptyDumpty5.getText(),
                    SkalaHumptyDumpty6.getSelectedItem().toString(), NilaiHumptyDumpty6.getText(),
                    SkalaHumptyDumpty7.getSelectedItem().toString(), NilaiHumptyDumpty7.getText(),
                    NilaiHumptyDumptyTotal.getText(), SkalaResiko1.getSelectedItem().toString(),
                    NilaiResiko1.getText(), SkalaResiko2.getSelectedItem().toString(), NilaiResiko2.getText(),
                    SkalaResiko3.getSelectedItem().toString(), NilaiResiko3.getText(),
                    SkalaResiko4.getSelectedItem().toString(), NilaiResiko4.getText(),
                    SkalaResiko5.getSelectedItem().toString(), NilaiResiko5.getText(),
                    SkalaResiko6.getSelectedItem().toString(), NilaiResiko6.getText(), NilaiResikoTotal.getText(),
                    SkalaGizi1.getSelectedItem().toString(), NilaiGizi1.getText(),
                    SkalaGizi2.getSelectedItem().toString(), NilaiGizi2.getText(),
                    SkalaGizi3.getSelectedItem().toString(), NilaiGizi3.getText(),
                    SkalaGizi4.getSelectedItem().toString(), NilaiGizi4.getText(), NilaiGiziTotal.getText(),
                    DiagnosaKhususGizi.getSelectedItem().toString(), KeteranganDiagnosaKhususGizi.getText(),
                    DiketahuiDietisen.getSelectedItem().toString(), KeteranganDiketahuiDietisen.getText(),
                    Kriteria1.getSelectedItem().toString(), Kriteria2.getSelectedItem().toString(),
                    Kriteria3.getSelectedItem().toString(), Kriteria4.getSelectedItem().toString(), pilih1, pilih2,
                    pilih3, pilih4, pilih5, pilih6, pilih7, pilih8, Rencana.getText(), KdPetugas.getText(),
                    KdPetugas2.getText(), KdDPJP.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()}) == true) {
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_kritis", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_ranap_masalah_kritis", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbMasalahKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_kritis", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_ranap_rencana_kritis", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbRencanaKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            getMasalah();
            tampil();
            DetailRencana.setText(Rencana.getText());
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

    private void isTotalResikoJatuh() {
        try {
            NilaiResikoTotal
                    .setText((Integer.parseInt(NilaiResiko1.getText()) + Integer.parseInt(NilaiResiko2.getText())
                            + Integer.parseInt(NilaiResiko3.getText()) + Integer.parseInt(NilaiResiko4.getText())
                            + Integer.parseInt(NilaiResiko5.getText()) + Integer.parseInt(NilaiResiko6.getText())) + "");
            if (Integer.parseInt(NilaiResikoTotal.getText()) < 25) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiResikoTotal.getText()) < 45) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Sedang (25-44), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiResikoTotal.getText()) >= 45) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Tinggi (> 45), Tindakan : Intervensi pencegahan risiko jatuh standar dan Intervensi risiko jatuh tinggi");
            }
        } catch (Exception e) {
            // NilaiResikoTotal.setText("0");
            TingkatResiko.setText(
                    "Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        }
    }

    private void isTotalGizi() {
        try {
            NilaiGiziTotal.setText((Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(NilaiGizi2.getText())
                    + Integer.parseInt(NilaiGizi3.getText()) + Integer.parseInt(NilaiGizi4.getText())) + "");
        } catch (Exception e) {
            NilaiGiziTotal.setText("0");
        }
    }

    private void tampilMasalah() {
        try {
            Valid.tabelKosong(tabModeMasalah);
            file = new File("./cache/masalahkeperawatan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_masalah_keperawatan order by master_masalah_keperawatan.kode_masalah");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeMasalah.addRow(new Object[]{false, rs.getString(1), rs.getString(2)});
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"NamaMasalah\":\"" + rs.getString(2)
                            + "\"},";
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"masalahkeperawatan\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;
        } catch (IOException | SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilMasalah2() {
        try {
            jml = 0;
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            masalah = null;
            masalah = new String[jml];

            index = 0;
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbMasalahKeperawatan.getValueAt(i, 1).toString();
                    masalah[index] = tbMasalahKeperawatan.getValueAt(i, 2).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeMasalah);

            for (i = 0; i < jml; i++) {
                tabModeMasalah.addRow(new Object[]{pilih[i], kode[i], masalah[i]});
            }

            myObj = new FileReader("./cache/masalahkeperawatan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahkeperawatan");
            if (response.isArray()) {
                for (JsonNode list : response) {
                    if (list.path("KodeMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())
                            || list.path("NamaMasalah")
                                    .asText()
                                    .toLowerCase()
                                    .contains(TCariMasalah.getText().toLowerCase())) {
                        tabModeMasalah.addRow(new Object[]{false, list.path("KodeMasalah").asText(),
                            list.path("NamaMasalah").asText()});
                    }
                }
            }
            myObj.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilRencana() {
        try {
            file = new File("./cache/rencanakeperawatan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_rencana_keperawatan order by master_rencana_keperawatan.kode_rencana");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"KodeRencana\":\"" + rs.getString(2)
                            + "\",\"NamaRencana\":\"" + rs.getString(3) + "\"},";
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"rencanakeperawatan\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;
        } catch (IOException | SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilRencana2() {
        try {
            jml = 0;
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            masalah = null;
            masalah = new String[jml];

            index = 0;
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbRencanaKeperawatan.getValueAt(i, 1).toString();
                    masalah[index] = tbRencanaKeperawatan.getValueAt(i, 2).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeRencana);

            for (i = 0; i < jml; i++) {
                tabModeRencana.addRow(new Object[]{pilih[i], kode[i], masalah[i]});
            }

            myObj = new FileReader("./cache/rencanakeperawatan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanakeperawatan");
            if (response.isArray()) {
                for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                    if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                        for (JsonNode list : response) {
                            if (list.path("KodeMasalah")
                                    .asText()
                                    .toLowerCase()
                                    .equals(tbMasalahKeperawatan.getValueAt(i, 1).toString())
                                    && list.path("NamaRencana")
                                            .asText()
                                            .toLowerCase()
                                            .contains(TCariRencana.getText().toLowerCase())) {
                                tabModeRencana.addRow(new Object[]{false, list.path("KodeRencana").asText(),
                                    list.path("NamaRencana").asText()});
                            }
                        }
                    }
                }
            }
            myObj.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isTotalResikoHumptyDumpty() {
        try {
            NilaiHumptyDumptyTotal.setText((Integer.parseInt(NilaiHumptyDumpty1.getText())
                    + Integer.parseInt(NilaiHumptyDumpty2.getText()) + Integer.parseInt(NilaiHumptyDumpty3.getText())
                    + Integer.parseInt(NilaiHumptyDumpty4.getText()) + Integer.parseInt(NilaiHumptyDumpty5.getText())
                    + Integer.parseInt(NilaiHumptyDumpty6.getText()) + Integer.parseInt(NilaiHumptyDumpty7.getText()))
                    + "");
            if (Integer.parseInt(NilaiHumptyDumptyTotal.getText()) < 12) {
                TingkatHumptyDumpty.setText(
                        "Tingkat Resiko : Risiko Rendah (7 - 11), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiHumptyDumptyTotal.getText()) > 12) {
                TingkatHumptyDumpty.setText(
                        "Tingkat Resiko : Risiko Tinggi >12, Tindakan : Intervensi pencegahan risiko jatuh standar");
            }
        } catch (Exception e) {
            NilaiHumptyDumptyTotal.setText("0");
            TingkatHumptyDumpty
                    .setText("Tingkat Resiko : Risiko Rendah (7 - 11), Tindakan : Intervensi pencegahan risiko standar");
        }
    }

    private static final Logger LOG = Logger.getLogger(RMPenilaianAwalKeperawatanRanapKritis.class.getName());

}
