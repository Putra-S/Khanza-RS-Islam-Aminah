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
public class RMPenilaianAwalKeperawatanRanapAnak extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeMasalah, tabModeDetailMasalah, tabModeImunisasi, tabModeImunisasi2,
            tabModeRencana, tabModeDetailRencana;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps, ps2, ps3;

    private ResultSet rs, rs2, rs3;

    private int i = 0, jml = 0, index = 0;

    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    private DlgCariDokter dokter = new DlgCariDokter(null, false);

    private StringBuilder htmlContent;

    private String pilihan = "", pilih1 = "", pilih2 = "", pilih3 = "", pilih4 = "", pilih5 = "", pilih6 = "",
            pilih7 = "", pilih8 = "";

    private boolean[] pilih;

    private String[] kode, masalah;

    private String masalahkeperawatan = "", htmlke1 = "", htmlke2 = "", htmlke3 = "", htmlke4 = "", htmlke5 = "",
            htmlke6 = "", finger = "";

    private MasterCariImunisasi imunisasi = new MasterCariImunisasi(null, false);

    private boolean ke1 = false, ke2 = false, ke3 = false, ke4 = false, ke5 = false, ke6 = false;

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
    public RMPenilaianAwalKeperawatanRanapAnak(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        DlgRiwayatImunisasi.setSize(465, 112);

        tabMode = new DefaultTableModel(null, new Object[]{"No.Rawat", "No.RM", "Nama Pasien", "Tgl.Lahir", "J.K.",
            "NIP Pengkaji 1", "Nama Pengkaji 1", "NIP Pengkaji 2", "Nama Pengkaji 2", "Kode DPJP", "Nama DPJP",
            "Tgl.Asuhan", "Macam Kasus", "Anamnesis", "Tiba Di Ruang Rawat", "Cara Masuk", "Keluhan Utama",
            "Riwayat Penyakit Saat Ini", "Riwayat Penyakit Dahulu", "Riwayat Penyakit Keluarga",
            "Riwayat Penggunaan Obat", "Riwayat Pembedahan", "Riwayat Dirawat Di RS", "Alat Bantu Yang Dipakai",
            "Dalam Keadaan Hamil/Sedang Menyusui", "Riwayat Transfusi Darah", "Riwayat Alergi", "Merokok",
            "Batang/Hari", "Alkohol", "Gelas/Hari", "Obat Tidur", "Olah Raga", "Kesadaran Mental", "Keadaan Umum",
            "GCS(E,V,M)", "TD(mmHg)", "Nadi(x/menit)", "RR(x/menit)", "Suhu(°C)", "SpO2(%)", "BB(Kg)", "TB(cm)",
            "Kepala", "Wajah", "Leher", "Kejang", "Sensorik", "Pulsasi", "Sirkulasi", "Denyut Nadi", "Retraksi",
            "Pola Nafas", "Suara Nafas", "Batuk & Sekresi", "Volume", "Jenis Pernafasaan", "Irama", "Mulut",
            "Lidah", "Gigi", "Tenggorokan", "Abdomen", "Peistatik Usus", "Anus", "Sensorik", "Penglihatan",
            "Alat Bantu Penglihatan", "Motorik", "Pendengaran", "Bicara", "Otot", "Kulit", "Warna Kulit", "Turgor",
            "Resiko Decubitas", "Oedema", "Pergerakan Sendi", "Kekuatan Otot", "Fraktur", "Nyeri Sendi",
            "Frekuensi BAB", "x/", "Konsistensi BAB", "Warna BAB", "Frekuensi BAK", "x/", "Warna BAK",
            "Lain-lain BAK", "Anak Ke", "Dari", "Cara Kelahiran", "Ket.Cara Kelahiran", "Umur Kelahiran",
            "Kelainan Bawaan", "Ket.Kelainan Bawaan", "Tengkurap", "Duduk", "Berdiri", "Gigi Pertama", "Berjalan",
            "Bicara", "Membaca", "Menulis", "Gangguan Emosi", "Mandi", "Makan/Minum", "Berpakaian", "Eliminasi",
            "Berpindah", "Porsi Makan", "Frekuensi Makan", "Jenis Makanan", "Lama Tidur", "Gangguan Tidur",
            "a. Aktifitas Sehari-hari", "b. Berjalan", "c. Aktifitas", "d. Alat Ambulasi", "e. Ekstremitas Atas",
            "f. Ekstremitas Bawah", "g. Kemampuan Menggenggam", "h. Kemampuan Koordinasi",
            "i. Kesimpulan Gangguan Fungsi", "a. Kondisi Psikologis", "b. Adakah Perilaku",
            "c. Gangguan Jiwa di Masa Lalu", "d. Hubungan Pasien", "e. Agama", "f. Tinggal Dengan", "g. Pekerjaan",
            "h. Pembayaran", "i. Nilai-nilai Kepercayaan", "j. Bahasa Sehari-hari", "k. Pendidikan Pasien",
            "l. Pendidikan P.J.", "m. Edukasi Diberikan Kepada", "n. Wajib", "o. Halangan Lain", "p. Toharah",
            "q. Sholat", "r. Motivasi Kesembuhan & Ibadah", "Skala Wajah", "N.S. Wajah", "Skala Kaki", "N.S. Kaki",
            "Skala Aktifitas", "N.S. Aktifitas", "Skala Menangis", "N.S. Menangis", "Skala Bersuara",
            "N.S. Bersuara", "Skala Nyeri", "Nyeri", "Penyebab Nyeri", "Kualitas Nyeri", "Lokasi Nyeri",
            "Nyeri Menyebar", "Waktu / Durasi", "Nyeri Hilang Bila", "Diberitahukan Pada Dokter", "Humpty Dumpty 1",
            "N.S. 1", "Humpty Dumpty 2", "N.S. 2", "Humpty Dumpty 3", "N.S. 3", "Humpty Dumpty 4", "N.S. 4",
            "Humpty Dumpty 5", "N.S. 5", "Humpty Dumpty 6", "N.S. 6", "Humpty Dumpty 7", "N.S. 7", "T.S.", "S.G. 1",
            "N.G. 1", "S.G. 2", "N.G. 2", "S.G. 3", "N.G. 3", "S.G. 4", "N.G. 4", "T.S. Gizi", "Umur > 65 Tahun",
            "Keterbatasan mobilitas", "Perawatan atau pengobatan lanjutan",
            "Bantuan untuk melakukan aktifitas sehari-hari", "Perencanaan 1", "Perencanaan 2", "Perencanaan 3",
            "Perencanaan 4", "Perencanaan 5", "Perencanaan 6", "Perencanaan 7", "Perencanaan 8",
            "Rencana Keperawatan Lainnya"}) {
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

        for (i = 0; i < 198; i++) {
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
            } else if (i == 193) {
                column.setPreferredWidth(200);
            } else if (i == 194) {
                column.setPreferredWidth(200);
            } else if (i == 195) {
                column.setPreferredWidth(200);
            } else if (i == 196) {
                column.setPreferredWidth(200);
            } else if (i == 197) {
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeImunisasi = new DefaultTableModel(null,
                new Object[]{"Kode", "Nama Imunisasi", "Ke 1", "Ke 2", "Ke 3", "Ke 4", "Ke 5", "Ke 6"}) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class};

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbImunisasi.setModel(tabModeImunisasi);
        tbImunisasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbImunisasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbImunisasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else {
                column.setPreferredWidth(50);
            }
        }
        tbImunisasi.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeImunisasi2 = new DefaultTableModel(null,
                new Object[]{"Kode", "Nama Imunisasi", "Ke 1", "Ke 2", "Ke 3", "Ke 4", "Ke 5", "Ke 6"}) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class};

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbImunisasi2.setModel(tabModeImunisasi2);
        tbImunisasi2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbImunisasi2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbImunisasi2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(205);
            } else {
                column.setPreferredWidth(35);
            }
        }
        tbImunisasi2.setDefaultRenderer(Object.class, new WarnaTable());

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
        KetSistemSarafKepala.setDocument(new batasInput((byte) 50).getKata(KetSistemSarafKepala));
        KetSistemSarafWajah.setDocument(new batasInput((byte) 50).getKata(KetSistemSarafWajah));
        KetSistemSarafKejang.setDocument(new batasInput((byte) 50).getKata(KetSistemSarafKejang));
        KetKardiovaskularSirkulasi.setDocument(new batasInput((byte) 50).getKata(KetKardiovaskularSirkulasi));
        KetRespirasiJenisPernafasan.setDocument(new batasInput((byte) 50).getKata(KetRespirasiJenisPernafasan));
        KetGastrointestinalMulut.setDocument(new batasInput((byte) 50).getKata(KetGastrointestinalMulut));
        KetGastrointestinalLidah.setDocument(new batasInput((byte) 50).getKata(KetGastrointestinalLidah));
        KetGastrointestinalGigi.setDocument(new batasInput((byte) 50).getKata(KetGastrointestinalGigi));
        KetGastrointestinalTenggorakan.setDocument(new batasInput((byte) 50).getKata(KetGastrointestinalTenggorakan));
        KetGastrointestinalAbdomen.setDocument(new batasInput((byte) 50).getKata(KetGastrointestinalAbdomen));
        KetNeurologiPenglihatan.setDocument(new batasInput((byte) 50).getKata(KetNeurologiPenglihatan));
        KetNeurologiBicara.setDocument(new batasInput((byte) 50).getKata(KetNeurologiBicara));
        KetMuskuloskletalOedema.setDocument(new batasInput((byte) 50).getKata(KetMuskuloskletalOedema));
        KetMuskuloskletalFraktur.setDocument(new batasInput((byte) 50).getKata(KetMuskuloskletalFraktur));
        KetMuskuloskletalNyeriSendi.setDocument(new batasInput((byte) 50).getKata(KetMuskuloskletalNyeriSendi));
        BAB.setDocument(new batasInput((byte) 5).getKata(BAB));
        XBAB.setDocument(new batasInput((byte) 10).getKata(XBAB));
        KBAB.setDocument(new batasInput((byte) 30).getKata(KBAB));
        WBAB.setDocument(new batasInput((byte) 30).getKata(WBAB));
        BAK.setDocument(new batasInput((byte) 5).getKata(BAK));
        XBAK.setDocument(new batasInput((byte) 10).getKata(XBAK));
        WBAK.setDocument(new batasInput((byte) 30).getKata(WBAK));
        LBAK.setDocument(new batasInput((byte) 30).getKata(LBAK));
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

        LoadHTML = new widget.editorpane();
        DlgRiwayatImunisasi = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        jLabel99 = new widget.Label();
        BtnKeluarImunisasi = new widget.Button();
        BtnSimpanImunisasi = new widget.Button();
        BtnImunisasi = new widget.Button();
        NmImunisasi = new widget.TextBox();
        KdImunisasi = new widget.TextBox();
        jLabel81 = new widget.Label();
        ImunisasiKe = new widget.ComboBox();
        BtnHapusImunisasi = new widget.Button();
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
        SistemSarafKepala = new widget.ComboBox();
        KetSistemSarafKepala = new widget.TextBox();
        jLabel132 = new widget.Label();
        SistemSarafWajah = new widget.ComboBox();
        KetSistemSarafWajah = new widget.TextBox();
        jLabel133 = new widget.Label();
        SistemSarafLeher = new widget.ComboBox();
        jLabel134 = new widget.Label();
        SistemSarafSensorik = new widget.ComboBox();
        jLabel135 = new widget.Label();
        SistemSarafKejang = new widget.ComboBox();
        KetSistemSarafKejang = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel136 = new widget.Label();
        KardiovaskularPulsasi = new widget.ComboBox();
        jLabel137 = new widget.Label();
        KardiovaskularSirkulasi = new widget.ComboBox();
        KetKardiovaskularSirkulasi = new widget.TextBox();
        jLabel138 = new widget.Label();
        KardiovaskularDenyutNadi = new widget.ComboBox();
        jLabel35 = new widget.Label();
        jLabel139 = new widget.Label();
        RespirasiRetraksi = new widget.ComboBox();
        RespirasiPolaNafas = new widget.ComboBox();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        RespirasiSuaraNafas = new widget.ComboBox();
        jLabel142 = new widget.Label();
        RespirasiIrama = new widget.ComboBox();
        jLabel143 = new widget.Label();
        RespirasiVolume = new widget.ComboBox();
        jLabel144 = new widget.Label();
        RespirasiJenisPernafasan = new widget.ComboBox();
        KetRespirasiJenisPernafasan = new widget.TextBox();
        jLabel145 = new widget.Label();
        RespirasiBatuk = new widget.ComboBox();
        jLabel49 = new widget.Label();
        jLabel146 = new widget.Label();
        GastrointestinalMulut = new widget.ComboBox();
        KetGastrointestinalMulut = new widget.TextBox();
        GastrointestinalGigi = new widget.ComboBox();
        jLabel148 = new widget.Label();
        KetGastrointestinalGigi = new widget.TextBox();
        jLabel149 = new widget.Label();
        GastrointestinalAnus = new widget.ComboBox();
        jLabel147 = new widget.Label();
        GastrointestinalLidah = new widget.ComboBox();
        KetGastrointestinalLidah = new widget.TextBox();
        jLabel150 = new widget.Label();
        GastrointestinalTenggorakan = new widget.ComboBox();
        KetGastrointestinalTenggorakan = new widget.TextBox();
        jLabel151 = new widget.Label();
        GastrointestinalAbdomen = new widget.ComboBox();
        KetGastrointestinalAbdomen = new widget.TextBox();
        jLabel152 = new widget.Label();
        GastrointestinalUsus = new widget.ComboBox();
        jLabel50 = new widget.Label();
        jLabel153 = new widget.Label();
        NeurologiSensorik = new widget.ComboBox();
        jLabel154 = new widget.Label();
        NeurologiMotorik = new widget.ComboBox();
        jLabel155 = new widget.Label();
        NeurologiOtot = new widget.ComboBox();
        jLabel156 = new widget.Label();
        NeurologiPenglihatan = new widget.ComboBox();
        KetNeurologiPenglihatan = new widget.TextBox();
        jLabel157 = new widget.Label();
        NeurologiAlatBantuPenglihatan = new widget.ComboBox();
        jLabel158 = new widget.Label();
        NeurologiPendengaran = new widget.ComboBox();
        jLabel159 = new widget.Label();
        NeurologiBicara = new widget.ComboBox();
        KetNeurologiBicara = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel160 = new widget.Label();
        IntegumentKulit = new widget.ComboBox();
        jLabel161 = new widget.Label();
        IntegumentWarnaKulit = new widget.ComboBox();
        jLabel162 = new widget.Label();
        IntegumentTurgor = new widget.ComboBox();
        IntegumentDecubitus = new widget.ComboBox();
        jLabel163 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel164 = new widget.Label();
        MuskuloskletalOedema = new widget.ComboBox();
        KetMuskuloskletalOedema = new widget.TextBox();
        KetMuskuloskletalFraktur = new widget.TextBox();
        MuskuloskletalFraktur = new widget.ComboBox();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        MuskuloskletalNyeriSendi = new widget.ComboBox();
        KetMuskuloskletalNyeriSendi = new widget.TextBox();
        jLabel167 = new widget.Label();
        MuskuloskletalPegerakanSendi = new widget.ComboBox();
        MuskuloskletalKekuatanOtot = new widget.ComboBox();
        jLabel168 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel113 = new widget.Label();
        KBAB = new widget.TextBox();
        jLabel114 = new widget.Label();
        BAB = new widget.TextBox();
        jLabel115 = new widget.Label();
        XBAB = new widget.TextBox();
        jLabel116 = new widget.Label();
        WBAB = new widget.TextBox();
        jLabel117 = new widget.Label();
        BAK = new widget.TextBox();
        jLabel118 = new widget.Label();
        XBAK = new widget.TextBox();
        jLabel119 = new widget.Label();
        WBAK = new widget.TextBox();
        jLabel120 = new widget.Label();
        LBAK = new widget.TextBox();
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
        jSeparator11 = new javax.swing.JSeparator();
        jLabel271 = new widget.Label();
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
        jLabel69 = new widget.Label();
        jLabel240 = new widget.Label();
        jLabel241 = new widget.Label();
        SkalaHumptyDumpty1 = new widget.ComboBox();
        jLabel242 = new widget.Label();
        NilaiHumptyDumpty1 = new widget.TextBox();
        jLabel243 = new widget.Label();
        jLabel244 = new widget.Label();
        SkalaHumptyDumpty2 = new widget.ComboBox();
        jLabel246 = new widget.Label();
        NilaiHumptyDumpty2 = new widget.TextBox();
        jLabel247 = new widget.Label();
        jLabel248 = new widget.Label();
        SkalaHumptyDumpty3 = new widget.ComboBox();
        jLabel249 = new widget.Label();
        NilaiHumptyDumpty3 = new widget.TextBox();
        jLabel250 = new widget.Label();
        jLabel251 = new widget.Label();
        SkalaHumptyDumpty4 = new widget.ComboBox();
        jLabel252 = new widget.Label();
        NilaiHumptyDumpty4 = new widget.TextBox();
        jLabel253 = new widget.Label();
        jLabel254 = new widget.Label();
        SkalaHumptyDumpty5 = new widget.ComboBox();
        jLabel255 = new widget.Label();
        NilaiHumptyDumpty5 = new widget.TextBox();
        jLabel256 = new widget.Label();
        jLabel257 = new widget.Label();
        SkalaHumptyDumpty6 = new widget.ComboBox();
        jLabel258 = new widget.Label();
        NilaiHumptyDumpty6 = new widget.TextBox();
        jLabel259 = new widget.Label();
        jLabel260 = new widget.Label();
        SkalaHumptyDumpty7 = new widget.ComboBox();
        jLabel261 = new widget.Label();
        NilaiHumptyDumpty7 = new widget.TextBox();
        TingkatHumptyDumpty = new widget.Label();
        jLabel270 = new widget.Label();
        NilaiHumptyDumptyTotal = new widget.TextBox();
        SkalaWajah = new widget.ComboBox();
        jLabel290 = new widget.Label();
        jLabel291 = new widget.Label();
        jLabel292 = new widget.Label();
        jLabel293 = new widget.Label();
        jLabel294 = new widget.Label();
        jLabel295 = new widget.Label();
        NilaiWajah = new widget.TextBox();
        SkalaKaki = new widget.ComboBox();
        NilaiKaki = new widget.TextBox();
        SkalaAktifitas = new widget.ComboBox();
        NilaiAktifitas = new widget.TextBox();
        SkalaMenangis = new widget.ComboBox();
        NilaiMenangis = new widget.TextBox();
        SkalaBersuara = new widget.ComboBox();
        NilaiBersuara = new widget.TextBox();
        jLabel296 = new widget.Label();
        SkalaNyeri = new widget.TextBox();
        jLabel96 = new widget.Label();
        jLabel57 = new widget.Label();
        Anakke = new widget.TextBox();
        DariSaudara = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        CaraKelahiran = new widget.ComboBox();
        KetCaraKelahiran = new widget.TextBox();
        KelainanBawaan = new widget.ComboBox();
        jLabel68 = new widget.Label();
        KetKelainanBawaan = new widget.TextBox();
        UmurKelahiran = new widget.ComboBox();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel97 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        tbImunisasi = new widget.Table();
        BtnTambahImunisasi = new widget.Button();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel98 = new widget.Label();
        jLabel72 = new widget.Label();
        UsiaTengkurap = new widget.TextBox();
        jLabel73 = new widget.Label();
        UsiaDuduk = new widget.TextBox();
        jLabel74 = new widget.Label();
        UsiaBerdiri = new widget.TextBox();
        jLabel75 = new widget.Label();
        UsiaGigi = new widget.TextBox();
        jLabel76 = new widget.Label();
        UsiaBerjalan = new widget.TextBox();
        jLabel77 = new widget.Label();
        UsiaBicara = new widget.TextBox();
        jLabel78 = new widget.Label();
        UsiaMembaca = new widget.TextBox();
        UsiaMenulis = new widget.TextBox();
        jLabel79 = new widget.Label();
        GangguanEmosi = new widget.TextBox();
        jLabel80 = new widget.Label();
        jSeparator15 = new javax.swing.JSeparator();
        BtnPanggilHapusImunisasi = new widget.Button();
        SG1 = new widget.ComboBox();
        jLabel217 = new widget.Label();
        jLabel218 = new widget.Label();
        SG2 = new widget.ComboBox();
        jLabel219 = new widget.Label();
        jLabel220 = new widget.Label();
        jLabel221 = new widget.Label();
        SG3 = new widget.ComboBox();
        jLabel222 = new widget.Label();
        jLabel223 = new widget.Label();
        jLabel224 = new widget.Label();
        jLabel225 = new widget.Label();
        NilaiGizi1 = new widget.TextBox();
        SG4 = new widget.ComboBox();
        NilaiGizi2 = new widget.TextBox();
        NilaiGizi3 = new widget.TextBox();
        NilaiGizi4 = new widget.TextBox();
        TotalNilaiGizi = new widget.TextBox();
        jLabel297 = new widget.Label();
        Wajib = new widget.ComboBox();
        KetHalanganLain = new widget.TextBox();
        jLabel82 = new widget.Label();
        Toharah = new widget.ComboBox();
        jLabel298 = new widget.Label();
        jLabel83 = new widget.Label();
        Sholat = new widget.ComboBox();
        jLabel299 = new widget.Label();
        MotivasiKesembuhanIbadah = new widget.ComboBox();
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
        scrollPane9 = new widget.ScrollPane();
        tbImunisasi2 = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetail = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        DlgRiwayatImunisasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRiwayatImunisasi.setName("DlgRiwayatImunisasi"); // NOI18N
        DlgRiwayatImunisasi.setUndecorated(true);
        DlgRiwayatImunisasi.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Riwayat Imunisasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        jLabel99.setText("Imunisasi :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelBiasa2.add(jLabel99);
        jLabel99.setBounds(0, 13, 67, 23);

        BtnKeluarImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluarImunisasi.setMnemonic('U');
        BtnKeluarImunisasi.setText("Tutup");
        BtnKeluarImunisasi.setToolTipText("Alt+U");
        BtnKeluarImunisasi.setName("BtnKeluarImunisasi"); // NOI18N
        BtnKeluarImunisasi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarImunisasiActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluarImunisasi);
        BtnKeluarImunisasi.setBounds(340, 50, 100, 30);

        BtnSimpanImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanImunisasi.setMnemonic('S');
        BtnSimpanImunisasi.setText("Simpan");
        BtnSimpanImunisasi.setToolTipText("Alt+S");
        BtnSimpanImunisasi.setName("BtnSimpanImunisasi"); // NOI18N
        BtnSimpanImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanImunisasiActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSimpanImunisasi);
        BtnSimpanImunisasi.setBounds(10, 50, 100, 30);

        BtnImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnImunisasi.setMnemonic('2');
        BtnImunisasi.setToolTipText("Alt+2");
        BtnImunisasi.setName("BtnImunisasi"); // NOI18N
        BtnImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImunisasiActionPerformed(evt);
            }
        });
        BtnImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnImunisasi);
        BtnImunisasi.setBounds(307, 13, 28, 23);

        NmImunisasi.setEditable(false);
        NmImunisasi.setName("NmImunisasi"); // NOI18N
        NmImunisasi.setPreferredSize(new java.awt.Dimension(207, 23));
        panelBiasa2.add(NmImunisasi);
        NmImunisasi.setBounds(124, 13, 180, 23);

        KdImunisasi.setEditable(false);
        KdImunisasi.setName("KdImunisasi"); // NOI18N
        KdImunisasi.setPreferredSize(new java.awt.Dimension(80, 23));
        KdImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(KdImunisasi);
        KdImunisasi.setBounds(71, 13, 50, 23);

        jLabel81.setText("Ke :");
        jLabel81.setName("jLabel81"); // NOI18N
        panelBiasa2.add(jLabel81);
        jLabel81.setBounds(343, 13, 30, 23);

        ImunisasiKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        ImunisasiKe.setName("ImunisasiKe"); // NOI18N
        ImunisasiKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ImunisasiKeKeyPressed(evt);
            }
        });
        panelBiasa2.add(ImunisasiKe);
        ImunisasiKe.setBounds(377, 13, 60, 23);

        BtnHapusImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusImunisasi.setMnemonic('H');
        BtnHapusImunisasi.setText("Hapus");
        BtnHapusImunisasi.setToolTipText("Alt+H");
        BtnHapusImunisasi.setName("BtnHapusImunisasi"); // NOI18N
        BtnHapusImunisasi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusImunisasiActionPerformed(evt);
            }
        });
        BtnHapusImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnHapusImunisasi);
        BtnHapusImunisasi.setBounds(230, 50, 100, 30);

        internalFrame4.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgRiwayatImunisasi.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Keperawatan Rawat Inap Anak ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(954, 3350));
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
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024 08:31:12" }));
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

        TibadiRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jalan Tanpa Bantuan", "Kursi Roda", "Brankar", "Digendong" }));
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
        jLabel27.setText("Sistem Susunan Saraf Pusat :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(50, 530, 187, 23);

        jLabel131.setText("Kepala :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(0, 550, 109, 23);

        SistemSarafKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Hydrocephalus", "Hematoma", "Lain-lain" }));
        SistemSarafKepala.setName("SistemSarafKepala"); // NOI18N
        SistemSarafKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafKepalaKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafKepala);
        SistemSarafKepala.setBounds(120, 550, 103, 23);

        KetSistemSarafKepala.setFocusTraversalPolicyProvider(true);
        KetSistemSarafKepala.setName("KetSistemSarafKepala"); // NOI18N
        KetSistemSarafKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafKepalaKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafKepala);
        KetSistemSarafKepala.setBounds(250, 550, 184, 23);

        jLabel132.setText("Wajah :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(440, 550, 80, 23);

        SistemSarafWajah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Asimetris", "Kelainan Kongenital" }));
        SistemSarafWajah.setName("SistemSarafWajah"); // NOI18N
        SistemSarafWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafWajahKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafWajah);
        SistemSarafWajah.setBounds(520, 550, 150, 23);

        KetSistemSarafWajah.setFocusTraversalPolicyProvider(true);
        KetSistemSarafWajah.setName("KetSistemSarafWajah"); // NOI18N
        KetSistemSarafWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafWajahKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafWajah);
        KetSistemSarafWajah.setBounds(670, 550, 184, 23);

        jLabel133.setText("Leher :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(0, 580, 109, 23);

        SistemSarafLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kaku Kuduk", "Pembesaran Thyroid", "Pembesaran KGB" }));
        SistemSarafLeher.setName("SistemSarafLeher"); // NOI18N
        SistemSarafLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafLeherKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafLeher);
        SistemSarafLeher.setBounds(120, 580, 155, 23);

        jLabel134.setText("Sensorik :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(660, 580, 80, 23);

        SistemSarafSensorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Sakit Nyeri", "Rasa Kebas" }));
        SistemSarafSensorik.setName("SistemSarafSensorik"); // NOI18N
        SistemSarafSensorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafSensorikKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafSensorik);
        SistemSarafSensorik.setBounds(740, 580, 115, 23);

        jLabel135.setText("Kejang :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(310, 580, 60, 23);

        SistemSarafKejang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kuat", "Ada" }));
        SistemSarafKejang.setName("SistemSarafKejang"); // NOI18N
        SistemSarafKejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistemSarafKejangKeyPressed(evt);
            }
        });
        FormInput.add(SistemSarafKejang);
        SistemSarafKejang.setBounds(370, 580, 80, 23);

        KetSistemSarafKejang.setFocusTraversalPolicyProvider(true);
        KetSistemSarafKejang.setName("KetSistemSarafKejang"); // NOI18N
        KetSistemSarafKejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSistemSarafKejangKeyPressed(evt);
            }
        });
        FormInput.add(KetSistemSarafKejang);
        KetSistemSarafKejang.setBounds(450, 580, 184, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Kardiovaskuler :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(50, 610, 122, 23);

        jLabel136.setText("Pulsasi :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(0, 630, 109, 23);

        KardiovaskularPulsasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuat", "Lemah", "Lain-lain" }));
        KardiovaskularPulsasi.setName("KardiovaskularPulsasi"); // NOI18N
        KardiovaskularPulsasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularPulsasiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularPulsasi);
        KardiovaskularPulsasi.setBounds(120, 630, 96, 23);

        jLabel137.setText("Sirkulasi :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(250, 630, 60, 23);

        KardiovaskularSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Akral Hangat", "Akral Dingin", "Edema" }));
        KardiovaskularSirkulasi.setName("KardiovaskularSirkulasi"); // NOI18N
        KardiovaskularSirkulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularSirkulasiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularSirkulasi);
        KardiovaskularSirkulasi.setBounds(310, 630, 120, 23);

        KetKardiovaskularSirkulasi.setFocusTraversalPolicyProvider(true);
        KetKardiovaskularSirkulasi.setName("KetKardiovaskularSirkulasi"); // NOI18N
        KetKardiovaskularSirkulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKardiovaskularSirkulasiKeyPressed(evt);
            }
        });
        FormInput.add(KetKardiovaskularSirkulasi);
        KetKardiovaskularSirkulasi.setBounds(440, 630, 184, 23);

        jLabel138.setText("Denyut Nadi :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(650, 630, 80, 23);

        KardiovaskularDenyutNadi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        KardiovaskularDenyutNadi.setName("KardiovaskularDenyutNadi"); // NOI18N
        KardiovaskularDenyutNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KardiovaskularDenyutNadiKeyPressed(evt);
            }
        });
        FormInput.add(KardiovaskularDenyutNadi);
        KardiovaskularDenyutNadi.setBounds(740, 630, 120, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Respirasi :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(50, 660, 96, 23);

        jLabel139.setText("Retraksi :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(0, 680, 109, 23);

        RespirasiRetraksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ringan", "Berat" }));
        RespirasiRetraksi.setName("RespirasiRetraksi"); // NOI18N
        RespirasiRetraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiRetraksiKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiRetraksi);
        RespirasiRetraksi.setBounds(120, 680, 100, 23);

        RespirasiPolaNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Bradipnea", "Tachipnea" }));
        RespirasiPolaNafas.setName("RespirasiPolaNafas"); // NOI18N
        RespirasiPolaNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiPolaNafasKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiPolaNafas);
        RespirasiPolaNafas.setBounds(310, 680, 102, 23);

        jLabel140.setText("Pola Nafas :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(220, 680, 80, 23);

        jLabel141.setText("Suara Nafas :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(420, 680, 80, 23);

        RespirasiSuaraNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vesikuler", "Wheezing", "Rhonki" }));
        RespirasiSuaraNafas.setName("RespirasiSuaraNafas"); // NOI18N
        RespirasiSuaraNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiSuaraNafasKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiSuaraNafas);
        RespirasiSuaraNafas.setBounds(510, 680, 100, 23);

        jLabel142.setText("Irama :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(670, 710, 60, 23);

        RespirasiIrama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        RespirasiIrama.setName("RespirasiIrama"); // NOI18N
        RespirasiIrama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiIramaKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiIrama);
        RespirasiIrama.setBounds(740, 710, 120, 23);

        jLabel143.setText("Volume :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(0, 710, 109, 23);

        RespirasiVolume.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Hiperventilasi", "Hipoventilasi" }));
        RespirasiVolume.setName("RespirasiVolume"); // NOI18N
        RespirasiVolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiVolumeKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiVolume);
        RespirasiVolume.setBounds(120, 710, 120, 23);

        jLabel144.setText("Jenis Pernafasaan :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(240, 710, 120, 23);

        RespirasiJenisPernafasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pernafasan Dada", "Alat Bantu Pernafasaan" }));
        RespirasiJenisPernafasan.setName("RespirasiJenisPernafasan"); // NOI18N
        RespirasiJenisPernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiJenisPernafasanKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiJenisPernafasan);
        RespirasiJenisPernafasan.setBounds(360, 710, 166, 23);

        KetRespirasiJenisPernafasan.setFocusTraversalPolicyProvider(true);
        KetRespirasiJenisPernafasan.setName("KetRespirasiJenisPernafasan"); // NOI18N
        KetRespirasiJenisPernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetRespirasiJenisPernafasanKeyPressed(evt);
            }
        });
        FormInput.add(KetRespirasiJenisPernafasan);
        KetRespirasiJenisPernafasan.setBounds(530, 710, 135, 23);

        jLabel145.setText("Batuk & Sekresi :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(610, 680, 100, 23);

        RespirasiBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya : Produktif", "Ya : Non Produktif" }));
        RespirasiBatuk.setName("RespirasiBatuk"); // NOI18N
        RespirasiBatuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespirasiBatukKeyPressed(evt);
            }
        });
        FormInput.add(RespirasiBatuk);
        RespirasiBatuk.setBounds(720, 680, 140, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Gastrointestinal :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(50, 740, 129, 23);

        jLabel146.setText("Mulut :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 760, 109, 23);

        GastrointestinalMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Stomatitis", "Mukosa Kering", "Bibir Pucat", "Lain-lain" }));
        GastrointestinalMulut.setName("GastrointestinalMulut"); // NOI18N
        GastrointestinalMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalMulutKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalMulut);
        GastrointestinalMulut.setBounds(120, 760, 120, 23);

        KetGastrointestinalMulut.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalMulut.setName("KetGastrointestinalMulut"); // NOI18N
        KetGastrointestinalMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalMulutKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalMulut);
        KetGastrointestinalMulut.setBounds(240, 760, 190, 23);

        GastrointestinalGigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Karies", "Goyang", "Lain-lain" }));
        GastrointestinalGigi.setName("GastrointestinalGigi"); // NOI18N
        GastrointestinalGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalGigiKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalGigi);
        GastrointestinalGigi.setBounds(120, 820, 95, 23);

        jLabel148.setText("Gigi :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(0, 820, 109, 23);

        KetGastrointestinalGigi.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalGigi.setName("KetGastrointestinalGigi"); // NOI18N
        KetGastrointestinalGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalGigiKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalGigi);
        KetGastrointestinalGigi.setBounds(215, 820, 170, 23);

        jLabel149.setText("Anus :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(700, 820, 50, 23);

        GastrointestinalAnus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Atresia Ani" }));
        GastrointestinalAnus.setName("GastrointestinalAnus"); // NOI18N
        GastrointestinalAnus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalAnusKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalAnus);
        GastrointestinalAnus.setBounds(750, 820, 108, 23);

        jLabel147.setText("Lidah :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 790, 109, 23);

        GastrointestinalLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Kotor", "Gerak Asimetris", "Lain-lain" }));
        GastrointestinalLidah.setName("GastrointestinalLidah"); // NOI18N
        GastrointestinalLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalLidahKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalLidah);
        GastrointestinalLidah.setBounds(120, 790, 130, 23);

        KetGastrointestinalLidah.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalLidah.setName("KetGastrointestinalLidah"); // NOI18N
        KetGastrointestinalLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalLidahKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalLidah);
        KetGastrointestinalLidah.setBounds(250, 790, 190, 23);

        jLabel150.setText("Tenggorokan :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(460, 760, 80, 23);

        GastrointestinalTenggorakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Gangguan Menelan", "Sakit Menelan", "Lain-lain" }));
        GastrointestinalTenggorakan.setName("GastrointestinalTenggorakan"); // NOI18N
        GastrointestinalTenggorakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalTenggorakanKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalTenggorakan);
        GastrointestinalTenggorakan.setBounds(540, 760, 150, 23);

        KetGastrointestinalTenggorakan.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalTenggorakan.setName("KetGastrointestinalTenggorakan"); // NOI18N
        KetGastrointestinalTenggorakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalTenggorakanKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalTenggorakan);
        KetGastrointestinalTenggorakan.setBounds(690, 760, 164, 23);

        jLabel151.setText("Abdomen :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(460, 790, 80, 23);

        GastrointestinalAbdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supel", "Asictes", "Tegang", "Nyeri Tekan/Lepas", "Lain-lain" }));
        GastrointestinalAbdomen.setName("GastrointestinalAbdomen"); // NOI18N
        GastrointestinalAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalAbdomenKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalAbdomen);
        GastrointestinalAbdomen.setBounds(540, 790, 150, 23);

        KetGastrointestinalAbdomen.setFocusTraversalPolicyProvider(true);
        KetGastrointestinalAbdomen.setName("KetGastrointestinalAbdomen"); // NOI18N
        KetGastrointestinalAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetGastrointestinalAbdomenKeyPressed(evt);
            }
        });
        FormInput.add(KetGastrointestinalAbdomen);
        KetGastrointestinalAbdomen.setBounds(690, 790, 164, 23);

        jLabel152.setText("Peistatik Usus :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(400, 820, 100, 23);

        GastrointestinalUsus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Tidak Ada Bising Usus", "Hiperistaltik" }));
        GastrointestinalUsus.setName("GastrointestinalUsus"); // NOI18N
        GastrointestinalUsus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GastrointestinalUsusKeyPressed(evt);
            }
        });
        FormInput.add(GastrointestinalUsus);
        GastrointestinalUsus.setBounds(500, 820, 164, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Neurologi :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(50, 850, 98, 23);

        jLabel153.setText("Sensorik :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(0, 870, 109, 23);

        NeurologiSensorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Sakit Nyeri", "Rasa Kebas", "Lain-lain" }));
        NeurologiSensorik.setName("NeurologiSensorik"); // NOI18N
        NeurologiSensorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiSensorikKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiSensorik);
        NeurologiSensorik.setBounds(120, 870, 108, 23);

        jLabel154.setText("Motorik :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(0, 900, 109, 23);

        NeurologiMotorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Hemiparese", "Tetraparese", "Tremor", "Lain-lain" }));
        NeurologiMotorik.setName("NeurologiMotorik"); // NOI18N
        NeurologiMotorik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiMotorikKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiMotorik);
        NeurologiMotorik.setBounds(120, 900, 108, 23);

        jLabel155.setText("Otot :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(730, 900, 40, 23);

        NeurologiOtot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuat", "Lemah" }));
        NeurologiOtot.setName("NeurologiOtot"); // NOI18N
        NeurologiOtot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiOtotKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiOtot);
        NeurologiOtot.setBounds(780, 900, 82, 23);

        jLabel156.setText("Penglihatan :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(230, 870, 80, 23);

        NeurologiPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Ada Kelainan" }));
        NeurologiPenglihatan.setName("NeurologiPenglihatan"); // NOI18N
        NeurologiPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiPenglihatan);
        NeurologiPenglihatan.setBounds(320, 870, 115, 23);

        KetNeurologiPenglihatan.setFocusTraversalPolicyProvider(true);
        KetNeurologiPenglihatan.setName("KetNeurologiPenglihatan"); // NOI18N
        KetNeurologiPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNeurologiPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(KetNeurologiPenglihatan);
        KetNeurologiPenglihatan.setBounds(440, 870, 150, 23);

        jLabel157.setText("Alat Bantu Penglihatan :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(590, 870, 140, 23);

        NeurologiAlatBantuPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Kacamata", "Lensa Kontak" }));
        NeurologiAlatBantuPenglihatan.setName("NeurologiAlatBantuPenglihatan"); // NOI18N
        NeurologiAlatBantuPenglihatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiAlatBantuPenglihatanKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiAlatBantuPenglihatan);
        NeurologiAlatBantuPenglihatan.setBounds(740, 870, 120, 23);

        jLabel158.setText("Pendengaran :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(230, 900, 80, 23);

        NeurologiPendengaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Berdengung", "Nyeri", "Tuli", "Keluar Cairan", "Lain-lain" }));
        NeurologiPendengaran.setName("NeurologiPendengaran"); // NOI18N
        NeurologiPendengaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiPendengaranKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiPendengaran);
        NeurologiPendengaran.setBounds(320, 900, 117, 23);

        jLabel159.setText("Bicara :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(440, 900, 50, 23);

        NeurologiBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jelas", "Tidak Jelas" }));
        NeurologiBicara.setName("NeurologiBicara"); // NOI18N
        NeurologiBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NeurologiBicaraKeyPressed(evt);
            }
        });
        FormInput.add(NeurologiBicara);
        NeurologiBicara.setBounds(490, 900, 105, 23);

        KetNeurologiBicara.setFocusTraversalPolicyProvider(true);
        KetNeurologiBicara.setName("KetNeurologiBicara"); // NOI18N
        KetNeurologiBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNeurologiBicaraKeyPressed(evt);
            }
        });
        FormInput.add(KetNeurologiBicara);
        KetNeurologiBicara.setBounds(600, 900, 127, 23);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("Integument :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(50, 930, 108, 23);

        jLabel160.setText("Kulit :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 950, 109, 23);

        IntegumentKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Rash/Kemerahan", "Luka", "Memar", "Ptekie", "Bula" }));
        IntegumentKulit.setName("IntegumentKulit"); // NOI18N
        IntegumentKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentKulitKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentKulit);
        IntegumentKulit.setBounds(120, 950, 134, 23);

        jLabel161.setText("Warna Kulit :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(260, 950, 70, 23);

        IntegumentWarnaKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Pucat", "Sianosis", "Lain-lain" }));
        IntegumentWarnaKulit.setName("IntegumentWarnaKulit"); // NOI18N
        IntegumentWarnaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentWarnaKulitKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentWarnaKulit);
        IntegumentWarnaKulit.setBounds(330, 950, 92, 23);

        jLabel162.setText("Turgor :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(420, 950, 48, 23);

        IntegumentTurgor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Buruk" }));
        IntegumentTurgor.setName("IntegumentTurgor"); // NOI18N
        IntegumentTurgor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentTurgorKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentTurgor);
        IntegumentTurgor.setBounds(480, 950, 86, 23);

        IntegumentDecubitus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Usia > 65 tahun", "Obesitas", "Imobilisasi", "Paraplegi/Vegetative State ", "Dirawat Di HCU", "Penyakit Kronis (DM, CHF, CKD)", "Inkontinentia Uri/Alvi" }));
        IntegumentDecubitus.setName("IntegumentDecubitus"); // NOI18N
        IntegumentDecubitus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntegumentDecubitusKeyPressed(evt);
            }
        });
        FormInput.add(IntegumentDecubitus);
        IntegumentDecubitus.setBounds(660, 950, 202, 23);

        jLabel163.setText("Resiko Decubi :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(560, 950, 90, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Muskuloskletal :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(50, 980, 122, 23);

        jLabel164.setText("Oedema :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(0, 1000, 109, 23);

        MuskuloskletalOedema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalOedema.setName("MuskuloskletalOedema"); // NOI18N
        MuskuloskletalOedema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalOedemaKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalOedema);
        MuskuloskletalOedema.setBounds(120, 1000, 100, 23);

        KetMuskuloskletalOedema.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalOedema.setName("KetMuskuloskletalOedema"); // NOI18N
        KetMuskuloskletalOedema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalOedemaKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalOedema);
        KetMuskuloskletalOedema.setBounds(220, 1000, 220, 23);

        KetMuskuloskletalFraktur.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalFraktur.setName("KetMuskuloskletalFraktur"); // NOI18N
        KetMuskuloskletalFraktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalFrakturKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalFraktur);
        KetMuskuloskletalFraktur.setBounds(220, 1030, 220, 23);

        MuskuloskletalFraktur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalFraktur.setName("MuskuloskletalFraktur"); // NOI18N
        MuskuloskletalFraktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalFrakturKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalFraktur);
        MuskuloskletalFraktur.setBounds(120, 1030, 100, 23);

        jLabel165.setText("Fraktur :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(0, 1030, 109, 23);

        jLabel166.setText("Nyeri Sendi :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(450, 1030, 80, 23);

        MuskuloskletalNyeriSendi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        MuskuloskletalNyeriSendi.setName("MuskuloskletalNyeriSendi"); // NOI18N
        MuskuloskletalNyeriSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalNyeriSendiKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalNyeriSendi);
        MuskuloskletalNyeriSendi.setBounds(530, 1030, 100, 23);

        KetMuskuloskletalNyeriSendi.setFocusTraversalPolicyProvider(true);
        KetMuskuloskletalNyeriSendi.setName("KetMuskuloskletalNyeriSendi"); // NOI18N
        KetMuskuloskletalNyeriSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetMuskuloskletalNyeriSendiKeyPressed(evt);
            }
        });
        FormInput.add(KetMuskuloskletalNyeriSendi);
        KetMuskuloskletalNyeriSendi.setBounds(640, 1030, 220, 23);

        jLabel167.setText("Pergerakan Sendi :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(460, 1000, 109, 23);

        MuskuloskletalPegerakanSendi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bebas", "Terbatas" }));
        MuskuloskletalPegerakanSendi.setName("MuskuloskletalPegerakanSendi"); // NOI18N
        MuskuloskletalPegerakanSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalPegerakanSendiKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalPegerakanSendi);
        MuskuloskletalPegerakanSendi.setBounds(570, 1000, 93, 23);

        MuskuloskletalKekuatanOtot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Lemah", "Tremor" }));
        MuskuloskletalKekuatanOtot.setName("MuskuloskletalKekuatanOtot"); // NOI18N
        MuskuloskletalKekuatanOtot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MuskuloskletalKekuatanOtotKeyPressed(evt);
            }
        });
        FormInput.add(MuskuloskletalKekuatanOtot);
        MuskuloskletalKekuatanOtot.setBounds(770, 1000, 85, 23);

        jLabel168.setText("Kekuatan Otot :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(680, 1000, 90, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("Eliminasi :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(50, 1060, 95, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("X/");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(220, 1080, 13, 23);

        KBAB.setHighlighter(null);
        KBAB.setName("KBAB"); // NOI18N
        KBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KBABKeyPressed(evt);
            }
        });
        FormInput.add(KBAB);
        KBAB.setBounds(420, 1080, 175, 23);

        jLabel114.setText("Konsistensi :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(340, 1080, 70, 23);

        BAB.setHighlighter(null);
        BAB.setName("BAB"); // NOI18N
        BAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BABKeyPressed(evt);
            }
        });
        FormInput.add(BAB);
        BAB.setBounds(170, 1080, 50, 23);

        jLabel115.setText("BAB : Frekuensi :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1080, 166, 23);

        XBAB.setHighlighter(null);
        XBAB.setName("XBAB"); // NOI18N
        XBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                XBABKeyPressed(evt);
            }
        });
        FormInput.add(XBAB);
        XBAB.setBounds(240, 1080, 75, 23);

        jLabel116.setText("Warna :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(620, 1080, 55, 23);

        WBAB.setHighlighter(null);
        WBAB.setName("WBAB"); // NOI18N
        WBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBABKeyPressed(evt);
            }
        });
        FormInput.add(WBAB);
        WBAB.setBounds(680, 1080, 175, 23);

        jLabel117.setText("BAK : Frekuensi :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1110, 166, 23);

        BAK.setHighlighter(null);
        BAK.setName("BAK"); // NOI18N
        BAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BAKKeyPressed(evt);
            }
        });
        FormInput.add(BAK);
        BAK.setBounds(170, 1110, 50, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("X/");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(220, 1110, 13, 23);

        XBAK.setHighlighter(null);
        XBAK.setName("XBAK"); // NOI18N
        XBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                XBAKKeyPressed(evt);
            }
        });
        FormInput.add(XBAK);
        XBAK.setBounds(240, 1110, 75, 23);

        jLabel119.setText("Warna :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(340, 1110, 70, 23);

        WBAK.setHighlighter(null);
        WBAK.setName("WBAK"); // NOI18N
        WBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBAKKeyPressed(evt);
            }
        });
        FormInput.add(WBAK);
        WBAK.setBounds(420, 1110, 175, 23);

        jLabel120.setText("Lain-lain :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(620, 1110, 55, 23);

        LBAK.setHighlighter(null);
        LBAK.setName("LBAK"); // NOI18N
        LBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LBAKKeyPressed(evt);
            }
        });
        FormInput.add(LBAK);
        LBAK.setBounds(680, 1110, 175, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 1140, 880, 1);

        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel169.setText("VI. POLA KEHIDUPAN SEHARI - HARI ");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(10, 1460, 490, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("b. Pola Nutrisi :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(50, 1560, 100, 23);

        jLabel170.setText("Mandi :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(0, 1500, 109, 23);

        PolaAktifitasEliminasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasEliminasi.setName("PolaAktifitasEliminasi"); // NOI18N
        PolaAktifitasEliminasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasEliminasiKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasEliminasi);
        PolaAktifitasEliminasi.setBounds(120, 1530, 164, 23);

        jLabel171.setText("Makan/Minum :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(310, 1500, 100, 23);

        jLabel172.setText("Berpakaian :");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(610, 1500, 80, 23);

        jLabel173.setText("Eliminasi :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(0, 1530, 109, 23);

        jLabel174.setText("Berpindah :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(310, 1530, 100, 23);

        PolaAktifitasMandi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasMandi.setName("PolaAktifitasMandi"); // NOI18N
        PolaAktifitasMandi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasMandiKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasMandi);
        PolaAktifitasMandi.setBounds(120, 1500, 164, 23);

        PolaAktifitasMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasMakan.setName("PolaAktifitasMakan"); // NOI18N
        PolaAktifitasMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasMakanKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasMakan);
        PolaAktifitasMakan.setBounds(410, 1500, 164, 23);

        PolaAktifitasBerpakaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasBerpakaian.setName("PolaAktifitasBerpakaian"); // NOI18N
        PolaAktifitasBerpakaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasBerpakaianKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasBerpakaian);
        PolaAktifitasBerpakaian.setBounds(690, 1500, 164, 23);

        PolaAktifitasBerpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan Orang Lain" }));
        PolaAktifitasBerpindah.setName("PolaAktifitasBerpindah"); // NOI18N
        PolaAktifitasBerpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaAktifitasBerpindahKeyPressed(evt);
            }
        });
        FormInput.add(PolaAktifitasBerpindah);
        PolaAktifitasBerpindah.setBounds(410, 1530, 164, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("a. Pola Aktifitas :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(50, 1480, 128, 23);

        jLabel121.setText("Porsi Makan");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(110, 1560, 73, 23);

        PolaNutrisiPorsi.setHighlighter(null);
        PolaNutrisiPorsi.setName("PolaNutrisiPorsi"); // NOI18N
        PolaNutrisiPorsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiPorsiKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiPorsi);
        PolaNutrisiPorsi.setBounds(190, 1560, 50, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("porsi,");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(240, 1560, 31, 23);

        jLabel123.setText("Frekuensi Makan");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(260, 1560, 94, 23);

        PolaNutrisiFrekuensi.setHighlighter(null);
        PolaNutrisiFrekuensi.setName("PolaNutrisiFrekuensi"); // NOI18N
        PolaNutrisiFrekuensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiFrekuensiKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiFrekuensi);
        PolaNutrisiFrekuensi.setBounds(360, 1560, 50, 23);

        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel175.setText("x/hari,");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(420, 1560, 40, 23);

        jLabel177.setText("Jenis Makanan");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(430, 1560, 92, 23);

        PolaNutrisiJenis.setHighlighter(null);
        PolaNutrisiJenis.setName("PolaNutrisiJenis"); // NOI18N
        PolaNutrisiJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaNutrisiJenisKeyPressed(evt);
            }
        });
        FormInput.add(PolaNutrisiJenis);
        PolaNutrisiJenis.setBounds(530, 1560, 328, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("c. Pola Tidur :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(50, 1590, 80, 23);

        jLabel176.setText("Lama Tidur");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(110, 1590, 60, 23);

        PolaTidurLama.setHighlighter(null);
        PolaTidurLama.setName("PolaTidurLama"); // NOI18N
        PolaTidurLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaTidurLamaKeyPressed(evt);
            }
        });
        FormInput.add(PolaTidurLama);
        PolaTidurLama.setBounds(180, 1590, 50, 23);

        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel178.setText(" jam/hari,");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(230, 1590, 51, 23);

        PolaTidurGangguan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Gangguan", "Insomnia" }));
        PolaTidurGangguan.setName("PolaTidurGangguan"); // NOI18N
        PolaTidurGangguan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaTidurGangguanKeyPressed(evt);
            }
        });
        FormInput.add(PolaTidurGangguan);
        PolaTidurGangguan.setBounds(290, 1590, 164, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1620, 880, 1);

        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel180.setText("VII. PENGKAJIAN FUNGSI");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(10, 1620, 180, 23);

        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel179.setText("a. Kemampuan Aktifitas Sehari-hari");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(50, 1640, 180, 23);

        AktifitasSehari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan minimal", "Bantuan Sebagian", "Ketergantungan Total" }));
        AktifitasSehari2.setName("AktifitasSehari2"); // NOI18N
        AktifitasSehari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasSehari2KeyPressed(evt);
            }
        });
        FormInput.add(AktifitasSehari2);
        AktifitasSehari2.setBounds(230, 1640, 158, 23);

        jLabel181.setText("b. Berjalan :");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(400, 1640, 70, 23);

        Berjalan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Penurunan Kekuatan/ROM", "Paralisis", "Sering Jatuh", "Deformitas", "Hilang keseimbangan", "Riwayat Patah Tulang", "Lain-lain" }));
        Berjalan.setName("Berjalan"); // NOI18N
        Berjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerjalanKeyPressed(evt);
            }
        });
        FormInput.add(Berjalan);
        Berjalan.setBounds(470, 1640, 178, 23);

        KeteranganBerjalan.setFocusTraversalPolicyProvider(true);
        KeteranganBerjalan.setName("KeteranganBerjalan"); // NOI18N
        KeteranganBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganBerjalan);
        KeteranganBerjalan.setBounds(650, 1640, 204, 23);

        jLabel182.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel182.setText("c. Aktifitas");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(50, 1670, 60, 23);

        Aktifitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tirah Baring", "Duduk", "Berjalan" }));
        Aktifitas.setName("Aktifitas"); // NOI18N
        Aktifitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasKeyPressed(evt);
            }
        });
        FormInput.add(Aktifitas);
        Aktifitas.setBounds(110, 1670, 110, 23);

        jLabel183.setText("d. Alat Ambulasi :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(230, 1670, 100, 23);

        AlatAmbulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Walker", "Tongkat", "Kursi Roda", "Tidak Menggunakan" }));
        AlatAmbulasi.setName("AlatAmbulasi"); // NOI18N
        AlatAmbulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatAmbulasiKeyPressed(evt);
            }
        });
        FormInput.add(AlatAmbulasi);
        AlatAmbulasi.setBounds(330, 1670, 147, 23);

        jLabel184.setText("e. Ekstremitas Atas :");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(480, 1670, 110, 23);

        EkstrimitasAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Lemah", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasAtas.setName("EkstrimitasAtas"); // NOI18N
        EkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasAtas);
        EkstrimitasAtas.setBounds(600, 1670, 120, 23);

        KeteranganEkstrimitasAtas.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasAtas.setName("KeteranganEkstrimitasAtas"); // NOI18N
        KeteranganEkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasAtas);
        KeteranganEkstrimitasAtas.setBounds(720, 1670, 137, 23);

        jLabel185.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel185.setText("f. Ekstremitas Bawah");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(50, 1700, 110, 23);

        EkstrimitasBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Varises", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasBawah.setName("EkstrimitasBawah"); // NOI18N
        EkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasBawah);
        EkstrimitasBawah.setBounds(160, 1700, 120, 23);

        KeteranganEkstrimitasBawah.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasBawah.setName("KeteranganEkstrimitasBawah"); // NOI18N
        KeteranganEkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasBawah);
        KeteranganEkstrimitasBawah.setBounds(290, 1700, 137, 23);

        jLabel186.setText("g. Kemampuan Menggenggam :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(430, 1700, 160, 23);

        KemampuanMenggenggam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Terakhir", "Lain-lain" }));
        KemampuanMenggenggam.setName("KemampuanMenggenggam"); // NOI18N
        KemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanMenggenggam);
        KemampuanMenggenggam.setBounds(590, 1700, 147, 23);

        KeteranganKemampuanMenggenggam.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanMenggenggam.setName("KeteranganKemampuanMenggenggam"); // NOI18N
        KeteranganKemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanMenggenggam);
        KeteranganKemampuanMenggenggam.setBounds(740, 1700, 115, 23);

        jLabel187.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel187.setText("h. Kemampuan Koordinasi");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(50, 1730, 140, 23);

        KemampuanKoordinasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Ada Masalah" }));
        KemampuanKoordinasi.setName("KemampuanKoordinasi"); // NOI18N
        KemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanKoordinasi);
        KemampuanKoordinasi.setBounds(190, 1730, 147, 23);

        KeteranganKemampuanKoordinasi.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanKoordinasi.setName("KeteranganKemampuanKoordinasi"); // NOI18N
        KeteranganKemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanKoordinasi);
        KeteranganKemampuanKoordinasi.setBounds(340, 1730, 147, 23);

        jLabel188.setText("i. Kesimpulan Gangguan Fungsi :");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(490, 1730, 170, 23);

        KesimpulanGangguanFungsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak (Tidak Perlu Co DPJP)", "Ya (Co DPJP)" }));
        KesimpulanGangguanFungsi.setName("KesimpulanGangguanFungsi"); // NOI18N
        KesimpulanGangguanFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanGangguanFungsiKeyPressed(evt);
            }
        });
        FormInput.add(KesimpulanGangguanFungsi);
        KesimpulanGangguanFungsi.setBounds(660, 1730, 195, 23);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 1760, 880, 1);

        jLabel189.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel189.setText("VIII. RIWAYAT PSIKOLOGIS – SOSIAL – EKONOMI – BUDAYA – SPIRITUAL");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(10, 1760, 490, 23);

        jLabel190.setText(":");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(150, 1780, 10, 23);

        KondisiPsikologis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Marah", "Takut", "Depresi", "Cepat Lelah", "Cemas", "Gelisah", "Sulit Tidur", "Lain-lain" }));
        KondisiPsikologis.setName("KondisiPsikologis"); // NOI18N
        KondisiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KondisiPsikologis);
        KondisiPsikologis.setBounds(160, 1780, 142, 23);

        jLabel191.setText("b. Adakah Perilaku :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(300, 1780, 110, 23);

        AdakahPerilaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Perilaku Kekerasan", "Gangguan Efek", "Gangguan Memori", "Halusinasi", "Kecenderungan Percobaan Bunuh Diri", "Lain-lain" }));
        AdakahPerilaku.setName("AdakahPerilaku"); // NOI18N
        AdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(AdakahPerilaku);
        AdakahPerilaku.setBounds(420, 1780, 235, 23);

        KeteranganAdakahPerilaku.setFocusTraversalPolicyProvider(true);
        KeteranganAdakahPerilaku.setName("KeteranganAdakahPerilaku"); // NOI18N
        KeteranganAdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganAdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganAdakahPerilaku);
        KeteranganAdakahPerilaku.setBounds(660, 1780, 202, 23);

        jLabel192.setText(":");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(200, 1810, 10, 23);

        GangguanJiwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        GangguanJiwa.setName("GangguanJiwa"); // NOI18N
        GangguanJiwa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanJiwaKeyPressed(evt);
            }
        });
        FormInput.add(GangguanJiwa);
        GangguanJiwa.setBounds(210, 1810, 77, 23);

        jLabel193.setText("d. Hubungan Pasien dengan Anggota Keluarga :");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(290, 1810, 240, 23);

        HubunganAnggotaKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Harmonis", "Kurang Harmonis", "Tidak Harmonis", "Konflik Besar" }));
        HubunganAnggotaKeluarga.setName("HubunganAnggotaKeluarga"); // NOI18N
        HubunganAnggotaKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganAnggotaKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(HubunganAnggotaKeluarga);
        HubunganAnggotaKeluarga.setBounds(540, 1810, 133, 23);

        jLabel194.setText("e. Agama :");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(670, 1810, 60, 23);

        Agama.setEditable(false);
        Agama.setFocusTraversalPolicyProvider(true);
        Agama.setName("Agama"); // NOI18N
        FormInput.add(Agama);
        Agama.setBounds(740, 1810, 120, 23);

        jLabel195.setText(":");
        jLabel195.setName("jLabel195"); // NOI18N
        FormInput.add(jLabel195);
        jLabel195.setBounds(130, 1840, 10, 23);

        TinggalDengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sendiri", "Orang Tua", "Suami/Istri", "Keluarga", "Lain-lain" }));
        TinggalDengan.setName("TinggalDengan"); // NOI18N
        TinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(TinggalDengan);
        TinggalDengan.setBounds(150, 1840, 105, 23);

        KeteranganTinggalDengan.setFocusTraversalPolicyProvider(true);
        KeteranganTinggalDengan.setName("KeteranganTinggalDengan"); // NOI18N
        KeteranganTinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganTinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganTinggalDengan);
        KeteranganTinggalDengan.setBounds(260, 1840, 137, 23);

        jLabel196.setText("g. Pekerjaan :");
        jLabel196.setName("jLabel196"); // NOI18N
        FormInput.add(jLabel196);
        jLabel196.setBounds(390, 1840, 83, 23);

        PekerjaanPasien.setEditable(false);
        PekerjaanPasien.setFocusTraversalPolicyProvider(true);
        PekerjaanPasien.setName("PekerjaanPasien"); // NOI18N
        FormInput.add(PekerjaanPasien);
        PekerjaanPasien.setBounds(480, 1840, 140, 23);

        jLabel197.setText("h. Pembayaran :");
        jLabel197.setName("jLabel197"); // NOI18N
        FormInput.add(jLabel197);
        jLabel197.setBounds(620, 1840, 90, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setFocusTraversalPolicyProvider(true);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(720, 1840, 140, 23);

        jLabel198.setText(":");
        jLabel198.setName("jLabel198"); // NOI18N
        FormInput.add(jLabel198);
        jLabel198.setBounds(330, 1870, 10, 23);

        NilaiKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        NilaiKepercayaan.setName("NilaiKepercayaan"); // NOI18N
        NilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(NilaiKepercayaan);
        NilaiKepercayaan.setBounds(340, 1870, 105, 23);

        KeteranganNilaiKepercayaan.setFocusTraversalPolicyProvider(true);
        KeteranganNilaiKepercayaan.setName("KeteranganNilaiKepercayaan"); // NOI18N
        KeteranganNilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganNilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganNilaiKepercayaan);
        KeteranganNilaiKepercayaan.setBounds(450, 1870, 160, 23);

        jLabel199.setText("j. Bahasa Sehari-hari :");
        jLabel199.setName("jLabel199"); // NOI18N
        FormInput.add(jLabel199);
        jLabel199.setBounds(610, 1870, 120, 23);

        Bahasa.setEditable(false);
        Bahasa.setFocusTraversalPolicyProvider(true);
        Bahasa.setName("Bahasa"); // NOI18N
        FormInput.add(Bahasa);
        Bahasa.setBounds(740, 1870, 120, 23);

        jLabel200.setText(":");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(150, 1900, 10, 23);

        PendidikanPasien.setEditable(false);
        PendidikanPasien.setFocusTraversalPolicyProvider(true);
        PendidikanPasien.setName("PendidikanPasien"); // NOI18N
        FormInput.add(PendidikanPasien);
        PendidikanPasien.setBounds(160, 1900, 100, 23);

        jLabel201.setText("l. Pendidikan P.J. :");
        jLabel201.setName("jLabel201"); // NOI18N
        FormInput.add(jLabel201);
        jLabel201.setBounds(260, 1900, 100, 23);

        PendidikanPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA", "SLTA/SEDERAJAT", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        PendidikanPJ.setName("PendidikanPJ"); // NOI18N
        PendidikanPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PendidikanPJKeyPressed(evt);
            }
        });
        FormInput.add(PendidikanPJ);
        PendidikanPJ.setBounds(360, 1900, 135, 23);

        jLabel202.setText("m. Edukasi Diberikan Kepada :");
        jLabel202.setName("jLabel202"); // NOI18N
        FormInput.add(jLabel202);
        jLabel202.setBounds(500, 1900, 160, 23);

        EdukasiPsikolgis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien", "Keluarga" }));
        EdukasiPsikolgis.setName("EdukasiPsikolgis"); // NOI18N
        EdukasiPsikolgis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiPsikolgisKeyPressed(evt);
            }
        });
        FormInput.add(EdukasiPsikolgis);
        EdukasiPsikolgis.setBounds(660, 1900, 95, 23);

        KeteranganEdukasiPsikologis.setFocusTraversalPolicyProvider(true);
        KeteranganEdukasiPsikologis.setName("KeteranganEdukasiPsikologis"); // NOI18N
        KeteranganEdukasiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEdukasiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEdukasiPsikologis);
        KeteranganEdukasiPsikologis.setBounds(760, 1900, 99, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 2010, 880, 1);

        jLabel203.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel203.setText("IX. PENILAIAN TINGKAT NYERI");
        jLabel203.setName("jLabel203"); // NOI18N
        FormInput.add(jLabel203);
        jLabel203.setBounds(10, 2010, 380, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(370, 2140, 1, 140);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(40, 2150, 320, 130);

        Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Nyeri", "Nyeri Akut", "Nyeri Kronis" }));
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Nyeri);
        Nyeri.setBounds(380, 2150, 130, 23);

        jLabel204.setText("Penyebab :");
        jLabel204.setName("jLabel204"); // NOI18N
        FormInput.add(jLabel204);
        jLabel204.setBounds(510, 2150, 60, 23);

        Provokes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proses Penyakit", "Benturan", "Lain-lain" }));
        Provokes.setName("Provokes"); // NOI18N
        Provokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProvokesKeyPressed(evt);
            }
        });
        FormInput.add(Provokes);
        Provokes.setBounds(580, 2150, 130, 23);

        KetProvokes.setFocusTraversalPolicyProvider(true);
        KetProvokes.setName("KetProvokes"); // NOI18N
        KetProvokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProvokesKeyPressed(evt);
            }
        });
        FormInput.add(KetProvokes);
        KetProvokes.setBounds(710, 2150, 146, 23);

        jLabel205.setText("Kualitas :");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(370, 2180, 55, 23);

        Quality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seperti Tertusuk", "Berdenyut", "Teriris", "Tertindih", "Tertiban", "Lain-lain" }));
        Quality.setName("Quality"); // NOI18N
        Quality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QualityKeyPressed(evt);
            }
        });
        FormInput.add(Quality);
        Quality.setBounds(430, 2180, 140, 23);

        KetQuality.setFocusTraversalPolicyProvider(true);
        KetQuality.setName("KetQuality"); // NOI18N
        KetQuality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetQualityKeyPressed(evt);
            }
        });
        FormInput.add(KetQuality);
        KetQuality.setBounds(580, 2180, 280, 23);

        jLabel206.setText("Wilayah :");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(370, 2210, 55, 23);

        jLabel207.setText("Lokasi :");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(400, 2230, 60, 23);

        Lokasi.setFocusTraversalPolicyProvider(true);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput.add(Lokasi);
        Lokasi.setBounds(460, 2230, 220, 23);

        jLabel208.setText("Menyebar :");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(690, 2230, 79, 23);

        Menyebar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Menyebar.setName("Menyebar"); // NOI18N
        Menyebar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenyebarKeyPressed(evt);
            }
        });
        FormInput.add(Menyebar);
        Menyebar.setBounds(780, 2230, 80, 23);

        jLabel211.setText("Waktu / Durasi :");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(630, 2260, 90, 23);

        Durasi.setFocusTraversalPolicyProvider(true);
        Durasi.setName("Durasi"); // NOI18N
        Durasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DurasiKeyPressed(evt);
            }
        });
        FormInput.add(Durasi);
        Durasi.setBounds(730, 2260, 90, 23);

        jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel212.setText("Menit");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(820, 2260, 35, 23);

        jLabel213.setText("Nyeri hilang bila :");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(0, 2290, 130, 23);

        NyeriHilang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Istirahat", "Medengar Musik", "Minum Obat" }));
        NyeriHilang.setName("NyeriHilang"); // NOI18N
        NyeriHilang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriHilangKeyPressed(evt);
            }
        });
        FormInput.add(NyeriHilang);
        NyeriHilang.setBounds(140, 2290, 130, 23);

        KetNyeri.setFocusTraversalPolicyProvider(true);
        KetNyeri.setName("KetNyeri"); // NOI18N
        KetNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNyeriKeyPressed(evt);
            }
        });
        FormInput.add(KetNyeri);
        KetNyeri.setBounds(270, 2290, 150, 23);

        jLabel214.setText("Diberitahukan pada dokter ?");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(480, 2290, 150, 23);

        PadaDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PadaDokter.setName("PadaDokter"); // NOI18N
        PadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(PadaDokter);
        PadaDokter.setBounds(640, 2290, 80, 23);

        jLabel215.setText("Jam  :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(720, 2290, 50, 23);

        KetPadaDokter.setFocusTraversalPolicyProvider(true);
        KetPadaDokter.setName("KetPadaDokter"); // NOI18N
        KetPadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(KetPadaDokter);
        KetPadaDokter.setBounds(780, 2290, 80, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 2330, 880, 1);

        jLabel216.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel216.setText("X. PENILAIAN RESIKO JATUH");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(10, 2330, 380, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 2630, 880, 1);

        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel271.setText("XI. SKRINING GIZI");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(10, 2630, 380, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 2830, 880, 1);

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
        Scroll6.setBounds(10, 3140, 400, 143);

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
        TabRencanaKeperawatan.setBounds(440, 3140, 420, 143);

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
        BtnTambahMasalah.setBounds(370, 3290, 28, 23);

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
        BtnAllMasalah.setBounds(340, 3290, 28, 23);

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
        BtnCariMasalah.setBounds(300, 3290, 28, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(80, 3290, 215, 23);

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
        BtnTambahRencana.setBounds(810, 3290, 28, 23);

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
        BtnAllRencana.setBounds(780, 3290, 28, 23);

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
        BtnCariRencana.setBounds(750, 3290, 28, 23);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(440, 3290, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(510, 3290, 235, 23);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(20, 3290, 60, 23);

        jLabel280.setText(":");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(170, 1730, 10, 23);

        jLabel281.setText(":");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(150, 1700, 10, 23);

        jLabel282.setText(":");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(100, 1670, 10, 23);

        jLabel283.setText(":");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(220, 1640, 10, 23);

        jLabel284.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel284.setText("a. Kondisi Psikologis");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(50, 1780, 110, 23);

        jLabel285.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel285.setText("c. Gangguan Jiwa di Masa Lalu");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(50, 1810, 160, 23);

        jLabel286.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel286.setText("f. Tinggal Dengan");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(50, 1840, 100, 23);

        jLabel287.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel287.setText("i. Nilai-nilai Kepercayaan/Budaya Yang Perlu Diperhatikan");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(50, 1870, 290, 23);

        jLabel288.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel288.setText("k. Pendidikan Pasien");
        jLabel288.setName("jLabel288"); // NOI18N
        FormInput.add(jLabel288);
        jLabel288.setBounds(50, 1900, 110, 23);

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
        jLabel60.setBounds(40, 2860, 590, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("1. Umur > 65 Tahun");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(40, 2880, 260, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("2. Keterbatasan Mobilitas");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(40, 2910, 260, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("3. Perawatan Atau Pengobatan Lanjutan");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(40, 2940, 260, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("4. Bantuan Untuk Melakukan Aktifitas Sehari-Hari");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(40, 2970, 260, 23);

        Kriteria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria1.setName("Kriteria1"); // NOI18N
        FormInput.add(Kriteria1);
        Kriteria1.setBounds(330, 2880, 80, 23);

        Kriteria2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria2.setName("Kriteria2"); // NOI18N
        FormInput.add(Kriteria2);
        Kriteria2.setBounds(330, 2910, 80, 23);

        Kriteria3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria3.setName("Kriteria3"); // NOI18N
        FormInput.add(Kriteria3);
        Kriteria3.setBounds(330, 2940, 80, 23);

        Kriteria4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria4.setName("Kriteria4"); // NOI18N
        FormInput.add(Kriteria4);
        Kriteria4.setBounds(330, 2970, 80, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Apabila salah satu jawaban YA dari kriteria diatas, maka akan dilanjutkan dengan perencanaan sebagai berikut :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(40, 3020, 570, 23);

        pilihan1.setBackground(new java.awt.Color(255, 255, 255));
        pilihan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan1.setForeground(new java.awt.Color(50, 50, 50));
        pilihan1.setText("Perawatan diri (Mandi, BAB, BAK)");
        pilihan1.setName("pilihan1"); // NOI18N
        FormInput.add(pilihan1);
        pilihan1.setBounds(50, 3050, 200, 19);

        pilihan2.setBackground(new java.awt.Color(255, 255, 255));
        pilihan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan2.setForeground(new java.awt.Color(50, 50, 50));
        pilihan2.setText("Pemantauan pemberian obat");
        pilihan2.setName("pilihan2"); // NOI18N
        FormInput.add(pilihan2);
        pilihan2.setBounds(50, 3070, 180, 19);

        pilihan3.setBackground(new java.awt.Color(255, 255, 255));
        pilihan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan3.setForeground(new java.awt.Color(50, 50, 50));
        pilihan3.setText("Pemantauan diet");
        pilihan3.setName("pilihan3"); // NOI18N
        FormInput.add(pilihan3);
        pilihan3.setBounds(50, 3090, 120, 19);

        pilihan4.setBackground(new java.awt.Color(255, 255, 255));
        pilihan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan4.setForeground(new java.awt.Color(50, 50, 50));
        pilihan4.setText("Bantuan medis / perawatan di rumah (Homecare)");
        pilihan4.setName("pilihan4"); // NOI18N
        FormInput.add(pilihan4);
        pilihan4.setBounds(50, 3110, 280, 19);

        pilihan5.setBackground(new java.awt.Color(255, 255, 255));
        pilihan5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan5.setForeground(new java.awt.Color(50, 50, 50));
        pilihan5.setText("Perawatan luka");
        pilihan5.setName("pilihan5"); // NOI18N
        FormInput.add(pilihan5);
        pilihan5.setBounds(330, 3050, 120, 19);

        pilihan6.setBackground(new java.awt.Color(255, 255, 255));
        pilihan6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan6.setForeground(new java.awt.Color(50, 50, 50));
        pilihan6.setText("Latihan fisik lanjutan");
        pilihan6.setName("pilihan6"); // NOI18N
        FormInput.add(pilihan6);
        pilihan6.setBounds(330, 3070, 130, 19);

        pilihan7.setBackground(new java.awt.Color(255, 255, 255));
        pilihan7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan7.setForeground(new java.awt.Color(50, 50, 50));
        pilihan7.setText("Pendampingan tenaga khusus di rumah");
        pilihan7.setName("pilihan7"); // NOI18N
        FormInput.add(pilihan7);
        pilihan7.setBounds(330, 3090, 220, 19);

        pilihan8.setBackground(new java.awt.Color(255, 255, 255));
        pilihan8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan8.setForeground(new java.awt.Color(50, 50, 50));
        pilihan8.setText("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)");
        pilihan8.setName("pilihan8"); // NOI18N
        FormInput.add(pilihan8);
        pilihan8.setBounds(330, 3110, 370, 19);

        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel289.setText("XII. SKRINING PERENCANAAN PEMULANGAN");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(10, 2840, 380, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 3130, 880, 1);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Skala Humpty Dumpty :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(30, 2350, 120, 23);

        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel240.setText("1. Usia");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(50, 2370, 300, 23);

        jLabel241.setText("Skala :");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(310, 2370, 80, 23);

        SkalaHumptyDumpty1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< 3 Tahun", "3-7 Tahun", "7-13 Tahun", ">13 Tahun" }));
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
        SkalaHumptyDumpty1.setBounds(390, 2370, 350, 23);

        jLabel242.setText("Nilai :");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(700, 2370, 75, 23);

        NilaiHumptyDumpty1.setEditable(false);
        NilaiHumptyDumpty1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty1.setText("4");
        NilaiHumptyDumpty1.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty1.setName("NilaiHumptyDumpty1"); // NOI18N
        FormInput.add(NilaiHumptyDumpty1);
        NilaiHumptyDumpty1.setBounds(780, 2370, 60, 23);

        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel243.setText("2. Jenis Kelamin");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(50, 2400, 300, 23);

        jLabel244.setText("Skala :");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(310, 2400, 80, 23);

        SkalaHumptyDumpty2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
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
        SkalaHumptyDumpty2.setBounds(390, 2400, 350, 23);

        jLabel246.setText("Nilai :");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(700, 2400, 75, 23);

        NilaiHumptyDumpty2.setEditable(false);
        NilaiHumptyDumpty2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty2.setText("2");
        NilaiHumptyDumpty2.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty2.setName("NilaiHumptyDumpty2"); // NOI18N
        FormInput.add(NilaiHumptyDumpty2);
        NilaiHumptyDumpty2.setBounds(780, 2400, 60, 23);

        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel247.setText("3. Diagnosis");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(50, 2430, 300, 23);

        jLabel248.setText("Skala :");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(310, 2430, 80, 23);

        SkalaHumptyDumpty3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diagnosis neurologi", "Perubahan oksigenasi", "Gangguan prilaku", "Diagnosis lainnya" }));
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
        SkalaHumptyDumpty3.setBounds(390, 2430, 350, 23);

        jLabel249.setText("Nilai :");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(700, 2430, 75, 23);

        NilaiHumptyDumpty3.setEditable(false);
        NilaiHumptyDumpty3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty3.setText("4");
        NilaiHumptyDumpty3.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty3.setName("NilaiHumptyDumpty3"); // NOI18N
        FormInput.add(NilaiHumptyDumpty3);
        NilaiHumptyDumpty3.setBounds(780, 2430, 60, 23);

        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel250.setText("4. Gangguan Kognitif");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(50, 2460, 300, 23);

        jLabel251.setText("Skala :");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(310, 2460, 80, 23);

        SkalaHumptyDumpty4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak menyadari keterbatasan diri", "Lupa akan adanya keterbatasan", "Orientasi baik" }));
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
        SkalaHumptyDumpty4.setBounds(390, 2460, 350, 23);

        jLabel252.setText("Nilai :");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(700, 2460, 75, 23);

        NilaiHumptyDumpty4.setEditable(false);
        NilaiHumptyDumpty4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty4.setText("3");
        NilaiHumptyDumpty4.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty4.setName("NilaiHumptyDumpty4"); // NOI18N
        FormInput.add(NilaiHumptyDumpty4);
        NilaiHumptyDumpty4.setBounds(780, 2460, 60, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("5. Faktor Lingkungan");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(50, 2490, 300, 23);

        jLabel254.setText("Skala :");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(310, 2490, 80, 23);

        SkalaHumptyDumpty5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Riwayat jatuh/ditempat tidur dewasa", "Pasien/bayi menggunakan alat bantu/tempat tidur bayi", "Pasien/bayi diletakan di tempat tidur standart", "Area luar RS" }));
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
        SkalaHumptyDumpty5.setBounds(390, 2490, 350, 23);

        jLabel255.setText("Nilai :");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(700, 2490, 75, 23);

        NilaiHumptyDumpty5.setEditable(false);
        NilaiHumptyDumpty5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty5.setText("4");
        NilaiHumptyDumpty5.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty5.setName("NilaiHumptyDumpty5"); // NOI18N
        FormInput.add(NilaiHumptyDumpty5);
        NilaiHumptyDumpty5.setBounds(780, 2490, 60, 23);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("6. Respon Terhadap Pembedahan Sedasi / Anastesi");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(50, 2520, 300, 23);

        jLabel257.setText("Skala :");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(310, 2520, 80, 23);

        SkalaHumptyDumpty6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dalam 24 jam", "Dalam 48 jam", "1>48 jam/tidak menjalani pembedahan/sedasi/anestesi" }));
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
        SkalaHumptyDumpty6.setBounds(390, 2520, 350, 23);

        jLabel258.setText("Nilai :");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(700, 2520, 75, 23);

        NilaiHumptyDumpty6.setEditable(false);
        NilaiHumptyDumpty6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty6.setText("3");
        NilaiHumptyDumpty6.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty6.setName("NilaiHumptyDumpty6"); // NOI18N
        FormInput.add(NilaiHumptyDumpty6);
        NilaiHumptyDumpty6.setBounds(780, 2520, 60, 23);

        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel259.setText("7. Penggunaan Medikamentosa");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(50, 2550, 300, 23);

        jLabel260.setText("Skala :");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(310, 2550, 80, 23);

        SkalaHumptyDumpty7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Penggunaan multiple obat", "Penggunaan salah satu obat", "Tidak ada medikasi" }));
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
        SkalaHumptyDumpty7.setBounds(390, 2550, 350, 23);

        jLabel261.setText("Nilai :");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(700, 2550, 75, 23);

        NilaiHumptyDumpty7.setEditable(false);
        NilaiHumptyDumpty7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty7.setText("3");
        NilaiHumptyDumpty7.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty7.setName("NilaiHumptyDumpty7"); // NOI18N
        FormInput.add(NilaiHumptyDumpty7);
        NilaiHumptyDumpty7.setBounds(780, 2550, 60, 23);

        TingkatHumptyDumpty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatHumptyDumpty.setText("Tingkat Resiko : Risiko Tinggi >12, Tindakan : Intervensi pencegahan risiko jatuh standar");
        TingkatHumptyDumpty.setName("TingkatHumptyDumpty"); // NOI18N
        FormInput.add(TingkatHumptyDumpty);
        TingkatHumptyDumpty.setBounds(50, 2600, 650, 23);

        jLabel270.setText("Total :");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(700, 2600, 75, 23);

        NilaiHumptyDumptyTotal.setEditable(false);
        NilaiHumptyDumptyTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumptyTotal.setText("23");
        NilaiHumptyDumptyTotal.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumptyTotal.setName("NilaiHumptyDumptyTotal"); // NOI18N
        FormInput.add(NilaiHumptyDumptyTotal);
        NilaiHumptyDumptyTotal.setBounds(780, 2600, 60, 23);

        SkalaWajah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersenyum/tidak ada ekspresi khusus", "Terkadang meringis/menarik diri", "Sering menggetarkan dagu dan mengatupkan rahang" }));
        SkalaWajah.setName("SkalaWajah"); // NOI18N
        SkalaWajah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaWajahItemStateChanged(evt);
            }
        });
        SkalaWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaWajahKeyPressed(evt);
            }
        });
        FormInput.add(SkalaWajah);
        SkalaWajah.setBounds(100, 2050, 310, 23);

        jLabel290.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel290.setText("Skala FLACCS :");
        jLabel290.setName("jLabel290"); // NOI18N
        FormInput.add(jLabel290);
        jLabel290.setBounds(30, 2030, 210, 23);

        jLabel291.setText("Wajah :");
        jLabel291.setName("jLabel291"); // NOI18N
        FormInput.add(jLabel291);
        jLabel291.setBounds(30, 2050, 60, 23);

        jLabel292.setText("Kaki :");
        jLabel292.setName("jLabel292"); // NOI18N
        FormInput.add(jLabel292);
        jLabel292.setBounds(30, 2080, 60, 23);

        jLabel293.setText("Aktifitas :");
        jLabel293.setName("jLabel293"); // NOI18N
        FormInput.add(jLabel293);
        jLabel293.setBounds(30, 2110, 60, 23);

        jLabel294.setText("Menangis :");
        jLabel294.setName("jLabel294"); // NOI18N
        FormInput.add(jLabel294);
        jLabel294.setBounds(470, 2050, 60, 23);

        jLabel295.setText("Bersuara :");
        jLabel295.setName("jLabel295"); // NOI18N
        FormInput.add(jLabel295);
        jLabel295.setBounds(470, 2080, 60, 23);

        NilaiWajah.setEditable(false);
        NilaiWajah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiWajah.setText("0");
        NilaiWajah.setFocusTraversalPolicyProvider(true);
        NilaiWajah.setName("NilaiWajah"); // NOI18N
        NilaiWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiWajahKeyPressed(evt);
            }
        });
        FormInput.add(NilaiWajah);
        NilaiWajah.setBounds(410, 2050, 40, 23);

        SkalaKaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gerakan normal/relaksasi", "Tidak tenang/tegang", "Kaki dibuat menendang/menarik" }));
        SkalaKaki.setName("SkalaKaki"); // NOI18N
        SkalaKaki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKakiItemStateChanged(evt);
            }
        });
        SkalaKaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKakiKeyPressed(evt);
            }
        });
        FormInput.add(SkalaKaki);
        SkalaKaki.setBounds(100, 2080, 310, 23);

        NilaiKaki.setEditable(false);
        NilaiKaki.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiKaki.setText("0");
        NilaiKaki.setFocusTraversalPolicyProvider(true);
        NilaiKaki.setName("NilaiKaki"); // NOI18N
        NilaiKaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiKakiKeyPressed(evt);
            }
        });
        FormInput.add(NilaiKaki);
        NilaiKaki.setBounds(410, 2080, 40, 23);

        SkalaAktifitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidur posisi normal, mudah bergerak", "Gerakan menggeliat/berguling, kaku", "Melengkungkan punggung/kaku menghentak" }));
        SkalaAktifitas.setName("SkalaAktifitas"); // NOI18N
        SkalaAktifitas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaAktifitasItemStateChanged(evt);
            }
        });
        SkalaAktifitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaAktifitasKeyPressed(evt);
            }
        });
        FormInput.add(SkalaAktifitas);
        SkalaAktifitas.setBounds(100, 2110, 310, 23);

        NilaiAktifitas.setEditable(false);
        NilaiAktifitas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiAktifitas.setText("0");
        NilaiAktifitas.setFocusTraversalPolicyProvider(true);
        NilaiAktifitas.setName("NilaiAktifitas"); // NOI18N
        NilaiAktifitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiAktifitasKeyPressed(evt);
            }
        });
        FormInput.add(NilaiAktifitas);
        NilaiAktifitas.setBounds(410, 2110, 40, 23);

        SkalaMenangis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak menangis (mudah bergerak)", "Mengerang/merengek", "Menangis terus menerus, terisak, menjerit" }));
        SkalaMenangis.setName("SkalaMenangis"); // NOI18N
        SkalaMenangis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaMenangisItemStateChanged(evt);
            }
        });
        SkalaMenangis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaMenangisKeyPressed(evt);
            }
        });
        FormInput.add(SkalaMenangis);
        SkalaMenangis.setBounds(530, 2050, 266, 23);

        NilaiMenangis.setEditable(false);
        NilaiMenangis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiMenangis.setText("0");
        NilaiMenangis.setFocusTraversalPolicyProvider(true);
        NilaiMenangis.setName("NilaiMenangis"); // NOI18N
        NilaiMenangis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiMenangisKeyPressed(evt);
            }
        });
        FormInput.add(NilaiMenangis);
        NilaiMenangis.setBounds(800, 2050, 40, 23);

        SkalaBersuara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersuara normal/tenang", "Tenang bila dipeluk, digendong/diajak bicara", "Sulit untuk menenangkan" }));
        SkalaBersuara.setName("SkalaBersuara"); // NOI18N
        SkalaBersuara.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaBersuaraItemStateChanged(evt);
            }
        });
        SkalaBersuara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaBersuaraKeyPressed(evt);
            }
        });
        FormInput.add(SkalaBersuara);
        SkalaBersuara.setBounds(530, 2080, 266, 23);

        NilaiBersuara.setEditable(false);
        NilaiBersuara.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiBersuara.setText("0");
        NilaiBersuara.setFocusTraversalPolicyProvider(true);
        NilaiBersuara.setName("NilaiBersuara"); // NOI18N
        NilaiBersuara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiBersuaraKeyPressed(evt);
            }
        });
        FormInput.add(NilaiBersuara);
        NilaiBersuara.setBounds(800, 2080, 40, 23);

        jLabel296.setText("Skala nyeri :");
        jLabel296.setName("jLabel296"); // NOI18N
        FormInput.add(jLabel296);
        jLabel296.setBounds(670, 2110, 90, 23);

        SkalaNyeri.setEditable(false);
        SkalaNyeri.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        SkalaNyeri.setText("0");
        SkalaNyeri.setFocusTraversalPolicyProvider(true);
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(760, 2110, 80, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("III. RIWAYAT TUMBUH KEMBANG DAN PERINATAL CARE");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(10, 1140, 350, 23);

        jLabel57.setText("Anak ke :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(150, 1160, 55, 23);

        Anakke.setFocusTraversalPolicyProvider(true);
        Anakke.setName("Anakke"); // NOI18N
        Anakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnakkeKeyPressed(evt);
            }
        });
        FormInput.add(Anakke);
        Anakke.setBounds(210, 1160, 40, 23);

        DariSaudara.setFocusTraversalPolicyProvider(true);
        DariSaudara.setName("DariSaudara"); // NOI18N
        DariSaudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DariSaudaraKeyPressed(evt);
            }
        });
        FormInput.add(DariSaudara);
        DariSaudara.setBounds(280, 1160, 40, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("dari");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(250, 1160, 24, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("saudara");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(320, 1160, 50, 23);

        jLabel67.setText("Cara Kelahiran :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(400, 1160, 110, 23);

        CaraKelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Sectio Caesaria", "Lain-Lain" }));
        CaraKelahiran.setName("CaraKelahiran"); // NOI18N
        CaraKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(CaraKelahiran);
        CaraKelahiran.setBounds(520, 1160, 127, 23);

        KetCaraKelahiran.setFocusTraversalPolicyProvider(true);
        KetCaraKelahiran.setName("KetCaraKelahiran"); // NOI18N
        KetCaraKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetCaraKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(KetCaraKelahiran);
        KetCaraKelahiran.setBounds(650, 1160, 209, 23);

        KelainanBawaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        KelainanBawaan.setName("KelainanBawaan"); // NOI18N
        KelainanBawaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelainanBawaanKeyPressed(evt);
            }
        });
        FormInput.add(KelainanBawaan);
        KelainanBawaan.setBounds(520, 1190, 100, 23);

        jLabel68.setText("Kelainan Bawaan :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(400, 1190, 110, 23);

        KetKelainanBawaan.setFocusTraversalPolicyProvider(true);
        KetKelainanBawaan.setName("KetKelainanBawaan"); // NOI18N
        KetKelainanBawaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKelainanBawaanKeyPressed(evt);
            }
        });
        FormInput.add(KetKelainanBawaan);
        KetKelainanBawaan.setBounds(620, 1190, 236, 23);

        UmurKelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cukup Bulan", "Kurang Bulan" }));
        UmurKelahiran.setName("UmurKelahiran"); // NOI18N
        UmurKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(UmurKelahiran);
        UmurKelahiran.setBounds(150, 1190, 140, 23);

        jLabel70.setText("Riwayat Kelahiran :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1160, 146, 23);

        jLabel71.setText("Umur Kelahiran :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1190, 146, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 1220, 880, 1);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("IV. RIWAYAT IMUNISASI");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(10, 1220, 350, 23);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbImunisasi.setName("tbImunisasi"); // NOI18N
        Scroll10.setViewportView(tbImunisasi);

        FormInput.add(Scroll10);
        Scroll10.setBounds(100, 1240, 760, 93);

        BtnTambahImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahImunisasi.setMnemonic('3');
        BtnTambahImunisasi.setToolTipText("Alt+3");
        BtnTambahImunisasi.setName("BtnTambahImunisasi"); // NOI18N
        BtnTambahImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahImunisasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahImunisasi);
        BtnTambahImunisasi.setBounds(70, 1240, 28, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 1340, 880, 1);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("V. RIWAYAT TUMBUH KEMBANG ANAK");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 1340, 350, 23);

        jLabel72.setText("a. Tengkurap, usia :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1360, 133, 23);

        UsiaTengkurap.setFocusTraversalPolicyProvider(true);
        UsiaTengkurap.setName("UsiaTengkurap"); // NOI18N
        UsiaTengkurap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaTengkurapKeyPressed(evt);
            }
        });
        FormInput.add(UsiaTengkurap);
        UsiaTengkurap.setBounds(140, 1360, 90, 23);

        jLabel73.setText("b. Duduk, usia :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(250, 1360, 90, 23);

        UsiaDuduk.setFocusTraversalPolicyProvider(true);
        UsiaDuduk.setName("UsiaDuduk"); // NOI18N
        UsiaDuduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaDudukKeyPressed(evt);
            }
        });
        FormInput.add(UsiaDuduk);
        UsiaDuduk.setBounds(340, 1360, 90, 23);

        jLabel74.setText("c. Berdiri, usia :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(450, 1360, 90, 23);

        UsiaBerdiri.setFocusTraversalPolicyProvider(true);
        UsiaBerdiri.setName("UsiaBerdiri"); // NOI18N
        UsiaBerdiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBerdiriKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBerdiri);
        UsiaBerdiri.setBounds(540, 1360, 90, 23);

        jLabel75.setText("d. Gigi pertama, usia :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(630, 1360, 130, 23);

        UsiaGigi.setFocusTraversalPolicyProvider(true);
        UsiaGigi.setName("UsiaGigi"); // NOI18N
        UsiaGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaGigiKeyPressed(evt);
            }
        });
        FormInput.add(UsiaGigi);
        UsiaGigi.setBounds(770, 1360, 90, 23);

        jLabel76.setText("e. Berjalan, usia :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 1390, 122, 23);

        UsiaBerjalan.setFocusTraversalPolicyProvider(true);
        UsiaBerjalan.setName("UsiaBerjalan"); // NOI18N
        UsiaBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBerjalan);
        UsiaBerjalan.setBounds(130, 1390, 90, 23);

        jLabel77.setText("f. Bicara Usia, usia :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(310, 1390, 110, 23);

        UsiaBicara.setFocusTraversalPolicyProvider(true);
        UsiaBicara.setName("UsiaBicara"); // NOI18N
        UsiaBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBicaraKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBicara);
        UsiaBicara.setBounds(420, 1390, 90, 23);

        jLabel78.setText("g. Mulai bisa membaca, usia :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(580, 1390, 180, 23);

        UsiaMembaca.setFocusTraversalPolicyProvider(true);
        UsiaMembaca.setName("UsiaMembaca"); // NOI18N
        UsiaMembaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaMembacaKeyPressed(evt);
            }
        });
        FormInput.add(UsiaMembaca);
        UsiaMembaca.setBounds(770, 1390, 90, 23);

        UsiaMenulis.setFocusTraversalPolicyProvider(true);
        UsiaMenulis.setName("UsiaMenulis"); // NOI18N
        UsiaMenulis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaMenulisKeyPressed(evt);
            }
        });
        FormInput.add(UsiaMenulis);
        UsiaMenulis.setBounds(180, 1420, 90, 23);

        jLabel79.setText("h. Mulai bisa menulis, usia :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1420, 172, 23);

        GangguanEmosi.setFocusTraversalPolicyProvider(true);
        GangguanEmosi.setName("GangguanEmosi"); // NOI18N
        GangguanEmosi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanEmosiKeyPressed(evt);
            }
        });
        FormInput.add(GangguanEmosi);
        GangguanEmosi.setBounds(620, 1420, 240, 23);

        jLabel80.setText("Gangguan perkembangan mental / emosi, bila ada, jelaskan :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(290, 1420, 320, 23);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 1450, 880, 1);

        BtnPanggilHapusImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnPanggilHapusImunisasi.setMnemonic('3');
        BtnPanggilHapusImunisasi.setToolTipText("Alt+3");
        BtnPanggilHapusImunisasi.setName("BtnPanggilHapusImunisasi"); // NOI18N
        BtnPanggilHapusImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPanggilHapusImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPanggilHapusImunisasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPanggilHapusImunisasi);
        BtnPanggilHapusImunisasi.setBounds(70, 1270, 28, 23);

        SG1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG1.setName("SG1"); // NOI18N
        SG1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG1ItemStateChanged(evt);
            }
        });
        SG1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG1KeyPressed(evt);
            }
        });
        FormInput.add(SG1);
        SG1.setBounds(700, 2650, 80, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1.  Apakah pasien tampak kurus");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(40, 2650, 610, 23);

        jLabel218.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel218.setText("Apakah terdapat salah satu dari kondisi tersebut? Diare > 5 kali/hari dan/muntah > 3 kali/hari dalam seminggu terakhir;");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(50, 2720, 610, 23);

        SG2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG2.setName("SG2"); // NOI18N
        SG2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG2ItemStateChanged(evt);
            }
        });
        SG2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG2KeyPressed(evt);
            }
        });
        FormInput.add(SG2);
        SG2.setBounds(700, 2690, 80, 23);

        jLabel219.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel219.setText("bila ada atau untuk bayi < 1 tahun ; berat badan tidak naik selama 3 bulan terakhir)");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(50, 2690, 600, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2.");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(40, 2680, 20, 37);

        jLabel221.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel221.setText("Asupan makanan berkurang selama 1 minggu terakhir");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(50, 2740, 610, 23);

        SG3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG3.setName("SG3"); // NOI18N
        SG3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG3ItemStateChanged(evt);
            }
        });
        SG3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG3KeyPressed(evt);
            }
        });
        FormInput.add(SG3);
        SG3.setBounds(700, 2730, 80, 23);

        jLabel222.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel222.setText("Apakah terdapat penurunan berat badan selama satu bulan terakhir? (berdasarkan penilaian objektif data berat badan");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(50, 2680, 600, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3.");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(40, 2720, 20, 37);

        jLabel224.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel224.setText("4.  Apakah terdapat penyakit atau keadaan yang menyebabkan pasien beresiko mengalami malnutrisi?");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(40, 2770, 610, 23);

        jLabel225.setText("Total Skor :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(680, 2800, 90, 23);

        NilaiGizi1.setEditable(false);
        NilaiGizi1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi1.setText("0");
        NilaiGizi1.setFocusTraversalPolicyProvider(true);
        NilaiGizi1.setName("NilaiGizi1"); // NOI18N
        NilaiGizi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi1KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi1);
        NilaiGizi1.setBounds(790, 2650, 60, 23);

        SG4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG4.setName("SG4"); // NOI18N
        SG4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG4ItemStateChanged(evt);
            }
        });
        SG4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG4KeyPressed(evt);
            }
        });
        FormInput.add(SG4);
        SG4.setBounds(700, 2770, 80, 23);

        NilaiGizi2.setEditable(false);
        NilaiGizi2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi2.setText("0");
        NilaiGizi2.setFocusTraversalPolicyProvider(true);
        NilaiGizi2.setName("NilaiGizi2"); // NOI18N
        NilaiGizi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi2KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi2);
        NilaiGizi2.setBounds(790, 2690, 60, 23);

        NilaiGizi3.setEditable(false);
        NilaiGizi3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi3.setText("0");
        NilaiGizi3.setFocusTraversalPolicyProvider(true);
        NilaiGizi3.setName("NilaiGizi3"); // NOI18N
        NilaiGizi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi3KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi3);
        NilaiGizi3.setBounds(790, 2730, 60, 23);

        NilaiGizi4.setEditable(false);
        NilaiGizi4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi4.setText("0");
        NilaiGizi4.setFocusTraversalPolicyProvider(true);
        NilaiGizi4.setName("NilaiGizi4"); // NOI18N
        NilaiGizi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi4KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi4);
        NilaiGizi4.setBounds(790, 2770, 60, 23);

        TotalNilaiGizi.setEditable(false);
        TotalNilaiGizi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalNilaiGizi.setText("0");
        TotalNilaiGizi.setFocusTraversalPolicyProvider(true);
        TotalNilaiGizi.setName("TotalNilaiGizi"); // NOI18N
        TotalNilaiGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalNilaiGiziKeyPressed(evt);
            }
        });
        FormInput.add(TotalNilaiGizi);
        TotalNilaiGizi.setBounds(770, 2800, 80, 23);

        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel297.setText("n. wajib :");
        jLabel297.setName("jLabel297"); // NOI18N
        FormInput.add(jLabel297);
        jLabel297.setBounds(50, 1930, 50, 23);

        Wajib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baligh", "Non baligh" }));
        Wajib.setName("Wajib"); // NOI18N
        Wajib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WajibKeyPressed(evt);
            }
        });
        FormInput.add(Wajib);
        Wajib.setBounds(110, 1930, 100, 23);

        KetHalanganLain.setFocusTraversalPolicyProvider(true);
        KetHalanganLain.setName("KetHalanganLain"); // NOI18N
        KetHalanganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetHalanganLainKeyPressed(evt);
            }
        });
        FormInput.add(KetHalanganLain);
        KetHalanganLain.setBounds(310, 1930, 210, 23);

        jLabel82.setText("p. toharah :");
        jLabel82.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(520, 1930, 60, 23);

        Toharah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tayamum", "Wudhu", "Lain-lain" }));
        Toharah.setName("Toharah"); // NOI18N
        Toharah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToharahKeyPressed(evt);
            }
        });
        FormInput.add(Toharah);
        Toharah.setBounds(590, 1930, 100, 23);

        jLabel298.setText("o. halangan lain :");
        jLabel298.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel298.setName("jLabel298"); // NOI18N
        FormInput.add(jLabel298);
        jLabel298.setBounds(210, 1930, 90, 23);

        jLabel83.setText("q. sholat :");
        jLabel83.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(700, 1930, 50, 23);

        Sholat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdiri", "Duduk", "Berbaring" }));
        Sholat.setName("Sholat"); // NOI18N
        Sholat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SholatKeyPressed(evt);
            }
        });
        FormInput.add(Sholat);
        Sholat.setBounds(750, 1930, 100, 23);

        jLabel299.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel299.setText("r. motivasi kesembuhan dan ibadah :");
        jLabel299.setName("jLabel299"); // NOI18N
        jLabel299.setPreferredSize(new java.awt.Dimension(180, 14));
        FormInput.add(jLabel299);
        jLabel299.setBounds(50, 1960, 190, 23);

        MotivasiKesembuhanIbadah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        MotivasiKesembuhanIbadah.setName("MotivasiKesembuhanIbadah"); // NOI18N
        MotivasiKesembuhanIbadah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotivasiKesembuhanIbadahKeyPressed(evt);
            }
        });
        FormInput.add(MotivasiKesembuhanIbadah);
        MotivasiKesembuhanIbadah.setBounds(240, 1960, 60, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
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
        FormMasalahRencana.setLayout(new java.awt.GridLayout(4, 0, 1, 1));

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Riwayat Imunisasi :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        tbImunisasi2.setName("tbImunisasi2"); // NOI18N
        scrollPane9.setViewportView(tbImunisasi2);

        FormMasalahRencana.add(scrollPane9);

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
            if (Sequel.menyimpantf("penilaian_awal_keperawatan_ranap_anak",
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                    "No.Rawat", 216, new String[]{
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
                        getText(), SistemSarafKepala.getSelectedItem().
                                toString(), KetSistemSarafKepala.getText(),
                        SistemSarafWajah.getSelectedItem().toString(),
                        KetSistemSarafWajah.getText(), SistemSarafLeher.
                        getSelectedItem().toString(), SistemSarafKejang.
                                getSelectedItem().toString(),
                        KetSistemSarafKejang.getText(), SistemSarafSensorik.
                        getSelectedItem().toString(), KardiovaskularDenyutNadi.
                                getSelectedItem().toString(),
                        KardiovaskularSirkulasi.getSelectedItem().toString(),
                        KetKardiovaskularSirkulasi.getText(),
                        KardiovaskularPulsasi.getSelectedItem().toString(),
                        RespirasiPolaNafas.getSelectedItem().toString(),
                        RespirasiRetraksi.getSelectedItem().toString(),
                        RespirasiSuaraNafas.getSelectedItem().toString(),
                        RespirasiVolume.getSelectedItem().toString(),
                        RespirasiJenisPernafasan.getSelectedItem().toString(),
                        KetRespirasiJenisPernafasan.getText(), RespirasiIrama.
                        getSelectedItem().toString(), RespirasiBatuk.
                                getSelectedItem().toString(),
                        GastrointestinalMulut.getSelectedItem().toString(),
                        KetGastrointestinalMulut.getText(),
                        GastrointestinalGigi.getSelectedItem().toString(),
                        KetGastrointestinalGigi.getText(),
                        GastrointestinalLidah.getSelectedItem().toString(),
                        KetGastrointestinalLidah.getText(),
                        GastrointestinalTenggorakan.getSelectedItem().toString(),
                        KetGastrointestinalTenggorakan.getText(),
                        GastrointestinalAbdomen.getSelectedItem().toString(),
                        KetGastrointestinalAbdomen.getText(),
                        GastrointestinalUsus.getSelectedItem().toString(),
                        GastrointestinalAnus.getSelectedItem().toString(),
                        NeurologiPenglihatan.getSelectedItem().toString(),
                        KetNeurologiPenglihatan.getText(),
                        NeurologiAlatBantuPenglihatan.getSelectedItem().
                                toString(), NeurologiPendengaran.
                                getSelectedItem().toString(), NeurologiBicara.
                                getSelectedItem().toString(),
                        KetNeurologiBicara.getText(), NeurologiSensorik.
                        getSelectedItem().toString(), NeurologiMotorik.
                                getSelectedItem().toString(),
                        NeurologiOtot.getSelectedItem().toString(),
                        IntegumentWarnaKulit.getSelectedItem().toString(),
                        IntegumentTurgor.getSelectedItem().toString(),
                        IntegumentKulit.getSelectedItem().toString(),
                        IntegumentDecubitus.getSelectedItem().toString(),
                        MuskuloskletalPegerakanSendi.getSelectedItem().
                                toString(),
                        MuskuloskletalKekuatanOtot.getSelectedItem().toString(),
                        MuskuloskletalNyeriSendi.getSelectedItem().toString(),
                        KetMuskuloskletalNyeriSendi.getText(),
                        MuskuloskletalOedema.getSelectedItem().toString(),
                        KetMuskuloskletalOedema.getText(),
                        MuskuloskletalFraktur.getSelectedItem().toString(),
                        KetMuskuloskletalFraktur.getText(), BAB.getText(), XBAB.
                        getText(), KBAB.getText(), WBAB.getText(), BAK.getText(),
                        XBAK.getText(), WBAK.getText(), LBAK.getText(), Anakke.
                        getText(), DariSaudara.getText(),
                        CaraKelahiran.getSelectedItem().toString(),
                        KetCaraKelahiran.getText(), UmurKelahiran.
                        getSelectedItem().toString(), KelainanBawaan.
                                getSelectedItem().toString(), KetKelainanBawaan.
                                getText(), UsiaTengkurap.getText(),
                        UsiaDuduk.getText(), UsiaBerdiri.getText(), UsiaGigi.
                        getText(), UsiaBerjalan.getText(), UsiaBicara.getText(),
                        UsiaMembaca.getText(), UsiaMenulis.getText(),
                        GangguanEmosi.getText(), PolaAktifitasMakan.
                        getSelectedItem().toString(), PolaAktifitasMandi.
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
                        SkalaWajah.getSelectedItem().toString(), NilaiWajah.
                        getText(), SkalaKaki.getSelectedItem().toString(),
                        NilaiKaki.getText(), SkalaAktifitas.getSelectedItem().
                        toString(), NilaiAktifitas.getText(), SkalaMenangis.
                        getSelectedItem().toString(),
                        NilaiMenangis.getText(),
                        SkalaBersuara.getSelectedItem().toString(),
                        NilaiBersuara.getText(), SkalaNyeri.getText(), Nyeri.
                        getSelectedItem().toString(),
                        Provokes.getSelectedItem().toString(), KetProvokes.
                        getText(),
                        Quality.getSelectedItem().toString(), KetQuality.
                        getText(), Lokasi.getText(), Menyebar.getSelectedItem().
                        toString(), Durasi.getText(), NyeriHilang.
                        getSelectedItem().toString(), KetNyeri.getText(),
                        PadaDokter.getSelectedItem().toString(),
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
                        SG1.getSelectedItem().toString(),
                        NilaiGizi1.getText(), SG2.getSelectedItem().toString(),
                        NilaiGizi2.getText(), SG3.getSelectedItem().toString(),
                        NilaiGizi3.getText(), SG4.getSelectedItem().toString(),
                        NilaiGizi4.getText(), TotalNilaiGizi.getText(),
                        Kriteria1.getSelectedItem().toString(), Kriteria2.
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
                                "penilaian_awal_keperawatan_ranap_masalah_anak",
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
                                "penilaian_awal_keperawatan_ranap_rencana_anak",
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
                        "select penilaian_awal_keperawatan_ranap_anak.no_rawat,penilaian_awal_keperawatan_ranap_anak.tanggal,penilaian_awal_keperawatan_ranap_anak.informasi,penilaian_awal_keperawatan_ranap_anak.ket_informasi,penilaian_awal_keperawatan_ranap_anak.tiba_diruang_rawat,"
                        + "penilaian_awal_keperawatan_ranap_anak.kasus_trauma,penilaian_awal_keperawatan_ranap_anak.cara_masuk,penilaian_awal_keperawatan_ranap_anak.rps,penilaian_awal_keperawatan_ranap_anak.rpd,penilaian_awal_keperawatan_ranap_anak.rpk,penilaian_awal_keperawatan_ranap_anak.rpo,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_anak.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_anak.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_anak.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_anak.riwayat_alergi,penilaian_awal_keperawatan_ranap_anak.riwayat_merokok,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_narkoba,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_olahraga,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gcs,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_td,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_suhu,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_pola_nafas,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_volume_pernafasan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_irama_nafas,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_peistatik_usus,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_motorik,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_turgor,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                        + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_lainlain,"
                        + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpakaian,"
                        + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_porsi_makan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_anak.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_aktifitas,"
                        + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ambulasi,"
                        + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah,"
                        + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kesimpulan,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan_keterangan,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_kualitas,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_skala,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_diberitahukan_dokter,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai1,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala3,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai4,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala6,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala1,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai2,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala4,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai5,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala7,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai8,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala10,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai11,"
                        + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_anak.skrining_gizi1,penilaian_awal_keperawatan_ranap_anak.nilai_gizi1,penilaian_awal_keperawatan_ranap_anak.skrining_gizi2,"
                        + "penilaian_awal_keperawatan_ranap_anak.nilai_gizi2,penilaian_awal_keperawatan_ranap_anak.nilai_total_gizi,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_ket_diagnosa_khusus,"
                        + "penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.rencana,penilaian_awal_keperawatan_ranap_anak.nip1,"
                        + "penilaian_awal_keperawatan_ranap_anak.nip2,penilaian_awal_keperawatan_ranap_anak.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                        + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                        + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join penilaian_awal_keperawatan_ranap_anak on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_anak.no_rawat "
                        + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_anak.nip1=pengkaji1.nip "
                        + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_anak.nip2=pengkaji2.nip "
                        + "inner join dokter on penilaian_awal_keperawatan_ranap_anak.kd_dokter=dokter.kd_dokter "
                        + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                        + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                        + "penilaian_awal_keperawatan_ranap_anak.tanggal between ? and ? "
                        + (TCari.getText().trim().isEmpty() ? "" : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_ranap_anak.nip1 like ? or "
                        + "pengkaji1.nama like ? or penilaian_awal_keperawatan_ranap_anak.kd_dokter like ? or dokter.nm_dokter like ?)")
                        + " order by penilaian_awal_keperawatan_ranap_anak.tanggal");

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
                                htmlContent.append("<tr class='isi'>"
                                        + " <td valign='top'>" + rs.getString(
                                                "no_rawat") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "no_rkm_medis") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nm_pasien") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "tgl_lahir") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "jk") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nip1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkaji1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nip2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkaji2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "kd_dokter") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nm_dokter") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "tanggal") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "kasus_trauma") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "informasi") + ", " + rs.
                                                getString("ket_informasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "tiba_diruang_rawat") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "cara_masuk") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "rps") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "rpd") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "rpk") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "rpo") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_pembedahan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_dirawat_dirs") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "alat_bantu_dipakai") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_kehamilan") + ", " + rs.
                                                getString(
                                                        "riwayat_kehamilan_perkiraan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_tranfusi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_alergi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_merokok") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_merokok_jumlah") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_alkohol") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_alkohol_jumlah") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_narkoba") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_olahraga") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_mental") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_keadaan_umum") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gcs") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_td") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_nadi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_rr") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_suhu") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_spo2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_bb") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_tb") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_susunan_kepala") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kepala_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_susunan_wajah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_susunan_leher") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_susunan_kejang") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kejang_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_susunan_sensorik") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_kardiovaskuler_pulsasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_kardiovaskuler_sirkulasi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_kardiovaskuler_denyut_nadi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_retraksi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_pola_nafas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_suara_nafas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_batuk") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_volume_pernafasan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_jenis_pernafasan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_respirasi_irama_nafas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_mulut") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_mulut_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_lidah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_lidah_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_gigi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_gigi_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_tenggorokan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_abdomen") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_abdomen_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_peistatik_usus") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_gastrointestinal_anus") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_sensorik") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_pengelihatan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pengelihatan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_alat_bantu_penglihatan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_motorik") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_pendengaran") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_bicara") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_bicara_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_neurologi_kekuatan_otot") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_integument_kulit") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_integument_warnakulit") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_integument_turgor") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_integument_dekubitas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_muskuloskletal_oedema") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_oedema_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_muskuloskletal_pergerakan_sendi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_muskuloskletal_kekauatan_otot") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_muskuloskletal_fraktur") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_fraktur_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_muskuloskletal_nyeri_sendi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_jumlah") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bab_frekuensi_durasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bab_konsistensi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bab_warna") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_jumlah") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bak_frekuensi_durasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bak_warna") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pemeriksaan_eliminasi_bak_lainlain") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_aktifitas_mandi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_aktifitas_makanminum") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_aktifitas_berpakaian") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_aktifitas_eliminasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_aktifitas_berpindah") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_nutrisi_porsi_makan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_nutrisi_frekuesi_makan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_nutrisi_jenis_makanan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_tidur_lama_tidur") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pola_tidur_gangguan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kemampuan_sehari") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_berjalan") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_aktifitas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ambulasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_atas") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_bawah") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_menggenggam") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_menggenggam_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_koordinasi") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kesimpulan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_kondisi_psiko") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_perilaku") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_perilaku_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_gangguan_jiwa") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_hubungan_keluarga") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "agama") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_tinggal") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_tinggal_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pekerjaan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "png_jawab") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_nilai_kepercayaan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_nilai_kepercayaan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nama_bahasa") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "pnd") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_pendidikan_pj") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "riwayat_psiko_edukasi_diberikan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_edukasi_diberikan_keterangan") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_penyebab") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_ket_penyebab") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_kualitas") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_ket_kualitas") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_lokasi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_menyebar") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_skala") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_waktu") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_hilang") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_ket_hilang") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_jam_diberitahukan_dokter") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala3") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai3") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala4") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai4") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala5") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai5") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_skala6") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_nilai6") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhmorse_totalnilai") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala3") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai3") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala4") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai4") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala5") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai5") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala6") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai6") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala7") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai7") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala8") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai8") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala9") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai9") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala10") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai10") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_skala11") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_nilai11") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "penilaian_jatuhsydney_totalnilai") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nilai_gizi1") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nilai_gizi2") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "nilai_total_gizi") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi_diagnosa_khusus") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi_ket_diagnosa_khusus") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi_diketahui_dietisen") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "skrining_gizi_jam_diketahui_dietisen") + "</td>"
                                        + " <td valign='top'>" + rs.getString(
                                                "rencana") + "</td>"
                                        + "</tr>");
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
                                        "<tr class='isi'><td valign='top'>" + rs.
                                                getString("no_rawat") + "</td><td valign='top'>" + rs.
                                        getString("no_rkm_medis") + "</td><td valign='top'>" + rs.
                                        getString("nm_pasien") + "</td><td valign='top'>" + rs.
                                        getString("tgl_lahir") + "</td><td valign='top'>" + rs.
                                        getString("jk") + "</td><td valign='top'>" + rs.
                                        getString("nip1") + "</td><td valign='top'>" + rs.
                                        getString("pengkaji1") + "</td><td valign='top'>" + rs.
                                        getString("nip2") + "</td><td valign='top'>" + rs.
                                        getString("pengkaji2") + "</td><td valign='top'>" + rs.
                                        getString("kd_dokter") + "</td><td valign='top'>" + rs.
                                        getString("nm_dokter") + "</td><td valign='top'>" + rs.
                                        getString("tanggal") + "</td><td valign='top'>" + rs.
                                        getString("kasus_trauma") + "</td><td valign='top'>" + rs.
                                        getString("informasi") + ", " + rs.
                                        getString("ket_informasi") + "</td><td valign='top'>" + rs.
                                        getString("tiba_diruang_rawat") + "</td><td valign='top'>" + rs.
                                        getString("cara_masuk") + "</td><td valign='top'>" + rs.
                                        getString("rps") + "</td><td valign='top'>" + rs.
                                        getString("rpd") + "</td><td valign='top'>" + rs.
                                        getString("rpk") + "</td><td valign='top'>" + rs.
                                        getString("rpo") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_pembedahan") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_dirawat_dirs") + "</td><td valign='top'>" + rs.
                                        getString("alat_bantu_dipakai") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_kehamilan") + ", " + rs.
                                        getString("riwayat_kehamilan_perkiraan") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_tranfusi") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_alergi") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_merokok") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_merokok_jumlah") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_alkohol") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_alkohol_jumlah") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_narkoba") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_olahraga") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_mental") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_keadaan_umum") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_gcs") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_td") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_nadi") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_rr") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_suhu") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_spo2") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_bb") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_tb") + "</td><td valign='top'>" + rs.
                                        getString("pemeriksaan_susunan_kepala") + ", " + rs.
                                        getString(
                                                "pemeriksaan_susunan_kepala_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_leher") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kejang") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kejang_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_sensorik") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_pulsasi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_sirkulasi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_denyut_nadi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_retraksi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_pola_nafas") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_suara_nafas") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_batuk") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_volume_pernafasan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_jenis_pernafasan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_irama_nafas") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_mulut") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_mulut_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_lidah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_lidah_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_gigi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_gigi_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_tenggorokan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_abdomen") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_abdomen_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_peistatik_usus") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_anus") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_sensorik") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pengelihatan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pengelihatan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_alat_bantu_penglihatan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_motorik") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pendengaran") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_bicara") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_bicara_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_kekuatan_otot") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_integument_kulit") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_integument_warnakulit") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_integument_turgor") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_integument_dekubitas") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_oedema") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_oedema_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_pergerakan_sendi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_kekauatan_otot") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_fraktur") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_fraktur_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_nyeri_sendi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_frekuensi_jumlah") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_frekuensi_durasi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_konsistensi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_warna") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_frekuensi_jumlah") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_frekuensi_durasi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_warna") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_lainlain") + "</td><td valign='top'>" + rs.
                                                getString("pola_aktifitas_mandi") + "</td><td valign='top'>" + rs.
                                        getString("pola_aktifitas_makanminum") + "</td><td valign='top'>" + rs.
                                        getString("pola_aktifitas_berpakaian") + "</td><td valign='top'>" + rs.
                                        getString("pola_aktifitas_eliminasi") + "</td><td valign='top'>" + rs.
                                        getString("pola_aktifitas_berpindah") + "</td><td valign='top'>" + rs.
                                        getString("pola_nutrisi_porsi_makan") + "</td><td valign='top'>" + rs.
                                        getString("pola_nutrisi_frekuesi_makan") + "</td><td valign='top'>" + rs.
                                        getString("pola_nutrisi_jenis_makanan") + "</td><td valign='top'>" + rs.
                                        getString("pola_tidur_lama_tidur") + "</td><td valign='top'>" + rs.
                                        getString("pola_tidur_gangguan") + "</td><td valign='top'>" + rs.
                                        getString(
                                                "pengkajian_fungsi_kemampuan_sehari") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_aktifitas") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ambulasi") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_menggenggam") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_menggenggam_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "pengkajian_fungsi_kesimpulan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "riwayat_psiko_kondisi_psiko") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "riwayat_psiko_perilaku") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_perilaku_keterangan") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "riwayat_psiko_gangguan_jiwa") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "riwayat_psiko_hubungan_keluarga") + "</td><td valign='top'>" + rs.
                                                getString("agama") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_psiko_tinggal") + ", " + rs.
                                        getString(
                                                "riwayat_psiko_tinggal_keterangan") + "</td><td valign='top'>" + rs.
                                                getString("pekerjaan") + "</td><td valign='top'>" + rs.
                                        getString("png_jawab") + "</td><td valign='top'>" + rs.
                                        getString(
                                                "riwayat_psiko_nilai_kepercayaan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_nilai_kepercayaan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString("nama_bahasa") + "</td><td valign='top'>" + rs.
                                        getString("pnd") + "</td><td valign='top'>" + rs.
                                        getString("riwayat_psiko_pendidikan_pj") + "</td><td valign='top'>" + rs.
                                        getString(
                                                "riwayat_psiko_edukasi_diberikan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_edukasi_diberikan_keterangan") + "</td><td valign='top'>" + rs.
                                                getString("penilaian_nyeri") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_penyebab") + ", " + rs.
                                        getString("penilaian_nyeri_ket_penyebab") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_kualitas") + ", " + rs.
                                        getString("penilaian_nyeri_ket_kualitas") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_lokasi") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_menyebar") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_skala") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_waktu") + "</td><td valign='top'>" + rs.
                                        getString("penilaian_nyeri_hilang") + ", " + rs.
                                        getString("penilaian_nyeri_ket_hilang") + "</td><td valign='top'>" + rs.
                                        getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_jam_diberitahukan_dokter") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala1") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai1") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala2") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai2") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala3") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai3") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala4") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai4") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala5") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai5") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala6") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai6") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_totalnilai") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala1") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai1") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala2") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai2") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala3") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai3") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala4") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai4") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala5") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai5") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala6") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai6") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala7") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai7") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala8") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai8") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala9") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai9") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala10") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai10") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala11") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai11") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_totalnilai") + "</td><td valign='top'>" + rs.
                                                getString("skrining_gizi1") + "</td><td valign='top'>" + rs.
                                        getString("nilai_gizi1") + "</td><td valign='top'>" + rs.
                                        getString("skrining_gizi2") + "</td><td valign='top'>" + rs.
                                        getString("nilai_gizi2") + "</td><td valign='top'>" + rs.
                                        getString("nilai_total_gizi") + "</td><td valign='top'>" + rs.
                                        getString(
                                                "skrining_gizi_diagnosa_khusus") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "skrining_gizi_ket_diagnosa_khusus") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "skrining_gizi_diketahui_dietisen") + "</td><td valign='top'>" + rs.
                                                getString(
                                                        "skrining_gizi_jam_diketahui_dietisen") + "</td><td valign='top'>" + rs.
                                                getString("rencana") + "</td></tr>");
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
                                htmlContent.append("\"" + rs.getString(
                                        "no_rawat") + "\";\"" + rs.getString(
                                                "no_rkm_medis") + "\";\"" + rs.
                                                getString("nm_pasien") + "\";\"" + rs.
                                        getString("tgl_lahir") + "\";\"" + rs.
                                        getString("jk") + "\";\"" + rs.
                                        getString("nip1") + "\";\"" + rs.
                                        getString("pengkaji1") + "\";\"" + rs.
                                        getString("nip2") + "\";\"" + rs.
                                        getString("pengkaji2") + "\";\"" + rs.
                                        getString("kd_dokter") + "\";\"" + rs.
                                        getString("nm_dokter") + "\";\"" + rs.
                                        getString("tanggal") + "\";\"" + rs.
                                        getString("kasus_trauma") + "\";\"" + rs.
                                        getString("informasi") + ", " + rs.
                                        getString("ket_informasi") + "\";\"" + rs.
                                        getString("tiba_diruang_rawat") + "\";\"" + rs.
                                        getString("cara_masuk") + "\";\"" + rs.
                                        getString("rps") + "\";\"" + rs.
                                        getString("rpd") + "\";\"" + rs.
                                        getString("rpk") + "\";\"" + rs.
                                        getString("rpo") + "\";\"" + rs.
                                        getString("riwayat_pembedahan") + "\";\"" + rs.
                                        getString("riwayat_dirawat_dirs") + "\";\"" + rs.
                                        getString("alat_bantu_dipakai") + "\";\"" + rs.
                                        getString("riwayat_kehamilan") + ", " + rs.
                                        getString("riwayat_kehamilan_perkiraan") + "\";\"" + rs.
                                        getString("riwayat_tranfusi") + "\";\"" + rs.
                                        getString("riwayat_alergi") + "\";\"" + rs.
                                        getString("riwayat_merokok") + "\";\"" + rs.
                                        getString("riwayat_merokok_jumlah") + "\";\"" + rs.
                                        getString("riwayat_alkohol") + "\";\"" + rs.
                                        getString("riwayat_alkohol_jumlah") + "\";\"" + rs.
                                        getString("riwayat_narkoba") + "\";\"" + rs.
                                        getString("riwayat_olahraga") + "\";\"" + rs.
                                        getString("pemeriksaan_mental") + "\";\"" + rs.
                                        getString("pemeriksaan_keadaan_umum") + "\";\"" + rs.
                                        getString("pemeriksaan_gcs") + "\";\"" + rs.
                                        getString("pemeriksaan_td") + "\";\"" + rs.
                                        getString("pemeriksaan_nadi") + "\";\"" + rs.
                                        getString("pemeriksaan_rr") + "\";\"" + rs.
                                        getString("pemeriksaan_suhu") + "\";\"" + rs.
                                        getString("pemeriksaan_spo2") + "\";\"" + rs.
                                        getString("pemeriksaan_bb") + "\";\"" + rs.
                                        getString("pemeriksaan_tb") + "\";\"" + rs.
                                        getString("pemeriksaan_susunan_kepala") + ", " + rs.
                                        getString(
                                                "pemeriksaan_susunan_kepala_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_wajah_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_leher") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kejang") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_susunan_kejang_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_susunan_sensorik") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_pulsasi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_sirkulasi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_sirkulasi_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kardiovaskuler_denyut_nadi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_retraksi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_pola_nafas") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_suara_nafas") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_batuk") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_volume_pernafasan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_jenis_pernafasan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_jenis_pernafasan_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_respirasi_irama_nafas") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_mulut") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_mulut_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_lidah") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_lidah_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_gigi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_gigi_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_tenggorokan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_tenggorokan_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_abdomen") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_abdomen_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_peistatik_usus") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_gastrointestinal_anus") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_sensorik") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pengelihatan") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pengelihatan_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_alat_bantu_penglihatan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_motorik") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_pendengaran") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_bicara") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_bicara_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_neurologi_kekuatan_otot") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_integument_kulit") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_integument_warnakulit") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_integument_turgor") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_integument_dekubitas") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_oedema") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_oedema_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_pergerakan_sendi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_kekauatan_otot") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_fraktur") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_fraktur_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_nyeri_sendi") + ", " + rs.
                                                getString(
                                                        "pemeriksaan_muskuloskletal_nyeri_sendi_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_frekuensi_jumlah") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_frekuensi_durasi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_konsistensi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bab_warna") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_frekuensi_jumlah") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_frekuensi_durasi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_warna") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_eliminasi_bak_lainlain") + "\";\"" + rs.
                                                getString("pola_aktifitas_mandi") + "\";\"" + rs.
                                        getString("pola_aktifitas_makanminum") + "\";\"" + rs.
                                        getString("pola_aktifitas_berpakaian") + "\";\"" + rs.
                                        getString("pola_aktifitas_eliminasi") + "\";\"" + rs.
                                        getString("pola_aktifitas_berpindah") + "\";\"" + rs.
                                        getString("pola_nutrisi_porsi_makan") + "\";\"" + rs.
                                        getString("pola_nutrisi_frekuesi_makan") + "\";\"" + rs.
                                        getString("pola_nutrisi_jenis_makanan") + "\";\"" + rs.
                                        getString("pola_tidur_lama_tidur") + "\";\"" + rs.
                                        getString("pola_tidur_gangguan") + "\";\"" + rs.
                                        getString(
                                                "pengkajian_fungsi_kemampuan_sehari") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_aktifitas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ambulasi") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_menggenggam") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_menggenggam_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi") + ", " + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_kesimpulan") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_kondisi_psiko") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_perilaku") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_perilaku_keterangan") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_gangguan_jiwa") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_hubungan_keluarga") + "\";\"" + rs.
                                                getString("agama") + "\";\"" + rs.
                                        getString("riwayat_psiko_tinggal") + ", " + rs.
                                        getString(
                                                "riwayat_psiko_tinggal_keterangan") + "\";\"" + rs.
                                                getString("pekerjaan") + "\";\"" + rs.
                                        getString("png_jawab") + "\";\"" + rs.
                                        getString(
                                                "riwayat_psiko_nilai_kepercayaan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_nilai_kepercayaan_keterangan") + "\";\"" + rs.
                                                getString("nama_bahasa") + "\";\"" + rs.
                                        getString("pnd") + "\";\"" + rs.
                                        getString("riwayat_psiko_pendidikan_pj") + "\";\"" + rs.
                                        getString(
                                                "riwayat_psiko_edukasi_diberikan") + ", " + rs.
                                                getString(
                                                        "riwayat_psiko_edukasi_diberikan_keterangan") + "\";\"" + rs.
                                                getString("penilaian_nyeri") + "\";\"" + rs.
                                        getString("penilaian_nyeri_penyebab") + ", " + rs.
                                        getString("penilaian_nyeri_ket_penyebab") + "\";\"" + rs.
                                        getString("penilaian_nyeri_kualitas") + ", " + rs.
                                        getString("penilaian_nyeri_ket_kualitas") + "\";\"" + rs.
                                        getString("penilaian_nyeri_lokasi") + "\";\"" + rs.
                                        getString("penilaian_nyeri_menyebar") + "\";\"" + rs.
                                        getString("penilaian_nyeri_skala") + "\";\"" + rs.
                                        getString("penilaian_nyeri_waktu") + "\";\"" + rs.
                                        getString("penilaian_nyeri_hilang") + ", " + rs.
                                        getString("penilaian_nyeri_ket_hilang") + "\";\"" + rs.
                                        getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + ", " + rs.
                                                getString(
                                                        "penilaian_nyeri_jam_diberitahukan_dokter") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_skala6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_nilai6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhmorse_totalnilai") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala7") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai7") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala8") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai8") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala9") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai9") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala10") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai10") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_skala11") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_nilai11") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuhsydney_totalnilai") + "\";\"" + rs.
                                                getString("skrining_gizi1") + "\";\"" + rs.
                                        getString("nilai_gizi1") + "\";\"" + rs.
                                        getString("skrining_gizi2") + "\";\"" + rs.
                                        getString("nilai_gizi2") + "\";\"" + rs.
                                        getString("nilai_total_gizi") + "\";\"" + rs.
                                        getString(
                                                "skrining_gizi_diagnosa_khusus") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_ket_diagnosa_khusus") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_diketahui_dietisen") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_jam_diketahui_dietisen") + "\";\"" + rs.
                                                getString("rencana") + "\"\n");
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
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_anak on penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_anak.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah");
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
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_anak on penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_anak.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana");
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
                    "select penilaian_awal_keperawatan_ranap_anak.no_rawat,penilaian_awal_keperawatan_ranap_anak.tanggal,penilaian_awal_keperawatan_ranap_anak.informasi,penilaian_awal_keperawatan_ranap_anak.ket_informasi,penilaian_awal_keperawatan_ranap_anak.tiba_diruang_rawat,"
                    + "penilaian_awal_keperawatan_ranap_anak.kasus_trauma,penilaian_awal_keperawatan_ranap_anak.cara_masuk,penilaian_awal_keperawatan_ranap_anak.rps,penilaian_awal_keperawatan_ranap_anak.rpd,penilaian_awal_keperawatan_ranap_anak.rpk,penilaian_awal_keperawatan_ranap_anak.rpo,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_anak.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_anak.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_anak.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_anak.riwayat_alergi,penilaian_awal_keperawatan_ranap_anak.riwayat_merokok,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_narkoba,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_olahraga,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gcs,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_td,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_suhu,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_pola_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_volume_pernafasan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_irama_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_peistatik_usus,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_motorik,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_turgor,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_lainlain,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpakaian,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_porsi_makan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_anak.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_aktifitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ambulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kesimpulan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_kualitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_skala,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai1,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala3,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai4,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala6,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala1,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai2,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala4,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai5,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala7,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai8,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala10,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai11,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_anak.skrining_gizi1,penilaian_awal_keperawatan_ranap_anak.nilai_gizi1,penilaian_awal_keperawatan_ranap_anak.skrining_gizi2,"
                    + "penilaian_awal_keperawatan_ranap_anak.nilai_gizi2,penilaian_awal_keperawatan_ranap_anak.nilai_total_gizi,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_ket_diagnosa_khusus,"
                    + "penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.rencana,penilaian_awal_keperawatan_ranap_anak.nip1,"
                    + "penilaian_awal_keperawatan_ranap_anak.nip2,penilaian_awal_keperawatan_ranap_anak.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_anak on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_anak.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_anak.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_anak.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_anak.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);

            Valid.MyReportqry("rptCetakPenilaianAwalKeperawatanRanap2.jasper",
                    "report",
                    "::[ Laporan Penilaian Awal Keperawatan Rawat Inap ]::",
                    "select penilaian_awal_keperawatan_ranap_anak.no_rawat,penilaian_awal_keperawatan_ranap_anak.tanggal,penilaian_awal_keperawatan_ranap_anak.informasi,penilaian_awal_keperawatan_ranap_anak.ket_informasi,penilaian_awal_keperawatan_ranap_anak.tiba_diruang_rawat,"
                    + "penilaian_awal_keperawatan_ranap_anak.kasus_trauma,penilaian_awal_keperawatan_ranap_anak.cara_masuk,penilaian_awal_keperawatan_ranap_anak.rps,penilaian_awal_keperawatan_ranap_anak.rpd,penilaian_awal_keperawatan_ranap_anak.rpk,penilaian_awal_keperawatan_ranap_anak.rpo,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_anak.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_anak.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_anak.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_anak.riwayat_alergi,penilaian_awal_keperawatan_ranap_anak.riwayat_merokok,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_narkoba,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_olahraga,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gcs,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_td,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_suhu,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_pola_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_volume_pernafasan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_irama_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_peistatik_usus,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_motorik,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_turgor,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_lainlain,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpakaian,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_porsi_makan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_anak.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_aktifitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ambulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kesimpulan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_kualitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_skala,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai1,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala3,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai4,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_skala6,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhmorse_totalnilai,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala1,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai1,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala2,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai2,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai3,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala4,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai4,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala5,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai5,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai6,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala7,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai7,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala8,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai8,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai9,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala10,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai10,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_skala11,penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_nilai11,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_jatuhsydney_totalnilai,penilaian_awal_keperawatan_ranap_anak.skrining_gizi1,penilaian_awal_keperawatan_ranap_anak.nilai_gizi1,penilaian_awal_keperawatan_ranap_anak.skrining_gizi2,"
                    + "penilaian_awal_keperawatan_ranap_anak.nilai_gizi2,penilaian_awal_keperawatan_ranap_anak.nilai_total_gizi,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_ket_diagnosa_khusus,"
                    + "penilaian_awal_keperawatan_ranap_anak.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_ranap_anak.rencana,penilaian_awal_keperawatan_ranap_anak.nip1,"
                    + "penilaian_awal_keperawatan_ranap_anak.nip2,penilaian_awal_keperawatan_ranap_anak.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_anak on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_anak.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_anak.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_anak.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_anak.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt, BtnDPJP, MacamKasus);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt, MacamKasus, KetAnamnesis);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        Valid.pindah(evt, BtnSimpan, BtnPetugas2);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        i = 1;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed

    }//GEN-LAST:event_KdPetugasKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, BtnPetugas);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2ActionPerformed
        i = 2;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void BtnPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugas2KeyPressed
        Valid.pindah(evt, BtnPetugas, BtnDPJP);
    }//GEN-LAST:event_BtnPetugas2KeyPressed

    private void KdPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugas2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugas2KeyPressed

    private void KdDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdDPJPKeyPressed

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void BtnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDPJPKeyPressed
        Valid.pindah(evt, BtnPetugas2, MacamKasus);
    }//GEN-LAST:event_BtnDPJPKeyPressed

    private void TibadiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TibadiRuangKeyPressed
        Valid.pindah(evt, KetAnamnesis, CaraMasuk);
    }//GEN-LAST:event_TibadiRuangKeyPressed

    private void CaraMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraMasukKeyPressed
        Valid.pindah(evt, TibadiRuang, RPS);
    }//GEN-LAST:event_CaraMasukKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt, RTranfusi, KebiasaanMerokok);
    }//GEN-LAST:event_AlergiKeyPressed

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        Valid.pindah2(evt, CaraMasuk, RPD);
    }//GEN-LAST:event_RPSKeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        Valid.pindah2(evt, RPD, RPO);
    }//GEN-LAST:event_RPKKeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        Valid.pindah2(evt, RPS, RPK);
    }//GEN-LAST:event_RPDKeyPressed

    private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPOKeyPressed
        Valid.pindah2(evt, RPK, RPembedahan);
    }//GEN-LAST:event_RPOKeyPressed

    private void DetailRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetailRencanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetailRencanaKeyPressed

    private void MacamKasusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MacamKasusKeyPressed
        Valid.pindah(evt, BtnDPJP, Anamnesis);
    }//GEN-LAST:event_MacamKasusKeyPressed

    private void KetAnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetAnamnesisKeyPressed
        Valid.pindah(evt, Anamnesis, TibadiRuang);
    }//GEN-LAST:event_KetAnamnesisKeyPressed

    private void RDirawatRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RDirawatRSKeyPressed
        Valid.pindah(evt, RPembedahan, AlatBantuDipakai);
    }//GEN-LAST:event_RDirawatRSKeyPressed

    private void RPembedahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPembedahanKeyPressed
        Valid.pindah(evt, RPO, RDirawatRS);
    }//GEN-LAST:event_RPembedahanKeyPressed

    private void AlatBantuDipakaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatBantuDipakaiKeyPressed
        Valid.pindah(evt, RDirawatRS, SedangMenyusui);
    }//GEN-LAST:event_AlatBantuDipakaiKeyPressed

    private void SedangMenyusuiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SedangMenyusuiKeyPressed
        Valid.pindah(evt, AlatBantuDipakai, KetSedangMenyusui);
    }//GEN-LAST:event_SedangMenyusuiKeyPressed

    private void KetSedangMenyusuiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSedangMenyusuiKeyPressed
        Valid.pindah(evt, SedangMenyusui, RTranfusi);
    }//GEN-LAST:event_KetSedangMenyusuiKeyPressed

    private void RTranfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RTranfusiKeyPressed
        Valid.pindah(evt, KetSedangMenyusui, Alergi);
    }//GEN-LAST:event_RTranfusiKeyPressed

    private void KebiasaanMerokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanMerokokKeyPressed
        Valid.pindah(evt, Alergi, KebiasaanJumlahRokok);
    }//GEN-LAST:event_KebiasaanMerokokKeyPressed

    private void KebiasaanJumlahRokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanJumlahRokokKeyPressed
        Valid.pindah(evt, KebiasaanMerokok, KebiasaanAlkohol);
    }//GEN-LAST:event_KebiasaanJumlahRokokKeyPressed

    private void KebiasaanAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanJumlahRokok, KebiasaanJumlahAlkohol);
    }//GEN-LAST:event_KebiasaanAlkoholKeyPressed

    private void KebiasaanJumlahAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanJumlahAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanAlkohol, KebiasaanNarkoba);
    }//GEN-LAST:event_KebiasaanJumlahAlkoholKeyPressed

    private void KebiasaanNarkobaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanNarkobaKeyPressed
        Valid.pindah(evt, KebiasaanJumlahAlkohol, OlahRaga);
    }//GEN-LAST:event_KebiasaanNarkobaKeyPressed

    private void OlahRagaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OlahRagaKeyPressed
        Valid.pindah(evt, KebiasaanNarkoba, KesadaranMental);
    }//GEN-LAST:event_OlahRagaKeyPressed

    private void KesadaranMentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranMentalKeyPressed
        Valid.pindah(evt, OlahRaga, KeadaanMentalUmum);
    }//GEN-LAST:event_KesadaranMentalKeyPressed

    private void KeadaanMentalUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanMentalUmumKeyPressed
        Valid.pindah(evt, KesadaranMental, GCS);
    }//GEN-LAST:event_KeadaanMentalUmumKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt, KeadaanMentalUmum, TD);
    }//GEN-LAST:event_GCSKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt, GCS, Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt, TD, RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt, Nadi, Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, RR, SpO2);
    }//GEN-LAST:event_SuhuKeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        Valid.pindah(evt, Suhu, BB);
    }//GEN-LAST:event_SpO2KeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt, SpO2, TB);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt, BB, SistemSarafKepala);
    }//GEN-LAST:event_TBKeyPressed

    private void SistemSarafKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafKepalaKeyPressed
        Valid.pindah(evt, TB, KetSistemSarafKepala);
    }//GEN-LAST:event_SistemSarafKepalaKeyPressed

    private void KetSistemSarafKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafKepalaKeyPressed
        Valid.pindah(evt, SistemSarafKepala, SistemSarafWajah);
    }//GEN-LAST:event_KetSistemSarafKepalaKeyPressed

    private void SistemSarafWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafWajahKeyPressed
        Valid.pindah(evt, KetSistemSarafKepala, KetSistemSarafWajah);
    }//GEN-LAST:event_SistemSarafWajahKeyPressed

    private void KetSistemSarafWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafWajahKeyPressed
        Valid.pindah(evt, SistemSarafWajah, SistemSarafLeher);
    }//GEN-LAST:event_KetSistemSarafWajahKeyPressed

    private void SistemSarafLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafLeherKeyPressed
        Valid.pindah(evt, KetSistemSarafWajah, SistemSarafKejang);
    }//GEN-LAST:event_SistemSarafLeherKeyPressed

    private void SistemSarafSensorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafSensorikKeyPressed
        Valid.pindah(evt, KetSistemSarafKejang, KardiovaskularPulsasi);
    }//GEN-LAST:event_SistemSarafSensorikKeyPressed

    private void SistemSarafKejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistemSarafKejangKeyPressed
        Valid.pindah(evt, SistemSarafLeher, KetSistemSarafKejang);
    }//GEN-LAST:event_SistemSarafKejangKeyPressed

    private void KetSistemSarafKejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSistemSarafKejangKeyPressed
        Valid.pindah(evt, SistemSarafKejang, SistemSarafSensorik);
    }//GEN-LAST:event_KetSistemSarafKejangKeyPressed

    private void KardiovaskularPulsasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularPulsasiKeyPressed
        Valid.pindah(evt, SistemSarafSensorik, KardiovaskularSirkulasi);
    }//GEN-LAST:event_KardiovaskularPulsasiKeyPressed

    private void KardiovaskularSirkulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularSirkulasiKeyPressed
        Valid.pindah(evt, KardiovaskularPulsasi, KetKardiovaskularSirkulasi);
    }//GEN-LAST:event_KardiovaskularSirkulasiKeyPressed

    private void KetKardiovaskularSirkulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKardiovaskularSirkulasiKeyPressed
        Valid.pindah(evt, KardiovaskularSirkulasi, KardiovaskularDenyutNadi);
    }//GEN-LAST:event_KetKardiovaskularSirkulasiKeyPressed

    private void KardiovaskularDenyutNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KardiovaskularDenyutNadiKeyPressed
        Valid.pindah(evt, KetKardiovaskularSirkulasi, RespirasiRetraksi);
    }//GEN-LAST:event_KardiovaskularDenyutNadiKeyPressed

    private void RespirasiRetraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiRetraksiKeyPressed
        Valid.pindah(evt, KardiovaskularDenyutNadi, RespirasiPolaNafas);
    }//GEN-LAST:event_RespirasiRetraksiKeyPressed

    private void RespirasiPolaNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiPolaNafasKeyPressed
        Valid.pindah(evt, RespirasiRetraksi, RespirasiSuaraNafas);
    }//GEN-LAST:event_RespirasiPolaNafasKeyPressed

    private void RespirasiSuaraNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiSuaraNafasKeyPressed
        Valid.pindah(evt, RespirasiPolaNafas, RespirasiBatuk);
    }//GEN-LAST:event_RespirasiSuaraNafasKeyPressed

    private void RespirasiIramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiIramaKeyPressed
        Valid.pindah(evt, KetRespirasiJenisPernafasan, GastrointestinalMulut);
    }//GEN-LAST:event_RespirasiIramaKeyPressed

    private void RespirasiVolumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiVolumeKeyPressed
        Valid.pindah(evt, RespirasiBatuk, RespirasiJenisPernafasan);
    }//GEN-LAST:event_RespirasiVolumeKeyPressed

    private void RespirasiJenisPernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiJenisPernafasanKeyPressed
        Valid.pindah(evt, RespirasiVolume, KetRespirasiJenisPernafasan);
    }//GEN-LAST:event_RespirasiJenisPernafasanKeyPressed

    private void KetRespirasiJenisPernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetRespirasiJenisPernafasanKeyPressed
        Valid.pindah(evt, RespirasiJenisPernafasan, RespirasiIrama);
    }//GEN-LAST:event_KetRespirasiJenisPernafasanKeyPressed

    private void RespirasiBatukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespirasiBatukKeyPressed
        Valid.pindah(evt, RespirasiSuaraNafas, RespirasiVolume);
    }//GEN-LAST:event_RespirasiBatukKeyPressed

    private void GastrointestinalMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalMulutKeyPressed
        Valid.pindah(evt, RespirasiIrama, KetGastrointestinalMulut);
    }//GEN-LAST:event_GastrointestinalMulutKeyPressed

    private void KetGastrointestinalGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalGigiKeyPressed
        Valid.pindah(evt, GastrointestinalGigi, GastrointestinalTenggorakan);
    }//GEN-LAST:event_KetGastrointestinalGigiKeyPressed

    private void GastrointestinalAnusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalAnusKeyPressed
        Valid.pindah(evt, GastrointestinalUsus, NeurologiSensorik);
    }//GEN-LAST:event_GastrointestinalAnusKeyPressed

    private void GastrointestinalGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalGigiKeyPressed
        Valid.pindah(evt, KetGastrointestinalLidah, KetGastrointestinalGigi);
    }//GEN-LAST:event_GastrointestinalGigiKeyPressed

    private void KetGastrointestinalMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalMulutKeyPressed
        Valid.pindah(evt, GastrointestinalMulut, GastrointestinalLidah);
    }//GEN-LAST:event_KetGastrointestinalMulutKeyPressed

    private void GastrointestinalLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalLidahKeyPressed
        Valid.pindah(evt, KetGastrointestinalMulut, KetGastrointestinalLidah);
    }//GEN-LAST:event_GastrointestinalLidahKeyPressed

    private void KetGastrointestinalLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalLidahKeyPressed
        Valid.pindah(evt, GastrointestinalLidah, GastrointestinalGigi);
    }//GEN-LAST:event_KetGastrointestinalLidahKeyPressed

    private void GastrointestinalTenggorakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalTenggorakanKeyPressed
        Valid.pindah(evt, KetGastrointestinalGigi,
                KetGastrointestinalTenggorakan);
    }//GEN-LAST:event_GastrointestinalTenggorakanKeyPressed

    private void KetGastrointestinalTenggorakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalTenggorakanKeyPressed
        Valid.pindah(evt, GastrointestinalTenggorakan, GastrointestinalAbdomen);
    }//GEN-LAST:event_KetGastrointestinalTenggorakanKeyPressed

    private void GastrointestinalAbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalAbdomenKeyPressed
        Valid.pindah(evt, KetGastrointestinalTenggorakan,
                KetGastrointestinalAbdomen);
    }//GEN-LAST:event_GastrointestinalAbdomenKeyPressed

    private void KetGastrointestinalAbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetGastrointestinalAbdomenKeyPressed
        Valid.pindah(evt, GastrointestinalAbdomen, GastrointestinalUsus);
    }//GEN-LAST:event_KetGastrointestinalAbdomenKeyPressed

    private void GastrointestinalUsusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GastrointestinalUsusKeyPressed
        Valid.pindah(evt, KetGastrointestinalGigi, GastrointestinalAnus);
    }//GEN-LAST:event_GastrointestinalUsusKeyPressed

    private void NeurologiSensorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiSensorikKeyPressed
        Valid.pindah(evt, GastrointestinalAnus, NeurologiPenglihatan);
    }//GEN-LAST:event_NeurologiSensorikKeyPressed

    private void NeurologiMotorikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiMotorikKeyPressed
        Valid.pindah(evt, NeurologiAlatBantuPenglihatan, NeurologiPendengaran);
    }//GEN-LAST:event_NeurologiMotorikKeyPressed

    private void NeurologiOtotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiOtotKeyPressed
        Valid.pindah(evt, KetNeurologiBicara, IntegumentKulit);
    }//GEN-LAST:event_NeurologiOtotKeyPressed

    private void NeurologiPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiPenglihatanKeyPressed
        Valid.pindah(evt, NeurologiSensorik, KetNeurologiPenglihatan);
    }//GEN-LAST:event_NeurologiPenglihatanKeyPressed

    private void KetNeurologiPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNeurologiPenglihatanKeyPressed
        Valid.pindah(evt, NeurologiPenglihatan, NeurologiAlatBantuPenglihatan);
    }//GEN-LAST:event_KetNeurologiPenglihatanKeyPressed

    private void NeurologiAlatBantuPenglihatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiAlatBantuPenglihatanKeyPressed
        Valid.pindah(evt, KetNeurologiPenglihatan, NeurologiMotorik);
    }//GEN-LAST:event_NeurologiAlatBantuPenglihatanKeyPressed

    private void NeurologiPendengaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiPendengaranKeyPressed
        Valid.pindah(evt, NeurologiMotorik, NeurologiBicara);
    }//GEN-LAST:event_NeurologiPendengaranKeyPressed

    private void NeurologiBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NeurologiBicaraKeyPressed
        Valid.pindah(evt, NeurologiPendengaran, KetNeurologiBicara);
    }//GEN-LAST:event_NeurologiBicaraKeyPressed

    private void KetNeurologiBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNeurologiBicaraKeyPressed
        Valid.pindah(evt, NeurologiBicara, NeurologiOtot);
    }//GEN-LAST:event_KetNeurologiBicaraKeyPressed

    private void IntegumentKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentKulitKeyPressed
        Valid.pindah(evt, NeurologiOtot, IntegumentWarnaKulit);
    }//GEN-LAST:event_IntegumentKulitKeyPressed

    private void IntegumentWarnaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentWarnaKulitKeyPressed
        Valid.pindah(evt, IntegumentKulit, IntegumentTurgor);
    }//GEN-LAST:event_IntegumentWarnaKulitKeyPressed

    private void IntegumentTurgorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentTurgorKeyPressed
        Valid.pindah(evt, IntegumentWarnaKulit, IntegumentDecubitus);
    }//GEN-LAST:event_IntegumentTurgorKeyPressed

    private void IntegumentDecubitusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntegumentDecubitusKeyPressed
        Valid.pindah(evt, IntegumentTurgor, MuskuloskletalOedema);
    }//GEN-LAST:event_IntegumentDecubitusKeyPressed

    private void MuskuloskletalOedemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalOedemaKeyPressed
        Valid.pindah(evt, IntegumentDecubitus, KetMuskuloskletalOedema);
    }//GEN-LAST:event_MuskuloskletalOedemaKeyPressed

    private void KetMuskuloskletalOedemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalOedemaKeyPressed
        Valid.pindah(evt, MuskuloskletalOedema, MuskuloskletalPegerakanSendi);
    }//GEN-LAST:event_KetMuskuloskletalOedemaKeyPressed

    private void KetMuskuloskletalFrakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalFrakturKeyPressed
        Valid.pindah(evt, MuskuloskletalFraktur, MuskuloskletalNyeriSendi);
    }//GEN-LAST:event_KetMuskuloskletalFrakturKeyPressed

    private void MuskuloskletalFrakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalFrakturKeyPressed
        Valid.pindah(evt, MuskuloskletalKekuatanOtot, KetMuskuloskletalFraktur);
    }//GEN-LAST:event_MuskuloskletalFrakturKeyPressed

    private void MuskuloskletalNyeriSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalNyeriSendiKeyPressed
        Valid.pindah(evt, KetMuskuloskletalFraktur, KetMuskuloskletalNyeriSendi);
    }//GEN-LAST:event_MuskuloskletalNyeriSendiKeyPressed

    private void KetMuskuloskletalNyeriSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetMuskuloskletalNyeriSendiKeyPressed
        Valid.pindah(evt, KetMuskuloskletalNyeriSendi, BAB);
    }//GEN-LAST:event_KetMuskuloskletalNyeriSendiKeyPressed

    private void MuskuloskletalPegerakanSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalPegerakanSendiKeyPressed
        Valid.pindah(evt, KetMuskuloskletalOedema, MuskuloskletalKekuatanOtot);
    }//GEN-LAST:event_MuskuloskletalPegerakanSendiKeyPressed

    private void MuskuloskletalKekuatanOtotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MuskuloskletalKekuatanOtotKeyPressed
        Valid.pindah(evt, MuskuloskletalPegerakanSendi, MuskuloskletalFraktur);
    }//GEN-LAST:event_MuskuloskletalKekuatanOtotKeyPressed

    private void KBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KBABKeyPressed
        Valid.pindah(evt, XBAB, WBAB);
    }//GEN-LAST:event_KBABKeyPressed

    private void BABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BABKeyPressed
        Valid.pindah(evt, KetMuskuloskletalNyeriSendi, XBAB);
    }//GEN-LAST:event_BABKeyPressed

    private void XBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_XBABKeyPressed
        Valid.pindah(evt, BAB, KBAB);
    }//GEN-LAST:event_XBABKeyPressed

    private void WBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBABKeyPressed
        Valid.pindah(evt, KBAB, BAK);
    }//GEN-LAST:event_WBABKeyPressed

    private void BAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BAKKeyPressed
        Valid.pindah(evt, WBAB, XBAK);
    }//GEN-LAST:event_BAKKeyPressed

    private void XBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_XBAKKeyPressed
        Valid.pindah(evt, BAK, WBAK);
    }//GEN-LAST:event_XBAKKeyPressed

    private void WBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBAKKeyPressed
        Valid.pindah(evt, XBAK, LBAK);
    }//GEN-LAST:event_WBAKKeyPressed

    private void LBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LBAKKeyPressed
        Valid.pindah(evt, WBAK, PolaAktifitasMandi);
    }//GEN-LAST:event_LBAKKeyPressed

    private void PolaAktifitasEliminasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaAktifitasEliminasiKeyPressed
        Valid.pindah(evt, PolaAktifitasBerpakaian, PolaAktifitasBerpindah);
    }//GEN-LAST:event_PolaAktifitasEliminasiKeyPressed

    private void PolaAktifitasMandiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaAktifitasMandiKeyPressed
        Valid.pindah(evt, LBAK, PolaAktifitasMakan);
    }//GEN-LAST:event_PolaAktifitasMandiKeyPressed

    private void PolaAktifitasMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaAktifitasMakanKeyPressed
        Valid.pindah(evt, PolaAktifitasMandi, PolaAktifitasBerpakaian);
    }//GEN-LAST:event_PolaAktifitasMakanKeyPressed

    private void PolaAktifitasBerpakaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaAktifitasBerpakaianKeyPressed
        Valid.pindah(evt, PolaAktifitasMakan, PolaAktifitasEliminasi);
    }//GEN-LAST:event_PolaAktifitasBerpakaianKeyPressed

    private void PolaAktifitasBerpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaAktifitasBerpindahKeyPressed
        Valid.pindah(evt, PolaAktifitasEliminasi, PolaNutrisiPorsi);
    }//GEN-LAST:event_PolaAktifitasBerpindahKeyPressed

    private void PolaNutrisiPorsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaNutrisiPorsiKeyPressed
        Valid.pindah(evt, PolaAktifitasBerpindah, PolaNutrisiFrekuensi);
    }//GEN-LAST:event_PolaNutrisiPorsiKeyPressed

    private void PolaNutrisiFrekuensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaNutrisiFrekuensiKeyPressed
        Valid.pindah(evt, PolaNutrisiPorsi, PolaNutrisiJenis);
    }//GEN-LAST:event_PolaNutrisiFrekuensiKeyPressed

    private void PolaNutrisiJenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaNutrisiJenisKeyPressed
        Valid.pindah(evt, PolaNutrisiFrekuensi, PolaTidurLama);
    }//GEN-LAST:event_PolaNutrisiJenisKeyPressed

    private void PolaTidurLamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaTidurLamaKeyPressed
        Valid.pindah(evt, PolaNutrisiJenis, PolaTidurGangguan);
    }//GEN-LAST:event_PolaTidurLamaKeyPressed

    private void PolaTidurGangguanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaTidurGangguanKeyPressed
        Valid.pindah(evt, PolaTidurLama, AktifitasSehari2);
    }//GEN-LAST:event_PolaTidurGangguanKeyPressed

    private void AktifitasSehari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasSehari2KeyPressed
        Valid.pindah(evt, PolaTidurGangguan, Berjalan);
    }//GEN-LAST:event_AktifitasSehari2KeyPressed

    private void BerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerjalanKeyPressed
        Valid.pindah(evt, AktifitasSehari2, KeteranganBerjalan);
    }//GEN-LAST:event_BerjalanKeyPressed

    private void KeteranganBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganBerjalanKeyPressed
        Valid.pindah(evt, Berjalan, Aktifitas);
    }//GEN-LAST:event_KeteranganBerjalanKeyPressed

    private void AktifitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasKeyPressed
        Valid.pindah(evt, KeteranganBerjalan, AlatAmbulasi);
    }//GEN-LAST:event_AktifitasKeyPressed

    private void AlatAmbulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatAmbulasiKeyPressed
        Valid.pindah(evt, Aktifitas, EkstrimitasAtas);
    }//GEN-LAST:event_AlatAmbulasiKeyPressed

    private void EkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstrimitasAtasKeyPressed
        Valid.pindah(evt, AlatAmbulasi, KeteranganEkstrimitasAtas);
    }//GEN-LAST:event_EkstrimitasAtasKeyPressed

    private void KeteranganEkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEkstrimitasAtasKeyPressed
        Valid.pindah(evt, EkstrimitasAtas, EkstrimitasBawah);
    }//GEN-LAST:event_KeteranganEkstrimitasAtasKeyPressed

    private void EkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstrimitasBawahKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasAtas, KeteranganEkstrimitasBawah);
    }//GEN-LAST:event_EkstrimitasBawahKeyPressed

    private void KeteranganEkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEkstrimitasBawahKeyPressed
        Valid.pindah(evt, EkstrimitasBawah, KemampuanMenggenggam);
    }//GEN-LAST:event_KeteranganEkstrimitasBawahKeyPressed

    private void KemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KemampuanMenggenggamKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasBawah,
                KeteranganKemampuanMenggenggam);
    }//GEN-LAST:event_KemampuanMenggenggamKeyPressed

    private void KeteranganKemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKemampuanMenggenggamKeyPressed
        Valid.pindah(evt, KemampuanMenggenggam, KemampuanKoordinasi);
    }//GEN-LAST:event_KeteranganKemampuanMenggenggamKeyPressed

    private void KemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KeteranganKemampuanMenggenggam,
                KeteranganKemampuanKoordinasi);
    }//GEN-LAST:event_KemampuanKoordinasiKeyPressed

    private void KeteranganKemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KemampuanKoordinasi, KesimpulanGangguanFungsi);
    }//GEN-LAST:event_KeteranganKemampuanKoordinasiKeyPressed

    private void KesimpulanGangguanFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesimpulanGangguanFungsiKeyPressed
        Valid.pindah(evt, KeteranganKemampuanKoordinasi, KondisiPsikologis);
    }//GEN-LAST:event_KesimpulanGangguanFungsiKeyPressed

    private void KondisiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiPsikologisKeyPressed
        Valid.pindah(evt, KesimpulanGangguanFungsi, AdakahPerilaku);
    }//GEN-LAST:event_KondisiPsikologisKeyPressed

    private void AdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AdakahPerilakuKeyPressed
        Valid.pindah(evt, KondisiPsikologis, KeteranganAdakahPerilaku);
    }//GEN-LAST:event_AdakahPerilakuKeyPressed

    private void KeteranganAdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganAdakahPerilakuKeyPressed
        Valid.pindah(evt, AdakahPerilaku, GangguanJiwa);
    }//GEN-LAST:event_KeteranganAdakahPerilakuKeyPressed

    private void GangguanJiwaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GangguanJiwaKeyPressed
        Valid.pindah(evt, KeteranganAdakahPerilaku, HubunganAnggotaKeluarga);
    }//GEN-LAST:event_GangguanJiwaKeyPressed

    private void HubunganAnggotaKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganAnggotaKeluargaKeyPressed
        Valid.pindah(evt, GangguanJiwa, TinggalDengan);
    }//GEN-LAST:event_HubunganAnggotaKeluargaKeyPressed

    private void TinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinggalDenganKeyPressed
        Valid.pindah(evt, HubunganAnggotaKeluarga, KeteranganTinggalDengan);
    }//GEN-LAST:event_TinggalDenganKeyPressed

    private void KeteranganTinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganTinggalDenganKeyPressed
        Valid.pindah(evt, TinggalDengan, NilaiKepercayaan);
    }//GEN-LAST:event_KeteranganTinggalDenganKeyPressed

    private void NilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiKepercayaanKeyPressed
        Valid.pindah(evt, KeteranganTinggalDengan, KeteranganNilaiKepercayaan);
    }//GEN-LAST:event_NilaiKepercayaanKeyPressed

    private void KeteranganNilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganNilaiKepercayaanKeyPressed
        Valid.pindah(evt, NilaiKepercayaan, PendidikanPJ);
    }//GEN-LAST:event_KeteranganNilaiKepercayaanKeyPressed

    private void PendidikanPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PendidikanPJKeyPressed
        Valid.pindah(evt, KeteranganNilaiKepercayaan, EdukasiPsikolgis);
    }//GEN-LAST:event_PendidikanPJKeyPressed

    private void EdukasiPsikolgisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiPsikolgisKeyPressed
        Valid.pindah(evt, PendidikanPJ, KeteranganEdukasiPsikologis);
    }//GEN-LAST:event_EdukasiPsikolgisKeyPressed

    private void KeteranganEdukasiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEdukasiPsikologisKeyPressed
        Valid.pindah(evt, EdukasiPsikolgis, Nyeri);
    }//GEN-LAST:event_KeteranganEdukasiPsikologisKeyPressed

    private void NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriKeyPressed
        Valid.pindah(evt, KeteranganEdukasiPsikologis, Provokes);
    }//GEN-LAST:event_NyeriKeyPressed

    private void ProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProvokesKeyPressed
        Valid.pindah(evt, Nyeri, KetProvokes);
    }//GEN-LAST:event_ProvokesKeyPressed

    private void KetProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetProvokesKeyPressed
        Valid.pindah(evt, Provokes, Quality);
    }//GEN-LAST:event_KetProvokesKeyPressed

    private void QualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QualityKeyPressed
        Valid.pindah(evt, KetProvokes, KetQuality);
    }//GEN-LAST:event_QualityKeyPressed

    private void KetQualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetQualityKeyPressed
        Valid.pindah(evt, Quality, Lokasi);
    }//GEN-LAST:event_KetQualityKeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        Valid.pindah(evt, KetQuality, Menyebar);
    }//GEN-LAST:event_LokasiKeyPressed

    private void MenyebarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenyebarKeyPressed
        Valid.pindah(evt, Lokasi, SkalaNyeri);
    }//GEN-LAST:event_MenyebarKeyPressed

    private void DurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DurasiKeyPressed
        Valid.pindah(evt, SkalaNyeri, NyeriHilang);
    }//GEN-LAST:event_DurasiKeyPressed

    private void NyeriHilangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriHilangKeyPressed
        Valid.pindah(evt, Durasi, KetNyeri);
    }//GEN-LAST:event_NyeriHilangKeyPressed

    private void KetNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNyeriKeyPressed
        Valid.pindah(evt, NyeriHilang, PadaDokter);
    }//GEN-LAST:event_KetNyeriKeyPressed

    private void PadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PadaDokterKeyPressed
        Valid.pindah(evt, KetNyeri, KetPadaDokter);
    }//GEN-LAST:event_PadaDokterKeyPressed

    private void KetPadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetPadaDokterKeyPressed
//        Valid.pindah(evt,PadaDokter,SkalaResiko1);
    }//GEN-LAST:event_KetPadaDokterKeyPressed

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
        if (tabModeMasalah.getRowCount() != 0) {
            try {
                tampilRencana2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
        if (tabModeMasalah.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
        if (tabModeMasalah.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilRencana2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        Valid.pindah2(evt, TCariMasalah, BtnSimpan);
    }//GEN-LAST:event_RencanaKeyPressed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatan form = new MasterMasalahKeperawatan(null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllMasalahActionPerformed
        TCari.setText("");
        tampilMasalah();
    }//GEN-LAST:event_BtnAllMasalahActionPerformed

    private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllMasalahActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariMasalah, TCariMasalah);
        }
    }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariMasalahActionPerformed
        tampilMasalah2();
    }//GEN-LAST:event_BtnCariMasalahActionPerformed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Kriteria1.requestFocus();
        }
    }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Kriteria1.requestFocus();
        }
    }//GEN-LAST:event_TCariMasalahKeyPressed

    private void BtnTambahRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahRencanaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterRencanaKeperawatan form = new MasterRencanaKeperawatan(null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void BtnAllRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllRencanaActionPerformed
        TCariRencana.setText("");
        tampilRencana();
        tampilRencana2();
    }//GEN-LAST:event_BtnAllRencanaActionPerformed

    private void BtnAllRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllRencanaActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariRencana, TCariRencana);
        }
    }//GEN-LAST:event_BtnAllRencanaKeyPressed

    private void BtnCariRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariRencanaActionPerformed
        tampilRencana2();
    }//GEN-LAST:event_BtnCariRencanaActionPerformed

    private void BtnCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilRencana2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCariRencana.requestFocus();
        }
    }//GEN-LAST:event_BtnCariRencanaKeyPressed

    private void TCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilRencana2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            BtnCariRencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCariMasalah.requestFocus();
        }
    }//GEN-LAST:event_TCariRencanaKeyPressed

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

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt, CaraMasuk, RPD);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void SkalaHumptyDumpty1ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaHumptyDumpty1.getSelectedIndex() == 0) {
            NilaiHumptyDumpty1.setText("4");
        } else if (SkalaHumptyDumpty1.getSelectedIndex() == 1) {
          NilaiHumptyDumpty1.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty1ItemStateChanged
        } else if (SkalaHumptyDumpty1.getSelectedIndex() == 2) {
            NilaiHumptyDumpty1.setText("2");
        } else {
            NilaiHumptyDumpty1.setText("1");
        }
        isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty1ItemStateChanged

    private void SkalaHumptyDumpty1KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaHumptyDumpty1KeyPressed
  }//GEN-LAST:event_SkalaHumptyDumpty1KeyPressed

    private void SkalaHumptyDumpty2ItemStateChanged(java.awt.event.ItemEvent evt) {
        if (SkalaHumptyDumpty2.getSelectedIndex() == 0) {
            NilaiHumptyDumpty2.setText("2");
        } else {
          NilaiHumptyDumpty2.setText("1");//GEN-FIRST:event_SkalaHumptyDumpty2ItemStateChanged
        }
        isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty2ItemStateChanged

    private void SkalaHumptyDumpty2KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty1, SkalaHumptyDumpty3);
    }
//GEN-FIRST:event_SkalaHumptyDumpty2KeyPressed
		private void SkalaHumptyDumpty3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty2KeyPressed
                    if (SkalaHumptyDumpty3.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty3.setText("4");
                    } else if (SkalaHumptyDumpty3.getSelectedIndex() == 1) {
          NilaiHumptyDumpty3.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty3ItemStateChanged
                    } else if (SkalaHumptyDumpty3.getSelectedIndex() == 2) {
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
		private void SkalaHumptyDumpty4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty3KeyPressed
                    if (SkalaHumptyDumpty4.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty4.setText("3");
                    } else if (SkalaHumptyDumpty4.getSelectedIndex() == 1) {
          NilaiHumptyDumpty4.setText("2");//GEN-FIRST:event_SkalaHumptyDumpty4ItemStateChanged
                    } else {
                        NilaiHumptyDumpty4.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty4ItemStateChanged

    private void SkalaHumptyDumpty4KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty3, SkalaHumptyDumpty5);
    }
//GEN-FIRST:event_SkalaHumptyDumpty4KeyPressed
		private void SkalaHumptyDumpty5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty4KeyPressed
                    if (SkalaHumptyDumpty5.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty5.setText("4");
                    } else if (SkalaHumptyDumpty5.getSelectedIndex() == 1) {
          NilaiHumptyDumpty5.setText("3");//GEN-FIRST:event_SkalaHumptyDumpty5ItemStateChanged
                    } else if (SkalaHumptyDumpty5.getSelectedIndex() == 2) {
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
		private void SkalaHumptyDumpty6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty5KeyPressed
                    if (SkalaHumptyDumpty6.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty6.setText("3");
                    } else if (SkalaHumptyDumpty6.getSelectedIndex() == 1) {
          NilaiHumptyDumpty6.setText("2");//GEN-FIRST:event_SkalaHumptyDumpty6ItemStateChanged
                    } else {
                        NilaiHumptyDumpty6.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty6ItemStateChanged

    private void SkalaHumptyDumpty6KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty5, SkalaHumptyDumpty7);
    }
//GEN-FIRST:event_SkalaHumptyDumpty6KeyPressed
		private void SkalaHumptyDumpty7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SkalaHumptyDumpty6KeyPressed
                    if (SkalaHumptyDumpty7.getSelectedIndex() == 0) {
                        NilaiHumptyDumpty4.setText("3");
                    } else if (SkalaHumptyDumpty7.getSelectedIndex() == 1) {
          NilaiHumptyDumpty7.setText("2");//GEN-FIRST:event_SkalaHumptyDumpty7ItemStateChanged
                    } else {
                        NilaiHumptyDumpty7.setText("1");
                    }
                    isTotalResikoHumptyDumpty();
  }//GEN-LAST:event_SkalaHumptyDumpty7ItemStateChanged

    private void SkalaHumptyDumpty7KeyPressed(java.awt.event.KeyEvent evt) {
//GEN-FIRST:event_SkalaHumptyDumpty7KeyPressed
  }//GEN-LAST:event_SkalaHumptyDumpty7KeyPressed

    private void SkalaWajahItemStateChanged(java.awt.event.ItemEvent evt) {
        NilaiWajah.setText(SkalaWajah.getSelectedIndex() + "");
        SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer
                .parseInt(NilaiKaki.getText()) + Integer.parseInt(NilaiAktifitas.
                getText()) + Integer.parseInt(NilaiMenangis.getText()) + Integer.//GEN-FIRST:event_SkalaWajahItemStateChanged
              parseInt(NilaiBersuara.getText())));
  }//GEN-LAST:event_SkalaWajahItemStateChanged

    private void SkalaWajahKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SG4, SkalaKaki);
    }
//GEN-FIRST:event_SkalaWajahKeyPressed
		private void NilaiWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaWajahKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiWajahKeyPressed
		private void SkalaKakiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_NilaiWajahKeyPressed
                    NilaiKaki.setText(SkalaKaki.getSelectedIndex() + "");
                    SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer
                            .parseInt(NilaiKaki.getText()) + Integer.parseInt(NilaiAktifitas.
                            getText()) + Integer.parseInt(NilaiMenangis.getText()) + Integer.//GEN-FIRST:event_SkalaKakiItemStateChanged
              parseInt(NilaiBersuara.getText())));
  }//GEN-LAST:event_SkalaKakiItemStateChanged

    private void SkalaKakiKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaWajah, SkalaAktifitas);
    }
//GEN-FIRST:event_SkalaKakiKeyPressed
		private void NilaiKakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaKakiKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiKakiKeyPressed
		private void SkalaAktifitasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_NilaiKakiKeyPressed
                    NilaiAktifitas.setText(SkalaAktifitas.getSelectedIndex() + "");
                    SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer
                            .parseInt(NilaiKaki.getText()) + Integer.parseInt(NilaiAktifitas.
                            getText()) + Integer.parseInt(NilaiMenangis.getText()) + Integer.//GEN-FIRST:event_SkalaAktifitasItemStateChanged
              parseInt(NilaiBersuara.getText())));
  }//GEN-LAST:event_SkalaAktifitasItemStateChanged

    private void SkalaAktifitasKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaKaki, SkalaMenangis);
    }
//GEN-FIRST:event_SkalaAktifitasKeyPressed
		private void NilaiAktifitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaAktifitasKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiAktifitasKeyPressed
		private void SkalaMenangisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_NilaiAktifitasKeyPressed
                    NilaiMenangis.setText(SkalaMenangis.getSelectedIndex() + "");
                    SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer
                            .parseInt(NilaiKaki.getText()) + Integer.parseInt(NilaiAktifitas.
                            getText()) + Integer.parseInt(NilaiMenangis.getText()) + Integer.//GEN-FIRST:event_SkalaMenangisItemStateChanged
              parseInt(NilaiBersuara.getText())));
  }//GEN-LAST:event_SkalaMenangisItemStateChanged

    private void SkalaMenangisKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaAktifitas, SkalaBersuara);
    }
//GEN-FIRST:event_SkalaMenangisKeyPressed
		private void NilaiMenangisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaMenangisKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiMenangisKeyPressed
		private void SkalaBersuaraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_NilaiMenangisKeyPressed
                    NilaiBersuara.setText(SkalaBersuara.getSelectedIndex() + "");
                    SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer
                            .parseInt(NilaiKaki.getText()) + Integer.parseInt(NilaiAktifitas.
                            getText()) + Integer.parseInt(NilaiMenangis.getText()) + Integer.//GEN-FIRST:event_SkalaBersuaraItemStateChanged
              parseInt(NilaiBersuara.getText())));
  }//GEN-LAST:event_SkalaBersuaraItemStateChanged

    private void SkalaBersuaraKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaMenangis, Nyeri);
    }
//GEN-FIRST:event_SkalaBersuaraKeyPressed
		private void NilaiBersuaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaBersuaraKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiBersuaraKeyPressed
		private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NilaiBersuaraKeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_SkalaNyeriKeyPressed
		private void AnakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SkalaNyeriKeyPressed
        Valid.pindah(evt, LBAK, DariSaudara);
    }
//GEN-FIRST:event_AnakkeKeyPressed
		private void DariSaudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_AnakkeKeyPressed
        Valid.pindah(evt, Anakke, UmurKelahiran);
    }
//GEN-FIRST:event_DariSaudaraKeyPressed
		private void CaraKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_DariSaudaraKeyPressed
        Valid.pindah(evt, UmurKelahiran, KetCaraKelahiran);
    }
//GEN-FIRST:event_CaraKelahiranKeyPressed
		private void KetCaraKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_CaraKelahiranKeyPressed
        Valid.pindah(evt, CaraKelahiran, KelainanBawaan);
    }
//GEN-FIRST:event_KetCaraKelahiranKeyPressed
		private void KelainanBawaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetCaraKelahiranKeyPressed
        Valid.pindah(evt, KetCaraKelahiran, KetKelainanBawaan);
    }
//GEN-FIRST:event_KelainanBawaanKeyPressed
		private void KetKelainanBawaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KelainanBawaanKeyPressed
        Valid.pindah(evt, KelainanBawaan, UsiaTengkurap);
    }
//GEN-FIRST:event_KetKelainanBawaanKeyPressed
		private void UmurKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_KetKelainanBawaanKeyPressed
        Valid.pindah(evt, DariSaudara, CaraKelahiran);
    }
//GEN-FIRST:event_UmurKelahiranKeyPressed
		private void BtnTambahImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_UmurKelahiranKeyPressed
                    if (TNoRM.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Pilih terlebih dahulu pasien yang mau dimasukkan data riwayat imunisasinya...");
          Anamnesis.requestFocus();//GEN-FIRST:event_BtnTambahImunisasiActionPerformed
                    } else {
                        KdImunisasi.setText("");
                        NmImunisasi.setText("");
                        ImunisasiKe.setSelectedIndex(0);
                        BtnImunisasi.setEnabled(true);
                        BtnSimpanImunisasi.setVisible(true);
                        BtnHapusImunisasi.setVisible(false);
                        DlgRiwayatImunisasi.setLocationRelativeTo(internalFrame1);
                        DlgRiwayatImunisasi.setVisible(true);
                    }
  }//GEN-LAST:event_BtnTambahImunisasiActionPerformed

    private void UsiaTengkurapKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, KetKelainanBawaan, UsiaDuduk);
    }
//GEN-FIRST:event_UsiaTengkurapKeyPressed
		private void UsiaDudukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaTengkurapKeyPressed
        Valid.pindah(evt, UsiaTengkurap, UsiaBerdiri);
    }
//GEN-FIRST:event_UsiaDudukKeyPressed
		private void UsiaBerdiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaDudukKeyPressed
        Valid.pindah(evt, UsiaDuduk, UsiaGigi);
    }
//GEN-FIRST:event_UsiaBerdiriKeyPressed
		private void UsiaGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaBerdiriKeyPressed
        Valid.pindah(evt, UsiaBerdiri, UsiaBerjalan);
    }
//GEN-FIRST:event_UsiaGigiKeyPressed
		private void UsiaBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaGigiKeyPressed
        Valid.pindah(evt, UsiaGigi, UsiaBicara);
    }
//GEN-FIRST:event_UsiaBerjalanKeyPressed
		private void UsiaBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaBerjalanKeyPressed
        Valid.pindah(evt, UsiaBerjalan, UsiaMembaca);
    }
//GEN-FIRST:event_UsiaBicaraKeyPressed
		private void UsiaMembacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaBicaraKeyPressed
        Valid.pindah(evt, UsiaBicara, UsiaMenulis);
    }
//GEN-FIRST:event_UsiaMembacaKeyPressed
		private void UsiaMenulisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaMembacaKeyPressed
        Valid.pindah(evt, UsiaMembaca, GangguanEmosi);
    }
//GEN-FIRST:event_UsiaMenulisKeyPressed
		private void GangguanEmosiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_UsiaMenulisKeyPressed
        Valid.pindah(evt, UsiaMenulis, PolaAktifitasMandi);
    }
//GEN-FIRST:event_GangguanEmosiKeyPressed
		private void BtnPanggilHapusImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_GangguanEmosiKeyPressed
                    if (tbImunisasi.getSelectedRow() > -1) {
                        if (TNoRM.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Pilih terlebih dahulu pasien yang mau dihapus data riwayat imunisasinya...");//GEN-FIRST:event_BtnPanggilHapusImunisasiActionPerformed
                            Anamnesis.requestFocus();
                        } else {
                            BtnImunisasi.setEnabled(false);
                            KdImunisasi.setText(tbImunisasi.getValueAt(tbImunisasi.
                                    getSelectedRow(), 0).toString());
                            NmImunisasi.setText(tbImunisasi.getValueAt(tbImunisasi.
                                    getSelectedRow(), 1).toString());
                            BtnSimpanImunisasi.setVisible(false);
                            BtnHapusImunisasi.setVisible(true);
                            DlgRiwayatImunisasi.setLocationRelativeTo(internalFrame1);
                            DlgRiwayatImunisasi.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                "Silahkan anda pilih data terlebih dahulu..!!");
                    }
  }//GEN-LAST:event_BtnPanggilHapusImunisasiActionPerformed

    private void SG1ItemStateChanged(java.awt.event.ItemEvent evt) {
        NilaiGizi1.setText(SG1.getSelectedIndex() + "");
        TotalNilaiGizi.setText(
                "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.//GEN-FIRST:event_SG1ItemStateChanged
              parseInt(NilaiGizi4.getText())));
  }//GEN-LAST:event_SG1ItemStateChanged

    private void SG1KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SkalaHumptyDumpty7, SG2);
    }
//GEN-FIRST:event_SG1KeyPressed
		private void SG2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SG1KeyPressed
                    NilaiGizi2.setText(SG2.getSelectedIndex() + "");
                    TotalNilaiGizi.setText(
                            "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                            NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.//GEN-FIRST:event_SG2ItemStateChanged
              parseInt(NilaiGizi4.getText())));
  }//GEN-LAST:event_SG2ItemStateChanged

    private void SG2KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SG1, SG3);
    }
//GEN-FIRST:event_SG2KeyPressed
		private void SG3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_SG2KeyPressed
                    NilaiGizi3.setText(SG3.getSelectedIndex() + "");
                    TotalNilaiGizi.setText(
                            "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                            NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.//GEN-FIRST:event_SG3ItemStateChanged
              parseInt(NilaiGizi4.getText())));
  }//GEN-LAST:event_SG3ItemStateChanged

    private void SG3KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SG2, SG4);
    }
//GEN-FIRST:event_SG3KeyPressed
		private void NilaiGizi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SG3KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiGizi1KeyPressed
		private void SG4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-LAST:event_NilaiGizi1KeyPressed
                    NilaiGizi4.setText(SG4.getSelectedIndex() + "");
                    TotalNilaiGizi.setText(
                            "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                            NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi4.getText()) + Integer.//GEN-FIRST:event_SG4ItemStateChanged
              parseInt(NilaiGizi4.getText())));
  }//GEN-LAST:event_SG4ItemStateChanged

    private void SG4KeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, SG3, SkalaWajah);
    }
//GEN-FIRST:event_SG4KeyPressed
		private void NilaiGizi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_SG4KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiGizi2KeyPressed
		private void NilaiGizi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NilaiGizi2KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiGizi3KeyPressed
		private void NilaiGizi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NilaiGizi3KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_NilaiGizi4KeyPressed
		private void TotalNilaiGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_NilaiGizi4KeyPressed
        // TODO add your handling code here:
    }
//GEN-FIRST:event_TotalNilaiGiziKeyPressed
		private void BtnKeluarImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_TotalNilaiGiziKeyPressed
        DlgRiwayatImunisasi.dispose();
    }
//GEN-FIRST:event_BtnKeluarImunisasiActionPerformed
		private void BtnSimpanImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_BtnKeluarImunisasiActionPerformed
                    if (KdImunisasi.getText().trim().isEmpty() || NmImunisasi.getText().trim().isEmpty()) {
                        Valid.textKosong(BtnImunisasi, "Imunisasi");
      } else {//GEN-FIRST:event_BtnSimpanImunisasiActionPerformed
                        if (Sequel.menyimpantf("riwayat_imunisasi", "?,?,?",
                                "Riwayat Imunisasi", 3, new String[]{
                                    TNoRM.getText(), KdImunisasi.getText(), ImunisasiKe.
                                    getSelectedItem().toString()
                                }) == true) {
                            KdImunisasi.setText("");
                            NmImunisasi.setText("");
                            ImunisasiKe.setSelectedIndex(0);
                            tampilImunisasi();
                        }
                    }
  }//GEN-LAST:event_BtnSimpanImunisasiActionPerformed

    private void BtnImunisasiActionPerformed(java.awt.event.ActionEvent evt) {
        imunisasi.isCek();
        imunisasi.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
      imunisasi.setLocationRelativeTo(internalFrame1);//GEN-FIRST:event_BtnImunisasiActionPerformed
        imunisasi.setVisible(true);
  }//GEN-LAST:event_BtnImunisasiActionPerformed

    private void BtnImunisasiKeyPressed(java.awt.event.KeyEvent evt) {
        // Valid.pindah(evt,Monitoring,BtnSimpan);
    }
//GEN-FIRST:event_BtnImunisasiKeyPressed
		private void KdImunisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BtnImunisasiKeyPressed
//GEN-FIRST:event_KdImunisasiKeyPressed
  }//GEN-LAST:event_KdImunisasiKeyPressed

    private void ImunisasiKeKeyPressed(java.awt.event.KeyEvent evt) {
        Valid.pindah(evt, TglAsuhan, TD);
    }
//GEN-FIRST:event_ImunisasiKeKeyPressed
		private void BtnHapusImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_ImunisasiKeKeyPressed
                    Sequel.queryu2("delete from riwayat_imunisasi where no_rkm_medis=? and kode_imunisasi=? and no_imunisasi=?",
                            3, new String[]{TNoRM.getText(), KdImunisasi.getText(),
                                ImunisasiKe.getSelectedItem().toString()});//GEN-FIRST:event_BtnHapusImunisasiActionPerformed
                    DlgRiwayatImunisasi.dispose();
                    tampilImunisasi();
  }//GEN-LAST:event_BtnHapusImunisasiActionPerformed

    private void BtnHapusImunisasiKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
//GEN-FIRST:event_BtnHapusImunisasiKeyPressed
    private void WajibKeyPressed(java.awt.event.KeyEvent evt) {//GEN-LAST:event_BtnHapusImunisasiKeyPressed
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
            RMPenilaianAwalKeperawatanRanapAnak dialog = new RMPenilaianAwalKeperawatanRanapAnak(
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
    private widget.TextBox Anakke;
    private widget.ComboBox Anamnesis;
    private widget.TextBox BAB;
    private widget.TextBox BAK;
    private widget.TextBox BB;
    private widget.TextBox Bahasa;
    private widget.ComboBox Berjalan;
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
    private widget.Button BtnHapusImunisasi;
    private widget.Button BtnImunisasi;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluarImunisasi;
    private widget.Button BtnPanggilHapusImunisasi;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanImunisasi;
    private widget.Button BtnTambahImunisasi;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahRencana;
    private widget.TextBox CaraBayar;
    private widget.ComboBox CaraKelahiran;
    private widget.ComboBox CaraMasuk;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DariSaudara;
    private widget.TextArea DetailRencana;
    private javax.swing.JDialog DlgRiwayatImunisasi;
    private widget.TextBox Durasi;
    private widget.ComboBox EdukasiPsikolgis;
    private widget.ComboBox EkstrimitasAtas;
    private widget.ComboBox EkstrimitasBawah;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox GCS;
    private widget.TextBox GangguanEmosi;
    private widget.ComboBox GangguanJiwa;
    private widget.ComboBox GastrointestinalAbdomen;
    private widget.ComboBox GastrointestinalAnus;
    private widget.ComboBox GastrointestinalGigi;
    private widget.ComboBox GastrointestinalLidah;
    private widget.ComboBox GastrointestinalMulut;
    private widget.ComboBox GastrointestinalTenggorakan;
    private widget.ComboBox GastrointestinalUsus;
    private widget.ComboBox HubunganAnggotaKeluarga;
    private widget.ComboBox ImunisasiKe;
    private widget.ComboBox IntegumentDecubitus;
    private widget.ComboBox IntegumentKulit;
    private widget.ComboBox IntegumentTurgor;
    private widget.ComboBox IntegumentWarnaKulit;
    private widget.TextBox Jk;
    private widget.TextBox KBAB;
    private widget.ComboBox KardiovaskularDenyutNadi;
    private widget.ComboBox KardiovaskularPulsasi;
    private widget.ComboBox KardiovaskularSirkulasi;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdImunisasi;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas2;
    private widget.ComboBox KeadaanMentalUmum;
    private widget.ComboBox KebiasaanAlkohol;
    private widget.TextBox KebiasaanJumlahAlkohol;
    private widget.TextBox KebiasaanJumlahRokok;
    private widget.ComboBox KebiasaanMerokok;
    private widget.ComboBox KebiasaanNarkoba;
    private widget.ComboBox KelainanBawaan;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox KemampuanKoordinasi;
    private widget.ComboBox KemampuanMenggenggam;
    private widget.TextBox KesadaranMental;
    private widget.ComboBox KesimpulanGangguanFungsi;
    private widget.TextBox KetAnamnesis;
    private widget.TextBox KetCaraKelahiran;
    private widget.TextBox KetGastrointestinalAbdomen;
    private widget.TextBox KetGastrointestinalGigi;
    private widget.TextBox KetGastrointestinalLidah;
    private widget.TextBox KetGastrointestinalMulut;
    private widget.TextBox KetGastrointestinalTenggorakan;
    private widget.TextBox KetHalanganLain;
    private widget.TextBox KetKardiovaskularSirkulasi;
    private widget.TextBox KetKelainanBawaan;
    private widget.TextBox KetMuskuloskletalFraktur;
    private widget.TextBox KetMuskuloskletalNyeriSendi;
    private widget.TextBox KetMuskuloskletalOedema;
    private widget.TextBox KetNeurologiBicara;
    private widget.TextBox KetNeurologiPenglihatan;
    private widget.TextBox KetNyeri;
    private widget.TextBox KetPadaDokter;
    private widget.TextBox KetProvokes;
    private widget.TextBox KetQuality;
    private widget.TextBox KetRespirasiJenisPernafasan;
    private widget.TextBox KetSedangMenyusui;
    private widget.TextBox KetSistemSarafKejang;
    private widget.TextBox KetSistemSarafKepala;
    private widget.TextBox KetSistemSarafWajah;
    private widget.TextBox KeteranganAdakahPerilaku;
    private widget.TextBox KeteranganBerjalan;
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
    private widget.TextBox LBAK;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextBox Lokasi;
    private widget.ComboBox MacamKasus;
    private widget.ComboBox Menyebar;
    private widget.ComboBox MotivasiKesembuhanIbadah;
    private widget.ComboBox MuskuloskletalFraktur;
    private widget.ComboBox MuskuloskletalKekuatanOtot;
    private widget.ComboBox MuskuloskletalNyeriSendi;
    private widget.ComboBox MuskuloskletalOedema;
    private widget.ComboBox MuskuloskletalPegerakanSendi;
    private widget.TextBox Nadi;
    private widget.ComboBox NeurologiAlatBantuPenglihatan;
    private widget.ComboBox NeurologiBicara;
    private widget.ComboBox NeurologiMotorik;
    private widget.ComboBox NeurologiOtot;
    private widget.ComboBox NeurologiPendengaran;
    private widget.ComboBox NeurologiPenglihatan;
    private widget.ComboBox NeurologiSensorik;
    private widget.TextBox NilaiAktifitas;
    private widget.TextBox NilaiBersuara;
    private widget.TextBox NilaiGizi1;
    private widget.TextBox NilaiGizi2;
    private widget.TextBox NilaiGizi3;
    private widget.TextBox NilaiGizi4;
    private widget.TextBox NilaiHumptyDumpty1;
    private widget.TextBox NilaiHumptyDumpty2;
    private widget.TextBox NilaiHumptyDumpty3;
    private widget.TextBox NilaiHumptyDumpty4;
    private widget.TextBox NilaiHumptyDumpty5;
    private widget.TextBox NilaiHumptyDumpty6;
    private widget.TextBox NilaiHumptyDumpty7;
    private widget.TextBox NilaiHumptyDumptyTotal;
    private widget.TextBox NilaiKaki;
    private widget.ComboBox NilaiKepercayaan;
    private widget.TextBox NilaiMenangis;
    private widget.TextBox NilaiWajah;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmImunisasi;
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
    private widget.ComboBox RespirasiBatuk;
    private widget.ComboBox RespirasiIrama;
    private widget.ComboBox RespirasiJenisPernafasan;
    private widget.ComboBox RespirasiPolaNafas;
    private widget.ComboBox RespirasiRetraksi;
    private widget.ComboBox RespirasiSuaraNafas;
    private widget.ComboBox RespirasiVolume;
    private widget.ComboBox SG1;
    private widget.ComboBox SG2;
    private widget.ComboBox SG3;
    private widget.ComboBox SG4;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox SedangMenyusui;
    private widget.ComboBox Sholat;
    private widget.ComboBox SistemSarafKejang;
    private widget.ComboBox SistemSarafKepala;
    private widget.ComboBox SistemSarafLeher;
    private widget.ComboBox SistemSarafSensorik;
    private widget.ComboBox SistemSarafWajah;
    private widget.ComboBox SkalaAktifitas;
    private widget.ComboBox SkalaBersuara;
    private widget.ComboBox SkalaHumptyDumpty1;
    private widget.ComboBox SkalaHumptyDumpty2;
    private widget.ComboBox SkalaHumptyDumpty3;
    private widget.ComboBox SkalaHumptyDumpty4;
    private widget.ComboBox SkalaHumptyDumpty5;
    private widget.ComboBox SkalaHumptyDumpty6;
    private widget.ComboBox SkalaHumptyDumpty7;
    private widget.ComboBox SkalaKaki;
    private widget.ComboBox SkalaMenangis;
    private widget.TextBox SkalaNyeri;
    private widget.ComboBox SkalaWajah;
    private widget.TextBox SpO2;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
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
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox TibadiRuang;
    private widget.ComboBox TinggalDengan;
    private widget.Label TingkatHumptyDumpty;
    private widget.ComboBox Toharah;
    private widget.TextBox TotalNilaiGizi;
    private widget.ComboBox UmurKelahiran;
    private widget.TextBox UsiaBerdiri;
    private widget.TextBox UsiaBerjalan;
    private widget.TextBox UsiaBicara;
    private widget.TextBox UsiaDuduk;
    private widget.TextBox UsiaGigi;
    private widget.TextBox UsiaMembaca;
    private widget.TextBox UsiaMenulis;
    private widget.TextBox UsiaTengkurap;
    private widget.TextBox WBAB;
    private widget.TextBox WBAK;
    private widget.ComboBox Wajib;
    private widget.TextBox XBAB;
    private widget.TextBox XBAK;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
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
    private widget.Label jLabel159;
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
    private widget.Label jLabel21;
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
    private widget.Label jLabel23;
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
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
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
    private widget.Label jLabel58;
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
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa2;
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
    private widget.ScrollPane scrollPane9;
    private widget.Table tbImunisasi;
    private widget.Table tbImunisasi2;
    private widget.Table tbMasalahDetail;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaKeperawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select penilaian_awal_keperawatan_ranap_anak.no_rawat,penilaian_awal_keperawatan_ranap_anak.tanggal,penilaian_awal_keperawatan_ranap_anak.informasi,penilaian_awal_keperawatan_ranap_anak.ket_informasi,penilaian_awal_keperawatan_ranap_anak.tiba_diruang_rawat,"
                    + "penilaian_awal_keperawatan_ranap_anak.kasus_trauma,penilaian_awal_keperawatan_ranap_anak.cara_masuk,penilaian_awal_keperawatan_ranap_anak.keluhan_utama,penilaian_awal_keperawatan_ranap_anak.rps,penilaian_awal_keperawatan_ranap_anak.rpd,penilaian_awal_keperawatan_ranap_anak.rpk,penilaian_awal_keperawatan_ranap_anak.rpo,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_pembedahan,penilaian_awal_keperawatan_ranap_anak.riwayat_dirawat_dirs,penilaian_awal_keperawatan_ranap_anak.alat_bantu_dipakai,penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_kehamilan_perkiraan,penilaian_awal_keperawatan_ranap_anak.riwayat_tranfusi,penilaian_awal_keperawatan_ranap_anak.riwayat_alergi,penilaian_awal_keperawatan_ranap_anak.riwayat_merokok,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_merokok_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol,penilaian_awal_keperawatan_ranap_anak.riwayat_alkohol_jumlah,penilaian_awal_keperawatan_ranap_anak.riwayat_narkoba,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_olahraga,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gcs,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_td,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_rr,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_suhu,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_spo2,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_bb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_tb,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kepala_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_wajah_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_leher,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_kejang_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_susunan_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_denyut_nadi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_sirkulasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_kardiovaskuler_pulsasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_pola_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_retraksi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_suara_nafas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_volume_pernafasan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_jenis_pernafasan_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_irama_nafas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_respirasi_batuk,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_mulut_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_gigi_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_lidah_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_tenggorokan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_abdomen_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_peistatik_usus,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_gastrointestinal_anus,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pengelihatan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_alat_bantu_penglihatan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_pendengaran,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_bicara_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_sensorik,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_motorik,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_neurologi_kekuatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_warnakulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_turgor,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_kulit,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_integument_dekubitas,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_kekauatan_otot,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_nyeri_sendi_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_oedema_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_muskuloskletal_fraktur_keterangan,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_jumlah,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_frekuensi_durasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_konsistensi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bab_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_jumlah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_frekuensi_durasi,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_warna,penilaian_awal_keperawatan_ranap_anak.pemeriksaan_eliminasi_bak_lainlain,penilaian_awal_keperawatan_ranap_anak.anakke,penilaian_awal_keperawatan_ranap_anak.darisaudara,"
                    + "penilaian_awal_keperawatan_ranap_anak.caralahir,penilaian_awal_keperawatan_ranap_anak.ket_caralahir,penilaian_awal_keperawatan_ranap_anak.umurkelahiran,penilaian_awal_keperawatan_ranap_anak.kelainanbawaan,penilaian_awal_keperawatan_ranap_anak.ket_kelainan_bawaan,"
                    + "penilaian_awal_keperawatan_ranap_anak.usiatengkurap,penilaian_awal_keperawatan_ranap_anak.usiaduduk,penilaian_awal_keperawatan_ranap_anak.usiaberdiri,penilaian_awal_keperawatan_ranap_anak.usiagigipertama,penilaian_awal_keperawatan_ranap_anak.usiaberjalan,"
                    + "penilaian_awal_keperawatan_ranap_anak.usiabicara,penilaian_awal_keperawatan_ranap_anak.usiamembaca,penilaian_awal_keperawatan_ranap_anak.usiamenulis,penilaian_awal_keperawatan_ranap_anak.gangguanemosi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_makanminum,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_mandi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_eliminasi,penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpakaian,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_aktifitas_berpindah,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_frekuesi_makan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_jenis_makanan,penilaian_awal_keperawatan_ranap_anak.pola_nutrisi_porsi_makan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pola_tidur_lama_tidur,penilaian_awal_keperawatan_ranap_anak.pola_tidur_gangguan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kemampuan_sehari,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_aktifitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_berjalan_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ambulasi,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_atas_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_ekstrimitas_bawah_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_menggenggam_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_koordinasi_keterangan,penilaian_awal_keperawatan_ranap_anak.pengkajian_fungsi_kesimpulan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_kondisi_psiko,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_perilaku_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_hubungan_keluarga,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_tinggal_keterangan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_nilai_kepercayaan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_pendidikan_pj,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_edukasi_diberikan_keterangan,"
                    + "penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_wajib,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_ket_halangan_lain,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_toharah,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_sholat,penilaian_awal_keperawatan_ranap_anak.riwayat_psiko_motivasi_kesembuhan_ibadah,"
                    + "penilaian_awal_keperawatan_ranap_anak.wajah,penilaian_awal_keperawatan_ranap_anak.nilaiwajah,penilaian_awal_keperawatan_ranap_anak.kaki,"
                    + "penilaian_awal_keperawatan_ranap_anak.nilaikaki,penilaian_awal_keperawatan_ranap_anak.aktifitas,penilaian_awal_keperawatan_ranap_anak.nilaiaktifitas,penilaian_awal_keperawatan_ranap_anak.menangis,penilaian_awal_keperawatan_ranap_anak.nilaimenangis,"
                    + "penilaian_awal_keperawatan_ranap_anak.bersuara,penilaian_awal_keperawatan_ranap_anak.nilaibersuara,penilaian_awal_keperawatan_ranap_anak.hasilnyeri,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_penyebab,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_kualitas,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_lokasi,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_menyebar,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_waktu,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_ranap_anak.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_usia,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_usia,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_jk,"
                    + "penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_jk,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_diagnosis,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_diagnosis,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_gangguan_kognitif,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_gangguan_kognitif,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_faktor_lingkungan,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_faktor_lingkungan,penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_respon_pembedahan,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_respon_pembedahan,"
                    + "penilaian_awal_keperawatan_ranap_anak.resiko_jatuh_medikamentosa,penilaian_awal_keperawatan_ranap_anak.nilai_resiko_jatuh_medikamentosa,penilaian_awal_keperawatan_ranap_anak.total_hasil_resiko_jatuh,penilaian_awal_keperawatan_ranap_anak.sg1,penilaian_awal_keperawatan_ranap_anak.nilai1,penilaian_awal_keperawatan_ranap_anak.sg2,penilaian_awal_keperawatan_ranap_anak.nilai2,penilaian_awal_keperawatan_ranap_anak.sg3,penilaian_awal_keperawatan_ranap_anak.nilai3,"
                    + "penilaian_awal_keperawatan_ranap_anak.sg4,penilaian_awal_keperawatan_ranap_anak.nilai4,penilaian_awal_keperawatan_ranap_anak.total_hasil,penilaian_awal_keperawatan_ranap_anak.kriteria1,penilaian_awal_keperawatan_ranap_anak.kriteria2,penilaian_awal_keperawatan_ranap_anak.kriteria3,penilaian_awal_keperawatan_ranap_anak.kriteria4,"
                    + "penilaian_awal_keperawatan_ranap_anak.pilihan1,penilaian_awal_keperawatan_ranap_anak.pilihan2,penilaian_awal_keperawatan_ranap_anak.pilihan3,"
                    + "penilaian_awal_keperawatan_ranap_anak.pilihan4,penilaian_awal_keperawatan_ranap_anak.pilihan5,penilaian_awal_keperawatan_ranap_anak.pilihan6,"
                    + "penilaian_awal_keperawatan_ranap_anak.pilihan7,penilaian_awal_keperawatan_ranap_anak.pilihan8,penilaian_awal_keperawatan_ranap_anak.rencana,penilaian_awal_keperawatan_ranap_anak.nip1,"
                    + "penilaian_awal_keperawatan_ranap_anak.nip2,penilaian_awal_keperawatan_ranap_anak.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_anak on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_anak.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_anak.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_anak.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_anak.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                    + "penilaian_awal_keperawatan_ranap_anak.tanggal between ? and ? "
                    + (TCari.getText().trim().isEmpty() ? ""
                    : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_ranap_anak.nip1 like ? or "
                    + "pengkaji1.nama like ? or penilaian_awal_keperawatan_ranap_anak.kd_dokter like ? or dokter.nm_dokter like ?)")
                    + " order by penilaian_awal_keperawatan_ranap_anak.tanggal");

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
                        rs.getString("pemeriksaan_susunan_kepala") + ", "
                        + rs.getString("pemeriksaan_susunan_kepala_keterangan"),
                        rs.getString("pemeriksaan_susunan_wajah") + ", "
                        + rs.getString("pemeriksaan_susunan_wajah_keterangan"),
                        rs.getString("pemeriksaan_susunan_leher"),
                        rs.getString("pemeriksaan_susunan_kejang") + ", "
                        + rs.getString("pemeriksaan_susunan_kejang_keterangan"),
                        rs.getString("pemeriksaan_susunan_sensorik"),
                        rs.getString("pemeriksaan_kardiovaskuler_pulsasi"),
                        rs.getString("pemeriksaan_kardiovaskuler_sirkulasi") + ", "
                        + rs.getString("pemeriksaan_kardiovaskuler_sirkulasi_keterangan"),
                        rs.getString("pemeriksaan_kardiovaskuler_denyut_nadi"),
                        rs.getString("pemeriksaan_respirasi_retraksi"),
                        rs.getString("pemeriksaan_respirasi_pola_nafas"),
                        rs.getString("pemeriksaan_respirasi_suara_nafas"),
                        rs.getString("pemeriksaan_respirasi_batuk"),
                        rs.getString("pemeriksaan_respirasi_volume_pernafasan"),
                        rs.getString("pemeriksaan_respirasi_jenis_pernafasan") + ", "
                        + rs.getString("pemeriksaan_respirasi_jenis_pernafasan_keterangan"),
                        rs.getString("pemeriksaan_respirasi_irama_nafas"),
                        rs.getString("pemeriksaan_gastrointestinal_mulut") + ", "
                        + rs.getString("pemeriksaan_gastrointestinal_mulut_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_lidah") + ", "
                        + rs.getString("pemeriksaan_gastrointestinal_lidah_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_gigi") + ", "
                        + rs.getString("pemeriksaan_gastrointestinal_gigi_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_tenggorokan") + ", "
                        + rs.getString("pemeriksaan_gastrointestinal_tenggorokan_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_abdomen") + ", "
                        + rs.getString("pemeriksaan_gastrointestinal_abdomen_keterangan"),
                        rs.getString("pemeriksaan_gastrointestinal_peistatik_usus"),
                        rs.getString("pemeriksaan_gastrointestinal_anus"),
                        rs.getString("pemeriksaan_neurologi_sensorik"),
                        rs.getString("pemeriksaan_neurologi_pengelihatan") + ", "
                        + rs.getString("pemeriksaan_neurologi_pengelihatan_keterangan"),
                        rs.getString("pemeriksaan_neurologi_alat_bantu_penglihatan"),
                        rs.getString("pemeriksaan_neurologi_motorik"),
                        rs.getString("pemeriksaan_neurologi_pendengaran"),
                        rs.getString("pemeriksaan_neurologi_bicara") + ", "
                        + rs.getString("pemeriksaan_neurologi_bicara_keterangan"),
                        rs.getString("pemeriksaan_neurologi_kekuatan_otot"),
                        rs.getString("pemeriksaan_integument_kulit"),
                        rs.getString("pemeriksaan_integument_warnakulit"),
                        rs.getString("pemeriksaan_integument_turgor"),
                        rs.getString("pemeriksaan_integument_dekubitas"),
                        rs.getString("pemeriksaan_muskuloskletal_oedema") + ", "
                        + rs.getString("pemeriksaan_muskuloskletal_oedema_keterangan"),
                        rs.getString("pemeriksaan_muskuloskletal_pergerakan_sendi"),
                        rs.getString("pemeriksaan_muskuloskletal_kekauatan_otot"),
                        rs.getString("pemeriksaan_muskuloskletal_fraktur") + ", "
                        + rs.getString("pemeriksaan_muskuloskletal_fraktur_keterangan"),
                        rs.getString("pemeriksaan_muskuloskletal_nyeri_sendi") + ", "
                        + rs.getString("pemeriksaan_muskuloskletal_nyeri_sendi_keterangan"),
                        rs.getString("pemeriksaan_eliminasi_bab_frekuensi_jumlah"),
                        rs.getString("pemeriksaan_eliminasi_bab_frekuensi_durasi"),
                        rs.getString("pemeriksaan_eliminasi_bab_konsistensi"),
                        rs.getString("pemeriksaan_eliminasi_bab_warna"),
                        rs.getString("pemeriksaan_eliminasi_bak_frekuensi_jumlah"),
                        rs.getString("pemeriksaan_eliminasi_bak_frekuensi_durasi"),
                        rs.getString("pemeriksaan_eliminasi_bak_warna"),
                        rs.getString("pemeriksaan_eliminasi_bak_lainlain"), rs.getString("anakke"),
                        rs.getString("darisaudara"), rs.getString("caralahir"), rs.getString("ket_caralahir"),
                        rs.getString("umurkelahiran"), rs.getString("kelainanbawaan"),
                        rs.getString("ket_kelainan_bawaan"), rs.getString("usiatengkurap"),
                        rs.getString("usiaduduk"), rs.getString("usiaberdiri"), rs.getString("usiagigipertama"),
                        rs.getString("usiaberjalan"), rs.getString("usiabicara"), rs.getString("usiamembaca"),
                        rs.getString("usiamenulis"), rs.getString("gangguanemosi"),
                        rs.getString("pola_aktifitas_mandi"), rs.getString("pola_aktifitas_makanminum"),
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
                        rs.getString("riwayat_psiko_motivasi_kesembuhan_ibadah"), rs.getString("wajah"),
                        rs.getString("nilaiwajah"), rs.getString("kaki"), rs.getString("nilaikaki"),
                        rs.getString("aktifitas"), rs.getString("nilaiaktifitas"), rs.getString("menangis"),
                        rs.getString("nilaimenangis"), rs.getString("bersuara"), rs.getString("nilaibersuara"),
                        rs.getString("hasilnyeri"), rs.getString("penilaian_nyeri"),
                        rs.getString("penilaian_nyeri_penyebab") + ", "
                        + rs.getString("penilaian_nyeri_ket_penyebab"),
                        rs.getString("penilaian_nyeri_kualitas") + ", "
                        + rs.getString("penilaian_nyeri_ket_kualitas"),
                        rs.getString("penilaian_nyeri_lokasi"), rs.getString("penilaian_nyeri_menyebar"),
                        rs.getString("penilaian_nyeri_waktu"),
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
                        rs.getString("sg1"), rs.getString("nilai1"), rs.getString("sg2"), rs.getString("nilai2"),
                        rs.getString("sg3"), rs.getString("nilai3"), rs.getString("sg4"), rs.getString("nilai4"),
                        rs.getString("total_hasil"), rs.getString("kriteria1"), rs.getString("kriteria2"),
                        rs.getString("kriteria3"), rs.getString("kriteria4"), rs.getString("pilihan1"),
                        rs.getString("pilihan2"), rs.getString("pilihan3"), rs.getString("pilihan4"),
                        rs.getString("pilihan5"), rs.getString("pilihan6"), rs.getString("pilihan7"),
                        rs.getString("pilihan8"), rs.getString("rencana")});
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

    /**
     *
     */
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
        SistemSarafKepala.setSelectedIndex(0);
        KetSistemSarafKepala.setText("");
        SistemSarafWajah.setSelectedIndex(0);
        KetSistemSarafWajah.setText("");
        SistemSarafLeher.setSelectedIndex(0);
        SistemSarafKejang.setSelectedIndex(0);
        KetSistemSarafKejang.setText("");
        SistemSarafSensorik.setSelectedIndex(0);
        KardiovaskularPulsasi.setSelectedIndex(0);
        KardiovaskularSirkulasi.setSelectedIndex(0);
        KetKardiovaskularSirkulasi.setText("");
        KardiovaskularDenyutNadi.setSelectedIndex(0);
        RespirasiRetraksi.setSelectedIndex(0);
        RespirasiPolaNafas.setSelectedIndex(0);
        RespirasiSuaraNafas.setSelectedIndex(0);
        RespirasiBatuk.setSelectedIndex(0);
        RespirasiVolume.setSelectedIndex(0);
        RespirasiJenisPernafasan.setSelectedIndex(0);
        KetRespirasiJenisPernafasan.setText("");
        RespirasiIrama.setSelectedIndex(0);
        GastrointestinalMulut.setSelectedIndex(0);
        KetGastrointestinalMulut.setText("");
        GastrointestinalLidah.setSelectedIndex(0);
        KetGastrointestinalLidah.setText("");
        GastrointestinalGigi.setSelectedIndex(0);
        KetGastrointestinalGigi.setText("");
        GastrointestinalTenggorakan.setSelectedIndex(0);
        KetGastrointestinalTenggorakan.setText("");
        GastrointestinalAbdomen.setSelectedIndex(0);
        KetGastrointestinalAbdomen.setText("");
        GastrointestinalUsus.setSelectedIndex(0);
        GastrointestinalAnus.setSelectedIndex(0);
        NeurologiSensorik.setSelectedIndex(0);
        NeurologiPenglihatan.setSelectedIndex(0);
        KetNeurologiPenglihatan.setText("");
        NeurologiAlatBantuPenglihatan.setSelectedIndex(0);
        NeurologiMotorik.setSelectedIndex(0);
        NeurologiPendengaran.setSelectedIndex(0);
        NeurologiBicara.setSelectedIndex(0);
        KetNeurologiBicara.setText("");
        NeurologiOtot.setSelectedIndex(0);
        IntegumentKulit.setSelectedIndex(0);
        IntegumentWarnaKulit.setSelectedIndex(0);
        IntegumentTurgor.setSelectedIndex(0);
        IntegumentDecubitus.setSelectedIndex(0);
        MuskuloskletalOedema.setSelectedIndex(0);
        KetMuskuloskletalOedema.setText("");
        MuskuloskletalPegerakanSendi.setSelectedIndex(0);
        MuskuloskletalKekuatanOtot.setSelectedIndex(0);
        MuskuloskletalFraktur.setSelectedIndex(0);
        KetMuskuloskletalFraktur.setText("");
        MuskuloskletalNyeriSendi.setSelectedIndex(0);
        KetMuskuloskletalNyeriSendi.setText("");
        BAB.setText("");
        XBAB.setText("");
        KBAB.setText("");
        WBAB.setText("");
        BAK.setText("");
        XBAK.setText("");
        WBAK.setText("");
        LBAK.setText("");
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
        Durasi.setText("");
        NyeriHilang.setSelectedIndex(0);
        KetNyeri.setText("");
        PadaDokter.setSelectedIndex(0);
        KetPadaDokter.setText("");
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
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString().contains("TAK")) {
                SistemSarafKepala.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString().contains("Hydrocephalus")) {
                SistemSarafKepala.setSelectedItem("Hydrocephalus");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString().contains("Hematoma")) {
                SistemSarafKepala.setSelectedItem("Hematoma");
            } else {
                SistemSarafKepala.setSelectedItem("Lain-lain");
            }
            KetSistemSarafKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 43)
                    .toString()
                    .replaceAll(SistemSarafKepala.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString().contains("TAK")) {
                SistemSarafWajah.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString().contains("Asimetris")) {
                SistemSarafWajah.setSelectedItem("Asimetris");
            } else {
                SistemSarafWajah.setSelectedItem("Kelainan Kongenital");
            }
            KetSistemSarafWajah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 44)
                    .toString()
                    .replaceAll(SistemSarafWajah.getSelectedItem().toString() + ", ", ""));
            SistemSarafLeher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 45).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString().contains("TAK")) {
                SistemSarafKejang.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString().contains("Kuat")) {
                SistemSarafKejang.setSelectedItem("Kuat");
            } else {
                SistemSarafKejang.setSelectedItem("Ada");
            }
            KetSistemSarafKejang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 46)
                    .toString()
                    .replaceAll(SistemSarafKejang.getSelectedItem().toString() + ", ", ""));
            SistemSarafSensorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString());
            KardiovaskularPulsasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 48).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString().contains("Akral Hangat")) {
                KardiovaskularSirkulasi.setSelectedItem("Akral Hangat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString().contains("Akral Dingin")) {
                KardiovaskularSirkulasi.setSelectedItem("Akral Dingin");
            } else {
                KardiovaskularSirkulasi.setSelectedItem("Edema");
            }
            KetKardiovaskularSirkulasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 49)
                    .toString()
                    .replaceAll(KardiovaskularSirkulasi.getSelectedItem().toString() + ", ", ""));
            KardiovaskularDenyutNadi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 50).toString());
            RespirasiRetraksi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 51).toString());
            RespirasiPolaNafas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 52).toString());
            RespirasiSuaraNafas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 53).toString());
            RespirasiBatuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 54).toString());
            RespirasiVolume.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 55).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 56).toString().contains("Pernafasan Dada")) {
                RespirasiJenisPernafasan.setSelectedItem("Pernafasan Dada");
            } else {
                RespirasiJenisPernafasan.setSelectedItem("Alat Bantu Pernafasaan");
            }
            KetRespirasiJenisPernafasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 56)
                    .toString()
                    .replaceAll(RespirasiJenisPernafasan.getSelectedItem().toString() + ", ", ""));
            RespirasiIrama.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString().contains("TAK")) {
                GastrointestinalMulut.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString().contains("Stomatitis")) {
                GastrointestinalMulut.setSelectedItem("Stomatitis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString().contains("Mukosa Kering")) {
                GastrointestinalMulut.setSelectedItem("Mukosa Kering");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString().contains("Bibir Pucat")) {
                GastrointestinalMulut.setSelectedItem("Bibir Pucat");
            } else {
                GastrointestinalMulut.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalMulut.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 58)
                    .toString()
                    .replaceAll(GastrointestinalMulut.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString().contains("TAK")) {
                GastrointestinalLidah.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString().contains("Kotor")) {
                GastrointestinalLidah.setSelectedItem("Kotor");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString().contains("Gerak Asimetris")) {
                GastrointestinalLidah.setSelectedItem("Gerak Asimetris");
            } else {
                GastrointestinalLidah.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalLidah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 59)
                    .toString()
                    .replaceAll(GastrointestinalLidah.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString().contains("TAK")) {
                GastrointestinalGigi.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString().contains("Karies")) {
                GastrointestinalGigi.setSelectedItem("Karies");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString().contains("Goyang")) {
                GastrointestinalGigi.setSelectedItem("Goyang");
            } else {
                GastrointestinalGigi.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalGigi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 60)
                    .toString()
                    .replaceAll(GastrointestinalGigi.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString().contains("TAK")) {
                GastrointestinalTenggorakan.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString().contains("Gangguan Menelan")) {
                GastrointestinalTenggorakan.setSelectedItem("Gangguan Menelan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString().contains("Sakit Menelan")) {
                GastrointestinalTenggorakan.setSelectedItem("Sakit Menelan");
            } else {
                GastrointestinalTenggorakan.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalTenggorakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 61)
                    .toString()
                    .replaceAll(GastrointestinalTenggorakan.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString().contains("Supel")) {
                GastrointestinalAbdomen.setSelectedItem("Supel");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString().contains("Asictes")) {
                GastrointestinalAbdomen.setSelectedItem("Asictes");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString().contains("Tegang")) {
                GastrointestinalAbdomen.setSelectedItem("Tegang");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString().contains("Nyeri Tekan/Lepas")) {
                GastrointestinalAbdomen.setSelectedItem("Nyeri Tekan/Lepas");
            } else {
                GastrointestinalAbdomen.setSelectedItem("Lain-lain");
            }
            KetGastrointestinalAbdomen.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 62)
                    .toString()
                    .replaceAll(GastrointestinalAbdomen.getSelectedItem().toString() + ", ", ""));
            GastrointestinalUsus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString());
            GastrointestinalAnus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 64).toString());
            NeurologiSensorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 65).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 66).toString().contains("TAK")) {
                NeurologiPenglihatan.setSelectedItem("TAK");
            } else {
                NeurologiPenglihatan.setSelectedItem("Ada Kelainan");
            }
            KetNeurologiPenglihatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 66)
                    .toString()
                    .replaceAll(NeurologiPenglihatan.getSelectedItem().toString() + ", ", ""));
            NeurologiAlatBantuPenglihatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 67).toString());
            NeurologiMotorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 68).toString());
            NeurologiPendengaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 69).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString().contains("Jelas")) {
                NeurologiBicara.setSelectedItem("Jelas");
            } else {
                NeurologiBicara.setSelectedItem("Tidak Jelas");
            }
            KetNeurologiBicara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 70)
                    .toString()
                    .replaceAll(NeurologiBicara.getSelectedItem().toString() + ", ", ""));
            NeurologiOtot.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            IntegumentKulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString());
            IntegumentWarnaKulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString());
            IntegumentTurgor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString());
            IntegumentDecubitus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString().contains("Tidak Ada")) {
                MuskuloskletalOedema.setSelectedItem("Tidak Ada");
            } else {
                MuskuloskletalOedema.setSelectedItem("Ada");
            }
            KetMuskuloskletalOedema.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 76)
                    .toString()
                    .replaceAll(MuskuloskletalOedema.getSelectedItem().toString() + ", ", ""));
            MuskuloskletalPegerakanSendi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString());
            MuskuloskletalKekuatanOtot.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString().contains("Tidak Ada")) {
                MuskuloskletalFraktur.setSelectedItem("Tidak Ada");
            } else {
                MuskuloskletalFraktur.setSelectedItem("Ada");
            }
            KetMuskuloskletalFraktur.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 79)
                    .toString()
                    .replaceAll(MuskuloskletalFraktur.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 80).toString().contains("Tidak Ada")) {
                MuskuloskletalNyeriSendi.setSelectedItem("Tidak Ada");
            } else {
                MuskuloskletalNyeriSendi.setSelectedItem("Ada");
            }
            KetMuskuloskletalNyeriSendi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 80)
                    .toString()
                    .replaceAll(MuskuloskletalNyeriSendi.getSelectedItem().toString() + ", ", ""));
            BAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString());
            XBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 82).toString());
            KBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 83).toString());
            WBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 84).toString());
            BAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 85).toString());
            XBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 86).toString());
            WBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString());
            LBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 88).toString());
            Anakke.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 89).toString());
            DariSaudara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString());
            CaraKelahiran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString());
            KetCaraKelahiran.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 92).toString());
            UmurKelahiran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 93).toString());
            KelainanBawaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 94).toString());
            KetKelainanBawaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 95).toString());
            UsiaTengkurap.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 96).toString());
            UsiaDuduk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 97).toString());
            UsiaBerdiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString());
            UsiaGigi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 99).toString());
            UsiaBerjalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 100).toString());
            UsiaBicara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 101).toString());
            UsiaMembaca.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 102).toString());
            UsiaMenulis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString());
            GangguanEmosi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 104).toString());
            PolaAktifitasMandi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 105).toString());
            PolaAktifitasMakan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 106).toString());
            PolaAktifitasBerpakaian.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 107).toString());
            PolaAktifitasEliminasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 108).toString());
            PolaAktifitasBerpindah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 109).toString());
            PolaNutrisiPorsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 110).toString());
            PolaNutrisiFrekuensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 111).toString());
            PolaNutrisiJenis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 112).toString());
            PolaTidurLama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 113).toString());
            PolaTidurGangguan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 114).toString());
            AktifitasSehari2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 115).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("TAK")) {
                Berjalan.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Penurunan Kekuatan/ROM")) {
                Berjalan.setSelectedItem("Penurunan Kekuatan/ROM");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Paralisis")) {
                Berjalan.setSelectedItem("Paralisis");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Sering Jatuh")) {
                Berjalan.setSelectedItem("Sering Jatuh");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Deformitas")) {
                Berjalan.setSelectedItem("Deformitas");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString().contains("Hilang keseimbangan")) {
                Berjalan.setSelectedItem("Hilang keseimbangan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString().contains("Riwayat Patah Tulang")) {
                Berjalan.setSelectedItem("Riwayat Patah Tulang");
            } else {
                Berjalan.setSelectedItem("Lain-lain");
            }
            KeteranganBerjalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 116)
                    .toString()
                    .replaceAll(Berjalan.getSelectedItem().toString() + ", ", ""));
            Aktifitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 117).toString());
            AlatAmbulasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 118).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString().contains("TAK")) {
                EkstrimitasAtas.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString().contains("Lemah")) {
                EkstrimitasAtas.setSelectedItem("Lemah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString().contains("Oedema")) {
                EkstrimitasAtas.setSelectedItem("Oedema");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString().contains("Tidak Simetris")) {
                EkstrimitasAtas.setSelectedItem("Tidak Simetris");
            } else {
                EkstrimitasAtas.setSelectedItem("Lain-lain");
            }
            KeteranganEkstrimitasAtas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 119)
                    .toString()
                    .replaceAll(EkstrimitasAtas.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString().contains("TAK")) {
                EkstrimitasBawah.setSelectedItem("TAK");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString().contains("Varises")) {
                EkstrimitasBawah.setSelectedItem("Varises");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString().contains("Oedema")) {
                EkstrimitasBawah.setSelectedItem("Oedema");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString().contains("Tidak Simetris")) {
                EkstrimitasBawah.setSelectedItem("Tidak Simetris");
            } else {
                EkstrimitasBawah.setSelectedItem("Lain-lain");
            }
            KeteranganEkstrimitasBawah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 120)
                    .toString()
                    .replaceAll(EkstrimitasBawah.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 121).toString().contains("Tidak Ada Kesulitan")) {
                KemampuanMenggenggam.setSelectedItem("Tidak Ada Kesulitan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 121).toString().contains("Terakhir")) {
                KemampuanMenggenggam.setSelectedItem("Terakhir");
            } else {
                KemampuanMenggenggam.setSelectedItem("Lain-lain");
            }
            KeteranganKemampuanMenggenggam.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 121)
                    .toString()
                    .replaceAll(KemampuanMenggenggam.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 122).toString().contains("Tidak Ada Kesulitan")) {
                KemampuanKoordinasi.setSelectedItem("Tidak Ada Kesulitan");
            } else {
                KemampuanKoordinasi.setSelectedItem("Ada Masalah");
            }
            KeteranganKemampuanKoordinasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 122)
                    .toString()
                    .replaceAll(KemampuanKoordinasi.getSelectedItem().toString() + ", ", ""));
            KesimpulanGangguanFungsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 123).toString());
            KondisiPsikologis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 124).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString().contains("Tidak Ada Masalah")) {
                AdakahPerilaku.setSelectedItem("Tidak Ada Masalah");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString().contains("Perilaku Kekerasan")) {
                AdakahPerilaku.setSelectedItem("Perilaku Kekerasan");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString().contains("Gangguan Efek")) {
                AdakahPerilaku.setSelectedItem("Gangguan Efek");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString().contains("Gangguan Memori")) {
                AdakahPerilaku.setSelectedItem("Gangguan Memori");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString().contains("Halusinasi")) {
                AdakahPerilaku.setSelectedItem("Halusinasi");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 125)
                    .toString()
                    .contains("Kecenderungan Percobaan Bunuh Diri")) {
                AdakahPerilaku.setSelectedItem("Kecenderungan Percobaan Bunuh Diri");
            } else {
                AdakahPerilaku.setSelectedItem("Lain-lain");
            }
            KeteranganAdakahPerilaku.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 125)
                    .toString()
                    .replaceAll(AdakahPerilaku.getSelectedItem().toString() + ", ", ""));
            GangguanJiwa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 126).toString());
            HubunganAnggotaKeluarga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 127).toString());
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 128).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString().contains("Sendiri")) {
                TinggalDengan.setSelectedItem("Sendiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString().contains("Orang Tua")) {
                TinggalDengan.setSelectedItem("Orang Tua");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString().contains("Suami/Istri")) {
                TinggalDengan.setSelectedItem("Suami/Istri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString().contains("Keluarga")) {
                TinggalDengan.setSelectedItem("Keluarga");
            } else {
                TinggalDengan.setSelectedItem("Lain-lain");
            }
            KeteranganTinggalDengan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 129)
                    .toString()
                    .replaceAll(TinggalDengan.getSelectedItem().toString() + ", ", ""));
            PekerjaanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 130).toString());
            CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 131).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 132).toString().contains("Tidak Ada")) {
                NilaiKepercayaan.setSelectedItem("Tidak Ada");
            } else {
                NilaiKepercayaan.setSelectedItem("Ada");
            }
            KeteranganNilaiKepercayaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 132)
                    .toString()
                    .replaceAll(NilaiKepercayaan.getSelectedItem().toString() + ", ", ""));
            Bahasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 133).toString());
            PendidikanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 134).toString());
            PendidikanPJ.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 136).toString().contains("Pasien")) {
                EdukasiPsikolgis.setSelectedItem("Pasien");
            } else {
                EdukasiPsikolgis.setSelectedItem("Keluarga");
            }
            KeteranganEdukasiPsikologis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 136)
                    .toString()
                    .replaceAll(EdukasiPsikolgis.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 137).toString().contains("Baligh")) {
                Wajib.setSelectedItem("Baligh");
            } else {
                Wajib.setSelectedItem("Non baligh");
            }
            KetHalanganLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 138).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 139).toString().contains("Tayamum")) {
                Toharah.setSelectedItem("Tayamum");
            } else {
                Toharah.setSelectedItem("Wudhu");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 140).toString().contains("Berdiri")) {
                Sholat.setSelectedItem("Berdiri");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 140).toString().contains("Duduk")) {
                Sholat.setSelectedItem("Duduk");
            } else {
                Sholat.setSelectedItem("Berbaring");
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 141).toString().contains("Ya")) {
                MotivasiKesembuhanIbadah.setSelectedItem("Ya");
            } else {
                MotivasiKesembuhanIbadah.setSelectedItem("Tidak");
            }
            SkalaWajah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 142).toString());
            NilaiWajah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 143).toString());
            SkalaKaki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 144).toString());
            NilaiKaki.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 145).toString());
            SkalaAktifitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 146).toString());
            NilaiAktifitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 147).toString());
            SkalaMenangis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 148).toString());
            NilaiMenangis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 149).toString());
            SkalaBersuara.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 150).toString());
            NilaiBersuara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 151).toString());
            SkalaNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 152).toString());
            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 153).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 154).toString().contains("Proses Penyakit")) {
                Provokes.setSelectedItem("Proses Penyakit");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 154).toString().contains("Benturan")) {
                Provokes.setSelectedItem("Benturan");
            } else {
                Provokes.setSelectedItem("Lain-lain");
            }
            KetProvokes.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 154)
                    .toString()
                    .replaceAll(Provokes.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString().contains("Seperti Tertusuk")) {
                Quality.setSelectedItem("Seperti Tertusuk");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString().contains("Berdenyut")) {
                Quality.setSelectedItem("Berdenyut");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString().contains("Teriris")) {
                Quality.setSelectedItem("Teriris");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString().contains("Tertindih")) {
                Quality.setSelectedItem("Tertindih");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString().contains("Tertiban")) {
                Quality.setSelectedItem("Tertiban");
            } else {
                Quality.setSelectedItem("Lain-lain");
            }
            KetQuality.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 155)
                    .toString()
                    .replaceAll(Quality.getSelectedItem().toString() + ", ", ""));
            Lokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 156).toString());
            Menyebar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 157).toString());
            Durasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 158).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 159).toString().contains("Istirahat")) {
                NyeriHilang.setSelectedItem("Istirahat");
            } else if (tbObat.getValueAt(tbObat.getSelectedRow(), 159).toString().contains("Medengar Musik")) {
                NyeriHilang.setSelectedItem("Medengar Musik");
            } else {
                NyeriHilang.setSelectedItem("Minum Obat");
            }
            KetNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 159)
                    .toString()
                    .replaceAll(NyeriHilang.getSelectedItem().toString() + ", ", ""));
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString().contains("Ya")) {
                PadaDokter.setSelectedItem("Ya");
            } else {
                PadaDokter.setSelectedItem("Tidak");
            }
            KetPadaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 160)
                    .toString()
                    .replaceAll(PadaDokter.getSelectedItem().toString() + ", ", ""));
            SkalaHumptyDumpty1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 161).toString());
            SkalaHumptyDumpty2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString());
            SkalaHumptyDumpty3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 165).toString());
            SkalaHumptyDumpty4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 167).toString());
            SkalaHumptyDumpty5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString());
            SkalaHumptyDumpty6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 171).toString());
            SkalaHumptyDumpty7.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString());
            isTotalResikoHumptyDumpty();
            SG1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 176).toString());
            NilaiGizi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 177).toString());
            SG2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 178).toString());
            NilaiGizi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 179).toString());
            SG3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 180).toString());
            NilaiGizi3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 181).toString());
            SG4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 182).toString());
            NilaiGizi4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 183).toString());
            TotalNilaiGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 184).toString());
            Kriteria1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 185).toString());
            Kriteria2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 186).toString());
            Kriteria3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 187).toString());
            Kriteria4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 189).toString().equals("Perawatan diri (Mandi, BAB, BAK)")) {
                pilihan1.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 190).toString().equals("Pemantauan pemberian obat")) {
                pilihan2.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 191).toString().equals("Pemantauan diet")) {
                pilihan3.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 192)
                    .toString()
                    .equals("Bantuan medis / perawatan di rumah (Homecare)")) {
                pilihan4.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 193).toString().equals("Perawatan luka")) {
                pilihan5.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 194).toString().equals("Latihan fisik lanjutan")) {
                pilihan6.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 195)
                    .toString()
                    .equals("Pendampingan tenaga khusus di rumah")) {
                pilihan7.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 196)
                    .toString()
                    .equals("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)")) {
                pilihan8.setSelected(true);
            }
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 197).toString());

            try {
                Valid.tabelKosong(tabModeMasalah);

                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_anak on penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_anak.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah");
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
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_anak on penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_anak.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana");
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
                    + "bahasa_pasien.nama_bahasa,pasien.pnd,pasien.pekerjaan "
                    + "from pasien inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Agama.setText(rs.getString("agama"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    PendidikanPasien.setText(rs.getString("pnd"));
                    PekerjaanPasien.setText(rs.getString("pekerjaan"));
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

    /**
     * @param norwt
     * @param tgl2
     * @param carabayar
     * @param norm
     */
    public void setNoRm(String norwt, Date tgl2, String carabayar, String norm) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='" + norwt + "'",
                DTPCari1);
        CaraBayar.setText(carabayar);
        DTPCari2.setDate(tgl2);
        isRawat();
    }

    /**
     *
     */
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
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 197).toString());

            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan.kode_masalah,master_masalah_keperawatan.nama_masalah from master_masalah_keperawatan "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_anak on penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah=master_masalah_keperawatan.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_anak.no_rawat=? and penilaian_awal_keperawatan_ranap_masalah_anak.tanggal=? order by penilaian_awal_keperawatan_ranap_masalah_anak.kode_masalah");
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
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_anak on penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana=master_rencana_keperawatan.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_anak.no_rawat=? and penilaian_awal_keperawatan_ranap_rencana_anak.tanggal=? order by penilaian_awal_keperawatan_ranap_rencana_anak.kode_rencana");
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
        if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_ranap_anak where no_rawat=? and tanggal=?", 2,
                new String[]{tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString()}) == true) {
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_anak", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_anak", "no_rawat", "tanggal",
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
        if (Sequel.mengedittf("penilaian_awal_keperawatan_ranap_anak", "no_rawat=?",
                "no_rawat=?,tanggal=?,informasi=?,ket_informasi=?,tiba_diruang_rawat=?,kasus_trauma=?,cara_masuk=?,keluhan_utama=?,rps=?,rpd=?,rpk=?,rpo=?,riwayat_pembedahan=?,riwayat_dirawat_dirs=?,alat_bantu_dipakai=?,riwayat_kehamilan=?,riwayat_kehamilan_perkiraan=?,riwayat_tranfusi=?,riwayat_alergi=?,riwayat_merokok=?,riwayat_merokok_jumlah=?,riwayat_alkohol=?,riwayat_alkohol_jumlah=?,riwayat_narkoba=?,riwayat_olahraga=?,pemeriksaan_mental=?,pemeriksaan_keadaan_umum=?,pemeriksaan_gcs=?,pemeriksaan_td=?,pemeriksaan_nadi=?,pemeriksaan_rr=?,pemeriksaan_suhu=?,pemeriksaan_spo2=?,pemeriksaan_bb=?,pemeriksaan_tb=?,pemeriksaan_susunan_kepala=?,pemeriksaan_susunan_kepala_keterangan=?,pemeriksaan_susunan_wajah=?,pemeriksaan_susunan_wajah_keterangan=?,pemeriksaan_susunan_leher=?,pemeriksaan_susunan_kejang=?,pemeriksaan_susunan_kejang_keterangan=?,pemeriksaan_susunan_sensorik=?,pemeriksaan_kardiovaskuler_denyut_nadi=?,pemeriksaan_kardiovaskuler_sirkulasi=?,pemeriksaan_kardiovaskuler_sirkulasi_keterangan=?,pemeriksaan_kardiovaskuler_pulsasi=?,pemeriksaan_respirasi_pola_nafas=?,pemeriksaan_respirasi_retraksi=?,pemeriksaan_respirasi_suara_nafas=?,pemeriksaan_respirasi_volume_pernafasan=?,pemeriksaan_respirasi_jenis_pernafasan=?,pemeriksaan_respirasi_jenis_pernafasan_keterangan=?,pemeriksaan_respirasi_irama_nafas=?,pemeriksaan_respirasi_batuk=?,pemeriksaan_gastrointestinal_mulut=?,pemeriksaan_gastrointestinal_mulut_keterangan=?,pemeriksaan_gastrointestinal_gigi=?,pemeriksaan_gastrointestinal_gigi_keterangan=?,pemeriksaan_gastrointestinal_lidah=?,pemeriksaan_gastrointestinal_lidah_keterangan=?,pemeriksaan_gastrointestinal_tenggorokan=?,pemeriksaan_gastrointestinal_tenggorokan_keterangan=?,pemeriksaan_gastrointestinal_abdomen=?,pemeriksaan_gastrointestinal_abdomen_keterangan=?,pemeriksaan_gastrointestinal_peistatik_usus=?,pemeriksaan_gastrointestinal_anus=?,pemeriksaan_neurologi_pengelihatan=?,pemeriksaan_neurologi_pengelihatan_keterangan=?,pemeriksaan_neurologi_alat_bantu_penglihatan=?,pemeriksaan_neurologi_pendengaran=?,pemeriksaan_neurologi_bicara=?,pemeriksaan_neurologi_bicara_keterangan=?,pemeriksaan_neurologi_sensorik=?,pemeriksaan_neurologi_motorik=?,pemeriksaan_neurologi_kekuatan_otot=?,pemeriksaan_integument_warnakulit=?,pemeriksaan_integument_turgor=?,pemeriksaan_integument_kulit=?,pemeriksaan_integument_dekubitas=?,pemeriksaan_muskuloskletal_pergerakan_sendi=?,pemeriksaan_muskuloskletal_kekauatan_otot=?,pemeriksaan_muskuloskletal_nyeri_sendi=?,pemeriksaan_muskuloskletal_nyeri_sendi_keterangan=?,pemeriksaan_muskuloskletal_oedema=?,pemeriksaan_muskuloskletal_oedema_keterangan=?,pemeriksaan_muskuloskletal_fraktur=?,pemeriksaan_muskuloskletal_fraktur_keterangan=?,pemeriksaan_eliminasi_bab_frekuensi_jumlah=?,pemeriksaan_eliminasi_bab_frekuensi_durasi=?,pemeriksaan_eliminasi_bab_konsistensi=?,pemeriksaan_eliminasi_bab_warna=?,pemeriksaan_eliminasi_bak_frekuensi_jumlah=?,pemeriksaan_eliminasi_bak_frekuensi_durasi=?,pemeriksaan_eliminasi_bak_warna=?,pemeriksaan_eliminasi_bak_lainlain=?,anakke=?,darisaudara=?,caralahir=?,ket_caralahir=?,umurkelahiran=?,kelainanbawaan=?,ket_kelainan_bawaan=?,usiatengkurap=?,usiaduduk=?,usiaberdiri=?,usiagigipertama=?,usiaberjalan=?,usiabicara=?,usiamembaca=?,usiamenulis=?,gangguanemosi=?,pola_aktifitas_mandi=?,pola_aktifitas_makanminum=?,pola_aktifitas_eliminasi=?,pola_aktifitas_berpakaian=?,pola_aktifitas_berpindah=?,pola_nutrisi_frekuesi_makan=?,pola_nutrisi_jenis_makanan=?,pola_nutrisi_porsi_makan=?,pola_tidur_lama_tidur=?,pola_tidur_gangguan=?,pengkajian_fungsi_kemampuan_sehari=?,pengkajian_fungsi_aktifitas=?,pengkajian_fungsi_berjalan=?,pengkajian_fungsi_berjalan_keterangan=?,pengkajian_fungsi_ambulasi=?,pengkajian_fungsi_ekstrimitas_atas=?,pengkajian_fungsi_ekstrimitas_atas_keterangan=?,pengkajian_fungsi_ekstrimitas_bawah=?,pengkajian_fungsi_ekstrimitas_bawah_keterangan=?,pengkajian_fungsi_menggenggam=?,pengkajian_fungsi_menggenggam_keterangan=?,pengkajian_fungsi_koordinasi=?,pengkajian_fungsi_koordinasi_keterangan=?,pengkajian_fungsi_kesimpulan=?,riwayat_psiko_kondisi_psiko=?,riwayat_psiko_gangguan_jiwa=?,riwayat_psiko_perilaku=?,riwayat_psiko_perilaku_keterangan=?,riwayat_psiko_hubungan_keluarga=?,riwayat_psiko_tinggal=?,riwayat_psiko_tinggal_keterangan=?,riwayat_psiko_nilai_kepercayaan=?,riwayat_psiko_nilai_kepercayaan_keterangan=?,riwayat_psiko_pendidikan_pj=?,riwayat_psiko_edukasi_diberikan=?,riwayat_psiko_edukasi_diberikan_keterangan=?,riwayat_psiko_wajib=?,riwayat_psiko_ket_halangan_lain=?,riwayat_psiko_toharah=?,riwayat_psiko_sholat=?,riwayat_psiko_motivasi_kesembuhan_ibadah=?,wajah=?,nilaiwajah=?,kaki=?,nilaikaki=?,aktifitas=?,nilaiaktifitas=?,menangis=?,nilaimenangis=?,bersuara=?,nilaibersuara=?,hasilnyeri=?,penilaian_nyeri=?,penilaian_nyeri_penyebab=?,penilaian_nyeri_ket_penyebab=?,penilaian_nyeri_kualitas=?,penilaian_nyeri_ket_kualitas=?,penilaian_nyeri_lokasi=?,penilaian_nyeri_menyebar=?,penilaian_nyeri_waktu=?,penilaian_nyeri_hilang=?,penilaian_nyeri_ket_hilang=?,penilaian_nyeri_diberitahukan_dokter=?,penilaian_nyeri_jam_diberitahukan_dokter=?,resiko_jatuh_usia=?,nilai_resiko_jatuh_usia=?,resiko_jatuh_jk=?,nilai_resiko_jatuh_jk=?,resiko_jatuh_diagnosis=?,nilai_resiko_jatuh_diagnosis=?,resiko_jatuh_gangguan_kognitif=?,nilai_resiko_jatuh_gangguan_kognitif=?,resiko_jatuh_faktor_lingkungan=?,nilai_resiko_jatuh_faktor_lingkungan=?,resiko_jatuh_respon_pembedahan=?,nilai_resiko_jatuh_respon_pembedahan=?,resiko_jatuh_medikamentosa=?,nilai_resiko_jatuh_medikamentosa=?,total_hasil_resiko_jatuh=?,sg1=?,nilai1=?,sg2=?,nilai2=?,sg3=?,nilai3=?,sg4=?,nilai4=?,total_hasil=?,kriteria1=?,kriteria2=?,kriteria3=?,kriteria4=?,pilihan1=?,pilihan2=?,pilihan3=?,pilihan4=?,pilihan5=?,pilihan6=?,pilihan7=?,pilihan8=?,rencana=?,nip1=?,nip2=?,kd_dokter=?",
                217,
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
                    TB.getText(), SistemSarafKepala.getSelectedItem().toString(), KetSistemSarafKepala.getText(),
                    SistemSarafWajah.getSelectedItem().toString(), KetSistemSarafWajah.getText(),
                    SistemSarafLeher.getSelectedItem().toString(), SistemSarafKejang.getSelectedItem().toString(),
                    KetSistemSarafKejang.getText(), SistemSarafSensorik.getSelectedItem().toString(),
                    KardiovaskularDenyutNadi.getSelectedItem().toString(),
                    KardiovaskularSirkulasi.getSelectedItem().toString(), KetKardiovaskularSirkulasi.getText(),
                    KardiovaskularPulsasi.getSelectedItem().toString(),
                    RespirasiPolaNafas.getSelectedItem().toString(), RespirasiRetraksi.getSelectedItem().toString(),
                    RespirasiSuaraNafas.getSelectedItem().toString(), RespirasiVolume.getSelectedItem().toString(),
                    RespirasiJenisPernafasan.getSelectedItem().toString(), KetRespirasiJenisPernafasan.getText(),
                    RespirasiIrama.getSelectedItem().toString(), RespirasiBatuk.getSelectedItem().toString(),
                    GastrointestinalMulut.getSelectedItem().toString(), KetGastrointestinalMulut.getText(),
                    GastrointestinalGigi.getSelectedItem().toString(), KetGastrointestinalGigi.getText(),
                    GastrointestinalLidah.getSelectedItem().toString(), KetGastrointestinalLidah.getText(),
                    GastrointestinalTenggorakan.getSelectedItem().toString(),
                    KetGastrointestinalTenggorakan.getText(), GastrointestinalAbdomen.getSelectedItem().toString(),
                    KetGastrointestinalAbdomen.getText(), GastrointestinalUsus.getSelectedItem().toString(),
                    GastrointestinalAnus.getSelectedItem().toString(),
                    NeurologiPenglihatan.getSelectedItem().toString(), KetNeurologiPenglihatan.getText(),
                    NeurologiAlatBantuPenglihatan.getSelectedItem().toString(),
                    NeurologiPendengaran.getSelectedItem().toString(), NeurologiBicara.getSelectedItem().toString(),
                    KetNeurologiBicara.getText(), NeurologiSensorik.getSelectedItem().toString(),
                    NeurologiMotorik.getSelectedItem().toString(), NeurologiOtot.getSelectedItem().toString(),
                    IntegumentWarnaKulit.getSelectedItem().toString(),
                    IntegumentTurgor.getSelectedItem().toString(), IntegumentKulit.getSelectedItem().toString(),
                    IntegumentDecubitus.getSelectedItem().toString(),
                    MuskuloskletalPegerakanSendi.getSelectedItem().toString(),
                    MuskuloskletalKekuatanOtot.getSelectedItem().toString(),
                    MuskuloskletalNyeriSendi.getSelectedItem().toString(), KetMuskuloskletalNyeriSendi.getText(),
                    MuskuloskletalOedema.getSelectedItem().toString(), KetMuskuloskletalOedema.getText(),
                    MuskuloskletalFraktur.getSelectedItem().toString(), KetMuskuloskletalFraktur.getText(),
                    BAB.getText(), XBAB.getText(), KBAB.getText(), WBAB.getText(), BAK.getText(), XBAK.getText(),
                    WBAK.getText(), LBAK.getText(), Anakke.getText(), DariSaudara.getText(),
                    CaraKelahiran.getSelectedItem().toString(), KetCaraKelahiran.getText(),
                    UmurKelahiran.getSelectedItem().toString(), KelainanBawaan.getSelectedItem().toString(),
                    KetKelainanBawaan.getText(), UsiaTengkurap.getText(), UsiaDuduk.getText(),
                    UsiaBerdiri.getText(), UsiaGigi.getText(), UsiaBerjalan.getText(), UsiaBicara.getText(),
                    UsiaMembaca.getText(), UsiaMenulis.getText(), GangguanEmosi.getText(),
                    PolaAktifitasMakan.getSelectedItem().toString(),
                    PolaAktifitasMandi.getSelectedItem().toString(),
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
                    SkalaWajah.getSelectedItem().toString(), NilaiWajah.getText(),
                    SkalaKaki.getSelectedItem().toString(), NilaiKaki.getText(),
                    SkalaAktifitas.getSelectedItem().toString(), NilaiAktifitas.getText(),
                    SkalaMenangis.getSelectedItem().toString(), NilaiMenangis.getText(),
                    SkalaBersuara.getSelectedItem().toString(), NilaiBersuara.getText(), SkalaNyeri.getText(),
                    Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
                    KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(),
                    Lokasi.getText(), Menyebar.getSelectedItem().toString(), Durasi.getText(),
                    NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(),
                    PadaDokter.getSelectedItem().toString(), KetPadaDokter.getText(),
                    SkalaHumptyDumpty1.getSelectedItem().toString(), NilaiHumptyDumpty1.getText(),
                    SkalaHumptyDumpty2.getSelectedItem().toString(), NilaiHumptyDumpty2.getText(),
                    SkalaHumptyDumpty3.getSelectedItem().toString(), NilaiHumptyDumpty3.getText(),
                    SkalaHumptyDumpty4.getSelectedItem().toString(), NilaiHumptyDumpty4.getText(),
                    SkalaHumptyDumpty5.getSelectedItem().toString(), NilaiHumptyDumpty5.getText(),
                    SkalaHumptyDumpty6.getSelectedItem().toString(), NilaiHumptyDumpty6.getText(),
                    SkalaHumptyDumpty7.getSelectedItem().toString(), NilaiHumptyDumpty7.getText(),
                    NilaiHumptyDumptyTotal.getText(), SG1.getSelectedItem().toString(), NilaiGizi1.getText(),
                    SG2.getSelectedItem().toString(), NilaiGizi2.getText(), SG3.getSelectedItem().toString(),
                    NilaiGizi3.getText(), SG4.getSelectedItem().toString(), NilaiGizi4.getText(),
                    TotalNilaiGizi.getText(), Kriteria1.getSelectedItem().toString(),
                    Kriteria2.getSelectedItem().toString(), Kriteria3.getSelectedItem().toString(),
                    Kriteria4.getSelectedItem().toString(), pilih1, pilih2, pilih3, pilih4, pilih5, pilih6, pilih7,
                    pilih8, Rencana.getText(), KdPetugas.getText(), KdPetugas2.getText(), KdDPJP.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()}) == true) {
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_anak", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_ranap_masalah_anak", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbMasalahKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_anak", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_ranap_rencana_anak", "?,?,?", 3,
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

    private void getImunisasi() {
        try {
            Valid.tabelKosong(tabModeImunisasi2);
            ps = koneksi.prepareStatement(
                    "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                    + "where riwayat_imunisasi.no_rkm_medis=? group by master_imunisasi.kode_imunisasi order by master_imunisasi.kode_imunisasi  ");
            try {
                ps.setString(1, TNoRM1.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    ke1 = false;
                    ke2 = false;
                    ke3 = false;
                    ke4 = false;
                    ke5 = false;
                    ke6 = false;
                    ps2 = koneksi.prepareStatement(
                            "select * from riwayat_imunisasi where riwayat_imunisasi.no_rkm_medis=? and riwayat_imunisasi.kode_imunisasi=?");
                    try {
                        ps2.setString(1, TNoRM1.getText());
                        ps2.setString(2, rs.getString(1));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            if (rs2.getInt("no_imunisasi") == 1) {
                                ke1 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 2) {
                                ke2 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 3) {
                                ke3 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 4) {
                                ke4 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 5) {
                                ke5 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 6) {
                                ke6 = true;
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    tabModeImunisasi2
                            .addRow(new Object[]{rs.getString(1), rs.getString(2), ke1, ke2, ke3, ke4, ke5, ke6});
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

    private void tampilImunisasi() {
        try {
            Valid.tabelKosong(tabModeImunisasi);
            ps = koneksi.prepareStatement(
                    "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                    + "where riwayat_imunisasi.no_rkm_medis=? group by master_imunisasi.kode_imunisasi order by master_imunisasi.kode_imunisasi  ");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    ke1 = false;
                    ke2 = false;
                    ke3 = false;
                    ke4 = false;
                    ke5 = false;
                    ke6 = false;
                    ps2 = koneksi.prepareStatement(
                            "select * from riwayat_imunisasi where riwayat_imunisasi.no_rkm_medis=? and riwayat_imunisasi.kode_imunisasi=?");
                    try {
                        ps2.setString(1, TNoRM.getText());
                        ps2.setString(2, rs.getString(1));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            if (rs2.getInt("no_imunisasi") == 1) {
                                ke1 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 2) {
                                ke2 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 3) {
                                ke3 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 4) {
                                ke4 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 5) {
                                ke5 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 6) {
                                ke6 = true;
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    tabModeImunisasi
                            .addRow(new Object[]{rs.getString(1), rs.getString(2), ke1, ke2, ke3, ke4, ke5, ke6});
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

    private static final Logger LOG = Logger.getLogger(RMPenilaianAwalKeperawatanRanapAnak.class.getName());

}
